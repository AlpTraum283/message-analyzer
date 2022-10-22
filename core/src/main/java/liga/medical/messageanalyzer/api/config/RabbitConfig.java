package liga.medical.messageanalyzer.api.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

@Configuration
public class RabbitConfig {
    private static final String LOCALHOST = "172.16.16.7";
    private static final String COMMON_MONITORING_QUEUE = "common_monitoring";

    Logger logger = Logger.getLogger(String.valueOf(RabbitConfig.class));

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory(LOCALHOST);
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public Queue commonMonitoring() {
        return new Queue(COMMON_MONITORING_QUEUE);
    }
}
