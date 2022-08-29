package trainingManagementSystem.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import trainingManagementSystem.model.Report;
import trainingManagementSystem.model.Comment;

public class MailService {

	    @Autowired
	    public JavaMailSender emailSender;
	    
	    public void sendReportCreatedNoticeEmail(Report report) throws MessagingException {

	        MimeMessage message = emailSender.createMimeMessage();

	        boolean multipart = true;
	        
	        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");
	        
	        String htmlMsg = "<img src='https://education.sun-asterisk.vn/assets/logo-5e0c3fe76b8e2d574a45cee16a4ae458646eb8be827b3a2e00082255a2b21e73.png'>"
	        				+String.format("<h3>%s đã gửi một báo cáo mới</h3>", report.getUser().getEmail())
	                		+"<h3>Nhấp vào link bên dưới để xem chi tiết: </h3>"
	                		+String.format("<a href='https://localhost:8080/reports/%s'> Link </a>", report.getId().toString());
	        
	        message.setContent(htmlMsg, "text/html");
	        
	        helper.setTo(report.getReviewer().getEmail());
	        
	        helper.setSubject(report.getUser().getEmail() + " vừa tạo một báo cáo mới");
	        
	    
	        this.emailSender.send(message);

	        return;
	    }
	    
	    public void sendCommentedNoticeEmail(Comment comment) throws MessagingException {

	        MimeMessage message = emailSender.createMimeMessage();

	        boolean multipart = true;
	        
	        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");
	        
	        String htmlMsg = "<img src='https://education.sun-asterisk.vn/assets/logo-5e0c3fe76b8e2d574a45cee16a4ae458646eb8be827b3a2e00082255a2b21e73.png'>"
	        				+String.format("<h3>Quản lý %s đã nhận xét báo cáo của bạn</h3>", comment.getUser().getName())
	                		+"<h3>Nhấp vào link bên dưới để xem chi tiết: </h3>"
	                		+String.format("<a href='https://localhost:8080/reports/%s'> Link </a>", comment.getReport().getId().toString());
	        
	        message.setContent(htmlMsg, "text/html");
	        
	        helper.setTo(comment.getReport().getUser().getEmail());
	        
	        helper.setSubject(String.format("Báo cáo %s vừa được nhận xét", comment.getReport().getActualTask()));
	        
	    
	        this.emailSender.send(message);

	        return;
	    }
	    
	    public void sendVerifyEmail(String verifyLink, String userEmail) throws MessagingException {

	        MimeMessage message = emailSender.createMimeMessage();

	        boolean multipart = true;
	        
	        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");
	        
	        String htmlMsg = "<img src='https://education.sun-asterisk.vn/assets/logo-5e0c3fe76b8e2d574a45cee16a4ae458646eb8be827b3a2e00082255a2b21e73.png'>"
	                		+"<h3>Nhấp vào liên kết dưới đây để hoàn tất quá trình đăng ký: </h3>"
	                		+String.format("<a href='%s'> Xác nhận tài khoản </a>", verifyLink);
	        
	        message.setContent(htmlMsg, "text/html");
	        
	        helper.setTo(userEmail);
	        
	        helper.setSubject("Xác nhận đăng ký tài khoản");
	        
	    
	        this.emailSender.send(message);

	        return;
	    }
}