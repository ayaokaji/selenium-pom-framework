package com.automationframeworks.selenium_testng_java.utilities;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestUserProvider {
	
	public JSONObject applicationUser;
    public String determineUserFilePath(){
        String environment = System.getProperty("env", "dev");
        if (environment.equals("prod")){
            return "src/main/resources/prod-users.json";
        }
        else if(environment.equals("uat"))
		{
        	return "src/main/resources/uat-users.json";
		}
        else{
            return "src/main/resources/dev-users.json";
        }
    }

    public JSONObject readAllUsers(){
        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get(determineUserFilePath())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new JSONObject(content);
    }


    public JSONObject returnUserByApplicationRole(String application, String role) {
        JSONObject allUsers = readAllUsers();
        for (String key : allUsers.keySet()) {
            JSONObject singleUser = allUsers.getJSONObject(key);
            JSONObject userRoles = singleUser.getJSONObject("application_roles");

            if (userRoles.has(application)) {
                JSONArray jsonElements =  userRoles.getJSONArray(application);
                for(Object singleRole: jsonElements){
                    if (singleRole.toString().equals(role)) {
                        return singleUser;
                    }
                }
            }
        }
        throw new RuntimeException("No user matched for Application: " + application + " and Role: " + role);
    }



    

}
