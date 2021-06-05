package com.spring.moviecollection.security;

import com.spring.moviecollection.model.Admins;
import com.spring.moviecollection.model.Employee;
import com.spring.moviecollection.model.Users;
import com.spring.moviecollection.service.AdminService;
import com.spring.moviecollection.service.EmployeeService;
import com.spring.moviecollection.service.UserService;
import com.spring.moviecollection.utils.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("customSuccessHandler")
@Slf4j
@RequiredArgsConstructor
public class CustomSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private final UserService userService;

    private final EmployeeService employeeService;

    private final AdminService adminService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws  IOException {
        String targetUrl = StringUtils.EMPTY;
        final User user = (User) authentication.getPrincipal();
        final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        final List<String> roles = new ArrayList<>();
        authorities.forEach(grantedAuthority -> roles.add(grantedAuthority.getAuthority()));
        final Users dbUser = userService.findByUserName(user.getUsername());
        if (isAdmin(roles))
            targetUrl = authenticationAdmin(request, dbUser);
        if (isEmployee(roles))
            targetUrl = authenticationEmployee(request, dbUser);
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    public String authenticationAdmin(HttpServletRequest request, Users dbUser){
        final Admins admin  = adminService.findByUser(dbUser);
        request.getSession().setAttribute(Constants.userInfoKey, admin);
        return "/admin";
    }

    public String authenticationEmployee(HttpServletRequest request, Users dbUser){
        final Employee employee  = employeeService.findByUser(dbUser);
        request.getSession().setAttribute(Constants.userInfoKey, employee);
        return "/employee";
    }

    private boolean isAdmin(List<String> roles){
        return roles.contains("ROLE_ADMIN");
    }

    private boolean isEmployee(List<String> roles){
        return roles.contains("ROLE_EMPLOYEE");
    }
}
