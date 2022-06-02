package com.devteam.marketing.external.service;

import com.devteam.marketing.external.dto.MailDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@RequiredArgsConstructor
@Service
public class MailService {

    private final Logger logger =LoggerFactory.getLogger(this.getClass());

    private final JavaMailSender javaMailSender;

    public void send(MailDto mailDto) {
        try {
            final MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            final MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setTo(mailDto.getTo());
            mimeMessageHelper.setFrom(mailDto.getFrom());
            mimeMessageHelper.setText(mailDto.getText());
            mimeMessageHelper.setSubject(mailDto.getSubject());
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }

}
