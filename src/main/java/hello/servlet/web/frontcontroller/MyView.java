package hello.servlet.web.frontcontroller;

import java.io.IOException;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyView {
  private final String viewPath;

  public MyView(String viewPath) {
    this.viewPath = viewPath;
  }

  public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    final RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
    dispatcher.forward(request, response);
  }

  public void render(final Map<String, Object> model, final HttpServletRequest request,
      final HttpServletResponse response) throws ServletException, IOException {
    modelToRequestAtrribute(model, request);
    final RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
    dispatcher.forward(request, response);
  }

  private static void modelToRequestAtrribute(final Map<String, Object> model, final HttpServletRequest request) {
    model.forEach((key, value) -> request.setAttribute(key, value));
  }
}
