package hardware;

import java.io.File;
	
public interface SonarInterface 
/**
 * provides the remote functionalities for rpicar
 * 
 * by Robert Kofler
 */
{
	public abstract void getDistance(char _identifyer_F_L_R) throws Exception;
	public abstract void getDistanceStream(char _identifyer_F_L_R) throws Exception;
	public abstract void stop() throws Exception;

}
