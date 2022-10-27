import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class ExecuteClient {
	
	public static void main(String[] args) {
		
		final String HOST = "192.168.0.107";
		final int PORT = 8081;	
		DataInputStream dataInputStream = null;
		DataOutputStream dataOutputStream = null;		
		
		
		try {
			Socket sc = new Socket(HOST, PORT);
			System.out.println("Client Started");
			
			
			while(true) {				
				
				System.out.println("Client Connected");
				
				dataInputStream = new DataInputStream(sc.getInputStream());
				dataOutputStream = new DataOutputStream(sc.getOutputStream());
				
				//dataOutputStream.writeUTF("Calling server to save the transacction in the log server");
				
				dataOutputStream.writeUTF(new Gson().toJson(new Persona("PruebaName", "PruebaLastName", "34", "Argentina")));
				
				String message = dataInputStream.readUTF();
				
				System.out.println("Received Message from server= " + message);
				
				
				
				sc.close();
				
				System.out.println("Client Disconnected");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
