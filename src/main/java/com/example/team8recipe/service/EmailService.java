package com.example.team8recipe.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Random;

@PropertySource("classpath:application.properties")
@Slf4j
@RequiredArgsConstructor
@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    //인증번호 생성
    private String ePw;

    @Value("${spring.mail.username}")
    private String id;

    public MimeMessage createMessage(String to)throws MessagingException, UnsupportedEncodingException {
        log.info("보내는 대상 : "+ to);
        log.info("인증 번호 : " + ePw);
        MimeMessage  message = javaMailSender.createMimeMessage();

        message.addRecipients(MimeMessage.RecipientType.TO, to); // to 보내는 대상
        message.setSubject("자취생들을 위한 레시피 회원가입 인증 코드"); //메일 제목

        // 메일 내용 메일의 subtype을 html로 지정하여 html문법 사용 가능
        String msg="";
        msg += "<div style='margin:100px;'>";
        msg += "<h1> 안녕하세요</h1>";
        msg += "<h1> 자취생들을 위한 레시피 입니다</h1>";
        msg += "<br>";
        msg += "<p>아래 코드를 회원가입 창으로 돌아가 입력해주세요<p>";
        msg += "<br>";
        msg += "<p>항상 당신의 레시피를 응원합니다. 감사합니다!<p>";
        msg += "<br>";
        msg += "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msg += "<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>";
        msg += "<div style='font-size:130%'>";
        msg += "CODE : <strong>";
        msg += ePw + "</strong><div><br/> "; // 메일에 인증번호 넣기
        msg += "</div>";

        message.setText(msg, "utf-8", "html"); //내용, charset타입, subtype
        message.setFrom(new InternetAddress(id,"Tean8Recipe")); //보내는 사람의 메일 주소, 보내는 사람 이름

        return message;
    }

    // 인증코드 만들기
    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 8; i++) { // 인증코드 8자리
            int index = rnd.nextInt(3); // 0~2 까지 랜덤, rnd 값에 따라서 아래 switch 문이 실행됨

            switch (index) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
                    // a~z (ex. 1+97=98 => (char)98 = 'b')
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
                    // A~Z
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    // 0~9
                    break;
            }
        }
        return key.toString();
    }

    /*
        메일 발송
        sendSimpleMessage의 매개변수로 들어온 to는 인증번호를 받을 메일주소
        MimeMessage 객체 안에 내가 전송할 메일의 내용을 담아준다.
        bean으로 등록해둔 javaMailSender 객체를 사용하여 이메일 send
     */
    public String sendSimpleMessage(String to)throws Exception {

        ePw = createKey();

        MimeMessage message = createMessage(to);
        try{
            javaMailSender.send(message); // 메일 발송
        }catch(MailException es){
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
        return ePw; // 메일로 보냈던 인증 코드를 서버로 리턴
    }

    public String getEPw() {
        return ePw;
    }
}