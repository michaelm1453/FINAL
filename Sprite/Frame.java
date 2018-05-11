import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Frame extends JPanel implements ActionListener
{
    private Sprite sprite;
    private Timer timer;
    private final int DELAY = 10;
    private String type = "1";
    public String NPC;
    
    //where npcs are located on x y plane
    static int npc1x = 450;
    static int npc1y = 120;
    
    //where talk images are located on x y plane
    private static int npcTalkx = 150;
    private static int npcTalky = 90;
    int charaTalkx = 600;
    int charaTalky = 90;
    private int totalscore = 100;
    File file = new File("Level1Dialogue.txt");
    String dialogue = "Take out the trash. So get out.";
    Scanner sc;
    //Pixel pixels = new Pixel("Images//hardwareskeleton.png");
    
    public Frame(String typeOfCharacter)
    {
        type = typeOfCharacter;
        try{sc = new Scanner(file);}
        catch(FileNotFoundException e){}
        initFrame();
    }

    private void initFrame()
    {
        addKeyListener(new AAdapter()); //see private class below
        setFocusable(true);
        
        sprite = new Sprite(type);
        timer = new Timer(DELAY, this); //why do you need a timer? for the syncing part. 
        timer.start();//no timer, no movement
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        draw(g, sprite.isTalking());
        Toolkit.getDefaultToolkit().sync(); //syncs the graphics with the Frame
    }
    
    public void draw(Graphics g, boolean talk)
    {
        Graphics2D graphix = (Graphics2D) g;
        //score bars
            
        if(sprite.levelUp() == 1)
        {
            npc1x=450;
            npc1y=120;
            sprite.setInterior2(300, 480, 761, 365); //these are the critical points for entering/exiting the hardware store. 
            sprite.setInterior1(321, 434, 253, 130); //critical points for entering/exiting the house
            ImageIcon background = new ImageIcon("Images//Background.jpg");
            if(sprite.inside() == 1){
                background = new ImageIcon("Images//Interior1_1.png");
                npc1x = 10000; npc1y = 10000; //sets the npc hit boxes really far away so it just doesn't cause problems
            }
            else if(sprite.inside() ==2){
                background = new ImageIcon("Images//Interior1_2.png");
                npc1x = 10000; npc1y = 10000;
            }
            Image background1 = background.getImage();
            //Graphics2D graphix = (Graphics2D) g;
            graphix.drawImage(background1, 0,0, this);
            //graphix.drawImage(sprite.getImg(), sprite.getX(), sprite.getY(), this); //draws the sprite (this will be really helpful later)
            
            //npcs
            ImageIcon npc1This = new ImageIcon("Images//npcs//" + type + "//idle.png");
            Image npc1 = npc1This.getImage();
            NPC = "Lv1Mom";
            graphix.drawImage(npc1, npc1x, npc1y, this);
            sprite.whichNPC(NPC);

            //score bars
            graphix.setColor(Color.WHITE);
            graphix.fillRect(0,0, 200, 25);
            graphix.setColor(Color.RED);
            if(education_score(0) < 200)
                graphix.fillRect(0,0, education_score(1), 25);
            /*else
                graphix.fillRect(0,0, education_score(0), 25);*/
            graphix.setColor(Color.WHITE);
            graphix.fillRect(0,25, 200, 25);
            graphix.setColor(Color.GREEN);
            if(money_score(0) < 200)
                graphix.fillRect(0,25, money_score(2), 25);
            /*else
                graphix.fillRect(0,25, money_score(0), 25); */
            graphix.setColor(Color.WHITE);
            graphix.fillRect(0,50, 200, 25);
            graphix.setColor(Color.BLUE);
            if(love_score(0) < 200)
                graphix.fillRect(0,50, love_score(3), 25);
            /*else
                graphix.fillRect(0,50, love_score(0), 25);*/
            
            graphix.drawImage(sprite.getImg(), sprite.getX(), sprite.getY(), this); //I just moved the code so the character is superimposed over the npc
            //System.out.println(sprite.getX() + "" + sprite.getY());
            
            if (talk)
            {
                //file = new File("Text//" + sprite.levelUp() + "//" + type + ".txt");
                
                ImageIcon charaTalk = new ImageIcon("Images//" + type + "//idletalk.png");
                Image charaImage = charaTalk.getImage();
                graphix.drawImage(charaImage, charaTalkx, charaTalky, this);
            
                ImageIcon npcTalk = new ImageIcon("Images//npcs//" + type + "//idletalk.png");
                Image npcImage = npcTalk.getImage();
                graphix.drawImage(npcImage, npcTalkx, npcTalky, this);
    
                ImageIcon textbox = new ImageIcon("Images//textbox.png"); //code for the little text box that conversation appears
                Image textboxpic = textbox.getImage();
                graphix.drawImage(textboxpic, 63, 290, this);
                graphix.setColor(Color.BLACK);
                graphix.drawString(dialogue, 90, 400);
                if(sprite.advance) //this will go through the files for conversation and display them
                { 
                    sc.useDelimiter("\n");
                    if(sc.hasNext())
                        dialogue = sc.next();
                        else
                        {
                            dialogue = "";
                            //if(sprite.choice() == 1)
                            //dialogue = "Excellent choice...";

                        }
                    sprite.advance = false;
                }
                
                ImageIcon goon = new ImageIcon("Images//go.png");
                Image go = goon.getImage();
                graphix.drawImage(go, 750, 520, this);
            }
        }
        if(sprite.levelUp() == 2)
        {
            sprite.setInterior2(567, 300, 554, 187); //critical points for each of the respective interiors
            sprite.setInterior1(390, 490, 114, 414);
            ImageIcon background2 = new ImageIcon("Images//background2.jpg");
            if(sprite.inside() == 1){
                background2 = new ImageIcon("Images//Interior2_1.png");
                npc1x = 10000; npc1y = 10000; //boots the npc hit boxes off the screen
            }
            else if(sprite.inside() ==2){
                background2 = new ImageIcon("Images//Interior2_2.png");
                npc1x = 10000; npc1y = 10000;
            }
            npc1x = 10000; npc1y = 10000;
            Image background3 = background2.getImage();
            //Graphics2D graphix = (Graphics2D) g;
            graphix.drawImage(background3, 0,0, this);
            
            //score bars
            graphix.setColor(Color.WHITE);
            graphix.fillRect(0,0, 200, 25);
            graphix.setColor(Color.RED);
            if(education_score(0) < 200)
                graphix.fillRect(0,0, education_score(1), 25);
            /*else
                graphix.fillRect(0,0, education_score(0), 25);*/
            graphix.setColor(Color.WHITE);
            graphix.fillRect(0,25, 200, 25);
            graphix.setColor(Color.GREEN);
            if(money_score(0) < 200)
                graphix.fillRect(0,25, money_score(2), 25);
            /*else
                graphix.fillRect(0,25, money_score(0), 25); */
            graphix.setColor(Color.WHITE);
            graphix.fillRect(0,50, 200, 25);
            graphix.setColor(Color.BLUE);
            if(love_score(0) < 200)
                graphix.fillRect(0,50, love_score(3), 25);
            /*else
                graphix.fillRect(0,50, love_score(0), 25);*/
                
            graphix.drawImage(sprite.getImg(), sprite.getX(), sprite.getY(), this);
        }
        if(sprite.levelUp() == 3) //just a blank level for now, he can leave and he can go on to the bridge of the Enterprise. This is just a placeholder.
        {
            ImageIcon background4 = new ImageIcon("Images//Test_Background.jpg");
            Image background5 = background4.getImage();
            graphix.drawImage(background5, 0, 0, this);
            graphix.drawImage(sprite.getImg(), sprite.getX(), sprite.getY(), this);
        }
    }
    
    private void choices(Graphics g)
    {
        Graphics2D graphix = (Graphics2D) g;
        graphix.drawString("OK HERE WE GO", 90, 400);
    }
    
    public static int getnpc1x() //getters in case we ever need them
    {
        return npc1x;
    }
    
    public static int getnpc1y()
    {
        return npc1y;
    }
    
    public boolean isTalking()
    {
        return sprite.isTalking();
    }
    
    public int love_score(int increase) //this is how you can change each of the scores
    {
        sprite.LOVE_SCORE += increase;
              
        return sprite.LOVE_SCORE;
    }
    
    public int education_score(int increase)
    {
        sprite.EDUCATION_SCORE += increase;
        
        return sprite.EDUCATION_SCORE;
    }
    
    public int money_score(int increase)
    {
        sprite.MONEY_SCORE += increase;
        
        return sprite.MONEY_SCORE;
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (!sprite.isTalking() || sprite.stopTalk())
        {
            sprite.move();
        }
        repaint();
    }

    //I might delete this later for conciseness sake and just incorporate it in another part. Until i do that or someone else wants to, this is staying.
    private class AAdapter extends KeyAdapter
    {
        @Override
        public void keyReleased(KeyEvent e)
        {sprite.keyReleased(e);}
        @Override
        public void keyPressed(KeyEvent e)
        {
            sprite.keyPressed(e);
        }
    }

}