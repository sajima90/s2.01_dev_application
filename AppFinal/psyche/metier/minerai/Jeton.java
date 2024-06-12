package psyche.metier.minerai;

/**
 *
 * @author Groupe 4 SAE2.01 : Guelle Clément, Cauvin Pierre, Montagne Aubin, Delpech Nicolas
 * 							  Bouquet Jules, Rougeolle Henri, Yachir Yanis
 */


public class Jeton
{

	/*--------------*/
	/*  Données     */
	/*--------------*/
	private IRessource type;


	/*--------------*/
	/* Instructions */
	/*--------------*/

	/**
	 * Constructeur du Jeton
	 * @param type type est un jeton qui peut soit être une pièce, soit un minerai
	 */
	public Jeton(IRessource type)
	{
		this.type = type;
	}



	/*--------------*/
	/*      Get     */
	/*--------------*/

	/**
	 * Retourne le type de ce jeton.
	 *
	 * @return le type de jeton (pièce ou minerai).
	 */
	public IRessource getType () { return type; }


	/*-----------------*/
	/* Autres Méthodes */
	/*-----------------*/

	/**
	 * Retourne une représentation sous forme de chaîne de caractères du jeton.
	 *
	 * @return une chaîne de caractères représentant le jeton.
	 */
	public String toString() { return "Jeton {  type = " + type.toString() + " }"; }


}
