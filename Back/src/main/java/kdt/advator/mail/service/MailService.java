package kdt.advator.mail.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String companyMember;

    @Transactional
    public void mailSend(String replyEmail, String toEmail, String title, String content) throws MessagingException {
        // JavaMailSender 객체 사용해 MimeMessage 객체 생성
        MimeMessage message = mailSender.createMimeMessage();
        // 이메일 메시지 관련 설정
        // true를 전달하여 multipart 형식의 메시지를 지원 및 "utf-8"을 전달해 문자 인코딩 설정
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
        // 이메일의 발신자 주소 설정
        helper.setFrom(companyMember);
        // 이메일의 답장 주소 설정
        helper.setReplyTo(replyEmail);
        // 이메일의 수신자 주소 설정
        helper.setTo(toEmail);
        // 이메일의 제목을 설정
        helper.setSubject(title);
        // 이메일의 내용 설정 두 번째 매개 변수에 true를 설정하여 html 설정으로한다.
        helper.setText(content, true);
        mailSender.send(message);
    }

    // 이메일 전송
    @Transactional
    public void joinEmail(String replyEmail, String ToEmail, String company) throws MessagingException {
        String title = "[AdVator] " + company + " 15초 광고 견적 요청합니다."; // 이메일 제목
        String content =
                "메일 발송."; // 이메일 내용 삽입
        mailSend(replyEmail, ToEmail, title, content);
    }
}
