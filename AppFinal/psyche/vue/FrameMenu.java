package psyche.vue;

import psyche.Controleur;
//import psyche.jeu.ControleurJeu;
//import psyche.jeu.ControleurJeu;
import psyche.jeu.ControleurJeu;
import psyche.jeu.metier.Mine;
import psyche.map.ControleurMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class FrameMenu extends JFrame implements ActionListener, MouseListener
{

	private JButton btnJouer;
	private JButton btnModifier;
	//private PanelGraph panelGraph;
	private Controleur ctrl;
	//Necessaire pour scenarios
	private ControleurMap ctrlMap;
	private ControleurJeu ctrlJeu;

	public FrameMenu(Controleur ctrl)
	{
		this.ctrl = ctrl;
		/*---------------------------*/
		/* Configuration de la Frame */
		/*---------------------------*/
		setTitle("Menu de Lancement");
		setSize(1000, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		//		this.setExtendedState(JFrame.MAXIMIZED_BOTH);

		/*------------------------------------*/
		/* Création des composant de la Frame */
		/*------------------------------------*/
		//this.panelGraph = new PanelGraph();


		/*---------------------------*/
		// Créer des sous-panneaux
		/*---------------------------*/
		JPanel panelBarre = new JPanel();
		JPanel panelTitre = new JPanel();


		/*---------------------------*/
		/* Sous Panel dans Menu */
		/*---------------------------*/
		panelBarre.setBackground(Color.LIGHT_GRAY);
		panelBarre.setLayout(new GridLayout(4, 1));

		panelTitre.setBackground(Color.LIGHT_GRAY);
		panelTitre.setLayout(new BorderLayout());


		/*--------------------------------------*/
		/* Création des composant de PanelTitre */
		/*--------------------------------------*/
		JLabel lblTitre = new JLabel("Plateau Selectionner");
		Font font = new Font("Lucida Console", Font.BOLD, 13);
		lblTitre.setFont(font);
		lblTitre.setOpaque(true);

		/*---------------------------------------*/
		/* Positon des coposants dans PanelTitre */
		/*---------------------------------------*/
		panelTitre.add(lblTitre, BorderLayout.CENTER);


		/*---------------------------------------*/
		/* Création des composants de PanelBarre */
		/*---------------------------------------*/
		this.btnJouer = new JButton("Jouer");
		this.btnJouer.setContentAreaFilled(false);
		this.btnJouer.setBorderPainted(false);
		this.btnJouer.setBackground(Color.WHITE);
		this.btnJouer.setOpaque(true);

		this.btnModifier = new JButton("Modifier");
		this.btnModifier.setContentAreaFilled(false);
		this.btnModifier.setBorderPainted(false);
		this.btnModifier.setBackground(Color.WHITE);
		this.btnModifier.setOpaque(true);

		JPanel panelHautContainer = new JPanel(new BorderLayout());
		panelHautContainer.add(panelTitre, BorderLayout.NORTH);
		panelHautContainer.setBackground(Color.LIGHT_GRAY);

		/*-----------------------------------------*/
		/* Position des composants dans PanelBarre */
		/*-----------------------------------------*/
		panelBarre.add(panelHautContainer);
		panelBarre.add(btnModifier);
		panelBarre.add(btnJouer);

		/*--------------------------------------*/
		/* Position des composants dans la Frame*/
		/*--------------------------------------*/
		this.add(panelBarre, BorderLayout.WEST);
		//this.add(this.panelGraph, BorderLayout,CENTER);

		/*----------------------------*/
		/*  Activation des composants */
		/*----------------------------*/

		this.btnJouer.addActionListener(this);
		this.btnModifier.addActionListener(this);
		this.btnJouer.addMouseListener(this);
		this.btnModifier.addMouseListener(this);


		setVisible(true);
	}

	public List<Mine> getMines()
	{
		return this.ctrlJeu.getMines();
	}

	/*-------------------------*/
	/*  Méthodes de scénarios  */
	/*-------------------------*/

	public void ouvrirFenetreModifier()
	{
		ctrlMap = new ControleurMap(this.ctrl);
		System.out.println("Modifier");
		this.setVisible(false);
	}

	public void ouvrirFenetreJouer()
	{
		ctrlJeu = new ControleurJeu(this.ctrl);
		System.out.println("Jouer");
		this.setVisible(false);
	}

	public void fermerFenetreModifier()
	{
		this.ctrlMap.fermerFenetre();
	}
	public void fermerFenetreJouer()
	{
		this.ctrlJeu.fermerFenetre();
	}


	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnModifier)
		{
			ouvrirFenetreModifier();
		}
		else if (e.getSource() == this.btnJouer)
		{
			ouvrirFenetreJouer();
		}
	}

	public void mouseEntered(MouseEvent e)
	{
		if(e.getSource() == this.btnJouer)
		{
			this.btnJouer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			this.btnJouer.setBackground(Color.GRAY);
			this.btnJouer.setForeground(Color.WHITE);
			this.btnJouer.setOpaque(true);
		}
		else if(e.getSource() == this.btnModifier)
		{
			this.btnModifier.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			this.btnModifier.setBackground(Color.GRAY);
			this.btnModifier.setForeground(Color.WHITE);
			this.btnModifier.setOpaque(true);
		}
	}

	public void mouseExited(MouseEvent e)
	{
		if(e.getSource() == this.btnJouer)
		{
			this.btnJouer.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			this.btnJouer.setBackground(Color.WHITE);
			this.btnJouer.setForeground(Color.BLACK);
			this.btnJouer.setOpaque(true);
		}
		else if(e.getSource() == this.btnModifier)
		{
			this.btnModifier.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			this.btnModifier.setBackground(Color.WHITE);
			this.btnModifier.setForeground(Color.BLACK);
			this.btnModifier.setOpaque(true);
		}
	}

	public void mouseClicked(MouseEvent e)
	{
		if(e.getSource() == this.btnModifier)
		{
			//this.setVisible(false);
		}

		if(e.getSource() == this.btnJouer)
		{
			//this.setVisible(false);
		}
	}

	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}

}
