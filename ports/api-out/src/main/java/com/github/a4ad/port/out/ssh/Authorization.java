package com.github.a4ad.port.out.ssh;

import com.github.a4ad.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
@EqualsAndHashCode(callSuper = false)
public class Authorization extends SelfValidating<Authorization> {

    @NotBlank
    final String username;
    @NotBlank
    String password;


    private Authorization(String username, String password) {
        this.username = username;
        this.password = password;
        validateSelf();
    }

    public static Authorization byPassword(String username, String password) {
        return new Authorization(username, password);
    }
}