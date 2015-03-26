package guestbook;
import java.io.ByteArrayOutputStream;
import java.io.File;
import org.coode.owlapi.manchesterowlsyntax.ManchesterOWLSyntaxOntologyFormat;
import org.coode.owlapi.turtle.TurtleOntologyFormat;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.OWLXMLOntologyFormat;
import org.semanticweb.owlapi.io.StreamDocumentTarget;
import org.semanticweb.owlapi.model.*;
import static org.semanticweb.owlapi.vocab.OWLFacet.MAX_EXCLUSIVE;
import static org.semanticweb.owlapi.vocab.OWLFacet.MIN_INCLUSIVE;

import java.util.HashSet;
import java.util.Set;


public class o2m2 {
	

 
    private OWLOntologyManager manager;
    private IRI ontologyIRI;
    private OWLOntology ontology;
    private OWLDataFactory factory;
    public OWLDataFactory getDataFactory()
    {
        return factory;
    }
    public void setDataFactory(OWLDataFactory new_owl_factory)
    {
        factory = new_owl_factory;
        return;
    }
    public OWLOntology getOwlOntology()
    {
        return ontology;
    }
    public void setOwlOntology(OWLOntology new_ontology)
    {
        this.ontology = new_ontology;
        return;
    }
    public IRI getOntologyIri()
    {
        return ontologyIRI;
    }
    public void setOntologyIri(IRI an_IRI)
    {
        ontologyIRI = an_IRI;
        return;
    }
    public OWLOntologyManager getManager()
    {
        return this.manager;
    }
    public void setManager(OWLOntologyManager a_manager)
    {
        this.manager = a_manager;
        return;
    }
    public void setOnologyIriFromString(String uri)
    {
        ontologyIRI = IRI.create(uri);
        return;
    }
    public o2m2()
    {
        manager = OWLManager.createOWLOntologyManager();
        ontologyIRI = IRI.create("http://www.ai.uga.edu/owlapi/job-satisfaction-map");
        try
        {
             ontology = manager.createOntology(ontologyIRI);
        }
        catch (OWLOntologyCreationException e) {
             System.out.println("There was a problem creating the ontology: " + e.getMessage());
             e.printStackTrace();
        }
        factory = manager.getOWLDataFactory();
            
    }
    public o2m2(String alternative_iri)
    {
        manager = OWLManager.createOWLOntologyManager();
        ontologyIRI = IRI.create(alternative_iri);
        try
        {
             ontology = manager.createOntology(ontologyIRI);
        }
        catch (OWLOntologyCreationException e) {
             System.out.println("There was a problem creating the ontology: " + e.getMessage());
             e.printStackTrace();
        }
        factory = manager.getOWLDataFactory();
            
    }
    public OWLClass getOwlClass(String classname)
    {
       String IRIString = ontologyIRI + "#" + classname;
       OWLClass myclass = factory.getOWLClass(IRI.create(IRIString));
       return myclass;
    }
    public OWLIndividual getIndividual(String individual)
    {
        String IRIString = ontologyIRI + "#" + individual;
        OWLIndividual ind = factory.getOWLNamedIndividual(IRI.create(IRIString));
        return ind;
    }
    public OWLObjectProperty getProperty(String a_property)
    {
        String IRIString = ontologyIRI + "#" + a_property;
        OWLObjectProperty myproperty = factory.getOWLObjectProperty(IRI.create(IRIString));
        return myproperty;
    }
    public OWLDataProperty getDataProperty(String a_property)
    {
        String IRIString = ontologyIRI + "#" + a_property;
        OWLDataProperty myproperty = factory.getOWLDataProperty(IRI.create(IRIString));
        return myproperty;
    }
    public void assertFacts(String[] properties, String [] individuals1, String [] individuals2)
    {
        if ((properties == null) || (individuals1 == null) || (individuals2 == null))
        {
            return;
        }
        if ((properties.length > individuals1.length) || (properties.length < individuals1.length))
            return;
        if ((properties.length > individuals2.length) || (properties.length < individuals2.length))
            return;
        if (properties.length == 0)
            return;
        for (int i = 0; i < properties.length; i++)
            assertFact(properties[i], individuals1[i], individuals2[i]);
        return;
    }
    public void assertFact(String property, String ind1, String ind2)
    {
        OWLObjectProperty P = this.getProperty(property);
        OWLIndividual i = this.getIndividual(ind1);
        OWLIndividual j = this.getIndividual(ind2);
        assertFact(P, i, j);
        return;
    }
    public void assertFact(OWLObjectProperty prop, OWLIndividual ind1, OWLIndividual ind2)
    {
        OWLObjectPropertyAssertionAxiom myaxiom = factory.getOWLObjectPropertyAssertionAxiom(prop, ind1, ind2);
        AddAxiom an_axiom_to_add = new AddAxiom(ontology, myaxiom);
        manager.applyChange(an_axiom_to_add);
        return;
    }
    public void assertFact(OWLDataProperty prop, OWLIndividual ind1, Integer myint)
    {
        OWLDataPropertyAssertionAxiom myaxiom = factory.getOWLDataPropertyAssertionAxiom(prop, ind1, myint);
        AddAxiom an_axiom_to_add = new AddAxiom(ontology, myaxiom);
        manager.applyChange(an_axiom_to_add);
        return;
    }
    public void assertDomain(OWLObjectProperty property, OWLClass domain)
    {
        OWLObjectPropertyDomainAxiom myaxiom = factory.getOWLObjectPropertyDomainAxiom(property, domain);
        AddAxiom an_axiom_to_add = new AddAxiom(ontology, myaxiom);
        manager.applyChange(an_axiom_to_add);
        return;
    }
    public void assertRange(OWLObjectProperty property, OWLClass range)
    {
        OWLObjectPropertyRangeAxiom myaxiom = factory.getOWLObjectPropertyRangeAxiom(property, range);
        AddAxiom an_axiom_to_add = new AddAxiom(ontology, myaxiom);
        manager.applyChange(an_axiom_to_add);
        return;
    }
    public void assertDomainAndRange(OWLObjectProperty property, OWLClass domain, OWLClass range)
    {
        assertDomain(property, domain);
        assertRange(property, range);
        return;
    }
    public void assertDataDomain(OWLDataProperty property, OWLClass domain)
    {
        OWLDataPropertyDomainAxiom myaxiom = factory.getOWLDataPropertyDomainAxiom(property, domain);
        AddAxiom an_axiom_to_add = new AddAxiom(ontology, myaxiom);
        manager.applyChange(an_axiom_to_add);
        return;
    }
    public void assertRangeAsInteger(OWLDataProperty property)
    {
        OWLDatatype integerDatatype = factory.getIntegerOWLDatatype();
        OWLDataPropertyRangeAxiom myaxiom = factory.getOWLDataPropertyRangeAxiom(property, integerDatatype);
        AddAxiom an_axiom_to_add = new AddAxiom(ontology, myaxiom);
        manager.applyChange(an_axiom_to_add);
        return;
    }
    public void assertRangeAsDouble(OWLDataProperty property)
    {
        OWLDatatype doubleDatatype = factory.getDoubleOWLDatatype();
        OWLDataPropertyRangeAxiom myaxiom = factory.getOWLDataPropertyRangeAxiom(property, doubleDatatype);
        AddAxiom an_axiom_to_add = new AddAxiom(ontology, myaxiom);
        manager.applyChange(an_axiom_to_add);
        return;
    }
    public void assertRangeAsString(OWLDataProperty property)
    {
        OWLDatatype stringDatatype = factory.getRDFPlainLiteral();
        OWLDataPropertyRangeAxiom myaxiom = factory.getOWLDataPropertyRangeAxiom(property, stringDatatype);
        AddAxiom an_axiom_to_add = new AddAxiom(ontology, myaxiom);
        manager.applyChange(an_axiom_to_add);
        return;
    }
    public void hasClass(OWLIndividual individual, OWLClass myClass)
    {
        OWLClassAssertionAxiom myaxiom = factory.getOWLClassAssertionAxiom(myClass, individual);
        AddAxiom an_axiom_to_add = new AddAxiom(ontology, myaxiom);
        manager.applyChange(an_axiom_to_add);
        return;        
    }
    public void isAsymmetric(OWLObjectProperty property)
    {
         OWLAsymmetricObjectPropertyAxiom myaxiom = factory.getOWLAsymmetricObjectPropertyAxiom(property);
         AddAxiom an_axiom_to_add = new AddAxiom(ontology, myaxiom);
         manager.applyChange(an_axiom_to_add);
         return;        
    }
    public void isFunctional(OWLObjectProperty property)
    {
        OWLFunctionalObjectPropertyAxiom myaxiom = factory.getOWLFunctionalObjectPropertyAxiom(property);
        AddAxiom an_axiom_to_add = new AddAxiom(ontology, myaxiom);
        manager.applyChange(an_axiom_to_add);
        return;  
    }
    public void isFunctional(OWLDataProperty property)
    {
        OWLFunctionalDataPropertyAxiom myaxiom = factory.getOWLFunctionalDataPropertyAxiom(property);
        AddAxiom an_axiom_to_add = new AddAxiom(ontology, myaxiom);
        manager.applyChange(an_axiom_to_add);
        return;
    }
    public void subPropertyOf(OWLObjectProperty subProperty, OWLObjectProperty superProperty)
    {
        OWLSubObjectPropertyOfAxiom myaxiom = factory.getOWLSubObjectPropertyOfAxiom(subProperty, superProperty);
        AddAxiom an_axiom_to_add = new AddAxiom(ontology, myaxiom);
        manager.applyChange(an_axiom_to_add);
        return;  
    }
    public void inverseProperties(OWLObjectProperty property1, OWLObjectProperty property2)
    {
        OWLInverseObjectPropertiesAxiom myaxiom = factory.getOWLInverseObjectPropertiesAxiom(property1, property2);
        AddAxiom an_axiom_to_add = new AddAxiom(ontology, myaxiom);
        manager.applyChange(an_axiom_to_add);
        return;  
    }
    public void isInverseFunctional(OWLObjectProperty property)
    {
        OWLInverseFunctionalObjectPropertyAxiom myaxiom = factory.getOWLInverseFunctionalObjectPropertyAxiom(property);
        AddAxiom an_axiom_to_add = new AddAxiom(ontology, myaxiom);
        manager.applyChange(an_axiom_to_add);
        return;  
    }
    public void isIrreflexive(OWLObjectProperty property)
    {
        OWLIrreflexiveObjectPropertyAxiom myaxiom = factory.getOWLIrreflexiveObjectPropertyAxiom(property);
        AddAxiom an_axiom_to_add = new AddAxiom(ontology, myaxiom);
        manager.applyChange(an_axiom_to_add);
        return;  
    }
    public OWLClassExpression exactCardinality(OWLDataProperty property, Integer cardinality)
    {
        OWLDataExactCardinality myclassexp = factory.getOWLDataExactCardinality(cardinality, property);
        return myclassexp;
    }
    public OWLClassExpression oneOf(OWLIndividual a, OWLIndividual b)
    {
          OWLObjectOneOf myclassexp = factory.getOWLObjectOneOf(a, b);
          return myclassexp;
    }
    public OWLClassExpression oneOf(OWLIndividual a, OWLIndividual b, OWLIndividual c)
    {
          OWLObjectOneOf myclassexp = factory.getOWLObjectOneOf(a, b, c);
          return myclassexp;
    }
            
     
    public void differentIndividuals(OWLIndividual a, OWLIndividual b)
    {
        OWLDifferentIndividualsAxiom myaxiom = factory.getOWLDifferentIndividualsAxiom(a, b);
        AddAxiom an_axiom_to_add = new AddAxiom(ontology, myaxiom);
        manager.applyChange(an_axiom_to_add);
        return;          
    }
    public void isSubClassOf(OWLClass subclass, OWLClass superclass)
    {
        OWLSubClassOfAxiom myaxiom = factory.getOWLSubClassOfAxiom(subclass, superclass);
        AddAxiom an_axiom_to_add = new AddAxiom(ontology, myaxiom);
        manager.applyChange(an_axiom_to_add);
        return;                  
    }
    public void differentIndividuals(OWLIndividual a, OWLIndividual b, OWLIndividual c)
    {
        differentIndividuals(a, b);
        differentIndividuals(b, c);
        differentIndividuals(a, c);
        return;
    }
    public void differentIndividuals(OWLIndividual [] group)
    {
        for (int i = 0; i < group.length; i++)
            for (int j = i; j < group.length; j++)
                differentIndividuals(group[i], group[j]);
        return;
    }
    public void differentIndividuals(java.util.ArrayList<OWLIndividual> group)
    {
       OWLIndividual [] groupArray = (OWLIndividual [])group.toArray();
       differentIndividuals(groupArray);
       return;
    }
            
