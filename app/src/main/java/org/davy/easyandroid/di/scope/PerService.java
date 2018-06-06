package org.davy.easyandroid.di.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;


/**
 * author: wangyonghua
 * version: V1.0
 * date: 2017/10/24
 * time: 12:14
 */
@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface PerService {
}
