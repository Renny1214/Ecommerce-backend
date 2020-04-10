package com.caseStudy.caseStudy.service;

import com.caseStudy.caseStudy.doa.product.ProductRepository;
import com.caseStudy.caseStudy.models.products.PriceColorAndImages;
import com.caseStudy.caseStudy.models.products.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SearchService {

    private String previousAttr;
    private String previousKey;

    private final ArrayList<String> prepositions;
    private Set<Product> result;
    private int recentPrep=-1;

    public SearchService() {
        result = new HashSet<>();
        prepositions = new ArrayList<>();

        final String[] arr = {"of", "with", "at", "from", "including", "to", "for", "by", "over", "after", "under", "within", "beyond", "around", "above", "near"};
        prepositions.addAll(Arrays.asList(arr));
    }

    public Set<Product> searchRoot(String search,ProductRepository productRepository){
        String[] str=search.split(" ");

        for(int i=0;i<str.length;i++){
            search(str[i],productRepository);
        }

        return result;
    }

    private Set<Product> search(String keyword,ProductRepository productRepository){

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
                if(previousAttr == null){
                    //category
                    if(!productRepository.findAllByCategory(keyword).isEmpty()){
                        result.addAll(productRepository.findAllByCategory(keyword));
                        previousAttr="category";
                        previousKey=keyword;
                    }

                    //subcategory
                    if(result.isEmpty()){
                        if(!productRepository.findAllBySubcategory(keyword).isEmpty()){
                            result.addAll(productRepository.findAllBySubcategory(keyword));
                            previousAttr="subcategory";
                            previousKey=keyword;
                        }
                    }

                    //brand
                    if(result.isEmpty()){
                        if(!productRepository.findAllByBrand(keyword).isEmpty()){
                            result.addAll(productRepository.findAllByBrand(keyword));
                            previousAttr="brand";
                            previousKey=keyword;
                        }
                    }

                    //name
                    if(result.isEmpty()){
                        if(!productRepository.findAllByName(keyword).isEmpty()){
                            result.addAll(productRepository.findAllByName(keyword));
                            previousAttr="name";
                            previousKey=keyword;
                        }
                    }
                }
                else if(recentPrep != -1){
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
                else if(previousAttr != null){

                    if(previousAttr.equals("category")){
                        Iterator<Product> iterator=result.iterator();
                        while (iterator.hasNext()){
                            Product product=iterator.next();

                            if(!product.getBrand().toLowerCase().equals(keyword)
                                    && !product.getName().toLowerCase().equals(keyword)){
                                result.remove(product);
                            }
                        }
                    }
                    else if(previousAttr.equals("brand")){
                        Iterator<Product> iterator=result.iterator();
                        while (iterator.hasNext()){
                            Product product=iterator.next();

                            if(!product.getCategory().toLowerCase().equals(keyword)
                                    && !product.getSubcategory().toLowerCase().equals(keyword)
                                    && !product.getName().toLowerCase().equals(keyword)){
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
