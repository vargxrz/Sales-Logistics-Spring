package org.example.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CredenciaisDTO {

    private String login;
    private String senha;
}
