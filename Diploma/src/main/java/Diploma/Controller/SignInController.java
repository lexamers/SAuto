package Diploma.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignInController extends SuperController{//вход в систему

    @GetMapping("/signIn")
    public String signIn() {
        return "signIn";
    }
}
