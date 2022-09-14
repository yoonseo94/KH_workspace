package kh.java.pickmeup.run;

import java.util.Scanner;

import kh.java.pickmeup.controller.GameManager;

public class Run {
	Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		GameManager gameManager = new GameManager();
		gameManager.Intro();
	}
}