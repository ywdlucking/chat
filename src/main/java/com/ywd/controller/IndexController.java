package com.ywd.controller;

import com.ywd.model.Message;
import com.ywd.model.User;
import com.ywd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/6/29.
 */
@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginS() {
        return "login";
    }

    @RequestMapping("/chat")
    public String chat(HttpServletRequest request, Model model) {
        String remoteUser = request.getRemoteUser();
        User user = userService.findByName(remoteUser);
        List<User> users = userService.listAll();
        List<User> listFriend = new ArrayList<>();
        for (User u : users){
            if (!u.getName().equals(remoteUser)){
                listFriend.add(u);
            }
        }
        model.addAttribute("user", user);
        model.addAttribute("listFriend", listFriend);
        model.addAttribute("msg", new Message());
        return "chat";
    }
}
