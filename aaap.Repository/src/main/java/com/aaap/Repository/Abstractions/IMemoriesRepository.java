package com.aaap.Repository.Abstractions;

import com.aaap.Model.Models.Memory;

import java.util.List;

public interface IMemoriesRepository {
    public List<Memory> getAllMemories();
    public Memory getMemoryById(String id);
}
