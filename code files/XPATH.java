package com.alliedtesting;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;

public class XPATH {

    public static void main(String[] args) {
        try {
            File inputFile = new File("company.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder;

            dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/company/department";
            System.out.println(checkIfNodeExists(doc, expression));

            expression = "/skills/student";
            System.out.println(checkIfChildExists(doc, expression));

            expression = "//skills";
            System.out.println(checkIfChildExists(doc, expression));

            expression = "//skill";
            System.out.println(checkIfChildExists(doc, expression));

        }
        catch (ParserConfigurationException e){
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    private static boolean checkIfNodeExists(Document document, String xpathExpression) throws Exception
    {
        boolean matches = false;

        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();

        try{
            XPathExpression expression = xpath.compile(xpathExpression);

            NodeList nodes = (NodeList) expression.evaluate(document, XPathConstants.NODESET);

            if(nodes != null && nodes.getLength() > 0){
                matches = true;
            }
        }
        catch (XPathExpressionException e){
            e.printStackTrace();
        }
        return matches;
    }

    private static boolean checkIfChildExists(Document document, String xpathExpression) throws Exception
    {
        boolean matches = false;

        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();

        try{
            XPathExpression expression = xpath.compile(xpathExpression);

            NodeList nodes = (NodeList) expression.evaluate(document, XPathConstants.NODESET);

            if(nodes != null && nodes.getLength() > 0){
                for (int i = 0; i < nodes.getLength(); i++) {
                    Node nNode = nodes.item(i);
                    if(nNode.hasChildNodes())
                    {
                        NodeList nNodeList = nNode.getChildNodes();
                        for (int j = 0; i < nNodeList.getLength(); i++)
                        {
                            Node nNode2 = nNodeList.item(j);
                            //System.out.println(nNode2.getTextContent());
                            if(nNode2.getNodeType() != Node.TEXT_NODE)
                                matches = true;
                        }

                    }
                }

            }
        }
        catch (XPathExpressionException e){
            e.printStackTrace();
        }
        return matches;
    }

    //private static

}
