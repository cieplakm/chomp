package com.mmc.chomp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mmc.chomp.app.canonicalmodel.publishedlanguage.AggregateId;
import com.mmc.chomp.app.game.application.api.service.GameService;
import com.mmc.chomp.app.game.domain.board.Size;
import com.mmc.chomp.app.game.domain.game.Game;
import com.mmc.chomp.app.game.domain.game.GameRepository;
import com.mmc.chomp.app.sharedkernel.Player;
import com.mmc.chomp.app.system.user.User;
import com.mmc.chomp.app.system.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.UUID;

@Slf4j
public class DefaultWebSocketHandler implements WebSocketHandler {

    private GameService gameService;
    private GameRepository gameRepository;
    private UserService userService;


    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {

    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        String userId = UUID.randomUUID().toString();
        AggregateId userAggregateId = new AggregateId(userId);
        User user = userService.get(userAggregateId);
        AggregateId gameAggregateId = gameService.createGame(new Player(userAggregateId, user.getLogin()), new Size(5, 5));
        Game game = gameRepository.get(gameAggregateId);

        GameProjection gameProjection = game.snapshot();
        ObjectMapper objectMapper = new ObjectMapper();
        String gameProjectionJson = objectMapper.writeValueAsString(gameProjection);
        webSocketSession.sendMessage(new TextMessage(gameProjectionJson));
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {

    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
