package com.aaap.webapi.Controllers;

import com.aaap.Model.Models.Memory;
import com.aaap.Service.Abstractions.IMemoriesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller()
@RequestMapping("memories")
public class MemoriesController {
    private final IMemoriesService memoriesService;

    public MemoriesController(IMemoriesService memoriesService){
        this.memoriesService = memoriesService;
    }

    @GetMapping
    public String getMemories(Model model) {

        List<Memory> memories = memoriesService.getAllMemories();

        model.addAttribute("memories", memories);

        return "memories";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Memory getMemory(@PathVariable String id) {

        return memoriesService.getMemoryById(id);
    }
}
