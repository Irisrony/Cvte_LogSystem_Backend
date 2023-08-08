package com.cvte.logsystem.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
public class User {
    private Integer id;
    @NotBlank
    @Length(min = 6, max = 16)
    private String username;
    @NotBlank
    @Length(min = 8, max = 18)
    private String password;
    private Integer role;
}
