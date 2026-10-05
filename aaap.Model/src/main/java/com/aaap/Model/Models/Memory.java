package com.aaap.Model.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Memory {
    private String id;
    private String title;
    private String date;
    private String text;
}