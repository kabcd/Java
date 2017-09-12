package eu.ase;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		ServerSocket server=null;
		Socket client=null;
		boolean listening=true;
		try{
			server=new ServerSocket(8000);
			System.out.println("Server Waiting!");
			while(listening){
				try{
					client=server.accept();
					ClientThread clientAtServer=new ClientThread(client);
					clientAtServer.start();
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(server!=null){
				try{
					server.close();
				}catch(Exception exp){
					exp.printStackTrace();
				}
			}
		}
	}
}
