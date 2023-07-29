package graphique;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

public class NoeudDrawable extends FormDrawable{

    public NoeudDrawable( Point pos, Dimension dim) {
        super(pos, dim);

    }

    public void draw(Graphics g) {
        Color c = g.getColor();
        g.setColor(color);
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
                g.drawString(str , rect.x + 11 , rect.y + 10 * ligne  + 1) ;
                str = "" ;
                ligne++;
              }
            cpt++;
          }
          paragraph = str ;
        }else
        {
          g.drawString(paragraph , rect.x + 10  , rect.y + 10 ) ;
        }
        g.setColor(Color.black);
        g.drawRect(rect.x,rect.y,7*20,10 * ligne ) ;
    }

}
