package kr.ac.skuniv.Model;

public class IntroModel {
	/***************************************************************/
	// ¸â¹öº¯¼ö
	private int introWidth = 800;
	private int introHeight = 800;
	private int menuWidth = 450;
	private int menuHeight = 99;
	private int menuX = 175;
	private int menuY = 280;
	private int menuInterval = 100;
	private int select_gameX = 240;
	private int select_gameY = 325;
	private int select_gameWidth = 320;
	private int select_gameHeight = 85;
	private int select_gameInterval = 40;
	private int checkEntered = 0;
	private int checkClicked = 0;
	private int checkMulti = 0;
	/***************************************************************/
	// getter & setter
	public int getIntroWidth() { return introWidth; }
	public int getIntroHeight() { return introHeight; }
	public int getMenuWidth() { return menuWidth; }
	public int getMenuHeight() { return menuHeight; }
	public int getMenuX() { return menuX; }
	public int getMenuY() {	return menuY;}
	public int getMenuInterval() { return menuInterval; }
	public int getSelect_gameX() { return select_gameX;	}
	public int getSelect_gameY() { return select_gameY;	}
	public int getSelect_gameWidth() { return select_gameWidth; }
	public int getSelect_gameHeight() { return select_gameHeight; }
	public int getSelect_gameInterval() { return select_gameInterval; }
	public int getCheckEntered() { return checkEntered; }
	public void setCheckEntered(int checkEntered) {
		this.checkEntered = checkEntered;
	}
	public int getCheckClicked() { return checkClicked; }
	public void setCheckClicked(int checkClicked) {
		this.checkClicked = checkClicked;
	}
	public int getCheckMulti() { return checkMulti; }
	public void setCheckMulti(int checkMulti) { this.checkMulti = checkMulti; }
}
