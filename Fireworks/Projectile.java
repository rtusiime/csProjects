//*Kevin Tusiime
// rtusiime
//31529433
// HW10
// 2:00pm - 3:15pm
// I did not collaborate with anyone on this assignment

//this class designs the GUI of the system: the JFrame, the two Jpanels.
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import java.awt.List;

import javax.swing.*;
import javax.swing.event.*;


public class Projectile extends JFrame {

	//Slider meant to control projectile angle
	JSlider angle = new JSlider(JSlider.HORIZONTAL, 30, 89, 60);

	//Slider meant to control initial velocity of the firework.
	JSlider speed = new JSlider(JSlider.HORIZONTAL, 50,150, 100);

	//Toggle between single projectile and multiple projectiles
	String[] typeOfProject = {"Normal", "Extra Credit"};
	JComboBox type = new JComboBox(typeOfProject);


	//Slider meant to control the time it takes for the firework to blow up.
	JSlider timeDelay = new JSlider(JSlider.HORIZONTAL, 10,20, 15);

	String colourOptions[] = {"Red", "Blue", "Green", "Yellow", "Magenta"};//eventually going to change this to objects
	String patternOptions[] = {"Jamaica", "Glitch", "Clanderon","Aeon", "Nirvana"};//eventually also gonna change this shit

	//Combobox meant to allow the user to pick the color of the projectile path.
	JComboBox colour = new JComboBox(colourOptions);

	//Combox meant to allow the user to pick the type of pattern created by the fireworks
	JComboBox pattern = new JComboBox(patternOptions);

	//Button meant to implement new firework simulation whenever changes are made in the GUI
	JButton launcher = new JButton("Launch");
	//Creating Tapestry object as global variable so the ControlPanel class
	Tapestry canvas = new Tapestry();


	public Projectile() {
		setTitle("Projectile Simulator");
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLayout(new BorderLayout());

		ControlPanel cp = new ControlPanel();
		cp.setLayout(new WrapLayout());

		add(cp, BorderLayout.NORTH);
		canvas.setBackground(Color.BLACK);
		add(canvas, BorderLayout.CENTER);


		pack();
		setVisible(true);
	}

	protected class ControlPanel extends JPanel implements ActionListener{


		//Constructor for control panel window
		public ControlPanel() {
			super();
			angle.setPaintLabels(true);
			angle.setPaintTicks(true);
			angle.setMinorTickSpacing(1);
			angle.setMajorTickSpacing(5);
			JLabel angleLabel = new JLabel("Projectile \nAngle:");
			add(angleLabel);
			add(angle);

			speed.setPaintLabels(true);
			speed.setPaintTicks(true);
			speed.setMinorTickSpacing(5);
			speed.setMajorTickSpacing(25);
			JLabel speedLabel = new JLabel("Initial \nVelocity(m/s):");
			add(speedLabel);
			add(speed);

			timeDelay.setPaintLabels(true);
			timeDelay.setPaintTicks(true);
			//timeDelay.setMinorTickSpacing(1);
			timeDelay.setMajorTickSpacing(1);
			JLabel timerLabel = new JLabel("Time \nDelay(seconds):");
			add(timerLabel);
			add(timeDelay);

			add(colour);

			add(pattern);

			add(type);

			launcher.addActionListener(this);
			add(launcher);
			pack();
		}


		@Override
		public void actionPerformed(ActionEvent e) {
			canvas.repaint();

		}
	}

	protected class Tapestry extends JPanel{
		double x=0;
		double y=0;
		double[] pointOfOrigin;
		Graphics graphics;

		public Tapestry() {
			super();
			setSize(8000,8000);
		}

		@Override
		public void paintComponent(Graphics g) {
			pointOfOrigin= new double[2];
			pointOfOrigin[0] = 0;
			pointOfOrigin[1] = 0;
			super.paintComponent(g);
			System.out.println("calling paintComponent in Tapestry");
			colourize(g);
			if((type.getSelectedItem().toString()).equalsIgnoreCase("Normal")) {
				explosion(g, launch(g,pointOfOrigin));
			}
			else {
				//secondLaunch(g,launch(g,pointOfOrigin));
				ArrayList<double[]> tempStorage = secondLaunch(g,launch(g,pointOfOrigin));
				explosion(g,tempStorage.get(1));
				explosion(g,tempStorage.get(0));
				for(double[] d : tempStorage) {
					explosion(g,d);
				}

			}


		}


		public void colourize(Graphics g) {//this method sets color of the projectile
			if((colour.getSelectedItem().toString()).equalsIgnoreCase("Magenta")) {
				g.setColor(Color.MAGENTA);
			}
			else if((colour.getSelectedItem().toString()).equalsIgnoreCase("Red")) {
				g.setColor(Color.RED);
			}
			else if((colour.getSelectedItem().toString()).equalsIgnoreCase("Green")) {
				g.setColor(Color.GREEN);
			}
			else if((colour.getSelectedItem().toString()).equalsIgnoreCase("yellow")) {
				g.setColor(Color.YELLOW);
			}
			else {
				g.setColor(Color.BLUE);
			}
		}

