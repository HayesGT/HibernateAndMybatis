package com.yxkong.common.filter;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewRendererServlet;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.yxkong.common.utils.StringUtil;

public class FreemarkerFilter implements Filter {
	private Locale locale;
	private ApplicationContext ctx;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String localeStr = filterConfig.getInitParameter("locale");
        if(StringUtil.isNotEmpty(localeStr)){
            locale = new Locale(localeStr);
        }else {
            locale = Locale.getDefault();
        }
	}

	@SuppressWarnings("unused")
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		 try {
	            HttpServletRequest req = (HttpServletRequest)request;
	            HttpServletResponse res = (HttpServletResponse)response;
	            if(ctx == null){
	    			ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(req.getSession().getServletContext());
	    			if(null == ctx){
	    				throw new ExceptionInInitializerError("spring context is not loaded!");
	    			}
	    		}

	            String name = req.getRequestURI();
//	            name = name.substring(1, name.lastIndexOf(".ftl"));
	            FreeMarkerViewResolver viewResolver = ctx.getBean(FreeMarkerViewResolver.class);
	            View view = viewResolver.resolveViewName(name, locale);
	            @SuppressWarnings("unchecked")
	            Map<String, Object> model = (Map<String, Object>) request.getAttribute(ViewRendererServlet.MODEL_ATTRIBUTE);
	            view.render(null, req, res);
	        } catch (Exception e) {
	            throw new ServletException(e);
	        }
	}

	@Override
	public void destroy() {
	}

}
