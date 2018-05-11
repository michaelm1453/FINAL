import java.awt.event.KeyEvent;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.Scanner;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.util.Map;
import java.util.HashMap;

public class Sprite
{
    private int dx; private int dy; private int x = 436; private int y= 268;
    private int defaultx = 435; private int defaulty = 268;
    //dx and dy will be for the rate the sprite is moving
    //x and y are the position of the thing
    private Image image;
    private String typeChar = "";
    private ImageIcon sprite;
    private int[][] arr;
    private boolean movingLeft = false; //these variables will be used so the program knows what point to test for collisions with a border
    private boolean movingUp = false;
    private boolean movingDown = false;
    private boolean movingRight = false;
    private int width; private int height; //width and height of the image, will be used to change what corner to use to test for collisions
    private boolean talkNow = false;
    private boolean stoppedTalking = true;
    private Pixel pixels;
    public static int LOVE_SCORE = 0;
    public static int MONEY_SCORE = 0;
    public static int EDUCATION_SCORE = 0;
    public static boolean advance;
    public int nextLevel = 1;
    private int choice = 0;
    public int interior = 0;
    private int entrancex = 0; private int entrancey = 0; private int exitx = 0; private int exity = 0;
    private int entrancex1 = 0; private int entrancey1 = 0; private int exitx1 = 0; private int exity1 = 0;
    public String NPC;
    
    private int tempLove;
    private int tempEducation;
    private int tempMoney;
    private String tempLoveString = "";
    private String tempEducationString = "";
    private String tempMoneyString = "";
    private String needsParsing;
    private int index;
    
    public Map <String, String> Qkey = new HashMap<>();
    public Map <String, String> Wkey = new HashMap<>();
    public Map <String, String> Ekey = new HashMap<>();
    public Map <String, String> Rkey = new HashMap<>();
    public Map <String, String> Tkey = new HashMap<>();
    public Map <String, String> Ykey = new HashMap<>();
    public Map <String, String> Ukey = new HashMap<>();
    
