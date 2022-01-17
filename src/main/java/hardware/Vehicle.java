package hardware;

import wsClient.WsClient;

public class Vehicle implements VehicleInterface
{
	public Vehicle (WsClient _websocket)
	{
	}

	@Override
	public void move(int _speed) throws Exception {
		WsClient.sendCommand("vehicle.move "+_speed);
	}

	@Override
	public void steer(int _direction) throws Exception {
		System.out.println("vehicle.steer "+_direction);
		WsClient.sendCommand("vehicle.steer "+_direction);
		
	}

}
