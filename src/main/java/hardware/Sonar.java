package hardware;

import wsClient.WsClient;

public class Sonar extends Hardware implements SonarInterface
{
	public Sonar (WsClient _websocket)
	{
		super ( "sonar");
		
	}

	@Override
	public void getDistance(char _identifyer_F_L_R) throws Exception {
		WsClient.sendCommand("sonar.getDistanceStream "+_identifyer_F_L_R);
	}

	@Override
	public void getDistanceStream(char _identifyer_F_L_R) throws Exception {
		System.out.println("sonar.getDistanceStream "+_identifyer_F_L_R);
		WsClient.sendCommand("sonar.getDistanceStream "+_identifyer_F_L_R);
		
	}

	@Override
	public void stop() throws Exception {
		System.out.println("sonar.stop");
		WsClient.sendCommand("sonar.stop");
		
	}

}
