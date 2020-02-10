package khmerhowto.Repository.Model.SendEmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SendEmailToUser {

        @Autowired
        JavaMailSender javaMailSender;

        public void sendEmail() {
        SimpleMailMessage msg = new SimpleMailMessage();
        String[] emailArray= {"humchamroeunhrd@gmail.com", "humchamroeuncs@gmail.com", "humchamroeunmmo@gmail.com"};
        msg.setTo(emailArray);
        msg.setSubject("Kunloes Update");
        msg.setText("Find a beautiful piece of art. If you fall in love with Van Gogh or Matisse or John Oliver Killens, or if you fall love with the music of Coltrane, the music of Aretha Franklin, or the music of Chopin - find some beautiful art and admire it, and realize that that was created by human beings just like you, no more human, no less. Read more at https://www.brainyquote.com/topics/beautiful-quotes");
        javaMailSender.send(msg);
        System.out.println("send email");
    }
}

