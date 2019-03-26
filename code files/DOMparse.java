package com.alliedtesting;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class DOMparse {

    public static void main(String[] args) {
        try{
            File inputFile = new File("company.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("department");
            System.out.println("------------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.print("\nCurrent Element :");
                System.out.println(nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.print("Department : ");
                    System.out.println(eElement.getAttribute("name"));
                    System.out.print("Department ID : ");
                    System.out.println(eElement.getAttribute("depId"));
                    System.out.println("");
                    NodeList carNameList = eElement.getElementsByTagName("employee");

                    for (int count = 0; count < carNameList.getLength(); count++) {
                        Node node1 = carNameList.item(count);

                        if (node1.getNodeType() == node1.ELEMENT_NODE) {
                            Element employee = (Element) node1;

                            System.out.println("\tEmployee ID : "
                                    + employee
                                    .getAttribute("empId"));

                            System.out.println("\tLast Name : "
                                    + employee
                                    .getElementsByTagName("lastName")
                                    .item(0)
                                    .getTextContent());
                            System.out.println("\tFirst Name : "
                                    + employee
                                    .getElementsByTagName("firstName")
                                    .item(0)
                                    .getTextContent());

                            System.out.println("\tPosition : "
                                    + employee
                                    .getElementsByTagName("position")
                                    .item(0)
                                    .getTextContent());
                            System.out.println("\tDate of Birth : "
                                    + employee
                                    .getElementsByTagName("birthDate")
                                    .item(0)
                                    .getTextContent());

                            System.out.println("\tSkills:");

                            NodeList skillsList = employee.getElementsByTagName("skills");
                            for (int tmp = 0; tmp < skillsList.getLength(); tmp++)
                            {
                                Node skillNode = skillsList.item(tmp);
                                if (skillNode.getNodeType() == Node.ELEMENT_NODE)
                                {
                                    Element skill = (Element) skillNode;
                                    NodeList skillsList2 = ((Element) skillNode).getElementsByTagName("skill");
                                    for (int tmp2 = 0; tmp2 < skillsList2.getLength(); tmp2++)
                                    {
                                        Element skill2 = (Element) skillsList2.item(tmp2);
                                        System.out.println("\t\tSkill "+ tmp2 + " : "
                                                +skill.getElementsByTagName("skill")
                                                .item(tmp2)
                                                .getTextContent() );
                                    }
                                    ;
                                }
                            }

                            System.out.println("\tManager ID : "
                                    + employee
                                    .getElementsByTagName("managerId")
                                    .item(0)
                                    .getTextContent()+"\n");
                        }
                    }
                }
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
