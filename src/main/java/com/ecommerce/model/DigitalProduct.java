package com.ecommerce.model;

public class DigitalProduct extends Product{

    private String downloadUrl;

    public DigitalProduct(String id, String name, double price, Category category, String downloadUrl) {
        super(id, name, price, category);
        setDownloadUrl(downloadUrl);
    }


    public void setDownloadUrl(String downloadUrl){
        if(downloadUrl == null || downloadUrl.trim().isEmpty()){
            throw new IllegalArgumentException("Download URL Cannot be Empty.");
        }
        this.downloadUrl= downloadUrl;
    }


    @Override
    public double calculateTax() {
        return getPrice() * 0.05;
    }

    @Override
    public String toString() {
        return "DigitalProduct{" +
                "id='"+ getId() + '\'' +
                ",name='"+ getName() + '\'' +
                ",price="+ getPrice() +
                ",downloadUrl='" + downloadUrl+'\''  +
                '}';
    }
}
