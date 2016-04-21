package com.drug.platform.controller.aop;

import com.drug.platform.controller.ControllerUtil;
import com.drug.platform.controller.Pages;
import com.drug.platform.controller.annotation.UserAuthenticate;
import com.drug.platform.controller.annotation.UserType;
import com.drug.platform.model.SessionUser;
import com.drug.platform.utils.Assert;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Yaochao on 2016/3/20.
 */
@Component
@Aspect
public class UserAuthenticateAspect implements Ordered {

    @Pointcut("execution(public * com.drug.platform.controller..*.*(..))")
    public void userAuthenticate() {
    }

    @Around("userAuthenticate()")
    public Object before(ProceedingJoinPoint joinPoint) throws Throwable {
        UserAuthenticate userAuthenticate = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(UserAuthenticate.class);
        if (Assert.notNull(userAuthenticate)) {
            Object[] args = joinPoint.getArgs();
            for (Object arg : args) {
                if (arg instanceof HttpServletRequest) {
                    HttpServletRequest request = (HttpServletRequest) arg;
                    SessionUser sessionUser = ControllerUtil.getSessionUser(request);
                    if (Assert.notNull(sessionUser)) {
                        UserType[] userTypes = userAuthenticate.value();
                        for (UserType userType : userTypes) {
                            if (userType.toString().equals(sessionUser.getUserType())) {
                                return joinPoint.proceed();
                            }
                        }
                        return Pages.ERROR.AUTHENTICATION_FAILED;
                    } else {
                        return Pages.REDIRECT_LOGIN;
                    }
                }
            }
            return Pages.ERROR.AUTHENTICATION_FAILED;
        }
        return joinPoint.proceed();
    }

    /**
     * Return the order value of this object, with a
     * higher value meaning greater in terms of sorting.
     * <p>Normally starting with 0, with {@code Integer.MAX_VALUE}
     * indicating the greatest value. Same order values will result
     * in arbitrary positions for the affected objects.
     * <p>Higher values can be interpreted as lower priority. As a
     * consequence, the object with the lowest value has highest priority
     * (somewhat analogous to Servlet "load-on-startup" values).
     *
     * @return the order value
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
