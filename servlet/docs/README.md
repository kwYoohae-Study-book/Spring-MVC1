## 웹서버와 웹 애플리케이션
- 웹은 HTTP 기반으로 이루어져있습니다. 
- 음성 영상, 파일등 JSON도 거의 모든 형태의 데이터를 주고 받을 때도 모두 HTTP를 사용합니다.
## 웹 서버 
- HTTP 기반으로 동작하는 서버
- 정적 리소스와 기타 부가기능을 제공함
  - 정적 리소스 : HTML, CSS, JS, 이미지, 영상등을 모아둡니다.
  - NGINX, APACHE 등이 존재합니다.
## 웹 애플리케이션 서버(WAS - Web Application Server)
- HTTP 기반으로 동작을 합니다. 
- 웹 서버 기능을 포함 하고, 추가적으로 **정적 리소스도 제공이 가능하빈다.**
- 프로그램 코드를 실행해서 애플리케이션 로직을 수행합니다. 
  - 동적 HTML, HTTP API (JSON)
  - 서블릿, JSP, 스프링 MVC
- 톰캣(Tomcat) Jetty, Undertow
## 웹 서버와 웹 애플리케이션 서버의 차이
- 웹 서버는 정정 리소스 , WAS는 애플리케이션 로직
  - 서로는 서로의 기능을 포함하기도함
- 자바는 서블릿 컨테이너 기능을 제공하면, WAS
- **WAS는 애플리케이션 코드를 실행하는데 더 특화되어 있다고 보면 됩니다.**
## 웹 시스템 구성 - WAS, DB
- 우리는 웹 시스템을 WAS와 DB만으로도 시스템을 구성할 수 있습니다. 
- 하지만, 이러면, WAS가 너무 많은 역할을 담당해, 서버가 과부화 됩니다. 
- **HTML, CSS등은 싸지만, 애플리케이션 로직은 비싼데, 정적 리소스 때문에 수행이 어려워 질 수도 있음**
- WAS는 잘 죽기 때문에, 장애가 일어나면, 오류 화면도 노출이 불가능합니다.
## 웹 시스템 구성 - WEB, WAS, DB
- 정적 리소스는 WEB 서버가 처리를 합니다
- 웹 서버는 애플리케이션 로직을 처리할 때, WAS에 요청을 위임합니다. 
  - 결국, WAS가 중요한 로직을 처리합니다. 
- 이는, 효율적인 리소스 관리가 가능합니다.
  - 정적 리소스가 많이 사용된다면, Web 서버를 증설합니다. 
  - 애플리케이션 리소스가 많이 사용된다면 WAS를 증설합니다. 
- 또한, 웹 서버는 정적 리소스만 제공하기 때문에 잘 죽지 않습니다. 
  - 하지만, WAS는 애플리 케이션 로직이 동작하기 때문에 쉽게 죽습니다.
  - 그래도, Web서버는 죽지 않았으므로, WEB서버는 오류 화면을 제공할 수 있습니다. 
---
## 서블릿
- 웹 브라우저는 POST 전송을 하면, HTTP 메세지를 클라이언트에게 보냅니다. 
  - Conent-Type: application/x-www-form-urlencoded로 전달합니다. 
- 이외에 여러가지 응답을 넣어야하는데, 파싱을해서 넣는 것도 힘들고, 응답 메세지는 결국 text이므로, 값을 가져오는것도 힘들다
- 서블릿은 그래서, 응답 메세지 생성, 부터 여러가지 일들을 다 해줍니다.
### 특징
```java
@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
  @Override 
  protected void service(HttpservletRequest request, HttpServletResponse response) {ㅎ
    // 애플리케이션 로직
  }
}
```
- `urlPatterns(/hello)`의 url이 호출된다면 서블릿의 코드가 실행이 됩니다. 
- 우리는 요청 정보를 `HttpServletRequest`, 응답 정보를 `HttpServletResponse`를 통해서, 쉽게 사용을 할 수있습니다. 
- 우리는 이러한 것을 통해서 HTTP 스펙을 매우 편리하게 사용이 가능합니다. 
### HTTP 요청, 응답 흐름
- HTTP 요청이 온다면
  - WAS는 Request, Response 객체를 새로 만들어서 서블릿 객체를 호출하빈다. 
  - 개발자는 Request 객체에서 HTTP 요청 정보를 편하게 꺼내서 사용가능하빈다. 또한, 응답도 마찬가지 입니다. 
  - WAS는 Response 객체에 담겨있는 내용을 통해 HTTP 응답 정보를 생성합니다. 
  - 이러한 Servlet 객체는 WAS안에 있는 **서블릿 컨테이너**가 생성하고 호출, 관리까지 진행합니다. 
