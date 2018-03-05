package kr.ac.skuniv.View;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import kr.ac.skuniv.Model.MultiModel;

public class MultiView extends JPanel {
	/***************************************************************/
	// 멤버변수
	MultiModel multiModel;
	String name;
	ImageIcon back;
	ImageIcon[] bar = new ImageIcon[7];
	/***************************************************************/
	// MultiView 생성자
	public MultiView(MultiModel multiModel, String name) {
		this.multiModel = multiModel;
		this.name = name;
		back = new ImageIcon("image/multi_play.png");
		bar[0] = new ImageIcon("image/bar1.png");
		bar[1] = new ImageIcon("image/bar2.png");
		bar[2] = new ImageIcon("image/bar3.png");
		bar[3] = new ImageIcon("image/bar4.png");
		bar[4] = new ImageIcon("image/bar5.png");
		bar[5] = new ImageIcon("image/bar6.png");
		bar[6] = new ImageIcon("image/bar7.png");
	}
	/***************************************************************/
	// paint 메소드 : multiplay 화면을 그려줌
	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight()); 
		g.drawImage(back.getImage(), 0, 0, 1200, 901, null);
		g.setFont(new Font("Courier", Font.BOLD, 40));
		g.drawString(name, 745, 85);
		if(name.equals("ClientA"))
			g.drawString("ClientB", 318, 85);
		else
			g.drawString("ClientA", 318, 85);
		paintGameScore(g);
		paintGameScore2(g);
		paintNextBlock(g);
		paintNextBlock2(g);
		paintBlock(g);
		paintBlock2(g);
		paintBoard(g);
		paintBoard2(g);
		setOpaque(false); 
	}
	/***************************************************************/
	// paintBlock 메소드 : block을 그려줌
	public void paintBlock(Graphics g) {
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++)
				if(multiModel.getBlockArr()[multiModel.getTurnNum()][multiModel.getNowBlock()][i][j] > 0)
					g.drawImage(bar[multiModel.getNowBlock()].getImage(), multiModel.getNowBlockX() + (j * multiModel.getBlockSize()), multiModel.getNowBlockY() + (i * multiModel.getBlockSize()), multiModel.getBlockSize(), multiModel.getBlockSize(), null);
	}
	public void paintBlock2(Graphics g) {
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++)
				if(multiModel.getBlockArr()[multiModel.getTurnNum2()][multiModel.getNowBlock2()][i][j] > 0)
					g.drawImage(bar[multiModel.getNowBlock2()].getImage(), multiModel.getStartBlockX2() + (multiModel.getNowRow2() * multiModel.getBlockSize()) + (j * multiModel.getBlockSize()), multiModel.getStartBlockY() + (multiModel.getNowCol2() * multiModel.getBlockSize()) + (i * multiModel.getBlockSize()), multiModel.getBlockSize(), multiModel.getBlockSize(), null);
	}
	/***************************************************************/
	// paintBoard 메소드 : 테트리스 게임판을 그려줌
	public void paintBoard(Graphics g) {
		for(int i = 0; i < multiModel.getBoardY(); i++)
			for(int j = 0; j < multiModel.getBoardX(); j++)
				if(multiModel.getGameBoard()[i][j] > 0)
					g.drawImage(bar[multiModel.getGameBoard()[i][j] - 1].getImage(), multiModel.getStartBlockX() + (j * multiModel.getBlockSize()), multiModel.getStartBlockY() + (i * multiModel.getBlockSize()), multiModel.getBlockSize(), multiModel.getBlockSize(), null);
	}
	public void paintBoard2(Graphics g) {
		for(int i = 0; i < multiModel.getBoardY(); i++)
			for(int j = 0; j < multiModel.getBoardX(); j++)
				if(multiModel.getGameBoard3()[i][j] > 0)
					g.drawImage(bar[multiModel.getGameBoard3()[i][j] - 1].getImage(), multiModel.getStartBlockX2() + (j * multiModel.getBlockSize()), multiModel.getStartBlockY() + (i * multiModel.getBlockSize()), multiModel.getBlockSize(), multiModel.getBlockSize(), null);
	}
	/***************************************************************/
	// paintNextBlock 메소드 : 다음 block을 그려줌
	public void paintNextBlock(Graphics g) {
		int x = multiModel.getNextBlock();
		int startx = 1035, starty = 195;
		switch(x) {
		case 0:
			startx += 12;
			starty += 15;
			break;
		case 1:
			startx -= 20;
			starty += 50;
			break;
		case 2:
			startx -= 10;
			starty += 60;
			break;
		case 3: case 4:
			startx -= 5;
			starty += 25;
			break;
		case 5: case 6:
			startx -= 15;
			starty += 30;
			break;
		}
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++)
				if(multiModel.getBlockArr()[0][x][i][j] > 0)
				g.drawImage(bar[x].getImage(), startx + (j * multiModel.getBlockSize()), starty + (i * multiModel.getBlockSize()), multiModel.getBlockSize(), multiModel.getBlockSize(), null);
	}
	public void paintNextBlock2(Graphics g) {
		int x = multiModel.getNextBlock2();
		int startx = 30, starty = 195;
		switch(x) {
		case 0:
			startx += 12;
			starty += 15;
			break;
		case 1:
			startx -= 20;
			starty += 50;
			break;
		case 2:
			startx -= 10;
			starty += 60;
			break;
		case 3: case 4:
			startx -= 5;
			starty += 25;
			break;
		case 5: case 6:
			startx -= 15;
			starty += 30;
			break;
		}
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++)
				if(multiModel.getBlockArr()[0][x][i][j] > 0)
				g.drawImage(bar[x].getImage(), startx + (j * multiModel.getBlockSize()), starty + (i * multiModel.getBlockSize()), multiModel.getBlockSize(), multiModel.getBlockSize(), null);
	}
	/***************************************************************/
	// paintGameScore 메소드 : gamescore를 그려줌
	public void paintGameScore(Graphics g) {
		if(multiModel.getGameScore() == 0) {
			g.setFont(new Font("Courier", Font.BOLD, 60));
			g.drawString(Integer.toString(multiModel.getGameScore()), 1100, 790);
		}
		else if (multiModel.getGameScore() > 0 && multiModel.getGameScore() < 1000){
			g.setFont(new Font("Courier", Font.BOLD, 40));
			g.drawString(Integer.toString(multiModel.getGameScore()), 1075, 790);
		}
		else {
			g.setFont(new Font("Courier", Font.BOLD, 40));
			g.drawString(Integer.toString(multiModel.getGameScore()), 1070, 790);
		}
	}
	public void paintGameScore2(Graphics g) {
		if(multiModel.getGameScore2() == 0) {
			g.setFont(new Font("Courier", Font.BOLD, 60));
			g.drawString(Integer.toString(multiModel.getGameScore2()), 90, 790);
		}
		else if (multiModel.getGameScore2() > 0 && multiModel.getGameScore2() < 1000){
			g.setFont(new Font("Courier", Font.BOLD, 40));
			g.drawString(Integer.toString(multiModel.getGameScore2()), 68, 790);
		}
		else {
			g.setFont(new Font("Courier", Font.BOLD, 40));
			g.drawString(Integer.toString(multiModel.getGameScore2()), 62, 790);
		}
	}
}
