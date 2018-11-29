package com.mmc.chomp.app.game.domain.ranking;

import com.mmc.chomp.app.canonicalmodel.publishedlanguage.BaseAgregateRoot;
import com.mmc.chomp.ddd.annotation.domain.support.AggregateId;
import com.mmc.chomp.ddd.annotation.domain.support.ParticipantData;

public class Rank extends BaseAgregateRoot {

    private ParticipantData participantData;
    private long rank;

    public static Rank create(AggregateId participantId) {
        Rank rank = new Rank();
        rank.participantData = new ParticipantData(participantId, "BLE");
        return rank;
    }

    public void up() {
        rank++;
    }

    public void down() {
        rank--;
    }

    public long getRank(){
        return rank;
    }

    public ParticipantData getParticipantData() {
        return participantData;
    }
}
