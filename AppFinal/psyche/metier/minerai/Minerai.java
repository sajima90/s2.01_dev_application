package psyche.metier.minerai;


/**
 *
 * @author Groupe 4 SAE2.01 : Guelle Clément, Cauvin Pierre, Montagne Aubin, Delpech Nicolas
 * 							  Bouquet Jules, Rougeolle Henri, Yachir Yanis
 */


public enum Minerai implements IRessource
{

	/*--------------*/
	/*  Enums       */
	/*--------------*/

	ALUMINIUM("Al"),
	ARGENT   ("Ag"),
	OR       ("Au"),
	COBALT   ("Co"),
	FER      ("Fe"),
	NICKEL   ("Ni"),
	PLATINE  ("Pt"),
	TITANE   ("Ti");


	/*--------------*/
	/*  Données     */
	/*--------------*/

	private final String  symbole;



	/*--------------*/
	/* Instructions */
	/*--------------*/

	/**
	 * Constructeur de Minerai
	 *
	 * @param symbole
	 * 		Symbole du minerai (Fe, Al, Au...)
	 */

	private Minerai(String symbole)
	{
		this.symbole = symbole;
	}


	/*--------------*/
	/*     Get      */
	/*--------------*/

	/**
	 * Retourne le symbole associé à cet objet.
	 *
	 * @return String, le symbole sous forme de chaîne de caractères.
	 */
	public String  getSymbole() { return this.symbole;	}


	/*-----------------*/
	/* Autres méthodes */
	/*-----------------*/

	/**
	 * Retourne une représentation sous forme de chaîne de caractères de Minerai.
	 *
	 * @return une chaîne de caractères représentant le Minerai.
	 */
	public String toString() { return this.name() + " (" + this.getSymbole() + ")"; }

	public String getImage() { return "./theme/images/" + this.name().toLowerCase() + ".png"; }

}

