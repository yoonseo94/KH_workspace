package kh.order.vo.menu;

import kh.order.vo.Order;

public class Pizza extends Order {

   public Pizza(String menuName, int menuNum, int price) {
          super(menuName, menuNum, price);
       }

   public Pizza() {
          
   }

   public void menuSet(int menuNum) {
	   this.menuNum = menuNum;
	   
      if (menuNum == 1) {
         this.menuName = "마르게리따";
         this.price = 14_000;

      } else if (menuNum == 2) {
         this.menuName = "불고기 피자";
         this.price = 14_000;

      } else if (menuNum == 3) {
         this.menuName = "포테이토 피자";
         this.price = 15_000;

      } else if (menuNum == 4) {
         this.menuName = "고구마 피자";
         this.price = 15_000;
         
      } else if (menuNum == 5) {
         this.menuName = "고르곤졸라";
         this.price = 14_000;
      }
   }
}