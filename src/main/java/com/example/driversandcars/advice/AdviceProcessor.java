package com.example.driversandcars.advice;
import com.example.driversandcars.entity.log.LogEntity;
import com.example.driversandcars.service.LogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class AdviceProcessor {

    private final LogService logService;

    private final HttpServletRequest httpServletRequest;

    @Pointcut("execution(public * com.example.driversandcars.controller.DriversAndCarsController.getDriverByCarId(..)) && args(numberOfCar, ..)")
    public void pointcut(String numberOfCar){};

    @Around(value = "pointcut(numberOfCar)")
    public Object aroundProcess(ProceedingJoinPoint pjp, String numberOfCar) throws Throwable {

        Signature sign = pjp.getSignature();
        LocalDateTime timestamp = LocalDateTime.now();
        String clientHost = httpServletRequest.getRemoteHost();
        String method = sign.getDeclaringType() + "." + sign.getName();

        Long startTime = System.currentTimeMillis();

        log.debug("before executing {}. Requesting from remote host {} driver's info with carnumber {}",
                method,
                httpServletRequest.getRemoteHost(),
                numberOfCar);

        Object result = pjp.proceed();

        Long endTime = System.currentTimeMillis();

        logService.saveLog(LogEntity.builder()
                        .method(method)
                        .timeStamp(timestamp)
                        .executionTime(endTime - startTime)
                        .result(result.toString())
                        .exception(null)
                        .clientHost(clientHost)
                .build());

        log.debug("after executing {}.{}. Returned value {}", sign.getDeclaringType(), sign.getName(), result);

        return result;

    }

}