### 서블릿 컨테이너
- 톰캣과 같이 Servlet을 지원하는 WAS를 **서블릿 컨테이너**라고 부릅니다. 
- 이는, 서블릿 객체를 생성 -> 초기화 -> 호출 -> 종료 하는 생명주기를 관리합니다. 
- 이들의 객체는 **싱글톤으로 관리 됩니다**
  - 그래서, 최초의 로딩 시점에서 미리 만들어 두고 재활용합니다. 
  - 결국 모든 고객의 요청은 동일한 서블릿 객체 인스턴스에 접근하게 됩니다. 
  - **그렇기 때문에 공유 변수 사용에 주의 해야합니다.**
- JSP도 서블릿으로 변환되어서 사용됩니다. 
- 동시요청을 위한 **멀티 쓰레드 처리**를 지원합니다.
---
## 동시 요청 - 멀티 쓰레드 
- 한명이, 요청을 하면, WAS가 Servlet을 호출할 때, **쓰레드**가 호출을 합니다. 
### 쓰레드 
- 애플리케이션 코드를 하나하나 순차적으로 실행하는 것을 쓰레드 입니다. 
- 쓰레드가 없으면, 자바 애플리케이션을 실행이 불가능 합니다. 
  - 왜냐하면, 자바의 main메서드를 처음 실행하면, main이라는 이름의 쓰레드가 생성되기 때문이빈다. 
- 이런, 쓰레드는 한번에 하나의 코드 라인만 수행합니다. 
- 동시처리가 필요하면, 쓰레드를 추가로 생성합니다. 
- 이러한, 쓰레드는 요청할때마다, 신규 쓰레드르 생성해, 고객의 요청에 응답합니다.
### 요청 마다 쓰레드 생성의 장단점
- 장점
  - 동시 요청을 처리가능합니다. 
  - 리소스가 허락을 한다면, 계속 처리가 가능합니다.
  - 하나가 지연되도 나머시 쓰레드를 생성하므로, 나머지는 정상동작 합니다. 
- 단점
  - 쓰레드 생성 비용은 매우 비쌉니다. 
    - 고객의 요청이 올때마다, 생성한다면, 응답 속도가 느려집니다. 
  - 쓰레드는 Context Switching 비용이 발생합니다. 
  - 쓰레드 생성에 제한이 없어, 고객의 요청이 많으면, CPU와 메모리 임계점을 넘어 서버가 죽을수도 있습니다. 
### 쓰레드 풀
- 위의 단점을 해결하기 위해, 미리 쓰레드를 생성을 해두고, 이를 가져다 쓰는 방식을 사용합니다.
  - 응답이 끝난, 쓰레드는 다시 쓰레드 풀에 반환합니다. 
- 미리 쓰레드를 생성한 곳을 **쓰레드 풀**이라고 합니다.
### 쓰레드 풀의 특징
- 특징
  - 필요한 쓰레드를 쓰레드 풀에 보관하고 관리합니다. 
  - 쓰레드 풀에 생성가능한 쓰레드의 최대치를 관리합니다. 
    - 톰캣은 기본적으로 **최대 200개**를 가집니다. 
- 사용
  - 요청시, 쓰레드 풀에서 쓰레드를 꺼내 사용합니다. 
  - 사용이 끝나면, 쓰레드 풀에 반납합니다. 
  - 사용가능한 쓰레드가 없다면, 요청을 거정하거나 특정 숫자 만큼 대기하도록 할 수 있습니다. 
