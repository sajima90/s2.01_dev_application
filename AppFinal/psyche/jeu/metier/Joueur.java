package psyche.jeu.metier;

import psyche.jeu.vue.PanelJoueur;

import java.util.ArrayList;

public class Joueur
{

	/*--------------*/
	/*  Données     */
	/*--------------*/

	private ArrayList<Mine>    minesObtenues;
	private ArrayList<Minerai> mineraisObtenues;

	private int             nbPoints;
	private int				nbJetons;
	private String			nom		;

	private PanelJoueur     attribJoueur;

	/*--------------*/
	/*  Méthodes    */
	/*--------------*/

	/**
	 * Constructeur de Joueur
	 *
	 *
	 *
	 */
	public Joueur(String nom)
	{

		this.nbPoints      	= 0;
		this.nbJetons		= 25;
		this.nom			= nom;
		this.minesObtenues 	    = new ArrayList<Mine>   ();
		this.mineraisObtenues 	= new ArrayList<Minerai>();
		this.attribJoueur = null;
	}

	/*----------*/
	/*   Get    */
	/*----------*/


	public int                getNbPoints        ()	{ return this.nbPoints;		   }
	public int 			      getNbJetons	     ()	{ return this.nbJetons;		   }
	public ArrayList<Mine>    getMinesObtenues   ()	{ return this.minesObtenues;   }
	public ArrayList<Minerai> getMineraisObtenues() { return this.mineraisObtenues;}
	public String             getNom			 () { return this.nom;             }

	/*-------------------------*/
	/*   Setters, variateurs   */
	/*-------------------------*/

	/**
	 * Ajoute une mine à la liste des mines obtenues du joueur
	 *
	 * @param mine
	 * 		La mine à ajouter
	 */
	public void ajouterMine    ( Mine mine ) 	 {this.minesObtenues.add(mine);      }
	public void ajouterMinerais(Minerai minerai) {this.mineraisObtenues.add(minerai);}


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

		return sRet;
	}
}