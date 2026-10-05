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
                I don't think I say this enough, but having you in my life
                is one of the most beautiful things that has ever happened
                to me.
            
                Sometimes I think about all the little moments we've shared.
                The random conversations, the silly jokes, the moments where
                we did absolutely nothing and still somehow had the best time.
            
                And those are the moments I treasure the most.
            
                I will always choose you.
                """,
                "I love you. ❤️",
                "Yours always ❤️"
        );
    }

    @Override
    public Letter getLetter(){
        return this.letter;
    }
}
