package org.koreait.controllers;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistForm {
    @NotBlank
    @Size(max = 25)
    private String title;

    @NotBlank
    private String content;
}
