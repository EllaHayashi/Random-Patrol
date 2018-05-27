
/*
 * Lab6: RandomPatrol.Java
 * @author: Ella Hayashi, 001A-B
 * Nov 5th 2016, 3:13 PM
 * Purpose: This program simulates a drunkard on a grid of 11 by 11 streets.
 *          The drunkard walks randomly on the streets, choosing between
 *          four directions, up, down, left, and right, while there are two 
 *          stationary police men patroling the area. The drunkard uses 
 *          up to 50 moves where he tries to escape the area, each move
 *          is the equivalent of a block of 100 meters. If the drunkard
 *          escapes then he is successful, and the distance it took him to
 *          escape is shown. If he does not escape, then he is unsuccessful,
 *          and he is also unsuccessful if he runs into a policeman.
 *          The program forces us to use loops, and teaches us more efficient
 *          way's to program.
 *
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class RandomPatrol extends JFrame implements ActionListener {

    private JButton button;
    private JPanel panel;

 
    public static void main(String[] args) 
    {
        RandomPatrol frame = new RandomPatrol();
        frame.setSize(350,350);   
        frame.createGUI();
        frame.setVisible(true);
    }

    private void createGUI() 
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout() );
      
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(275, 275));
        panel.setBackground(Color.white);
      
        window.add(panel);
        button = new JButton("go");       //button 'go' simulates drunkard
        window.add(button);
        button.addActionListener(this); 
    }
 
   /*
    * In the method actionPerformed, we first simulate our two police men, 
    * who are represented as two red circles, and are randomly placed on the 
    * grid. 
    * We then use a for loop, to simulate our drunkard. The for loop runs
    * for 50 intervals, and only stops when the 50 intervals have gone, or
    * when either the drunkards coordinates are the same as the policemens, or
    * if the drunkard escapes the area. Since the drunkards movments are
    * random, a switch statment and if/else statements are used to determin
    * the movment of the drunkard.
    */
    @Override
    public void actionPerformed(ActionEvent event) 
    {
        int firstPoliceX; //x-coordinate for the first police
        int firstPoliceY; //y-coordinate for the first police
        int secondPoliceX; //x-coordinate for the second police
        int secondPoliceY; //y-coordinate for the second police
        int direction; //direction change for the drunkard   
        int drunkX = 5; //x-coordinate for the drunkard
        int drunkY = 5; //y-coordinate for the drunkard
        int distance = 0; //distance the drunkard travles if he escapes
      
        Random policeX = new Random();
        Random policeY = new Random();
        
        //random variables x,y for the first police
        firstPoliceX = policeX.nextInt(10); 
        firstPoliceY = policeY.nextInt(10);
 
       /* random variables x,y for the second police (methods used so that
        * the cordinates of the second police are not the same as the
        * coordinates of the first police.
        */
        secondPoliceX = getPolice(firstPoliceX);
        secondPoliceY = getPolice(firstPoliceY);
        
        Graphics g = panel.getGraphics();
        g.setColor(Color.white);
        g.fillRect(0,0,275,275);
        
        //draw police method called)
        drawPolice(g, firstPoliceX, firstPoliceY, secondPoliceX, secondPoliceY);
         
        /*
        This for loop is the movment of the drunkard. The drunkard has 50 
        moves maximum, therefor the for loop increments from 1 to 51. If the
        drunkcard runs into the police or escapes the area, then the 50
        moves is cut short and the loop terminates.
        */
        for (int n = 1; n <=51; n++)
        {       
            //refills over the old drunkard after it moves.
            g.setColor(Color.white);
            g.fillOval (drunkX*25, (25)*drunkY, 25, 25);
            
            //method that draw's the grid
            drawGrid(g);  
    
            /*
            If the drunk's coordinates are the same as one of the polices
            coordinates, then the program termanates by setting n = 51.
            Note: this must go at the begining of the loop before the direction
            change of the drunkard
            */
            if(drunkX == firstPoliceX && drunkY == firstPoliceY || 
               drunkX == secondPoliceX && drunkY == secondPoliceY)
            {
               n=51;
            }
            else
            {
                /*
                This method calls the direction of the drunakard. It receives 
                a random number, either 0, 1, 2, or 3. 
                0: represents moving north
                1: represents moving east
                2: represents moving south
                3: represent moving west 
                */
                direction = getDirection();

                /*
                switch statment is used to seperate the four value's retreived,
                and decide the drunkards movment based on it's current 
                possition.
                */
                switch(direction)
                {
                    case 0:                             // Drunkard moves north
                        /*
                        if the drunk is at the top then the drunkard escapes. 
                        The distance is calculated by the current 'n', then 
                        set n=51, so that the for loop terminantes
                        */
                        if(drunkY==0)      
                        {
                            distance = getDistance(n);         
                            n=51;
                        }
                        drunkY= drunkY-1;   //Y coordinate of drunk decreases    
                        break;
                    case 1:                             // Drunkard moves east 
                        /*
                        if the drunk is at the right then the drunk escapes. 
                        The distance is calculated by the current 'n', then 
                        set n=51, so that the for loop terminantes
                        */
                        if (drunkX ==10)
                        {
                            distance = getDistance(n);
                            n=51;
                        }
                        drunkX = drunkX+1;    //X coordinate of drunk increases
                        break;
                    case 2:                             // Drunkard moves South   
                        /*
                        if the drunk is at the bottom then the drunk escapes. 
                        The distance is calculated by the current 'n', then 
                        set n=51, so that the for loop terminantes
                        */
                        if(drunkY==10)
                        {
                            distance = getDistance(n);
                            n=51;   
                        }
                        drunkY = drunkY +1;  //Y coordinate of drunk increases
                        break;
                    case 3:                             // Drunkard moves west   
                        /*
                        if the drunkard is at the top then the drunk escapes. 
                        The distance is calculated by the current 'n', then 
                        set n=51, so that the for loop terminantes
                        */
                        if(drunkX==0)
                        {
                            distance = getDistance(n);
                            n=51;
                        }
                        drunkX = drunkX-1;   //X coordinate of drunk decreases
                        break;        
                }        
            }
            //draw's the drunk as a black circle.
            g.setColor(Color.black);
            g.fillOval (drunkX*25, (25)*drunkY, 25, 25);  
            
            //method delay is used to delay the movment of the drunk
            delay();               
        }
        
        /*
        if else statment for if the drunkard escaped, if the drunkard was 
        caught by the police, or if it didn't escape.
        */
        if (distance>0)
        {
            JOptionPane.showMessageDialog(null, "The distance the drunkard "
                    + "travled before escaping was:" + distance +"m");    
        }
        else if (((drunkX == firstPoliceX) && (drunkY == firstPoliceY)) || 
                ((drunkX == secondPoliceX) && (drunkY == secondPoliceY))){
            JOptionPane.showMessageDialog(null, "The drunkard was stopped "
                    + "by the police");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "the drunkard was still "
                    + "inside after 50 moves"); 
        }
    }
 
    /*
    method that draw's the grid of 11 by 11 streets. It uses a loop to draw
    the horizontal lines and the virticle lines
    */
    private void drawGrid(Graphics g)
    {    
        g.setColor(Color.black);
    
        for (int n=0; n<=11; n++)
        {  
            g.drawLine(25*n, 0, 25*n, 275);
            g.drawLine(0, 25*n, 275, 25*n);
        }  
    }
    
    /*
    Method to get the random variables for the second police, as to make
    sure they are not the same as the first police's coordinates.
    It uses a do/while loop, so that it recalculates while it's coordinates
    equal the coordinates of the value's sent down. It return's the x or y
    coordinate that it was called for.
    */
    private int getPolice(int x)
    {
        int y;
        Random position = new Random();       
        do 
        {
            y = position.nextInt(10);
        }while (x==y);        
        return(y);       
    }
    
    /*
    method draw's the police, represented by a red circle.
    */
    private void drawPolice(Graphics g, int x1, int y1, int x2, int y2)
    {
        g.setColor(Color.red);
        g.fillOval(25*x1, 25*y1, 25, 25);
        g.fillOval(25*x2, 25*y2, 25, 25);    
    }
    
    /*
    method that retreives the random direction of the drunkard. There are
    only four options the drunkard can move, and these are represented by
    0, 1, 2 or 3.
    */
    private int getDirection()
    {
        int n;
        Random direction = new Random();
        n = direction.nextInt(4);
        return(n);
    }  
    /*
    method delay delay's the program by 200 milliseconds, so that the
    movments of the drunk are better seen.
    */
    private void delay()
    {
        try 
        {
            Thread.sleep(200);
        } 
        catch (InterruptedException e) {}
    }     
    
    /*
    method that calculates the distance the drunkard has moved before it
    it escapes. Uses the number of movements 'n', it has taken, and multiplies
    that by 100, as every movement is one block of 100 meters.
    */
    private int getDistance(int n)
    {
        int distance = n*100;
        return (distance);    
    }
}
  

  