- 장점
  - 쓰레드가 미리 생성되어있어, 쓰레드를 생성하고 종료하는 비용이 절약되고, 응답이 빠릅니다. 
  - 생성 가능한 쓰레드의 최대가 있어, 기존 요청을 안전하게 처리 가능합니다.
### 실무 팁
- WAS에서 중요한 것은 결국 **최대 쓰레드 갯수입니다.**
  - 너무 낮으면, 서버 리소스는 여유로운데 클라이언트에서 지연이 많이 발생합니다. 
  - 너무 높으면, CPU, 메모리 리소스 임계점 초과로 서버가 다운됩니다. 
- 그래서, 장애가 발생하면 다음과 같이 행동합니다. 
  - 클라우드면, 서버를 늘리고, 이후 튜닝합니다. 
  - 클라우드가 아니면, 열심히, 쓰레드 갯수를 튜닝합니다. 
### 쓰레드 풀의 적정 숫자
- 적정 숫자는, 애플리케이션의 복잡도, CPU, 메모리 등에 따라 모두 다릅니다. 
- 그래서 우리는 최대한 실제 서비스와 비슷하게 성능 테스트를 미리 합니다. 
  - 툴 : 아파치 ab, Jmeter, nGrinder등을 사용합니다. 
### 핵심
- 우리는 WAS를 쓰면서 멀티 쓰레드 관련 코드를 신경안써도 됩니다. 
- 싱글 쓰레드를 프로그래밍 하듯이 편하게 소스 코드를 개발하면 됩니다. 
- 하지만, **멀티 쓰레드 환경이기 때문에 싱글톤 객체들은 주의해서 사용을 해야합니다.**
---
### 정적 리소스
- 고정된 HTML, CSS, JS 이미지등을 줍니다.
### HTML 페이지
- 동적으로 필요한 HTML 파일을 생성해서 전달하고, 이를 웹 브라우저가 해석합니다.
  - JSP, 타임리프등이 생성합니다.
### HTTP API
- HTML이 아닌 데이터를 주로 `JSON`형식을 사용해서 보냅니다.
- 이들은 다양한 시스템에서 호출이 됩니다.
- 데이터만 주고 받기 때문에, UI 화면이 필요하다면, 클라이언트가 별도로 처리합니다.
- 보통, UI 클라이언트, 서버 to 서버등과 JSON 형태로 데이터를 통신합니다.
## 서버사이드 렌더링, 클라이언트 사이드 렌더링
### SSR - 서버 사이드 렌더링
- HTML 최종 결과를 서버에서 만들고 웹 브라우저에게 전달합니다.
- 주로, 정적인 화면에서 사용이 됩니다.
- 백엔드 개발자가 주요 사용합니다. (JSP, 타임리프등)
### CSR - 클라이언트 사이드 렌더링
- HTML 결과를 자바스크립트를 사용해서 웹 브라우저에서 동적으로 생성을 합니다.
- 동적인 화면에 사용하여, 앱처럼 필요한 부분들을 부분부분 변경이 가능합니다.
  - 구글 지도, Gmail 등에서 사용합니다.
- React, Vue.js 등 웹 프론트엔드 개발자가 사용합니다.
### 참고사항
- SSR를 써도, 자바스크립트를 사용해서, 일부 화면을 동적으로 변경이 가능합니다.
- 또한, SSR과 CSR을 동시에 지원하는 프레임워크도 존재합니다.
---
### 자바 웹 기술의 역사 - 과거 기술
- 서블릿 - 1997 
  - HTML 생성이 어려웠음
- JSP - 1999
  - JSP는 비지니스 로직까지 담당을 해야해서 복잡했음
- 서블릿, JSP 조합해 MVC 패턴 사용
  - 모델, 뷰, 컨트롤러로 역할을 분리해서 개발함
- MVC 프레임워크 춘추 전국 시대 - 2000초 ~ 2010
  - MVC 패턴을 자동화하거나, 복잡한 기술들을 편리하게 사용할 수 있는 기능을 지원함
  - 스프링 MVC (과거버전) 등을 사용함
