package gui;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectOutputStream;

import javax.swing.AbstractAction;
import javax.swing.Action;
//import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import java.awt.event.ItemListener;


import hardware.Vehicle;
import hardware.Sonar;



//import clientSide.VehicleJsonBuilder;


public class ControllingGUI4TestsV4 extends JFrame 
{
	
	/**
	 * besser mit keybindings arbeiten: 
contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"), "doEnterAction");
 
 
 f�r einen neuen key
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JButton btnFwd = new JButton("FWD"), btnRwd = new JButton("RWD"), btnLeft = new JButton("Left"), btnRight = new JButton("Right"),btnFwdRight = new JButton("FwdR"),btnFwdLeft = new JButton("FwdL"),btnRwdRight = new JButton("RwdR"),btnRwdLeft = new JButton("RwdL");
	private static JCheckBox cbxDistF = new JCheckBox(), cbxDistL = new JCheckBox(), cbxDistR = new JCheckBox();
	private JPanel controlls = new JPanel(), cockpit = new JPanel(), pnlEmpty = new JPanel();
//	private static VehicleJsonBuilder vehicleJSONbuilder = null;
	private static JTextField txtSpeed = new JTextField("30"), txtMaxSpeed = new JTextField("100"), txtDistanceF = new JTextField("- - -"), txtDistanceL = new JTextField("- - -"), txtDistanceR = new JTextField("- - -");
	JLabel lblSpeed = new JLabel("speed:"), lblMaxSpeed = new JLabel("max:"), lblDistanceF = new JLabel("Dist. Front:"),lblDistanceL = new JLabel("Dist. Left:"),lblDistanceR = new JLabel("Dist. Right:");
	static JLabel picture;
	private static Action move, moveActionStopFwd, moveActionRwd, moveActionStopRwd, moveActionRight, moveActionStopRight, moveActionLeft, moveActionStopLeft, moveActionIncreaseSpeed, moveActionDecreaseSpeed, sonarActionGetDistance, cameraActionShootStill;   
    private static boolean fwdBol=false, rwdBol=false, leftBol=false, rightBol=false;
    private static Vehicle v;
    private static Sonar sonar;

	
	
//	public ControllingGUI4TestsV3(VehicleJsonBuilder _jsonBuilder)
//	{
//		vehicleJSONbuilder=_jsonBuilder;
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//		//this.setLayout(new GridLayout(3, 3));
//		this.setLayout(null);
//		
//		JRootPane rootPane = this.getRootPane();	
//	}
    
	public ControllingGUI4TestsV4(Vehicle _v, Sonar _sonar)
	{
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//this.setLayout(new GridLayout(3, 3));
		this.setLayout(null);
		this.setTitle("rpiCar remote control");
		ControllingGUI4TestsV4.v = _v;
		ControllingGUI4TestsV4.sonar = _sonar;
	
//		JRootPane rootPane = this.getRootPane();
		
		
		
	}
	public void init()
	{
		this.setSize(800, 600);
		
		new MoveButtonListener();
		
		move = new MoveActionFwd();
		moveActionStopFwd = new MoveActionStopFwd();
		moveActionRwd = new MoveActionRwd();
		moveActionStopRwd = new MoveActionStopRwd();
		
		moveActionRight = new MoveActionRight();
		moveActionStopRight = new MoveActionStopRight();
		moveActionLeft = new MoveActionLeft();
		moveActionStopLeft = new MoveActionStopLeft();
		
		moveActionIncreaseSpeed = new MoveActionIncreaseSpeed();
		moveActionDecreaseSpeed = new MoveActionDecreaseSpeed();
		
//		sonarActionGetDistance = new SonarActionGetDistance();
		
		cameraActionShootStill = new CameraActionShootStill();
		
		
		controlls.setLayout(new GridLayout(3,3,2,2));
		controlls.setBounds(10, 5, 230, 100);
		
	//	controlls.setBackground(Color.DARK_GRAY);
		cockpit.setBackground(Color.lightGray);
		pnlEmpty.setBackground(Color.lightGray);
//		btnFwd.addActionListener(buttonListener);

//		myComponent.getInputMap(JComponent.WHEN_FOCUSED);
//		myComponent.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
//		myComponent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

		
		rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "doMoveActionForward");
		rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true), "doStopActionForward");
		rootPane.getActionMap().put( "doMoveActionForward", move );
		rootPane.getActionMap().put( "doStopActionForward", moveActionStopFwd );
		
		rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), "doMoveActionRearward");
		rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), "doStopActionRearward");
		rootPane.getActionMap().put( "doMoveActionRearward", moveActionRwd );
		rootPane.getActionMap().put( "doStopActionRearward", moveActionStopRwd );
		
		rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "doMoveActionRight");
		rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), "doStopActionRight");
		rootPane.getActionMap().put( "doMoveActionRight", moveActionRight );
		rootPane.getActionMap().put( "doStopActionRight", moveActionStopRight );
		
		rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "doMoveActionLeft");
		rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), "doStopActionLeft");
		rootPane.getActionMap().put( "doMoveActionLeft", moveActionLeft );
		rootPane.getActionMap().put( "doStopActionLeft", moveActionStopLeft );
		
		
		rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, 0, false), "doMoveActionDecreaseSpeed");
		rootPane.getActionMap().put( "doMoveActionDecreaseSpeed", moveActionDecreaseSpeed);
		rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SUBTRACT, 0, false), "doMoveActionDecreaseSpeed");
		rootPane.getActionMap().put( "doMoveActionDecreaseSpeed", moveActionDecreaseSpeed);
		rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, 0, false), "doMoveActionIncreaseSpeed");
		rootPane.getActionMap().put( "doMoveActionIncreaseSpeed", moveActionIncreaseSpeed);
		rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ADD, 0, false), "doMoveActionIncreaseSpeed");
		rootPane.getActionMap().put( "doMoveActionIncreaseSpeed", moveActionIncreaseSpeed);
		
		
		rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false), "doSonarActionGetDistance");
		rootPane.getActionMap().put( "doSonarActionGetDistance", sonarActionGetDistance);
	
		rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0, false), "doCameraShootStill");
		rootPane.getActionMap().put( "doCameraShootStill", cameraActionShootStill);
	
		
		
		
		
		controlls.add(btnFwdLeft);
		controlls.add(btnFwd);
		controlls.add(btnFwdRight);
		controlls.add(btnLeft);
		controlls.add(pnlEmpty);
		controlls.add(btnRight);
		controlls.add(btnRwdLeft);
		controlls.add(btnRwd);
		controlls.add(btnRwdRight);
		
		
		add(controlls);
		
		cockpit.setLayout(null);
		cockpit.setBounds(10, controlls.getHeight()+10, controlls.getWidth(), 200-controlls.getHeight()+20);
		
		
		lblSpeed.setBounds(0, 5, 60, 20);
		lblMaxSpeed.setBounds(0, 25, 60, 25);
		lblDistanceF.setBounds(0, 45, 90, 20);
		lblDistanceL.setBounds(0, 65, 90, 20);
		lblDistanceR.setBounds(0, 85, 90, 20);
		cockpit.add(lblSpeed);
		cockpit.add(lblMaxSpeed);
		cockpit.add(lblDistanceF);
		cockpit.add(lblDistanceL);
		cockpit.add(lblDistanceR);
		
		txtSpeed.setEditable(false);
		txtSpeed.setBounds(lblDistanceF.getWidth(), 5, 50, 20);
		txtSpeed.setHorizontalAlignment(JTextField.RIGHT);
		
		txtMaxSpeed.setEditable(false);
		txtMaxSpeed.setBounds(lblDistanceF.getWidth(),25,50, 20);
		txtMaxSpeed.setHorizontalAlignment(JTextField.RIGHT);
		
		txtDistanceF.setEditable(false);
		txtDistanceF.setBounds(lblDistanceF.getWidth(), 45, 50, 20);
		txtDistanceF.setHorizontalAlignment(JTextField.RIGHT);

		txtDistanceL.setEditable(false);
		txtDistanceL.setBounds(lblDistanceL.getWidth(), 65, 50, 20);
		txtDistanceL.setHorizontalAlignment(JTextField.RIGHT);

		txtDistanceR.setEditable(false);
		txtDistanceR.setBounds(lblDistanceR.getWidth(), 85, 50, 20);
		txtDistanceR.setHorizontalAlignment(JTextField.RIGHT);
		
		cockpit.add(txtMaxSpeed);
		cockpit.add(txtSpeed);
		cockpit.add(txtDistanceF);
		cockpit.add(txtDistanceL);
		cockpit.add(txtDistanceR);
		
		
		cbxDistF.setBounds(lblDistanceF.getWidth()+txtDistanceF.getWidth()+5, 45, 20, 20);
		cbxDistF.setSelected(false);
		cbxDistF.setBackground(Color.lightGray);
		cbxDistF.addItemListener(new ItemListener() {
	         public void itemStateChanged(ItemEvent e) {     
	        	 if (e.getStateChange() == ItemEvent.SELECTED)
	        	 {//System.out.println("GUI sonar.getDistanceStream F ist aktiviert");
	        		 try {
						sonar.getDistanceStream('F');
					} catch (Exception e1) {
						 System.err.println("GUI Failure at sonar.getDistanceStream F!");
							e1.printStackTrace();}
	        	 }else
	        	 {//System.out.println("GUI sonar.stop"); 
	        		 try {
						sonar.stop();
	        		 } catch (Exception e1) {
						 System.err.println("GUI Failure at sonar.stop!");
							e1.printStackTrace();
					}
	        	 }
	         }           
	      });
		//cbxDistF.setMnemonic(KeyEvent.VK_S);
		cbxDistL.setBounds(lblDistanceL.getWidth()+txtDistanceL.getWidth()+5, 65, 20, 20);
		cbxDistL.setSelected(false);
		cbxDistL.setBackground(Color.lightGray);
		cbxDistL.addItemListener(new ItemListener() {
	         public void itemStateChanged(ItemEvent e) {     
	        	 if (e.getStateChange() == ItemEvent.SELECTED)
	        	 {//System.out.println("GUI sonar.getDistanceStream L ist aktiviert");
	        		 try {
						sonar.getDistanceStream('L');
					} catch (Exception e1) {
						 System.err.println("GUI Failure at sonar.getDistanceStream L!");
							e1.printStackTrace();}
	        	 }else
	        	 {//System.out.println("GUI sonar.stop"); 
	        		 try {
						sonar.stop();
	        		 } catch (Exception e1) {
						 System.err.println("GUI Failure at sonar.stop!");
							e1.printStackTrace();
					}
	        	 }
	         }           
	      });
		cbxDistR.setBounds(lblDistanceR.getWidth()+txtDistanceR.getWidth()+5, 85, 20, 20);
		cbxDistR.setSelected(false);
		cbxDistR.setBackground(Color.lightGray);
		cbxDistR.addItemListener(new ItemListener() {
	         public void itemStateChanged(ItemEvent e) {     
	        	 if (e.getStateChange() == ItemEvent.SELECTED)
	        	 {//System.out.println("GUI sonar.getDistanceStream R ist aktiviert");
	        		 try {
						sonar.getDistanceStream('R');
					} catch (Exception e1) {
						 System.err.println("GUI Failure at sonar.getDistanceStream R!");
							e1.printStackTrace();}
	        	 }else
	        	 {//System.out.println("GUI sonar.stop"); 
	        		 try {
						sonar.stop();
	        		 } catch (Exception e1) {
						 System.err.println("GUI Failure at sonar.stop!");
							e1.printStackTrace();
					}
	        	 }
	         }           
	      });
		
		cockpit.add(cbxDistF);
		cockpit.add(cbxDistL);
		cockpit.add(cbxDistR);
		
		add(cockpit);
		
		

		
		picture = new JLabel();
		picture.setBounds(220, 0, 400, 400);
		picture.setPreferredSize(new Dimension(400, 400));
		add(picture);
		repaint();
		
		
		
	}
	
	
	private static void activateButton(JButton _button)
	{
		_button.setForeground(Color.green);
		_button.setBackground(Color.LIGHT_GRAY);
	}
	private static void deactivateButton(JButton _button)
	{
		_button.setForeground(Color.black);
		//_button.setBackground(Color.gray);
		_button.setBackground(null);
	}
	private static void increaseSpeed(int _amount){	
	 if (Integer.parseInt(txtSpeed.getText())+_amount<=Integer.parseInt(txtMaxSpeed.getText()))
	   {
		   int speed = Integer.parseInt(txtSpeed.getText());
		   speed+=_amount;
		   txtSpeed.setText("");
		   txtSpeed.setText(""+speed);
	   }
	}
	private static void decreaseSpeed(int _amount){	
		 if (Integer.parseInt(txtSpeed.getText())-_amount>=0)
		   {
			   int speed = Integer.parseInt(txtSpeed.getText());
			   speed-=_amount;
			   txtSpeed.setText("");
			   txtSpeed.setText(""+speed);
		   }
		}
	
	public static void setDistanceF(String _distance)
	{
		txtDistanceF.setText(_distance);
	}
	public static void setDistanceL(String _distance)
	{
		txtDistanceL.setText(_distance);
	}
	public static void setDistanceR(String _distance)
	{
		txtDistanceR.setText(_distance);
	}
	
	
	
	static class MoveButtonListener implements ActionListener
    {
        public void actionPerformed( ActionEvent bp )
        {            // provides feedback to the console to show that the enter button
            // was pressed
//            System.out.println("Button was pushed");
            // focus must be returned to the text field in order for the
            // selectAll() method to work.
//            btnFwd.requestFocusInWindow();
        } // end method actionPerformed()
    } // end class ButtonListener
	
	
	static class MoveActionFwd extends AbstractAction
    {	private static final long serialVersionUID = 1L;
		public void actionPerformed( ActionEvent tf )
        {
            if (!fwdBol){
            	System.out.println( "Gui: Key UP was pressed!" );
//            	btnFwd.doClick();
            	activateButton(btnFwd);
	            try {
	            	
	            	v.move(Integer.parseInt(txtSpeed.getText()));
	            	} catch (Exception e1) {
					System.err.println("GUI Failure at move fwd!");
					e1.printStackTrace();
				}
	            fwdBol=true;
            }
	          
        } 
    } 
	
	static class MoveActionStopFwd extends AbstractAction
    {		     
		private static final long serialVersionUID = 1L;

		public void actionPerformed( ActionEvent tf )
        {
			
        	if (fwdBol)
			{
				System.out.println( "Gui: Key UP was released!" );
				deactivateButton(btnFwd);
	            try {
	            	v.move(0);
				} catch (Exception e1) {
					 System.err.println("GUI Failure at stop move fwd!");
					e1.printStackTrace();
				}
	            fwdBol=false;
			}
        } 
    } 
	
	static class MoveActionRwd extends AbstractAction
    {	private static final long serialVersionUID = 1L;
		public void actionPerformed( ActionEvent tf )
        {
            if (!rwdBol){
            	System.out.println( "Gui: Key DOWN was pressed!" );
            	if (!fwdBol)
            	{
//	            	btnRwd.doClick(); //klicks sind deaktivert.. tastursteuerung ftw!
	            	activateButton(btnRwd);
		            try {
		            	v.move(Integer.parseInt(txtSpeed.getText())*-1);
					} catch (Exception e1) {
						System.err.println("GUI Failure at move fwd!");
						e1.printStackTrace();
					}
		            rwdBol=true;
            	}
            }
	          
        } 
    } 
	
	static class MoveActionStopRwd extends AbstractAction
    {
		private static final long serialVersionUID = 1L;

		public void actionPerformed( ActionEvent tf )
        {
			
        	if (rwdBol)
			{
				System.out.println( "Gui: Key DOWN was released!" );
				deactivateButton(btnRwd);
	            try {
	            	v.move(0);
				} catch (Exception e1) {
					 System.err.println("GUI Failure at stop move rwd!");
					e1.printStackTrace();
				}
	            rwdBol=false;
			}
        } 
    }
	
	
	class MoveActionRight extends AbstractAction
    {	private static final long serialVersionUID = 1L;
		public void actionPerformed( ActionEvent tf )
        {
            
			if (!rightBol)
			{
	        	System.out.println( "Gui: Key RIGHT was pressed!" );
			}
	            	activateButton(btnRight);
		            try {
		            	rightBol=true; 
		            	v.steer(2);
					} catch (Exception e1) {
						System.err.println("GUI Failure at steer right!");
						e1.printStackTrace();
					}
			
        } 
    } 
	
	class MoveActionStopRight extends AbstractAction
    { 	private static final long serialVersionUID = 1L;
		public void actionPerformed( ActionEvent tf )
        {
			
			System.out.println( "Gui: Key RIGHT was released!" );
			deactivateButton(btnRight);
            try {
            	rightBol=false;
            	v.steer(0);
            	} catch (Exception e1) {
				 System.err.println("GUI Failure at stop steer right!");
				e1.printStackTrace();
			}
        }
    } 
	class MoveActionLeft extends AbstractAction
	{	private static final long serialVersionUID = 1L;
	public void actionPerformed( ActionEvent tf )
    {
     
		if (!leftBol)
		{
	    	System.out.println( "Gui: Key LEFT was pressed!" );
		}
	        	activateButton(btnLeft);
	            try {
	            	leftBol=true;
	            	v.steer(1);
				} catch (Exception e1) {
					System.err.println("GUI Failure at steer left!");
					e1.printStackTrace();
				}
		
		
    } 
} 
  
	class MoveActionStopLeft extends AbstractAction
    {	private static final long serialVersionUID = 1L;
		public void actionPerformed( ActionEvent tf )
	    {
			
			System.out.println( "Gui: Key LEFT was released!" );
			deactivateButton(btnLeft);
	        try {
	        	leftBol=false;
	        	v.steer(0);
			} catch (Exception e1) {
				 System.err.println("GUI Failure at stop steer left!");
				e1.printStackTrace();
			}
	    } 
    }
	
	class MoveActionIncreaseSpeed extends AbstractAction
	{	private static final long serialVersionUID = 1L;
	public void actionPerformed( ActionEvent tf )
    {
     
		{
			int factor=10;
			
			System.out.println( "Gui: Incrase speed Action detected!");
			increaseSpeed(factor);
	    } 
    } 
} 
  
	class MoveActionDecreaseSpeed extends AbstractAction
    {	private static final long serialVersionUID = 1L;
		public void actionPerformed( ActionEvent tf )
	    {
			int factor=10;
			
			System.out.println( "Gui: Decrase speed Action detected!");
			decreaseSpeed(factor);
	    } 
    }
	

//	static class SonarActionGetDistance extends AbstractAction
//    {	private static final long serialVersionUID = 1L;
//		public void actionPerformed( ActionEvent tf )
//	    {
//			try {
////				double distance = vehicleJSONbuilder.getDistanceSonar();
//				System.out.println("Gui: Distance: "+distance);
//				
//				distance=(Math.round(distance*100.0)/100.0);
////				txtDistance.setText(""+distance);
//				
//			} catch ( Exception e) {
//				System.err.println( "GUI Failure getDistance!");
//				System.out.println(e.getLocalizedMessage());
//			}
//	    } 
//    }
	
	class CameraActionShootStill extends AbstractAction
    {	private static final long serialVersionUID = 1L;
		public void actionPerformed( ActionEvent tf )
	    {
//			File pic=null;
//			try {
////				System.out.println("make a pic");
////				BufferedImage pic = vehicle.getPicture();
////				pic = vehicleJSONbuilder.getPicture();
//			 
//				 System.out.println("Name: "+pic.getName());
//				
//			} catch ( Exception e) {
//				System.err.println( "GUI Picture failed!");
//				System.out.println(e.getLocalizedMessage());
//				e.printStackTrace();
//			}
//			
//			 try {
//				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("test.png"));
//				out.writeObject(pic);
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
			     
//			 picture.setIcon((Icon) pic);
//			 picture.getParent().revalidate();
//			 System.out.println("Gui: Picture made.");

	    } 
    }


}

