package kh.order.vo.menu;
import kh.order.vo.Order;

public class Risotto extends Order {

   public Risotto(String menuName, int menuNum, String spicy, int price) {
          super(menuName, menuNum, spicy, price);
       }

   public Risotto() {
          
       }

   public void menuSet(int menuNum) {
	   this.menuNum = menuNum;
      if (menuNum == 1) {
         this.menuName = "게살 로제 리조또";
         this.price = 12_000;

      } else if (menuNum == 2) {
         this.menuName = "크림 리조또";
         this.price = 11_000;

      } else if (menuNum == 3) {
         this.menuName = "해산물 토마토 리조또";
         this.price = 13_000;

      } else if (menuNum == 4) {
         this.menuName = "불고기 리조또";
         this.price = 12_000;
         
      } 
   }

}