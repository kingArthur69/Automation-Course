package com.alliedtesting;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Iterator;

public class JSONparse {

    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        Workbook workbook = new XSSFWorkbook();
        CreationHelper creationHelper = workbook.getCreationHelper();

        try{
            Object object = parser.parse(new FileReader("company.json"));

            JSONObject jsonObject = (JSONObject) object;
            System.out.println(jsonObject);

            JSONObject object2 = (JSONObject) object;
            System.out.println(object2.get("department"));

            JSONArray array = (JSONArray) object2.get("department");
            for(int i = 0; i < array.size();i++)
            {
                JSONObject department = (JSONObject) array.get(i);
                String depName = (String) department.get("name");
                String depId = (String) department.get("depId");

                Sheet sheet1 = workbook.createSheet(depName + " " +depId);

                Row header = sheet1.createRow(0);
                Cell  empId = header.createCell(0);
                empId.setCellValue("Emp Id");

                Cell lastName = header.createCell(1);
                lastName.setCellValue("Lastname");

                Cell firstName = header.createCell(2);
                firstName.setCellValue("Firstname");

                Cell birthDate = header.createCell(3);
                birthDate.setCellValue("BirthDate");

                Cell managerId = header.createCell(4);
                managerId.setCellValue("Manager ID");

                Cell skills = header.createCell(5);
                skills.setCellValue("Skills");

                JSONArray empArray = (JSONArray)  department.get("employees");
                for(int j = 0; j < empArray.size(); j++)
                {
                    JSONObject employee = (JSONObject) empArray.get(j);
                    Row empRow = sheet1.createRow(j+1);
                    empId = empRow.createCell(0);
                    String empIdstr = (String) employee.get("empId");
                    empId.setCellValue(empIdstr);

                    lastName = empRow.createCell(1);
                    String lastNamestr = (String) employee.get("lastName");
                    lastName.setCellValue(lastNamestr);

                    firstName = empRow.createCell(2);
                    String firstNamestr = (String) employee.get("firstName");
                    firstName.setCellValue(firstNamestr);

                    birthDate = empRow.createCell(3);
                    String birthDatestr = (String) employee.get("birthDate");
                    birthDate.setCellValue(birthDatestr);

                    managerId = empRow.createCell(4);
                    String managerIdstr = (String) employee.get("managerId");
                    managerId.setCellValue(managerIdstr);

                    skills = empRow.createCell(5);
                    JSONArray skillArray = (JSONArray) employee.get("skills");
                    CellStyle cs = workbook.createCellStyle();
                    cs.setWrapText(true);
                    skills.setCellStyle(cs);
                    String skillsstr1 = (String) skillArray.get(0);
                    String skillsstr2 = (String) skillArray.get(1);

                    skills.setCellValue(skillsstr1 + "\n" + skillsstr2);
                    //empRow.setHeightInPoints(2*sheet1.getDefaultRowHeightInPoints());
                    sheet1.autoSizeColumn(2);
                }
            }
            try (OutputStream fileOut = new FileOutputStream("workbook.xlsx")) {
                workbook.write(fileOut);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
