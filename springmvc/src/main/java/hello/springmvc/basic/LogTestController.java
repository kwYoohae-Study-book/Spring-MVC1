package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController // 사용을 하면 View를 안찾고, 그냥 String이 반환이됨
public class LogTestController {
//  private final Logger log = LoggerFactory.getLogger(getClass());

  @RequestMapping("/log-test")
  public String logTest() {
    String name = "Spring";

    System.out.println("name = " + name);

    log.trace("trace log={}", name); // 문자열 병합으로 하면, 자바에서 연산이 일어나 CPU 및 메모리 사용하므로 다음과 같은 방법으로 해야합니다.
    log.debug("debug log={}", name);
    log.info(" info log={}", name);
    log.warn(" warn log={}", name);
    log.error(" error log={}", name);


    return "ok";
  }
}