		public double[] launch(Graphics g, double[] point) {
			double angleValue = Math.PI*angle.getValue()/180;//converts the user's input to radians
			int speedValue = speed.getValue();
			double time = timeDelay.getValue();
			Graphics2D g2 = (Graphics2D)g;//creating 2d graphics object

			double increment=0;//this will help me plot the X and Y co-ordinates of the projectile every 0.01 seconds

			double[] endPoint = new double[2];
			for(int i =0; i<=time*10; i++) {//this plots the position of the projectile every 0.01
				//System.out.println("timeDelay: "+ time+ "  speedValue= "+ speedValue+ " angleValue= "+angle.getValue());
				//System.out.println("Value of i is: " +i+ " Value of increase ="+ increment);
				x= point[0]+ speedValue*Math.cos(angleValue)*(increment);
				//System.out.print("Current x = "+x);
				y= getHeight()-(point[1]+speedValue*Math.sin(angleValue)*(increment)-0.5*9.81*(increment*increment));
				//System.out.println("   Current y = " + y);
				increment += 0.1;

				g2.setStroke(new BasicStroke(6f));
				g2.draw(new Rectangle2D.Double(x, y,6,6));
				if(i==time*10) {
					//System.out.println("the Final Value of x and y is "+ x + " and "+ y);
					endPoint[0]= x;
					endPoint[1]= y;
				}			

			}


			return endPoint;//endPoint;//this is the point where the projectile explodes
		}

		public ArrayList<double[]> secondLaunch(Graphics g, double[] point) {
			ArrayList<double[]> endPoints = new ArrayList<double[]>();
			Graphics2D g2 = (Graphics2D)g;//creating 2d graphics object
			Random randomValue = new Random();
			double[] endPoint = new double[2];
			for(int i=0; i<=5; i++) {
				int randomAngle = randomValue.nextInt(180);//generates a random angle between 0 and 180
				double angleValue = Math.PI*randomAngle/180;//converts the user's input to radians
				int speedValue = speed.getValue();
				int time = randomValue.nextInt(5);
				double increment=0;//this will help me plot the X and Y co-ordinates of the projectile every 0.01 second

				for(int j =0; j<=time*10; j++) {//this plots the position of the projectile every 0.01
					//System.out.println("timeDelay: "+ time+ "  speedValue= "+ speedValue+ " angleValue= "+angle.getValue());
					//System.out.println("Value of i is: " +i+ " Value of increase ="+ increment);
					x= point[0]+ speedValue*Math.cos(angleValue)*(increment);
					//System.out.print("Current x = "+x);
					y= (point[1]-speedValue*Math.sin(angleValue)*(increment)-0.5*9.81*(increment*increment));
					//System.out.println("   Current y = " + y);
					increment += 0.1;

					g2.setStroke(new BasicStroke(3f));
					g2.draw(new Rectangle2D.Double(x, y,3,3));
					if(j==time*10) {
						//System.out.println("the Final Value of x and y is "+ x + " and "+ y);
						double[] ending = {x,y};
						endPoints.add(ending);

					}		


				}

			}
			return endPoints;//endPoint;//this is the point where the projectile explodes
		}

		public void explosion(Graphics g, double[] point) {// graphics for different explosions
			Graphics2D g2 = (Graphics2D)g;//creating 2d graphics object
			Random random =  new Random();
			if((pattern.getSelectedItem().toString()).equalsIgnoreCase("Jamaica")) {
				double r=0;
				g2.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
				g2.setStroke(new BasicStroke(5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
//				float[] dash2 = { 1f, 10f, 2f };
//				BasicStroke bs2 = new BasicStroke(2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 5.0f, dash2, 5f);
//				g2.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
//				g2.setStroke(bs2);
//				for(double i=0.0; i<=6.284;i+=0.2) {
//
//					g2.draw(new Line2D.Double(point[0],point[1], point[0]+120*Math.cos(i), point[1]-120*Math.sin(i)));
//				}
				for(double z = 0; z<=Math.PI*2; z+= Math.PI/2) {
					double p = point[0] + 150* Math.cos(z);
					double q = point[1] - 150*Math.sin(z);

					g2.setColor(Color.green);

					for(double i=Math.PI; i<=3*Math.PI;i+=0.001) {
						r=(1+0.9*Math.cos(8*i))*(1+0.1*Math.cos(24*i))*(0.9+0.1*Math.cos(200*i))*(1+Math.sin(i));
						double x = p+ 50*r*Math.cos(i);
						double y = q- 50*r*Math.sin(i);
						g2.setStroke(new BasicStroke(2f));
						g2.draw(new Ellipse2D.Double(x, y,1,1));
					}



				}
			}
			else if((pattern.getSelectedItem().toString()).equalsIgnoreCase("Glitch")) {
				g2.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));

				float[] dash1 = { 5f, 5f, 5f };
				BasicStroke bs1 = new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 5.0f, dash1, 10f);
				g2.setStroke(bs1);
				for(double i=0.0; i<=6.284;i+=0.05) {
					g2.draw(new Line2D.Double(point[0],point[1], point[0]+200*Math.cos(i), point[1]-200*Math.sin(i)));

				}
				float[] dash2 = { 1f, 10f, 2f };
				BasicStroke bs2 = new BasicStroke(2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 5.0f, dash2, 5f);
				g2.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
				g2.setStroke(bs2);
				for(double i=0.0; i<=6.284;i+=0.2) {

					g2.draw(new Line2D.Double(point[0],point[1], point[0]+120*Math.cos(i), point[1]-120*Math.sin(i)));
				}

			}
			else if((pattern.getSelectedItem().toString()).equalsIgnoreCase("Clanderon")) {
				g2.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));

