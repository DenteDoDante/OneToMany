package OneToMany.controller;

import org.springframework.web.bind.annotation.RestController;

import OneToMany.entity.UserFeedback;
import OneToMany.service.FeedbackService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping("/publishFeedBack")
    public UserFeedback postUserFeedback(@RequestBody UserFeedback UserFeedback) {

        return feedbackService.SaveFeedback(UserFeedback);
    }

}
