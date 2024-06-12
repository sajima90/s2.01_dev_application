package psyche.metier.map;


/**
 * @author Groupe 4 SAE2.01 : Guelle Clément, Cauvin Pierre, Montagne Aubin, Delpech Nicolas
 * 							  Bouquet Jules, Rougeolle Henri, Yachir Yanis
 * La classe permet de gérer la Route
 */
public class Route
{

	/*--------------*/
	/*  Données     */
	/*--------------*/

	private int  nbTroncons;
	private Mine mineDepart;
	private Mine mineArrivee;



	/*--------------*/
	/* Instructions */
	/*--------------*/

	/**
	 * Constructeur de la Route
	 *
	 * @param mineDepart
	 * 		Mine de départ
	 * @param mineArrivee
	 * 		Mine d'arrivée
	 * @param nbTroncons
	 * 		Nombre de segments sur la route
	 */
	private Route(Mine mineDepart, Mine mineArrivee, int nbTroncons)
	{
		this.mineDepart  = mineDepart;
		this.mineArrivee = mineArrivee;
		this.nbTroncons  = nbTroncons;
	}

	/**
	 * Factory de la Route, vérifie que les paramètres soit valide et créé la route sinon retourne null
	 *
	 * @param mineDepart
	 * 		mine de départ
	 * @param mineArrivee
	 * 		mine d'arrivée
	 * @param nombreTroncons
	 * 		nombre de segments sur la route
	 * @return La Route créée ou null en fonction de la validité des paramètres
	 */
	public static Route creerRoute(Mine mineDepart, Mine mineArrivee, int nombreTroncons)
	{



		if (nombreTroncons < 0 || nombreTroncons > 10)
			return null;

		if (mineDepart == null || mineArrivee == null)
			return null;

		if (mineDepart.equals(mineArrivee))
			return null;

		return new Route(mineDepart, mineArrivee, nombreTroncons);
	}



	/*--------------*/
	/*      Get     */
	/*--------------*/

	public int  getTroncons() { return this.nbTroncons;  }
	public Mine getDepart  () { return this.mineDepart;  }
	public Mine getArrivee () { return this.mineArrivee; }


	/*--------------*/
	/*      Set     */
	/*--------------*/


	public void setTroncons(int troncons) { this.nbTroncons  = troncons; }
	public void setDepart  (Mine depart)  { this.mineDepart  = depart;   }
	public void setArrivee (Mine arrivee) { this.mineArrivee = arrivee;  }


	/*-----------------*/
	/* Autres Méthodes */
	/*-----------------*/


	public String toString()
	{
		return  "Route{"
			   + "nbTroncons="
			   + this.nbTroncons
			   + ", mineDepart="
			   + this.mineDepart
			   + ", mineArrivee="
			   + this.mineArrivee
			   + "}";
	}

}
