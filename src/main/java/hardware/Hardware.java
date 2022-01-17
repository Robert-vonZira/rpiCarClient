package hardware;
//superclass for  Hardware
import wsClient.WsClient;

public class Hardware {
	
	private String name;
	private WsClient websocket;
	
	public Hardware(WsClient _websocket, String _name)
	{
		this.name = _name;
		this.websocket =_websocket;
	}

}
