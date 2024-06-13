package psyche;

import psyche.metier.map.Metier;
import psyche.metier.map.Mine;
import psyche.metier.map.Route;
import psyche.metier.minerai.Couleur;
import psyche.vue.map.*;

import java.util.List;

/**
 * @author Groupe 4 SAE2.01 : Guelle Clément, Cauvin Pierre, Montagne Aubin, Delpech Nicolas
 * 							  Bouquet Jules, Rougeolle Henri, Yachir Yanis
 *
 */

public class ControleurMap
{

	/*--------------*/
	/*  Données     */
	/*--------------*/

	private final Metier metier;
	private final Frame frame;

	private FrameAjouterVille frameAjouterVille;
	private FrameAjouterRoute frameAjouterRoute;
	private FrameModifierVille frameModifierVille;
	private FrameModifierRoute frameModifierRoute;
	private FrameSupprimerMine frameSupprimerMine;



	private PanelGraph panelGraph;
	private PanelInfoVille panelInfoVille;


	/*--------------*/
	/*  Méthodes    */
	/*--------------*/

	/**
	 * Constructeur de Controleur
	 */

	public ControleurMap()
	{
		this.metier = new Metier();
		this.frame = new Frame(this);
		this.panelGraph = new PanelGraph(this);
		this.panelInfoVille = new PanelInfoVille(this);
	}

	/**
	 * Crée la frame d'ajout de ville
	 */
	public void ouvrirAjouterVille()  { this.frameAjouterVille  = new FrameAjouterVille(this); }

	/**
	 * Crée la frame d'ajout de routes
	 */
	public void ouvrirAjouterRoute()  { this.frameAjouterRoute  = new FrameAjouterRoute(this); }

	/**
	 * Crée la frame de modification de ville
	 */
	public void ouvrirModifierVille() { System.out.println("Test Controleur modifier route"); this.frameModifierVille = new FrameModifierVille(this);}

	/**
	 * Crée la frame de modification de route
	 */
	public void ouvrirModifierRoute() { this.frameModifierRoute = new FrameModifierRoute(this);}


	public void ouvrirSupprimerMine() { this.frameSupprimerMine = new FrameSupprimerMine(this);}
	/**
	 * Ajoute une mine
	 *
	 * @param x
	 * 		Coordonnées X de la mine
	 * @param y
	 * 		Coordonnées Y de la mine
	 */
	public Mine ajouterMine(int x, int y, int point, Couleur couleur)
	{
		this.majIHM();
		return this.metier.ajouterMine(x, y, point, couleur);
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
	 * 		Mine de départ
	 * @param arrivee
	 * 		Mine d'arrivée
	 * @param troncons
	 * 		Nombre de tronçons de la route
	 */
	public Route ajouterRoute(Mine depart, Mine arrivee, int troncons)
	{
		this.majIHM();
		return this.metier.ajouterRoute(depart, arrivee, troncons);
	}



	/**
	 * Modifie une mine
	 *
	 * @param x
	 * 		Coordonnées X de la mine
	 * @param y
	 * 		Coordonnées Y de la mine
	 */
	public void modifierMine(int x, int y, Couleur couleur, int point)
	{
		this.metier.modifierMine(x, y, this.getMineParMineraiPoint(couleur, point));
		this.majIHM();
	}

	public boolean modifierRoute(Mine mineDepart, Mine mineArrivee, int nbTroncons)
	{
		if ( this.metier.modifierRoute(mineDepart  , mineArrivee, nbTroncons) )
		{
			this.majIHM();
			return true;
		}

		if ( this.metier.modifierRoute(mineArrivee , mineDepart , nbTroncons) )
		{
			this.majIHM();
			return true;
		}

		return false;
	}

	public void supprimerRoute(Mine mineDepart, Route route)
	{
		this.metier.supprimerRouteVille(mineDepart, route);
		this.majIHM();
	}


	public void supprimerMine(int i)
	{
		this.metier.supprimerMine(i);
	}

	/**
	 * Renvoie une Mine à partir du point et de la couleur entrée
	 *
	 * @param couleur
	 * 		Couleur de la mine à trouver
	 * @param point
	 * 		Point de la mine à trouver
	 */
	public Mine getMineParMineraiPoint(Couleur couleur, int point) { return this.metier.getMineParMineraiPoint(couleur, point); }

	/**
	 * Renvoie la liste des Mines
	 *
	 */
	public List<Mine> getMines() { return this.metier.getMines(); }




	/**
	 * Renvoie la liste des Routes
	 *
	 */
	public List<Route> getRoutes() { return this.metier.getRoutes(); }

	/**
	 * Renvoie une liste de Routes liées à une mine
	 *
	 * @param mine
	 * @return List<Route>
	 * 		Nom de la mine
	 */
	public List<Route> getRoute(Mine mine) { return mine.getRoutes(); }



	public boolean estPossibleRoute(Mine depart, Mine arrivee, int troncons)
	{
		return this.metier.estPossibleRoute(depart, arrivee, troncons);
	}

	/**
	 * Met à jour l'IHM
	 *
	 */
	public void majIHM() { this.frame.getPanelGraph().repaint(); }

	/**
	 * Renvoie une Mine à partir de son nom
	 *
	 * @param villeSelect
	 * 		Nom de la mine dont les infos doivent être affichées.
	 */
	public void majIHM(Mine villeSelect)
	{
		this.frame.getPanelGraph().repaint();
		this.frame.getPanelInfoVille().majVilleInfo(villeSelect.getX(), villeSelect.getY(), villeSelect.getCouleur(), villeSelect.getPoint());
	}


	//Fichier

	/**
	 * Enregistre les données actuelles dans le fichier et met à jour l'IHM.
	 *
	 */
	public void enregistrer()
	{
		this.metier.enregistrer();
		this.majIHM();
	}

	/**
	 * Enregistre les données actuelles dans un fichier seléctionné par l'utilisateur et met à jour l'IHM.
	 *
	 */
	public void enregistrerSous()
	{
		this.majIHM();
		this.metier.enregistrerSous();
	}

	/**
	 * Renvoie le chemin d'accès du fichier d'où charger les données
	 *
	 */
	public String getFichierCharger() {
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




	//	// CONTROLEUR MINERAI
	//
	//
	//
	//	private Frame   ihm;
	//	private Pioche  pioche;
	//	private Plateau metier;
	//
	//	public Controleur()
	//	{
	//		this.metier = new Plateau();
	//		this.pioche = new Pioche();
	//
	//		Jeton jeton;
	//		while ((jeton = pioche.tirerJeton()) != null)
	//		{
	//			metier.ajouterRessource(jeton);
	//		}
	//
	//		this.ihm    = new Frame(this);
	//
	//
	//	}
	//
	//	public String getImageFond()
	//	{
	//		return "../images/plateau.png";
	//	}
	//
	//	public String getPiece()
	//	{
	//		return "../images/bronze.png";
	//	}
	//
	//	public String getMinerai(int indice)
	//	{
	//		return "../images/bronze.png";
	//		//return "../images/" + (((Minerai) metier.getListJeton().get(indice).getType()).name()).toLowerCase() + ".png";
	//	}
	//
	//	public int getNbPiece()
	//	{
	//		return metier.getNbPiece();
	//	}







}