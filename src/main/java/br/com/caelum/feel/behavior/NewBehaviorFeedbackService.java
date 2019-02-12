package br.com.caelum.feel.behavior;

import org.springframework.stereotype.Service;

@Service
public class NewBehaviorFeedbackService {

    private final BehaviorFeedbackRepository behaviorFeedbackRepository;
    private final NewBehaviorFeedbackMail newFeedbackMail;

    public NewBehaviorFeedbackService(BehaviorFeedbackRepository behaviorFeedbackRepository, NewBehaviorFeedbackMail newFeedbackMail) {
        this.behaviorFeedbackRepository = behaviorFeedbackRepository;
        this.newFeedbackMail = newFeedbackMail;
    }

    public void save(BehaviorFeedback behaviorFeedback) {

        behaviorFeedbackRepository.save(behaviorFeedback);
        newFeedbackMail.sendMail(behaviorFeedback);
    }
}
