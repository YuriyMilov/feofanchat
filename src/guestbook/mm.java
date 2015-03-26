package guestbook;

import java.io.IOException;
import java.util.Properties;
//import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.BasicConfigurator;
import org.jsoup.Jsoup;


public class mm extends HttpServlet {
	public static String slog = "";
	public static String slog1 = "";

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		try {
			BasicConfigurator.configure();
			String sh = req.getScheme() + "://" + req.getServerName() + ":"
					+ req.getServerPort() + req.getContextPath();
			Properties props = new Properties();
			Session session = Session.getDefaultInstance(props, null);
			MimeMessage ms1 = new MimeMessage(session, req.getInputStream());
			Object msgContent = ms1.getContent();

			String s = "";
			if (msgContent instanceof Multipart) {
				Multipart multipart = (Multipart) msgContent;
					for (int j = 0; j < multipart.getCount(); j++) {
					BodyPart bodyPart = multipart.getBodyPart(j);
					String disposition = bodyPart.getDisposition();
					if (disposition != null
							&& (disposition.equalsIgnoreCase("ATTACHMENT"))) {
						//DataHandler handler = bodyPart.getDataHandler();
					} else {
						s = bodyPart.getContent().toString(); 
					}
				}
			} else
				s = ms1.getContent().toString();			
			int i = s.indexOf("-- ");
			if (i > 0)
				s = s.substring(0,i);
			s=s.replace("&nbsp;", " ");	
			
			System.err.println( "****** \r\n" + s);

			
			s=Jsoup.parse(s).text().replaceAll("[ ]+", " ").trim();
			String sbj = ms1.getSubject();
			String srp = "";
			i=ms1.getReplyTo().length;
			while(i>0)
				srp=srp+ms1.getReplyTo()[--i].toString();
			srp=MimeUtility.decodeText(srp);
			sbj=MimeUtility.decodeText(sbj);
			String sotvet=stq.mm_get_otvet(sh,sbj,s,srp);
			stat.sr="";
			if(!srp.contains("kuka@feofan.com"))
			{	
				stq.mail_admins("Re: "+sbj,sotvet);				
				System.err.println( "-- KTO --> "+ srp +" "+
				 ms1.getSender().toString() + " -- SUBJ --> "+ sbj);
			}
			else
				System.err.println( " === ot Feofana ===>> Feofanu ne otvechayu ne ");
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		doGet(req, resp);
	}


	public void send_mail(Multipart mp, String sadr, String subject, String sbody, String stxt)
			throws Exception {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("kuka@gmail.com",
				"Kuka"));
		msg.setSubject(subject);
		msg.setText("UFOS Daily Activity Report attached");
		//Multipart mp = new MimeMultipart();
		MimeBodyPart textPart = new MimeBodyPart();
		textPart.setContent(sbody, "text/html");
		mp.addBodyPart(textPart);
		msg.setContent(mp);
		Transport.send(msg);
	}
	
	private static final long serialVersionUID = 1L;

}