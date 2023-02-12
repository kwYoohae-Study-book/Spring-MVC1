package hello.servlet.web.servletmvc;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

  @Override
  protected void service(final HttpServletRequest request, final HttpServletResponse response)
      throws ServletException, IOException {
    String viewPath = "/WEB-INF/views/new-form.jsp";
    final RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath); // JSP로 제어권을 넘겨줌
    // 다른 서블릿이나, JSP로 이동할 수 있는기능 / 서버 내부에서 다시 호출이 발생함 (redirect가 아님)
    dispatcher.forward(request, response);
  }
}
