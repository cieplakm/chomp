package com.mmc.chomp.board;

import com.mmc.chomp.app.canonicalmodel.publishedlanguage.AggregateId;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class AggregateIdTests {

    @Test
    public void shouldBeEquals(){
        AggregateId aggregateId1 = AggregateId.create("A");
        AggregateId aggregateId2 = AggregateId.create("A");

        Assertions.assertThat(aggregateId1).isEqualTo(aggregateId2);

    }
}
