package com.superman.modules.sys.listener;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

/**
 * Define Super.Sun.
 * <p>Created with IntelliJ IDEA on 2016/6/2.</p>
 *
 * @author Super.Sun
 * @version 1.0
 */
public class webContextListener extends ContextLoaderListener{

    @Override
    public WebApplicationContext initWebApplicationContext(ServletContext servletContext) {
        return super.initWebApplicationContext(servletContext);
    }
}
