package graphique;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Menu {
   public Menu(){
   	System.out.print("Editeur en interface graphique en cours ... \n");
   	createWindow();
   }
   
   

   private static void createWindow() {    
      JFrame frame = new JFrame("Menu LDVEH");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      createUI(frame);
      frame.setSize(400,400 );      
      frame.setLocationRelativeTo(null);  
      frame.setVisible(true);
      frame.setResizable(false);
      frame.setBackground(new Color(249,224,162));
      
     
   }

   private static void createUI(final JFrame frame){  
      JPanel pannelNewProject = new JPanel();
      JPanel pannelLoadproject = new JPanel();

      LayoutManager layout = new FlowLayout();  
      pannelNewProject.setLayout(layout);       
      pannelLoadproject.setLayout(layout); 
      
      JButton btnNewProject = new JButton("Nouveau Projet");
      JButton btnLoadproject = new JButton("Charger un Projet");
      
      btnNewProject.setPreferredSize( new Dimension( 400, 50 ) );
      btnLoadproject.setPreferredSize( new Dimension( 400, 50 ) );
      
      btnNewProject.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            MainFrame MF = new MainFrame();
            frame.dispose();
         }
      });
      
      btnLoadproject.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(frame, "Besoin de l'affichage de l'arbre!");
         }
      });
	
      pannelNewProject.add(btnNewProject);
      pannelLoadproject.add(btnLoadproject);
      frame.getContentPane().add(pannelNewProject, BorderLayout.NORTH);   
      frame.getContentPane().add(pannelLoadproject, BorderLayout.CENTER); 
   }  
}
