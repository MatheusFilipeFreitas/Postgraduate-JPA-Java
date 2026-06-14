package com.mathffreitas.jpaunipds.model.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(name = "RegisterRequest", description = "User registration payload")
public class RegisterRequest {

    @Schema(example = "Jane Doe")
    private String name;

    @Schema(example = "jane.doe@example.com")
    private String email;

    @Schema(example = "secret123")
    private String password;
}