    public Sprite(String type)
    {
        typeChar = type;
        createSprite("Images//" + typeChar + "//Idle.png", x, y); //Creates a sprite of the given image
        pixels = new Pixel("Images//skeleton1.png");
       Qkey.put("Lv1Mom", "10:3:0");
        Wkey.put("Lv1Mom", "3:10:0");
        Ekey.put("Lv1Mom", "8:2:0");
        Rkey.put("Lv1Mom", "2:8:0");
        Tkey.put("Lv1Mom", "6:1:0");
        Ykey.put("Lv1Mom", "1:6:0");
        
        //Level 1 Friend options
        Qkey.put("Lv1Friend", "0:0:20");
        Wkey.put("Lv1Friend", "0:0:18");
        Ekey.put("Lv1Friend", "0:0:15");
        Rkey.put("Lv1Friend", "0:0:12");
        Tkey.put("Lv1Friend", "0:0:10");
        Ykey.put("Lv1Friend", "0:0:5");

        //Level 1 Stranger options
        Qkey.put("Lv1Stranger", "10:0:0");
        Wkey.put("Lv1Stranger", "0:0:10");
        Ekey.put("Lv1Stranger", "0:10:0");
        Rkey.put("Lv1Stranger", "0:0:5");
        Tkey.put("Lv1Stranger", "5:0:0");
        Ykey.put("Lv1Stranger", "0:5:0");
        Ukey.put("Lv1Stranger", "0:0:-5");
        
        //Level 1 Teacher options
        Qkey.put("Lv1Teacher", "0:0:8");
        Wkey.put("Lv1Teacher", "8:0:0");
        Ekey.put("Lv1Teacher", "4:0:0");
        Rkey.put("Lv1Teacher", "0:0:4");
        Tkey.put("Lv1Teacher", "-2:0:0");
        Ykey.put("Lv1Teacher", "0:0:-2");
        
        //Level 2 Friend options
        Qkey.put("Lv2Friend", "4:0:0");
        
        //Level 2 Friend-2 options (unlocked after first talk)
        Qkey.put("Lv2Friend-2", "5:0:0");
        Wkey.put("Lv2Friend-2", "0:0:0");
        Ekey.put("Lv2Friend-2", "-1:0:0");
        
        //Level 2 Jealous Boy/Apologizing Girl optins
        Qkey.put("Lv2BoyGirl", "-1:0:0");
        Wkey.put("Lv2BoyGirl", "2:0:0");
        
        //Level 2 Mom options
        Qkey.put("Lv2Mom", "4:0:0");
        
        //Level 2 Dad options
        Qkey.put("Lv2Dad", "5:0:0");
        
        //Level 2 Professor1 options
        Qkey.put("Lv2Professor1", "0:5:0");
        
        //Level 2 Professor2 options
        Qkey.put("Lv2Professor2", "0:5:0");
        
        //Level 2 Professor3 options
        Qkey.put("Lv2Professor3", "0:5:0");
        
        //Level 2 Professor3-2 options
        Qkey.put("Lv2Professor3", "0:0:0");
        Wkey.put("Lv2Professor3", "0:0:0");
        
        //Level 3 Surgeon1 options
        Qkey.put("Lv3Surgeon1", "3:0:0");
        Wkey.put("Lv3Surgeon1", "-1:3:0");
        
        //Level 3 Surgeon2 options
        Qkey.put("Lv3Surgeon2", "0:3:0");
        
        //Level 3 HeadSurgeon options
        Qkey.put("Lv3HeadSurgeon", "0:0:5");
        
        //Level 3 HeadSurgeon-2 options
        Qkey.put("Lv3HeadSurgeon-2", "0:0:0");
        Wkey.put("Lv3HeadSurgeon-2", "0:0:0");
        
        //Level 4 Friend options
        Qkey.put("Lv4Friend", "1:0:0");
        
        //Level 4 Friend-2 options (unlocked after first talk)
        Qkey.put("Lv4Friend-2", "5:0:0");
        Wkey.put("Lv4Friend-2", "0:0:0");
        Ekey.put("Lv4Friend-2", "-1:0:0");
        
        //Level 4 Jealous Boy/Apologizing Girl optins
        Qkey.put("Lv4BoyGirl", "-1:0:0");
        Wkey.put("Lv4BoyGirl", "2:0:0");
        
        //Level 4 Mom options
        Qkey.put("Lv4Mom", "3:0:0");
        
        //Level 4 Dad options
        Qkey.put("Lv4Dad", "3:0:3");
        
        //Level 4 Coworker1 options
        Qkey.put("Lv4Coworker1", "2:0:0");
        
        //Level 4 Coworker2 options
        Qkey.put("Lv4Coworker2", "2:0:0");
        
        //Level 4 Coworker3 options
        Qkey.put("Lv4Coworker3", "-2:0:0");
        
        //Level 4 Boss options
        Qkey.put("Lv4Boss", "0:0:5");
        
        //Level 4 Boss-2 options
        Qkey.put("Lv4Boss-2", "0:0:5");
        
        //Level 4 Boss-3 options
        Qkey.put("Lv4Boss-3", "0:0:5");
        
        //Level 4 Boss-4 options
        Qkey.put("Lv4Boss-4", "5:0:15");
        Wkey.put("Lv4Boss-4", "0:10:0");
        Ekey.put("Lv4Boss-4", "0:0:0");
        
        //Level 5 Mom options
        Qkey.put("Lv5Mom", "3:0:0");
        
        //Level 5 Dad options
        Qkey.put("Lv5Dad", "3:0:3");
        
        //Level 5 Coworker1 options
        Qkey.put("Lv5Coworker1", "2:0:0");
        
        //Level 5 Coworker2 options
        Qkey.put("Lv5Coworker2", "2:0:0");
        
        //Level 5 Coworker3 options
        Qkey.put("Lv5Coworker3", "-2:0:0");
        
        //Level 5 Boss options
        Qkey.put("Lv5Boss", "0:0:5");
        
        //Level 5 Boss-2 options
        Qkey.put("Lv5Boss-2", "0:0:5");
        
        //Level 5 Boss-3 options
        Qkey.put("Lv5Boss-3", "0:0:5");
        
        //Level 5 Boss-4 options
        Qkey.put("Lv5Boss-4", "45:0:45");
        Wkey.put("Lv5Boss-4", "0:0:0");
        
        //Level 6 Mom options
        Qkey.put("Lv6Mom", "3:0:0");
        
        //Level 6 Dad options
        Qkey.put("Lv6Dad", "3:0:3");
        
        //Level 6 Coworker1 options
        Qkey.put("Lv6Coworker1", "2:0:0");
        
        //Level 6 Coworker2 options
        Qkey.put("Lv6Coworker2", "2:0:0");
        
        //Level 6 Coworker3 options
        Qkey.put("Lv6Coworker3", "-2:0:0");
        
        //Level 6 Coworker4 options
        Qkey.put("Lv6Coworker4", "0:5:0");
        
        //Level 6 NightClassTeacher1 options
        Qkey.put("Lv6NightClassTeacher1", "0:3:0");
        
        //Level 6 NightClassTeacher2 options
        Qkey.put("Lv6NightClassTeacher2", "0:3:0");
        
        //Level 6 NightClassTeacher3 options
        Qkey.put("Lv6NightClassTeacher3", "0:0:0");
        Wkey.put("Lv6NightClassTeacher3", "0:15:50");
        Ekey.put("Lv6NightClassTeacher3", "0:0:0");
    }

