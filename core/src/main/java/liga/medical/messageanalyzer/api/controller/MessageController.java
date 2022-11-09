package liga.medical.messageanalyzer.api.controller;

import liga.medical.common.dto.RabbitMessageDto;
import liga.medical.common.dto.annotations.DbLog;
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
    @DbLog
    public void queue1(@RequestBody RabbitMessageDto rabbitMessageDto) {
        logger.info("passing to common_monitoring");
        template.convertAndSend("common_monitoring", rabbitMessageDto);
    }
}
