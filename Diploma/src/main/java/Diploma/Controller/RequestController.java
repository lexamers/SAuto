package Diploma.Controller;

import Diploma.Persistence.Entity.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class RequestController extends SuperController{

    @GetMapping("/requestStatus/{id}")
    public String request (@PathVariable("id") Long id, Model model, HttpServletResponse httpResponse) throws IOException {
        Request currentRequest = requestService.getRequest(id);
        if (currentRequest==null || currentRequest.isFormed()){
            httpResponse.sendRedirect("/");
            return null;
        }
        model.addAttribute("currentRequest", currentRequest);
        return "requestStatus";
    }
}
