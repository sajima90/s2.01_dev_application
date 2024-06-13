package psyche.map;

import psyche.Controleur;
import psyche.map.metier.Arrete;
import psyche.map.metier.Couleur;
import psyche.map.metier.Metier;
import psyche.map.metier.Sommet;
import psyche.map.vue.*;

import java.util.List;

public class ControleurMap
{

	/*--------------*/
	/*  Données     */
	/*--------------*/

	private final Metier metier;
	private final FrameMap frameMap;
	private final Controleur ctrl;

	private FrameAjouterSommet   frameAjouterSommet;
	private FrameAjouterArrete   frameAjouterArrete;
	private FrameModifierSommet  frameModifierSommet;
	private FrameModifierArrete  frameModifierArrete;
	private FrameSupprimerSommet frameSupprimerSommet;

	private PanelGraph     panelGraph;
	private PanelInfoVille panelInfoVille;


	/*--------------*/
	/*  Méthodes    */
	/*--------------*/

	/**
	 * Constructeur de Controleur
	 */

	public ControleurMap(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.metier = new Metier();
		this.frameMap = new FrameMap(this, this.ctrl);
		this.panelGraph = new PanelGraph(this);
		this.panelInfoVille = new PanelInfoVille(this);
	}


	public void ouvrirAjouterSommet()
	{
		this.frameAjouterSommet = new FrameAjouterSommet(this);
	}

	/**
	 * Crée la frame d'ajout de routes
	 */
	public void ouvrirAjouterArrete()
	{
		this.frameAjouterArrete = new FrameAjouterArrete(this);
	}

	/**
	 * Crée la frame de modification de ville
	 */
	public void ouvrirModifierSommet()
	{
		this.frameModifierSommet = new FrameModifierSommet(this);
	}

	/**
	 * Crée la frame de modification de route
	 */
	public void ouvrirModifierArrete()
	{
		this.frameModifierArrete = new FrameModifierArrete(this);
	}

	public void ouvrirSupprimerSommet()
	{
		this.frameSupprimerSommet = new FrameSupprimerSommet(this);
	}

	/**
	 * Ajoute une mine
	 *
	 * @param x
	 * 		Coordonnées X de la mine
	 * @param y
	 * 		Coordonnées Y de la mine
	 */
	public Sommet ajouterSommet(int x, int y, int point, Couleur couleur)
	{
		this.majIHM();
		return this.metier.ajouterSommet(x, y, point, couleur);
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

	/**
	 * Ajoute une route
	 *
	 * @param depart
	 * 		Sommet de départ
	 * @param arrivee
	 * 		Sommet d'arrivée
	 * @param troncons
	 * 		Nombre de tronçons de la route
	 */
	public Arrete ajouterArrete(Sommet depart, Sommet arrivee, int troncons)
	{
		this.majIHM();
		return this.metier.ajouterArrete(depart, arrivee, troncons);
	}

	/**
	 * Modifie une mine
	 *
	 * @param x
	 * 		Coordonnées X de la mine
	 * @param y
	 * 		Coordonnées Y de la mine
	 */
	public void modifierSommet(int x, int y, String nom)
	{
		this.metier.modifierSommet(x, y, this.getSommet(nom));
		this.majIHM();
	}

	public boolean modifierArrete(Sommet mineDepart, Sommet mineArrivee, int nbTroncons)
	{
		if (this.metier.modifierArrete(mineDepart, mineArrivee, nbTroncons))
		{
			this.majIHM();
			return true;
		}

		if (this.metier.modifierArrete(mineArrivee, mineDepart, nbTroncons))
		{
			this.majIHM();
			return true;
		}

		return false;
	}

	public void supprimerArrete(Sommet mineDepart, Arrete route)
	{
		this.metier.supprimerArreteSommet(mineDepart, route);
		this.majIHM();
	}

	public void supprimerSommet(int i)
	{
		this.metier.supprimerSommet(i);
	}


	public Sommet getSommet(String nom)
	{
		return this.metier.getSommet(nom);
	}

	public Sommet getSommet(Couleur couleur, int point)
	{
		return this.metier.getSommet(couleur, point);
	}

	/**
	 * Renvoie la liste des Mines
	 */
	public List<Sommet> getSommets()
	{
		return this.metier.getSommets();
	}

	/**
	 * Renvoie la liste des Routes
	 */
	public List<Arrete> getArretes()
	{
		return this.metier.getArretes();
	}

	/**
	 * Renvoie une liste de Routes liées à une mine
	 *
	 * @param mine
	 * @return List<Arrete> Nom de la mine
	 */
	public List<Arrete> getArrete(Sommet mine)
	{
		return mine.getArretes();
	}

	public boolean estPossibleArrete(Sommet depart, Sommet arrivee, int troncons)
	{
		return this.metier.estPossibleArrete(depart, arrivee, troncons);
	}

	/**
	 * Met à jour l'IHM
	 */
	public void majIHM()
	{
		this.frameMap.getPanelGraph().repaint();
	}

	/**
	 * Renvoie une Sommet à partir de son nom
	 *
	 * @param villeSelect
	 * 		Nom de la mine dont les infos doivent être affichées.
	 */
	public void majIHM(Sommet villeSelect)
	{
		this.frameMap.getPanelGraph().repaint();
		this.frameMap.getPanelInfoVille().majVilleInfo(villeSelect.getX(), villeSelect.getY(), villeSelect.getCouleur(), villeSelect.getPoint());
	}

	//Fichier

	/**
	 * Enregistre les données actuelles dans le fichier et met à jour l'IHM.
	 */
	public void enregistrer()
	{
		this.metier.enregistrer();
		this.majIHM();
	}

	/**
	 * Enregistre les données actuelles dans un fichier seléctionné par l'utilisateur et met à jour l'IHM.
	 */
	public void enregistrerSous()
	{
		this.majIHM();
		this.metier.enregistrerSous();
	}

	/**
	 * Renvoie le chemin d'accès du fichier d'où charger les données
	 */
	public String getFichierCharger()
	{
		return this.metier.getFicherCharger();
	}

	/**
	 * Définit le chemin d'accès du fichier
	 *
	 * @param path
	 * 		Le chemin d'accès à définir.
	 */
	public void setFichierCharger(String path)
	{
		this.metier.setFichierCharger(path);
		this.majIHM();
	}
}
