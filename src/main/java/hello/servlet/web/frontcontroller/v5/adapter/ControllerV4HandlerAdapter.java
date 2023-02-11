package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {

  @Override
  public boolean supports(final Object handler) {
    return (handler instanceof ControllerV4);
  }

  @Override
  public ModelView handle(final HttpServletRequest request, final HttpServletResponse response,
      final Object handler)
      throws ServletException, IOException {
    final ControllerV4 controller = (ControllerV4) handler;

    final Map<String, String> paramMap = createParamMap(request);

    final HashMap<String, Object> model = new HashMap<>();

    final String viewName = controller.process(paramMap, model);

    final ModelView mv = new ModelView(viewName);
    mv.setModel(model);

    return mv;
  }

  private Map<String, String> createParamMap(HttpServletRequest request) {
    Map<String, String> paramMap = new HashMap<>();
    request.getParameterNames().asIterator()
        .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
    return paramMap;
  }
}
