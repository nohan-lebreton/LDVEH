package graphique;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.Graphics;


public class PanelGraph extends JPanel
{
  private Graphics g ;
  private List<IDrawable> drawables = new LinkedList<IDrawable>();
  public void paint(Graphics g) {
    this.setBackground(new Color(88,103,203));

        super.paint(g);
        for (Iterator iter = drawables.iterator(); iter.hasNext();) {
            IDrawable d = (IDrawable) iter.next();
            d.draw(g);
        }
    }

    public void addDrawable(IDrawable d) {
        drawables.add(d);
        repaint();
    }

    public void removeDrawable(IDrawable d) {
        drawables.remove(d);
        repaint();
    }

    public void clear() {
        drawables.clear();
        repaint();
    }

  /*
  public PanelGraph()
  {
    this.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseDragged(MouseEvent e) {
      System.out.println(e.getX() + "," + e.getY());
      drawNoeud(g,e.getX(),e.getY());
      this.repaint();
    }
  });
    this.setBackground(new Color(88,103,203));
  }
  @Override
  public void paintComponent(Graphics g)
  {
      this.g = g ;
      super.paintComponent(g);
      this.drawNoeud(g,20,100);
  }
  public void drawNoeud(Graphics g , int x , int y )
  {
    String paragraph = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed tincidunt viverra consectetur. Aenean sit amet quam eu velit pellentesque dictum ac et odio. Nam iaculis sem at fringilla ullamcorper odio." ;
    int ligne = 1 ;
    if ( paragraph.length() > 20 )
    {
      int cpt = 0 ;

      String str = "" ;
      while (cpt < paragraph.length())
      {
        str+= paragraph.charAt(cpt);
        if ((cpt+1) % 20 == 0 || paragraph.charAt(cpt) == '\n'){
            g.drawString(str , x + 11 , y + 10 * ligne  + 1) ;
            str = "" ;
            ligne++;
          }
        cpt++;
      }
      paragraph = str ;
    }else
    {
      g.drawString(paragraph , x + 10  , y + 10 ) ;
    }
    g.setColor(Color.black);
    g.drawRect(x,y,7*20,10 * ligne ) ;
  }

  public void drawEnfant(String txt , char pose , int x , int y)
  {

  }
  */
  public List findDrawables(Point p) {
    List<IDrawable> l = new ArrayList<IDrawable>();
    for (Iterator iter = drawables.iterator(); iter.hasNext();) {
        IDrawable element = (IDrawable) iter.next();
        if(element.getRectangle().contains(p)){
            l.add(element);
        }
    }
    return l;
  }
  public boolean isFree(Rectangle rect) {
    for (Iterator iter = drawables.iterator(); iter.hasNext();) {
        IDrawable element = (IDrawable) iter.next();
        if(element.getRectangle().intersects(rect)){
            return false;
        }
    }
    return true;
}
  @Override
  public Dimension getPreferredSize()
  {
    return new Dimension(400, 350);
  }
}
