package org.ac.cst8277.deLazzari.adriana.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.ac.cst8277.deLazzari.adriana.domain.entity.UserEntity;
import org.ac.cst8277.deLazzari.adriana.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class BearerTokenInterceptor implements HandlerInterceptor {

  private final UserService userService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) {
    try {
      List<String> allowedUrls = new ArrayList<>();
      allowedUrls.add("/authentication/register");
      allowedUrls.add("/authentication/login");
      allowedUrls.add("/swagger-ui/index.html");
      allowedUrls.add("/v3/api-docs");
      allowedUrls.add("/v3/api-docs/swagger-config");

      String servletPath = request.getServletPath();
      if (!allowedUrls.contains(servletPath)) {
        String authorizationHeader = request.getHeader("authorization");
        if (Objects.isNull(request.getAttribute("userEntity"))) {
          if (Objects.nonNull(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            authorizationHeader = authorizationHeader.substring(7);
            UserEntity userEntity = this.userService.findByUuid(authorizationHeader);
            request.setAttribute("userEntity", userEntity);
          } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
          }
        }
        return true;
      }
      return HandlerInterceptor.super.preHandle(request, response, handler);
    } catch (Exception e) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      return false;
    }
  }
}