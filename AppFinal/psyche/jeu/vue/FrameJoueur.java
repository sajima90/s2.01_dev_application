package psyche.jeu.vue;

import psyche.jeu.ControleurJeu;
import psyche.jeu.metier.Joueur;

import javax.swing.*;

public class FrameJoueur extends JFrame
{
	private PanelJoueur   panelJ;
	private ControleurJeu ctrlJeu;

	public FrameJoueur(Joueur j, ControleurJeu ctrlJeu)
	{

		switch (j.getNumJoueur())
		{
			case 1:
				this.setTitle("Corporation Solaire");
				this.setLocation(1050,20);
				break;
			case 2:
				this.setTitle("Syndicat Astral");
				this.setLocation(1050,420);
				break;
		}


		this.setSize(553, 410);

		this.ctrlJeu = ctrlJeu;

		/*-------------------------*/
		/* Création des composants */
		/*-------------------------*/
		this.panelJ = new PanelJoueur(j, this.ctrlJeu);

		/*-------------------------*/
		/* Position des composants */
		/*-------------------------*/
		this.add(this.panelJ);


		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
	}

	public void majIHM()
	{
		this.panelJ.repaint();
	}
}
