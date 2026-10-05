package com.aaap.Repository.Abstractions;

import com.aaap.Model.Models.Memory;

import java.util.List;

public interface IMemoriesRepository {
    List<Memory> getAllMemories();
    Memory getMemoryById(String id);
}
