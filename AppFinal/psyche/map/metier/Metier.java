package psyche.map.metier;


import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Groupe 4 SAE2.01 : Guelle Clément, Cauvin Pierre, Montagne Aubin, Delpech Nicolas
 * 							  Bouquet Jules, Rougeolle Henri, Yachir Yanis
 * La classe Metier gère les opérations liées aux sommets et aux arretes.
 */
public class Metier
{
	/*--------------*/
	/*  Données     */
	/*--------------*/

	GestionFichier gestionFichier = new GestionFichier(this);


	private final List<Sommet> sommets;
	private final List<Arrete> arretes;

	/*--------------*/
	/* Instructions */
	/*--------------*/

	/**
	 * Constructeur de la classe Metier.
	 * Initialise les listes de sommets et de arretes.
	 */
	public Metier()
	{
		this.sommets = new ArrayList<>();
		this.arretes = new ArrayList<>();

		this.setFichierCharger(getFicherCharger());
	}

	


	
	public Sommet ajouterSommet(int x, int y, int point, Couleur couleur)
	{
		for (Sommet sommet : sommets)
			if (sommet.getCouleur().name().equals(couleur.name()) && sommet.getPoint() == point)
			{
				JOptionPane.showMessageDialog(null, "La Sommet existe déjà", "Erreur", JOptionPane.ERROR_MESSAGE);
				return null;
			}

		if (x < 0 || x > 1000 || y < 0 || y > 800)
		{
			JOptionPane.showMessageDialog(null, "Les coordonnées ne sont pas valides", "Erreur", JOptionPane.ERROR_MESSAGE);
			return null;
		}

		Sommet sommet = Sommet.creerSommet(x, y, point, couleur );
		System.out.println(sommet);
		sommets.add(sommet);

		return sommet;
	}



	/**
	 * Modifie les coordonnées d'une sommet existante.
	 *
	 * @param x La nouvelle coordonnée X de la sommet.
	 * @param y La nouvelle coordonnée Y de la sommet.
	 * @return true si la modification a réussi, false si les coordonnées sont invalides ou si la sommet n'existe pas.
	 */
	public boolean modifierSommet(int x, int y, Sommet sommet)
	{
		if (x < 0 || x > 1000 || y < 0 || y > 800)
			return false;


		if (sommet == null)
		{
			return false;
		}

		sommet.setX(x);
		sommet.setY(y);

		return true;
	}


	public Sommet getSommet(Couleur couleur, int point)
	{
		for (Sommet sommet : this.sommets)
			if (sommet.getCouleur().name().equals(couleur.name()) && sommet.getPoint() == point)
				return sommet;

		return null;
	}

	public Sommet getSommet(String nom)
	{
		for (Sommet sommet : this.sommets)
			if (sommet.getNom().equals(nom))
				return sommet;

		return null;
	}


	/**
	 * Supprime une sommet existante.
	 *
	 * @param ind L'ID de la sommet à supprimer.
	 * @return true si la sommet a été supprimée, false si l'ID est invalide ou si la liste des sommets est vide.
	 */
	public boolean supprimerSommet(int ind)
	{
		if (this.sommets.isEmpty() || ind < 0 || ind >= this.sommets.size())
			return false;

		Sommet sommetSupp = this.sommets.get(ind);

		this.arretes.removeIf(Arretes -> Arretes.getDepart().equals(sommetSupp) || Arretes.getArrivee().equals(sommetSupp));

		this.sommets.remove(ind);
		return true;
	}


	/**
	 * Récupère la liste de toutes les sommets.
	 *
	 * @return La liste des sommets.
	 */
	public List<Sommet> getSommets() { return sommets; }





	/**
	 * Ajoute une Arretes entre deux sommets.
	 *
	 * @param depart La sommet de départ.
	 * @param arrivee La sommet d'arrivée.
	 * @param troncons Le nombre de tronçons de la Arretes.
	 * @return La Arretes ajoutée, ou null si une Arretes entre les mêmes sommets existe déjà.
	 */
	public Arrete ajouterArrete(Sommet depart, Sommet arrivee, int troncons)
	{

		if(!estPossibleArrete(depart, arrivee, troncons))
		{
			JOptionPane.showMessageDialog(null, "L'arrete existe déjà", "Erreur", JOptionPane.ERROR_MESSAGE);
			return null;
		}

		Arrete arrete = Arrete.creerArrete(depart, arrivee, troncons);
		arretes.add(arrete);

		depart .addArrete(arrete);
		arrivee.addArrete(arrete);

		return arrete;
	}


	public boolean estPossibleArrete( Sommet depart, Sommet arrivee, int troncons)
	{
		for (Arrete arrete : this.arretes)
			if (arrete.getDepart().equals(depart) && arrete.getArrivee().equals(arrivee))
				return false;

		for (Arrete arrete : this.arretes)
			if (arrete.getDepart().equals(arrivee) && arrete.getArrivee().equals(depart))
				return false;

		if ( troncons < 1 || troncons > 2 )
		{
			JOptionPane.showMessageDialog(null, "Nombre de tronçon invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
			return false;

		}


		return true;

	}




	/**
	 * Modifie le nombre de tronçons d'une Arretes entre deux sommets.
	 *
	 * @param depart La sommet de départ.
	 * @param arrivee La sommet d'arrivée.
	 * @param troncon Le nouveau nombre de tronçons.
	 * @return true si le nombre de tronçons a été modifié, false si le nombre de tronçons est invalide.
	 */
	public boolean modifierArrete(Sommet depart, Sommet arrivee, int troncon)
	{

		if (troncon != 1 && troncon != 2)
		{
			JOptionPane.showMessageDialog(null, "Nombre de tronçon invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		for (Arrete arrete : this.getArretes())
		{
			if (arrete.getDepart().equals(depart) && arrete.getArrivee().equals(arrivee))
			{
				arrete.setTroncons(troncon);
				return true;
			}
		}

		return false;
	}



	/**
	 * Récupère la liste de toutes les arretes.
	 *
	 * @return La liste des arretes.
	 */
	public List<Arrete> getArretes() { return this.arretes; }

	public void supprimerArreteSommet(Sommet sommet, Arrete arrete)
	{
		sommet.supprimerArreteSommet(sommet, arrete);
		this.arretes.remove(arrete);
	}



	/**
	 * Réinitialise les ID des sommets.
	 */
	public void resetId() { Sommet.resetId(); }





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
		return "\n\nMetier{ \n" + "MINE : \n" + sommets + "\n\nROUTES : \n" + arretes;
	}


}
