package kr.ac.skuniv.Server;

import java.net.ServerSocket;
import java.net.Socket;

import kr.ac.skuniv.Model.ServerModel;

public class MainServer {
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		ServerModel serverModel = new ServerModel();
		try {
			serverSocket = new ServerSocket(8050);
			while(true) {
				Socket socket = serverSocket.accept();
				Thread thread = new PerClientThread(socket, serverModel);
				thread.start();
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
