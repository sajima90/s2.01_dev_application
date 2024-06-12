package psyche.metier.minerai;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Groupe 4 SAE2.01 : Guelle Clément, Cauvin Pierre, Montagne Aubin, Delpech Nicolas
 * 							  Bouquet Jules, Rougeolle Henri, Yachir Yanis
 */

/**
 * La classe Pioche représente une collection de jetons d'où les jetons peuvent être tirés.
 * Elle permet d'initialiser une liste de jetons avec des types prédéfinis et de tirer des jetons un par un.
 */
public class Pioche
{
	private List<Jeton> lstJetons;


	/**
	 * Constructeur de la classe Pioche.
	 * Initialise une nouvelle pioche avec une liste de jetons et les remplit en appelant la méthode initPioche.
	 */
	public Pioche()
	{
		this.lstJetons = new ArrayList<Jeton>();
		this.initPioche();
	}


	/**
	 * Tire un jeton de la pioche.
	 * Si la pioche n'est pas vide, le dernier jeton de la liste est retiré et retourné.
	 * Si la pioche est vide, retourne null.
	 *
	 * @return le jeton tiré, ou null si la pioche est vide.
	 */
	public Jeton tirerJeton()
	{
		if(!this.lstJetons.isEmpty())
		{
			return lstJetons.remove(lstJetons.size() - 1);
		}
		return null;
	}


	/**
	 * Initialise la pioche avec une liste prédéfinie de jetons.
	 * Ajoute plusieurs jetons de type Minerai.ALUMINIUM et deux jetons de type Piece.NR à la liste des jetons.
	 */
	private void initPioche()
	{
		this.lstJetons.add(new Jeton(Minerai.ALUMINIUM ));
		this.lstJetons.add(new Jeton(Minerai.ALUMINIUM ));
		this.lstJetons.add(new Jeton(Minerai.ALUMINIUM ));
		this.lstJetons.add(new Jeton(Minerai.ALUMINIUM ));

		this.lstJetons.add(new Jeton(Minerai.ARGENT    ));
		this.lstJetons.add(new Jeton(Minerai.ARGENT    ));
		this.lstJetons.add(new Jeton(Minerai.ARGENT    ));
		this.lstJetons.add(new Jeton(Minerai.ARGENT    ));

		this.lstJetons.add(new Jeton(Minerai.OR        ));
		this.lstJetons.add(new Jeton(Minerai.OR        ));
		this.lstJetons.add(new Jeton(Minerai.OR        ));
		this.lstJetons.add(new Jeton(Minerai.OR        ));

		this.lstJetons.add(new Jeton(Minerai.COBALT    ));
		this.lstJetons.add(new Jeton(Minerai.COBALT    ));
		this.lstJetons.add(new Jeton(Minerai.COBALT    ));
		this.lstJetons.add(new Jeton(Minerai.COBALT    ));

		this.lstJetons.add(new Jeton(Minerai.FER       ));
		this.lstJetons.add(new Jeton(Minerai.FER       ));
		this.lstJetons.add(new Jeton(Minerai.FER       ));
		this.lstJetons.add(new Jeton(Minerai.FER       ));

		this.lstJetons.add(new Jeton(Minerai.NICKEL    ));
		this.lstJetons.add(new Jeton(Minerai.NICKEL    ));
		this.lstJetons.add(new Jeton(Minerai.NICKEL    ));
		this.lstJetons.add(new Jeton(Minerai.NICKEL    ));

		this.lstJetons.add(new Jeton(Minerai.PLATINE   ));
		this.lstJetons.add(new Jeton(Minerai.PLATINE   ));
		this.lstJetons.add(new Jeton(Minerai.PLATINE   ));
		this.lstJetons.add(new Jeton(Minerai.PLATINE   ));

		this.lstJetons.add(new Jeton(Minerai.TITANE    ));
		this.lstJetons.add(new Jeton(Minerai.TITANE    ));
		this.lstJetons.add(new Jeton(Minerai.TITANE    ));
		this.lstJetons.add(new Jeton(Minerai.TITANE    ));


		this.lstJetons.add(new Jeton(Piece.NR          ));
		this.lstJetons.add(new Jeton(Piece.NR          ));
		this.lstJetons.add(new Jeton(Piece.NR          ));
		this.lstJetons.add(new Jeton(Piece.NR          ));
		this.lstJetons.add(new Jeton(Piece.NR          ));
		this.lstJetons.add(new Jeton(Piece.NR          ));
		this.lstJetons.add(new Jeton(Piece.NR          ));
		this.lstJetons.add(new Jeton(Piece.NR          ));

	}
}
