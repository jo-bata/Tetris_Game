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
	// ��� �ʵ�
	static List<PrintWriter> list = Collections.synchronizedList(new ArrayList<PrintWriter>());
	Socket socket;
	PrintWriter writer;
	ServerModel serverModel;
	/***************************************************************/
	// PerClientThread ������
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
	// run �޼ҵ�
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
				System.out.println("msg : " + str);  // str Ȯ��
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
			sendAll("#" + userName + "���� �����̽��ϴ�.");
			try {
				socket.close();
			} catch(Exception ignored) {
				
			}
		}
	}
	/***************************************************************/
	// sendAll �޼ҵ� : ��� Ŭ���̾�Ʈ���� �޼��� ����
	private void sendAll(String str) {
		for(PrintWriter writer : list) {
			writer.println(str);
			writer.flush();
		}
	}
}
