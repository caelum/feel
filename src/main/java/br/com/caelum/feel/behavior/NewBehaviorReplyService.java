package br.com.caelum.feel.behavior;

import org.springframework.stereotype.Service;

@Service
public class NewBehaviorReplyService {

    private final BehaviorReplyRepository behaviorReplyRepository;
    private  BehaviorFeedbackMail feedbackMail;

    public NewBehaviorReplyService(BehaviorReplyRepository behaviorReplyRepository, BehaviorFeedbackMail feedbackMail) {
        this.behaviorReplyRepository = behaviorReplyRepository;
        this.feedbackMail = feedbackMail;
    }

    public void save(BehaviorReply reply) {
        behaviorReplyRepository.save(reply);
        feedbackMail.sendAsyncNewReplyMail(reply);
    }
}
