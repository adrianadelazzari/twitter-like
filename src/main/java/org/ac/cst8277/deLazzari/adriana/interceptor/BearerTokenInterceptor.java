package org.ac.cst8277.deLazzari.adriana.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.ac.cst8277.deLazzari.adriana.domain.entity.UserEntity;
import org.ac.cst8277.deLazzari.adriana.service.UserService;
import org.ac.cst8277.deLazzari.adriana.util.JwtTokenUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class BearerTokenInterceptor implements HandlerInterceptor {

  private final UserService userService;
  private final JwtTokenUtil jwtTokenUtil;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) {
    try {
      List<String> publicUrls = new ArrayList<>();
      publicUrls.add("/authentication/token");
      publicUrls.add("/swagger-ui/");
      publicUrls.add("/v3/api-docs");
      publicUrls.add("/error");

      String servletPath = request.getServletPath();
      if (publicUrls.stream().filter(s -> servletPath.startsWith(s)).findAny().isEmpty()) {
        String jwtToken = request.getHeader("authorization");
        if (Objects.nonNull(jwtToken) && jwtToken.startsWith("Bearer ")) {
          jwtToken = jwtToken.substring(7);
          String username = this.jwtTokenUtil.getUsernameFromToken(jwtToken);
          UserEntity userEntity = this.userService.findByUsername(username);
          if (!this.jwtTokenUtil.validateToken(jwtToken, userEntity.getUsername())) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
          }
          request.setAttribute("userEntity", userEntity);
          return true;
        } else {
          response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
          return false;
        }
      }
      return HandlerInterceptor.super.preHandle(request, response, handler);
    } catch (Exception e) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      return false;
    }
  }
}