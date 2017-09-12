package eu.ase;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		ServerSocket server=null;
		Socket client=null;
		boolean listening=true;
		try{
			server=new ServerSocket(8000);
			while(listening){
				try{
					client=server.accept();
					ClientThread clientAtServer=new ClientThread(client);
					clientAtServer.start();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(server!=null){
				try{
					server.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
}
class ClientThread extends Thread{
	private Socket client;

	public ClientThread(Socket client) {
		this.client = client;
	}
	@Override
	public void run(){
		ObjectOutputStream out=null;
		ObjectInputStream in=null;
		try{
			in=new ObjectInputStream(client.getInputStream());
			Object obj=in.readObject();
			if(obj instanceof StreamAudio){
				StreamAudio request=(StreamAudio)obj;
				System.out.println("Client request to server: "+request);
			}
			else if(obj instanceof StreamVideo){
				StreamVideo request=(StreamVideo)obj;
				System.out.println("Client request to server: "+request);
			}
			
			out=new ObjectOutputStream(client.getOutputStream());
			out.writeObject(obj);
			
			in.close();out.close();client.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}