    private void createSprite(String filePath, int x1, int y1) //creates a sprite at a given location, using a certain picture
    {
        sprite = new ImageIcon(filePath); 
        image = sprite.getImage();
        width = sprite.getIconWidth();
        height = sprite.getIconHeight();
        x = x1; y = y1;
    }
    
    static void foo() throws IOException {
        throw new IOException("your message");
    }
    
    /*Controls the motion for the sprite, tests  for collisions between the sprite and walls
     * as well as for collisions between sprite and objects in the background. 
     */
    public void move()
    {
        if(movingRight){
            if(x+width <= 898){
                if(pixels.getPixel(x+width,y)|| pixels.getPixel(x+width, y + height)) //if the next pixel is black (from the skeleton), go back to the previous spot
                    x--;
                else    
                    x += dx;
            }
        }
        if(movingDown){
            if(y+height <= 570){
                if(pixels.getPixel(x+width,y+height)|| pixels.getPixel(x, y + height)) //if the next pixel is black (from the skeleton), go back to the previous spot
                {
                    y--;
                    if(pixels.advance(x+width, y +height) || pixels.advance(x, y +height)) //advance tests for the green (interior_1)
                    {
                        interior = 0;
                        pixels = new Pixel("Images//skeleton" + nextLevel +".png");
                        x = exitx; y=exity;
                    }
                    else if(pixels.advancealso(x+width, y+height) || pixels.advancealso(x, y+height)) //advancealso tests for the red (interior_2)
                    {
                        interior = 0;
                        pixels = new Pixel("Images//skeleton" + nextLevel + ".png");
                        x = exitx1; y = exity1;
                    }
                }
                else
                    y += dy;
            }
            else
            {
                if(nextLevel != 1 && interior == 0){ //this is how you can go back to levels (but ONLY if you are not in an interior)
                    nextLevel --;
                    y = 0;
                    pixels = new Pixel("Images//skeleton" + nextLevel +".png");   
                }
            }
        }
        if(movingLeft) {//all the similar stuff as to movingRight and Down
            if(x >= 1){
                if(pixels.getPixel(x,y)|| pixels.getPixel(x, y + height)) //if the next pixel is black (from the skeleton), go back to the previous spot
                    x++;
                else
                    x += dx;
            }
        }
        if(movingUp){
            if(y >= 0){
                if(pixels.getPixel(x+width,y)|| pixels.getPixel(x, y)){ //if the next pixel is black (from the skeleton), go back to the previous spot
                    y++;
                    if(pixels.advance(x+width, y) || pixels.advance(x, y))
                    {
                        interior = 1;
                        //System.out.println("HELOOOOOOOOOOOO");
                        x = entrancex; y = entrancey;
                        pixels = new Pixel("Images//Interior" + nextLevel+ "_1s.png"); //loads the new skeleton
                    }
                    else if(pixels.advancealso(x+width, y) || pixels.advancealso(x, y))
                    {
                        interior = 2;
                        x = entrancex1; y = entrancey1;
                        pixels = new Pixel("Images//Interior" + nextLevel + "_2s.png");
                    }
                }
                
                else
                    y += dy;
            }
            else
            {
                nextLevel ++;
                y = 530;
                pixels = new Pixel("Images//skeleton" + nextLevel +".png");
            }
           

        }
          
    }
    /*General getters
     */
    public int getX(){return x;} 
    public int getY(){return y;}
    public boolean isTalking() {return talkNow;}
    public void changeTalk(boolean talking) {talkNow = talking;}
    public boolean stopTalk() {return stoppedTalking;}
    public boolean Advance() {return advance;}
    public int levelUp() {return nextLevel;}
    public Image getImg(){return image;}
    public ImageIcon getImgIcn(){return sprite;}
    public int choice(){return choice;}
    public int inside(){return interior;}
    public void whichNPC(String npc) {NPC = npc;}
    
