package eu.ase;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpServer {
	public static void main(String[] args) {
		DatagramSocket server=null;
		try{
			server=new DatagramSocket(8000);
			while(true){
				try{
					byte[] receive_data=new byte[256];
					DatagramPacket packet=new DatagramPacket(receive_data, receive_data.length);
					server.receive(packet);
					
					System.out.println("Client request to server: "+new String(packet.getData()));
					
					String str=new String("OK!");
					byte[] send_data=str.getBytes();
					packet=new DatagramPacket(send_data, send_data.length, packet.getAddress(), packet.getPort());
					server.send(packet);
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
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}
	}
}
