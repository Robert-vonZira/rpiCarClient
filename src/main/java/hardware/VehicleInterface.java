package hardware;
	
public interface VehicleInterface 
/**
 * provides the remote functionalities for rpicar
 * 
 * by Robert Kofler
 */
{
	public abstract void move(int _speed) throws Exception;
	public abstract void steer(int _direction) throws Exception;
}
