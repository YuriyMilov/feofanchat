package guestbook;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mindswap.pellet.jena.PelletReasonerFactory;
import com.clarkparsia.pellet.sparqldl.jena.SparqlDLExecutionFactory;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class owl extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String sh = req.getScheme() + "://" + req.getServerName() + ":"
				+ req.getServerPort() + req.getContextPath();

		ServletOutputStream out = resp.getOutputStream();
		resp.setContentType("text/xml; charset=UTF8");
		
		resp.setCharacterEncoding("UTF8");
		String s = "";
		

		if(stat.sowl!=null)
			if(stat.sowl.length()>0)
					s=stat.sowl;
			else
				{
				//if(stat.sr.trim().equals(""))
				
				stat.get_owl83(stat.sr,sh);
				s=stat.sowl;
				}
		
		
		byte[] b = s.getBytes("UTF8");
		resp.setHeader("Content-Disposition", "attachment; filename=mir.owl");
		out.write(b);
	}

	private static final long serialVersionUID = 1L;
}