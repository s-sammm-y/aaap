package com.aaap.Repository.Repositories;

import com.aaap.Model.Models.Letter;
import com.aaap.Repository.Abstractions.ILetterRepository;
import org.springframework.stereotype.Repository;

@Repository
public class LetterRepository implements ILetterRepository {
    private Letter letter;

    public LetterRepository() {

        this.letter = new Letter(
                "myletter",
                "October 5, 2026",
                "To My Favourite Person ❤️",
                "My love,",
                """
                It's been a long time we are together, I wanted to say that I so lucky that I have you.

                Because of you I have changed in a very optimistic way and the credit goes to you as your calm energy has affected me so much I learned to control myself.

                Maybe I'm not the perfect when it comes to emotion management but I have learned a lot from you.

                There has been so much in these years and the only constant thing that fed me energy was our relationship.

                Trust me I would have been a total mess if you were not here.

                Even now I feel that I learning something new from you.

                Thank you for being patient for me.

                You have such a beautiful soul, whenever I'm near you I feel like the earth just changed.

                Like it's a different dimension where everything is very bright.

                Please don't judge I'm just writing what I'm feeling.

                But there is one thing make I won't be able to say vocally is that.

                I'm sorry for disturbing your fun.

                I know I am restricting you in some of your fun places, it's just I'm possessive and I don't wanna lose you.

                It's not intentional I don't want to have fights where it's out of control.

                I really love you, I'm trying to improve myself I will do my best to be the perfect for you.

                Thanks for being there for me.

                You are so beautiful and kindhearted.

                You the definition of a Girl who actually thinks logical.

                I love you bbgg. ❤️
                """,
                "I Mishhhh Youuuu. ❤️",
                "Dakha Korbooooooooooooooo. ❤️"
        );
    }

    @Override
    public Letter getLetter() {
        return this.letter;
    }
}