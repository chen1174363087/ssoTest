package com.chenxin.config;

import com.chenxin.interceptor.SingleLoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
public class SpringMVCConfig extends WebMvcConfigurerAdapter {
    /**
     * 配置访问路径及后缀
     *
     * @return
     */
    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/html/");
        resolver.setSuffix(".html");
        return resolver;
    }

    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * 设置默认页面
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/toLogin");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }

    /**
     * 添加Spring Interceptor
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 可添加多个
        registry.addInterceptor(new SingleLoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/toLogin").excludePathPatterns("/login")
        .excludePathPatterns("/test");
    }
}