### 자바 웹 기술의 역사 - 현재 사용 기술
- 애노테이션 기반의 스프링 MVC가 등장하여, 이전 기술들이 사용되지 않게 되었습니다. 
- 스프링 부트가 등장하였습니다. 
  - 스프링 부트는 서브를 내장하고 있기 때문에, 빌드 배포를 단순화 할 수 있었습니다. 
### 자바 웹 기술의 역사 - 최신 기술
- Web Servlet - Spring MVC
- Web Reactive - Spring WebFlux
  - 비동기 넌 블럭킹 처리를 합니다. 
  - 최소 쓰레드로 최대 성능을 올려, 쓰레드 컨텍스트 스위칭 비용이 효율화 됩니다. 
  - 함수형 스타일로 개발해, 동시처리 코드가 효율화 됩니다. 
  - 서블릿 기술을 사용하지 않습니다. 
  - 하지만, 기술적 난이도가 높고, RDB의 지원 부족등으로 실무에서 많이 사용안합니다. 
  - 또한, 일반 MVC도 쓰레드 모델도 충분히 빠릅니다.
### 자바 뷰 템플릿 역사
- JSP
  - 속도가 느리고 기능이 부족합니다. 
- Freemarker, Velocity
  - 속도 문제를 해결하고 다양한 기능을 제공합니다. 
- Thymeleaf
  - 내추럴 템플릿으로, HTML의 모양을 유지하면서 뷰 템플릿을 적용을 가능하게 하였습니다. 
  - 스프링 MVC와 강력한 기능을 통합해서 제공합니다. 
  - 하지만, Freemarker나 Velocity보다는 느립니다.
---
### HTTP 요청이 있을 경우 임시 저장소 기능이 존재
- 저장 : `request.setAttribute(name, value)`
- 조회 : `reqeust.getSession(create: true)`
- 세션 관리 : `request.getSession(create: true)`
---
## HTTP 요청 데이터 
- 보통은 다음 3가지 방법을 사용합니다.
- GET - 쿼리파라미터
  - `?username=hello`
    - 메시지 바디 없이, URL을 통해 데이터를 전달
    - 검색, 필터, 페이징 등에 많이 사용
- POST - HTML Form
  - content-type: application/x-www-form-urlencoded
  - 이는 GET의 쿼리 파라미터 형식과 같습니다. (서버에서 받을 때만)
  - 메시지 바디에 쿼리 파라미터 형식으로 전달함 
  - 회원 가입 등에 사용을 함
- HTTP Message body
  - HTTP API에서 주로 사용을 함 (JSON, XML, TEXT등)
  - 주로 JSON을 사용하고 POST, PUT, PATCH등을 사용
---
## MVC패턴 - 개요
- JSP나 Servlet으로 하면, 비지니스 로직과 뷰 렌더링까지 모두 처리하므로, 유지보수가 어려워진다. 
- 또한, 둘 사이에 변경의 라이프 사이클이 다릅니다. 
  - 결국, UI를 수정하는 일과 비지니스 로직을 수정하는 일은 보통, 따로따로 합니다. 그래서 서로에게 영향을 주지 않습니다. 
  - 그러므로, 라이프 사이클이 둘은 다른데 하나의 코드로 관리해 유지보수가 어렵습니다.
  - 그리고 JSP는 화면 렌더링에만 최적화 되어있기 때문에 화면만 렌더링하게 합니다. 
### MVC (Model, View, Controller)
- 컨트롤러 
  - HTTP 요청을 받음
  - 파라미터를 검증함
  - 비지니스 로직을 실행함
  - 뷰에 전달할 결과 데이터를 조회해 모델에 담음
- 모델
  - 뷰에 출력한 데이터를 담음
  - 데이터를 담기 때문에, 뷰는 비지니스 로직과 데이터 접근을 몰라도되고 렌더링에만 집중할 수 있다. 
- 뷰
  - 모델에 담겨있는 데이터를 사용해, 화면을 그리는 일에 집중함
  - HTML을 생성하는 부분을 말함
