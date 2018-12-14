package com.earthworm.ipsp.foundation.controller.aspect;

import com.earthworm.postgres.helper.PageHelper;
import com.earthworm.postgres.helper.SortHelper;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PageInitAspect {
    @Pointcut("execution(public * com.earthworm.ipsp..controller.*Controller.*(..))")
    public void pointCutBefore(){}

    @Before("pointCutBefore()")
    public void before() {
        // Init page is null
        PageHelper.setPageParam(null);

        // Init sort is null
        SortHelper.setSort(null);
    }
}
