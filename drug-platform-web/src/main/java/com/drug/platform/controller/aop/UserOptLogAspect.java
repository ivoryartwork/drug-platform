package com.drug.platform.controller.aop;

import com.drug.platform.controller.ControllerUtil;
import com.drug.platform.controller.annotation.UserOptLog;
import com.drug.platform.dao.UserOptLogDAO;
import com.drug.platform.model.SessionUser;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by Yaochao on 2016/5/20.
 */
@Component
@Aspect
public class UserOptLogAspect implements Ordered {

    private static final Logger LOGGER = Logger.getLogger(UserOptLogAspect.class);

    @Resource
    private UserOptLogDAO userOptLogDAO;

    @Pointcut("execution(public * com.drug.platform.controller..*.*(..))")
    public void userOptLog() {
    }

    @After("userOptLog()")
    public void after(JoinPoint joinPoint) throws Throwable {
        UserOptLog userOptLog = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(UserOptLog.class);
        if (userOptLog != null && !userOptLog.optDes().equals("")) {
            Object[] args = joinPoint.getArgs();
            for (Object arg : args) {
                if (arg instanceof HttpServletRequest) {
                    try {
                        HttpServletRequest request = (HttpServletRequest) arg;
                        SessionUser sessionUser = ControllerUtil.getSessionUser(request);
                        com.drug.platform.model.UserOptLog optLog = new com.drug.platform.model.UserOptLog();
                        optLog.setUsername(sessionUser.getUserName());
                        optLog.setOptDes(userOptLog.optDes());
                        optLog.setOptDate(new Date());
                        userOptLogDAO.save(optLog);
                    } catch (Exception e) {
                        LOGGER.error("记录用户" + userOptLog.optDes() + "行为失败");
                        e.printStackTrace();
                    }
                }
            }
        }
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
        return 1;
    }
}
