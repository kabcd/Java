package eu.ase;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpClient {
	public static void main(String[] args) {
		DatagramSocket client=null;
		try{
			client=new DatagramSocket();
			String str=new String("Hello!");
			byte[] send_data=str.getBytes();
			DatagramPacket packet=new DatagramPacket(send_data, send_data.length, InetAddress.getLocalHost(), 8000);
			client.send(packet);
			
			byte[] receive_data=new byte[256];
			packet=new DatagramPacket(receive_data, receive_data.length);
			client.receive(packet);
			System.out.println("Server response to client: "+new String(packet.getData()));
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			client.close();
		}
	}
}
