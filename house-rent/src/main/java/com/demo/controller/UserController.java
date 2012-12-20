package com.demo.controller;

import com.demo.bean.CommonDto;
import com.demo.domain.User;
import com.demo.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 12-12-20
 * Time: 上午12:25
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/manage")
public class UserController {

    @Resource
    UserRepository userRepository;

    @RequestMapping("/listUser/{pageNumber}")
    public String toHouse(Model model,User user,CommonDto commonDto){

         Page<User> userPage = userRepository.findAll(new PageRequest(commonDto.getPageNumber()-1, commonDto.getPageSize()));
        addPageInfo(model, userPage, "/manage/listUser").addAttribute("userPage", userPage);
        return "user";
    }

    @RequestMapping("/toAddUser")
    public String toAddHouse(Model model){
        model.addAttribute("user",new User());
        return "saveuser";
    }

    @RequestMapping("/toUpdateUser/{id}")
    public String toUpdateHouse(Model model,@PathVariable Integer id){
        model.addAttribute("user",userRepository.findOne(id));
        return "saveuser";
    }

    @RequestMapping("/saveUser/{id}")
    public String saveHouse(Model model,User user,RedirectAttributes redirectAttrs){
        try {
            userRepository.save(user);
            redirectAttrs.addFlashAttribute("message", "保存"+user.getName()+"成功");
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("error", "保存"+user.getName()+"失败");
        }
        return "redirect:/manage/listUser/1";
    }

    @RequestMapping("/delUser")
    public String delUser(@PathVariable Integer id,RedirectAttributes redirectAttrs){
        userRepository.delete(id);
        redirectAttrs.addFlashAttribute("message", "删除id为"+id+"的用户记录成功");
        return "redirect:/manage/listHouse/1";
    }

    public <T> Model addPageInfo(Model model, Page<T> page, String contextUrl) {

        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());
        Integer totalPages = page.getTotalPages();
        Long totalElements = page.getTotalElements();
        Integer pageSize = page.getSize();

        return model == null ? model : model.addAttribute("currentIndex", current)
                .addAttribute("beginIndex", begin)
                .addAttribute("endIndex", end)
                .addAttribute("totalPages", totalPages)
                .addAttribute("totalElements", totalElements)
                .addAttribute("contextUrl", contextUrl)
                .addAttribute("pageSize", pageSize);
    }


}
