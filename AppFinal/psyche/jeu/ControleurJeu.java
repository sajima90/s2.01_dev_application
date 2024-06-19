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

	private final Controleur  ctrl;
	private final Metier      metier;
	private final FrameCarte  frameCarte;
	private       FrameJoueur frameJoueur1;
	private       FrameJoueur frameJoueur2;
	private       FrameNom    frameNom;

	/*--------------*/
	/* Méthodes */
	/*--------------*/

	/**
	 * Constructeur de Controleur
	 */

	public ControleurJeu(Controleur ctrl)
	{
		this.ctrl        = ctrl;
		this.metier      = new Metier    ();
		this.frameCarte  = new FrameCarte(this);
		this.frameNom    = new FrameNom  (this);
		this.frameJoueur1 = null;
		this.frameJoueur2 = null;

	}

	public Joueur getJoueurActuel() { return this.metier.getJoueurActuel(); }


	public Joueur setJoueur1(String nom)
	{
		return this.metier.setJoueur1(nom);
	}

	public Joueur setJoueur2(String nom)
	{
		return this.metier.setJoueur2(nom);
	}

	public Joueur getJoueur1()
	{
		return this.metier.getJoueur1();
	}

	public Joueur getJoueur2()
	{
		return this.metier.getJoueur2();
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
		this.frameJoueur1.majIHM();
		this.frameJoueur2.majIHM();
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

	public void changerTour()
	{

		this.metier.changerTour();
		this.majIHM();

	}

	public void possederMine(Mine mine)
	{

		System.out.println("Test possederMine");
		this.getJoueurActuel().ajouterMine(mine);

		System.out.println("EFSUVZEIGSVBFHJKEFGVJgvfuehefis      " + mine.estPrise());

		if ( mine.estPrise() )
		{
			System.out.println("Ajout de la ressource : au joueur " + this.getJoueurActuel().getNumJoueur());
			this.getJoueurActuel().ajouterRessource(mine.getJeton());
		}
		else
		{
			System.out.println("Mine vide");

		}
		mine.enleverMinerai();

	}

	public boolean joueurPossedeMine (Mine mine)
	{
		return this.getJoueurActuel().PossedeMine(mine);
	}

	public boolean mineEstAdjacent (Mine mine1, Mine mine2)
	{
		if (mine1 == null || mine2 == null)
		{
			return false;
		}

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
		Joueur joueurActuel = this.getJoueurActuel();

		if (joueurActuel.getListJeton().get(indice) != null && joueurActuel.getListJeton().size() > 1 )
		{
			String lien =  ((Minerai) joueurActuel.getListJeton().get(indice).getType()).getLienImage();
			return "../theme/images/ressources/" + lien;
		}

		return "";
	}

	public int getNbPiece()
	{
		return this.getJoueurActuel().getNbPiece();
	}


	public void setProprietaireRoute(Joueur joueur, Route route)
	{
		this.metier.setProprietaire(route, joueur);

	}


	//Scénarios
	public void fermerFenetre ()
	{
		this.frameCarte .dispose();
		this.frameJoueur1.dispose();
		this.frameJoueur2.dispose();
		this.frameNom   .dispose();
	}

	public void setFrameJoueur1(FrameJoueur frameJoueur)
	{
		this.frameJoueur1 = frameJoueur;
	}

	public void setFrameJoueur2(FrameJoueur frameJoueur)
	{
		this.frameJoueur2 = frameJoueur;
	}

}
