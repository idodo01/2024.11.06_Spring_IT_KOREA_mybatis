package com.koreait.demo.practice4;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
public class RequestController {
    // RequestMapping의 GET 버전
    // 1) ModelAndView를 통해 View에 데이터를 실어보내기
    @GetMapping("/data/req1")
    public ModelAndView get_req1() {
        int number = 10;
        ModelAndView modelAndView = new ModelAndView();
        // num이라는 이름으로 number값을 binding한다
        modelAndView.addObject("num", number);
        // View 이름도 설정해야 한다
        modelAndView.setViewName("/data/req1");
        return modelAndView;
    }

    // Model객체를 주입받아서 데이터를 binding
    @GetMapping("/data/req2")
    public String get_req2(Model model) {
        int number = 10;
        // num이라는 이름으로 number값을 binding한다
        model.addAttribute("num", number);
        return "/data/req1";
    }

    /*************************************************/
    // HttpServletResponse 객체를 주입받아 sendRedirect로 리다이렉트
    @GetMapping("/data/req3")
    public void get_req3(HttpServletResponse response)
            throws IOException {
        System.out.println("req3 실행되었음..");

        // 반환 방법 1
        response.sendRedirect("/data/req1");
    }

    @GetMapping("/data/req4")
    public String get_req4(){
        System.out.println("req4 실행되었음..");
        // 반환 방법 2
        return "redirect:/data/req1";
    }

    @GetMapping("/data/req5")
    public String get_req5(Model model){
        System.out.println("req5 실행되었음..");
        // model에 걸어준 데이터는 현재 요청에서만 유효하다 == request scope를 가진다.
        // redirect시, 사라진다.
        model.addAttribute("myNumber", 100);
        // req3처럼 해도 되지만 반환에 redirect를 붙이면 끝이다.
        return "redirect:/data/req2";
    }
/////////////////////////////////////////////

    // 1. Integer 값
    // 1) return "/data/req1"
//    @GetMapping("/data/req6")
//    public String get_req6(Model model, @RequestParam("num") Integer myNumber){
//        System.out.println("req6 실행되었음..");
//
//        model.addAttribute("myNumber", myNumber);
//        return "/data/req1";
//    }

    // 2) redirect:/dat~~
//    @GetMapping("/data/req6")
//    public String get_req6(Model model, @RequestParam("num") Integer myNumber){
//        System.out.println("req6 실행되었음..");

    // 리다이렉트할 땐, 리턴값뒤에 값을 붙여줘야함
//        return "redirect:/data/req2?myNumber=" + myNumber;
//    }

    // 2. String
    @GetMapping("/data/req6")
    public String get_req6(Model model, @RequestParam("message") String message){
        System.out.println("req6 실행되었음..");
        System.out.println("message: " + message);
//        model.addAttribute("myNumber", myNumber);
        // req3처럼 해도 되지만 반환에 redirect를 붙이면 끝이다.
//        return "/data/req1";
        //// 영어,숫자, 간단한 기호 외에는 URL인코딩 후에 URL으로 작성해야한다!
        String encodedMessage = URLEncoder.encode(message, StandardCharsets.UTF_8);
//        String decodedMessage = URLDecoder.decode(encodedMessage, StandardCharsets.UTF_8);
        return "redirect:/data/req2?message=" + encodedMessage;
    }

    @GetMapping("/data/req7")
    public String get_req7(
            // 이 핸들러 메서드가 redirect를 할 것이라면 이 친구가 BEST!
            RedirectAttributes redirectAttributes,
            @RequestParam("m1") String m1,
            @RequestParam("m2") String m2,
            @RequestParam("m3") String m3
    ){
        System.out.println("req7 실행되었음..");
        //redirectAttributes에 add하게 되면 자동으로
        // m1=??&m2=??&m3=?? 를 자동으로 url에 붙여준다!
        // 그것도 URLEncoding해서;
        redirectAttributes.addAttribute("m1", m1);
        redirectAttributes.addAttribute("m2", m2);
        redirectAttributes.addAttribute("m3", m3);
        return "redirect:/data/req2";
    }

    /*****************************************************************/
    // 로그인 버튼 누르기 전, 작동
    @GetMapping("/data/login")
    public void get_data_login(){
        System.out.println("/data/login - get 요청");
    }

    // 로그인 버튼 누른 후, 작동
    @PostMapping("/data/login")
    public void post_data_login(@RequestParam("data") String data){
        System.out.println("/data/login - post 요청");
        if(data.equals("1")){
            System.out.println("성공");
        }else{
            System.out.println("실패");
        }
    }



}
