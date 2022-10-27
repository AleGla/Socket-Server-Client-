package com.example.demo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {

	
	@PostMapping(value = "socket",
			consumes = "application/json",
			produces = "application/json")
	public ResponseEntity<String> saveInfoForSocket(@RequestBody Person p){
		
		sendDataToSocket(p);
		
		
		return new ResponseEntity<String>(responseJSON(p), HttpStatus.OK);
	}
	
	
	public String responseJSON(Object obj) {
		
		JSONObject json = new JSONObject();
		
		json.put("status", "OK");
		json.put(obj.getClass().getSimpleName(), new JSONObject(obj));
		
		return json.toString();
	}
	
	
	public void sendDataToSocket(Object obj) {
		final String HOST = "192.168.0.107";
		final int PORT = 8081;	
		DataInputStream dataInputStream = null;
		DataOutputStream dataOutputStream = null;		
		boolean isOpen = true;
		
		
		try {
			Socket sc = new Socket(HOST, PORT);
			System.out.println("Client Started");
			
			
						
				System.out.println("Client Connected");
				
				dataInputStream = new DataInputStream(sc.getInputStream());
				dataOutputStream = new DataOutputStream(sc.getOutputStream());
				
				//dataOutputStream.writeUTF("Calling server to save the transacction in the log server");
				
				dataOutputStream.writeUTF(new JSONObject(obj).toString());
				
				//while(sc.isConnected() && !sc.isInputShutdown()) {					
					while(isOpen) {
						String message = dataInputStream.readUTF();
						
						if(message.equals("PROCESSING")) {
							System.out.println("STATUS -> ◍");
							
						}else if(message.equals("DONE")) {
							System.out.println("STATUS -> ✓");
						}else {
							System.out.println("The document is: FINISHED");
							isOpen = false;
						}										
				}
				
				sc.close();
				System.out.println("Client Disconnected");			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
