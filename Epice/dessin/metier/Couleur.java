package dessin.metier;

import java.awt.*;

/**
 *
 * @author Groupe 4 SAE2.01 : Guelle Clément , Cauvin Pierre , Montagne Aubin , Delpech Nicolas
 * @version 1.0
 */


public enum Couleur
{
	JAUNE(255,255,255),
	GRIS(255,255,255),
	OR(255,255,255),
	OCRE(255,255,255),
	ORANGE(255,255,255),
	BLEU_CLAIR(255,255,255),
	ROSE(255,255,255),
	VERT(255,255,255);

	private int r, v, b ;

	private Couleur(int r,int v,int b)
	{
		this.r = r ;
		this.v = v ;
		this.b = b ;
	}

	public Color getColor()
	{
		return new Color(this.r,this.v,this.b);
	}

	public String getSymbole()
	{
		return this.name();
	}

	public static int getNbCouleur()
	{
		return values().length;
	}

	public static Couleur valueOf(int ordinal)
	{
		return values()[ordinal];
	}


}
