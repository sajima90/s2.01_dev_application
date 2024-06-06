package dessin.metier;

/**
 *
 * @author Groupe 4 SAE2.01 : Guelle Clément , Cauvin Pierre , Montagne Aubin , Delpech Nicolas
 * @version 1.0
 */


public enum Piece implements IRessource
{
	BRONZE(1),
	ARGENT(2),
		OR(5);

	private int valeur;

	private Piece (int valeur)
	{
		this.valeur = valeur;
	}

	public int getValeur(){return this.valeur;}

	public String toString()
	{
		return this.name() + " (" + this.valeur + " points (TO STRING PIECE))";
	}
}
