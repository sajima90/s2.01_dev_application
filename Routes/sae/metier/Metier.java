package sae.metier;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Metier
{
	GestionFichier gestionFichier = new GestionFichier(this);

	private final List<Ville> villes;
	private final List<Route> routes;

	public Metier()
	{
		this.villes = new ArrayList<>();
		this.routes = new ArrayList<>();
	}


	/************/
	/***Villes***/
	/************/

	public Ville ajouterVille(String nom, int x, int y)
	{
		for (Ville ville : villes)
			if (ville.getNom().toLowerCase().trim().equals(nom.toLowerCase().trim())){
				JOptionPane.showMessageDialog(null, "La ville existe déjà", "Erreur", JOptionPane.ERROR_MESSAGE);
				return null;
			}

		if (x < 0 || x > 1000 || y < 0 || y > 800)
		{
			JOptionPane.showMessageDialog(null, "Les coordonnées ne sont pas valides", "Erreur", JOptionPane.ERROR_MESSAGE);
			return null;
		}

		Ville ville = Ville.creerVille(nom, x, y);
		villes.add(ville);
		return ville;
	}


	public void modifierVille(String nom, int x, int y)
	{
		if (x < 0 || x > 1000 || y < 0 || y > 800)
			return;

		Ville ville = this.getVilleParNom(nom);
		if (ville != null)
		{
			ville.setX(x);
			ville.setY(y);
		}
	}



	public Ville getVilleParNom(String villeDepart)
	{
		for (Ville ville : villes)
			if (ville.getNom().equals(villeDepart))
				return ville;

		return null;
	}

	public List<Ville> getVilles()
	{
		return villes;
	}



	/************/
	/***Routes***/
	/************/

	public Route ajouterRoute(Ville depart, Ville arrivee, int troncons)
	{
		for (Route route : this.getRoutes())
			if (route.getDepart().equals(depart) && route.getArrivee().equals(arrivee))
				return null;

		Route route = Route.creerRoute(depart, arrivee, troncons);
		routes.add(route);

		depart.addRoute(route);
		arrivee.addRoute(route);

		return route;
	}

	public List<Route> getRoutes()
	{
		return this.routes;
	}


	@Override
	public String toString()
	{
		return "\n\nMetier{ \n" + "VILLE : \n" + villes + "\n\nROUTES : \n" + routes;
	}

	public void resetId()
	{
		Ville.resetId();
	}

	public void enregistrer()
	{
		this.gestionFichier.enregistrer();
	}

	public void enregistrerSous()
	{
		this.gestionFichier.enregistrerSous();
	}

	public void setFichierCharger(String path)
	{
		this.gestionFichier.setFichierCharger(path);
	}

	public String getFicherCharger()
	{
		return this.gestionFichier.getFichierCharger();
	}
}
