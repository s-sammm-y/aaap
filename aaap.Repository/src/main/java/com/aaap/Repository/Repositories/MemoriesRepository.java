package com.aaap.Repository.Repositories;

import com.aaap.Model.Models.Memory;
import com.aaap.Repository.Abstractions.IMemoriesRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemoriesRepository implements IMemoriesRepository {
    private final List<Memory> memories = List.of(

            new Memory(
                    "beginning",
                    "The Beginning ❤️",
                    "The day we met",
                    """
                    I didn't know that meeting you
                    would become such an important
                    part of my life.
        
                    At that moment, I had no idea
                    how many memories we would
                    eventually create together.
        
                    And honestly...
        
                    I'm really glad it happened.
                    """
            ),

            new Memory(
                    "firstDate",
                    "Our First Date ❤️",
                    "A day I'll always remember",
                    """
                    I still remember how nervous
                    I was that day.
        
                    There were probably a hundred
                    things going through my head.
        
                    But somewhere between the
                    conversations, the laughs and
                    all the little awkward moments,
        
                    I realised how much I loved
                    being around you.
                    """
            ),

            new Memory(
                    "favourite",
                    "My Favourite Memory ❤️",
                    "One of many",
                    """
                    People might think my favourite
                    memory would be some huge
                    special event.
        
                    But honestly...
        
                    it's the ordinary moments.
        
                    The random conversations.
                    The stupid jokes.
                    The times we do absolutely
                    nothing together.
        
                    Those are the moments I
                    never want to forget.
                    """
            )
    );

    public List<Memory> getAllMemories() {
        return memories;
    }

    public Memory getMemoryById(String id) {
        return memories.stream()
                .filter(memory -> memory.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
