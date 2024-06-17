package psyche.jeu.vue;

import psyche.jeu.ControleurJeu;
import psyche.jeu.metier.Joueur;

import javax.swing.*;

public class FrameJoueur extends JFrame
{
	private PanelJoueur panelJ;
	private ControleurJeu ctrlJeu;

	public FrameJoueur(Joueur j, ControleurJeu ctrlJeu)
	{
		if (j.getNom().equals("CS"))
			this.setTitle("Corporation Solaire");
		else
			this.setTitle("Syndicat Astral");
		this.setSize(553,397);
		/*-------------------------*/
		/* Création des composants */
		/*-------------------------*/
		this.panelJ = new PanelJoueur(j,this.ctrlJeu);

		/*-------------------------*/
		/* Position des composants */
		/*-------------------------*/
		this.add(this.panelJ);
		this.setVisible(true);
	}
}
