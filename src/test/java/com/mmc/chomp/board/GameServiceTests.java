package com.mmc.chomp.board;

import com.mmc.chomp.app.game.application.api.service.GameService;
import com.mmc.chomp.app.sharedkernel.Participant;
import com.mmc.chomp.app.game.application.impl.DefaultGameService;
import com.mmc.chomp.ddd.annotation.domain.support.AggregateId;

import com.mmc.chomp.app.game.domain.board.Size;
import com.mmc.chomp.app.sharedkernel.Position;
import org.junit.Test;

public class GameServiceTests {
    private GameService service = new DefaultGameService();

    @Test
    public void shouldCreateGame(){
        Participant participant1 = new Participant("Jon");
        Participant participant2 = new Participant("Ann");

        AggregateId id = service.createGame(participant1, new Size(5, 5));



        service.joinToGame(id, participant2);

        service.start(id);

        service.move(id, new Position(2,2));
        service.move(id, new Position(1,0));
        service.move(id, new Position(0,1));

    }
}
