package com.example.questionnaire.controller;

import com.example.questionnaire.dao.QueryAnswerDao;
import com.example.questionnaire.model.QuestionAnswerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class QuestionController {
    private final QueryAnswerDao dao;

    @Autowired
    QuestionController(QueryAnswerDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/question", method = RequestMethod.GET)
    String question(Model model) {
        model.addAttribute("questionAnswerModel", new QuestionAnswerModel());
        model.addAttribute("meatList", dao.meatList());
        model.addAttribute("vegetableList", dao.vegetableList());
        return "question";
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public String confirm(@Validated
                          @ModelAttribute("questionAnswerModel") QuestionAnswerModel questionAnswerModel,
                          BindingResult bindingResult,
                          Model model) {
        model.addAttribute("questionAnswerModel", questionAnswerModel);
        model.addAttribute("meatList", dao.meatList());
        model.addAttribute("vegetableList", dao.vegetableList());
        if (bindingResult.hasErrors()) {
            return "question";
        }
        return "confirm";
    }

    @RequestMapping(value = "/complete", method = RequestMethod.POST)
    String complete(@Validated
                    @ModelAttribute("questionAnswerModel") QuestionAnswerModel questionAnswerModel) {
        dao.add(questionAnswerModel);
        return "complete";
    }
}