package com.mmc.chomp.app.game.domain.game;

import com.mmc.chomp.app.sharedkernel.Participant;

import java.util.Random;

public class TurnChanger {

    public Participant choose(Participant participant1, Participant participant2){
        Random random = new Random();
        boolean b = random.nextBoolean();

        if (b){
            return participant1;
        }else {
            return participant2;
        }
    }

    public Participant switchTurn(Participant currentTurn, Participant participant1, Participant participant2) {
        Participant currentTurnParticipant;
        if (currentTurn.equals(participant1)){
            currentTurnParticipant = participant2;
        }else {
            currentTurnParticipant = participant1;
        }
        return currentTurnParticipant;
    }
}
