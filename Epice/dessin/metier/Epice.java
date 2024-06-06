package dessin.metier;

/**
 *
 * @author Groupe 4 SAE2.01 : Guelle Clément , Cauvin Pierre , Montagne Aubin , Delpech Nicolas
 * @version 1.0
 */


public enum Epice implements IRessource
{
	SESAME(Couleur.BLANC),
	CURCUMA(Couleur.JAUNE),
	PAPRIKA(Couleur.ROUGE),
	SAFRAN(Couleur.ORANGE),
	SUMAC(Couleur.VERT),
	CANNELLE(Couleur.MARRON),
	CARDAMONE(Couleur.VIOLET),
	POIVRE(Couleur.NOIR);

	private final Couleur couleur;


	private Epice(Couleur coul)
	{
		this.couleur = coul;
	}

	public String getLibCourt()
	{
		return this.name().substring(0, 3);
	}

	public Couleur getCouleur()
	{
		return this.couleur;
	}

	public String toString()
	{
		return this.name() + " (" + this.couleur.getSymbole() + ")";
	}


}

