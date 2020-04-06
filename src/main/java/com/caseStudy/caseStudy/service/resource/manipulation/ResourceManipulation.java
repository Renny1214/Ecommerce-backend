package com.caseStudy.caseStudy.service.resource.manipulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ResourceManipulation {

    public static StringBuilder getResource(String fileName) throws IOException {
        InputStream inputStream= ResourceManipulation.class.getResourceAsStream(fileName);
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder=new StringBuilder();
        String line;

        while ((line=bufferedReader.readLine()) != null){
            stringBuilder.append(line);
        }

        return stringBuilder;
    }

}
