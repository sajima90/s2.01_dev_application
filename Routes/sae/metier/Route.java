package sae.metier;

public class Route
{

	private int nbTroncons;
	private Ville villeDepart;
	private Ville villeArrivee;

	/**
	 * Constructeur de la Route
	 *
	 * @param villeDepart
	 * 		ville de départ
	 * @param villeArrivee
	 * 		ville d'arrivé
	 * @param nbTroncons
	 * 		nombres de segment sur la route
	 */
	private Route(Ville villeDepart, Ville villeArrivee, int nbTroncons)
	{
		this.villeDepart = villeDepart;
		this.villeArrivee = villeArrivee;
		this.nbTroncons = nbTroncons;
	}

	/**
	 * Factory de la Route, vérifie que les paramètres soit valide et créé la route sinon retourne null
	 *
	 * @param villeDepart
	 * 		ville de départ
	 * @param villeArrivee
	 * 		ville d'arrivé
	 * @param nombreTroncons
	 * 		nombres de segment sur la route
	 * @return La Route créé ou null en fonction de la validité des paramètres
	 */
	public static Route creerRoute(Ville villeDepart, Ville villeArrivee, int nombreTroncons)
	{
		if (nombreTroncons < 0 || nombreTroncons > 10)
			return null;
		if (villeDepart == null || villeArrivee == null)
			return null;
		if (villeDepart.equals(villeArrivee))
			return null;

		return new Route(villeDepart, villeArrivee, nombreTroncons);
	}



	public int getTroncons()
	{
		return this.nbTroncons;
	}

	public void setTroncons(int troncons)
	{
		this.nbTroncons = troncons;
	}

	public Ville getDepart()
	{
		return this.villeDepart;
	}

	public void setDepart(Ville depart)
	{
		this.villeDepart = depart;
	}

	public Ville getArrivee()
	{
		return this.villeArrivee;
	}

	public void setArrivee(Ville arrivee)
	{
		this.villeArrivee = arrivee;
	}

	public String toString()
	{
		return "Route{" + "nbTroncons=" + this.nbTroncons + ", villeDepart=" + this.villeDepart + ", villeArrivee=" + this.villeArrivee + "}";
	}

}
