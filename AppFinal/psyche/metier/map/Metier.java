package psyche.metier.map;

import psyche.metier.minerai.Couleur;
import psyche.metier.minerai.Minerai;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * @author Groupe 4 SAE2.01 : Guelle Clément, Cauvin Pierre, Montagne Aubin, Delpech Nicolas
 * 							  Bouquet Jules, Rougeolle Henri, Yachir Yanis
 * La classe Metier gère les opérations liées aux mines et aux routes.
 */
public class Metier
{
	/*--------------*/
	/*  Données     */
	/*--------------*/

	GestionFichier gestionFichier = new GestionFichier(this);


	private final List<Mine>  mines;
	private final List<Route> routes;

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

		this.setFichierCharger(getFicherCharger());
	}


	/************/
	/***Mines***/
	/************/



	/**
	 * Ajoute une mine avec le nom et les coordonnées spécifiés.
	 *
	 * @param x La coordonnée X de la mine.
	 * @param y La coordonnée Y de la mine.
	 * @return La mine ajoutée, ou null si la mine existe déjà ou si les coordonnées sont invalides.
	 */
	public Mine ajouterMine(int x, int y, int point, Couleur couleur)
	{
		for (Mine mine : mines)
			if (mine.getCouleur().name().equals(couleur.name()) && mine.getPoint() == point)
			{
				JOptionPane.showMessageDialog(null, "La Mine existe déjà", "Erreur", JOptionPane.ERROR_MESSAGE);
				return null;
			}

		if (x < 0 || x > 1000 || y < 0 || y > 800)
		{
			JOptionPane.showMessageDialog(null, "Les coordonnées ne sont pas valides", "Erreur", JOptionPane.ERROR_MESSAGE);
			return null;
		}

		Mine mine = Mine.creerMine(x, y, point, couleur );
		mines.add(mine);

		return mine;
	}



	/**
	 * Modifie les coordonnées d'une mine existante.
	 *
	 * @param x La nouvelle coordonnée X de la mine.
	 * @param y La nouvelle coordonnée Y de la mine.
	 * @return true si la modification a réussi, false si les coordonnées sont invalides ou si la mine n'existe pas.
	 */
	public boolean modifierMine(int x, int y, Mine mine)
	{
		if (x < 0 || x > 1000 || y < 0 || y > 800)
			return false;


		if (mine == null)
		{
			return false;
		}

		mine.setX(x);
		mine.setY(y);

		return true;
	}


	public Mine getMineParMineraiPoint(Couleur couleur, int point)
	{
		for (Mine mine : this.mines)
			if (mine.getCouleur().name().equals(couleur.name()) && mine.getPoint() == point)
				return mine;

		return null;
	}


	/**
	 * Supprime une mine existante.
	 *
	 * @param ind L'ID de la mine à supprimer.
	 * @return true si la mine a été supprimée, false si l'ID est invalide ou si la liste des mines est vide.
	 */
	public boolean supprimerMine(int ind)
	{
		if (this.mines.isEmpty() || ind < 0 || ind >= this.mines.size())
			return false;

		Mine mineSupp = this.mines.get(ind);

		this.routes.removeIf(route -> route.getDepart().equals(mineSupp) || route.getArrivee().equals(mineSupp));

		this.mines.remove(ind);
		return true;
	}


	/**
	 * Récupère la liste de toutes les mines.
	 *
	 * @return La liste des mines.
	 */
	public List<Mine> getMines() { return mines; }


	/************/
	/***Routes***/
	/************/



	/**
	 * Ajoute une route entre deux mines.
	 *
	 * @param depart La mine de départ.
	 * @param arrivee La mine d'arrivée.
	 * @param troncons Le nombre de tronçons de la route.
	 * @return La route ajoutée, ou null si une route entre les mêmes mines existe déjà.
	 */
	public Route ajouterRoute(Mine depart, Mine arrivee, int troncons)
	{



		if(!estPossibleRoute(depart, arrivee, troncons))
		{
			JOptionPane.showMessageDialog(null, "La route existe déjà", "Erreur", JOptionPane.ERROR_MESSAGE);
			return null;
		}

		Route route = Route.creerRoute(depart, arrivee, troncons);
		routes.add(route);

		depart .addRoute(route);
		arrivee.addRoute(route);

		return route;
	}


	public boolean estPossibleRoute( Mine depart, Mine arrivee, int troncons)
	{
		for (Route route : this.getRoutes())
			if (route.getDepart().equals(depart) && route.getArrivee().equals(arrivee))
				return false;

		for (Route route : this.getRoutes())
			if (route.getDepart().equals(arrivee) && route.getArrivee().equals(depart))
				return false;

		if ( troncons < 1 || troncons > 2 )
		{
			JOptionPane.showMessageDialog(null, "Nombre de tronçon invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
			return false;

		}


		return true;

	}




	/**
	 * Modifie le nombre de tronçons d'une route entre deux mines.
	 *
	 * @param depart La mine de départ.
	 * @param arrivee La mine d'arrivée.
	 * @param troncon Le nouveau nombre de tronçons.
	 * @return true si le nombre de tronçons a été modifié, false si le nombre de tronçons est invalide.
	 */
	public boolean modifierRoute(Mine depart, Mine arrivee, int troncon)
	{

		if (troncon != 1 && troncon != 2)
		{
			JOptionPane.showMessageDialog(null, "Nombre de tronçon invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		for (Route route : this.getRoutes())
		{
			if (route.getDepart().equals(depart) && route.getArrivee().equals(arrivee))
			{
				route.setTroncons(troncon);
				return true;
			}
		}

		return false;
	}



	/**
	 * Récupère la liste de toutes les routes.
	 *
	 * @return La liste des routes.
	 */
	public List<Route> getRoutes() { return this.routes; }

	public void supprimerRouteVille(Mine mine, Route route)
	{
		mine.supprimerRouteVille(mine, route);
		this.routes.remove(route);
	}



	/**
	 * Réinitialise les ID des mines.
	 */
	public void resetId() { Mine.resetId(); }





	/*------------------------*/
	/* Gestion de fichier     */
	/*------------------------*/


	/**
	 * Enregistre les données actuelles dans un fichier.
	 */
	public void enregistrer() { this.gestionFichier.enregistrer(); }

	/**
	 * Enregistre les données actuelles dans un nouveau fichier.
	 */
	public void enregistrerSous() { this.gestionFichier.enregistrerSous(); }

	/**
	 * Définit le chemin du fichier à charger.
	 *
	 * @param path Le chemin du fichier.
	 */
	public void setFichierCharger(String path) { this.gestionFichier.setFichierCharger(path); }

	/**
	 * Récupère le chemin du fichier chargé.
	 *
	 * @return Le chemin du fichier.
	 */
	public String getFicherCharger() { return this.gestionFichier.getFichierCharger(); }




	public String toString()
	{
		return "\n\nMetier{ \n" + "MINE : \n" + mines + "\n\nROUTES : \n" + routes;
	}

}
