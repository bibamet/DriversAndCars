package com.example.driversandcars.advice;

import com.example.driversandcars.dto.DriverDto;
import com.example.driversandcars.dto.KafkaMessageDto;
import com.example.driversandcars.entity.log.LogEntity;
import com.example.driversandcars.kafka.producer.Producer;
import com.example.driversandcars.service.LogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

//@Aspect
//@Component
@Slf4j
@RequiredArgsConstructor
//todo RENAME to logical name
public class AdviceProcessor {

    private final LogService logService;
    private final Producer producer;
    private final HttpServletRequest httpServletRequest;

    @Pointcut("execution(public * com.example.driversandcars.controller.DriversController.getDriverByCarId(..)) && args(numberOfCar, ..)")
    public void pointcut(String numberOfCar){};

    @Pointcut("execution(public * com.example.driversandcars.controller.DriversController.addDriver(..)) && args(driver, ..)")
    public void pointcutAdd(DriverDto driver){};

    @Around(value = "pointcut(numberOfCar)")
    public Object aroundProcess(ProceedingJoinPoint pjp, String numberOfCar) throws Throwable {
        Signature sign = pjp.getSignature();
        LocalDateTime timestamp = LocalDateTime.now();
        String clientHost = httpServletRequest.getRemoteHost();
        String method = sign.getDeclaringType() + "." + sign.getName();
        Object result = null;
        Long startTime = System.currentTimeMillis();

        log.debug("before executing {}. Requesting from remote host {} driver's info with carnumber {}",
                method,
                clientHost,
                numberOfCar);

        try {
            result = pjp.proceed();
        } catch (EntityNotFoundException exception) {
            Long endTime = System.currentTimeMillis();
//            logService.saveLog(LogEntity.builder()
//                    .method(method)
//                    .timeStamp(timestamp)
//                    .executionTime(endTime - startTime)
//                    .result(result == null ? null : result.toString())
//                    .exception(exception.toString())
//                    .clientHost(clientHost)
//                    .build());
            producer.sendMessage(KafkaMessageDto.builder()
                    .method(method)
                    .executionTime(endTime - startTime)
                    .result(result == null ? null : result.toString())
                    .exception(exception.getMessage())
                    .clientHost(clientHost)
                    .build());
            log.debug("after executing {}. Returned value {}", method, result);
            throw exception;
        }

        Long endTime = System.currentTimeMillis();
//        logService.saveLog(LogEntity.builder()
//                        .method(method)
//                        .timeStamp(timestamp)
//                        .executionTime(endTime - startTime)
//                        .result(result.toString())
//                        .exception(null)
//                        .clientHost(clientHost)
//                .build());
        producer.sendMessage(KafkaMessageDto.builder()
                .method(method)
                .executionTime(endTime - startTime)
                .result(result == null ? null : result.toString())
                .exception(null)
                .clientHost(clientHost)
                .build());
        log.debug("after executing {}. Returned value {}", method, result);
        return result;
    }

    @Around(value = "pointcutAdd(driver)") // todo Research transaction
    public Object aroundProcessAdd(ProceedingJoinPoint pjp, DriverDto driver) throws Throwable {

        Signature sign = pjp.getSignature();
        LocalDateTime timestamp = LocalDateTime.now();
        String clientHost = httpServletRequest.getRemoteHost();
        String method = sign.getDeclaringType() + "." + sign.getName();
        Long endTime;
        Object result = null;

        Long startTime = System.currentTimeMillis();

        log.debug("before executing {}. Posting from remote host {} driver: {}",
                method,
                clientHost,
                driver);

        try {
            result = pjp.proceed();
        } catch (ConstraintViolationException exception) {

            endTime = System.currentTimeMillis();

//            logService.saveLog(LogEntity.builder()
//                    .method(method)
//                    .timeStamp(timestamp)
//                    .executionTime(endTime - startTime)
//                    .result("false")
//                    .exception(exception.getMessage())
//                    .clientHost(httpServletRequest.getRemoteAddr())
//                    .build());

            producer.sendMessage(KafkaMessageDto.builder()
                    .method(method)
                    .executionTime(endTime - startTime)
                    .result(result == null ? null : result.toString())
                    .exception(exception.getMessage())
                    .clientHost(clientHost)
                    .build());

            log.debug("after executing {}. Exception: {}", method, exception);
            throw exception;

        } catch (DataIntegrityViolationException exception) {

            endTime = System.currentTimeMillis();

//            logService.saveLog(LogEntity.builder()
//                    .method(method)
//                    .timeStamp(timestamp)
//                    .executionTime(endTime - startTime)
//                    .result("false")
//                    .exception(exception.getRootCause().getMessage())
//                    .clientHost(httpServletRequest.getRemoteAddr())
//                    .build());

            producer.sendMessage(KafkaMessageDto.builder()
                    .method(method)
                    .executionTime(endTime - startTime)
                    .result(result == null ? null : result.toString())
                    .exception(exception.getRootCause().getMessage())
                    .clientHost(clientHost)
                    .build());

            log.debug("after executing {}. Exception: {}", method, exception);
            throw exception;

        }
        endTime = System.currentTimeMillis();
//        logService.saveLog(LogEntity.builder()
//                .method(method)
//                .timeStamp(timestamp)
//                .executionTime(endTime - startTime)
//                .result(result.toString())
////                .result(result != null ? String.valueOf(result) : null)
//                .exception(null)
//                .clientHost(clientHost)
//                .build());

        producer.sendMessage(KafkaMessageDto.builder()
                .method(method)
                .executionTime(endTime - startTime)
                .result(result != null ? String.valueOf(result) : null)
                .exception(null)
                .clientHost(clientHost)
                .build());

        log.debug("after executing {}. Returned value {}", method, result);
        return result;

    }

}