				for(double i=0.0; i<=6.284;i+=0.1) {
					g2.draw(new Ellipse2D.Double(point[0]+200*Math.cos(i), point[1]-200*Math.sin(i), 15, 16));

				}
				g2.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
				for(double i=0.0; i<=6.284;i+=0.1) {
					g2.draw(new Ellipse2D.Double(point[0]+175*Math.cos(i), point[1]-175*Math.sin(i), 15, 16));

				}
				g2.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
				for(double i=0.0; i<=6.284;i+=0.1) {
					g2.draw(new Ellipse2D.Double(point[0]+150*Math.cos(i), point[1]-150*Math.sin(i), 15, 16));

				}
				g2.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
				for(double i=0.0; i<=6.284;i+=0.1) {
					g2.draw(new Ellipse2D.Double(point[0]+120*Math.cos(i), point[1]-120*Math.sin(i), 15, 16));

				}
				g2.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
				for(double i=0.0; i<=6.284;i+=0.2) {

					g2.draw(new Line2D.Double(point[0],point[1], point[0]+145*Math.cos(i), point[1]-145*Math.sin(i)));

				}

				g2.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
				g2.setStroke(new BasicStroke(5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
				for(double i=0; i<=6.29;i+=0.2) {
					g2.draw(new Line2D.Double(point[0],point[1], point[0]+75*Math.cos(i), point[1]-75*Math.sin(i)));

				}
			}

			else if((pattern.getSelectedItem().toString()).equalsIgnoreCase("Aeon")) {
				g2.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
				g2.setStroke(new BasicStroke(5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
				float[] dash2 = { 1f, 10f, 2f };
				BasicStroke bs2 = new BasicStroke(2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 5.0f, dash2, 5f);
				g2.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
				g2.setStroke(bs2);
				for(double i=0.0; i<=6.284;i+=0.2) {

					g2.draw(new Line2D.Double(point[0],point[1], point[0]+120*Math.cos(i), point[1]-120*Math.sin(i)));
				}
				for(double z = 0; z<=Math.PI*2; z+= Math.PI/4) {
					double p = point[0] + 100* Math.cos(z);
					double q = point[1] - 100*Math.sin(z);

					double a=16, b=13, c=5, d=2, e = 1;
					g2.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
					for(double j =1; j<=3; j+=0.8) {
						for(double i=(1-4); i<=3;i+=0.01) {

							double x = p+ a*Math.pow(Math.sin(i),3);
							double y = q- (b*Math.cos(i)-c*Math.cos(2*i)-d*Math.cos(3*i)-e*Math.cos(4*i));
							g2.setStroke(new BasicStroke(2f));
							g2.draw(new Ellipse2D.Double(x, y,2,2));
						}
						a = a*j; b = b*j; c = c*j; d = d*j; e = e*j;
					}
				}
			}
			else if((pattern.getSelectedItem().toString()).equalsIgnoreCase("Nirvana")) {
				double r = 0;
				g2.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
				float[] dash2 = { 50f, 10f, 20f };
				BasicStroke bs2 = new BasicStroke(2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 5.0f, dash2, 5f);
				g2.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
				g2.setStroke(bs2);

				for(double i=0.1; i<=31.42;i+=0.01) {
					r =(Math.pow(Math.sin(1.2*i),2) + Math.pow(Math.cos(6*i),3));
					double x = point[0]+ 100*r*Math.cos(i);
					double y = point[1]- 100*r*Math.sin(i);
					g2.setStroke(new BasicStroke(2f));
					g2.draw(new Ellipse2D.Double(x, y,2,2));
				}

			}

		}
	}



	public static void main(String[] args) {
		new Projectile();
	}

}