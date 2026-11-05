package com.aaap.Model.Models;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class Question {
    private final String text;
    private final List<String> options;
    private final Set<String> correctOptions;

    public String getText(){
        return this.text;
    }

    public List<String> getOptions(){
        return this.options;
    }

    public Set<String> getCorrectOptions(){
        return this.correctOptions;
    }
}
