package com.drug.platform.controller.annotation;

import java.lang.annotation.*;

/**
 * Created by Yaochao on 2016/3/20.
 * 认证用户访问接口的权限
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface UserAuthenticate {
    UserType[] value();
}
