package org.example.common.web.aspect;

import cn.hutool.core.util.StrUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.example.common.core.enums.ResultCode;
import org.example.common.web.annotation.PreventDuplicateResubmit;
import org.example.common.web.exception.BizException;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.concurrent.TimeUnit;

/**
 * 防重复提交切面
 *
 * @author
 * @since 2023/05/09
 */
@Aspect
@Component
@Slf4j
public class DuplicateSubmitAspect {

    @Autowired
    private RedissonClient redissonClient;

    private static final String RESUBMIT_LOCK_PREFIX = "LOCK:RESUBMIT:";

    /**
     * 防重复提交切点
     */
    @Pointcut("@annotation(preventDuplicateResubmit)")
    public void preventDuplicateSubmitPointCut(PreventDuplicateResubmit preventDuplicateResubmit) {
        log.info("定义防重复提交切点");
    }

    @Around("preventDuplicateSubmitPointCut(preventDuplicateResubmit)")
    public Object doAround(ProceedingJoinPoint pjp, PreventDuplicateResubmit preventDuplicateResubmit) throws Throwable {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

//        String jti = SecurityUtils.getJti();
//        if (StrUtil.isNotBlank(jti)) {
//            String resubmitLockKey = RESUBMIT_LOCK_PREFIX + jti + ":" + request.getMethod() + "-" + request.getRequestURI();
//            int expire = preventDuplicateResubmit.expire(); // 防重提交锁过期时间
//            RLock lock = redissonClient.getLock(resubmitLockKey);
//            boolean lockResult = lock.tryLock(0, expire, TimeUnit.SECONDS); // 获取锁失败，直接返回 false
//            if (!lockResult) {
//                throw new BizException(ResultCode.REPEAT_SUBMIT_ERROR); // 抛出重复提交提示信息
//            }
//        }
        return pjp.proceed();
    }

}