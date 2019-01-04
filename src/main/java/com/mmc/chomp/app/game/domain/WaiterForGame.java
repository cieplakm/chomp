package com.mmc.chomp.app.game.domain;

import com.mmc.chomp.app.game.domain.board.Size;
import com.mmc.chomp.ddd.support.domain.DomainEventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component //TODO: refactor it to DOMAIN!
public class WaiterForGame {

    private Set<Waiter> waiters = new HashSet<>();

    @Autowired
    private DomainEventPublisher domainEventPublisher;

    public void signToWaiterList(AggregateId userId, Size size){
        waiters.add(new Waiter(userId, size));
        log.info("Player with id {} added to waiting list with size {}x{}", userId.getId(), size.getRows(), size.getCols());

        find();
    }

    private void find() {
        for (Waiter waiterOne : waiters) {
            for (Waiter waiterTwo : waiters) {
                if (!waiterOne.userId.equals(waiterTwo.userId)){
                    if (waiterOne.size.equals(waiterTwo.size)){
                        //emitEvent
                        log.info("Found partners {} and {}", waiterOne.userId.getId(), waiterTwo.userId.getId());
                        return;
                    }
                }
            }
        }
    }

    private class Waiter {
        private Size size;
        private AggregateId userId;

        private Waiter(AggregateId userId, Size size) {
            this.userId = userId;
            this.size = size;
        }
    }

}
