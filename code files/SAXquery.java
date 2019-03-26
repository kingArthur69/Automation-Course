package com.alliedtesting;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class SAXquery {
    public static void main(String[] args) {

        try {
            File inputFile = new File("company.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            UserHandler2 userhandler = new UserHandler2();
            saxParser.parse(inputFile, userhandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class UserHandler2 extends DefaultHandler {

    boolean bFirstName = false;
    boolean bLastName = false;
    boolean bBirthDate = false;
    boolean bManagerId = false;
    boolean bSkills = false;
    String empId = null;

    @Override
    public void startElement(String uri,
                             String localName, String qName, Attributes attributes) throws SAXException {

        if(qName.equalsIgnoreCase("employee")){
           empId = attributes.getValue("empId");

        }
            if(("002").equals(empId) && qName.equalsIgnoreCase("employee")){
                System.out.println("Roll No: " + empId);
            }
            {
                if (qName.equalsIgnoreCase("firstName")){
                    bFirstName = true;
                } else if (qName.equalsIgnoreCase("lastName")){
                    bLastName = true;
                } else if (qName.equalsIgnoreCase("birthDate")){
                    bBirthDate = true;
                } else if (qName.equalsIgnoreCase("skill")){
                    bSkills = true;
                } else if (qName.equalsIgnoreCase("managerId")){
                    bManagerId = true;
                }
            }

    }

    @Override
    public void endElement(String uri,
                           String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("employees")){
            if(("002").equals(empId)
                    && qName.equalsIgnoreCase("employee"))
                System.out.println("End Element: " + qName);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {

        if (bFirstName && ("002").equals(empId)) {
            System.out.println("First Name: "
                    + new String(ch, start, length));
            bFirstName = false;
        } else if (bLastName && ("002").equals(empId)) {
            System.out.println("Last Name: " + new String(ch, start, length));
            bLastName = false;
        } else if ( bBirthDate && ("002").equals(empId)) {
            System.out.println("Birth Date: " + new String(ch, start, length));
            bBirthDate = false;
        } else if ( bSkills && ("002").equals(empId)) {
            System.out.println("\tSkill: " + new String(ch, start, length));
            bSkills = false;
        } else if (bManagerId && ("002").equals(empId)) {
            System.out.println("Manager Id: " + new String(ch, start, length));
            bManagerId = false;
        }
    }


}
