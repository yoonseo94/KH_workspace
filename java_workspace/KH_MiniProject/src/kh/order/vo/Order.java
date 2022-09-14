package kh.order.vo;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Order implements Serializable{
	private static final long serialVersionUID = 1L;
	DecimalFormat df = new DecimalFormat("###,###");
   protected String menuName;
   protected int menuNum;
   protected String spicy;
   protected int price;
   
   
   public Order() {
   }


   public Order(String menuName, int menuNum, String spicy, int price) {
      this.menuName = menuName;
      this.menuNum = menuNum;
      this.spicy = spicy;
      this.price = price;
   }
   
   public Order(String menuName, int menuNum, int price) {
      this.menuName = menuName;
      this.menuNum = menuNum;
      this.price = price;
   }

   public String getMenuName() {
      return menuName;
   }


   public void setMenuName(String menuName) {
      this.menuName = menuName;
   }


   public int getMenuNum() {
      return menuNum;
   }


   public void setMenuNum(int menuNum) {
      this.menuNum = menuNum;
   }


   public String getSpicy() {
      return spicy;
   }
   
   
   public void setSpicy(String spicy) {
      this.spicy = spicy;
   }

   public int getPrice() {
      return price;
   }


   public void setPrice(int price) {
      this.price = price;
   }


   public String getOrderList() {
	   if(spicy == null) {
		   return menuName + ", " + price + " 원";
	   }else {
		   return menuName + "( 맵기 : " + spicy + "), " + df.format(price) + " 원";
	   }
      
   }
   
   
   
}