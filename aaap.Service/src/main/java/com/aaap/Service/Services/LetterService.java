package com.aaap.Service.Services;

import com.aaap.Model.Models.Letter;
import com.aaap.Repository.Abstractions.ILetterRepository;
import com.aaap.Service.Abstractions.ILetterService;
import org.springframework.stereotype.Service;

@Service
public class LetterService implements ILetterService {
    private final ILetterRepository letterRepository;

    public LetterService(ILetterRepository letterRepository){
        this.letterRepository = letterRepository;
    }

    public Letter getLetter(){
        return this.letterRepository.getLetter();
    }
}
