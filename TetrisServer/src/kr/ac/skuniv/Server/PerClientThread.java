package kr.ac.skuniv.Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import kr.ac.skuniv.Model.ServerModel;

public class PerClientThread extends Thread {
	/***************************************************************/
	// 멤버 필드
	static List<PrintWriter> list = Collections.synchronizedList(new ArrayList<PrintWriter>());
	Socket socket;
	PrintWriter writer;
	ServerModel serverModel;
	/***************************************************************/
	// PerClientThread 생성자
	PerClientThread(Socket socket, ServerModel serverModel) {
		this.socket = socket;
		this.serverModel = serverModel;
		try {
			writer = new PrintWriter(socket.getOutputStream());
			list.add(writer);
		} catch(Exception e) {
			System.out.println("error: " + e.getMessage());
		}
	}
	/***************************************************************/
	// run 메소드
	public void run() {
		String userName = null;
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			/***************************************************************/
			// 
			userName = reader.readLine();
			System.out.println("userName : " + userName);
			if(serverModel.getNameState() == false) {
				serverModel.setUserName1(userName);
				serverModel.setNameState(true);
				sendAll("wait");
			}
			else {
				serverModel.setUserName2(userName);
				serverModel.setStartState(1);
				serverModel.setNameState(false);
				sendAll("wait");
			}
			/***************************************************************/
			// 
			if(serverModel.getStartState() == 1) {
				System.out.println("Game Start");
				sendAll("start");
				serverModel.setStartState(2);
			}
			else {
				System.out.println("UserName : " + userName);
			}
			/***************************************************************/
			// 
			while(true) {
				String str = reader.readLine();
				System.out.println("msg : " + str);  // str 확인
				sendAll(str);
				if(str == "ClientAwin")
					sendAll("ClientAwin");
				else if(str == "ClientBwin")
					sendAll("ClientBwin");
				else if(str == null)
					break;
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			list.remove(writer);
			sendAll("#" + userName + "님이 나가셨습니다.");
			try {
				socket.close();
			} catch(Exception ignored) {
				
			}
		}
	}
	/***************************************************************/
	// sendAll 메소드 : 모든 클라이언트에게 메세지 전송
	private void sendAll(String str) {
		for(PrintWriter writer : list) {
			writer.println(str);
			writer.flush();
		}
	}
}
