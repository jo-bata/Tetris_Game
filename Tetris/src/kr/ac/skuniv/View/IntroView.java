package kr.ac.skuniv.View;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import kr.ac.skuniv.Model.IntroModel;

public class IntroView extends JPanel {
	/***************************************************************/
	// 멤버변수
	IntroModel introModel;
	ImageIcon intro, multi_start;
	ImageIcon[] menu = new ImageIcon[4];
	ImageIcon[] menu_click = new ImageIcon[4];
	ImageIcon[] select_game = new ImageIcon[3];
	/***************************************************************/
	// IntroView 생성자
	public IntroView(IntroModel introModel) {
		this.introModel = introModel;
		intro = new ImageIcon("image/intro.png");
		multi_start = new ImageIcon("image/multi_start.png");
		menu[0] = new ImageIcon("image/menu1.png");
		menu[1] = new ImageIcon("image/menu2.png");
		menu[2] = new ImageIcon("image/menu3.png");
		menu[3] = new ImageIcon("image/menu4.png");
		menu_click[0] = new ImageIcon("image/menu1_click.png");
		menu_click[1] = new ImageIcon("image/menu2_click.png");
		menu_click[2] = new ImageIcon("image/menu3_click.png");
		menu_click[3] = new ImageIcon("image/menu4_click.png");
		select_game[0] = new ImageIcon("image/select_game.png");
		select_game[1] = new ImageIcon("image/select_game_click1.png");
		select_game[2] = new ImageIcon("image/select_game_click2.png");
	}
	/***************************************************************/
	// drawMenu 메소드 : menu들을 그려줌
	public void drawMenu(Graphics g, IntroModel introModel) {
		for(int i = 0; i < menu.length; i++)
			g.drawImage(menu[i].getImage(), introModel.getMenuX(), introModel.getMenuY() + (i * introModel.getMenuInterval()), introModel.getMenuWidth(), introModel.getMenuHeight(),null);
		switch(introModel.getCheckEntered()) { 
			case 1: g.drawImage(menu_click[0].getImage(), introModel.getMenuX(), introModel.getMenuY(), introModel.getMenuWidth(), introModel.getMenuHeight(), null); break; 
			case 2: g.drawImage(menu_click[1].getImage(), introModel.getMenuX(), introModel.getMenuY() + introModel.getMenuInterval(), introModel.getMenuWidth(), introModel.getMenuHeight(), null); break;
			case 3: g.drawImage(menu_click[2].getImage(), introModel.getMenuX(), introModel.getMenuY() + (2 * introModel.getMenuInterval()), introModel.getMenuWidth(), introModel.getMenuHeight(), null); break;
			case 4: g.drawImage(menu_click[3].getImage(), introModel.getMenuX(), introModel.getMenuY() + (3 * introModel.getMenuInterval()), introModel.getMenuWidth(), introModel.getMenuHeight(), null); break;
		}
		switch(introModel.getCheckClicked()) {
			case 1: g.drawImage(select_game[0].getImage(), introModel.getMenuX(), introModel.getMenuY(), introModel.getMenuWidth(),introModel.getMenuHeight() * 3, null); break;
			case 2: g.drawImage(select_game[1].getImage(), introModel.getMenuX(), introModel.getMenuY(), introModel.getMenuWidth(),introModel.getMenuHeight() * 3, null); break;
			case 3: g.drawImage(select_game[2].getImage(), introModel.getMenuX(), introModel.getMenuY(), introModel.getMenuWidth(),introModel.getMenuHeight() * 3, null); break;
		}
		if(introModel.getCheckMulti() == 1)
			g.drawImage(multi_start.getImage(), 300, 680, 200, 50, null);
	}
	/***************************************************************/
	// paint 메소드 : 배경 이미지를 그려줌
	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight()); 
		g.drawImage(intro.getImage(), 0, 0, 800, 800, null);
		drawMenu(g, introModel); 
		setOpaque(false); 
	}
}
