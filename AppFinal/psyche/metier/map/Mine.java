package psyche.metier.map;

import psyche.metier.minerai.Couleur;
import psyche.metier.minerai.Minerai;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Groupe 4 SAE2.01 : Guelle Clément, Cauvin Pierre, Montagne Aubin, Delpech Nicolas
 * 							  Bouquet Jules, Rougeolle Henri, Yachir Yanis
 * La classe permet de gérer la Mine
 */
public class Mine // sommet
{

	/*--------------*/
	/*  Données     */
	/*--------------*/

	// Constante :

	private static int         NbMines = 0;


	// Variables :

	private int         id;
	private int         x;
	private int         y;
	private int         point;

	private List<Route> routes;

	private Couleur     couleur;
	private Minerai		minerai;

	private boolean     estPrise;


	/*--------------*/
	/* Instructions */
	/*--------------*/


	/**
	 * Constructeur de la Mine
	 *
	 * @param x
	 * 		Coordonnée X de la mine
	 * @param y
	 * 		Coordonnée Y de la mine
	 *
	 * @param point
	 * 		Nombre de points que donne la mine
	 */
	private Mine(int x, int y, int point, Couleur couleur)
	{
		// Auto-incrémentation de l'id de la mine
		this.id       = ++Mine.NbMines;
		this.x        = x;
		this.y        = y;
		this.routes   = new ArrayList<>();
		this.minerai  = null;
		this.couleur  = couleur;
		this.point    = point;
		this.estPrise = false;
	}

	/**
	 * Factory de Mine, vérifie que les paramètres soient valide et créé la mine sinon retourne null
	 *
	 * @param x
	 * 		coordonnée X de la mine
	 * @param y
	 * 		coordonnée Y de la mine
	 * @param couleur
	 * 		Couleur de la mine
	 *
	 * @param point
	 * 		Nombre de points que donne la mine
	 *
	 * @return La Mine créée ou null en fonction de la validité des paramètres
	 */
	public static Mine creerMine(int x, int y, int point, Couleur couleur)
	{
		if (x < 0 || x > 1000 || y < 0 || y > 800)
			return null;

		return new Mine(x, y, point, couleur);
	}



	/*--------------*/
	/*      Get     */
	/*--------------*/

	public int         getId       () { return this.id;       }
	public int         getX        () { return this.x;        }
	public int         getY        () { return this.y;        }
	public List<Route> getRoutes   () { return this.routes;   }
	public Minerai     getMinerai  () { return this.minerai;  }
	public Couleur     getCouleur  () { return this.couleur;  }
	public int         getPoint    () { return this.point; 	  }
	public boolean     getMinePrise() { return this.estPrise; }

	/*--------------*/
	/*      Set     */
	/*--------------*/

	public void setX   (int x)	    { this.x   = x;   }
	public void setY   (int y)      { this.y   = y;   }
	public void setMinerai (Minerai minerai) { this.minerai = minerai; }
	public boolean setMinePrise () { return !this.estPrise; }

	/*-----------------*/
	/* Autres Méthodes */
	/*-----------------*/

	/**
	 * Méthode permettant la remise à 0 de l'id Mine
	 */
	public static void resetId() { Mine.NbMines = 0; }

	/**
	 * Méthode permettant de créer une route
	 * @param route la route à créer
	 */
	public void addRoute(Route route) { this.routes.add(route); }

	public void supprimerRouteVille(Mine mine, Route route)
	{
		this.routes.remove(route);
	}


    public String toString()
	{
        return "mine{" +
                "id=" + this.id +
                ", x=" + this.x +
                ", y=" + this.y +
                ", routes=" + this.routes.size() + "}";
    }
}
