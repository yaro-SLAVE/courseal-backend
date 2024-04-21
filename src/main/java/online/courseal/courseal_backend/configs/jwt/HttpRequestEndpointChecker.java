package online.courseal.courseal_backend.configs.jwt;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;

public class HttpRequestEndpointChecker {

    private DispatcherServlet servlet;

    public HttpRequestEndpointChecker(DispatcherServlet servlet) {
        this.servlet = servlet;
    }

    public boolean isEndpointExist(HttpServletRequest request) {

        for (HandlerMapping handlerMapping : servlet.getHandlerMappings()) {
            try {
                HandlerExecutionChain foundHandler = handlerMapping.getHandler(request);
                if (foundHandler != null) {
                    return true;
                }
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
}
