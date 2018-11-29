package com.mmc.chomp.app.sharedkernel;

import com.mmc.chomp.ddd.annotation.domain.support.ParticipantData;
import com.mmc.chomp.app.canonicalmodel.publishedlanguage.BaseAgregateRoot;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class Participant extends BaseAgregateRoot {
    private String login;

    public ParticipantData snapshot(){
        return new ParticipantData(aggregateId, login);
    }

}
