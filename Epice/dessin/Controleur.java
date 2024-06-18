package dessin;


import dessin.metier.Plateau;
import dessin.metier.Pioche;

import dessin.ihm.Frame;
import dessin.metier.Epice;
import dessin.metier.Jeton;
import dessin.metier.Piece;


public class Controleur
{
	private Frame   ihm;
	private Pioche  pioche;
	private Plateau metier;

	public Controleur()
	{
		this.metier = new Plateau();
		this.pioche = new Pioche();

		Jeton jeton;
		while ((jeton = pioche.tirerJeton()) != null)
		{
			metier.ajouterRessource(jeton);
		}

		this.ihm    = new Frame(this);


	}

	public String getImageFond()
	{
		return "../images/plateau.png";
	}

	public String getPiece()
	{
		return "../images/bronze.png";
	}

	public String getEpice(int indice)
	{
		
		if (          metier.getListJeton().get(indice) != null && 
		              metier.getListJeton().size() > 1          && 
		     ((Epice) metier.getListJeton().get(indice).getType()).name() != null )
		{
			switch (((Epice) metier.getListJeton().get(indice).getType()).name())
			{
			case "ALUMINIUM":
				return "../images/sesame.png";
			case "ARGENT":
				return "../images/curcuma.png";
			case "OR":
				return "../images/paprika.png";
			case "COBALT":
				return "../images/safran.png";
			case "FER":
				return "../images/sumac.png";
			case "":
				return "../images/cannelle.png";
			case "CARDAMONE":
				return "../images/cardamone.png";
			case "POIVRE":
				return "../images/poivre.png";
			}
		}

		return "";
	}

	public int getNbPiece()
	{
		return metier.getNbPiece();
	}


	public static void main(String[] a)
	{
		new Controleur();
	}
}