package hardware;

import wsClient.WsClient;

public class Sonar implements SonarInterface
{
	private  WsClient webscoket;
	
	public Sonar (WsClient _websocket)
	{
		this.webscoket = _websocket;
	}

	@Override
	public void getDistance(char _identifyer_F_L_R) throws Exception {
		this.webscoket.sendCommand("sonar.getDistanceStream "+_identifyer_F_L_R);
	}

	@Override
	public void getDistanceStream(char _identifyer_F_L_R) throws Exception {
		System.out.println("sonar.getDistanceStream "+_identifyer_F_L_R);
		this.webscoket.sendCommand("sonar.getDistanceStream "+_identifyer_F_L_R);
		
	}

	@Override
	public void stop() throws Exception {
		System.out.println("sonar.stop");
		this.webscoket.sendCommand("sonar.stop");
		
	}

}
