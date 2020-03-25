package com.github.a4ad.adapters.logging;

import com.github.a4ad.port.out.logging.LoggingPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;


class LoggingAdapter implements LoggingPort {

    private final Logger logger;

    public LoggingAdapter(final Class<?> clazz) {
        this.logger = LoggerFactory.getLogger(clazz);
    }


    @Override
    public void debug(Supplier<String> message) {
        if (logger.isDebugEnabled()) {
            logger.debug(message.get());
        }
    }

    @Override
    public void info(Supplier<String> message) {
        if (logger.isInfoEnabled()) {
            logger.info(message.get());
        }
    }
}
