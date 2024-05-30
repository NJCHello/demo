package com.example.springevent.aop;

import com.example.springevent.annotation.LogStopWatch;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * @author chenghao
 * @since 2024/5/30 14:03
 */
@Slf4j
@Aspect
@Component
public class LogStopWatchAspect {

    /**
     * 定义切面
     * - 此处代表com.example.demo.module.controller包下的所有接口都会被统计
     */
    @Pointcut("@annotation(com.example.springevent.annotation.LogStopWatch)")
    // @Pointcut("execution(* com.example.springevent..*.*(..))")
    public void logStopWatch() {}

    @Around("logStopWatch()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        String className = point.getSignature().getDeclaringType().getSimpleName();
        String methodName = point.getSignature().getName();
        LogStopWatch annotation = getAnnotation(point);
        StopWatch stopWatch = new StopWatch(annotation.id());
        stopWatch.start(annotation.taskName());
        //切点方法
        Object proceed = point.proceed();
        stopWatch.stop();
        logStopWatch(stopWatch, className, methodName);
        return proceed;
    }

    private LogStopWatch getAnnotation(ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature)point.getSignature();
        return signature.getMethod().getAnnotation(LogStopWatch.class);
    }

    private void logStopWatch(StopWatch stopWatch, String className, String methodName) {
        log.info("{}.{}.stopWatch.prettyPrint:{}", className, methodName, stopWatch.prettyPrint());
        log.info("{}.{}.stopWatch.getTotalTimeSeconds:{}", className, methodName, stopWatch.getTotalTimeSeconds());
        //log.info("{}.{}.stopWatch.getTaskInfo:{}", className, methodName, JSONObject.toJSONString(stopWatch.getTaskInfo()));
    }
}
