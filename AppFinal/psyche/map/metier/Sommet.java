package psyche.map.metier;


import java.util.ArrayList;
import java.util.List;

/**
 * @author Groupe 4 SAE2.01 : Guelle Clément, Cauvin Pierre, Montagne Aubin, Delpech Nicolas
 * 							  Bouquet Jules, Rougeolle Henri, Yachir Yanis
 * La classe permet de gérer la Sommet
 */

public class Sommet
{

	private static int  nbArretes = 0;

	private int         id;
	private int         x;
	private int         y;
	private int         point;

	private String      nom;

	private List<Arrete> arretes;

	private Couleur couleur;


	private Sommet(int x, int y, int point, Couleur couleur, String nom)
	{
		// Auto-incrémentation de l'id de la mine
		this.id       = ++Sommet.nbArretes;
		this.x        = x;
		this.y        = y;
		this.arretes  = new ArrayList<>();
		this.couleur  = couleur;
		this.point    = point;
		this.nom      = nom;
	}

	/**
	 * Méthode permettant de créer une mine
	 * @param x la coordonnée x de la mine
	 * @param y la coordonnée y de la mine
	 * @param point le nombre de points que donne la mine
	 * @param couleur la couleur de la mine
	 * @return la mine créée
	 */
	public static Sommet creerSommet(int x, int y, int point, Couleur couleur)
	{
		if (x < 0 || x > 1000 || y < 0 || y > 800)
			return null;

		String nom = "";
		if (couleur.name().equals("ROME"))
			nom = couleur.name();
		else
			nom = couleur.name().substring(0, 1) + point;

		return new Sommet(x, y, point, couleur, nom);
	}




	public int          getId       () { return this.id;       }
	public int          getX        () { return this.x;        }
	public int          getY        () { return this.y;        }
	public int          getPoint    () { return this.point;    }
	public Couleur      getCouleur  () { return this.couleur;  }
	public List<Arrete> getArretes   () { return this.arretes;  }
	public String       getNom() { return this.nom; }

	public void         setX   (int x)	    { this.x   = x;    }
	public void         setY   (int y)      { this.y   = y;    }

	/*-----------------*/
	/* Autres Méthodes */
	/*-----------------*/

	public static void resetId() { Sommet.nbArretes = 0; }


	public void addArrete(Arrete arrete) { this.arretes.add(arrete); }

	public void supprimerArreteSommet(Sommet sommet, Arrete arrrete)
	{
		this.arretes.remove(arrrete);
	}


	public String toString()
	{
		return "mine{" +
				"id=" + this.id +
				", x=" + this.x +
				", y=" + this.y +
				", point=" + this.point +
				", couleur=" + this.couleur +
				'}';
	}
}
