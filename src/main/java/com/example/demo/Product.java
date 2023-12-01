package com.example.demo;

import java.io.Serializable;




public class Product implements Serializable { // 인터페이스 선언

    private static final long serialVersionUID = -4274700572038677000L;

    private String id;	//상품 아이디
    private String name;		//상품명
    private Integer unitPrice; //상품 가격
    private String description; //상품 설명
    private String manufacturer;//제조사
    private String category; 	//분류
    private long unitsInStock; //재고수
    private String condition; 	//신상품 or 중고품 or 재생품
    private String filename;
    private int quantity;

    public Product() {
        super();
    }

    public Product(String id, String name, Integer unitPrice, String description, String manufacturer, String category, long unitsInStock, String condition, String filename, int quantity) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.description = description;
        this.manufacturer = manufacturer;
        this.category = category;
        this.unitsInStock = unitsInStock;
        this.condition = condition;
        this.filename = filename;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(long unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
