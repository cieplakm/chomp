package com.mmc.chomp.app.game.domain;

import com.mmc.chomp.app.game.domain.board.Size;
import com.mmc.chomp.app.game.domain.game.events.FakePlayerNeededEvent;
import com.mmc.chomp.app.game.domain.game.events.OpponentFoundEvent;
import com.mmc.chomp.ddd.support.domain.DomainEventPublisher;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

@Slf4j
@Component
public class DefaultWaingList implements WaitingList {

    private DomainEventPublisher eventPublisher;
    private Set<Waiter> waiters;

    @Autowired
    DefaultWaingList(DomainEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
        waiters = new HashSet<>();
    }

    @Override
    public void signToWaitingList(AggregateId userId, Size size) {
        Waiter newly = new Waiter(userId, size);
        Optional<Waiter> optionalWaiter = find(newly);
        if (optionalWaiter.isPresent()) {
            log.info("User ({}) found opponent ({})", newly.userId, optionalWaiter.get().userId);
            Waiter waiter = optionalWaiter.get();
            eventPublisher.publish(new OpponentFoundEvent(newly.userId, waiter.userId, size));
            remove(waiter);
        }else {
            waiters.add(newly);
            log.info("Opponent not found. User {} added to waiting list.", newly.userId);

            TimerTask fakePlayerNeededEvent = new TimerTask() {
                @Override
                public void run() {
                    if (waiters.contains(newly)) {
                        log.info("User ({}) needs opponent", newly.userId);
                        eventPublisher.publish(new FakePlayerNeededEvent(newly.userId, size));
                        remove(newly);
                    }else {
                        log.info("User ({}) doesn't need opponent", newly.userId);
                    }
                }
            };

            Timer timer = new Timer();
            long delay = 1000L * 10;
            timer.schedule(fakePlayerNeededEvent, delay);
        }
    }

    private void remove(Waiter waiter){
        waiters.remove(waiter);
        log.info("User ({}) removed from waiting list.", waiter.userId);
    }

    private Optional<Waiter> find(Waiter newly) {
        for (Waiter waiter : waiters) {
            if (isMatch(newly, waiter)) {
                return Optional.of(waiter);
            }
        }
        return Optional.empty();
    }

    private boolean isMatch(Waiter newly, Waiter waiter) {
        return newly.size.equals(waiter.size) && !newly.userId.equals(waiter.userId);
    }

    @Value
    private class Waiter {
        private AggregateId userId;
        private Size size;
    }
}
