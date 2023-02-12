package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {

  private Map<String, ControllerV2> controllerV2Map  = new HashMap<>();

  public FrontControllerServletV2() {
    controllerV2Map.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
    controllerV2Map.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
    controllerV2Map.put("/front-controller/v2/members", new MemberListControllerV2());
  }

  @Override
  protected void service(final HttpServletRequest request, final HttpServletResponse response)
      throws ServletException, IOException {
    System.out.println("FrontControllerServletV2.service");

    final String requestURI = request.getRequestURI();

    final ControllerV2 controller = controllerV2Map.get(requestURI);

    if (controller == null) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return;
    }

    final MyView view = controller.process(request, response);
    view.render(request, response);
  }
}
