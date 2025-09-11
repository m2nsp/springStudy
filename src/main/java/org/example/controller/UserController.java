package org.example.controller;

import org.example.domain.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
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

    // ============================
    // JSP 뷰 용 (ModelAndView)
    // ============================

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
    @RequestMapping(value = "/add", method = RequestMethod.GET)
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
        model.setViewName("redirect:" + "/users/list");
        return model;
    }

    /**
     * 모든 유저 목록을 조회한다
     * @param model ModelAndView - 뷰 용
     */
//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    public String listUsers(Model model) {
//        List<User> users = userMapper.getAllUsers();
//        model.addAttribute("users", users);
//        return "userList";  //userList.jsp
//    }
    @RequestMapping(value ="/list", method = RequestMethod.GET)
    public ModelAndView listUsers(ModelAndView model) throws Exception {
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
    public ModelAndView getUserDetail(@PathVariable("id") long id, ModelAndView model) throws Exception {
//        User user = userMapper.getUserById(id);
        User user = userService.getUserDetail(id);
        model.addObject("user", user);
        model.setViewName("userDetail");    //userDetail.jsp
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
                                    ModelAndView model) throws Exception {
        User updateUser = new User();
        updateUser.setId(id);
        updateUser.setName(name);
        updateUser.setEmail(email);
//        userMapper.updateUser(updateUser);
        userService.updateUser(updateUser);
        model.setViewName("redirect:" + "/users/list");
        return model;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable("id") long id, ModelAndView model) throws Exception {
        userService.deleteUser(id);
        model.setViewName(("redirect:/users/list"));
        return model;
    }

    // ============================
    // APi/JSON용 (HashMap)
    // ============================

    // 모든 유저 조회
    @RequestMapping(value = "/api/list", method = RequestMethod.GET)
    @ResponseBody
    public HashMap<String, Object> userList() throws Exception {
        List<User> users = userService.getAllUsers();
        HashMap<String, Object> result = new HashMap<>();
        result.put("status", "SUCCESS");
        result.put("users", users);
        return result;
    }

    // 유저 상세 조회
    @RequestMapping(value = "/api/detail/{id}", method = RequestMethod.GET)
    @ResponseBody
    public HashMap<String, Object> getUserDetail(@PathVariable("id") long id) throws Exception {
        User user = userService.getUserDetail(id);
        HashMap<String, Object> result = new HashMap<>();
        if(user != null) {
            result.put("status", "SUCCESS");
            result.put("user", user);
        } else {
            result.put("status", "FAILURE");
            result.put("message", "USER_NOT_FOUND");
        }
        return result;
    }

    //유저 추가
    @RequestMapping(value = "/api/add", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> addUser(@RequestParam String name, @RequestParam String email) throws Exception {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        userService.addUser(user);

        HashMap<String, Object> result = new HashMap<>();
        result.put("status", "SUCCESS");
        result.put("user", user);
        return result;
    }

    //유저 수정
    @RequestMapping(value = "/api/update", method = RequestMethod.POST)
    public HashMap<String, Object>updateUser (@RequestParam long id,
                                              @RequestParam String name,
                                              @RequestParam String email) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        User updatedUser = new User();
        updatedUser.setId(id);
        updatedUser.setEmail(email);
        userService.updateUser(updatedUser);
        result.put("status", "SUCCESS");
        result.put("user", updatedUser);
        return result;
    }

    @RequestMapping(value = "/delete/:id", method = RequestMethod.DELETE)
    public HashMap<String, Object> deleteUser(Long id) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        userService.deleteUser(id);
        result.put("status", "SUCCESS");
        return result;
    }

}
