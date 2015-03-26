package guestbook;

import java.io.IOException;
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
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class qq5 extends HttpServlet  {

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String sh = req.getScheme() + "://" + req.getServerName() + ":"
				+ req.getServerPort() + req.getContextPath();
		stat.sh=sh;

		ServletOutputStream out = resp.getOutputStream();
		resp.setContentType("text/html; charset=UTF8");		
		
		String sowl =  "http://ru.feofan.com/qq/test5.owl";		
		String sq = "смертен";
		//sowl="http://127.0.0.1:8888/qqr";
		
		sowl =  req.getParameter("p1");	
		sq = req.getParameter("p2").trim();
		
		stat.sowl=stat.get_owl81(stat.sr);	
		
		
		String s="";
		int i2 =sq.indexOf("есть");
		if(i2==0)
			s=get_q3(sowl,sq,req);
		else
		{
		s=get_q1(sowl,sq,req);
		
		if(s.trim().length()==0)
			s=get_q2(sowl,sq,req);
		
		if(s.trim().length()==0)
		{
			s=get_q5(sowl,sq,req);
			s=s.replace(sq, "");
		}
		
		if(s.trim().length()==0)
		{
			s= get_q4(sowl,sq,req);
			s=s.replace(sq, "");
		}

		}
		s=s.trim();
		
		String s2=s.trim().replace(" ", ", ");
		
		if(s.length()==0)
		{
			s=get_q3(sowl,sq,req);
			
			s2="Не знаю про "+sq+". Тут есть: "+s.trim().replace(" ", ", ");
		}		
		
		byte[] b = s2.getBytes("UTF8");
		out.write(b);
	}	
	
	public String get_q1(String sowl, String  sq, HttpServletRequest req) {
		
		String  s = stat.get_prefix(stat.sh)
				+"SELECT ?кто  WHERE {qq:"+sq+" rdf:type ?кто}";
		

	
		OntModel model = ModelFactory
				.createOntologyModel(PelletReasonerFactory.THE_SPEC);

		String sh = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath();
		
		sh=sh+"/qq_s";
		
		
		//if(stat.st.length()>0)		
			model.read(sh);
		//else
		//	model.read(sowl);
		
		Query q = QueryFactory.create(s);
		ResultSet r = SparqlDLExecutionFactory.create(q, model).execSelect();
		s="";
		while(r.hasNext())
		s=s+r.next().toString();
		
		String s2="";
				String[] ss = s.split("#");
				
				int i = ss.length;
				int k = 1;
				while(k <i)
				{
					s=ss[k++];
					int m = s.indexOf(">");
					s=s.substring(0,m);
					s2=s2+" "+s;
					
				}
				s2=s2.replace("NamedIndividual", "").replace("Class", "").trim().replace("  ", " ");		

		return s2;
	}
	
	public String get_q2(String sowl, String  sq, HttpServletRequest req) {
		
		String s = stat.get_prefix(stat.sh)
				+"SELECT ?кто  WHERE {?кто a qq:"+sq+"}";

		OntModel model = ModelFactory
				.createOntologyModel(PelletReasonerFactory.THE_SPEC);

		String sh = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath();
		sh=sh+"/qq_s";
		
		//if(stat.st.length()>0)		
			model.read(sh);
		//else
		//	model.read(sowl);
		
		Query q = QueryFactory.create(s);
		ResultSet r = SparqlDLExecutionFactory.create(q, model).execSelect();
		s="";
		while(r.hasNext())
		s=s+r.next().toString();
		
		String s2="";
				String[] ss = s.split("#");
				
				int i = ss.length;
				int k = 1;
				while(k <i)
				{
					s=ss[k++];
					int m = s.indexOf(">");
					s=s.substring(0,m);
					s2=s2+" "+s;
					
				}
		
		s2=s2.replace("NamedIndividual", "").replace("Class", "").trim().replace("  ", " ");	

		
		return s2;
	}

	
