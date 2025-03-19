package suicouponpayment.suicouponpaymentbackend.config.queue;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String EXCHANGE = "appExchange";
    public static final String OTP_ROUTING_KEY = "otp.routingKey";
    public static final String PASSDEL_OTP_ROUTING_KEY = "passdelOtp.routingKey";
    public static final String LOGIN_ROUTING_KEY = "login.routingKey";
    public static final String USER_ROUTING_KEY = "user.routingKey";
    public static final String PASSDEL_ROUTING_KEY = "passdel.routingKey";
    public static final String IOS_ROUTING_KEY = "ios.routingKey";
    public static final String ORDER_ROUTING_KEY = "order.routingKey";
    public static final String INVENTORY_ROUTING_KEY = "inventory.routingKey";
    public static final String IOSPRO_OTP_ROUTING_KEY = "iosproOtp.routingKey";
    public static final String OTP_QUEUE = "otpQueue";
    public static final String PASSDEL_OTP_QUEUE = "passdelOtpQueue";
    public static final String LOGIN_QUEUE = "loginQueue";
    public static final String USER_QUEUE = "userQueue";
    public static final String PASSDEL_QUEUE = "passdelQueue";
    public static final String IOS_QUEUE = "iosQueue";
    public static final String ORDER_QUEUE = "orderQueue";
    public static final String INVENTORY_QUEUE = "inventoryQueue";
    public static final String IOSPRO_OTP_QUEUE = "iosproOtpQueue";

    public RabbitMQConfig() {
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("appExchange");
    }

    @Bean
    public Queue otpQueue() {
        return new Queue("otpQueue", true);
    }

    @Bean
    public Queue passdelOtpQueue() {
        return new Queue("passdelOtpQueue", true);
    }

    @Bean
    public Queue loginQueue() {
        return new Queue("loginQueue", true);
    }

    @Bean
    public Queue userQueue() {
        return new Queue("userQueue", true);
    }

    @Bean
    public Queue passdelQueue() {
        return new Queue("passdelQueue", true);
    }

    @Bean
    public Queue iosQueue() {
        return new Queue("iosQueue", true);
    }

    @Bean
    public Queue orderQueue() {
        return new Queue("orderQueue", true);
    }

    @Bean
    public Queue inventoryQueue() {
        return new Queue("inventoryQueue", true);
    }

    @Bean
    public Queue iosproOtpQueue() {
        return new Queue("iosproOtpQueue", true);
    }

    @Bean
    public Binding otpBinding(Queue otpQueue, TopicExchange exchange) {
        return BindingBuilder.bind(otpQueue).to(exchange).with("otp.routingKey");
    }

    @Bean
    public Binding passdelOtpBinding(Queue passdelOtpQueue, TopicExchange exchange) {
        return BindingBuilder.bind(passdelOtpQueue).to(exchange).with("passdelOtp.routingKey");
    }

    @Bean
    public Binding loginBinding(Queue loginQueue, TopicExchange exchange) {
        return BindingBuilder.bind(loginQueue).to(exchange).with("login.routingKey");
    }

    @Bean
    public Binding userBinding(Queue userQueue, TopicExchange exchange) {
        return BindingBuilder.bind(userQueue).to(exchange).with("user.routingKey");
    }

    @Bean
    public Binding passdelBinding(Queue passdelQueue, TopicExchange exchange) {
        return BindingBuilder.bind(passdelQueue).to(exchange).with("passdel.routingKey");
    }

    @Bean
    public Binding iosBinding(Queue iosQueue, TopicExchange exchange) {
        return BindingBuilder.bind(iosQueue).to(exchange).with("ios.routingKey");
    }

    @Bean
    public Binding orderBinding(Queue orderQueue, TopicExchange exchange) {
        return BindingBuilder.bind(orderQueue).to(exchange).with("order.routingKey");
    }

    @Bean
    public Binding inventoryBinding(Queue inventoryQueue, TopicExchange exchange) {
        return BindingBuilder.bind(inventoryQueue).to(exchange).with("inventory.routingKey");
    }

    @Bean
    public Binding iosproOtpBinding(Queue iosproOtpQueue, TopicExchange exchange) {
        return BindingBuilder.bind(iosproOtpQueue).to(exchange).with("iosproOtp.routingKey");
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(this.jsonMessageConverter());
        return template;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(this.jsonMessageConverter());
        return factory;
    }
}
