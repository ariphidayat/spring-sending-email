package org.arip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Arip Hidayat on 5/30/2017.
 */
@Controller
public class HomeController {

    @Autowired
    MailSender mailSender;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(SimpleMailMessage simpleMailMessage) {
        return "home";
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public String sendEmail(@ModelAttribute("simpleMailMessage") SimpleMailMessage simpleMailMessage) {
        mailSender.send(simpleMailMessage);

        return "redirect:/";
    }
}
