//********************************************************************
//  BrickWall.java       Author: Lewis and Loftus
//  
//  Solution to Programming Project 3.19 (page 173).
//********************************************************************

import java.applet.Applet;
import java.awt.*;

public class BrickWall extends Applet
{
   private int NUM_ROWS = 10;
   private int NUM_COLS = 2;
   private int START_X = 0;
   private int START_Y = 0;
   private final int BRICK_WIDTH = 32;
   public final static int BRICK_HEIGHT = 16;
   public final static int GAP = 4;

   //-----------------------------------------------------------------
   //  Paints a brick wall.
   //-----------------------------------------------------------------
   public BrickWall(int NUM_ROWS, int NUM_COLS, int START_X, int START_Y ) {
	   this.NUM_ROWS = NUM_ROWS;
	   this.NUM_COLS = NUM_COLS;
	   this.START_X = START_X;
	   this.START_Y = START_Y;
	   
   }
   public void paint(Graphics page)
   {
	   page.setColor(new Color(139, 79, 57));
      int x = START_X, y = START_Y;
      
      setBackground (Color.BLUE);

      for (int row=1; row <= NUM_ROWS; row++)
      {

         for (int col=1; col <= NUM_COLS; col++)
         {
            page.fillRect (x, y, BRICK_WIDTH, BRICK_HEIGHT);
            x += BRICK_WIDTH + GAP;
         }

         if (row%2 == 0)
            x = START_X;
         else
            x = START_X - BRICK_WIDTH/2 - GAP/2;

         y += BRICK_HEIGHT + GAP;

      }
   }
   
}