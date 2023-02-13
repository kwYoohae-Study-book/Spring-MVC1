package hello.springmvc.basic.request;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class RequestParamController {

  @RequestMapping("/request-param-v1")
  public void requestParamV1(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    final String uesrname = request.getParameter("username");
    final int age = Integer.parseInt(request.getParameter("age"));
    log.info("username = {}, age = {}", uesrname, age);

    response.getWriter().write("ok");
  }


}
