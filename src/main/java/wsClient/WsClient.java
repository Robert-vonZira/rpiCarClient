package wsClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;

import java.util.concurrent.Future;


import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.client.WebSocketClient;

/**
 * Versuch eines Standalone Client
 * @author Robert
 *
 */
public class WsClient {
	

	private static String standardHost= "raspi";
	private static String serverIP="127.0.0.1";//61 oder 67 livesystem , 111 testsystem
//	private static String serverIP="192.168.0.166";
	private static String port="8080";
	private static WebSocketClient client=null;
	static WsClient model;
	private static Session session=null;
	private static URI uri;
	
	public WsClient(MessageHandler _messageHandler){
//	
	try {
		serverIP = getIP(standardHost);
	} catch (Exception e) {
		System.out.println(e.getLocalizedMessage());
		System.out.println("Using localhost as Server.");
		e.printStackTrace();
	}
    uri = URI.create("ws://"+serverIP+":"+port);
    System.out.println("WSclient started...");

   client = new WebSocketClient();
   try
   {
//	    	try
//       {
           client.start();
           // The socket that receives events
           EventSocketClientSide socket = new EventSocketClientSide();
           socket.setMessageHandler(_messageHandler);
           // Attempt Connect
           Future<Session> fut = client.connect(socket,uri);
           // Wait for Connect
//                Session session = fut.get();
           session = fut.get();
       //    session.getRemote().sendString("Ping");
//       }
//           finally
//           {
//               client.stop();
//           }
       } 
	   catch (Throwable t)
       {
           t.printStackTrace(System.err);
       }

	}

	public static WsClient getModel(MessageHandler _messageHandler)
		{
			if (client==null)
			{
				try {
					model = new WsClient(_messageHandler);
				} catch (Exception e) {
					System.err.println("Could not create object WebsockeClient");
					System.err.println(e.getLocalizedMessage());
					e.printStackTrace();
				}
			}
			return model;
			
		}
	public static String sendCommand(String _command)
    {
    //	System.out.println("WsClient: received command: \n\t"+_command);
    	String answer="";
    	try {
			session.getRemote().sendString(_command);
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
			answer=e.getLocalizedMessage();
		}
    	return answer;
    }
	
//	public static String getMessage(String _message)
//	{
//		String answer="";
//		try {
//		
//			
//		} catch (Exception e) {
//			System.out.println(e.getLocalizedMessage());		}
//		return answer;
//	}
	public void clientStop()
	{
		 try {
			
			client.stop();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
    
    public static String getIP(String _name) throws Exception
	{
		 String ip ="";
		 String hostname = _name;// = args[0];

		    try 
		    {
		      System.out.println("Trying to get IP for Host: "+hostname);
		      InetAddress ipaddress = InetAddress.getByName(hostname);
		      ip=""+ipaddress.getHostAddress();
		      System.out.println("Got IP: "+ip);
		    } catch ( UnknownHostException e ) 
		    {
		      //System.err.println("Could not find or reach IP address for: " + hostname);
		      throw new Exception("Could not find or reach  IP address for: " + hostname);
		      
		    }
		    catch ( Exception e1 ) 
		    {
		      System.err.println(e1.getLocalizedMessage());
		      return null;
		    }
		    
		    
		   		    return ip;
	}
}
