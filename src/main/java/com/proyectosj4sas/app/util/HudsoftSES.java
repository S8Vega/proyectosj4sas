package com.proyectosj4sas.app.util;

import org.springframework.stereotype.Service;

@Service
public class HudsoftSES {

	// This address must be verified with Amazon SES.
	final String FROM = "jefersonurielhc@ufps.edu.co";
	final String PASSWORD = "xrowwuaebyvkwmig";

	// The subject line for the email.
	final String SUBJECT = "One last step to complete your registration with PhotoApp";

	final String PASSWORD_RESET_SUBJECT = "Password reset request";

	// The HTML body for the email.
	final String HTMLBODY = "<h1>Please verify your email address</h1>"
			+ "<p>Thank you for registering with our mobile app. To complete registration process and be able to log in,"
			+ " click on the following link: "
			+ "<a href='http://ec2-35-173-238-100.compute-1.amazonaws.com:8080/verification-service/email-verification.html?token=$tokenValue'>"
			+ "Final step to complete your registration" + "</a><br/><br/>"
			+ "Thank you! And we are waiting for you inside!";

	// The email body for recipients with non-HTML email clients.
	final String TEXTBODY = "Please verify your email address. "
			+ "Thank you for registering with our mobile app. To complete registration process and be able to log in,"
			+ " open then the following URL in your browser window: "
			+ " http://ec2-35-173-238-100.compute-1.amazonaws.com:8080/verification-service/email-verification.html?token=$tokenValue"
			+ " Thank you! And we are waiting for you inside!";

	final String PASSWORD_RESET_HTMLBODY = "<h1>A request to reset your password</h1>" + "<p>Hi, $firstName!</p> "
			+ "<p>Someone has requested to reset your password with our project. If it were not you, please ignore it."
			+ " otherwise please click on the link below to set a new password: "
			+ "<a href='http://localhost:8080/verification-service/password-reset.html?token=$tokenValue'>"
			+ " Click this link to Reset Password" + "</a><br/><br/>" + "Thank you!";

	// The email body for recipients with non-HTML email clients.
	final String PASSWORD_RESET_TEXTBODY = "A request to reset your password " + "Hi, $firstName! "
			+ "Someone has requested to reset your password with our project. If it were not you, please ignore it."
			+ " otherwise please open the link below in your browser window to set a new password:"
			+ " http://localhost:8080/verification-service/password-reset.html?token=$tokenValue" + " Thank you!";

	public boolean sendPasswordResetRequest(String email, String firstName, String token) {
		boolean returnValue = false;



		/*
		 * SendEmailResult result = client.sendEmail(request); if(result != null &&
		 * (result.getMessageId()!=null && !result.getMessageId().isEmpty())) {
		 * returnValue = true; }
		 */

		return returnValue;
	}
}
