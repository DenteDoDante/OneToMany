package OneToMany.service;

import org.springframework.stereotype.Service;

import OneToMany.entity.UserFeedback;
import OneToMany.repository.FeedbackRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public UserFeedback SaveFeedback(UserFeedback userFeedback) {
        return this.feedbackRepository.save(userFeedback);
    }

}