    public void setInterior1(int x1, int y1, int x2, int y2)
    {
        entrancex = x1; entrancey = y1;
        exitx = x2; exity= y2;
    }
    public void setInterior2(int x1, int y1, int x2, int y2)
    {
        entrancex1 = x1; entrancey1 = y1;
        exitx1 = x2; exity1 = y2;
    }

    //Different arrow keys move things
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();        
        /*This portion deals with the movement of the little sprite.
         * We have different images, and then depending on the (x,y) coordinate, they switch, giving the illusion that it's walking
         * The booleans moving<Direction> are changed in this method to see what point to test for collisions
         */
        if (key == KeyEvent.VK_LEFT) {
            dx=-1;
            movingLeft = true;
            movingRight = false;
            movingUp = false;
            movingDown = false;           
            if(x %2 ==0) 
                createSprite("Images//" + typeChar + "//left walk.png", x, y);
            else if(x % 1 == 0)
                createSprite("Images//" + typeChar + "//left walk 2.png", x, y);           
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx=1;
            movingLeft = false;
            movingRight = true;
            movingUp = false;
            movingDown = false;
            if(x % 2 ==0)
                createSprite("Images//" + typeChar + "//right walk.png", x, y);
            else if(x% 1 ==0)
                createSprite("Images//" + typeChar + "//right walk 2.png", x, y);
        }

        if (key == KeyEvent.VK_UP) {
            dy = -1;
            movingLeft = false;
            movingRight = false;
            movingUp = true;
            movingDown = false;
            if(y % 2 == 0)
                createSprite("Images//" + typeChar + "//back walk 1.png", x, y);
            else if(y % 1 == 0)
                createSprite("Images//" + typeChar + "//back walk 2.png", x, y);

        }

        if (key == KeyEvent.VK_DOWN) {
            movingLeft = false;
            movingRight = false;
            movingUp = false;
            movingDown = true;
            dy = 1;

            if(y% 2 == 0)
                createSprite("Images//" + typeChar + "//front walk 1.png", x, y);
            else if(y % 1 == 0)
                createSprite("Images//" + typeChar + "//front walk 2.png", x, y);
        }
        if (key == KeyEvent.VK_Z)
        {
            advance = true;
            stoppedTalking = false;
            if ((Math.abs(getX() - Frame.getnpc1x()) <= 50) && (Math.abs(getY() - Frame.getnpc1y()) <= 50))
            {
                //This is where actual VN conversations take place
                //System.out.println("IT WORKS!!");
                if(!talkNow)
                    advance = false;
                talkNow = true;
            }
        }
         if(key ==KeyEvent.VK_Q)
        {
            if (Qkey.containsKey(NPC))
            {
                needsParsing = Qkey.get(NPC);
                addPoints();
            }
            else if (Qkey.containsKey(NPC + "-2"))
            {
                NPC = NPC + "-2";
                needsParsing = Qkey.get(NPC);
                addPoints();
            }
            else if (Qkey.containsKey(NPC + "-3"))
            {
                NPC = NPC + "-3";
                needsParsing = Qkey.get(NPC);
                addPoints();
            }
            else if (Qkey.containsKey(NPC + "-4"))
            {
                NPC = NPC + "-4";
                needsParsing = Qkey.get(NPC);
                addPoints();
            }
            
            talkNow = false;
            advance = false;
            stoppedTalking = true;
        }
        
