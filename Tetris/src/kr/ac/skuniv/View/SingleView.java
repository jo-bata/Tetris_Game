package kr.ac.skuniv.View;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import kr.ac.skuniv.Model.SingleModel;

public class SingleView extends JPanel {
	/***************************************************************/
	// 멤버변수
	SingleModel singleModel;
	ImageIcon back;
	ImageIcon[] bar = new ImageIcon[7];
	/***************************************************************/
	// SingleView 생성자
	public SingleView(SingleModel singleModel) {
		this.singleModel = singleModel;
		back = new ImageIcon("image/single_play.png");
		bar[0] = new ImageIcon("image/bar1.png");
		bar[1] = new ImageIcon("image/bar2.png");
		bar[2] = new ImageIcon("image/bar3.png");
		bar[3] = new ImageIcon("image/bar4.png");
		bar[4] = new ImageIcon("image/bar5.png");
		bar[5] = new ImageIcon("image/bar6.png");
		bar[6] = new ImageIcon("image/bar7.png");
	}
	/***************************************************************/
	// paint 메소드 : singleplay 화면을 그려줌
	public void paint(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight()); 
		g.drawImage(back.getImage(), 0, 0, 1200, 901, null);
		g.setFont(new Font("Courier", Font.BOLD, 100));
		g.drawString(Integer.toString(singleModel.getGameStage()), 190, 770);
		paintGameScore(g);
		paintNextBlock(g);
		paintBlock(g);
		paintBoard(g);
		setOpaque(false); 
	}
	/***************************************************************/
	// paintBlock 메소드 : block을 그려줌
	public void paintBlock(Graphics g) {
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++)
				if(singleModel.getBlockArr()[singleModel.getTurnNum()][singleModel.getNowBlock()][i][j] > 0)
					g.drawImage(bar[singleModel.getNowBlock()].getImage(), singleModel.getNowBlockX() + (j * singleModel.getBlockSize()), singleModel.getNowBlockY() + (i * singleModel.getBlockSize()), singleModel.getBlockSize(), singleModel.getBlockSize(), null);
	}
	/***************************************************************/
	// paintBoard 메소드 : 테트리스 게임판을 그려줌
	public void paintBoard(Graphics g) {
		for(int i = 0; i < singleModel.getBoardY(); i++)
			for(int j = 0; j < singleModel.getBoardX(); j++)
				if(singleModel.getGameBoard()[i][j] > 0)
					g.drawImage(bar[singleModel.getGameBoard()[i][j] - 1].getImage(), singleModel.getStartBlockX() + (j * singleModel.getBlockSize()), singleModel.getStartBlockY() + (i * singleModel.getBlockSize()), singleModel.getBlockSize(), singleModel.getBlockSize(), null);
	}
	public void paintNextBlock(Graphics g) {
		int x = singleModel.getNextBlock();
		int startx = 890, starty = 140;
		switch(x) {
		case 0:
			startx += 20;
			starty -= 5;
			break;
		case 1:
			startx -= 10;
			starty += 20;
			break;
		case 2:
			startx += 5;
			starty += 30
					;
			break;
		case 3: case 4:
			startx += 5;
			starty += 10;
			break;
		case 5: case 6:
			startx += 5;
			starty += 10;
			break;
		}
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++)
				if(singleModel.getBlockArr()[0][x][i][j] > 0)
				g.drawImage(bar[x].getImage(), startx + (j * singleModel.getBlockSize()), starty + (i * singleModel.getBlockSize()), singleModel.getBlockSize(), singleModel.getBlockSize(), null);
	}
	public void paintGameScore(Graphics g) {
		if(singleModel.getGameScore() == 0) {
			g.setFont(new Font("Courier", Font.BOLD, 60));
			g.drawString(Integer.toString(singleModel.getGameScore()), 960, 760);
		}
		else if (singleModel.getGameScore() > 0 && singleModel.getGameScore() < 1000){
			g.setFont(new Font("Courier", Font.BOLD, 40));
			g.drawString(Integer.toString(singleModel.getGameScore()), 935, 760);
		}
		else {
			g.setFont(new Font("Courier", Font.BOLD, 40));
			g.drawString(Integer.toString(singleModel.getGameScore()), 930, 760);
		}
	}
}