    public void test() {
            

            
            
        try {
            OWLClass locus               = this.getOwlClass("Locus");
            OWLClass situation           = this.getOwlClass("Situation");
            OWLClass user                = this.getOwlClass("User");
            OWLObjectProperty hasZipCode = this.getProperty("hasZipCode");
            this.isFunctional(hasZipCode); 
            // test instances.
            OWLIndividual john = this.getIndividual("John");
            OWLIndividual mary = this.getIndividual("Mary");
            OWLIndividual susan = this.getIndividual("Susan");
            OWLIndividual bill = this.getIndividual("Bill");
            OWLIndividual david = this.getIndividual("David");
            OWLIndividual kate  = this.getIndividual("Kate");
            OWLObjectProperty hasWife = this.getProperty("hasWife");
            OWLObjectProperty hasHusband = this.getProperty("hasHusband");
            OWLObjectProperty hasChild = this.getProperty("hasChild");
            assertFact(hasWife, john, mary);
            assertFact(hasHusband, mary, john);
            assertFact(hasWife, david, kate);
            assertFact(hasHusband, kate, david);
            OWLObjectProperty hasSon = this.getProperty("hasSon");
            OWLObjectProperty hasDaughter = this.getProperty("hasDaughter"); 
            assertFact(hasSon, john, bill);
            assertFact(hasDaughter, john, susan);
            OWLDataProperty hasAge2 = null; 
            OWLDataProperty hasAge = this.getDataProperty("hasAge"); 
            assertFact(hasAge, john, 33);
            assertFact(hasAge, david, 32);
            assertFact(hasAge, kate,  21);
            assertFact(hasAge, susan, 17);
            assertFact(hasSon, mary, bill);
            assertFact(hasDaughter, mary, susan);
            assertFact(hasAge, mary, 31);
            assertFact(hasAge, bill, 13);
            assertFact(hasAge, susan, 8);
            OWLIndividual male          = this.getIndividual("male");
            OWLIndividual female        = this.getIndividual("female");
            OWLObjectProperty hasGender = this.getProperty("hasGender");
            assertFact(hasGender, john, male);
            assertFact(hasGender, mary, female);
            assertFact(hasGender, bill, male);
            assertFact(hasGender, susan, female);
            assertFact(hasGender, david, male);
            assertFact(hasGender, kate, female);
            OWLClass person = this.getOwlClass("Person");
            assertDomain(hasWife, person);
            assertRange(hasWife, person);
            assertDomain(hasSon, person);
            assertRange(hasSon, person);
            assertDomain(hasDaughter, person);
            assertRange(hasDaughter, person);
            assertDataDomain(hasAge, person);
            assertRangeAsInteger(hasAge);
            hasClass(david, person);
            hasClass(bill, person);
            hasClass(kate, person);
            hasClass(mary, person);
            hasClass(susan, person);
            hasClass(john, person);
            OWLDatatype integerDatatype = factory.getIntegerOWLDatatype();
            inverseProperties(hasWife, hasHusband);
            subPropertyOf(hasSon, hasChild);
            subPropertyOf(hasDaughter, hasChild);
            isFunctional(hasAge);
            isFunctional(hasWife);
            isIrreflexive(hasWife);
            isInverseFunctional(hasWife); 
            isAsymmetric(hasWife);
            OWLClass man    = this.getOwlClass("Man");
            OWLClass woman  = this.getOwlClass("Woman");
            OWLClass parent = this.getOwlClass("Parent");
            this.isSubClassOf(man, person);
            this.isSubClassOf(woman, person);
            this.isSubClassOf(parent, person);
            OWLDataProperty hasGender2 = this.getDataProperty("hasGender2");
            OWLClassExpression hasAgeRestriction = this.exactCardinality(hasAge, 1);
            OWLClassExpression hasGenderRestriction = this.exactCardinality(hasGender2, 1);
            OWLObjectOneOf maleOrFemale = factory.getOWLObjectOneOf(male, female);
            OWLObjectAllValuesFrom hasGenderOnlyMaleFemale = factory.getOWLObjectAllValuesFrom(hasGender, maleOrFemale);

            // Finally, we bundle these restrictions up into an intersection, since we want person
            // to be a subclass of the intersection of them
            OWLObjectIntersectionOf intersection = factory.getOWLObjectIntersectionOf(hasAgeRestriction,
                    hasGenderRestriction,
                    hasGenderOnlyMaleFemale);
            // And now we set this anonymous intersection class to be a superclass of Person using a subclass axiom
            manager.addAxiom(ontology, factory.getOWLSubClassOfAxiom(person, intersection));

            // Restrictions and other anonymous classes can also be used anywhere a named class can be used.
            // Let's set the range of hasSon to be Person and hasGender value male.  This requires an anonymous
            // class that is the intersection of Person, and also, hasGender value male.  We need to create
            // the hasGender value male restriction - this describes the class of things that have a hasGender
            // relationship to the individual male.
            OWLObjectHasValue hasGenderValueMaleRestriction = factory.getOWLObjectHasValue(hasGender,
                    male);
            // Now combine this with Person in an intersection
            OWLClassExpression personAndHasGenderValueMale = factory.getOWLObjectIntersectionOf(person,
                    hasGenderValueMaleRestriction);
            // Now specify this anonymous class as the range of hasSon using an object property range axioms
            manager.addAxiom(ontology, factory.getOWLObjectPropertyRangeAxiom(hasSon, personAndHasGenderValueMale));

            // We can do a similar thing for hasDaughter, by specifying that hasDaughter has a range
            // of Person and hasGender value female.  This time, we will make things a little more compact by
            // not using so many variables

            OWLClassExpression rangeOfHasDaughter = factory.getOWLObjectIntersectionOf(person,
                    factory.getOWLObjectHasValue(hasGender,
                            female));
            manager.addAxiom(ontology, factory.getOWLObjectPropertyRangeAxiom(hasDaughter, rangeOfHasDaughter));

            //////////////////////////////////////////////////////////////////////////////////////////////
            //
            //  Data Ranges and Equivalent Classes axioms
            //
            //////////////////////////////////////////////////////////////////////////////////////////////

            // In OWL 2, we can specify expressive data ranges.  Here, we will specify the classes
            // Teenage, Adult and Child by saying something about individuals ages.

            // First we take the class Teenager, all of whose instance have an age greater or equal to
            // 13 and less than 20.  In Manchester Syntax this is written as Person and hasAge some int[>=13, <20]
            // We create a data range by taking the integer datatype and applying facet restrictions to it.
            // Note that we have statically imported the data range facet vocabulary OWLFacet
            OWLFacetRestriction geq13 = factory.getOWLFacetRestriction(MIN_INCLUSIVE, 12);
            // We don't have to explicitly create the typed constant, there are convenience methods to do this
            OWLFacetRestriction lt20 = factory.getOWLFacetRestriction(MAX_EXCLUSIVE, 25);
            // Restrict the base type, integer (which is just an XML Schema Datatype) with the facet
            // restrictions.
            OWLFacetRestriction lt30 = factory.getOWLFacetRestriction(MAX_EXCLUSIVE, 30);
            OWLDataRange dataRng = factory.getOWLDatatypeRestriction(integerDatatype, geq13, lt20);
            OWLDataRange dataRng2 = factory.getOWLDatatypeRestriction(integerDatatype, geq13, lt30);
            // Now we have the data range of greater than equal to 13 and less than 20 we can use this in a
            // restriction.
            OWLDataSomeValuesFrom teenagerAgeRestriction = factory.getOWLDataSomeValuesFrom(hasAge, dataRng);
            OWLDataSomeValuesFrom lessThanThirtyAgeRestriction = factory.getOWLDataSomeValuesFrom(hasAge, dataRng2);
            // Now make Teenager equivalent to Person and hasAge some int[>=13, <20]
            // First create the class Person and hasAge some int[>=13, <20]
            OWLClassExpression teenagePerson = factory.getOWLObjectIntersectionOf(person, teenagerAgeRestriction);

            OWLClass teenager = factory.getOWLClass(IRI.create(ontologyIRI + "#Teenager"));
            OWLEquivalentClassesAxiom teenagerDefinition = factory.getOWLEquivalentClassesAxiom(teenager, teenagePerson);
            manager.addAxiom(ontology, teenagerDefinition);

            // Do the same for Adult that has an age greater than 21
            OWLDataRange geq21 = factory.getOWLDatatypeRestriction(integerDatatype,
                    factory.getOWLFacetRestriction(MIN_INCLUSIVE, 21));
            OWLClass adult = factory.getOWLClass(IRI.create(ontologyIRI + "#Adult"));
            OWLClassExpression adultAgeRestriction = factory.getOWLDataSomeValuesFrom(hasAge, geq21);
            OWLClassExpression adultPerson = factory.getOWLObjectIntersectionOf(person, adultAgeRestriction);
            OWLAxiom adultDefinition = factory.getOWLEquivalentClassesAxiom(adult, adultPerson);
            manager.addAxiom(ontology, adultDefinition);

            // And finally Child
            OWLDataRange notGeq21 = factory.getOWLDataComplementOf(geq21);
            OWLClass child = factory.getOWLClass(IRI.create(ontologyIRI + "#Child"));
            OWLClassExpression childAgeRestriction = factory.getOWLDataSomeValuesFrom(hasAge, notGeq21);
            OWLClassExpression childPerson = factory.getOWLObjectIntersectionOf(person, childAgeRestriction);
            OWLAxiom childDefinition = factory.getOWLEquivalentClassesAxiom(child, childPerson);
            manager.addAxiom(ontology, childDefinition);

            //////////////////////////////////////////////////////////////////////////////////////////////
            //
            //  Different individuals
            //
            //////////////////////////////////////////////////////////////////////////////////////////////

            // In OWL, we can say that individuals are different from each other.  To do this we use a
            // different individuals axiom.  Since John, Mary, Bill and Susan are all different individuals,
            // we can express this using a different individuals axiom.
            OWLDifferentIndividualsAxiom diffInds = factory.getOWLDifferentIndividualsAxiom(john, mary, bill, susan, david, kate);
            manager.addAxiom(ontology, diffInds);
            // Male and Female are also different
            manager.addAxiom(ontology, factory.getOWLDifferentIndividualsAxiom(male, female));

            //////////////////////////////////////////////////////////////////////////////////////////////
            //
            //  Disjoint classes
            //
            //////////////////////////////////////////////////////////////////////////////////////////////

            // Two say that two classes do not have any instances in common we use a disjoint classes
            // axiom:
            OWLDisjointClassesAxiom disjointClassesAxiom = factory.getOWLDisjointClassesAxiom(man, woman);
            manager.addAxiom(ontology, disjointClassesAxiom);

                     

            // Manchester Syntax
            // manager.saveOntology(ont, new ManchesterOWLSyntaxOntologyFormat(), new StreamDocumentTarget(System.out));
            manager.saveOntology(ontology,  new ManchesterOWLSyntaxOntologyFormat(), IRI.create(new File("Manchester.owl")));
            System.out.println("Manchester syntax: --- saved in Manchester.owl");

            // Turtle
           // manager.saveOntology(ont, new TurtleOntologyFormat(), new StreamDocumentTarget(System.out));
           manager.saveOntology(ontology, new TurtleOntologyFormat(), IRI.create(new File("Turtle.owl")));
           System.out.println("Turtle: --- saved in Turtle.owl");
        }
        catch (OWLOntologyStorageException e) {
            System.out.println("Problem saving ontology: " + e.getMessage());
        }
    
    }
 
    public String get_owl()
    {
    	String s="";
        try
        {
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			manager.saveOntology(ontology, outputStream);
			s = new String(outputStream.toByteArray(), "UTF-8");
        }
        catch (Exception e)
        {
          s= e.getMessage();  
        }       
        return s;  
    }
}