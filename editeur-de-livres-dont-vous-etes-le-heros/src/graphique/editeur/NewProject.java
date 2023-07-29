package editeur;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;


public class NewProject extends JFrame{

	protected JPanel pannelAuto = new JPanel();
      	protected JPanel pannelManuel = new JPanel();
	protected JButton BTNauto = new JButton("Automatique");
	protected JTextField nbNoeud = new JTextField("Nombre de noeuds", 20);
	protected JButton BTNmanuel = new JButton("Manuel");
	protected JTextArea solutionNoeud = new JTextArea("Ecrire les solutions lignes par lignes ...",5,10);
  protected JTextArea textNoeud = new JTextArea("Ecrire les paragraphes lignes par lignes ...",5,10);

	public NewProject(){

		JFrame frameNewprojet = new JFrame("NouveauProjet");
      		frameNewprojet.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     		frameNewprojet.setSize(600,600 );
      		frameNewprojet.setLocationRelativeTo(null);
      		frameNewprojet.setVisible(true);
     		frameNewprojet.setResizable(false);
      		frameNewprojet.setLayout(new GridLayout(1,2));

      		pannelAuto.add(BTNauto);
      		pannelAuto.add(nbNoeud);

      		textNoeud.setLineWrap(true);
					textNoeud.setWrapStyleWord(true);
					JScrollPane scroll2 = new JScrollPane(textNoeud);
					scroll2.setPreferredSize( new Dimension( 200, 200 ) );
					scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
					pannelAuto.add(scroll2);

			    solutionNoeud.setLineWrap(true);
					solutionNoeud.setWrapStyleWord(true);
					JScrollPane scroll = new JScrollPane(solutionNoeud);
					scroll.setPreferredSize( new Dimension( 200, 200 ) );
					scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
					pannelAuto.add(scroll);




      		pannelManuel.add(BTNmanuel);

      		frameNewprojet.add(pannelAuto);
      		frameNewprojet.add(pannelManuel);


          	BTNauto.addActionListener(new ActionListener() {

							 public void actionPerformed(ActionEvent e) {
								 	JOptionPane.showMessageDialog(frameNewprojet, "L'utilisateur rentrera au préalable le nombre de noeuds, les solutions, les paragraphes (voir version console) afin de générer l'abre automatiquement et par la suite le modifier dans l'éditeur.");
							    MainFrame MF = new MainFrame();

							 }
						});

						BTNmanuel.addActionListener(new ActionListener() {

							 public void actionPerformed(ActionEvent e) {
									JOptionPane.showMessageDialog(frameNewprojet, "L'utilisateur créer son arbre manuellement (voir console).");
									MainFrame MF = new MainFrame();

							 }
						});
	}

}
