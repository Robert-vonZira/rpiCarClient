package hardware;

import wsClient.WsClient;

public class Vehicle implements VehicleInterface
{
	private  WsClient webscoket;
	
	public Vehicle (WsClient _websocket)
	{
		this.webscoket = _websocket;
	}

	@Override
	public void move(int _speed) throws Exception {
		this.webscoket.sendCommand("vehicle.move "+_speed);
	}

	@Override
	public void steer(int _direction) throws Exception {
		System.out.println("vehicle.steer "+_direction);
		this.webscoket.sendCommand("vehicle.steer "+_direction);
		
	}

}
