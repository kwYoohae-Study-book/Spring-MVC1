package hello.servlet.web.frontcontroller.v2.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.ControllerV2;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberSaveControllerV2 implements ControllerV2 {

  private MemberRepository memberRepository = MemberRepository.getInstance();

  @Override
  public MyView process(final HttpServletRequest request, final HttpServletResponse response)
      throws ServletException, IOException {
    final String username = request.getParameter("username");
    final int age = Integer.parseInt(request.getParameter("age"));

    final Member member = new Member(username, age);
    memberRepository.save(member);

    // 여기서부터 이전 코드와 차이있음
    // 우리는 Model에 데이터를 보관해야함
    request.setAttribute("member", member);

    return new MyView("/WEB-INF/views/save-result.jsp");
  }
}
