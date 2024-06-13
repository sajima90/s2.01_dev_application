package psyche.map.metier;

import java.awt.*;

/**
 *
 * @author Groupe 4 SAE2.01 : Guelle Clément, Cauvin Pierre, Montagne Aubin, Delpech Nicolas
 * 							  Bouquet Jules, Rougeolle Henri, Yachir Yanis
 */


public enum Couleur
{

	/*--------------*/
	/*  Enums       */
	/*--------------*/

	JAUNE     	(255, 255,   0),
	BLEU_CLAIR	(173, 216, 230),
	GRIS      	(128, 128, 128),
	VERT      	( 34, 139,  34),
	ROSE      	(255, 192, 203),
	OCRE      	(205, 133,  63),
	ROME      	(  0,   0, 255);



	/*--------------*/
	/*  Données     */
	/*--------------*/


	private int r, v, b ;


	/*--------------*/
	/* Instructions */
	/*--------------*/

	/**
	 * Constructeur de la Couleur
	 * @param r entier de la couleur rouge
	 * @param v entier de la couleur verte
	 * @param b entier de la couleur bleu
	 */
	private Couleur(int r,int v,int b)
	{
		this.r = r ;
		this.v = v ;
		this.b = b ;
	}



	/*--------------*/
	/*      Get     */
	/*--------------*/

	public Color      getColor    () { return new Color(this.r,this.v,this.b); }
	public String     getSymbole  () { return this.name();                     }
	public static int getNbCouleur() { return values   ().length;              }


	/*-----------------*/
	/* Autres Méthodes */
	/*-----------------*/

	public static Couleur valueOf(int ordinal) { return values()[ordinal]; }


}
