package com.txacon.gap.application.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class MethodLogger {

    @Around("@annotation(com.txacon.gap.application.aspect.Loggable)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = point.proceed();
        log.info(
                "{} ({}): {} - {} in %[msec]s",
                ((MethodSignature) point.getSignature()).getMethod().getName(),
                point.getArgs(),
                result,
                System.currentTimeMillis() - start
        );
        return result;
    }
}
