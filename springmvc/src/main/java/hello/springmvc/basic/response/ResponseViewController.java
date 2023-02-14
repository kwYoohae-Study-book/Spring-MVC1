package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

  @RequestMapping("/response-view-v1")
  public ModelAndView responseViewV1() {
    final ModelAndView mav = new ModelAndView("response/hello").addObject("data", "hello!");
    return mav;
  }

  @RequestMapping("/response-view-v2")
  public String responseViewV2(Model model) {
    model.addAttribute("data", "hello!");
    return "response/hello";
  }

  @RequestMapping("/response/hello") // 같은경우 다음과 같이작성 가능 모호해서 권장 x
  public void responseViewV3(Model model) {
    model.addAttribute("data", "hello!");
  }
}
