package kr.ac.skuniv.Client;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import kr.ac.skuniv.Model.MultiModel;
import kr.ac.skuniv.Model.SoundModel;
import kr.ac.skuniv.View.MultiView;

public class SenderThread extends Thread {
	/***************************************************************/
	// 멤버 필드
	Socket socket;
	PrintWriter writer;
	String name;
	MultiModel multiModel;
	MultiView multiView;
	/***************************************************************/
	// SenderThread 생성자
	public SenderThread(Socket socket, String name, MultiModel multiModel, PrintWriter writer, MultiView multiView) {
		this.socket = socket;
		this.name = name;
		this.multiModel = multiModel;
		this.writer = writer;
		this.multiView = multiView;
	}
	/***************************************************************/
	// run 메소드
	public void run() {
		try {
			writer.println(name);
			writer.flush();
			while(!multiModel.getIsOver()) {
				if (multiModel.getCheckSpace() == 0)
					Thread.sleep(1000);
				else
					Thread.sleep(10);
				multiModel.checkBlockLast(multiModel.getNowBlock(), multiModel.getTurnNum());
				if(multiModel.getIsStart()){
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
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if(name.equals("ClientA")) {
				writer.println("clientBwin");
				writer.flush();
			}
			else  {
				writer.println("clientAwin");
				writer.flush();
			}
			try {
				socket.close();
			}
			catch(Exception ignored) {
				
			}
		}
	}
}
