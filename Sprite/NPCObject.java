import javax.swing.ImageIcon;
import java.awt.*;

public class NPCObject
{
    private int leftx; private int rightx; private int uppery; private int lowery;
    private boolean collision;
    private int[] npcboundaries;
    /*
     * This will be for objects that it shouldn't collide with
     */
    public NPCObject(int x1, int x2, int y3, int y4)
    {
        leftx = x1;
        rightx = x2;
        uppery = y3;
        lowery = y4;
    }

    /* 
     * Ok so this is gonna be for like NPC characters that also need to move
     */
    public NPCObject(ImageIcon sample, int x1, int y1)
    {
        leftx = x1; 
        rightx = x1+ sample.getIconWidth();
        uppery = y1;
        lowery = y1 + sample.getIconHeight();
    }
    
    /*
     * Tests to see if the sprite is within the boundaries (array)
     */
    public boolean collide(Sprite test)
    {
        int testx1 = test.getX();
        int testx2 = testx1 + test.getImgIcn().getIconWidth();
        int testy1 = test.getY(); 
        int testy2 = testy1 + test.getImgIcn().getIconHeight();
        
        return collision;
    }
    
    /*public void actionPerformed(ActionEvent e)
    {
        if(!sprite.isTalking())
        {
            sprite.move();
        }
        repaint();
    }*/
}