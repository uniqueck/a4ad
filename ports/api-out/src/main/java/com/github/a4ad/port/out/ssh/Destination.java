package com.github.a4ad.port.out.ssh;

import com.github.a4ad.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Value
@EqualsAndHashCode(callSuper = false)
public class Destination extends SelfValidating<Destination> {

    @NotNull
    final String host;
    @Min(1)
    final int port;

    private Destination(String host, int port) {
        this.host = host;
        this.port = port;
        validateSelf();
    }

    public static Destination of(String host, int port) {
        return new Destination(host, port);
    }

}

