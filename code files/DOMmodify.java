package com.alliedtesting;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DOMmodify {
    public static void main(String[] args) {
        try{

            File inputFile = new File("company.xml");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(inputFile);

            Node company = doc.getFirstChild();

            Element department = doc.createElement("department");
            company.appendChild(department);


            Attr attr = doc.createAttribute("name");
            attr.setValue("Cleaning");

            Attr attr2 = doc.createAttribute("depId");
            attr2.setValue("5");

            department.setAttributeNode(attr);
            department.setAttributeNode(attr2);

            Element lastName = doc.createElement("lastName");
            lastName.appendChild(doc.createTextNode("Babayan"));

            Element firstName = doc.createElement("firstName");
            firstName.appendChild(doc.createTextNode("Emi"));

            Element birthDate = doc.createElement("birthDate");
            birthDate.appendChild(doc.createTextNode("10.02.1999"));

            Element position = doc.createElement("Position");
            position.appendChild(doc.createTextNode("Son of mother's friend"));

            Element skills = doc.createElement("skills");
            Element skill = doc.createElement("skill");
            skill.appendChild(doc.createTextNode("Better than you in everything"));
            skills.appendChild(skill);

            Element managerId = doc.createElement("managerId");
            managerId.appendChild(doc.createTextNode("0"));

            department.appendChild(lastName);
            department.appendChild(firstName);
            department.appendChild(birthDate);
            department.appendChild(position);
            department.appendChild(skills);
            department.appendChild(managerId);
//            Node department = doc.getElementsByTagName("department").item(0);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            System.out.println("-----------Modified File-----------");
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
