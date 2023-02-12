package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

  private Map<String, ControllerV1> controllerV1Map  = new HashMap<>();

  public FrontControllerServletV1() {
    controllerV1Map.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
    controllerV1Map.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
    controllerV1Map.put("/front-controller/v1/members", new MemberListControllerV1());
  }

  @Override
  protected void service(final HttpServletRequest request, final HttpServletResponse response)
      throws ServletException, IOException {
    System.out.println("FrontControllerServletV1.service");

    final String requestURI = request.getRequestURI();

    final ControllerV1 controller = controllerV1Map.get(requestURI);

    if (controller == null) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return;
    }

    controller.process(request,  response);
  }
}
