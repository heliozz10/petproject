package com.heliozz10.petproject.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Pattern;

@ControllerAdvice
public class ReactForwardFilter implements Filter {
    //Paths to NOT forward to react app
    private static final String[] EXCLUDED_PATHS = new String[]{"api", "login", "login\\-process", "register", "register\\-process", "error", "css", "js", "images"};
    //Regex of the previous field
    private static final Pattern EXCLUDING_PATTERN = Pattern.compile("^/(?!(" + Arrays.stream(EXCLUDED_PATHS).reduce((subtotal, current) -> subtotal + '|' + current).get() + ")).*");

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String requestUri = request.getRequestURI();
        String path = requestUri.substring((request.getContextPath()).length());

        if(EXCLUDING_PATTERN.matcher(path).matches()) {
            request.getRequestDispatcher("/").forward(servletRequest, servletResponse);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
