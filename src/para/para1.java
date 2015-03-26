package para;

	import java.io.ByteArrayOutputStream;
	import java.io.File;
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

	public class para1 {

		static OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		static IRI ontologyIRI;
		static OWLOntology ontology;
		static OWLDataFactory factory;
		
		public para1() throws OWLOntologyCreationException {
			ontologyIRI = IRI.create("http://owl.feofan.com/1");
			ontology = manager.createOntology(ontologyIRI);
			factory = manager.getOWLDataFactory();
			OWLClass нечто = что("нечто");
			OWLIndividual Сократ = кто("Сократ");
			OWLIndividual Платон = кто("Платон");
			OWLObjectProperty учит = связь("учит");		
			OWLClass ученик = что("ученик");
			OWLClass учитель = что("учитель");		
			курлы_1(учитель, Сократ);
			курлы_1(ученик,  Платон);
			//курлы_2(нечто, Сократ, Платон);
			учитель_учит_N_учеников(учитель, учит, 1, ученик);
			
		}

		
		// ///////////////////////////////////////////////
		//
		//               учитель_учит_N_учеников
		//
		// ///////////////////////////////////////////////

		static public void учитель_учит_N_учеников(OWLClassExpression учитель,
				OWLObjectPropertyExpression учит, int N, OWLClassExpression ученик) {

		
			OWLObjectExactCardinality учит_N_учеников = factory
					.getOWLObjectExactCardinality(N, учит, ученик);
			
			//OWLObjectIntersectionOf ии = factory.getOWLObjectIntersectionOf(учит_N);

			OWLClassAxiom аксиома = factory.getOWLEquivalentClassesAxiom(учитель,учит_N_учеников);
				
			manager.addAxiom(ontology, аксиома);

		}


		// ///////////////////////////////////////////////
		//
		//               курлы 1 
		//
		// ///////////////////////////////////////////////

		static public void курлы_1(OWLClassExpression нечто, OWLIndividual Сократ) {
			OWLObjectOneOf сократы = factory.getOWLObjectOneOf(Сократ);
			OWLClassAxiom аксиома = factory.getOWLEquivalentClassesAxiom(нечто,сократы);
			manager.addAxiom(ontology, аксиома);

		}
		

		// ///////////////////////////////////////////////
		//
		//               курлы 2 
		//
		// ///////////////////////////////////////////////

		static public void курлы_2(OWLClassExpression нечто, OWLIndividual Сократ, OWLIndividual Платон) {
			OWLObjectOneOf сократы = factory.getOWLObjectOneOf(Сократ,Платон);
			OWLClassAxiom аксиома = factory.getOWLEquivalentClassesAxiom(нечто,сократы);
			manager.addAxiom(ontology, аксиома);

		}
		
		
		
		
		// ///////////////////////////////////////////////
		//
		//               КТО  
		//
		// ///////////////////////////////////////////////
		
		static public OWLIndividual кто(String individual) {
			String IRIString = ontologyIRI + "#" + individual;
			OWLIndividual ind = factory
					.getOWLNamedIndividual(IRI.create(IRIString));
			return ind;
		}

		// ///////////////////////////////////////////////
		//
		//              СВЯЗЬ  
		//
		// ///////////////////////////////////////////////
		

		
		static public OWLObjectProperty связь(String a_property) {
			String IRIString = ontologyIRI + "#" + a_property;
			OWLObjectProperty myproperty = factory.getOWLObjectProperty(IRI
					.create(IRIString));
			return myproperty;
		}
		

		// ///////////////////////////////////////////////
		//
		//               ЧТО  
		//
		// ///////////////////////////////////////////////
		

		static public OWLClass что(String classname) {
			String IRIString = ontologyIRI + "#" + classname;
			OWLClass myclass = factory.getOWLClass(IRI.create(IRIString));
			return myclass;
		}
		
		// ///////////////////////////////////////////////
		//
		//               КТО - ЧТО  
		//
		// ///////////////////////////////////////////////

		
		static public void кто_что(OWLIndividual кто,
				OWLClass что) {
			OWLClassAssertionAxiom в_классе_что_есть_идивид_кто = factory.getOWLClassAssertionAxiom(
					что, кто);
			manager.addAxiom(ontology, в_классе_что_есть_идивид_кто);
			return;
		}
		
		// ///////////////////////////////////////////////
		//
		//               любит_в_точности_N_сократов
		//
		// ///////////////////////////////////////////////


		static public void любит_в_точности_N_сократов(OWLClassExpression человек,
				OWLObjectPropertyExpression любит, int N, OWLIndividual Сократ) {

			OWLObjectOneOf сократ = factory.getOWLObjectOneOf(Сократ);

			OWLObjectExactCardinality человек_любит_в_точности_N = factory
					.getOWLObjectExactCardinality(N, любит, человек);
			
			OWLObjectIntersectionOf ии = factory.getOWLObjectIntersectionOf(сократ,человек,
					человек_любит_в_точности_N);

			OWLClassAxiom аксиома = factory.getOWLEquivalentClassesAxiom(человек,ии);
			manager.addAxiom(ontology, аксиома);

		}
		

		// ///////////////////////////////////////////////
		// ///////////////////////////////////////////////
		// ///////////////////////////////////////////////
		// ///////////////////////////////////////////////
		// ///////////////////////////////////////////////
		// ///////////////////////////////////////////////
		// ///////////////////////////////////////////////
		// ///////////////////////////////////////////////
		// ///////////////////////////////////////////////

		public static OWLDataFactory getDataFactory() {
			return factory;
		}

		static public void setDataFactory(OWLDataFactory new_owl_factory) {
			factory = new_owl_factory;
			return;
		}

		static public OWLOntology getOwlOntology() {
			return ontology;
		}

		static public void setOwlOntology(OWLOntology new_ontology) {
			ontology = new_ontology;
			return;
		}

		static public IRI getOntologyIri() {
			return ontologyIRI;
		}

		static public void setOntologyIri(IRI an_IRI) {
			ontologyIRI = an_IRI;
			return;
		}

		static public OWLOntologyManager getManager() {
			return manager;
		}

		static public void setManager(OWLOntologyManager a_manager) {
			manager = a_manager;
			return;
		}

		static public void setOnologyIriFromString(String uri) {
			ontologyIRI = IRI.create(uri);
			return;
		}

		

		static public OWLClass getOwlClass(String classname) {
			String IRIString = ontologyIRI + "#" + classname;
			OWLClass myclass = factory.getOWLClass(IRI.create(IRIString));
			return myclass;
		}

		static public OWLIndividual getIndividual(String individual) {
			String IRIString = ontologyIRI + "#" + individual;
			OWLIndividual ind = factory
					.getOWLNamedIndividual(IRI.create(IRIString));
			return ind;
		}

		static public OWLObjectProperty getProperty(String a_property) {
			String IRIString = ontologyIRI + "#" + a_property;
			OWLObjectProperty myproperty = factory.getOWLObjectProperty(IRI
					.create(IRIString));
			return myproperty;
		}

		static public OWLDataProperty getDataProperty(String a_property) {
			String IRIString = ontologyIRI + "#" + a_property;
			OWLDataProperty myproperty = factory.getOWLDataProperty(IRI
					.create(IRIString));
			return myproperty;
		}

		static public void assertFacts(String[] properties, String[] individuals1,
				String[] individuals2) {
			if ((properties == null) || (individuals1 == null)
					|| (individuals2 == null)) {
				return;
			}
			if ((properties.length > individuals1.length)
					|| (properties.length < individuals1.length))
				return;
			if ((properties.length > individuals2.length)
					|| (properties.length < individuals2.length))
				return;
			if (properties.length == 0)
				return;
			for (int i = 0; i < properties.length; i++)
				assertFact(properties[i], individuals1[i], individuals2[i]);
			return;
		}

		static public void assertFact(String property, String ind1, String ind2) {
			OWLObjectProperty P = getProperty(property);
			OWLIndividual i = getIndividual(ind1);
			OWLIndividual j = getIndividual(ind2);
			assertFact(P, i, j);
			return;
		}

		static public void assertFact(OWLObjectProperty prop, OWLIndividual ind1,
				OWLIndividual ind2) {
			OWLObjectPropertyAssertionAxiom myaxiom = factory
					.getOWLObjectPropertyAssertionAxiom(prop, ind1, ind2);
			AddAxiom an_axiom_to_add = new AddAxiom(ontology, myaxiom);
			manager.applyChange(an_axiom_to_add);
			return;
		}

		static public void assertFact(OWLDataProperty prop, OWLIndividual ind1,
				Integer myint) {
			OWLDataPropertyAssertionAxiom myaxiom = factory
					.getOWLDataPropertyAssertionAxiom(prop, ind1, myint);
			AddAxiom an_axiom_to_add = new AddAxiom(ontology, myaxiom);
			manager.applyChange(an_axiom_to_add);
			return;
		}

		static public void assertDomain(OWLObjectProperty property, OWLClass domain) {
			OWLObjectPropertyDomainAxiom myaxiom = factory
					.getOWLObjectPropertyDomainAxiom(property, domain);
			AddAxiom an_axiom_to_add = new AddAxiom(ontology, myaxiom);
			manager.applyChange(an_axiom_to_add);
			return;
		}

		static public void assertRange(OWLObjectProperty property, OWLClass range) {
			OWLObjectPropertyRangeAxiom myaxiom = factory
					.getOWLObjectPropertyRangeAxiom(property, range);
			AddAxiom an_axiom_to_add = new AddAxiom(ontology, myaxiom);
			manager.applyChange(an_axiom_to_add);
			return;
		}

		static public void assertDomainAndRange(OWLObjectProperty property,
				OWLClass domain, OWLClass range) {
			assertDomain(property, domain);
			assertRange(property, range);
			return;
		}

		static public void assertDataDomain(OWLDataProperty property,
				OWLClass domain) {
			OWLDataPropertyDomainAxiom myaxiom = factory
					.getOWLDataPropertyDomainAxiom(property, domain);
			AddAxiom an_axiom_to_add = new AddAxiom(ontology, myaxiom);
			manager.applyChange(an_axiom_to_add);
			return;
		}

		static public void assertRangeAsInteger(OWLDataProperty property) {
			OWLDatatype integerDatatype = factory.getIntegerOWLDatatype();
			OWLDataPropertyRangeAxiom myaxiom = factory
					.getOWLDataPropertyRangeAxiom(property, integerDatatype);
			AddAxiom an_axiom_to_add = new AddAxiom(ontology, myaxiom);
			manager.applyChange(an_axiom_to_add);
			return;
		}

		static public void assertRangeAsDouble(OWLDataProperty property) {
			OWLDatatype doubleDatatype = factory.getDoubleOWLDatatype();
			OWLDataPropertyRangeAxiom myaxiom = factory
					.getOWLDataPropertyRangeAxiom(property, doubleDatatype);
			AddAxiom an_axiom_to_add = new AddAxiom(ontology, myaxiom);
			manager.applyChange(an_axiom_to_add);
			return;
		}

		static public void assertRangeAsString(OWLDataProperty property) {
			OWLDatatype stringDatatype = factory.getRDFPlainLiteral();
			OWLDataPropertyRangeAxiom myaxiom = factory
					.getOWLDataPropertyRangeAxiom(property, stringDatatype);
			AddAxiom an_axiom_to_add = new AddAxiom(ontology, myaxiom);
			manager.applyChange(an_axiom_to_add);
			return;
		}

		static public void hasClass(OWLIndividual individual, OWLClass myClass) {
			OWLClassAssertionAxiom myaxiom = factory.getOWLClassAssertionAxiom(
					myClass, individual);
			AddAxiom an_axiom_to_add = new AddAxiom(ontology, myaxiom);
			manager.applyChange(an_axiom_to_add);
			return;
		}

		static public void isAsymmetric(OWLObjectProperty property) {
			OWLAsymmetricObjectPropertyAxiom myaxiom = factory
					.getOWLAsymmetricObjectPropertyAxiom(property);
			AddAxiom an_axiom_to_add = new AddAxiom(ontology, myaxiom);
			manager.applyChange(an_axiom_to_add);
			return;
		}

		static public void isFunctional(OWLObjectProperty property) {
			OWLFunctionalObjectPropertyAxiom myaxiom = factory
					.getOWLFunctionalObjectPropertyAxiom(property);
			AddAxiom an_axiom_to_add = new AddAxiom(ontology, myaxiom);
			manager.applyChange(an_axiom_to_add);
			return;
		}

		static public void isFunctional(OWLDataProperty property) {
			OWLFunctionalDataPropertyAxiom myaxiom = factory
					.getOWLFunctionalDataPropertyAxiom(property);
			AddAxiom an_axiom_to_add = new AddAxiom(ontology, myaxiom);
			manager.applyChange(an_axiom_to_add);
			return;
		}

		static public void subPropertyOf(OWLObjectProperty subProperty,
				OWLObjectProperty superProperty) {
			OWLSubObjectPropertyOfAxiom myaxiom = factory
					.getOWLSubObjectPropertyOfAxiom(subProperty, superProperty);
			AddAxiom an_axiom_to_add = new AddAxiom(ontology, myaxiom);
			manager.applyChange(an_axiom_to_add);
			return;
		}

		static public void inverseProperties(OWLObjectProperty property1,
				OWLObjectProperty property2) {
			OWLInverseObjectPropertiesAxiom myaxiom = factory
					.getOWLInverseObjectPropertiesAxiom(property1, property2);
			AddAxiom an_axiom_to_add = new AddAxiom(ontology, myaxiom);
			manager.applyChange(an_axiom_to_add);
			return;
		}

		static public void isInverseFunctional(OWLObjectProperty property) {
			OWLInverseFunctionalObjectPropertyAxiom myaxiom = factory
					.getOWLInverseFunctionalObjectPropertyAxiom(property);
			AddAxiom an_axiom_to_add = new AddAxiom(ontology, myaxiom);
			manager.applyChange(an_axiom_to_add);
			return;
		}

		static public void isIrreflexive(OWLObjectProperty property) {
			OWLIrreflexiveObjectPropertyAxiom myaxiom = factory
					.getOWLIrreflexiveObjectPropertyAxiom(property);
			AddAxiom an_axiom_to_add = new AddAxiom(ontology, myaxiom);
			manager.applyChange(an_axiom_to_add);
			return;
		}

		static public OWLClassExpression exactCardinality(OWLDataProperty property,
				Integer cardinality) {
			OWLDataExactCardinality myclassexp = factory
					.getOWLDataExactCardinality(cardinality, property);
			return myclassexp;
		}

		static public OWLClassExpression oneOf(OWLIndividual a, OWLIndividual b) {
			OWLObjectOneOf myclassexp = factory.getOWLObjectOneOf(a, b);
			return myclassexp;
		}

		static public OWLClassExpression oneOf(OWLIndividual a, OWLIndividual b,
				OWLIndividual c) {
			OWLObjectOneOf myclassexp = factory.getOWLObjectOneOf(a, b, c);
			return myclassexp;
		}

		static public void differentIndividuals(OWLIndividual a, OWLIndividual b) {
			OWLDifferentIndividualsAxiom myaxiom = factory
					.getOWLDifferentIndividualsAxiom(a, b);
			AddAxiom an_axiom_to_add = new AddAxiom(ontology, myaxiom);
			manager.applyChange(an_axiom_to_add);
			return;
		}

		static public void isSubClassOf(OWLClass subclass, OWLClass superclass) {
			OWLSubClassOfAxiom myaxiom = factory.getOWLSubClassOfAxiom(subclass,
					superclass);
			AddAxiom an_axiom_to_add = new AddAxiom(ontology, myaxiom);
			manager.applyChange(an_axiom_to_add);
			return;
		}

		static public void differentIndividuals(OWLIndividual a, OWLIndividual b,
				OWLIndividual c) {
			differentIndividuals(a, b);
			differentIndividuals(b, c);
			differentIndividuals(a, c);
			return;
		}

		static public void differentIndividuals(OWLIndividual[] group) {
			for (int i = 0; i < group.length; i++)
				for (int j = i; j < group.length; j++)
					differentIndividuals(group[i], group[j]);
			return;
		}

		static public void differentIndividuals(
				java.util.ArrayList<OWLIndividual> group) {
			OWLIndividual[] groupArray = (OWLIndividual[]) group.toArray();
			differentIndividuals(groupArray);
			return;
		}

		static public void print() {
			try {
				manager.saveOntology(ontology, new StreamDocumentTarget(System.out));
			} catch (OWLOntologyStorageException e) {
				System.out.println("Problem saving ontology: " + e.getMessage());
				e.printStackTrace();
			}
			return;
		}

		public static void save() {
			try {
				manager.saveOntology(ontology,
						IRI.create(new File("C:/inetpub/wwwroot/1.owl")));
			} catch (OWLOntologyStorageException e) {
				System.out.println("Problem saving ontology: " + e.getMessage());
				e.printStackTrace();
			}
			return;
		}

		public String get_owl() {
			String s = "";
			try {
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				manager.saveOntology(ontology, outputStream);
				s = new String(outputStream.toByteArray(), "UTF-8");
			} catch (Exception e) {
				s = e.getMessage();
			}
			return s;
		}
	}