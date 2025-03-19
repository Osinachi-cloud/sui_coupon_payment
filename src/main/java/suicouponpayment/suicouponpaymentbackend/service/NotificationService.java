package suicouponpayment.suicouponpaymentbackend.service;

import suicouponpayment.suicouponpaymentbackend.config.queue.RabbitMQConfig;
import suicouponpayment.suicouponpaymentbackend.model.dto.request.EmailRequest;
//import oai.commons.config.RabbitMQConfig;
//import oai.commons.model.dto.EmailRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final RabbitTemplate rabbitTemplate;

    public NotificationService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendEmailRequest(EmailRequest emailRequest) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.OTP_QUEUE, emailRequest);
    }
}