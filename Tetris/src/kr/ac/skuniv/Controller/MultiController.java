package kr.ac.skuniv.Controller;

import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;

import kr.ac.skuniv.Model.MultiModel;
import kr.ac.skuniv.Model.SoundModel;
import kr.ac.skuniv.View.MultiView;

public class MultiController implements KeyListener {
	/***************************************************************/
	// 멤버필드
	MultiModel multiModel;
	MultiView multiView;
	JFrame frame;
	Container contentPane;
	PrintWriter writer;
	String name;
	SoundModel soundModel;
	/***************************************************************/
	// MultiController 생성자
	public MultiController(MultiModel multiModel, MultiView multiView, JFrame frame, Container contentPane, PrintWriter writer, String name, SoundModel soundModel) {
		this.multiModel = multiModel;
		this.multiView = multiView;
		this.frame = frame;
		this.contentPane = contentPane;
		this.writer = writer;
		this.name = name;
		this.soundModel = soundModel;
		multiModel.setRandomBlock();
	}
	/***************************************************************/
	// KeyListener 오버라이딩
	@Override
	public void keyPressed(KeyEvent e) {
		if (multiModel.isMove(multiModel.getNowCol(), multiModel.getNowRow(), multiModel.getNowBlock(),
				multiModel.getTurnNum(), e.getKeyCode())) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				multiModel.setTurnNum(multiModel.getTurnNum() + 1);
				soundModel.moveBlockPlay();
				break;
			case KeyEvent.VK_LEFT:
				multiModel.setNowBlockX(multiModel.getNowBlockX() - multiModel.getBlockSize());
				multiModel.setNowRow(multiModel.getNowRow() - 1);
				break;
			case KeyEvent.VK_RIGHT:
				multiModel.setNowBlockX(multiModel.getNowBlockX() + multiModel.getBlockSize());
				multiModel.setNowRow(multiModel.getNowRow() + 1);
				break;
			case KeyEvent.VK_DOWN:
				multiModel.setNowBlockY(multiModel.getNowBlockY() + multiModel.getBlockSize());
				multiModel.setNowCol(multiModel.getNowCol() + 1);
				break;
			case KeyEvent.VK_SPACE:
				multiModel.setCheckSpace(1);
				soundModel.menuClickPlay();
				break;
			}
			HashMap<String, Object> data = new HashMap<String, Object>();
			data.put("name", name);
			data.put("nowBlock", multiModel.getNowBlock());
			data.put("nextBlock", multiModel.getNextBlock());
			data.put("turnNum", multiModel.getTurnNum());
			data.put("nowRow", multiModel.getNowRow());
			data.put("nowCol", multiModel.getNowCol());
			data.put("gameScore", multiModel.getGameScore());
			ArrayList<Integer> arr = new ArrayList<Integer>();
			for (int i = 0; i < multiModel.getBoardY(); i++)
				for (int j = 0; j < multiModel.getBoardX(); j++)
					arr.add(multiModel.getGameBoard()[i][j]);
			data.put("map", arr);
			writer.println(data);
			writer.flush();
			multiView.repaint();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
