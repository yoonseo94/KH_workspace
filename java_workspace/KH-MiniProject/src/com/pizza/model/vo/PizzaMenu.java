package com.pizza.model.vo;

import java.io.Serializable;
import java.util.List;

public class PizzaMenu extends Menu implements Serializable, Comparable<PizzaMenu>  {

    private static final long serialVersionUID = 1L;
    private String pNo;     // 메뉴 번호
    private String categoryNum; // 카테고리 정렬용 이름
    private String category;    // 카테고리
    private String title;    // 메뉴 이름
    private int price;        // 가격
    
    private List<ToppingMenu> toppings;
    
    
    
    public PizzaMenu() {}
    
    public PizzaMenu(String pNo, String categoryNum, String category, String title, int price) {
        super();
        this.pNo = pNo;
        this.categoryNum = categoryNum;
        this.category = category;
        this.title = title;
        this.price = price;
    }
    
    public List<ToppingMenu> getToppings() {
        return toppings;
    }

    public void setToppings(List<ToppingMenu> toppings) {
        this.toppings = toppings;
    }

    
    public String getCategoryNum() {
        return categoryNum;
    }
    public void setCategoryNum(String categoryNum) {
        this.categoryNum = categoryNum;
    }
    public String getpNo() {
        return pNo;
    }
    public void setpNo(String pNo) {
        this.pNo = pNo;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }



    @Override
    public String toString() {
        return category + "\t" + title + "\t\t" + price + "\t" + "토핑 : " + toppings;
    }

    @Override
    public int compareTo(PizzaMenu o) {
        return this.categoryNum.compareTo(o.categoryNum);
    }
    
}