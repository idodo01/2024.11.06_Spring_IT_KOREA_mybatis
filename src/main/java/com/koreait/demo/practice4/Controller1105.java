//package com.koreait.demo.practice4;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//
////@RequestMapping 를 클래스 레벨에 작성 시, 해당 경로가 먼저 온 후 메서드의 경로가 붙는다
//// get_req1의 최종경로 => "/test1105" + "/req1"
//@Controller
//@RequestMapping("/test1105")
//// mySessionModel 라는 이름이 Model 객체에 addAttribute가 되었다면,
//// 자동으로 session에도 setAttribute해라
//@SessionAttributes(names = {"mySessionModel"})
//public class Controller1105 {
//
//    // 모든 메서드에서 공통으로 사용하는 view 파일의 경로를 만든다
//    String create_view_path(String path){
//        return "test1105/" + path;
//    }
//
//    // 전달되는 매개변수 파라미터 이름과 자바 Bean 혹은 POJO 객체의 멤버변수명이 같으면
//    // 자동으로 변수에 파라미터 값이 주입되면서 객체가 생성된다
//    @GetMapping("/req1")
//    public void get_req1(
//            Model model,
//            Person person
//    ){
//        System.out.println(person);
//        model.addAttribute("person", person);
//    }
//
//    // @ModelAttribute를 메서드에 작성하면 핸들러 메서드 실행되기 전에 먼저 실행이 된다
//    // 이 컨트롤러에서 사용하는 공통 데이터를 미리 만들어 둘 수 있다
//
//    // person2라는 이름으로 Model객체에 addAttribute한다
//    @ModelAttribute("person2")
//    public Person createPersonModel(){
//        Person person = new Person();
//        person.setAge(20);
//        person.setName("test222");
//        return person;
//    }
//
//    // person3라는 이름으로 Model객체에 addAttribute한다
//    @ModelAttribute
//    public void createPersonModel(Model model){
//        Person person = new Person();
//        person.setAge(50);
//        person.setName("test333");
//        model.addAttribute("person3", person);
//    }
//
//    @GetMapping("/req2")
//    public String get_req2(){
//        return create_view_path("req1");
//    }
//
//    // @ModelAttribute를 매개변수로 작성하면, 파라미터 데이터(폼 데이터)를 자동으로 객체에 바인딩되고
//    // 또한 Model에 자동으로 추가한다. (addAttribute)
//    @GetMapping("/req3")
//    public String get_req3(@ModelAttribute("person4") Person person){
//        System.out.println(person);
//        return create_view_path("req1");
//    }
//
//    /*************************** 세션의 사용 ************************************/
//    // 1)HttpServletRequest를 가져온 후, getSession한다
//    @GetMapping("/session1")
//    public String get_session1(HttpServletRequest request){
//        HttpSession session = request.getSession();
//        return create_view_path("session1");
//    }
//
//    // 2)HttpSession을 주입받는다
//    @GetMapping("/session2")
//    public String get_session2(HttpSession session){
//        session.setAttribute("data", "세션에 바인딩 된 데이터!");
//        return create_view_path("session1");
//    }
//    //////////////// 세션에 바인딩 되어 있는 객체를 가져와서 사용할 경우
//    // 1)HttpSession을 주입받는다
//    @GetMapping("/session3")
//    public String get_session3(HttpSession session){
//        // 일일이 session에 바인딩 된 데이터를 가져오기
//        String data = (String) session.getAttribute("data"); // "세션에 바인딩 된 데이터!"
//        System.out.println("session데이터: " + data);
//        return create_view_path("session1");
//    }
//
//    // 2) @SessionAttriubte를 사용해서 Session에 바인딩된 데이터를 바로 가져온다
//    // session에 바인딩 되어 있는 "data" 라는 이름의 데이터를 바로 가져오기
//    // required=true가 기본이기때문에 세션에 "data"가 없으면 에러. 실행안된다
//    @GetMapping("/session4")
//    public String get_session4(@SessionAttribute("data") String sessionData){
//        System.out.println("session데이터: " + sessionData);
//        return create_view_path("session1");
//    }
//
//    /****************** 어떤 Model을 addAttribute하면서 Session에도 setAttribute하고 싶을때****/
//    // 1) 기존 방법대로 Session객체와 Model 객체를 사용한다
//    @GetMapping("/session5")
//    public String get_session5(Model model, HttpSession session){
//        String mySessionModel = "My session model^-^";
//        model.addAttribute("mySessionModel", mySessionModel);
//        session.setAttribute("mySessionModel", mySessionModel);
//        return create_view_path("session1");
//    }
//
//    // 2) 1)을 SessionAttributes를 사용해서 자동으로 하기
//    @GetMapping("/session6")
//    public String get_session6(Model model){
//        String mySessionModel = "My session model^-^";
//        model.addAttribute("mySessionModel", mySessionModel);
//        return create_view_path("session1");
//    }
//    // 위의 session6가 실행되고 나면 session에 데이터가 setAttribute되었을거니까 가져와보자
//    @GetMapping("/session7")
//    public String get_session7(@SessionAttribute("mySessionModel") String sessionData){
//        System.out.println(sessionData);
//        return create_view_path("session1");
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//}
