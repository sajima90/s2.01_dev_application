/**
 * @author Groupe 4 SAE2.01 : Guelle Clément, Cauvin Pierre, Montagne Aubin, Delpech Nicolas Bouquet Jules, Rougeolle
 * 		Henri, Yachir Yanis La classe Metier gère les opérations liées aux mines et aux routes.
 */
package psyche.jeu.metier;

import psyche.jeu.metier.Mine;
import psyche.jeu.metier.Route;
import psyche.jeu.metier.Couleur;
import psyche.map.metier.Sommet;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Metier
{
	/*--------------*/
	/* Données      */
	/*--------------*/

	//						   Al,Ag,Au,Co,Fe,Ni,Pt,Ti,NR
	public int[] nbMinerais = {4, 4, 4, 4, 4, 4, 4, 4, 8};

	private final List<Mine> mines;
	private final List<Route> routes;

	private final GestionFichier gestionFichier = new GestionFichier(this);

	/*--------------*/
	/* Instructions */
	/*--------------*/

	/**
	 * Constructeur de la classe Metier.
	 * Initialise les listes de mines et de routes.
	 */
	public Metier()
	{
		this.mines  = new ArrayList<>();
		this.routes = new ArrayList<>();

		this.setFichierCharger(getFichierCharger());

		this.initMinerais();
	}

	/*------------*/
	/* Méthodes   */
	/*------------*/

	public void initMinerais()
	{
		for(int i = 0; i<this.getMines().size();i++)
		{
			if(!this.getMines().get(i).getNom().equals("ROME"))
			{
				int rdm = (int) (Math.random() * 9);

				//System.out.println(rdm);
				if (this.nbMinerais[rdm] > 0)
				{
					this.nbMinerais[rdm]--;


					if (rdm < 8)
						this.getMines().get(i).setJeton(new Jeton (Minerai.values()[rdm]));
					else
						this.getMines().get(i).setJeton(new Jeton (Piece.values()[0]));
				}
				else
					i--;
			}
		}
	}

	public void initMinerais(int indexMine, int indexMinerais)
	{
		if (this.nbMinerais[indexMinerais] > 0)
		{
			this.getMine(indexMine).setJeton(new Jeton(Minerai.values()[indexMinerais]));
		}

	}

	/*------*/
	/* Get  */
	/*------*/



	/**
	 * Renvoie le fichier chargé
	 *
	 * @return Le chemin du fichier chargé sous forme de String
	 */
	public String getFichierCharger()
	{
		return this.gestionFichier.getFichierCharger();
	}

	/**
	 * Récupère une mine précise selon son id
	 *
	 * @param id
	 * @return Une mine
	 */
	public Mine getMine(int id)
	{
		for (Mine mine : this.mines)
			if (mine.getId() == id)
				return mine;

		return null;
	}



	/**
	 * Récupère la liste de toutes les mines.
	 *
	 * @return La liste des mines.
	 */
	public List<Mine> getMines()
	{
		return this.mines;
	}

	/**
	 * Récupère la liste de toutes les routes.
	 *
	 * @return La liste des routes.
	 */
	public List<Route> getRoutes()
	{
		return this.routes;
	}



	public void resetId()
	{
		Mine.resetId();
	}

	public Mine getMineIndice(int x, int y)
	{
		for (Mine mine : this.mines)
			if (mine.getX() == x && mine.getY() == y)
				return mine;

		return null;
	}

	public void setFichierCharger(String path)
	{
		this.gestionFichier.setFichierCharger(path);
	}

	public Mine ajouterMine(int x, int y, int point, Couleur couleur)
	{

		System.out.println("x :" + x);
		System.out.println("y :" + y);
		
		if (x < 0 || x > 1000 || y < 0 || y > 800)
		{
			JOptionPane.showMessageDialog(null, "Les coordonnées ne sont pas valides", "Erreur", JOptionPane.ERROR_MESSAGE);
			return null;
		}

		System.out.println("Mine créée : " + couleur.name().substring(0, 1) + point);
		Mine mine = Mine.creerMine(x, y, point, couleur);
		System.out.println(mine);
		mines.add(mine);

		return mine;
	}

	public Route ajouterRoute(Mine depart, Mine arrivee, int troncons)
	{

		if (!estPossibleRoute(depart, arrivee, troncons))
		{
			JOptionPane.showMessageDialog(null, "L'route existe déjà", "Erreur", JOptionPane.ERROR_MESSAGE);
			return null;
		}

		if (depart == null || arrivee == null) {
			throw new IllegalArgumentException("Depart or Arrivee mine is null");
		}
		Route route = new Route(depart, arrivee, troncons);
		this.routes.add(route);
		depart.addRoute(route);
		arrivee.addRoute(route);

		return route;
	}

	public boolean estPossibleRoute(Mine depart, Mine arrivee, int troncons)
	{
		for (Route route : this.routes)
			if (route.getDepart().equals(depart) && route.getArrivee().equals(arrivee))
				return false;

		for (Route route : this.routes)
			if (route.getDepart().equals(arrivee) && route.getArrivee().equals(depart))
				return false;

		if (troncons < 1 || troncons > 2)
		{
			JOptionPane.showMessageDialog(null, "Nombre de tronçon invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
			return false;

		}

		return true;

	}

	public String toString()
	{
		return "\n\nMetier{ \n" + "MINE : \n" + mines + "\n\nROUTES : \n" + routes;
	}

}
