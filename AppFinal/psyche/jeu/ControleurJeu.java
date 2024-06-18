/**
 * @author CAUVIN Pierre, MONTAGNE Aubin, DELPECHE Nicolas, GUELLE Clément BOUQUET Jules, YACHIR Yanis, ROUGEOLLE
 * 		Henri
 */
package psyche.jeu;

import psyche.Controleur;
import psyche.jeu.metier.*;
import psyche.jeu.vue.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class  ControleurJeu
{
	/*--------------*/
	/* Données      */
	/*--------------*/
	private final Metier joue;
	private final FrameCarte frameCarte;
	private final Controleur ctrl;
	private FrameJoueur frameJoueur;

	private List<Joueur> tabJoueur;

	private FrameNom frameNom;

	private String tourJoueur = "SA";

	/*--------------*/
	/* Méthodes */
	/*--------------*/

	/**
	 * Constructeur de Controleur
	 */

	public ControleurJeu(Controleur ctrl)
	{
		this.ctrl        = ctrl;
		this.tabJoueur   = new ArrayList<Joueur>();
		this.joue      = new Metier();
		this.frameCarte  = new FrameCarte(this);
		this.frameNom    = new FrameNom(this);

		this.frameJoueur = null;
	}

	public void ajouterJoueur(Joueur j) {this.tabJoueur.add(j);}

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

	public void setJoueur(FrameJoueur j)
	{
		this.frameJoueur = j;
	}


	public void approprierRoute (Joueur joueur, Route route)
	{
		joueur.ajouterRoute(route);
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
		return this.joue.getMine(i);
	}

	/**
	 * Renvoie la liste des Mines
	 */
	public List<Mine> getMines()
	{
		return this.joue.getMines();
	}

	/**
	 * Renvoie la liste des Routes
	 */
	public List<Route> getRoutes()
	{
		return this.joue.getRoutes();
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
		this.frameJoueur.majIHM();


	}

	public Mine getMineIndice(int x, int y)
	{
		return joue.getMineIndice(x, y);
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
		return this.joue.getFichierCharger();
	}

	/**
	 * Définit le chemin d'accès du fichier
	 *
	 * @param path
	 *             Le chemin d'accès à définir.
	 */
	public void setFichierCharger(String path)
	{
		this.joue.setFichierCharger(path);
		this.majIHM();
	}

	public String getTourJoueur()
	{
		return this.tourJoueur;
	}

	public String changerTour()
	{
		System.out.println("Tour changé");

		switch (this.tourJoueur)
		{
			case "SA":
				this.majIHM();
				return this.tourJoueur = "CS";
			case "CS":
				this.majIHM();
				return this.tourJoueur = "SA";
			default:
				return null;
		}
	}

	public void possederMine(Mine mine)
	{
		this.majIHM();

		System.out.println("Test possederMine");
		this.getJoueurTour().ajouterMine(mine);

		if (mine.getJeton() != null && !mine.getNom().equals("ROME"))
			{
				this.getJoueurTour().ajouterRessource(new Jeton(mine.getJeton().getType()));
			}

		this.majIHM();
	}

	public boolean joueurPossedeMine (Mine mine)
	{
		return this.getJoueurTour().PossedeMine(mine);
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

	public String getPiece()
	{
		return "../images/ressources/NR.png";
	}

	public String getMinerais(int indice)
	{
		Joueur joueurActuel = this.getJoueurTour();

		if (joueurActuel.getListJeton().get(indice) != null && joueurActuel.getListJeton().size() > 1 )
		{
			String lien =  ((Minerai) joueurActuel.getListJeton().get(indice).getType()).getLienImage();
			return "../theme/images/ressources/" + lien;
		}

		return "";
	}

	public int getNbPiece()
	{
		return this.getJoueurTour().getNbPiece();
	}

	//Scénarios
	public void fermerFenetre ()
	{
		this.frameCarte.dispose();
		this.frameJoueur.dispose();
		this.frameNom.dispose();
	}
}