        if(key == KeyEvent.VK_W)
        {
            if (Wkey.containsKey(NPC))
            {
                needsParsing = Wkey.get(NPC);
                addPoints();
            }
            else if (Wkey.containsKey(NPC + "-2"))
            {
                NPC = NPC + "-2";
                needsParsing = Wkey.get(NPC);
                addPoints();
            }
            else if (Wkey.containsKey(NPC + "-3"))
            {
                NPC = NPC + "-3";
                needsParsing = Wkey.get(NPC);
                addPoints();
            }
            else if (Wkey.containsKey(NPC + "-4"))
            {
                NPC = NPC + "-4";
                needsParsing = Wkey.get(NPC);
                addPoints();
            }
            
            talkNow = false;
            advance = false;
            stoppedTalking = true;
        }
        
        if(key == KeyEvent.VK_E)
        {
            if (Ekey.containsKey(NPC))
            {
                needsParsing = Ekey.get(NPC);
                addPoints();
            }
            else if (Ekey.containsKey(NPC + "-2"))
            {
                NPC = NPC + "-2";
                needsParsing = Ekey.get(NPC);
                addPoints();
            }
            else if (Ekey.containsKey(NPC + "-3"))
            {
                NPC = NPC + "-3";
                needsParsing = Ekey.get(NPC);
                addPoints();
            }
            else if (Ekey.containsKey(NPC + "-4"))
            {
                NPC = NPC + "-4";
                needsParsing = Ekey.get(NPC);
                addPoints();
            }
            
            talkNow = false;
            advance = false;
            stoppedTalking = true;
        }
        
        if(key == KeyEvent.VK_R)
        {
            if (Rkey.containsKey(NPC))
            {
                needsParsing = Rkey.get(NPC);
                addPoints();
            }
            else if (Rkey.containsKey(NPC + "-2"))
            {
                NPC = NPC + "-2";
                needsParsing = Rkey.get(NPC);
                addPoints();
            }
            else if (Rkey.containsKey(NPC + "-3"))
            {
                NPC = NPC + "-3";
                needsParsing = Rkey.get(NPC);
                addPoints();
            }
            else if (Rkey.containsKey(NPC + "-4"))
            {
                NPC = NPC + "-4";
                needsParsing = Rkey.get(NPC);
                addPoints();
            }
            
            talkNow = false;
            advance = false;
            stoppedTalking = true;
        }
        
        if(key == KeyEvent.VK_T)
        {
            if (Tkey.containsKey(NPC))
            {
                needsParsing = Tkey.get(NPC);
                addPoints();
            }
            else if (Tkey.containsKey(NPC + "-2"))
            {
                NPC = NPC + "-2";
                needsParsing = Tkey.get(NPC);
                addPoints();
            }
            else if (Tkey.containsKey(NPC + "-3"))
            {
                NPC = NPC + "-3";
                needsParsing = Tkey.get(NPC);
                addPoints();
            }
            else if (Tkey.containsKey(NPC + "-4"))
            {
                NPC = NPC + "-4";
                needsParsing = Tkey.get(NPC);
                addPoints();
            }
            
            talkNow = false;
            advance = false;
            stoppedTalking = true;
        }
        
        if(key == KeyEvent.VK_Y)
        {
            if (Ykey.containsKey(NPC))
            {
                needsParsing = Ykey.get(NPC);
                addPoints();
            }
            else if (Ykey.containsKey(NPC + "-2"))
            {
                NPC = NPC + "-2";
                needsParsing = Ykey.get(NPC);
                addPoints();
            }
            else if (Ykey.containsKey(NPC + "-3"))
            {
                NPC = NPC + "-3";
                needsParsing = Ykey.get(NPC);
                addPoints();
            }
            else if (Ykey.containsKey(NPC + "-4"))
            {
                NPC = NPC + "-4";
                needsParsing = Ykey.get(NPC);
                addPoints();
            }
            
            talkNow = false;
            advance = false;
            stoppedTalking = true;
        }
        
