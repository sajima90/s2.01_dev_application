package psyche.jeu.vue;

import psyche.jeu.ControleurJeu;
import psyche.jeu.metier.Joueur;
import psyche.jeu.metier.Mine;
import psyche.jeu.metier.Route;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PanelJoueur extends JPanel
{
	private Joueur joueur;
	private ControleurJeu ctrlJeu;

	public PanelJoueur(Joueur joueur, ControleurJeu ctrlJeu)
	{
		this.joueur = joueur;
		this.ctrlJeu = ctrlJeu;


	}


	public void actionPerformed(ActionEvent evt)
	{
		this.repaint();
	}


	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		// Charger l'image de fond
		if ( this.ctrlJeu.getNomJoueur().equals("CS"))
		{
			ImageIcon imageIcon = new ImageIcon("../psyche/theme/images/plateau_joueur_1.png");
			Image backgroundImage = imageIcon.getImage();
			System.out.println("test");
			g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
			FrameJoueur joueur1 = new FrameJoueur(this.joueur, ctrlJeu);


		}
		if ( this.ctrlJeu.getNomJoueur().equals("SA"))
		{
			ImageIcon imageIcon = new ImageIcon("../psyche/theme/images/plateau_joueur_2.png");
			Image backgroundImage = imageIcon.getImage();
			g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
			FrameJoueur joueur2 = new FrameJoueur(this.joueur, ctrlJeu);
		}

	}
}