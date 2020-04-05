package com.caseStudy.caseStudy.service.threads;

import com.caseStudy.caseStudy.service.resource.manipulation.ResourceManipulation;
import org.json.JSONObject;

import java.io.IOException;

final public class Credentials {

    private final static String emailKey="email";
    private final static String passwordKey="password";
    private final static String credentialFile="/credential.json";

    public static String getCompanyEmail() throws IOException {
        StringBuilder stringBuilder= ResourceManipulation.getResource(credentialFile);

        JSONObject jsonObject=new JSONObject(stringBuilder.toString());
        return jsonObject.getString(emailKey);
    }
    public static String getCompanyPassword() throws IOException{
        StringBuilder stringBuilder=ResourceManipulation.getResource(credentialFile);

        JSONObject jsonObject=new JSONObject(stringBuilder.toString());
        return jsonObject.getString(passwordKey);
    }
}
