package com.alliedtesting;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class JSONwrite {
    public static void main(String[] args) {
        JSONObject object = new JSONObject();
        object.put("name","Cleaning");
        object.put("depId","004");

        JSONObject innerObject = new JSONObject();
        innerObject.put("empId", "7");
        innerObject.put("firstName","Kostya");
        innerObject.put("lastName", "Kotov");
        innerObject.put("birthDate", "20 Jun 1980");
        innerObject.put("position", "Cleaner");

        JSONArray skills = new JSONArray();
        skills.add("Good health");
        skills.add("Speed x2");
        innerObject.put("skills",skills);
        innerObject.put("managerId","5");

        JSONArray list = new JSONArray();
        list.add(innerObject);

        object.put("employees",list);

        JSONArray department = new JSONArray();
        department.add(object);

        JSONObject outerObject = new JSONObject();
        outerObject.put("department",department);

        try {
            FileWriter file = new FileWriter("JSONwrite.json");

            file.write((outerObject.toJSONString()));
            file.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        System.out.println(outerObject);
    }
}
