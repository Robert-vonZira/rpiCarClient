package com.robert.com.robert.client;


import java.io.IOException;
import java.net.URI;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicReference;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import org.eclipse.jetty.websocket.client.WebSocketClient;

public class ChatClient extends WebSocketAdapter
{
    public static void main(String[] args) throws Exception
    {
        ChatClient chat = new ChatClient();
        chat.connect(new URI("ws://127.0.0.1:8080"));
        chat.beChatty();
    }

    private final int ID = ThreadLocalRandom.current().nextInt(1000);
    private final AtomicReference<Session> r = new AtomicReference<>();

    private void beChatty() throws InterruptedException
    {
        int count = 0;
        while (r.get().isOpen())
        {
           // String msg = String.format("Hi from %s [%d]",ID,count++);
           // System.out.println("[write] " + msg);
        	String msg = String.format("%s [%d]",ID,count++);
            System.out.println(">" + msg);
            r.get().getRemote().sendStringByFuture(msg);
            try {
				r.get().getRemote().sendString("hello");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            Thread.sleep(1000);
        }
    }

    private void connect(URI destServerURI) throws Exception
    {
        WebSocketClient client = new WebSocketClient();
        client.setMaxIdleTimeout(1500);
        client.start();
        client.connect(this,destServerURI).get();
    }

    @Override
    public void onWebSocketClose(int statusCode, String reason)
    {
        // TODO Auto-generated method stub
        super.onWebSocketClose(statusCode,reason);
    }

    @Override
    public void onWebSocketConnect(Session sess)
    {
        r.set(sess);
        System.out.printf("ChatClient local addr: %s%n",sess.getLocalAddress());
    }

    @Override
    public void onWebSocketError(Throwable cause)
    {
        cause.printStackTrace();
    }
}