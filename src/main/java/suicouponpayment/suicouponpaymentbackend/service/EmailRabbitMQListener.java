//package facecraft.facecraftbackend.service;
//
//import org.springframework.stereotype.Component;
//
//@Component
//public class EmailRabbitMQListener {
//
//    private final EmailService emailService;
//
//    public EmailRabbitMQListener(EmailService emailService) {
//        this.emailService = emailService;
//    }
//
//    @RabbitListener(queues = RabbitMQConfig.OTP_QUEUE)
//    public void receiveEmailRequest(EmailRequest emailRequest) {
//        // Extract details from the emailRequest and send the email
//        emailService.sendEmail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getBody());
//    }
//}