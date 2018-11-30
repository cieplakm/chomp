package com.mmc.chomp.app.game.application.api.service;

import com.mmc.chomp.app.canonicalmodel.publishedlanguage.PlayerData;

public interface RankingService {

    void changeRanking(PlayerData winner, PlayerData looser);

    void create(PlayerData playerData);

}
