package liga.medical.messageanalyzer.api.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.logging.Logger;

@RestController
@RequestMapping("/message")
public class MessageController {
    Logger logger = Logger.getLogger(String.valueOf(MessageController.class));

    @Autowired
    AmqpTemplate template;

    @PostMapping("/analyze")
    public void queue1(@RequestBody JsonNode body) {
        logger.info("passing to common_monitoring");
        template.convertAndSend("common_monitoring", body);
    }
}
