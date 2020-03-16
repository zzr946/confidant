package com.zzr.confidant.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @description:
 * @version: 1.0
 * @author: 赵志然
 * @date: 2020/3/12
 */
@Configuration
public class WebAppConfigurer extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //公司logo本地文件读取路径配置 /Users/zzr/images/companyPhoto/
        registry.addResourceHandler("/companyLogo/**").addResourceLocations("file:/Users/zzr/images/companyPhoto/");
        super.addResourceHandlers(registry);

        //产品logo本地文件读取路径配置 /Users/zzr/images/ProductLogo/
        registry.addResourceHandler("/productLogo/**").addResourceLocations("file:/Users/zzr/images/ProductLogo/");
        super.addResourceHandlers(registry);

        //创始人头像 本地文件读取路径配置 /Users/zzr/images/founderPhoto/
        registry.addResourceHandler("/founderPhoto/**").addResourceLocations("file:/Users/zzr/images/founderPhoto/");
        super.addResourceHandlers(registry);

        //普通用户头像 本地文件读取路径配置 /Users/zzr/images/userPhoto/
        registry.addResourceHandler("/userPhoto/**").addResourceLocations("file:/Users/zzr/images/userPhoto/");
        super.addResourceHandlers(registry);
    }

}

