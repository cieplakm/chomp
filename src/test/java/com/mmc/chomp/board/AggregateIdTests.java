package com.mmc.chomp.board;

import com.mmc.chomp.ddd.annotation.domain.support.AggregateId;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class AggregateIdTests {

    @Test
    public void shouldBeEquals(){
        AggregateId aggregateId1 = new AggregateId("A");
        AggregateId aggregateId2 = new AggregateId("A");

        Assertions.assertThat(aggregateId1).isEqualTo(aggregateId2);
    }
}