        if(key == KeyEvent.VK_U)
        {
            if (Ukey.containsKey(NPC))
            {
                needsParsing = Ukey.get(NPC);
                addPoints();
            }
            else if (Ukey.containsKey(NPC + "-2"))
            {
                NPC = NPC + "-2";
                needsParsing = Ukey.get(NPC);
                addPoints();
            }
            else if (Ukey.containsKey(NPC + "-3"))
            {
                NPC = NPC + "-3";
                needsParsing = Ukey.get(NPC);
                addPoints();
            }
            else if (Ukey.containsKey(NPC + "-4"))
            {
                NPC = NPC + "-4";
                needsParsing = Ukey.get(NPC);
                addPoints();
            }
            
            talkNow = false;
            advance = false;
            stoppedTalking = true;
        }
    }
    private void addPoints()
    {
        //parsing and setting the LOVE points
        //parsing the string
        for(index = 0; needsParsing.charAt(index) != ':'; index++)
        {
            tempLoveString += needsParsing.charAt(index);
        }
        index--;
        needsParsing = needsParsing.substring(index + 2);
        //accounting for a negative
        if (tempLoveString.charAt(0) == '-')
        {
            tempLove = -1;
            tempLoveString = tempLoveString.substring(1);
            index--;
        }
        else
        {
            tempLove = 1;
        }
        //making it into an integer value
        if (index == 1)
        {
            tempLove *= ((tempLoveString.charAt(0) - 48) * 10) + (tempLoveString.charAt(1) - 48);
        }
        else
        {
            tempLove *= (tempLoveString.charAt(0) - 48);
        }
        //here i need to figure out how to add the temp score to the actual bar....
        LOVE_SCORE += tempLove;
            
        //parsing and setting the EDUCATION points
        for(index = 0; needsParsing.charAt(index) != ':'; index++)
        {
            tempEducationString += needsParsing.charAt(index);
        }
        index--;
        needsParsing = needsParsing.substring(index + 2);
        if (tempEducationString.charAt(0) == '-') //there is a problem here for some reason
        {
            tempEducation = -1;
            tempEducationString = tempEducationString.substring(1);
            index--;
        }
        else
        {
            tempEducation = 1;
        }
        if (index == 1)
        {
            tempEducation *= ((tempEducationString.charAt(0) - 48) * 10) + (tempEducationString.charAt(1) - 48);
        }
        else
        {
            tempEducation *= (tempEducationString.charAt(0) - 48);
        }
        //here i need to figure out how to add the temp score to the actual bar....
        EDUCATION_SCORE += tempEducation;
            
        //parsing and setting the MONEY points
        for(index = 0; index < needsParsing.length(); index++)
        {   
            tempMoneyString += needsParsing.charAt(index);
        }
        index--;
        needsParsing = needsParsing.substring(index);
        if (tempMoneyString.charAt(0) == '-')
        {
            tempMoney = -1;
            tempMoneyString = tempMoneyString.substring(1);
            index--;
        }
        else
        {
            tempMoney = 1;
        }
        if (index == 1)
        {
            tempMoney *= ((tempMoneyString.charAt(0) - 48) * 10) + (tempMoneyString.charAt(1) - 48);
        }
        else
        {
            tempMoney *= (tempMoneyString.charAt(0) - 48);
        }
        //here i need to figure out how to add the temp score to the actual bar....
        MONEY_SCORE += tempMoney;
        
        //removes all the keys after one choice is made
        if (Qkey.containsKey(NPC))
            Qkey.remove(NPC);
        if (Wkey.containsKey(NPC))
            Wkey.remove(NPC);
        if (Ekey.containsKey(NPC))
            Ekey.remove(NPC);
        if (Rkey.containsKey(NPC))
            Rkey.remove(NPC);
        if (Tkey.containsKey(NPC))
            Tkey.remove(NPC);
        if (Ykey.containsKey(NPC))
            Ykey.remove(NPC);
        if (Ukey.containsKey(NPC))
            Ukey.remove(NPC);
            
        //resets all the variables
        tempLove = 0;
        tempEducation = 0;
        tempMoney = 0;
        tempLoveString = "";
        tempEducationString = "";
        tempMoneyString = "";
        needsParsing = "";
        index = 0;
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();
        
        //Once the key is released, they're returned to the respective idle states.

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
            createSprite("Images//" + typeChar + "//left idle.png", x, y);
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
            createSprite("Images//" + typeChar + "//right idle.png", x, y);
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
            createSprite("Images//" + typeChar + "//back.png", x, y);
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
            createSprite("Images//" + typeChar + "//Idle.png", x, y);
        }
    }
}