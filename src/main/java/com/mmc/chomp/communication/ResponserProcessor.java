package com.mmc.chomp.communication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mmc.chomp.GameProjection;
import com.mmc.chomp.app.canonicalmodel.publishedlanguage.AggregateId;
import com.mmc.chomp.app.game.application.api.service.GameService;
import com.mmc.chomp.app.game.application.api.service.RankingService;
import com.mmc.chomp.app.game.domain.board.ChocolateValue;
import com.mmc.chomp.app.game.domain.ranking.Rank;
import com.mmc.chomp.app.sharedkernel.Position;
import com.mmc.chomp.communication.request.CreateGameRequest;
import com.mmc.chomp.communication.request.JoinGameRequest;
import com.mmc.chomp.communication.request.MoveRequest;
import com.mmc.chomp.communication.request.RatingRequest;
import com.mmc.chomp.communication.request.RequestDto;
import com.mmc.chomp.communication.request.StartRequest;
import com.mmc.chomp.communication.request.StateRequest;
import com.mmc.chomp.communication.response.CreateGameResponse;
import com.mmc.chomp.communication.response.RatingResponse;
import com.mmc.chomp.communication.response.Response;
import com.mmc.chomp.communication.response.StateResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;

@Component
public class ResponserProcessor {

    private ObjectMapper objectMapper;
    private GameService gameService;
    private RankingService rankingService;

    public ResponserProcessor(GameService gameService, RankingService rankingService, ObjectMapper objectMapper) {
        this.gameService = gameService;
        this.rankingService = rankingService;
        this.objectMapper = objectMapper;
    }

    public String response(Serializable request) throws IOException {
        return objectMapper.writeValueAsString(resolveResponse(request));
    }

    private Response resolveResponse(Serializable request) throws IOException {
        RequestDto requestDto = objectMapper.readValue((String) request, RequestDto.class);
        CommandType requestType = requestDto.getRequestType();

        switch (requestType) {
            case CREATE:
                return createGame(request);
            case JOIN:
                return join(request);
            case MOVE:
                return move(request);
            case START:
                return start(request);
            case STATE:
                return state(request);
            case RATING:
                return rating(request);

        }

        throw new RuntimeException("No command found");
    }

    private <T> T map(Serializable serializable, Class<? extends T> clazz) {
        try {
            return objectMapper.readValue((String) serializable, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Serialization error");
        }

    }

    private Response rating(Serializable request) {
        RatingRequest ratingRequest = map(request, RatingRequest.class);
        Rank rank = rankingService.get(new AggregateId(ratingRequest.getUserId()));

        return new RatingResponse(rank.getRank());
    }

    private Response state(Serializable request) {
        StateRequest startRequest = map(request, StateRequest.class);

        GameProjection projection = gameService.projection(new AggregateId(startRequest.getGameId()));
        StateResponse response = new StateResponse(
                projection.getStatus(),
                mapChocolateToBoolean(projection.getBoard().getChocolateValue()),
                projection.getCreatorId(),
                projection.getJoinerId(),
                projection.getWinnerId()
        );
        return response;
    }

    private boolean[][] mapChocolateToBoolean(ChocolateValue[][] chocolateValue) {
        boolean[][] booleans = new boolean[chocolateValue.length][];
        for (int i = 0; i < chocolateValue.length; i++) {
            ChocolateValue[] chocolateValues = chocolateValue[i];
            boolean[] row = new boolean[chocolateValues.length];

            for (int k = 0; k < chocolateValues.length; k++) {
                ChocolateValue value = chocolateValue[i][k];
                row[k] = value.isTaken();
            }

            booleans[i] = row;
        }
        return booleans;
    }

    private Response start(Serializable request) {
        StartRequest startRequest = map(request, StartRequest.class);

        gameService.start(new AggregateId(startRequest.getGameId()));

        return null;
    }

    private Response move(Serializable request) {
        MoveRequest moveRequest = map(request, MoveRequest.class);

        gameService.move(new AggregateId(moveRequest.getGameId()), new Position(moveRequest.getRow(), moveRequest.getCol()));

        return null;
    }

    private Response join(Serializable request) {
        JoinGameRequest joinGameRequest = map(request, JoinGameRequest.class);

        gameService.joinToGame(new AggregateId(joinGameRequest.getUserId()), new AggregateId(joinGameRequest.getGameId()));

        return null;
    }

    //TODO: convert to command
    private Response createGame(Serializable request) {
        CreateGameRequest createGameRequest = map(request, CreateGameRequest.class);
        AggregateId gameAggregateId = gameService.createGame(new AggregateId(createGameRequest.getUserId()), createGameRequest.getRows(), createGameRequest.getCols());

        CreateGameResponse response = new CreateGameResponse(gameAggregateId.getId());
        response.setUserId(createGameRequest.getUserId());
        response.setType(CommandType.CREATE);

        return response;
    }


}
