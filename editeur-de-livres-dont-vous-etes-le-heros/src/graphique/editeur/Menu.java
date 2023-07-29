package editeur;

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
      frame.setSize(300,170);
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
      frame.setResizable(false);
      frame.setBackground(new Color(249,224,162));
	     JOptionPane.showMessageDialog(frame, "Par manque de temps l'interface graphique n'est pas operationel, cependant des pop up telles que celle-ci vous guideront pour l'explorer afin de ne pas négliger le travail en partie graphique. ");

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

         public void actionPerformed(ActionEvent e) {
         	  NewProject NPIHM = new NewProject();
          	frame.dispose();
         }
      });

      btnLoadproject.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(frame, "Une frame s'ouvre afin que l'utilisateur selectione le fichier à charger. Puis une fois selectioné, ce dernier le dirige vers la MainFrame afin d'éditer l'arbre.");
         }
      });

      pannelNewProject.add(btnNewProject);
      pannelLoadproject.add(btnLoadproject);
      frame.getContentPane().add(pannelNewProject, BorderLayout.NORTH);
      frame.getContentPane().add(pannelLoadproject, BorderLayout.CENTER);
   }
}
