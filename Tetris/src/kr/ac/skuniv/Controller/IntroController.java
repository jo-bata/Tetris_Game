package kr.ac.skuniv.Controller;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JTextField;

import kr.ac.skuniv.Client.ReceiverThread;
import kr.ac.skuniv.Client.SenderThread;
import kr.ac.skuniv.Model.IntroModel;
import kr.ac.skuniv.Model.MultiModel;
import kr.ac.skuniv.Model.SingleModel;
import kr.ac.skuniv.Model.SoundModel;
import kr.ac.skuniv.View.IntroView;
import kr.ac.skuniv.View.MultiView;
import kr.ac.skuniv.View.SingleView;

public class IntroController implements MouseListener, MouseMotionListener {
	/***************************************************************/
	// 멤버변수
	IntroModel introModel;
	IntroView introView;
	JFrame frame;
	JTextField nameField;
	Container contentPane;
	SingleModel singleModel;
	SingleView singleView;
	SingleController singleController;
	MultiModel multiModel;
	MultiView multiView;
	Socket socket;
	PrintWriter writer;
	SoundModel soundModel;
	String name;
	/***************************************************************/
	// IntroController 생성자
	public IntroController(IntroModel introModel, IntroView introView, JFrame frame, Container contentPane, SoundModel soundModel) {
		this.introModel = introModel;
		this.introView = introView;
		this.frame = frame;
		this.contentPane = contentPane;
		nameField = new JTextField("     게   임   시   작   대   기   중");
		singleModel = new SingleModel(soundModel);
		singleView = new SingleView(singleModel);
		singleController = new SingleController(singleModel, singleView, frame, contentPane, soundModel);
		name = "ClientA";
		this.soundModel = soundModel;
		multiModel = new MultiModel(soundModel);
		multiView = new MultiView(multiModel, name);
		try {
			socket = new Socket("192.168.0.9", 8050);
			writer = new PrintWriter(socket.getOutputStream());
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	/***************************************************************/
	// MouseListner, MouseMotionListener 오버라이딩
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		System.out.println("x : " + e.getX() + ", y : " + e.getY() + ", " + introModel.getCheckClicked());
		/***************************************************************/
		// SINGLE PLAY & MULTI PLAY
		if(introModel.getCheckClicked() > 0) {
			if((x >= introModel.getSelect_gameX() && x <= introModel.getSelect_gameX() + introModel.getSelect_gameWidth()) && (y >= introModel.getSelect_gameY() && y <= introModel.getSelect_gameY() + introModel.getSelect_gameHeight())) {
				Thread singleThread = new Thread(singleController);
				singleThread.start();
				soundModel.intoBgmStop();
				soundModel.menuClickPlay();
				contentPane.remove(introView);
				contentPane.add(singleView);
				frame.addKeyListener(singleController);
				frame.setSize(1200 + 15, 901 + 35);
				frame.setLocation(200, 0);
				frame.setVisible(true);
			}
			else if((x >= introModel.getSelect_gameX() && x <= introModel.getSelect_gameX() + introModel.getSelect_gameWidth()) && (y >= introModel.getSelect_gameY() + introModel.getSelect_gameHeight() + introModel.getSelect_gameInterval() && y <= introModel.getSelect_gameY() + (introModel.getSelect_gameHeight() * 2) + introModel.getSelect_gameInterval())) {
				introModel.setCheckMulti(1);
				introView.repaint();
				introView.add(nameField);
				nameField.setLocation(240, 600);
				nameField.setSize(330, 60);
				nameField.setFont(new Font("Courier", Font.BOLD, 22));
				introView.removeMouseMotionListener(this);
			}
			else if((x >= 300 && x <= 500) && (y >= 680 && y <= 730) && (introModel.getCheckClicked() == 3)) {
				Thread senderThread = new SenderThread(socket, name, multiModel, writer, multiView);
				Thread receiverThread = new ReceiverThread(socket, writer, introView, multiModel, multiView, contentPane, frame, name, soundModel);
				senderThread.start();
				receiverThread.start();
			}
		}
		/***************************************************************/
		// GAME START
		if((x >= introModel.getMenuX() && x <= introModel.getMenuX() + introModel.getMenuWidth()) && (y >= introModel.getMenuY() && y <= introModel.getMenuY() + introModel.getMenuHeight()))
			introModel.setCheckClicked(1);
		if((x >= introModel.getMenuX() && x <= introModel.getMenuX() + introModel.getMenuWidth()) && (y >= introModel.getMenuY() && y <= introModel.getMenuY() + introModel.getMenuHeight() * 3) || (introModel.getCheckClicked() == 3));
		else introModel.setCheckClicked(0);
		/***************************************************************/
		// HOW TO PLAY
		if((x >= introModel.getMenuX() && x <= introModel.getMenuX() + introModel.getMenuWidth()) && (y >= introModel.getMenuY() + introModel.getMenuHeight() && y <= introModel.getMenuY() + introModel.getMenuHeight() + introModel.getMenuInterval())) {
			
		}
		/***************************************************************/
		// RANKING
		if((x >= introModel.getMenuX() && x <= introModel.getMenuX() + introModel.getMenuWidth()) && (y >= introModel.getMenuY() + introModel.getMenuHeight() + introModel.getMenuInterval() && y <= introModel.getMenuY() + introModel.getMenuHeight() + (2 * introModel.getMenuInterval()))) {
			
		}
		/***************************************************************/
		// GAME EXIT
		if((x >= introModel.getMenuX() && x <= introModel.getMenuX() + introModel.getMenuWidth()) && (y >= introModel.getMenuY() + introModel.getMenuHeight() + (2 * introModel.getMenuInterval()) && y <= introModel.getMenuY() + introModel.getMenuHeight() + (3 * introModel.getMenuInterval()))) {
			System.exit(1);
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseDragged(MouseEvent e) {}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		/***************************************************************/
		// Menu들 옆에 ● 표시
		if((x >= introModel.getMenuX() && x <= introModel.getMenuX() + introModel.getMenuWidth()) && (y >= introModel.getMenuY() && y <= introModel.getMenuY() + (4 * introModel.getMenuHeight()) + (3 * introModel.getMenuInterval()))) {
			if((x >= introModel.getMenuX() && x <= introModel.getMenuX() + introModel.getMenuWidth()) && (y >= introModel.getMenuY() && y <= introModel.getMenuY() + introModel.getMenuHeight()))
				introModel.setCheckEntered(1);
			if((x >= introModel.getMenuX() && x <= introModel.getMenuX() + introModel.getMenuWidth()) && (y >= introModel.getMenuY() + introModel.getMenuHeight() && y <= introModel.getMenuY() + introModel.getMenuHeight() + introModel.getMenuInterval()))
				introModel.setCheckEntered(2);
			if((x >= introModel.getMenuX() && x <= introModel.getMenuX() + introModel.getMenuWidth()) && (y >= introModel.getMenuY() + introModel.getMenuHeight() + introModel.getMenuInterval() && y <= introModel.getMenuY() + introModel.getMenuHeight() + (2 * introModel.getMenuInterval())))
				introModel.setCheckEntered(3);
			if((x >= introModel.getMenuX() && x <= introModel.getMenuX() + introModel.getMenuWidth()) && (y >= introModel.getMenuY() + introModel.getMenuHeight() + (2 * introModel.getMenuInterval()) && y <= introModel.getMenuY() + introModel.getMenuHeight() + (3 * introModel.getMenuInterval())))
				introModel.setCheckEntered(4);
		}
		if( (x >= introModel.getMenuX() && x <= introModel.getMenuX() + introModel.getMenuWidth()) && (y >= introModel.getMenuY() && y <= introModel.getMenuY() + introModel.getMenuHeight()) ||
			(x >= introModel.getMenuX() && x <= introModel.getMenuX() + introModel.getMenuWidth()) && (y >= introModel.getMenuY() + introModel.getMenuHeight() && y <= introModel.getMenuY() + introModel.getMenuHeight() + introModel.getMenuInterval()) ||
			(x >= introModel.getMenuX() && x <= introModel.getMenuX() + introModel.getMenuWidth()) && (y >= introModel.getMenuY() + introModel.getMenuHeight() + introModel.getMenuInterval() && y <= introModel.getMenuY() + introModel.getMenuHeight() + (2 * introModel.getMenuInterval())) ||
			(x >= introModel.getMenuX() && x <= introModel.getMenuX() + introModel.getMenuWidth()) && (y >= introModel.getMenuY() + introModel.getMenuHeight() + (2 * introModel.getMenuInterval()) && y <= introModel.getMenuY() + introModel.getMenuHeight() + (3 * introModel.getMenuInterval())))
			;
		else introModel.setCheckEntered(0);
		/***************************************************************/
		// SINGLE PLAY & MULTI PLAY 색칠 
		if(introModel.getCheckClicked() > 0) {
			if((x >= introModel.getSelect_gameX() && x <= introModel.getSelect_gameX() + introModel.getSelect_gameWidth()) && (y >= introModel.getSelect_gameY() && y <= introModel.getSelect_gameY() + introModel.getSelect_gameHeight()))
				introModel.setCheckClicked(2);
			else if((x >= introModel.getSelect_gameX() && x <= introModel.getSelect_gameX() + introModel.getSelect_gameWidth()) && (y >= introModel.getSelect_gameY() + introModel.getSelect_gameHeight() + introModel.getSelect_gameInterval() && y <= introModel.getSelect_gameY() + (introModel.getSelect_gameHeight() * 2) + introModel.getSelect_gameInterval()))
				introModel.setCheckClicked(3);
			else
				introModel.setCheckClicked(1);
		}
		/***************************************************************/
		// repaint
		introView.repaint();
	}
}
