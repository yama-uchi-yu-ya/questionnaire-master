package com.example.questionnaire.controller;

import com.example.questionnaire.dao.AdminDao;
import com.example.questionnaire.entity.ViewAnswer;
import com.example.questionnaire.model.AdminModel;
import com.example.questionnaire.model.UpdateAnswerModel;
import com.example.questionnaire.repository.ViewAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

@Controller
public class AdminController {
    private AdminDao dao;
    @Autowired
    AdminController(AdminDao dao) {
        this.dao = dao;
    }

    @Autowired
    ViewAnswerRepository repository;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    String login(Model model) {
        model.addAttribute("adminModel", new AdminModel());
        return "login";
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    String check(@Validated
                 @ModelAttribute("adminModel")AdminModel adminModel,
                 BindingResult bindingResult,
                 Model model,
                 HttpServletResponse httpServletResponse) throws Exception {
        if (bindingResult.hasErrors()) {
            System.out.println("バリデーションエラーだよ");
            return "login";
        }
        if (Objects.isNull(dao.checkOne(adminModel))) {
            System.out.println("ID・パスワードが違うよ");
            model.addAttribute("isLoginFailed", true);
            return "login";
        }
        System.out.println("ログイン成功！");
        Cookie cookie = new Cookie("loginUser", "userId");
        cookie.setMaxAge(60 * 60);
        cookie.setPath("/");
        httpServletResponse.addCookie(cookie);
        return  "redirect:view";
    }
    
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    ModelAndView view(@CookieValue(value = "loginUser", required = false, defaultValue = "none") String cookieName,
                              ModelAndView modelAndView, Pageable pageable) {
        if (cookieName.equals("none")) {
            System.out.println("ログインしてね");
            modelAndView.setViewName("redirect:login");
            return modelAndView;
        }
        System.out.println("viewに来たよ");
        Page<ViewAnswer> pageList = repository.findByIsDeleted(pageable, 0);
        List<ViewAnswer> answerList = pageList.getContent();
        modelAndView.addObject("pages", pageList);
        modelAndView.addObject("answers", answerList);
        modelAndView.addObject("meatList", dao.meatList());
        modelAndView.addObject("viewVegetableAnswers", dao.viewVegetableAnswerList());
        modelAndView.addObject("vegetableList", dao.vegetableList());
        modelAndView.setViewName("view");
        return modelAndView;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    String logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        Cookie cookies[] = httpServletRequest.getCookies();
        for (var cookie : cookies) {
            if ("loginUser".equals(cookie.getName())) {
                cookie.setMaxAge(0);
                cookie.setPath("/");
                httpServletResponse.addCookie(cookie);
            }
        }
        System.out.println("ログアウト成功");
        return "redirect:login";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    String delete(@RequestParam int answer_id) {
        dao.delete(answer_id);
        return "redirect:view";
    }

    @RequestMapping(value = "/update/{answer_id}", method = RequestMethod.GET)
    ModelAndView update(@CookieValue(value = "loginUser", required = false, defaultValue = "none") String cookieName,
                        @PathVariable("answer_id") int answer_id, ModelAndView modelAndView) {
        if (cookieName.equals("none")) {
            System.out.println("ログインしてね");
            modelAndView.setViewName("redirect:/login");
            return modelAndView;
        }
        modelAndView.addObject("updateAnswerModel", new UpdateAnswerModel());
        modelAndView.addObject("updateAnswer", dao.updateAnswerList(answer_id));
        modelAndView.addObject("updateVegetableAnswer", dao.updateVegetableAnswerList(answer_id));
        modelAndView.addObject("meatList", dao.meatList());
        modelAndView.addObject("vegetableList", dao.vegetableList());
        modelAndView.setViewName("update");
        return modelAndView;
    }

    @RequestMapping(value = "/doUpdate", method = RequestMethod.POST)
    String doUpdate(@Validated
                    @ModelAttribute("updateAnswerModel") UpdateAnswerModel updateAnswerModel,
                    BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("updateAnswer", updateAnswerModel);
            model.addAttribute("updateVegetableAnswer", updateAnswerModel.getVegetable_id());
            model.addAttribute("meatList", dao.meatList());
            model.addAttribute("vegetableList", dao.vegetableList());
            return "update";
        }
        dao.update(updateAnswerModel);
        return "redirect:view";
    }
}

