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
import java.awt.geom.Ellipse2D.Double;
import java.util.*;
import java.awt.List;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.event.*;


public class Graphipult extends JFrame {
	int highScore;
	int round;
	static int soundNo;
	public int length;
	double height;
	double gravity = 9.81;
	Boolean activeGUI = false;//deactivates the GUI until the user clicks 
	MusicClip clip = new MusicClip();

	//Slider meant to control projectile angle
	JSlider angle = new JSlider(JSlider.HORIZONTAL, 30, 89, 60);

	//Slider meant to control initial velocity of the projectile.
	JSlider speed = new JSlider(JSlider.HORIZONTAL, 50,200, 100);

	//JTextfield that show the rounds left
	JTextField roundField = new JTextField(round + " rounds left");

	//JTextfield that shows the score
	JTextField scoreField = new JTextField("Score is: "+highScore);

	//Button meant to make the controls active
	JButton start = new JButton("Start Game");

	//Button meant to implement new firework simulation whenever changes are made in the GUI
	JButton launcher = new JButton("Launch");

	//Button meant to reset the game and start afresh

	JButton reset =  new JButton("Reset");

	int refresh =0;

	//Creating Tapestry object as global variable so the ControlPanel class can access it
	Tapestry canvas = new Tapestry();

	//Creating control panel object as a global variable so the Tapestry class can access it
	ControlPanel cp = new ControlPanel();

	public Graphipult() {
		setTitle("Graphipult Game");
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLayout(new BorderLayout());


		cp.setLayout(new WrapLayout());
		add(cp, BorderLayout.NORTH);

		canvas.setBackground(Color.BLACK);
		add(canvas, BorderLayout.CENTER);

		pack();
		setVisible(true);
	}

	protected class ControlPanel extends JPanel implements ActionListener, ChangeListener{

		int gameStart = 1;
		//Constructor for control panel window
		public ControlPanel() {
			super();
			//starts GUI
			start.addActionListener(this);
			add(start);


			roundField.setEditable(false);// Shows how many rounds left
			roundField.setColumns(30);
			add(roundField);

			scoreField.setEditable(false);
			scoreField.setColumns(30);	//Shows current score
			add(scoreField);

			angle.addChangeListener(this);
			angle.setPaintLabels(true);
			angle.setPaintTicks(true);
			angle.setMinorTickSpacing(1);
			angle.setMajorTickSpacing(5);
			angle.setEnabled(false);
			JLabel angleLabel = new JLabel("Projectile \nAngle:");
			add(angleLabel);
			add(angle);

			speed.setPaintLabels(true);
			speed.setPaintTicks(true);
			speed.setMinorTickSpacing(5);
			speed.setMajorTickSpacing(25);
			speed.setEnabled(false);
			JLabel speedLabel = new JLabel("Initial \nVelocity(m/s):");
			add(speedLabel);
			add(speed);



			reset.addActionListener(this);
			reset.setEnabled(false);
			add(reset);

			launcher.addActionListener(this);
			launcher.setEnabled(false);
			add(launcher);
			pack();
		}

		@Override
		public void stateChanged(ChangeEvent e) {
			if(angle.getValueIsAdjusting()&&!canvas.timex.isRunning()) {
				canvas.adjustCanon();
				System.out.println("Slider is adjusting when timer isnt running");
			}

		}

		@Override
		public void actionPerformed(ActionEvent e) {

			Object src = e.getSource();
			
			if(src == start) {
				if(gameStart==1) {
					round = 5;
				}
					canvas.setValues();
					angle.setEnabled(true);
					speed.setEnabled(true);
					reset.setEnabled(true);
					launcher.setEnabled(true);//will start game when clicked
					canvas.repositionWall();
					canvas.y = 0;
					gameStart = 0;

			}
			else if(src == launcher) {
				soundNo =1;
				clip.play();
				canvas.increment =0;
				if(round==0) {
					canvas.gameOver();
				}
				canvas.execute = true;
				roundField.setText(round+ " rounds left");
				System.out.println("Launcher Button called");
				canvas.timex.start();
				round = round-1;
				soundNo = 12;

			}

			else if(src == reset) {
				gameStart=1;
				round=5;
				highScore = 0;
				canvas.setValues();
			}

		}
	}

	protected class Tapestry extends JPanel implements ActionListener{
		Timer timex = new Timer(10,this);
		public boolean permission;
		Graphics graphicObject;
		public double final_Height;
		public double theta = 0.1;// angle will be used to rotate the ball object
		public double increment=0;//this will help me plot the X and Y co-ordinates of the projectile every 0.01 seconds
		public double[] pointOfOrigin;
		public  BufferedImage img;// will be used as background image
		public  BufferedImage img2; // will be used as projectile
		AffineTransform at = new AffineTransform();// will be used to rotate the buffered image

		BrickWall bw;  


		public Tapestry() {
			super();

			try {
				img = ImageIO.read(new File("Image/wallpaper.jpg"));
				img2= ImageIO.read(new File("Image/minCircle.png"));
			} catch (IOException e) {
			}
			setSize(8000,8000);
		}
		boolean execute = true;
		double x=0;
		double y=getHeight();
		Random random = new Random();
		double angleValue = Math.PI*angle.getValue()/180;//converts the user's input to radians



		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			//Setting Background
			Graphics2D g2 = (Graphics2D)g;
			g2.drawImage(img,
					0, 0, 
					getWidth(), getHeight(),
					0, 0 ,
					img.getWidth(), img.getHeight(),
					null);

