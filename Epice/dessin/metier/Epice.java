package dessin.metier;

/**
 *
 * @author Groupe 4 SAE2.01 : Guelle Clément , Cauvin Pierre , Montagne Aubin , Delpech Nicolas
 * @version 1.0
 */


public enum Epice implements IRessource
{
	ALUMINIUM(Couleur.JAUNE),
	ARGENT(Couleur.GRIS),
	OR(Couleur.OR),
	COBALT(Couleur.OCRE),
	FER(Couleur.ORANGE),
	NICKEL(Couleur.BLEU_CLAIR),
	PLATINE(Couleur.ROSE),
	TITANE(Couleur.VERT);

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

	public String getSymbole()
	{
		switch (this.name())
		{
		case "ALUMINIUM":
			return "Al";
		case "ARGENT":
			return "Ag";
		}
		return "";
	}
}
