package com.demo.controller;

import com.demo.bean.CommonDto;
import com.demo.domain.House;
import com.demo.repository.HouseRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.persistence.criteria.*;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 12-12-20
 * Time: 上午12:26
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/manage")
public class HouseController {

    @Resource
    HouseRepository houseRepository;

    @RequestMapping("/listHouse/{pageNumber}")
    public String toHouse(Model model, House house, CommonDto commonDto) {

        Page<House> housePage = null;
        final String name = house.getName();
        if (isBlank(name)) {
            housePage = houseRepository.findAll(new PageRequest(commonDto.getPageNumber()-1, commonDto.getPageSize()));
        } else {
            housePage = houseRepository.findAll(new Specification<House>() {
                @Override
                public Predicate toPredicate(Root<House> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    Path<String> path = root.get("name");
                    return cb.like(path, "%" + name + "%");
                }
            }, new PageRequest(commonDto.getPageNumber(), commonDto.getPageSize()));
        }

        addPageInfo(model, housePage, "/manage/listHouse").addAttribute("housePage", housePage);

        return "house";
    }

    @RequestMapping("/toAddHouse")
    public String toAddHouse(Model model){
        model.addAttribute("house",new House());
        return "savehouse";
    }

    @RequestMapping("/toUpdateHouse/{id}")
    public String toUpdateHouse(Model model,@PathVariable Integer id){
        model.addAttribute("house",houseRepository.findOne(id));
        return "savehouse";
    }

    @RequestMapping("/saveHouse/{id}")
    public String saveHouse(House house,RedirectAttributes redirectAttrs){
        try {
            houseRepository.save(house);
            redirectAttrs.addFlashAttribute("message", "保存"+house.getName()+"成功");
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("error", "保存"+house.getName()+"失败");
        }
        return "redirect:/manage/listHouse/1";
    }

    @RequestMapping("/buyHouse/{id}")
    public String buyHouse(House house,RedirectAttributes redirectAttrs){

        House house1 = houseRepository.findOne(house.getId());
        house1.setStatus("已售出");
        houseRepository.save(house1);

        redirectAttrs.addFlashAttribute("message", "购买"+house.getName()+"成功");
        return "redirect:/manage/listHouse/1";
    }

    @RequestMapping("/delHouse")
    public String delHouse(@PathVariable Integer id,RedirectAttributes redirectAttrs){
        houseRepository.delete(id);
        redirectAttrs.addFlashAttribute("message", "删除id为"+id+"的房屋记录成功");
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
