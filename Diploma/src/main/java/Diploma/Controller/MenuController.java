package Diploma.Controller;

import Diploma.Persistence.Entity.Request;
import Diploma.Persistence.Entity.RequestItem;
import Diploma.Service.RequestItemService;
import Diploma.Service.RequestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Controller
public class MenuController extends SuperController{
    @Autowired
    private RequestItemService requestItemService;

    @GetMapping("/menu")
    public String menu() {
        return "menu";
    }

    @PostMapping("/addItem")
    public RedirectView addItem(@RequestParam(value = "request") HttpServletRequest request, Long currentRequestId){
        HttpSession session = request.getSession(true);
        Long requestId;

        if (currentRequestId==null) {
            Request request = new Request();
            request.setDate(LocalDateTime.now());
            request.setStatus(RequestStatus.Formed);
            requestId = requestService.addRequest(request);
            session.setAttribute(requestId);
        } else {
            requestId = currentRequestId;
        }

        Request currentRequest = requestService.getRequest(requestId);
        RequestItem currentRequestItem = requestItemService.getByRequest(currentRequest);

        if (currentRequestItem!=null){
            currentRequestItem.setQuantity(currentRequestItem.getQuantity()+1);
        } else {
            currentRequestItem = new RequestItem();
            currentRequestItem.setRequest(currentRequest);
            currentRequestItem.setQuantity(1L);
        }
        requestItemService.addRequestItem(currentRequestItem);
        return new RedirectView("/menu-"+requestId);
    }
}