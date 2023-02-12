package hello.servlet.web.springmvc.v2;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/springmvc/v2/members")
public class SpringMemberControllerV2 {

  private MemberRepository memberRepository = MemberRepository.getInstance();

  @RequestMapping("/new-form")
  public ModelAndView newForm() {
    return new ModelAndView("new-form");
  }

  @RequestMapping
  public ModelAndView save() {
    final List<Member> members = memberRepository.findAll();

    final ModelAndView mv = new ModelAndView("members");
    mv.addObject("members", members);
    return mv;
  }

  @RequestMapping("/save")
  public ModelAndView members(HttpServletRequest request, HttpServletResponse response) {
    final String username = request.getParameter("username");
    final int age = Integer.parseInt(request.getParameter("age"));

    final Member member = new Member(username, age);
    memberRepository.save(member);

    final ModelAndView modelAndView = new ModelAndView("save-result");
    modelAndView.addObject("member", member);
    return modelAndView;
  }
}
