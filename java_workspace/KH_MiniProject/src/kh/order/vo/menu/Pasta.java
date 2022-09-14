package kh.order.vo.menu;

import kh.order.vo.Order;

public class Pasta extends Order {

	   public Pasta(String menuName, int menuNum, String spicy, int price) {
	      super(menuName, menuNum, spicy, price);
	   }

	   public Pasta() {
	      
	   }
	   
	   public void menuSet(int menuNum) {
	      this.menuNum = menuNum;
	      
	      if (menuNum == 1) {
	    	  this.menuName = "로제 파스타";
	    	  this.price = 12_000;

	      } else if (menuNum == 2) {
	    	  this.menuName = "토마토 파스타";
	    	  this.price = 12_000;

	      } else if (menuNum == 3) {
	    	  this.menuName = "알리오올리오";
	    	  this.price = 11_000;

	      } else if (menuNum == 4) {
	    	  this.menuName = "까르보나라";
	    	  this.price = 12_000;
	      }

	   }

	}