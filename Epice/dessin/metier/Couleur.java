package dessin.metier;

import java.awt.*;

/**
 *
 * @author Groupe 4 SAE2.01 : Guelle Clément , Cauvin Pierre , Montagne Aubin , Delpech Nicolas
 * @version 1.0
 */


public enum Couleur
{
	BLANC(255,255,255),
	BLEU(0,0,255),
	VERT(0,255,0),
	ROUGE(255,0,0),
	ORANGE(255,165,0),
	VIOLET(128,0,128),
	NOIR(0,0,0),
	JAUNE(255,255,0),
	BEIGE(245,245,220),
	MARRON(139,69,19);


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
