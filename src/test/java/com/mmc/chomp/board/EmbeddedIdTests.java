package com.mmc.chomp.board;

import com.mmc.chomp.game.board.application.impl.EmbeddedId;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class EmbeddedIdTests {

    @Test
    public void shouldBeEquals(){
        EmbeddedId embeddedId1 = new EmbeddedId("A");
        EmbeddedId embeddedId2 = new EmbeddedId("A");

        Assertions.assertThat(embeddedId1).isEqualTo(embeddedId2);
    }
}
