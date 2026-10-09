package com.aaap.Service.Abstractions;

import com.aaap.Model.Models.KissGame;

public interface IKissGameService {
    void move(KissGame game, String direction);

    void reset(KissGame game);
}
