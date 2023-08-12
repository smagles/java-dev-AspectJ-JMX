package org.example.converter.aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;


@Component
@Aspect
public class LoggingAspect {
    private static final Logger LOGGER = Logger.getLogger(LoggingAspect.class.getName());


    @Pointcut("execution(public String org.example.converter.TextCaseConverter.convertCase(String)) && args(text)")
    private void convertCase(String text) {
    }

    @AfterReturning(pointcut = "convertCase(text)", returning = "transformedCase")
    public void afterReturningConvertCase(String text, String transformedCase) {
        LOGGER.info("Початковий рядок: " + text);
        LOGGER.info("Перетворений рядок: " + transformedCase);
    }
}