			//System.out.println("Paint component has been called sir!");


			//Canon Object Code
			Ellipse2D.Double chem1 = new Ellipse2D.Double(0,getHeight()-50,50,50);
			Line2D.Double chem2 = new Line2D.Double(25, getHeight()-25, 25+(50*Math.cos(Math.PI*angle.getValue()/180)), (getHeight()-25)-(50*Math.sin(Math.PI*angle.getValue()/180)));
			Graphics2D gCanon = (Graphics2D)g;
			gCanon.setStroke(new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			gCanon.setColor(Color.red);
			gCanon.draw(chem1);
			gCanon.draw(chem2);

			if(bw!=null)
				bw.paint(g);

			graphicObject = g;


			Graphics2D g3 = (Graphics2D)g.create();

			if(y!=0) {
				at =AffineTransform.getRotateInstance(theta, x+img2.getWidth()/2, y+img2.getHeight()/2);
				theta +=0.23;
				if(theta >= 359) {
					theta=0;}
				g3.transform(at);
				g3.drawImage(img2, (int)x, (int)y, null);
				g3.dispose();
				System.out.println("calling paintComponent in Tapestry current value of x and y are "+ x + " and "+y);
			}
		}


		public void repositionWall() {
			length = (random.nextInt(100)*5)+500;
			int rows = random.nextInt(20)+10;
			height = getHeight()-((BrickWall.BRICK_HEIGHT+BrickWall.GAP)*rows);
			bw = new BrickWall(rows,3,length,(int)height);
			repaint();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Object src = e.getSource();
			if(src == timex) {
				System.out.println("timer is working!!");
				animate(graphicObject);

			}

		}

		public void adjustCanon() {
			System.out.println("adjusting canooooon");
			repaint();
		}

		public void animate(Graphics g) {
			//Graphics2D g2 = (Graphics2D)graphics;
			pointOfOrigin= new double[2];
			pointOfOrigin[0] = 1;
			pointOfOrigin[1] = 1;
			launch(g,pointOfOrigin);
			System.out.print("animate has been called!!");
		}

		public void results() {
			System.out.println("resultsssssssssssssssssssssssssss");
			//final_Height = (length*Math.tan(angle.getValue()) - ((gravity *Math.pow(length,2)) / (2*Math.pow((speed.getValue()*Math.cos(angle.getValue())),2))));
			while((length<=x&&length+20>=x)&&y>=getHeight()-height) {
				System.out.println("the projectile has hit at x: " + x+ " and y:"+ y +" height:" + height +" length: "+ length);
				clip.stop();
				soundNo=2;
				clip.play();
				timex.stop();
				highScore = highScore+4;
				scoreField.setText("HIT!! " + "score = " + highScore);

				execute = false;
				break;
			}
			while(execute) {
				if((length<=x&&length+20>=x)&&y>=getHeight()-height-100) {
					highScore = highScore + 1;
					scoreField.setText("Near miss... " + "score = " + highScore);
				}
				execute = false;
				break;
			}
			while(execute) {
				if(length<=x&&y<=getHeight()-height-50) {
					highScore = highScore -2;
					scoreField.setText("Way over! " + "score = " + highScore);
				}
				execute = false;
			}





			//do you still want to try this specific problem again?
			if (highScore < 1) {
				System.out.println("Your score is too low you lose!... "  + highScore);		
			}// kills run this problem while loop

		}
		public void setValues() {

			roundField.setText(round+ " rounds left");
			scoreField.setText("Scores: "+ highScore);



		}
		public void gameOver() {
			angle.setEnabled(false);
			speed.setEnabled(false);
			reset.setEnabled(false);
			launcher.setEnabled(false);
		}

		public void launch(Graphics g, double[] point) {

			int speedValue = speed.getValue();

			//System.out.println("timeDelay: "+ time+ "  speedValue= "+ speedValue+ " angleValue= "+angle.getValue());
			//System.out.println("Value of i is: " +i+ " Value of increase ="+ increment);

			x= point[0]+ speedValue*Math.cos(angleValue)*(increment);
			//System.out.print("Current x = "+x);
			y= getHeight()-(point[1]+speedValue*Math.sin(angleValue)*(increment)-0.5*9.81*(increment*increment));
			//System.out.println("   Current y = " + y);
			increment += 0.1;
			roundField.setText(round+ " rounds left");
			repaint();
			results();
			if(y>=getHeight()||x>=getWidth()) {
				timex.stop();
				clip.stop();
				System.out.println("this Code executes when it crosses the border");
				increment=0;
				if(x<length) {
					System.out.println("You throw like a little girl :(!!!!!!");
					highScore = highScore - 3;
					scoreField.setText("You throw like a little girl :(" + "score = " + highScore);
				}
				else if(x>length) {
					System.out.println("Way over!!!");
					highScore = highScore - 3;
					scoreField.setText("Way over!!" + "score = " + highScore);
				}
			}

		}



	}

	public static void main(String[] args) {
		new Graphipult();
	}

}