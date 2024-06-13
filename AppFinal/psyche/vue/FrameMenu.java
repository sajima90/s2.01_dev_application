package psyche.vue;

import psyche.Controleur;
import psyche.jeu.ControleurJeu;
import psyche.map.ControleurMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FrameMenu extends JFrame implements ActionListener, MouseListener
{

	private JButton btnjouer;
	private JButton btnmodifier;
	//private PanelGraph panelGraph;
	private Controleur ctrl;

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
		this.btnjouer = new JButton("Jouer");
		this.btnjouer.setContentAreaFilled(false);
		this.btnjouer.setBorderPainted(false);
		this.btnjouer.setBackground(Color.WHITE);
		this.btnjouer.setOpaque(true);

		this.btnmodifier = new JButton("Modifier");
		this.btnmodifier.setContentAreaFilled(false);
		this.btnmodifier.setBorderPainted(false);
		this.btnmodifier.setBackground(Color.WHITE);
		this.btnmodifier.setOpaque(true);
		this.btnmodifier.addActionListener(this);

		JPanel panelHautContainer = new JPanel(new BorderLayout());
		panelHautContainer.add(panelTitre, BorderLayout.NORTH);
		panelHautContainer.setBackground(Color.LIGHT_GRAY);

		/*-----------------------------------------*/
		/* Position des composants dans PanelBarre */
		/*-----------------------------------------*/
		panelBarre.add(panelHautContainer);
		panelBarre.add(btnmodifier);
		panelBarre.add(btnjouer);

		/*--------------------------------------*/
		/* Position des composants dans la Frame*/
		/*--------------------------------------*/
		this.add(panelBarre, BorderLayout.WEST);
		//this.add(this.panelGraph, BorderLayout,CENTER);

		/*----------------------------*/
		/*  Activation des coposants  */
		/*----------------------------*/
		this.btnjouer.addActionListener(this);
		this.btnjouer.addMouseListener(this);
		this.btnmodifier.addMouseListener(this);


		setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnmodifier)
		{
			new ControleurMap(this.ctrl);
			System.out.println("Modifier");
		}
		else if (e.getSource() == btnjouer)
		{
			 new ControleurJeu();
			System.out.println("Jouer");
		}
	}

	public void mouseEntered(MouseEvent e)
	{
		if(e.getSource() == this.btnjouer)
		{
			this.btnjouer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			this.btnjouer.setBackground(Color.GRAY);
			this.btnjouer.setForeground(Color.WHITE);
			this.btnjouer.setOpaque(true);
		}
		else if(e.getSource() == this.btnmodifier)
		{
			this.btnmodifier.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			this.btnmodifier.setBackground(Color.GRAY);
			this.btnmodifier.setForeground(Color.WHITE);
			this.btnmodifier.setOpaque(true);
		}
	}

	public void mouseExited(MouseEvent e)
	{
		if(e.getSource() == this.btnjouer)
		{
			this.btnjouer.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			this.btnjouer.setBackground(Color.WHITE);
			this.btnjouer.setForeground(Color.BLACK);
			this.btnjouer.setOpaque(true);
		}
		else if(e.getSource() == this.btnmodifier)
		{
			this.btnmodifier.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			this.btnmodifier.setBackground(Color.WHITE);
			this.btnmodifier.setForeground(Color.BLACK);
			this.btnmodifier.setOpaque(true);
		}
	}

	public void mouseClicked(MouseEvent e)
	{
		if(e.getSource() == this.btnmodifier)
		{
			this.setVisible(false);
		}
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}

}
