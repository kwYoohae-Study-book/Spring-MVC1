package hello.servlet.basic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

  @Override
  protected void service(final HttpServletRequest request, final HttpServletResponse response)
      throws ServletException, IOException {

    System.out.println("HelloServlet.service");
    System.out.println("request = " + request);
    System.out.println("response = " + response);

    //쿼리 파라미터 읽기
    String username = request.getParameter("username");
    System.out.println("username = " + username);

    // 응답 넘기기
    response.setContentType("text/plain");
    response.setCharacterEncoding("utf-8");
    response.getWriter().write("hello" + username);
  }
}
