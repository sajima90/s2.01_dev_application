/**
 * @author CAUVIN Pierre, AUBIN Montagne, DELPECHE Nicolas, GUELLE Clément
 * Cette classe gère les routes.
 */
package psyche.vue.map;

import psyche.Controleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class Frame extends JFrame implements ActionListener
{
	private JMenuItem     menuiAjouterVille;
	private JMenuItem     menuiAjouterRoute;

	private JMenuItem     menuiModifierVille;
	private JMenuItem     menuiModifierRoute;

	private JMenuItem     menuiEnregistrer;
	private JMenuItem     menuiEnregistrerSous;
	private JMenuItem     menuiCharger;

	private JMenuItem     menuiSupprimer;

	private Controleur    ctrl;

	private PanelInfoVille panelInfoVille;

	private  PanelGraph     panelGraph;




	public Frame(Controleur ctrl)
	{
		this.setTitle("Application GPS");
		this.setSize(1187, 825);
		this.setLocation(300	,75);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/*-------------------------*/
		/* Création des composants */
		/*-------------------------*/
		this.panelGraph = new PanelGraph(ctrl);
		this.ctrl = ctrl;

		// Créer un panel
		this.panelInfoVille = new PanelInfoVille(this.ctrl);
		this.panelGraph     = new PanelGraph(this.ctrl);







		JMenuBar menubMaBarre = new JMenuBar();

		JMenu menuOutils      = new JMenu ("Outils");
		menuOutils.setBackground(Color.LIGHT_GRAY);
		menuOutils.setOpaque(true);
		JMenu menuEdition     = new JMenu("Edition");
		menuEdition.setBackground(Color.LIGHT_GRAY);
		menuEdition.setOpaque(true);
		JMenu menuEnregistrer = new JMenu("Enregistrer");
		menuEnregistrer.setBackground(Color.LIGHT_GRAY);
		menuEnregistrer.setOpaque(true);

		/* Item de Outils */
		this.menuiAjouterVille = new JMenuItem("Ajouter Mine");
		this.menuiAjouterRoute = new JMenuItem("Ajouter Route");

		/* Item de Edition */
		this.menuiModifierVille = new JMenuItem("Modifier Mine");
		this.menuiModifierRoute = new JMenuItem("Modifier Route");
		this.menuiSupprimer     = new JMenuItem("Supprimer Mine");

		/* Item de Enregistrer */
		this.menuiEnregistrer     = new JMenuItem("Enregistrer");
		this.menuiEnregistrerSous = new JMenuItem("Enregistrer sous...");
		this.menuiCharger         = new JMenuItem("Ouvrir");

		/* Raccourcis clavier */
		this.menuiEnregistrer    .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		this.menuiEnregistrerSous.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		this.menuiCharger        .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));



		/* Ajout des Items dans Outils  */
		menuOutils.add(this.menuiAjouterVille);
		menuOutils.add(this.menuiAjouterRoute);

		/* Ajout des Items dans Edition  */
		menuEdition.add(this.menuiModifierVille);
		menuEdition.add(this.menuiModifierRoute);
		menuEdition.add(this.menuiSupprimer);

		/* Ajout des Items dans Enregistrer  */
		menuEnregistrer.add(this.menuiEnregistrer);
		menuEnregistrer.add(this.menuiEnregistrerSous);
		menuEnregistrer.addSeparator();
		menuEnregistrer.add(this.menuiCharger);

		// ajout du menu 'Fichier' a la barre de menu
		menubMaBarre.add( menuOutils );
		menubMaBarre.add( menuEdition);
		menubMaBarre.add( menuEnregistrer);

		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/
		this.setJMenuBar( menubMaBarre );
		/* Ajouter Panel Info */
		this.add(panelInfoVille, BorderLayout.EAST);
		this.add(panelGraph,BorderLayout.CENTER);



		/*-------------------------------*/
		/*   Activation des composants   */
		/*-------------------------------*/
		this.menuiAjouterVille   .addActionListener( this );
		this.menuiAjouterRoute   .addActionListener( this );
		this.menuiModifierRoute  .addActionListener( this );
		this.menuiModifierVille  .addActionListener( this );
		this.menuiSupprimer		 .addActionListener( this );
		this.menuiEnregistrer    .addActionListener( this );
		this.menuiEnregistrerSous.addActionListener( this );
		this.menuiCharger        .addActionListener( this );



		this.setVisible(true);
	}


	public PanelGraph     getPanelGraph()     {return this.panelGraph;}
	public PanelInfoVille getPanelInfoVille() {return this.panelInfoVille;}



	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.menuiAjouterVille)
		{
			this.ctrl.ouvrirAjouterVille();
		}
		else if (e.getSource() == this.menuiAjouterRoute)
		{
			if ( this.ctrl.getMines().size() >= 2)
				this.ctrl.ouvrirAjouterRoute();
		}
		else if (e.getSource() == this.menuiModifierVille)
		{
			if ( this.ctrl.getMines().size() >= 1 )
				this.ctrl.ouvrirModifierVille();
		}
		else if (e.getSource() == this.menuiModifierRoute)
		{
			System.out.println("test Frame modifier route 1");
			if (this.ctrl.getMines().size() >= 2)
			{
				System.out.println("test Frame modifier route 2");
				this.ctrl.ouvrirModifierRoute();
			}
		}
		else if (e.getSource() == this.menuiSupprimer)
		{
			if(this.ctrl.getMines().size() >= 1)
				this.ctrl.ouvrirSupprimerMine();
		}
		else if (e.getSource() == this.menuiEnregistrer)
		{
			if (this.ctrl.getFichierCharger().equals("") || this.ctrl.getFichierCharger() == null)
				this.ctrl.enregistrerSous();
			this.ctrl.enregistrer();
		}
		else if (e.getSource() == this.menuiEnregistrerSous)
		{
			this.ctrl.enregistrerSous();
		}
		else if (e.getSource() == this.menuiCharger)
		{

			JFileChooser fileChooser = new JFileChooser();
			int returnValue = fileChooser.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				this.ctrl.setFichierCharger(selectedFile.getPath());
			}
		}
	}

//	//Tentative de background
//	protected void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		try
//		{
//			String imageFond = ImageIO.read(new File("/home/saji/Cours/IUT/TP/s2/s2.01_dev_application/AppFinal/psyche/theme/img.png"));
//			g.drawImage(imageFond, 0, 0, getWidth(), getHeight(), this);
//		}
//		catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
