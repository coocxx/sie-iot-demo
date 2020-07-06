package com.sie.iot.demo.filter;

import com.sie.iot.common.filters.CommonXssFilter;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebFilter;

@Component
@ServletComponentScan
@WebFilter(urlPatterns = "/*", filterName = "commonXssFilter")
public class XssFilter extends CommonXssFilter {

}