### 참고사항
- Controller에 비지니스 로직을 처리하면, 너무 많은 역할을 담당하게됨
- 그래서 우리는 **Service** 라는 계층을 별도로 만들어 관리함
- 컨트롤러는 service만 호출하면됨
- 우리는 `request.setAttirbute()`, `request.getAttirbute()`를 통해서 데이터를 보관, 조회함 -> 이것이 모델로 쓰임
---
## Redirect vs Forward
- 리다이렉트
  - 실제 클라이언트에 응답이 나갔다가, 클라이언트가 redirect 경로로 다시 요청함
  - 클라이언트가 인지할 수 있고, URL 경로도 실제 변경됨
- 포워드
  - 서버 내부에서 일어나는 호출이므로, 클라이언트가 전혀 인지하지 못함
---
## MVC 패턴의 한계
- MVC덕에 컨트롤러의 역하로가 뷰를 렌더링  하는역할을 명확하게 구분할 수 있음
- 하지만, 컨트롤러는 중복이 많다. 
```java
// 포워드 중복
RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
dispatcher.forward(request, response);

// ViewPath 중복
String viewPath = "/WEB-INF/views/new-form.jsp"
```
- 이 뿐만이 아닌, 공통처리도 어렵고 여러가지 문제점들이 있습니다. 
- 우리는 **프론트 컨트롤러 패턴**을 도입해서 이러한 것을 해결할 수 있다.
---
### 프론트 컨트롤러 패턴
- 우리는 공통로직을 클라이언트 호출마다 존재했기 때문에, 코드가 중복되었습니다. 
- 하지만, Front Controller를도입해서, Servlet하나만 생성을 해서 이 컨트롤러가 모든 요청을 받도록 합니다. 
- 이후, 어떤 컨트롤러에 가야할지 매핑을 해줍니다.  
- 그렇기 때문에 다른 컨트롤러 들은 서블릿을 사용하지 않아도 됩니다. 
- 이를 Spring에서는 `DispatcherServlet`이 Front Controller 패턴으로 이루어짐
---
## 스프링 MVC
1. 클라이언트가 HTTP 요청을 한다. 
2. Front Controller인 Dispatcher Servlet이 받아 핸들러 매핑에 있는 `HandlerMapping`이라는 곳에 핸들러를 조회한다. 
3. 핸들러 어댑터 목록을 보고 핸들러를 처리할 수 있는 핸들러 어댑터를 조회합니다. 
4. 핸들러어댑터에있는 `handle`이라는 메서드를 실행합니다. 
5. 그러면 핸들러 어댑터는 실제 핸들러를 호출하고, ModelAndView를 반환합니다. 
6. 이후, 그 정보를 가지고 viewResolver를 호출하고, View를 반환합니다. 
7. 이후, `render(model)`을 호출하고 HTML응답을 내뱉습니다.
## DispatcherServlet
- 스프링 MVC의 프론트 컨트롤러가 바로 `DispathcerServlet`입니다. 
- 이는 `HttpServlet`을 상속받은 것 입니다. 결국 서블릿을 사용합니다. 
- 또한, 서블릿을 자동으로 등록하면서 **모든 경로**에 대해서 매핑을 합니다. 
- 서블릿이 호출되면, `HttpServlet`에서 제공하는 `service()` 가 호출이 됩니다. 
  - MVC는 `DispatcherSevlet`의 부모인, `FrameworkSevlet`에서 `service()`를 오버라이드해놔서, 이를 시작으로 `DispatcherServlet.doDispatch()`가 실행됩니다. 
  - 결국 `DispatcherServlet`의 `doDispatch()`가 가장 중요합니다.
