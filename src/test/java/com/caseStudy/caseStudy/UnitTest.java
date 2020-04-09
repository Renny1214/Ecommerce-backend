package com.caseStudy.caseStudy;

import com.caseStudy.caseStudy.models.products.Product;
import com.caseStudy.caseStudy.service.ProductService;
import com.caseStudy.caseStudy.service.generations.OTP;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.*;


public class UnitTest {

    @Test
    public void testOTP(){
        OTP otp=new OTP();
        System.out.println(otp.generateOTP());
    }

    @Test
    public void addProductTest() throws Exception{
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("name","Airmax")
                .put("brand","Nike")
                .put("category","shoes")
                .put("subcategory","sneakers")
                .put("gender","men")
                .put("description","Original Nike Airmax. The best sneakers in the world");

        JSONArray priceColorAndImages=new JSONArray();
        JSONObject jsonObject1=new JSONObject();
        jsonObject1.put("price",5500)
                .put("color","white");
        JSONArray stockAndSizes=new JSONArray();
        JSONObject jsonObject2=new JSONObject();
        jsonObject2.put("unitsInStock",3)
                .put("size","10");

        stockAndSizes.put(jsonObject2);

        jsonObject2=new JSONObject();
        jsonObject2.put("unitsInStock",6)
                .put("size","9");
        stockAndSizes.put(jsonObject2);

        jsonObject1.put("stockAndSizes",stockAndSizes);
        priceColorAndImages.put(jsonObject1);

        jsonObject1=new JSONObject();
        jsonObject1.put("price",4500);
        jsonObject1.put("color","black");

        stockAndSizes=new JSONArray();
        jsonObject2=new JSONObject();
        jsonObject2.put("unitsInStock",6)
                .put("size","8");
        stockAndSizes.put(jsonObject2);

        jsonObject1.put("stockAndSizes",stockAndSizes);
        priceColorAndImages.put(jsonObject1);

        jsonObject.put("priceColorAndImages",priceColorAndImages);


        System.out.println(jsonObject.toString());

        String email="rishabhmalhotra091@gmail.com";
        String password="Rishabh123@";
        String authStr=email+":"+password;
        byte[] authEncBytes= Base64.encodeBase64(authStr.getBytes());
        String authStringEnc=new String(authEncBytes);

        URL url=new URL("http://localhost:10083/products/addProducts");
        HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestProperty("Authorization","Basic "+authStringEnc);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);

        OutputStream outputStream=httpURLConnection.getOutputStream();
        System.out.println(jsonObject.toString());

        outputStream.write(jsonObject.toString().getBytes(StandardCharsets.UTF_16));
        outputStream.flush();
        outputStream.close();
        httpURLConnection.connect();

        int responseCode=httpURLConnection.getResponseCode();
        System.out.println("Response Code : "+responseCode);

        if(responseCode == HttpURLConnection.HTTP_OK){
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            StringBuffer response=new StringBuffer();

            while ((inputLine=bufferedReader.readLine())!=null){
                response.append(inputLine);
            }
            bufferedReader.close();

            System.out.println("Response : "+bufferedReader.toString());
        }
        else{
            System.out.println("POST request not worked");
        }
    }

    @Test
    public void searchTest(){
        ProductService productService=new ProductService();
        Set<Product> set=productService.getItemFromSearch("Nike");

        System.err.println("Search Length : "+set.size());

        Iterator<Product> iterator=set.iterator();
        while (iterator.hasNext()){
            Product product=iterator.next();

            System.err.println(product.getName()+"\t"+product.getBrand());
        }
    }
}
