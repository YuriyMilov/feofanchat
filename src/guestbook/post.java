package guestbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
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

public class post extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		String sh = req.getScheme() + "://" + req.getServerName() + ":"
				+ req.getServerPort() + req.getContextPath();
		stat.sh=sh;

		String s = "Феофан, загрузи новый мир \"Сократ\".спаркля(?кто это смертен)";//stat.posti(sh+"/w2f","post","привет - это я, post servlet ");
		//s= stq.f(sh, s);
		
		///////////
		
		ServletOutputStream out = resp.getOutputStream();
		resp.setContentType("text/html; charset=UTF8");		
		resp.setCharacterEncoding("UTF8");	
		byte[] b = s.getBytes("UTF8");
		out.write(b);
	}
	

		public static String rfu_utf(String s) {
			try {
				URL url = new URL(s);

				URLConnection conn = url.openConnection();
				BufferedReader br = new BufferedReader(new InputStreamReader(
						conn.getInputStream(), "utf8"));
				s = "";
				String thisLine = "";
				while ((thisLine = br.readLine()) != null) { // while loop begins
																// here
					s = s + thisLine + "\r\n";
				}
				br.close();
				return s.toString();

			} catch (Exception e) {
				return e.toString();
			}
		}


	private static final long serialVersionUID = 1L;
}