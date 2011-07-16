/*
 * Button with numbers
 * 
 */
package iron.heart;

import java.awt.Graphics;

/**
 *
 * @author setes
 */
public class ButtonpNum extends Button{
    
    // attributes of the class
    //
    int m_iCounterX;
    int m_iCounterY;
    BasicEntity be_num1;
    BasicEntity be_num2;
    BasicEntity be_num3;
    
    // constructor
    //
    public ButtonpNum(String ref, int x, int y, int id, String data, String path,
            String typ,  int resx, int resy){
        super(ref, "", id, x, y);
        m_iCounterX = resx;
        m_iCounterY = resy;
        be_num1 = new BasicEntity(data + path + typ, m_iCounterX, m_iCounterY);
        be_num2 = new BasicEntity(data + path + typ, m_iCounterX + 10, m_iCounterY);
        be_num3 = new BasicEntity(data + path + typ, m_iCounterX + 20, m_iCounterY);        
    }// constructor
        
    // draw the button and the nums
    //
    public void Draw(Graphics g, int x){
        sp_sprite.Draw(g,(int) d_xPos,(int) d_yPos);   
        
        int x1 = (x % 1000) / 10;
        int x2 = (x % 100) / 10;
        int x3 = x % 10;
        
        be_num1.sp_sprite.setSubImage(x1 * 11, 0, 11, 14);
        be_num1.sp_sprite.DrawSubImage(g, be_num1.getXPos(), be_num1.getYPos());
        be_num2.sp_sprite.setSubImage(x2 * 11, 0, 11, 14);
        be_num2.sp_sprite.DrawSubImage(g, be_num2.getXPos(), be_num2.getYPos());  
        be_num3.sp_sprite.setSubImage(x3 * 11, 0, 11, 14);
        be_num3.sp_sprite.DrawSubImage(g, be_num3.getXPos(), be_num3.getYPos());        
    }// Draw
}// class ButtonpNum
