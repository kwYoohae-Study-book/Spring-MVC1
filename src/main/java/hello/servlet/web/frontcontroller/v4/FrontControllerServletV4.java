package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

  private Map<String, ControllerV4> controllerV4Map = new HashMap<>();

  public FrontControllerServletV4() {
    controllerV4Map.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
    controllerV4Map.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
    controllerV4Map.put("/front-controller/v4/members", new MemberListControllerV4());
  }

  @Override
  protected void service(final HttpServletRequest request, final HttpServletResponse response)
      throws ServletException, IOException {

    final String requestURI = request.getRequestURI();

    final ControllerV4 controller = controllerV4Map.get(requestURI);

    if (controller == null) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return;
    }

    //paramMap을 넘겨줘야함
    Map<String, String> paramMap = createParamMap(request);
    Map<String, Object> model = new HashMap<>();
    final String viewName = controller.process(paramMap, model);

    //View resolver에 대한 기능 구현  (지금은 논리이름만 알 수 있음 New-form 등등)
    final MyView view = viewResolver(viewName);
    view.render(model, request, response);
  }

  private static MyView viewResolver(final String viewName) {
    return new MyView("/WEB-INF/views/" + viewName + ".jsp");
  }

  private static Map<String, String> createParamMap(final HttpServletRequest request) {
    Map<String, String> paramMap = new HashMap<>();
    request.getParameterNames().asIterator()
        .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
    return paramMap;
  }
}
