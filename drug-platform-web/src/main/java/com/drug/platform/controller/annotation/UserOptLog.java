package com.drug.platform.controller.annotation;

import java.lang.annotation.*;

/**
 * Created by Yaochao on 2016/5/20.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface UserOptLog {

    String optDes() default "";
}
