#### 1. Tomcat 서버를 시작할 때 웹 애플리케이션이 초기화하는 과정을 설명하라.
    1) WebServerLauncher에서 tomcat.start();로 서블릿 컨테이너 (톰캣)이 실행된다.
    2) ServletContextListener를 구현한 ContextLoaderListener가 실행되며,
        @WebListener로 web.xml을 대신한다.
    3) DispatcherServlet에서 @WebServlet(name = "dispatcher", urlPatterns = "/", loadOnStartup = 1) 속성 중
        loadOnStartup 속성으로 인해 서블릿 컨테이너가 실행될 때 DispatcherServlet도 같이 초기화된다.

#### 2. Tomcat 서버를 시작한 후 http://localhost:8080으로 접근시 호출 순서 및 흐름을 설명하라.
    1) WebServerLauncher에서 톰캣 시작 전, 세팅 시 setHost를 주지 않아서
       기본 값인 localhost로 Hostname이 설정된다.
    2) tomcat.setPort(8080)으로 톰캣의 포트를 8080으로 설정한다.
    3) DispatcherServlet에 @WebServlet(urlPatterns = "/") 로 주면 모든 Url이 DispatcherServlet에 매핑된다.
    4) Servlet의 service 메소드가 실행되며, req.getRequestURI()로 url를 가져온다.
    5) DispatcherServlet이 초기화 될 때, URI와 컨트롤러를 매칭한 데이터를 가진 Map이 선언되며 URI로 컨트롤러를 찾아온다.
    6) 각 컨트롤러별로 구현된 execute 메소드를 실행한다.

#### 3. 질문 목록은 정상적으로 작동하지만, 질문하기 기능은 정상적으로 동작하지 않는다. 질문하기 기능을 구현
    요구사항
        1) QuestionDao 클래스의 insert() 메소드를 사용한다.
        2) 질문하기를 성공 후 질문목록페이지("/")로 이동해야 한다.
    해결
        1) CreateQnaController를 등록 및 RequestMapping 클래스에 등록
        2) CreateQnaController의 execute 로직 구현
        3) CharacterEncodingFilter에 @WebFilter("/*")를 이용하여 한글 인코딩
            (필터는 서블릿 컨테이너에 URL로 접속할 때 마다 실행된다)

#### 4. 로그인하지 않은 사용자도 질문하기가 가능하다.
    요구사항
        1) 로그인한 사용자만 질문이 가능하도록 하자
        2) 질문할 때 글쓴이를 입력하지 않고 로그인 한 사용자 정보를 가져와 글쓴이 이름으로 사용하도록 하자.
    해결
        1) home.jsp에서 jstl의 if문을 이용하여 로그인 여부에 따라 질문하기 버튼을 보여준다.
        2) HomeController에서 getSession()으로 세션을 얻어와서 로그인 여부를 판단하는 변수를 home.jsp로 전달한다.
        3) ForwardController에서 세션에 담긴 User를 꺼내서 전달 (/qna/form.jsp에만 전달하고 싶지만 일단 해결먼저.)

#### 5. 질문목록의 답변목록을 동적인 jsp로 구현하기
    해결
        1) show.jsp의 댓글을 jstl로 변경

#### 6. 한글 필터 적용
    해결
        1) 3번 질문 해결 시 같이 적용

#### 7. next.web.qna package의 ShowController는 멀티 쓰레드 상황에서 문제가 발생하는 이유에 대해 설명하라.

#### 8. show.jsp에서 답변 달 때, 답변의 갯수를 최신화 하는 로직 추가하기
    해결
        1) AnswerDao에 Count 메소드 추가

#### 9. API를 위해 질문 목록을 JSON으로 반환하는 컨트롤러 추가
    해결
        1) ListQnaController 추가

#### 10. 질문글에서 답변 삭제하는 로직 추가
    요구사항
        1) Ajax로 구현하기

#### 11. 질문 수정기능 추가
    요구사항
        1) 글쓴이와 로그인 사용자가 같은 경우에만 수정이 가능하다.

