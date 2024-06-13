package psyche.map.metier;


/**
 * @author Groupe 4 SAE2.01 : Guelle Clément, Cauvin Pierre, Montagne Aubin, Delpech Nicolas
 * 							  Bouquet Jules, Rougeolle Henri, Yachir Yanis
 * La classe permet de gérer l'arrete
 */

public class Arrete
{

	/*--------------*/
	/*  Données     */
	/*--------------*/

	private int    nbTroncons;
	private Sommet sommetDepart;
	private Sommet sommetArrivee;


	private Arrete(Sommet mineDepart, Sommet mineArrivee, int nbTroncons)
	{
		this.sommetDepart  = mineDepart;
		this.sommetArrivee = mineArrivee;
		this.nbTroncons  = nbTroncons;
	}

	public static Arrete creerArrete(Sommet mineDepart, Sommet mineArrivee, int nombreTroncons)
	{

		if (nombreTroncons < 0 || nombreTroncons > 10)
			return null;

		if (mineDepart == null || mineArrivee == null)
			return null;

		if (mineDepart.equals(mineArrivee))
			return null;

		return new Arrete(mineDepart, mineArrivee, nombreTroncons);
	}



	public int    getTroncons() { return this.nbTroncons;    }
	public Sommet getDepart  () { return this.sommetDepart;  }
	public Sommet getArrivee () { return this.sommetArrivee; }



	public void setTroncons(int troncons)   { this.nbTroncons    = troncons; }
	public void setDepart  (Sommet depart)  { this.sommetDepart  = depart;   }
	public void setArrivee (Sommet arrivee) { this.sommetArrivee = arrivee;  }


	/*-----------------*/
	/* Autres Méthodes */
	/*-----------------*/


	public String toString()
	{
		return "Route de " + this.nbTroncons + " troncons entre " + this.sommetDepart + " et " + this.sommetArrivee;
	}

}
