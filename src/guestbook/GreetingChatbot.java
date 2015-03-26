package guestbook;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.channels.Channels;

import java.util.Iterator;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import guestbook.PMF;
import guestbook.Greeting;

import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.files.AppEngineFile;
import com.google.appengine.api.files.FileService;
import com.google.appengine.api.files.FileServiceFactory;
import com.google.appengine.api.files.FileWriteChannel;
import com.google.appengine.api.xmpp.JID;
import com.google.appengine.api.xmpp.Message;
import com.google.appengine.api.xmpp.MessageBuilder;
import com.google.appengine.api.xmpp.MessageType;
import com.google.appengine.api.xmpp.SendResponse;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException; 
import javax.mail.internet.InternetAddress; 
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage; 
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;


@SuppressWarnings("serial")
public class GreetingChatbot extends HttpServlet {

    @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
      IOException {
    	doPost(req,resp);
	return;
}
  
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
    IOException {
//		String sh = req.getScheme() + "://" + req.getServerName() + ":"
//				+ req.getServerPort() + req.getContextPath();
//		
  XMPPService xmpp = XMPPServiceFactory.getXMPPService();
  Message message = xmpp.parseMessage(req);
//   JID id = message.getFromJid();
   
 String s = message.getBody();
// s = stat.get_post(sh + "/qq", "p2=" + URLEncoder.encode(s, "UTF-8")).replaceAll("\\<.*?>","");

// if(s.indexOf("Феофан:")>-1)
	//   s=s.substring(s.lastIndexOf("Феофан:")+7);
 //if(s.indexOf("начнём")>-1)
	 //  s=s.substring(0,s.indexOf("начнём"));
 
	//s=s.trim();
	//sm(id,s);
  
 
  sendEmaila("Feofan", s);
  
	return;
}

  
void sm(JID fromJid, String s){
    // Send response..
    JID tojid = new JID(fromJid.getId());

    Message msg = new MessageBuilder().withRecipientJids(tojid).withBody(s).build();
    
    boolean messageSent = false;
    XMPPService xmpp = XMPPServiceFactory.getXMPPService();
    if (xmpp.getPresence(tojid).isAvailable()) {
      SendResponse status = xmpp.sendMessage(msg);
      messageSent = (status.getStatusMap().get(tojid) == SendResponse.Status.SUCCESS);
    }

}  
  
  
  
public void sendEmail(String recipient, String subject, String text) {
    Properties props = new Properties();
    Session session = Session.getDefaultInstance(props, null);
    try {
      MimeMessage msg = new MimeMessage(session);
      msg.setFrom(new InternetAddress("YOUREMAILGOESHERE"));
      msg.addRecipient(MimeMessage.RecipientType.TO,
                             new InternetAddress(recipient));
      msg.setSubject(subject);
      msg.setText(text);
      Transport.send(msg);
    } catch (AddressException e) {
         // ...
    } catch (MessagingException e) {
         // ...
    }
    
    
  }
public void sendEmaila(String subject, String text) {
    Properties props = new Properties();
    Session session = Session.getDefaultInstance(props, null);
    try {
      MimeMessage msg = new MimeMessage(session);
      msg.setFrom(new InternetAddress("kuka@feofan.com"));
      msg.addRecipient(MimeMessage.RecipientType.TO,
    		  new InternetAddress("admins"));
      msg.setSubject(subject);
      msg.setText(text);
      Transport.send(msg);
    } catch (AddressException e) {
         // ...
    } catch (MessagingException e) {
         // ...
    }
    
  
  }

  public String getGreetingList() {

    PersistenceManager pm = PMF.get().getPersistenceManager();

    String query = "";
    query = "select from " + Greeting.class.getName();
    List<Greeting> greetings = (List<Greeting>) pm.newQuery(query).execute();

    String greetinglist = "Greetings list:";

    for (Greeting g : greetings) {
      greetinglist += "\n" + g.getAuthor() + " posted:\n";
      greetinglist += g.getContent()
          + "\n";
    }
    pm.close();
    return greetinglist;
  }
  

}  