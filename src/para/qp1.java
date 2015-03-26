package para;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;


public class qp1 extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		ServletOutputStream out = resp.getOutputStream();
		resp.setContentType("text/xml; charset=UTF8");		
		resp.setHeader("Content-Disposition", "attachment; filename="
				+ "test.owl");
		resp.setCharacterEncoding("UTF8");
		
		String s = "";
		
	
	    
		try {
			para1 p=new para1();
			s=p.get_owl();
		} catch (OWLOntologyCreationException e) {
			
			s=e.toString();
		}
		

		byte[] b = s.getBytes("UTF8");
		out.write(b);
	}

	private static final long serialVersionUID = 1L;
}