package org.arip;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Arip Hidayat on 5/30/2017.
 */
@Controller
public class HomeController {

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    VelocityEngine velocityEngine;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(SimpleMailMessage simpleMailMessage) {
        return "home";
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public String sendEmail(@ModelAttribute("simpleMailMessage") SimpleMailMessage simpleMailMessage) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setTo(simpleMailMessage.getTo());
            mimeMessageHelper.setSubject(simpleMailMessage.getSubject());

            Map<String, Object> model = new HashMap<>();
            model.put("name", "Arip Hidayat");
            model.put("body", "I'm created by Velocity Engine!");

            mimeMessageHelper.setText(getContentFromTemplate(model), true);

            mailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return "redirect:/";
    }

    private String getContentFromTemplate(Map<String, Object> model) {
        StringBuffer content = new StringBuffer();
        content.append(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "/template/email-template.vm", "UTF-8", model));

        return content.toString();
    }
}
