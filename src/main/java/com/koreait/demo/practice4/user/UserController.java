package com.koreait.demo.practice4.user;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {
    @GetMapping("/login")
    public String get_login(HttpSession session) {
        Object user = session.getAttribute("user");

        // 로그인하고 왔다 == setAttribute해서 어떤 값이 있다
        if(user != null) {
            return "redirect:/user/success";
        }

        return "test1105/login";  // webapp > WEB-INF > views > test1105 > login.jsp가 보여짐
    }

    @PostMapping("/login")
    public String post_login(
            HttpSession session,
            User user
    ) {
        // 지금 id: korea / pw: 123 / name: 한국
        // 로그인에 성공했다면
        if(user.getId().equals("korea") && user.getPw().equals("123")) {
            // 로그인 성공한 유저를 session에 바인딩한다
            session.setAttribute("user", user);
            return "redirect:/user/success";
        }

        // 로그인에 실패했다면
        return "redirect:/user/login";
    }

    //////////////////////////////////////////////////////////////
    @GetMapping("/logout")
    public String get_logout(HttpSession session) {
        // 로그인 했든 안했든, 어떤 많은 정보가 있든지
        // 세션을 없애면 된다
        session.invalidate();
        // 로그인페이지로 다시 이동(GET)요청 한다
        return "redirect:/user/login";
    }
/////////////////////////////////////////////////////////////////////////////
    @GetMapping("/register")
    public String get_register() {
        return "test1105/register";
    }

    @GetMapping("/success")
    public String get_success(HttpSession session) {

        Object user = session.getAttribute("user");
        // 로그인 안하고 왔따 ==> session에 setAttribute한 적 없다
        if(user == null) {
            // 로그인 창으로 GET요청보내기
            return "redirect:/user/login";
        }

        return "test1105/success";
    }




}









