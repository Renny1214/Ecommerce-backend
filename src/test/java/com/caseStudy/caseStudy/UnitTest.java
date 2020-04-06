package com.caseStudy.caseStudy;

import com.caseStudy.caseStudy.service.generations.OTP;
import org.junit.Test;
import static org.junit.Assert.*;


public class UnitTest {

    @Test
    public void testOTP(){
        OTP otp=new OTP();
        System.out.println(otp.generateOTP());
    }
}
