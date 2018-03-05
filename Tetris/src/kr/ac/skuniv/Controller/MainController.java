package kr.ac.skuniv.Controller;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;

import kr.ac.skuniv.Model.IntroModel;
import kr.ac.skuniv.Model.SoundModel;
import kr.ac.skuniv.View.IntroView;

public class MainController {
	/***************************************************************/
	// 멤버변수
	IntroModel introModel;
	IntroView introView;
	JFrame frame;
	Container contentPane;
	IntroController introController;
	SoundModel soundModel;
	/***************************************************************/
	// MainController 생성자
	public MainController() {
		introModel = new IntroModel();
		introView = new IntroView(introModel);
		frame = new JFrame("TETRIS GAME");
		contentPane = frame.getContentPane();
		soundModel = new SoundModel();
		introController = new IntroController(introModel, introView, frame, contentPane, soundModel);
	}
	/***************************************************************/
	// run 메소드 : 게임 실행
	public void run() {
		contentPane.add(introView);
		soundModel.introBgmPlay();
		introView.addMouseListener(introController);
		introView.addMouseMotionListener(introController);
		frame.setPreferredSize(new Dimension(815, 835));  // 프레임크기 지정 
		frame.setLocation(200,100);  //프레임 실행 위치 설정 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.pack(); 
		frame.setVisible(true);
	}
}
