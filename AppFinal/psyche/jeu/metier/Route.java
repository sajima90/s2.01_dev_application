/**
 * @author Groupe 4 SAE2.01 : Guelle Clément, Cauvin Pierre, Montagne Aubin, Delpech Nicolas Bouquet Jules, Rougeolle
 * 		Henri, Yachir Yanis La classe permet de gérer l'Route
 */

package psyche.jeu.metier;

public class Route
{

	/*--------------*/
	/*  Données     */
	/*--------------*/

	private int nbTroncons;
	private Mine mineDepart;
	private Mine mineArrivee;

	Route(Mine mineDepart, Mine mineArrivee, int nbTroncons)
	{
		this.mineDepart = mineDepart;
		this.mineArrivee = mineArrivee;
		this.nbTroncons = nbTroncons;
	}

	public static Route creerRoute(Mine mineDepart, Mine mineArrivee, int nombreTroncons)
	{

		if (nombreTroncons < 0 || nombreTroncons > 10)
			return null;

		if (mineDepart == null || mineArrivee == null)
			return null;

		if (mineDepart.equals(mineArrivee))
			return null;

		return new psyche.jeu.metier.Route(mineDepart, mineArrivee, nombreTroncons);
	}

	public int getTroncons()
	{
		return this.nbTroncons;
	}

	public Mine getDepart()
	{
		return this.mineDepart;
	}

	public Mine getArrivee()
	{
		return this.mineArrivee;
	}

	public void setTroncons(int troncons)
	{
		this.nbTroncons = troncons;
	}

	public void setDepart(Mine depart)
	{
		this.mineDepart = depart;
	}

	public void setArrivee(Mine arrivee)
	{
		this.mineArrivee = arrivee;
	}


	/*-----------------*/
	/* Autres Méthodes */
	/*-----------------*/

	public String toString()
	{
		return "Route de " + this.nbTroncons + " troncons entre " + this.mineDepart + " et " + this.mineArrivee;
	}

}
 