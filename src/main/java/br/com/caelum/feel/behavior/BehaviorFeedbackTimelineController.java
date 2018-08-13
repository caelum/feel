package br.com.caelum.feel.behavior;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BehaviorFeedbackTimelineController {
	
	@Autowired
	private BehaviorFeedbackRepository behaviorFeedbackRepository;

	@GetMapping("/behavior/anonimous/timeline/{hash}")
	public String helloTimeline(Model model, @PathVariable("hash") String hash) {
		
		Optional<BehaviorFeedback> feedback = behaviorFeedbackRepository.findByHash(hash);
		
		if(!feedback.isPresent()) {
			model.addAttribute("error", "Provavelmente o link que você acessou não está correto. Por conta disso, não conseguimos achar sua mensagem :(. ");
		}
				
		
		return "complains/person-timeline";
	}
	
	@GetMapping("/behavior/anonimous/timeline/messages/{hash}")
	@ResponseBody
	public List<BehaviorFeedbackMessageDTO> messages(Model model, @PathVariable("hash") String hash) {
		
		BehaviorFeedback feedback = behaviorFeedbackRepository.findByHash(hash).get();
		ArrayList<BehaviorFeedbackMessageDTO> list = new ArrayList<>();
		list.add(new BehaviorFeedbackMessageDTO(feedback));
		
		return list;
	}
		
}
