package com.caseStudy.caseStudy.service;

import com.caseStudy.caseStudy.doa.product.ProductRepository;
import com.caseStudy.caseStudy.models.products.PriceColorAndImages;
import com.caseStudy.caseStudy.models.products.Product;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class SearchService {

    private String previousAttr;
    private String previousKey;

    private final ArrayList<String> prepositions;
    private Set<Product> result;
    private int recentPrep=-1;

    public SearchService(){
        result=new HashSet<>();
        prepositions=new ArrayList<>();

        final String[] arr={"of","with","at","from","including","to","for","by","over","after","under","within","beyond","around","above","near"};
        prepositions.addAll(Arrays.asList(arr));
    }

    @Autowired
    ProductRepository productRepository;

    public Set<Product> search(String keyword){
        keyword=keyword.toLowerCase();

        try{
            int checkNumber=Integer.parseInt(keyword);
            System.out.println("Search element is number");

            if(recentPrep != -1 && previousAttr != null){
                if(prepositions.get(recentPrep).equals("from")
                        || prepositions.get(recentPrep).equals("over")
                        || prepositions.get(recentPrep).equals("beyond")
                        || prepositions.get(recentPrep).equals("above")){

                    Iterator<Product> iterator=result.iterator();
                    while (iterator.hasNext()){
                        Product product=iterator.next();
                        if(average(product.getPriceColorAndImages()) < Double.parseDouble(keyword)){
                            result.remove(product);
                        }
                    }
                }
                else if(prepositions.get(recentPrep).equals("under")
                        || prepositions.get(recentPrep).equals("around")
                        || prepositions.get(recentPrep).equals("near")){

                    Iterator<Product> iterator=result.iterator();
                    while (iterator.hasNext()){
                        Product product=iterator.next();
                        if(average(product.getPriceColorAndImages()) > Double.parseDouble(keyword)){
                            result.remove(product);
                        }
                    }
                }
            }
        }
        catch(NumberFormatException n){
            System.out.println("Search element is not number");

            if(!prepositions.contains(keyword)){
                if(previousAttr == null && recentPrep == -1){
                    result.addAll(productRepository.findAllByCategoryIgnoreCase(keyword));
                    previousAttr="category";
                    previousKey=keyword;

                    //subcategory
                    if(result.isEmpty()){
                        result.addAll(productRepository.findAllBySubcategoryIgnoreCase(keyword));
                        previousAttr="subcategory";
                        previousKey=keyword;
                    }

                    //brand
                    if(result.isEmpty()){
                        result.addAll(productRepository.findAllByBrandIgnoreCase(keyword));
                        previousAttr="brand";
                        previousKey=keyword;
                    }

                    //name
                    if(result.isEmpty()){
                        result.addAll(productRepository.findAllByNameIgnoreCase(keyword));
                        previousAttr="name";
                        previousKey=keyword;
                    }
                }
                else if(recentPrep != 0 && previousAttr != null){
                    if(prepositions.get(recentPrep).equals("of")
                            || prepositions.get(recentPrep).equals("from")
                            || prepositions.get(recentPrep).equals("by")){

                        Iterator<Product> iterator=result.iterator();
                        while (iterator.hasNext()){
                            Product product=iterator.next();
                            if(!product.getBrand().toLowerCase().contains(keyword)){
                                result.remove(product);
                            }
                        }
                    }
                    else if(prepositions.get(recentPrep).equals("for")){

                        Iterator<Product> iterator=result.iterator();
                        while (iterator.hasNext()){
                            Product product=iterator.next();
                            if(!product.getGender().toLowerCase().contains(keyword)){
                                result.remove(product);
                            }
                        }
                    }
                }
                else if(recentPrep == 0 && previousAttr != null){

                    if(previousAttr.equals("category")){
                        Iterator<Product> iterator=result.iterator();
                        while (iterator.hasNext()){
                            Product product=iterator.next();

                            if(!product.getBrand().toLowerCase().equals(keyword)
                                    || !product.getName().toLowerCase().equals(keyword)){
                                result.remove(product);
                            }
                        }
                    }
                    else if(previousAttr.equals("brand")){
                        Iterator<Product> iterator=result.iterator();
                        while (iterator.hasNext()){
                            Product product=iterator.next();

                            if(!product.getCategory().toLowerCase().equals(keyword)
                                    || !product.getSubcategory().toLowerCase().equals(keyword)
                                    || !product.getName().toLowerCase().equals(keyword)){
                                result.remove(product);
                            }
                        }
                    }
                }
            }
            else if(prepositions.contains(keyword)){
                recentPrep=prepositions.indexOf(keyword);
            }
        }

        return result;
    }

    private double average(List<PriceColorAndImages> list){
        double sum=0;
        for(int i=0;i<list.size();i++){
            sum+=list.get(i).getPrice();
        }

        return (sum/list.size());
    }
}
