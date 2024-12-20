package kdt.advator.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

public class SMTPConfig {
    @Value("${spring.mail.host}")
    private String mailHost;
    @Value("${spring.mail.port}")
    private int mailPort;
    @Value("${spring.mail.username}")
    private String mailUser;
    @Value("${spring.mail.password}")
    private String mailPassword;
    @Bean
    // JAVA MAILSENDER 인터페이스를 구현한 객체를 빈 등록
    public JavaMailSender mailSender() {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl(); // JavaMailSender의 구현체 생성
        mailSender.setHost(mailHost); // 이메일 전송에 사용할 SMTP 서버 호스트 설정
        mailSender.setPort(mailPort); // 587로 포트를 지정
        mailSender.setUsername(mailUser); // 구글 계정
        mailSender.setPassword(mailPassword); // 구글 앱 비밀번호

        Properties javaMailProperties = new Properties(); // JavaMail의 속성을 설정하기 위해 Properties 객체 생성
        javaMailProperties.put("mail.transport.protocol", "smtp"); // 프로토콜로 smtp 사용
        javaMailProperties.put("mail.smtp.auth", "true"); // smtp 서버에 인증이 필요
        javaMailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // SSL 소켓 팩토리 클래스 사용
        javaMailProperties.put("mail.smtp.starttls.enable", "true"); // STARTTLS(TLS를 시작하는 명령)를 사용하여 암호화된 통신 활성화
        javaMailProperties.put("mail.debug", "true"); // 디버깅 정보 출력
        javaMailProperties.put("mail.smtp.ssl.trust", mailHost); // smtp 서버의 ssl 인증서를 신뢰
        javaMailProperties.put("mail.smtp.ssl.protocols", "TLSv1.2"); // 사용할 ssl 프로토콜 버젼

        mailSender.setJavaMailProperties(javaMailProperties); // mailSender에 우리가 만든 properties 삽입

        return mailSender;// 빈 등록
    }
}
