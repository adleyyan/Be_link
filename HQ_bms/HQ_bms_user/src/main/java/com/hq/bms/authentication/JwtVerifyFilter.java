package com.hq.bms.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hq.bms.Configuration.RsaKeyProperties;
import com.hq.bms.model.SysUser;
import com.hq.bms.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Adley.yan
 */
public class JwtVerifyFilter extends BasicAuthenticationFilter {

    @Autowired
    private RsaKeyProperties rsaKeyProperties;

    public JwtVerifyFilter(AuthenticationManager authenticationManager,RsaKeyProperties rsaKeyPropertie) {
        super(authenticationManager);
        this.rsaKeyProperties = rsaKeyPropertie;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String header = request.getHeader("Authorization");
        //没有登录
        if (header == null || !header.startsWith("RobodToken ")) {
            chain.doFilter(request, response);
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            PrintWriter out = response.getWriter();
            Map<String, Object> map = new HashMap<String, Object>(4);
            map.put("code", HttpServletResponse.SC_FORBIDDEN);
            map.put("message", "请登录！");
            out.write(new ObjectMapper().writeValueAsString(map));
            out.flush();
            out.close();
            return;
        }
        //登录之后从token中获取用户信息
        String token = header.replace("RobodToken ","");
        SysUser sysUser = JwtUtils.getInfoFromToken(token, rsaKeyProperties.getPublicKey(), SysUser.class).getUserInfo();
        if (sysUser != null) {
            Authentication authResult = new UsernamePasswordAuthenticationToken
                    (sysUser.getUsername(),null,sysUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authResult);
            chain.doFilter(request, response);
        }
    }
}
