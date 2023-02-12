package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {

  @Override
  public boolean supports(final Object handler) {
    return (handler instanceof ControllerV3);
  }

  @Override
  public ModelView handle(final HttpServletRequest request, final HttpServletResponse response,
      final Object handler)
      throws ServletException, IOException {
    final ControllerV3 controller = (ControllerV3) handler;

    final Map<String, String> paramMap = createParamMap(request);

    return controller.process(paramMap);
  }

  private static Map<String, String> createParamMap(final HttpServletRequest request) {
    Map<String, String> paramMap = new HashMap<>();
    request.getParameterNames().asIterator()
        .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
    return paramMap;
  }
}
