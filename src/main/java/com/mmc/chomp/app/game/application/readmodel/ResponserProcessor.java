//package com.mmc.chomp.app.game.application.command;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.mmc.chomp.app.game.application.readmodel.GameProjection;
//import com.mmc.chomp.app.canonicalmodel.publishedlanguage.AggregateId;
//import com.mmc.chomp.app.game.application.api.service.GameService;
//import com.mmc.chomp.app.game.application.api.service.RankingService;
//import com.mmc.chomp.app.game.application.command.Command;
//import com.mmc.chomp.app.game.application.command.impl.CreateGameCommand;
//import com.mmc.chomp.app.game.application.command.impl.Join2GameCommand;
//import com.mmc.chomp.app.game.application.command.impl.MoveCommand;
//import com.mmc.chomp.app.game.application.readmodel.RatingBasicCommand;
//import com.mmc.chomp.app.game.application.command.impl.StartGameCommand;
//import com.mmc.chomp.app.game.application.readmodel.StateBasicCommand;
//import com.mmc.chomp.app.game.domain.board.ChocolateValue;
//import com.mmc.chomp.app.game.domain.ranking.Rank;
//import com.mmc.chomp.app.sharedkernel.Position;
//import com.mmc.chomp.app.game.application.readmodel.response.CreateGameResponse;
//import com.mmc.chomp.app.game.application.readmodel.response.RatingResponse;
//import com.mmc.chomp.app.game.application.readmodel.response.Response;
//import com.mmc.chomp.app.game.application.readmodel.response.StateResponse;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.io.Serializable;
//
//@Component
//public class ResponserProcessor {
//
//    private ObjectMapper objectMapper;
//    private GameService gameService;
//    private RankingService rankingService;
//
//    public ResponserProcessor(GameService gameService, RankingService rankingService, ObjectMapper objectMapper) {
//        this.gameService = gameService;
//        this.rankingService = rankingService;
//        this.objectMapper = objectMapper;
//    }
//
//    public String response(Serializable request) throws IOException {
//        return objectMapper.writeValueAsString(resolveResponse(request));
//    }
//
//    private Response resolveResponse(Serializable request) throws IOException {
//        Command basicCommandDto = objectMapper.readValue((String) request, Command.class);
//        CommandType requestType = basicCommandDto.getRequestType();
//
//        switch (requestType) {
//            case CREATE:
//                return createGame(request);
//            case JOIN:
//                return join(request);
//            case MOVE:
//                return move(request);
//            case START:
//                return start(request);
//            case STATE:
//                return state(request);
//            case RATING:
//                return rating(request);
//
//        }
//
//        throw new RuntimeException("No command found");
//    }
//
//    private <T> T map(Serializable serializable, Class<? extends T> clazz) {
//        try {
//            return objectMapper.readValue((String) serializable, clazz);
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException("Serialization error");
//        }
//
//    }
//
//    private Response rating(Serializable request) {
//        RatingBasicCommand ratingRequest = map(request, RatingBasicCommand.class);
//        Rank rank = rankingService.get(new AggregateId(ratingRequest.getUserId()));
//
//        return new RatingResponse(rank.getRank());
//    }
//
//    private Response state(Serializable request) {
//        StateBasicCommand startRequest = map(request, StateBasicCommand.class);
//
//        GameProjection projection = gameService.projection(new AggregateId(startRequest.getGameId()));
//        StateResponse response = new StateResponse(
//                projection.getStatus(),
//                mapChocolateToBoolean(projection.getBoard().getChocolateValue()),
//                projection.getCreatorId(),
//                projection.getJoinerId(),
//                projection.getWinnerId()
//        );
//        return response;
//    }
//
//    private boolean[][] mapChocolateToBoolean(ChocolateValue[][] chocolateValue) {
//        boolean[][] booleans = new boolean[chocolateValue.length][];
//        for (int i = 0; i < chocolateValue.length; i++) {
//            ChocolateValue[] chocolateValues = chocolateValue[i];
//            boolean[] row = new boolean[chocolateValues.length];
//
//            for (int k = 0; k < chocolateValues.length; k++) {
//                ChocolateValue value = chocolateValue[i][k];
//                row[k] = value.isTaken();
//            }
//
//            booleans[i] = row;
//        }
//        return booleans;
//    }
//
//    private Response start(Serializable request) {
//        StartGameCommand startRequest = map(request, StartGameCommand.class);
//
//        gameService.start(new AggregateId(startRequest.getGameId()));
//
//        return null;
//    }
//
//    private Response move(Serializable request) {
//        MoveCommand moveRequest = map(request, MoveCommand.class);
//
//        gameService.move(new AggregateId(moveRequest.getGameId()), new Position(moveRequest.getRow(), moveRequest.getCol()));
//
//        return null;
//    }
//
//    private Response join(Serializable request) {
//        Join2GameCommand joinGameRequest = map(request, Join2GameCommand.class);
//
//        gameService.joinToGame(new AggregateId(joinGameRequest.getUserId()), new AggregateId(joinGameRequest.getGameId()));
//
//        return null;
//    }
//
//    //TODO: convert to command
//    private Response createGame(Serializable request) {
//        CreateGameCommand createGameRequest = map(request, CreateGameCommand.class);
//        AggregateId gameAggregateId = gameService.createGame(new AggregateId(createGameRequest.getUserId()), createGameRequest.getRows(), createGameRequest.getCols());
//
//        CreateGameResponse response = new CreateGameResponse(gameAggregateId.getId());
//        response.setUserId(createGameRequest.getUserId());
//        response.setType(CommandType.CREATE);
//
//        return response;
//    }
//
//
//}
