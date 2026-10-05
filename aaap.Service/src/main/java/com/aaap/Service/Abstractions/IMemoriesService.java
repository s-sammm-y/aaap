package com.aaap.Service.Abstractions;

import com.aaap.Model.Models.Memory;

import java.util.List;

public interface IMemoriesService {
    public List<Memory> getAllMemories();
    public Memory getMemoryById(String id);
}
