package graphique;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class PanelUser extends JPanel{

  protected JButton ajouterNoeud = new JButton("Ajouter un noeud");
  protected JButton supprimerNoeud = new JButton("supprimer un noeud ");

  protected JTextArea nomNoeud = new JTextArea("Nom du noeud...");
  protected JTextArea solutionNoeud = new JTextArea("Texte réponse du choix précédent ...",5,10);
  protected JTextArea textNoeud = new JTextArea("Texte nouveau choix ...",5,10);

  protected JButton sauvegarder = new JButton("Sauvegarder le text du noeud ");


  public PanelUser(){
    init();
  }

  public void init (){
  
        this.setBackground(new Color(249,224,162));
  

        //panel: ajouter et supprimer
        Panel btnPanel = new Panel();
        btnPanel.setLayout(new GridLayout(1,2));

        ajouterNoeud.setBackground(new Color(0,255,0));
        btnPanel.add(ajouterNoeud);

        supprimerNoeud.setBackground(new Color(255,0,0));
        btnPanel.add(supprimerNoeud);

        this.add(btnPanel);

        //panel: nom
        this.add(nomNoeud);

        //panel: TXT
        // Panel textPanel = new Panel();
        // textPanel.setLayout(new GridLayout(3,1));

        nomNoeud.setPreferredSize( new Dimension( 300, 20 ) );
        this.add(nomNoeud);


        solutionNoeud.setLineWrap(true);
        solutionNoeud.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(solutionNoeud);
        scroll.setPreferredSize( new Dimension( 300, 200 ) );
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scroll);

        textNoeud.setLineWrap(true);
        textNoeud.setWrapStyleWord(true);
        JScrollPane scroll2 = new JScrollPane(textNoeud);
        scroll2.setPreferredSize( new Dimension( 300, 200 ) );
        scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scroll2);

        // panel footer = new Panel();
        sauvegarder.setBackground(new Color(0,255,0));
        this.add(sauvegarder);


  }




  @Override
  public Dimension getPreferredSize()
  {
    return new Dimension(400, 350);
  }
}
