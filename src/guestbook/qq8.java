package guestbook;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

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

public class qq8 extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String s = "";
		String sh = req.getScheme() + "://" + req.getServerName() + ":"
				+ req.getServerPort() + req.getContextPath();
		ServletOutputStream out = resp.getOutputStream();
		resp.setContentType("text/html; charset=UTF8");

		try {
			
			///////////////////////////		
			
			stat.sowl = stat.para("");
			s = stat.get_prefix(stat.sh)
					+ "SELECT ?кто  WHERE {?кто qq:пара qq:7}";
			
			///////////////////////////			
			
			//stat.sqq7 = stat.scrt("");
			// s = stat.spref + "SELECT ?кто  WHERE {qq:Сократ a ?кто}";

			///////////////////////////			
				
			OntModel model = ModelFactory
					.createOntologyModel(PelletReasonerFactory.THE_SPEC);
			//model.read(sh + "/qqq");
			
			StringReader rdr = new StringReader(stat.para(""));
			
			Query q = QueryFactory.create(s);
			ResultSet rs = SparqlDLExecutionFactory.create(q, model)
					.execSelect();

			s = "";
			String sa = "";
			while (rs.hasNext()) {
				sa = rs.nextBinding().toString();
				if (sa.indexOf(stat.siri) > -1) {
					sa = sa.replace(stat.siri, "").replace("( ?кто = <", "")
							.replace("> ) -> [Root]", "");
					s = s + " " + sa;
				}
			}
			s=s.trim();
			if (s.length() == 0)
				s = "Нет ответа...";
			else
				s=s.replace(" ", ", ");

		} catch (Exception e) {
			s = e.toString();
		}
		byte[] b = s.getBytes("UTF8");
		out.write(b);
	}

	private static final long serialVersionUID = 1L;
}