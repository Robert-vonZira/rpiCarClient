package wsClient;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;



public class EventSocketClientSide extends WebSocketAdapter 
/**
 * https://github.com/jetty-project/embedded-jetty-websocket-examples/tree/master/native-jetty-websocket-example/src/main/java/org/eclipse/jetty/demo
 * 
 * provides the functionality of the socket connection. 
 */

{

	MessageHandler messageHander = null;
	
	public void setMessageHandler(MessageHandler _messageHander)
	{
		this.messageHander = _messageHander;
	}

    @Override
    public void onWebSocketConnect(Session sess)
    {
        super.onWebSocketConnect(sess);
        System.out.println("Socket Connected: " + sess);
    }
    
    @Override
    public void onWebSocketText(String message)
    {
        super.onWebSocketText(message);
        //System.out.println("< " + message);
        this.messageHander.processMessage(message);
        //        MessageHandler.getModel(null).splitMessag(message);
        
    }
    
    @Override
    public void onWebSocketClose(int statusCode, String reason)
    {
        super.onWebSocketClose(statusCode,reason);
        System.out.println("Socket Closed: [" + statusCode + "] " + reason);
    }
    
    @Override
    public void onWebSocketError(Throwable cause)
    {
        super.onWebSocketError(cause);
        cause.printStackTrace(System.err);
    }
}