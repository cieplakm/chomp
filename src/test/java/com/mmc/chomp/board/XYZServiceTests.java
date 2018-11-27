package com.mmc.chomp.board;

import com.mmc.chomp.game.Participant;
import com.mmc.chomp.game.board.application.impl.DefaultXYZService;
import com.mmc.chomp.ddd.annotation.domain.support.EmbeddedId;
import com.mmc.chomp.game.board.application.service.XYZService;
import com.mmc.chomp.game.board.domain.Size;
import com.mmc.chomp.game.sharedkernel.Position;
import org.junit.Test;

public class XYZServiceTests {


    @Test
    public void shoudl(){
        XYZService service = new DefaultXYZService();

        Participant participant1 = new Participant(EmbeddedId.generate(), "Jon");
        Participant participant2 = new Participant(EmbeddedId.generate(), "Ann");

        EmbeddedId id = service.createGame(participant1, new Size(5, 5));

        service.joinToGame(id, participant2);

        service.start(id);

        service.move(id, new Position(2,2));
        service.move(id, new Position(1,0));
        service.move(id, new Position(0,1));
        service.move(id, new Position(0,1));


    }
}
