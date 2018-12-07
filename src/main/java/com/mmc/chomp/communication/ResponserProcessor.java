package com.mmc.chomp.communication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mmc.chomp.GameProjection;
import com.mmc.chomp.app.canonicalmodel.publishedlanguage.AggregateId;
import com.mmc.chomp.app.game.application.api.service.GameService;
import com.mmc.chomp.app.game.application.api.service.RankingService;
import com.mmc.chomp.app.game.domain.board.ChocolateBoxValue;
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

@Component
public class ResponserProcessor {

    private ObjectMapper objectMapper = new ObjectMapper();
    private GameService gameService;
    private RankingService rankingService;

    public ResponserProcessor(GameService gameService, RankingService rankingService) {
        this.gameService = gameService;
        this.rankingService = rankingService;
    }

    public String response(RequestDto request) throws JsonProcessingException {
        return objectMapper.writeValueAsString(resolveResponse(request));
    }

    private Response resolveResponse(RequestDto request) {
        CommandType requestType = request.getRequestType();

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

    private Response rating(RequestDto request) {
        RatingRequest ratingRequest = (RatingRequest) request;
        Rank rank = rankingService.get(new AggregateId(request.getUserId()));

        return new RatingResponse(rank.getRank());
    }

    private Response state(RequestDto request) {
        StateRequest startRequest = (StateRequest) request;

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

    private Response start(RequestDto request) {
        StartRequest startRequest = (StartRequest) request;

        gameService.start(new AggregateId(startRequest.getGameId()));

        return null;
    }

    private Response move(RequestDto request) {
        MoveRequest moveRequest = (MoveRequest) request;

        gameService.move(new AggregateId(moveRequest.getGameId()), new Position(moveRequest.getRow(), moveRequest.getCol()));

        return null;
    }

    private Response join(RequestDto request) {
        JoinGameRequest joinGameRequest = (JoinGameRequest) request;

        gameService.joinToGame(new AggregateId(joinGameRequest.getUserId()), new AggregateId(joinGameRequest.getGameId()));

        return null;
    }

    //TODO: convert to command
    private Response createGame(RequestDto request) {
        CreateGameRequest createGameRequest = (CreateGameRequest) request;
        AggregateId gameAggregateId = gameService.createGame(new AggregateId(createGameRequest.getUserId()), createGameRequest.getRows(), createGameRequest.getCols());

        CreateGameResponse response = new CreateGameResponse(gameAggregateId.getId());
        response.setUserId(request.getUserId());
        response.setType(CommandType.CREATE);

        return response;
    }


}
