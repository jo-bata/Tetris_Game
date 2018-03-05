package kr.ac.skuniv.Client;

import java.awt.Container;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import kr.ac.skuniv.Controller.MultiController;
import kr.ac.skuniv.Model.MultiModel;
import kr.ac.skuniv.Model.SoundModel;
import kr.ac.skuniv.View.IntroView;
import kr.ac.skuniv.View.MultiView;

public class ReceiverThread extends Thread {
	/***************************************************************/
	// 멤버 변수
	Socket socket;
	PrintWriter writer;
	IntroView introView;
	MultiModel multiModel;
	MultiView multiView;
	Container contentPane;
	JFrame frame;
	MultiController multiController;
	String name;
	String name2;
	SoundModel soundModel;
	/***************************************************************/
	// ReceiverThread 생성자
	public ReceiverThread(Socket socket, PrintWriter writer, IntroView introView, MultiModel multiModel,
			MultiView multiView, Container contentPane, JFrame frame, String name, SoundModel soundModel) {
		this.socket = socket;
		this.writer = writer;
		this.introView = introView;
		this.multiModel = multiModel;
		this.multiView = multiView;
		this.contentPane = contentPane;
		this.frame = frame;
		this.name = name;
		this.soundModel = soundModel;
		multiController = new MultiController(multiModel, multiView, frame, contentPane, writer, name, soundModel);
	}
	/***************************************************************/
	// run 메소드
	public void run() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while (!multiModel.getIsOver()) {
				String str = reader.readLine();
				if (str != null && multiModel.getIsStart() == false)
					System.out.println("시작해도되나요? : " + str);
				if (str == null)
					System.out.println("str==null");
				else if (str.equals("ClientAwin")) {
					multiModel.setIsOver(true);
					name2 = "ClientA";
				}
				else if (str.equals("ClientBwin")) {
					multiModel.setIsOver(true);
					name2 = "ClientB";
				}
				else {
					if (multiModel.getIsStart()) {
						String str2 = reader.readLine();
						str2 = str2.substring(1, str2.length() - 1);
						String[] keyValuePairs = str2.split(",");
						HashMap<String, Object> map = new HashMap<String, Object>();
						ArrayList<Integer> arr = new ArrayList<Integer>();
						System.out.println("keyValuePairs : " + keyValuePairs.length);
						for (int i = 0; i < keyValuePairs.length; i++) {
							if (i >= 0 && i <= 6) {
								String pair = keyValuePairs[i].trim();
								String[] entry = pair.split("=");
								map.put(entry[0], entry[1]);
							} else if (i == 7) {
								String pair = keyValuePairs[i].trim();
								String[] entry = pair.split("[=\\[]");
								arr.add(Integer.parseInt(entry[2]));
							} else if (i > 7 && i < 206) {
								arr.add(Integer.parseInt(keyValuePairs[i].trim()));
							} else {
								String pair = keyValuePairs[i].trim();
								String[] entry = pair.split("]");
								arr.add(Integer.parseInt(entry[0]));
							}
						}
						if (!map.get("name").equals(name)) {
							multiModel.setNowBlock2(map.get("nowBlock"));
							multiModel.setNextBlock2(map.get("nextBlock"));
							multiModel.setTurnNum2(map.get("turnNum"));
							multiModel.setNowCol2(map.get("nowCol"));
							multiModel.setNowRow2(map.get("nowRow"));
							multiModel.setGameScore2(map.get("gameScore"));
							multiModel.setGameBoard3(arr);
							multiView.repaint();
						}
						;
					}
					if (str != null)
						if (str.equals("start")) {
							System.out.println("시작");
							soundModel.intoBgmStop();
							soundModel.menuClickPlay();
							contentPane.remove(introView);
							contentPane.add(multiView);
							frame.addKeyListener(multiController);
							frame.setSize(1200 + 15, 901 + 35);
							frame.setLocation(200, 0);
							frame.setVisible(true);
							multiModel.setIsStart(true);
						}
				}
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			if(name.equals(name2)) {
				soundModel.victoryPlay();
				int yes = JOptionPane.showConfirmDialog(frame, name + " 승리! 게임을 종료합니다.", "승리 !",
						JOptionPane.YES_OPTION);
				if (yes == JOptionPane.YES_OPTION)
					System.exit(1);
			}
			else {
				soundModel.losePlay();
				int yes = JOptionPane.showConfirmDialog(frame, name + " 패배! 게임을 종료합니다.", "패배 !",
						JOptionPane.YES_OPTION);
				if (yes == JOptionPane.YES_OPTION)
					System.exit(1);
			}
		}
	}
}
