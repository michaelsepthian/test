package com.michael.test.config.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecurityAspect {

    private static final Logger logger = LoggerFactory.getLogger(SecurityAspect.class);

    @Around("@annotation(org.springframework.security.access.annotation.Secured)")
    public Object measureSecuredMethodPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Object result = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - startTime;

        logger.info("Security Performance: User '{}' accessed secured method '{}.{}()' - Execution Time: {} ms",
                username, className, methodName, executionTime);

        return result;
    }
}