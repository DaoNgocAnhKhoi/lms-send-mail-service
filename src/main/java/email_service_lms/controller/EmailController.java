package email_service_lms.controller;

import email_service_lms.config.RabbitMQConfig;
import email_service_lms.model.EmailMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/email")
public class EmailController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // them thong tin cua order vao day
    @PostMapping("/payment-success")
    public ResponseEntity<String> sendPaymentSuccess(@RequestParam String email, @RequestParam String name) {
        Map<String, Object> templateData = new HashMap<>();
        templateData.put("name", name);
        templateData.put("date", LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        EmailMessage emailMessage = new EmailMessage(
                email,
                "Thanh toán thành công",
                "payment-success",
                templateData
        );

        rabbitTemplate.convertAndSend(RabbitMQConfig.EMAIL_QUEUE, emailMessage);
        return ResponseEntity.ok("Thông tin gửi email thanh toán đã được đưa vào hàng đợi.");
    }

    // 2. Endpoint gửi email chấp nhận/từ chối tạo tài khoản giáo viên
    @PostMapping("/account-request")
    public ResponseEntity<String> sendAccountRequest(@RequestParam String email, @RequestParam String name) {
        Map<String, Object> templateData = new HashMap<>();
        templateData.put("name", name);
        templateData.put("date", LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        EmailMessage emailMessage = new EmailMessage(
                email,
                "Thanh toán thành công",
                "payment-success",
                templateData
        );

        rabbitTemplate.convertAndSend(RabbitMQConfig.EMAIL_QUEUE, emailMessage);
        return ResponseEntity.ok("Thông tin gửi email thanh toán đã được đưa vào hàng đợi.");
    }

    // 3. Endpoint gửi email thông báo lý do khóa tài khoản
    @PostMapping("/lock-account")
    public ResponseEntity<String> sendLockAccount(@RequestParam String email, @RequestParam String name) {
        Map<String, Object> templateData = new HashMap<>();
        templateData.put("name", name);
        templateData.put("date", LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        EmailMessage emailMessage = new EmailMessage(
                email,
                "Thanh toán thành công",
                "payment-success",
                templateData
        );

        rabbitTemplate.convertAndSend(RabbitMQConfig.EMAIL_QUEUE, emailMessage);
        return ResponseEntity.ok("Thông tin gửi email thanh toán đã được đưa vào hàng đợi.");
    }
}
