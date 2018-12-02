package com.mmc.chomp.board;

import com.mmc.chomp.IoC;
import com.mmc.chomp.app.game.application.api.service.GameService;
import com.mmc.chomp.app.game.application.impl.DefaultGameService;
import com.mmc.chomp.app.game.domain.board.Size;
import com.mmc.chomp.app.game.infrastructure.repo.impl.MockUserRepository;
import com.mmc.chomp.app.sharedkernel.Player;
import com.mmc.chomp.app.sharedkernel.Position;
import com.mmc.chomp.app.system.user.DefaultUserService;
import com.mmc.chomp.app.system.user.UserService;
import com.mmc.chomp.app.canonicalmodel.publishedlanguage.AggregateId;
import org.junit.Test;

public class GameServiceTests {
    private GameService service = new DefaultGameService(IoC.gameRepository());

    @Test
    public void shouldCreateGame() {
        UserService userService = new DefaultUserService(new MockUserRepository(), IoC.domainEventPublisher());

        AggregateId annId = userService.create("Ann", "pass");
        AggregateId jonId = userService.create("Jon", "pass");

        Player player1 = new Player(jonId, "Jon");
        Player player2 = new Player(annId, "Ann");

        AggregateId id = service.createGame(player1, new Size(5, 5));

        service.joinToGame(id, player2);

        service.start(id);

        service.move(id, new Position(2, 2));
        service.move(id, new Position(1, 0));
        service.move(id, new Position(0, 1));

        AggregateId id2 = service.createGame(player1, new Size(5, 5));

        service.joinToGame(id2, player2);

        service.start(id2);

        service.move(id2, new Position(2, 2));
        service.move(id2, new Position(1, 0));
        service.move(id2, new Position(0, 1));
    }
}
