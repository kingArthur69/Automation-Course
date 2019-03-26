package com.alliedtesting;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class UserHandler extends DefaultHandler {

    boolean bFirstName = false;
    boolean bLastName = false;
    boolean bBirthDate = false;
    boolean bManagerId = false;
    boolean bSkills = false;

    @Override
    public void startElement(String uri,
                             String localName, String qName, Attributes attributes) throws SAXException {

        if(qName.equalsIgnoreCase("employee")){
            String empId = attributes.getValue("empId");
            System.out.println("Roll No: " + empId);
        }
        else if (qName.equalsIgnoreCase("firstName")){
            bFirstName = true;
        }
        else if (qName.equalsIgnoreCase("lastName")){
            bLastName = true;
        }
        else if (qName.equalsIgnoreCase("birthDate")){
            bBirthDate = true;
        }
        else if (qName.equalsIgnoreCase("skill")){
            bSkills = true;
        }
        else if (qName.equalsIgnoreCase("managerId")){
            bManagerId = true;
        }
    }

    @Override
    public void endElement(String uri,
                           String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("employees")){
            System.out.println("End Element: " + qName);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {

        if (bFirstName) {
            System.out.println("First Name: "
                    + new String(ch, start, length));
            bFirstName = false;
        } else if (bLastName) {
            System.out.println("Last Name: " + new String(ch, start, length));
            bLastName = false;
        } else if ( bBirthDate) {
            System.out.println("Birth Date: " + new String(ch, start, length));
            bBirthDate = false;
        } else if ( bSkills) {
            System.out.println("\tSkill: " + new String(ch, start, length));
            bSkills = false;
        } else if (bManagerId) {
            System.out.println("Manager Id: " + new String(ch, start, length));
            bManagerId = false;
        }
    }


}
