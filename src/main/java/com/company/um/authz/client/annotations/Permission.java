package com.company.um.authz.client.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.company.um.authz.client.permission.BasePermission;


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Permission {

    /**
     * Multiple permissions mean any one should satisfy. It is like ORing the permissions
     *
     * @return
     */
    Class<? extends BasePermission>[] permission();
}
