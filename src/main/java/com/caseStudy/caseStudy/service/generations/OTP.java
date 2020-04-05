package com.caseStudy.caseStudy.service.generations;

import java.util.Random;

public class OTP {

    public String generateOTP(){
        Random random=new Random();
        StringBuilder stringBuilder=new StringBuilder();

        for(int i=0;i<4;i++){
            int temp=random.nextInt(10);
            stringBuilder.append(temp);
        }

        return stringBuilder.toString();
    }
}