---
## HandlerMapping
- `RequestMapping`에서 사용하는 것을 매핑 해준다. 
- 스프링 빈의 이름으로 핸들러를 찾는다.
- 등등 많은 종류의 Mapping이 존재합니다. 
---
## HandlerAdapter
- RquestMapping에서 사용하는 어댑터가 존재
- HttpRqeustHandler를 처리하는 얘가 존재
- Controller 인터페이스를 처리하는 어댑터가 존재
등등이 존재한다. 
---
## 뷰 리졸버
- 뷰 리졸버는 prefix와 suffix를 지정을 해줘야합니다. 이를 등록해서 동작하게 할 수 있습니다. 
- 스프링 부트는 자동으로 여러 뷰 리졸버를 등록합니다. 
  - 빈 이름으로 뷰를 찾아서 반환합니다. 
  - JSP를 처리할 수 있는 뷰를 반환합니다.
  - 등등이 존재합니다. 
### 순서
1. 핸들러 어댑터를 호출합니다. 이때 논리 뷰 이름을 획득합니다. 
2. ViewResolver를 호출해서, 맞는 것을 찾아 사용합니다. 
3. 이후, 뷰 리졸버는 `InternalResourceView`를 반환합니다. 
4. `InternalResourceView`는 `forward()`를 사용해 JSP를 실행합니다.
---
## @RequestMapping
- `RequestMappingHandlerMapping`, `RequestMappingHandlerAdapter`는 가장 우선 순위가 높으며, 이를 사용한다. 
- 실무에서는 이와 같은 방식을 가장 많이 사용을 합니다.
- 해당 url이 호출 되면, 어노테이션이 받은 메서드가 호출됩니다.
---
## @Controller
- Component 스캔을 통해서 자동으로 빈으로 등록함
- MVC에서 애노테이션 기반 컨트롤러로 인식을합니다.
- `@Component`와 `@RequestMapping` 을 가지고도 대체할 수 있습니다. 
- MVC는 Controller또는 RequestMapping이 클래스 레벨에 있어야합니다. 
---
## 메세지 컨버터 
- 뷰템플릿이 아닌 JSON 데이터를 HTTP 메세지 바디에서 직접 읽거나 쓰는 경우 사용합니다. 
- HTTP 요청의 데이터를 읽을때는 다음과 같습니다. 
  - 일단 `canRead()`를 통해서 메세지 컨버터가 메세지를 읽을 수 있는지 확인합니다. 
  - 이후, Content-Type 미디어 타입을 지원하는지 확인합니다. 
  - 두랃 만족을 한다면 `read()`를 호출해 객체를 생성하고 반환합니다.
- 응답 데이터 생성은 다음과 같습니다. 
  - 메세지 컨버터가 메시지를 쓸 수 있는지 확인하기 위해서 `canWrite()`를 호출합니다. 
  - 이후 대상 클래스 타입을 지원하는지 확인하빈다. 
  - 또한, HTTP 요청의 Accept 미디어 타입을 지원하는지 확인합니다. 
  - `canWrite()`조건을 만족한다면 `write()`를 호춯해, HTTP 응답 메세지 바디에 데이터를 생성합니다. 
---
## 요청 매핑 핸들러 어뎁터 구조 
- `RequestMappingHandlerAdapter` 동작 방식
  - RequestMappingHandlerAdapter는 `ArgumentResolver`를 통해서 다양한 파라미터를 유연하게 처리합니다. 
    - 우리는 `Model`, `HttpServletRequest` 등등을 유연하게 Spring을 통해서 처리할 수 있습니다. 
    - 핸들러 매핑 어댑터가 `ArgumentReolver`에게 인자에 담긴 값을 넘겨줄 수 있는지 물어보고, 이를 받습니다. 
- `ReturnValueHandler`
  - 이는 위와 비슷하고, 다만 다른점은 응답 값을 변환하고 처리하는 것입니다. 
### HTTP 메세지 커버터
- `ArgumentResolver`와 `ReturnValueHandler`는 HTTP 메세지 컨버터를 사용합니다.
- 파라미터와 반환 값들을 보고, HTTP 메세지 컨버터를 사용해, 필요한 객체를 생성합니다. 이후, 응답 또는 요청의 결과를 만듭니다.
- 우리는 `ArgumentResolver`, `ReturnValueHandler`, `HttpMessageConverter`을 확장할 수 있게 인터페이스로 제공을 합니다. 
- 