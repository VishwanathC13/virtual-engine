package com.example.virtualengine.analytics;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TrackEvent {
    String value() default "";
}
