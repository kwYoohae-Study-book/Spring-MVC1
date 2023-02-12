package hello.servlet.basic.response;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

  @Override
  protected void service(final HttpServletRequest request, final HttpServletResponse response)
      throws ServletException, IOException {

    //[status-line]
    response.setStatus(HttpServletResponse.SC_OK);

    //[response-headers]
    response.setHeader("Content-Type", "text/plain;charset=utf-8");
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("my-header", "hello");

    //[header 편의 메서드]
//    content(response);
//    cookie(response);

    // 리다이렉트
    redirect(response);

    response.getWriter().println("안녕하세요~~");
  }

  private void content(HttpServletResponse response) {
//    response.setHeader("Content-Type", "text/plain;charset=utf-8");
    response.setContentType("text/plain");
    response.setCharacterEncoding("utf-8");
    // ContentLength 도 설정 가능, 이는 보통 생략하면 자동으로 생성을 함
  }

  private void cookie(HttpServletResponse response) {
    //Set-Cookie: myCookie=good; Max-Age=600; -> Header를 통해서 넣을 수도 있음
   //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
    Cookie cookie = new Cookie("myCookie", "good");
    cookie.setMaxAge(600); // 600초를 의미
    response.addCookie(cookie);
  }

  private void redirect(HttpServletResponse response) throws IOException {
    //Status Code 302
    //Location: /basic/hello-form.html

    ///response.setStatus(HttpServletResponse.SC_FOUND); // 302
    //response.setHeader("Location", "/basic/hello-form.html");
    response.sendRedirect("/basic/hello-form.html");
  }
}
