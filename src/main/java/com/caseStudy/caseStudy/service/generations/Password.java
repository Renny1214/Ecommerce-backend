package com.caseStudy.caseStudy.service.generations;

import java.util.Random;

public class Password {

    private final int asciiChar=95;
    private final String[] specialChar={"!","@","#","$","%","&","?"};

    public String generatePassword(){
        StringBuilder stringBuilder=new StringBuilder();
        Random random=new Random();

        for(int i=0;i<6;i++){
            char c=(char)(asciiChar+random.nextInt(26));
            stringBuilder.append(c);
        }
        for(int i=0;i<4;i++){
            int temp=random.nextInt(10);
            stringBuilder.append(temp);
        }

        int index=random.nextInt(7);
        stringBuilder.append(specialChar[index]);

        return stringBuilder.toString();
    }
}
