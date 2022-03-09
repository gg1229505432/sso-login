package com.example.unifiedauthentication.filter;

import com.example.unifiedauthentication.conf.Conf;
import com.example.unifiedauthentication.entity.SsoUser;
import com.example.unifiedauthentication.helper.CookieStoreBrowserHelper;
import com.example.unifiedauthentication.helper.SessionAndCookieHelper;
import com.example.unifiedauthentication.helper.SessionStoreRedisHelper;
import com.example.unifiedauthentication.login.LoginHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yenanren
 * @date 2022/3/8 0008
 * @Description
 */

@Slf4j
public class WebFilter extends HttpServlet implements Filter {
    private String ssoServer;
    private String excludedPaths;
    private static PathMatcher pathMatcher = new AntPathMatcher();


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ssoServer = filterConfig.getInitParameter(Conf.SSO_SERVER);
        excludedPaths = filterConfig.getInitParameter(Conf.EXCLUDED_PATHS);

        log.info("WebFilter init success!");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String servletPath = req.getServletPath();

        if (excludedPaths != null && excludedPaths.trim().length() > 0) {
            String[] splitExcludedPaths = excludedPaths.split(",");
            for (String splitExcludedPath : splitExcludedPaths) {

                //如果我们浏览器中的地址,被包含在excludedPaths中,那么直接放行
                if (pathMatcher.match(splitExcludedPath, servletPath)) {
                    //传给下一个filter
                    chain.doFilter(request, response);
                    return;
                }
            }
        }

        //如果不在排除路径里,执行是否登录
        boolean checkLogin = LoginHelper.checkLogin(req, res);

        //如果没有登录,系统默认会给我们删除cookie,并返回登录界面
        if (!checkLogin) {
            String loginUrl = ssoServer.concat(Conf.SSO_LOGIN);
            res.sendRedirect(loginUrl);
            return;
        }

        //如果登录的话,返回SsoUser,设为cookie的value,降低redis的并发访问
        String cookieValue = CookieStoreBrowserHelper.getValue(req);
        String userId = SessionAndCookieHelper.parseCookieValueToUserId(cookieValue);

        SsoUser ssoUser = SessionStoreRedisHelper.get(Integer.valueOf(userId));
        req.setAttribute(Conf.SSO_USER, ssoUser);

        chain.doFilter(req, res);
        return;

    }
}