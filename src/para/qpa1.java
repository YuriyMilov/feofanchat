package para;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.StreamDocumentTarget;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAsymmetricObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLClassAxiom;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataExactCardinality;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.model.OWLDifferentIndividualsAxiom;
import org.semanticweb.owlapi.model.OWLFunctionalDataPropertyAxiom;
import org.semanticweb.owlapi.model.OWLFunctionalObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLInverseFunctionalObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLInverseObjectPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLIrreflexiveObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLObjectExactCardinality;
import org.semanticweb.owlapi.model.OWLObjectIntersectionOf;
import org.semanticweb.owlapi.model.OWLObjectOneOf;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.model.OWLObjectPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.semanticweb.owlapi.model.OWLSubObjectPropertyOfAxiom;

public class qpa1 extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String sh = req.getScheme() + "://" + req.getServerName() + ":"
				+ req.getServerPort() + req.getContextPath();
		
		ServletOutputStream out = resp.getOutputStream();

		String s = req.getQueryString();

		byte[] b = null;
		if (s == null)
			s = "7";

		try {

			if (s.equals("7")) {
				st.ontology = null;
				
				st.ontologyIRI = IRI.create(sh +"/n1.owl");
				
				//st.ontologyIRI = IRI.create("http://www.feofan.com/1.owl");
				
				st.ontology = st.manager.loadOntology(st.ontologyIRI);				
				st.factory = st.manager.getOWLDataFactory();
				
				
				st.class_equiv_to_1_ind(st.что("учитель"), st.кто("Сократ"));
				st.class_equiv_to_1_ind(st.что("ученик"), st.кто("Платон"));
				
				st.учитель_учит_N_учеников(st.что("учитель"), st.связь("учит"), 1, st.что("ученик"));
				st.что_что(st.что("что1"), st.что("что2"));
				
				
				OWLClass человек=st.что("человек");
				OWLClass дурак=st.что("дурак");
				OWLIndividual Вася=st.кто("Вася");
				st.что_что(человек, дурак);
				st.кто_что(Вася, дурак);
				
				s = "5";
			}

			if (s.equals("1")) {
				st.ontology = null;
				st.ontologyIRI = IRI.create("http://owl.feofan.com/1");
				st.ontology = st.manager.createOntology(st.ontologyIRI);
				st.factory = st.manager.getOWLDataFactory();
				st.Сократ = st.кто("Сократ");
				st.Платон = st.кто("Платон");
			}

			if (s.equals("2"))
				st.учит = st.связь("учит");

			if (s.equals("3")) {
				st.ученик = st.что("ученик");
				st.учитель = st.что("учитель");
			}
			if (s.equals("4")) {
				st.курлы_1(st.ученик, st.Платон);
				// OWLClass нечто = st.что("нечто");
				// st.курлы_2(нечто, Сократ, Платон);
				st.учитель_учит_N_учеников(st.учитель, st.учит, 1, st.ученик);
			}
			if (s.equals("5"))
				b = st.get_bowl();

		} catch (OWLOntologyCreationException e) {

			b = e.toString().getBytes("UTF8");
			s=e.toString();
		}

		// byte[] b = s.getBytes("UTF8");
		if (s.equals("15")) {
			resp.setContentType("text/xml; charset=UTF8");
			resp.setHeader("Content-Disposition", "attachment; filename="
					+ "qpa1.owl");
			resp.setCharacterEncoding("UTF8");
			out.write(b);
		} else {
			//resp.setContentType("text/html; charset=UTF8");
			//out.write(s.getBytes("UTF8"));
			
			resp.setContentType("text/xml; charset=UTF8");
			resp.setCharacterEncoding("UTF8");
			//b[0]='<';

			out.write(b);
			
			//s= new String(b, "UTF-8");
			//s=removeNonUtf8CompliantCharacters(s);
			//out.println(s);
			
		}
	}


	private static final long serialVersionUID = 1L;

}