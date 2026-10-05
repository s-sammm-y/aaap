package com.aaap.Model.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Letter {
    private String id;
    private String date;
    private String title;
    private String greeting;
    private String content;
    private String closing;
    private String signature;
}
