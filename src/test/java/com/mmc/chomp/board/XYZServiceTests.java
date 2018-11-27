package com.mmc.chomp.board;

import com.mmc.chomp.game.Participant;
import com.mmc.chomp.game.board.application.impl.DefaultXYZService;
import com.mmc.chomp.game.board.application.impl.EmbeddedId;
import com.mmc.chomp.game.board.application.service.XYZService;
import com.mmc.chomp.game.board.domain.Size;
import com.mmc.chomp.game.sharedkernel.Position;
import org.junit.Test;

public class XYZServiceTests {


    @Test
    public void shoudl(){
        XYZService service = new DefaultXYZService();

        EmbeddedId id = service.createGame(new Participant(), new Size(5, 5));

        service.joinToGame(id, new Participant());

        service.move(id, new Position(2,2));
        service.move(id, new Position(1,0));
        service.move(id, new Position(0,1));
    }
}