public String get_q4(String sowl, String  sq, HttpServletRequest req) {
		
		String s = "" +
				stat.get_prefix(stat.sh)
				//+"SELECT ?кто  WHERE {?кто a :"+sq+"}";
				+"SELECT ?кто  WHERE {qq:"+sq+" rdfs:subClassOf ?кто}";
		

	
		OntModel model = ModelFactory
				.createOntologyModel(PelletReasonerFactory.THE_SPEC);

		String sh = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath();
		sh=sh+"/qq_s";
		
		//if(stat.st.length()>0)		
			model.read(sh);
		//else
		//	model.read(sowl);
		
		Query q = QueryFactory.create(s);
		ResultSet r = SparqlDLExecutionFactory.create(q, model).execSelect();
		s="";
		while(r.hasNext())
		s=s+r.next().toString();
		
		String s2="";
				String[] ss = s.split("#");
				
				int i = ss.length;
				int k = 1;
				while(k <i)
				{
					s=ss[k++];
					int m = s.indexOf(">");
					s=s.substring(0,m);
					s2=s2+" "+s;
					
				}
		
		s2=s2.replace("NamedIndividual", "").replace("Class", "").trim().replace("  ", " ");	

		
		return s2;
	}

public String get_q5(String sowl, String  sq, HttpServletRequest req) {
	
	String s = stat.get_prefix(stat.sh)
			+"SELECT ?кто  WHERE {?кто rdfs:subClassOf qq:"+sq+"}";
	


	OntModel model = ModelFactory
			.createOntologyModel(PelletReasonerFactory.THE_SPEC);

	String sh = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath();
		
	sh=sh+"/qq_s";
	
	//if(stat.st.length()>0)		
		model.read(sh);
	//else
	//	model.read(sowl);
	
	Query q = QueryFactory.create(s);
	ResultSet r = SparqlDLExecutionFactory.create(q, model).execSelect();
	s="";
	while(r.hasNext())
	s=s+r.next().toString();
	
	String s2="";
			String[] ss = s.split("#");
			
			int i = ss.length;
			int k = 1;
			while(k <i)
			{
				s=ss[k++];
				int m = s.indexOf(">");
				s=s.substring(0,m);
				s2=s2+" "+s;
				
			}
	
	s2=s2.replace("NamedIndividual", "").replace("Class", "").trim().replace("  ", " ");	

	
	return s2;
}
	
public String get_q3(String sowl, String  sq, HttpServletRequest req) {
		
	String s = stat.get_prefix(stat.sh)
+"SELECT ?кто ?какой WHERE {?кто a ?какой}";
		

	
		OntModel model = ModelFactory
				.createOntologyModel(PelletReasonerFactory.THE_SPEC);

		String sh = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath();
		sh=sh+"/qq_s";
		
		//if(stat.st.length()>0)		
			model.read(sh);
		//else
		//	model.read(sowl);
		
		Query q = QueryFactory.create(s);
		ResultSet r = SparqlDLExecutionFactory.create(q, model).execSelect();
		s="";
		
		while(r.hasNext())
		s=s+r.next().toString();
		
		String s2="";
				String[] ss = s.split("#");
				
				int i = ss.length;
				int k = 1;
				while(k <i)
				{
					s=ss[k++];
					int m = s.indexOf(">");
					s=s.substring(0,m);
					if(s2.indexOf(s)<0)
					s2=s2+" "+s;
					
				}
		
				s2=s2.replace("NamedIndividual", "").replace("Class", "").trim().replace("  ", " ");	
				
				if (s2.length()==0)
					s2="никого";
				
				return s2;
	}


public String get_q6(String sowl, String  sq, HttpServletRequest req) {
	
	String s = stat.get_prefix(stat.sh)
			+ "SELECT ?Who  WHERE {?Who qq:p qq:4}";

	
	
	OntModel model = ModelFactory
			.createOntologyModel(PelletReasonerFactory.THE_SPEC);
	//model.read("file:fish.owl");
	model.read("file:pair.owl");

	Query q = QueryFactory.create(s);
	ResultSet r = SparqlDLExecutionFactory.create(q, model).execSelect();
	s = r.nextBinding().toString();
	
	return s;
}


	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		doPost(req, resp);
	}
	private static final long serialVersionUID = 1L;
}