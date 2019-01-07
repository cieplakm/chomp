package com.mmc.chomp.app.game.domain;

import com.mmc.chomp.app.game.domain.board.Size;
import com.mmc.chomp.app.game.domain.game.events.PlayerAddedToWaitingListEvent;
import com.mmc.chomp.app.game.domain.game.events.OpponentFoundEvent;
import com.mmc.chomp.ddd.support.domain.DomainEventPublisher;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Component
public class DefaultWaitingList implements WaitingList {

    private DomainEventPublisher eventPublisher;

    private Set<Waiter> waiters;

    @Autowired
    DefaultWaitingList(DomainEventPublisher eventPublisher) {
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
        } else {
            waiters.add(newly);
            eventPublisher.publish(new PlayerAddedToWaitingListEvent(newly.userId, size));
            log.info("Opponent not found. User {} added to waiting list.", newly.userId);
        }
    }

    @Override
    public boolean isStillOnList(AggregateId playerId){
        for (Waiter waiter : waiters) {
            if (waiter.getUserId().equals(playerId)){
                return true;
            }
        }

        return false;
    }

    @Override
    public void remove(AggregateId waiterId) {
        Iterator<Waiter> iterator = waiters.iterator();
        while (iterator.hasNext()){
            Waiter waiter = iterator.next();
            if (waiter.getUserId().equals(waiterId)){
                waiters.remove(waiter);
                log.info("User ({}) removed from waiting list.", waiterId);
                return;
            }
        }
    }

    private void remove(Waiter waiter) {
        remove(waiter.getUserId());
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
