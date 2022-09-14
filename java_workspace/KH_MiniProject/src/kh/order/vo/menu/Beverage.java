package kh.order.vo.menu;
import kh.order.vo.Order;

public class Beverage extends Order {

   public Beverage(String menuName, int menuNum, int price) {
      super(menuName, menuNum, price);
   }

   public Beverage() {

   }

   public void menuSet(int menuNum) {
	   this.menuNum = menuNum;
	   
      if (menuNum == 1) {
         this.menuName = "콜라";
         this.price = 2_000;

      } else if (menuNum == 2) {
         this.menuName = "사이다";
         this.price = 2_000;

      } else if (menuNum == 3) {
         this.menuName = "레몬 에이드";
         this.price = 4_000;

      } else if (menuNum == 4) {
         this.menuName = "청포도 에이드";
         this.price = 4_000;

      } else if (menuNum == 5) {
         this.menuName = "자몽 에이드";
         this.price = 4_000;

      }
   }

}