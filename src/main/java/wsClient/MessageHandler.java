package wsClient;

import java.util.ArrayList;

import gui.ControllingGUI4TestsV4;

public class MessageHandler 
{
	private static MessageHandler instance = null;
	
	private MessageHandler()
	{
	}
	
	private MessageHandler(ControllingGUI4TestsV4 _gui)
	{
	}
	
	public String test()
	{
		return "bestanden";
	}
	private ArrayList<String> splitMessag(String _message)
	{
		
		String identifyer = _message.substring(0, _message.indexOf('.'));
		
		String command = _message.substring(_message.indexOf('.')+1, _message.indexOf(' '));
		String[] values = _message.substring(_message.indexOf(' ')+1,_message.length()).split(" ");
		
//		System.out.println("identifiyer: "+identifyer);
//		System.out.println("command: "+command);
//		System.out.println("values: ");
//		for (String s : values) {
//			System.out.println(s);
//		}
		
		ArrayList<String> message =new ArrayList<String>();
		message.add(identifyer);
		message.add(command);
		for (String s : values) {
			message.add(s);
		}
		return message;
	}
	
	public static MessageHandler getModel()
	{
		if (instance==null)
		{
			try {
				instance = new MessageHandler();
			} catch (Exception e) {
				System.err.println("Could not create object WebsockeClient");
				System.err.println(e.getLocalizedMessage());
				e.printStackTrace();
			}
		}
		return instance;
	}
	public void processMessage(String _message)
	{
		System.out.println(_message);
			
				if (_message.contains(".")&_message.contains(" "))
			{
					try {
							this.setAction(this.splitMessag(_message));
					} catch (Exception e) {
						System.err.println("\n MessageHanlder: could not processMessage!");
						//System.err.println(e.getLocalizedMessage());
					}
			}
			
	}
	private void setAction(ArrayList<String> _message)
	{
		switch (_message.get(0)) {
			case "HC_SR04_SonicSensorF":
//				System.out.println("MessageHandler: id 1 im Array ist: "+_message.get(1));
				if (_message.get(1).equals("distance"))
				{	
//					System.out.println("Message:Handler: distance Message identifyed");
					ControllingGUI4TestsV4.setDistanceF(_message.get(2));
				}	
				break;
			case "HC_SR04_SonicSensorL":
//				System.out.println("MessageHandler: id 1 im Array ist: "+_message.get(1));
				if (_message.get(1).equals("distance"))
				{	
//					System.out.println("Message:Handler: distance Message identifyed");
					ControllingGUI4TestsV4.setDistanceL(_message.get(2));
				}	
				break;
			case "HC_SR04_SonicSensorR":
//				System.out.println("MessageHandler: id 1 im Array ist: "+_message.get(1));
				if (_message.get(1).equals("distance"))
				{	
//					System.out.println("Message:Handler: distance Message identifyed");
					ControllingGUI4TestsV4.setDistanceR(_message.get(2));
				}	
				break;
	
			default:
				break;
			}
	}
	public void setGui (ControllingGUI4TestsV4 _gui)
	{
	}
	
}
