package kdt.advator.mail.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import kdt.advator.ad_company.dto.InquiryDTO;
import kdt.advator.common.domain.User;
import kdt.advator.estimate.dto.EmailDTO;
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
    public void joinEmail(User user, String ToEmail, String company, InquiryDTO inquiryDTO) throws MessagingException {
        String title = "[애드베이터] 견적 요청 – " + user.getStoreName() + " 사장님의 문의가 도착했습니다."; // 이메일 제목
        EmailDTO emailDTO = new EmailDTO(inquiryDTO, company);
        String content =
                "<p>" + company + " 담당자님, 안녕하세요?</p>" +
                        "<p>애드베이터를 통해 새로운 광고 견적 요청이 도착했습니다.</p>" +
                        "<ul>" +
                        "<li>광고를 희망하는 단지: " + emailDTO.getApartNames() + "</li>" +
                        "<li>광고 진행 시점: " + emailDTO.getStart() + "</li>" +
                        "<li>광고 진행 기간: " + emailDTO.getPeriod() + "</li>" +
                        "<li>업체명: " + user.getStoreName() + "</li>" +
                        "<li>업종: " + user.getIndustry().getName() + "</li>" +
                        "<li>사업유형: " + user.getBusiness().getName() + "</li>" +
                        "<li>고객명: " + user.getAuthor() + "</li>" +
                        "<li>고객 연락처: " + user.getPhone() + "</li>" +
                        "<li>고객 이메일: " + user.getEmail() + "</li>" +
                        "<li>기타 요청/문의사항: " + user.getDescription() + "</li>" +
                        "</ul>" +
                        "<p>고객님께 빠른 시일 내에 견적서를 전달해주시기 바랍니다.</p>" +
                        "<p>감사합니다.</p>" +
                        "<p>애드베이터 팀</p>";
        mailSend(user.getEmail(), ToEmail, title, content);
    }
}
