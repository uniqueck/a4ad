package com.github.a4ad.port.out.logging;

import java.util.function.Supplier;

public interface LoggingPort {


    void debug(Supplier<String> message);
    void info(Supplier<String> message);


}
