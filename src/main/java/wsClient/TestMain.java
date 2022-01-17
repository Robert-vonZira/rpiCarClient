package wsClient;

import java.util.Scanner;

import gui.ControllingGUI4TestsV4;
import hardware.Sonar;
import hardware.Vehicle;

	

public class TestMain { 
	
	private static Vehicle vehicle;
	private static Sonar sonar;
	private static MessageHandler messageHandler = MessageHandler.getModel();
	
	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);
		String message = "";
		String name = "java_Client1";

//	WebsocketClient client = new WebsocketClient();
//		WsClient client = WsClient.getModel();
		WsClient client = WsClient.getModel(messageHandler);
		vehicle = new Vehicle(client);
		sonar = new Sonar(client);
	
	try {
		System.out.println("Client up and running!");
		WsClient.sendCommand(name);
		
		
	}
	catch (Exception e) {
		System.out.println(e.getLocalizedMessage());
		e.printStackTrace();
	}
	
	openGui();
	
	try {
		
		while (! message.equals("exit"))
		{
			
			message=s.nextLine();
			WsClient.sendCommand(message);
			
		}
		client.clientStop();
		
		
	} catch (Exception e) {
		System.out.println(e.getLocalizedMessage());
	}
	finally 
	{
		s.close();
		client.clientStop();
	} 

}
	 public static void openGui()
		{
			//ein Fenster wird ge�ffnet mit dem per Hand das Fahrzeug gesteuert werden kann - eine Fernsteuerung
			ControllingGUI4TestsV4 controllsWindow = new ControllingGUI4TestsV4(vehicle, sonar);
//			ControllingGUI4Tests	 controllsWindow = new ControllingGUI4Tests(_interface);
			controllsWindow.setVisible(true);
			controllsWindow.init();
			controllsWindow.revalidate();;
			controllsWindow.repaint();
			messageHandler.setGui(controllsWindow);
		}
}
