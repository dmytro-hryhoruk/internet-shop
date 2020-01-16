package mate.academy.internetshop.web.filters;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import mate.academy.internetshop.library.Inject;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;


import static mate.academy.internetshop.model.Role.RoleName.ADMIN;
import static mate.academy.internetshop.model.Role.RoleName.USER;

public class AuthorizationFilter implements Filter {
    @Inject
    private static UserService userService;
    private Map<String, Role.RoleName> protectedUrls = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        protectedUrls.put("/servlet/getAllUsers", ADMIN);
        protectedUrls.put("/servlet/deleteItem", ADMIN);
        protectedUrls.put("/servlet/addItem", ADMIN);

        protectedUrls.put("/servlet/addItemToBucket", USER);
        protectedUrls.put("/servlet/deleteItemFromBucket", USER);
        protectedUrls.put("/servlet/showBucket", USER);
        protectedUrls.put("/servlet/checkout", USER);
        protectedUrls.put("/servlet/allOrders", USER);
        protectedUrls.put("/servlet/deleteOrderFromOrders", USER);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        if (req.getCookies() == null) {
            processUnAuthenticated(req, resp);
            return;
        }

        Role.RoleName roleName = protectedUrls.get(req.getServletPath());
        if (roleName == null) {
            processAuthenticated(chain, req, resp);
            return;
        }

        String token = null;
        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals("MATE")) {
                token = cookie.getValue();
                break;
            }
        }

        if (token == null) {
            processUnAuthenticated(req, resp);
        } else {
            Optional<User> user = userService.getByToken(token);
            if (user.isPresent()) {
                if (verifyRole(user.get(), roleName)) {
                    processAuthenticated(chain, req, resp);
                } else {
                    processDenied(req, resp);
                }
            } else {
                processUnAuthenticated(req, resp);
            }
        }
    }

    @Override
    public void destroy() {
    }

    private boolean verifyRole(User user, Role.RoleName roleName) {
        return user.getRoles().stream()
                .anyMatch(r -> r.getRoleName().equals(roleName));
    }

    private void processDenied(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/accessDenied.jsp").forward(req, resp);
    }

    private void processAuthenticated(FilterChain chain, HttpServletRequest req,
                                      HttpServletResponse resp)
            throws IOException, ServletException {
        chain.doFilter(req, resp);
    }

    private void processUnAuthenticated(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}