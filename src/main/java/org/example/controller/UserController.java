package org.example.controller;

import org.example.domain.User;
import org.example.mapper.UserMapper;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.example.service.UserService;

import java.util.List;

//import static org.example.App.userMapper;

@Controller
@RequestMapping("/users")
public class UserController {
//    private final UserMapper userMapper;
//
//    public UserController(UserMapper userMapper) {
//        this.userMapper = userMapper;
//    }

    @Autowired
    private UserService userService;

    /**
     * 유저 추가 폼 뷰 호출
     * @return
     */
//  1. model + return String 방식 사용
//    @RequestMapping(value="/add", method = RequestMethod.GET)
//    public String addForm() {
//        return "userForm";      //userForm.jsp
//    }
//  2. modelAndView 방식 사용
    public ModelAndView addForm(ModelAndView model){
        model.setViewName("userForm");
        return model;
    }

    /**
     * 새로운 유저를 추가한다
     * @param name
     * @param email
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
//    public String addUser(@RequestParam String name, @RequestParam String email) {
//        User user = new User();
//        user.setName(name);
//        user.setEmail(email);
//        userMapper.addUser(user);
//        return "redirect:/list";
//    }
    public ModelAndView addUser(@RequestParam String name, @RequestParam String email, ModelAndView model){
        User user = new User();
        user.setName(name);
        user.setEmail(email);
//        userMapper.addUser(user);
        model.setViewName("redirect:" + "/list");
        return model;
    }

    /**
     * 모든 유저 목록을 조회한다
     * @param model ModelAndView
     */
//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    public String listUsers(Model model) {
//        List<User> users = userMapper.getAllUsers();
//        model.addAttribute("users", users);
//        return "userList";  //userList.jsp
//    }
    @RequestMapping(value ="/list", method = RequestMethod.GET)
    public ModelAndView listUsers(ModelAndView model) throws Exception {
//        List<User> users = userMapper.getAllUsers();
        List<User> users = userService.getAllUsers();
        model.addObject("users", users);
        model.setViewName("userList");
        return model;
    }

    /**
     * 유저의 상세정보를 조회한다
     * @param id
     * @param model
     * @return
     */
//    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
//    public String getUser(@PathVariable("id") Long id, Model model) {
//        User user = userMapper.getUserById(id);
//        model.addAttribute("user", user);
//        return "userDetail";
//    }
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public ModelAndView getUesrDetail(@PathVariable("id") long id, ModelAndView model) throws Exception {
//        User user = userMapper.getUserById(id);
        User user = userService.getUserDetail(id);
        model.addObject("user", user);
        return model;
    }

    /**
     * 유저의 정보를 업데이트 한다
     * @param id
     * @param name
     * @param email
     * @return
     */
//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    public String updateUser(@RequestParam long id,
//                             @RequestParam String name,
//                             @RequestParam String email) {
//        User user = new User();
//        user.setId(id);
//        user.setName(name);
//        user.setEmail(email);
//        userMapper.updateUser(user);
//        return "redirect:/user/list";
//    }
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView updateUser(@RequestParam long id,
                                    @RequestParam String name,
                                    @RequestParam String email,
                                    ModelAndView model) {
        User updateUser = new User();
        updateUser.setId(id);
        updateUser.setName(name);
        updateUser.setEmail(email);
//        userMapper.updateUser(updateUser);
        model.setViewName("redirect:" + "/user/list");
        return model;
    }

    @RequestMapping(value = "/delete/:id", method = RequestMethod.DELETE)
    public void deleteUser(Long id) throws Exception {
        userService.deleteUser(id);
    }

}
