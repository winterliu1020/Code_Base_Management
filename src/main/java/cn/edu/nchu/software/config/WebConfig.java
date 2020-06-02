package cn.edu.nchu.software.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration          
public class WebConfig implements WebMvcConfigurer {
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(new AuthInterceptor());     
        registration.addPathPatterns("/**");         //所有路径都被拦截
        registration.excludePathPatterns("/","/login","/toRegister","/register",
        		"/assets/**","/codemirror/**","/css/**","/editor/**","/errorPage/**",
        		"/images/**","/js/**","/plugins/**");       //添加不拦截路径

    }
}
