package psyche.metier;

import psyche.metier.minerai.Plateau;
import psyche.metier.map.Mine;

import java.util.ArrayList;

public class Joueur
{

	/*--------------*/
	/*  Données     */
	/*--------------*/
	private ArrayList<Mine> minesObtenues;
	private Plateau         plateauJoueur;

	private int             nbPoints;
	private int				nbJetons;
	private String			nom		;


	/*--------------*/
	/*  Méthodes    */
	/*--------------*/

	/**
	 * Constructeur de Joueur
	 *
	 * @param plateau
	 * 		Le plateau du joueur
	 */
	public Joueur(Plateau plateau)
	{
		this.plateauJoueur 	= plateau;
		this.nbPoints      	= 0;
		this.nbJetons		= 25;
		this.minesObtenues 	= new ArrayList<Mine>();
		this.nom			= null;
	}

	/*----------*/
	/*   Get    */
	/*----------*/


	public int             getNbPoints     ()	{	return this.nbPoints;		}
	public int 			   getNbJetons	   ()	{	return this.nbJetons;		}
	public ArrayList<Mine> getMinesObtenues()	{	return this.minesObtenues;	}

	/*-------------------------*/
	/*   Setters, variateurs   */
	/*-------------------------*/

	/**
	 * Ajoute une mine à la liste des mines obtenues du joueur
	 *
	 * @param mine
	 * 		La mine à ajouter
	 */
	public void ajouterMine( Mine mine ) 		{	this.minesObtenues.add(mine); }

	/**
	 * Fait varier le nombre de points du joueur.
	 *
	 * @param nbPointsVariation
	 * 		Le nombre de points à faire varier.
	 * 		Prend les valeurs positives et négatives.
	 */
	public void varierPoint( int nbPointsVariation ) {	this.nbPoints += nbPointsVariation;	}
	public void varierJeton( int nbJetonsUtiliser  ) {	this.nbJetons -= nbJetonsUtiliser;	}
	public void setNom	   ( String nom			   ) {	this.nom = nom;						}


	/*---------------------*/
	/*   Autres méthodes   */
	/*---------------------*/

	/**
	 * Renvoie un affichage contenant les informations du joueur.
	 *
	 */
	public String toString()
	{
		String sRet = 	"Joueur" + this.nom + "\n"+
					  	"--------------------\n"  +
				      	"nbPoints : " + this.nbPoints + "\n"+
						"nbJetons : " + this.nbJetons + "\n";

		//Mines obtenues
		if ( this.minesObtenues.isEmpty() )	sRet += "Aucune mine obtenue.";
		else sRet += "Mines obtenues :\n" + this.minesObtenues.toString() + "\n";

		//Plateau
		sRet += "Etat du plateau :\n" + this.plateauJoueur.toString() + "\n";

		return sRet;
	}
}
