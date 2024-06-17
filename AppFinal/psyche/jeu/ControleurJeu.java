/**
 * @author CAUVIN Pierre, MONTAGNE Aubin, DELPECHE Nicolas, GUELLE Clément BOUQUET Jules, YACHIR Yanis, ROUGEOLLE
 * 		Henri
 */
package psyche.jeu;

import psyche.Controleur;
import psyche.jeu.metier.*;
import psyche.jeu.vue.*;

import java.util.ArrayList;
import java.util.List;

public class  ControleurJeu
{
	/*--------------*/
	/* Données      */
	/*--------------*/
	private static int nbJoueur = -1;
	private final Metier metier;
	private final FrameCarte frameCarte;
	private final Controleur ctrl;

	private final PanelCarte panelCarte;
	private List<Joueur> tabJoueur;

	private FrameNom frameNom;

	private String tourJoueur;

	/*--------------*/
	/* Méthodes */
	/*--------------*/

	/**
	 * Constructeur de Controleur
	 */

	public ControleurJeu(Controleur ctrl)
	{
		this.ctrl       = ctrl;
		this.metier     = new Metier();
		this.frameCarte = new FrameCarte(this);
		this.panelCarte = new PanelCarte(this);
		this.tabJoueur  = new ArrayList<Joueur>();
		this.frameNom   = new FrameNom(this);
	}

	public void ajouterJoueur(Joueur j) {this.tabJoueur.add(j);}

	public String getNomJoueur()
	{
		return this.tabJoueur.get(++nbJoueur).getNom();

	}

	public Joueur getJoueur(int indice) {return this.tabJoueur.get(indice);}

	public Joueur getJoueurTour ()
	{
		switch (this.tourJoueur)
		{
			case "SA" : return this.getJoueur(0);
			case "CS" : return this.getJoueur(1);
			default : return null;
		}

	}

	public Couleur getCouleur(String couleur)
	{
		for (Couleur c : Couleur.values())
		{
			if (c.name().equals(couleur.toUpperCase()))
			{
				return c;
			}
		}
		return null;
	}

	public Mine getMine(int i)
	{
		return this.metier.getMine(i);
	}

	/**
	 * Renvoie la liste des Mines
	 */
	public List<Mine> getMines()
	{
		return this.metier.getMines();
	}

	/**
	 * Renvoie la liste des Routes
	 */
	public List<Route> getRoutes()
	{
		return this.metier.getRoutes();
	}

	/**
	 * Renvoie une liste de Routes liées à une mine
	 *
	 * @param mine
	 * @return List<Route> Nom de la mine
	 */
	public List<Route> getRoute(Mine mine)
	{
		return mine.getRoutes();
	}

	/**
	 * Met à jour l'IHM
	 */
	public void majIHM()
	{
		this.frameCarte.getPanelCarte().repaint();
	}

	public Mine getMineIndice(int x, int y)
	{
		return metier.getMineIndice(x, y);
	}

	public void setVisible () { this.ctrl.setVisible(); }

	/*------------*/
	/* Fichiers */
	/*------------*/

	/**
	 * Renvoie le chemin d'accès du fichier d'où charger les données
	 */
	public String getFichierCharger()
	{
		return this.metier.getFichierCharger();
	}

	/**
	 * Définit le chemin d'accès du fichier
	 *
	 * @param path
	 *             Le chemin d'accès à définir.
	 */
	public void setFichierCharger(String path)
	{
		this.metier.setFichierCharger(path);
		this.majIHM();
	}

	public String getTourJoueur()
	{
		return this.tourJoueur;
	}

	public String changerTour()
	{
		switch (this.tourJoueur)
		{
			case "SA":
				return this.tourJoueur = "CS";
			case "CS":
				return this.tourJoueur = "SA";
			default:
				return null;
		}
	}

	public void possederMine(Mine mineSelect)
	{
		getJoueurTour().ajouterMine(mineSelect);
		mineSelect.enleverMinerai();
	}


	public boolean mineEstAdjacent (Mine mine1, Mine mine2)
	{
		for (Route r1 : mine1.getRoutes())
		{
			for (Route r2 : mine2.getRoutes())
			{
				if (r1 == r2)
					return true;
			}
		}
		return false;
	}
}
