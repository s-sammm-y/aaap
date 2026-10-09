
package com.aaap.Service.Services;

import com.aaap.Model.Models.KissGame;
import com.aaap.Service.Abstractions.IKissGameService;
import org.springframework.stereotype.Service;

@Service
public class KissGameService implements IKissGameService {

    @Override
    public void move(KissGame game, String direction) {
        game.move(direction);
    }

    @Override
    public void reset(KissGame game) {
        game.reset();
    }
}
