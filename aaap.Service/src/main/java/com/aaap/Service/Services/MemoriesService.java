package com.aaap.Service.Services;

import com.aaap.Model.Models.Memory;
import com.aaap.Service.Abstractions.IMemoriesService;
import org.springframework.stereotype.Service;

import com.aaap.Repository.Abstractions.IMemoriesRepository;
import java.util.List;

@Service
public class MemoriesService implements IMemoriesService {
    private final IMemoriesRepository memoriesRepository;

    public MemoriesService(IMemoriesRepository memoriesRepository) {
        this.memoriesRepository = memoriesRepository;
    }

    public List<Memory> getAllMemories() {
        return memoriesRepository.getAllMemories();
    }

    public Memory getMemoryById(String id) {
        return memoriesRepository.getMemoryById(id);
    }
}
