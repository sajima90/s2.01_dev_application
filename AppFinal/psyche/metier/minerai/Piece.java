package psyche.metier.minerai;

/**
 *
 * @author Groupe 4 SAE2.01 : Guelle Clément, Cauvin Pierre, Montagne Aubin, Delpech Nicolas
 * 							  Bouquet Jules, Rougeolle Henri, Yachir Yanis
 *
 * 	Cette enum est créé au cas où dans le futur le jeu évolue et d'autres monnaies arrivent
 */


public enum Piece implements IRessource
{

	/*--------------*/
	/*  Enums       */
	/*--------------*/

	NR(1);



	/*--------------*/
	/*  Données     */
	/*--------------*/

	private int valeur;



	/*--------------*/
	/* Instructions */
	/*--------------*/

	/**
	 * Constructeur de Minerai
	 *
	 * @param valeur
	 * 		Valeur de la pièce
	 */
	private Piece (int valeur)
	{
		this.valeur = valeur;
	}



	/*--------------*/
	/*     Get      */
	/*--------------*/

	/**
	 * Retourne un entier de la Valeur.
	 *
	 * @return un entier de Valeur.
	 */
	public int getValeur() { return this.valeur; }




	/*-----------------*/
	/* Autres méthodes */
	/*-----------------*/

	/**
	 * Retourne une représentation sous forme de chaîne de caractères de Piece.
	 *
	 * @return une chaîne de caractères représentant la Piece.
	 */
	public String toString() { return this.name() + " (" + this.valeur; }
}
