
package com.earthworm.ipsp.foundation;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.earthworm.postgres.PostgresConfiguration;
import com.earthworm.utils.cors.AllowOriginFilter;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.web.servlet.AbstractFilterRegistrationBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.RegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication(scanBasePackageClasses = {IPSPApplication.class, PostgresConfiguration.class})
@EnableTransactionManagement
public class IPSPApplication {
    public static void main(String[] args) {
        SpringApplication.run(IPSPApplication.class,args);
    }

    @Bean
    public RegistrationBean filterRegistrationBean(
            @Nullable @Value("${ew.ipsp.foundation.allow.origin.pattern}") String allowOriginPattern,
            @Nullable @Value("${ew.ipsp.foundation.allow.origin.list}") String allowOriginList,
            @Nullable @Value("${ew.ipsp.foundation.allow.method.pattern}") String allowMethodPattern,
            @Nullable @Value("${ew.ipsp.foundation.allow.header.pattern}") String allowHeaderPattern,
            @Nullable @Value("${ew.ipsp.foundation.allow.method.interceptors}") String interceptorMethods) {
        AbstractFilterRegistrationBean<AllowOriginFilter> filterRegistrationBean =
                new FilterRegistrationBean<>(new AllowOriginFilter());
        filterRegistrationBean.addInitParameter(AllowOriginFilter.ALLOW_ORIGIN_REGEX, allowOriginPattern);
        filterRegistrationBean.addInitParameter(AllowOriginFilter.ALLOW_ORIGINS, allowOriginList);
        filterRegistrationBean.addInitParameter(AllowOriginFilter.ALLOW_REQ_METHOD_REGEX, allowMethodPattern);
        filterRegistrationBean.addInitParameter(AllowOriginFilter.ALLOW_REQ_HEAD_REGEX, allowHeaderPattern);
        filterRegistrationBean.addInitParameter(AllowOriginFilter.INTERCEPT_REQ_METHODS, interceptorMethods);

        filterRegistrationBean.setUrlPatterns(Stream.of("/*").collect(Collectors.toSet()));
        filterRegistrationBean.setServletNames(Stream.of("allowOriginFilter").collect(Collectors.toSet()));

        return filterRegistrationBean;
    }

    /**
     * Config HttpMessageConverters
     * @return HttpMessageConverters
     */
    @Bean
    public HttpMessageConverters getHttpMessageConverter() {
        HttpMessageConverter<?> jsonConvert = new FastJsonHttpMessageConverter();

        return new HttpMessageConverters(jsonConvert);
    }
}
