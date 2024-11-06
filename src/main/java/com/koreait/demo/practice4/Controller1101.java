package com.koreait.demo.practice4;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Controller1101 {
    // 핸들러 메서드 (Handler Method: 요청을 받을 수 있는 메서드)
    // 반환형이 void라면, 요청 경로와 이름이 같은 view를 자동으로 찾아 보여준다
    // => req1.jsp 를 찾는다. 어디서? /WEB-INF/views 폴더에서!
    // 사실은 '/WEB-INF/views' + 'req1' + '.jsp' 해서 찾는것이다. (뷰리졸버에 설정해놨으니까)
    // 404 애러 뜨는 것 정상임
    @RequestMapping("/req1")
    void get_req1(){
        System.out.println("req1 에서 GET 요청을 받았습니다");
    }

    // 요청과 이름이 다른 페이지를 화면에 보여주고 싶다.
    // => return 타입으로 파일 경로의 String 을 작성한다.
    @RequestMapping("/req2")
    String get_req2(){
        System.out.println("req2 에서 GET 요청을 받았습니다");
        return "get"; // get 이라고 하는 이름의 view를 찾아라.
        // '/WEB-INF/views' + 'get' + '.jsp' 를 찾아서 화면에 보여준다
    }

    // ModelAndView 객체를 생성해서 반환하기
    @RequestMapping("/req3")
    ModelAndView get_req3(){
        System.out.println("req3 에서 GET 요청을 받았습니다");
        ModelAndView modelAndView = new ModelAndView();
        // '/WEB-INF/views' + 'get' + '.jsp' 를 찾아서 화면에 보여준다
        modelAndView.setViewName("get");
        return modelAndView; // ModelAndView 객체를 반환
    }
    /**************************************************************/
    ////// 요청 데이터를 받아오는 법
    // 1) 매개변수로 HttpServletRequest를 작성한다!(스프링이 객체를 주입해준다)
    @RequestMapping("/req4")
    void get_req4(HttpServletRequest request){
        System.out.println("req4 에서 GET 요청을 받았습니다");
        String name = request.getParameter("name");
        String age = request.getParameter("age"); // 파라미터는 무조건 문자열로 온다
        System.out.println("name: " + name);
        System.out.println("age: " + age);

    }


    // 2) @RequestParam을 붙이고,
    // 파라미터로 오는 파라미터 명을  @RequestParam에 작성하면
    // 스프링이 알아서 파라미터 값을 넣어준다
    // 이름이 일치하는 것만 넣어준다 (전달되는 파라미터가 많은건 상관이 없다.)
    // 전달되지 않는 파라미터는 변수 값이 null이 된다x => 오류가 난다
    @RequestMapping("/req5")
    void get_req5(
            // name: 파라미터명 / userName: 파라미터값을 담는 변수명
            // required = false를 넣으면 파라미터가 전달안되면 null이 주입된다
            @RequestParam(value = "name", required = false) String userName,
            // defaultValue는 파라미터가 전달안되면 userAddr에 기본값이 설정된다 => required = false가 자동으로 붙음
            @RequestParam(value = "addr", defaultValue = "대구 중구") String userAddr,
            // 기본형에서는 required = false가 의미없다. null 을 못넣기 때문에 오류난다
            @RequestParam(value = "age", required = false) int userAge,
            // required = false를 사용하는 정수형태는 Integer를 사용하자. null이 된다.
            @RequestParam(value = "height", required = false) Integer userHeight
    ){
        System.out.println("req5 에서 GET 요청을 받았습니다");
        System.out.println("name: " + userName);
        System.out.println("age: " + userAge);

    }

    /***************************************************************************/
    ////// GET, POST 등 여러 형태의 요청

    // @RequestMapping - GET이든 POST든 전부 받는다.
    // webapp > WEB-INF > request.jsp 에서 form 전송시 작동
//    @RequestMapping(value = "/req6")
//    void req6(){
//        System.out.println("req6 요청을 받았습니다^-^");
//    }

    // get 요청일 경우에만 받는다. 아니면 405오류 뜸
    // method = {RequestMethod.GET, RequestMethod.DELETE} => GET과 DELETE만 받도록 하겠다
    @RequestMapping(value = "/req6", method = RequestMethod.GET)
    void req6(){
        System.out.println("req6 요청을 받았습니다^-^");
    }

    // HTTP 요청 전송... POSTMAN
    @RequestMapping(
            value = "/req7",
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    String req7(){
        System.out.println("req7 요청을 받았습니다^-^");
        return "get";
    }

    //
    @RequestMapping(
            value = "/req77",
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    String req77(@RequestParam("data") String data){
        System.out.println("req777777777");
        System.out.println("data: " + data);
        return "get";
    }

    //
    @RequestMapping(
            value = "/req777",
            method = {RequestMethod.GET, RequestMethod.POST},
            // @RequestParam처럼 파라미터가 있어야만 요청을 받도록 설정한다
            params = { // 잘 안쓰는 방법이긴 함
                    "data=test", // data라는 파라미터가 존재하고, 값이 test일때만
                    "a",  // a라는 파라미터가 존재할때만
                    "!b" // b라는 파라미터가 존재하지 않을때만
            }
    )
    String req777(HttpServletRequest request){
        String data = request.getParameter("data");
        System.out.println("req7 요청을 받았습니다^-^");
        System.out.println("data: " + data);
        return "get";
    }

    ///// 요청 경로 안에 있는 어떤 단어를 가져오고 싶을 때
    @RequestMapping("/req8/{var}/b/c")
    public void get_req8(@PathVariable("var") String variable){
        System.out.println("req8이 실행됨");
        System.out.println("받은 경로 var 값: " + variable);
    }

//    ///// 요청 경로 안에 있는 어떤 단어를 가져오고 싶을 때
//    @RequestMapping("/req8/{var}/{var2}/c")
//    public void get_req8(
//            @PathVariable("var") String variable,
//            @PathVariable("var2") String variable2
//    ){
//        System.out.println("req8이 실행됨");
//        System.out.println("받은 경로 var 값: " + variable);
//        System.out.println("받은 경로 var2 값: " + variable2);
//    }







}







