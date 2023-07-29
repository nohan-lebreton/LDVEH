package graphique;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;

public class MainFrame extends JFrame
{
  public MainFrame () {
    this.setTitle( "Projet" ) ;
    this.setResizable(true) ;
    this.setLayout(new BorderLayout());
    PanelGraph tmp = new PanelGraph();
    PanelUser tmp2 = new PanelUser();
    this.add(tmp,BorderLayout.CENTER);
    this.add(tmp2,BorderLayout.EAST);
    Dimension dim  =new Dimension(7*20,70);
    IDrawable rect = new NoeudDrawable(new Point(100,100),dim);
    tmp.addDrawable(rect);
    new MoveDrawableMouseListener(tmp);
    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    this.setVisible(true);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
  }
  public static void main (String[] args)
  {
    new MainFrame();
  }
}
