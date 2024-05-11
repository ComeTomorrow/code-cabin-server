package org.example.authentication.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterDetails {

    @NotBlank(message = "手机号不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "确认密码不能为空")
    private String againPassword;

    private Boolean enabled;
}
