package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.ControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

  private Map<String, ControllerV3> controllerV3Map  = new HashMap<>();

  public FrontControllerServletV3() {
    controllerV3Map.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
    controllerV3Map.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
    controllerV3Map.put("/front-controller/v3/members", new MemberListControllerV3());
  }

  @Override
  protected void service(final HttpServletRequest request, final HttpServletResponse response)
      throws ServletException, IOException {

    final String requestURI = request.getRequestURI();

    final ControllerV3 controller = controllerV3Map.get(requestURI);

    if (controller == null) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return;
    }

    //paramMap을 넘겨줘야함
    Map<String, String> paramMap = createParamMap(request);
    final ModelView mv = controller.process(paramMap);

    final String viewName = mv.getViewName();

    //View resolver에 대한 기능 구현  (지금은 논리이름만 알 수 있음 New-form 등등)
    final MyView view = viewResolver(viewName);
    view.render(mv.getModel(), request, response);
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
