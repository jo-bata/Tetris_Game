package kr.ac.skuniv.Controller;

import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import kr.ac.skuniv.Model.SingleModel;
import kr.ac.skuniv.Model.SoundModel;
import kr.ac.skuniv.View.SingleView;

public class SingleController implements Runnable, KeyListener {
	/***************************************************************/
	// 멤버필드
	SingleModel singleModel;
	SingleView singleView;
	JFrame frame;
	Container contentPane;
	SoundModel soundModel;
	/***************************************************************/
	// SingleController 생성자
	public SingleController(SingleModel singleModel, SingleView singleView, JFrame frame, Container contentPane, SoundModel soundModel) {
		this.singleModel = singleModel;
		this.singleView = singleView;
		this.frame = frame;
		this.contentPane = contentPane;
		this.soundModel = soundModel;
		singleModel.setRandomBlock();  // 현재, 다음 블럭 랜덤으로 셋팅
	}
	/***************************************************************/
	// KeyListener 오버라이딩
	@Override
	public void keyPressed(KeyEvent e) {
		if(singleModel.isMove(singleModel.getNowCol(), singleModel.getNowRow(), singleModel.getNowBlock(), singleModel.getTurnNum(), e.getKeyCode())) {
			switch(e.getKeyCode()) {
				case KeyEvent.VK_UP:
					singleModel.setTurnNum(singleModel.getTurnNum() + 1);
					soundModel.moveBlockPlay();
					break;
				case KeyEvent.VK_LEFT:
					singleModel.setNowBlockX(singleModel.getNowBlockX() - singleModel.getBlockSize());
					singleModel.setNowRow(singleModel.getNowRow() - 1);
					break;
				case KeyEvent.VK_RIGHT:
					singleModel.setNowBlockX(singleModel.getNowBlockX() + singleModel.getBlockSize());
					singleModel.setNowRow(singleModel.getNowRow() + 1);
					break;
				case KeyEvent.VK_DOWN:
					singleModel.setNowBlockY(singleModel.getNowBlockY() + singleModel.getBlockSize());
					singleModel.setNowCol(singleModel.getNowCol() + 1);
					break;
				case KeyEvent.VK_SPACE:
					singleModel.setCheckSpace(1);
					soundModel.menuClickPlay();
					break;
			}
			singleView.repaint();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void run() {
		while(!singleModel.getIsOver()) {
			try {
				if(singleModel.getCheckSpace() == 0)
					Thread.sleep(1000 - (singleModel.getGameStage() * 100));
				else
					Thread.sleep(10);
				System.out.println("nowBlock : " + singleModel.getNowBlock() + ", turnNum : " + singleModel.getTurnNum() + ", nowCol : " + singleModel.getNowCol() + ", nowRow : " + singleModel.getNowRow());
				singleModel.checkBlockLast(singleModel.getNowBlock(), singleModel.getTurnNum());
				singleView.repaint();
			} catch(InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
		soundModel.losePlay();
		int yes = JOptionPane.showConfirmDialog(frame, "Game Over! 게임을 종료합니다.", "패배!",
				JOptionPane.YES_OPTION);
		if (yes == JOptionPane.YES_OPTION)
			System.exit(1);
	}
}
