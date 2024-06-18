ccccccccccccccccccccccccccccccccccccccccccccccccccccccpackage dessin.metier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * La classe Plateau gère le score ,et la répresentation métier du plateau comportant les jetons
 * @author Groupe 4 SAE2.01 : Guelle Clément , Cauvin Pierre , Montagne Aubin , Delpech Nicolas
 * @version 1.0
 */


public class Plateau
{
	/*-------------*/
	/* Donnees     */
	/*-------------*/

	// constante :

	// Nombre de case total pour les jetons
	private static final int NB_PIECE_MAX = 32;


	// variables  :

	private List<Jeton> tabJetonPresent;
	private int nbPiece;

	private int score;

	private String detailScore;

	/*-------------*/
	/*Instructions */
	/*-------------*/


	/**
	 * Création initiale du plateau
	 */
	public Plateau()
	{
		this.tabJetonPresent = new ArrayList<>();
		this.nbPiece         = 0;
		this.score           = 0;
		this.detailScore     = "";
	}



	public int getScore(){return this.score;}

	public String getDetailScore(){ return this.detailScore;}

	/**
	 * La méthode ajoute la ressource au plateau, tout en vérifiant si elle peut y être ajouté ou non.
	 * @param ressource qui est soit une épice, soit une piece
	 * @return un boolean pour savoir si la ressource peut être ajouté
	 */
	public boolean ajouterRessource(Jeton ressource)
	{
		int valPiece = 0;

		if(ressource.getType() instanceof Epice)
		{

			if (this.compteurJetons(this.tabJetonPresent, ressource) == 0)
			{
				if (this.getNbEpicesDifferentPlateau() < 5)
				{
					this.tabJetonPresent.add(ressource);
					this.tabJetonPresent = this.trieTab(this.tabJetonPresent);

					return true;
				}
			}
			else
			{

				if (this.compteurJetons(this.tabJetonPresent, ressource) < 3)
				{
					this.tabJetonPresent.add(ressource);



					this.tabJetonPresent = this.trieTab(this.tabJetonPresent);


					return true;
				}
			}
		}

		else
		{
			if (ressource.getType() instanceof Piece)
			{
				Piece piece = (Piece) ressource.getType();

				switch(piece)
				{
				case BRONZE -> {valPiece = 1;}
				case ARGENT -> {valPiece = 2;}
				case OR     -> {valPiece = 5;}
				}

				if (nbPiece + valPiece <= 8)
				{
					this.nbPiece += valPiece;
					return true;
				}
			}
		}

		return false;
	}


	private int compteurJetons(List<Jeton> listeJeton, Jeton jetonRechercher)
	{

		int compteurDoublons = 0;
		for (Jeton jeton : listeJeton)
		{
			if ( jeton != null )
			{
				if (jeton.getType().equals(jetonRechercher.getType()))
				{
					compteurDoublons++;
				}
			}
		}
		return compteurDoublons;
	}

	private int getNbEpicesDifferentPlateau ()
	{
		int nbEpicesDifferent = 0;
		List<Epice> listeEpices = new ArrayList<>();
		for (Jeton jeton : this.tabJetonPresent)
		{
			if ( jeton != null)
			{
				Epice epice = (Epice) jeton.getType();
				if (!listeEpices.contains(epice))
				{
					listeEpices.add(epice);
					nbEpicesDifferent++;
				}
			}

		}
		return nbEpicesDifferent;
	}




	public void score()
	{

		// Un 0 est ajouté pour prendre en compte le fait qu'il n'y est pas de pièce
		int[] tableauScoresPieces  = { 0, 0, 4, 9, 16, 25, 36, 49, 64 };
		int[] tableauScoresligne   = { 0, 0, 2, 5, 9, 14 };
		int[] tableauScoresColonne = { 0, 0, 2, 10 };

		int scorePiece;
		int scoreEpice;

		int scoreLig1;
		int scoreLig2;
		int scoreLig3;

		int scoreCol1;
		int scoreCol2;
		int scoreCol3;
		int scoreCol4;
		int scoreCol5;


		List<String>  tabTestJetonPresent     = new ArrayList<>();
		List<Integer> tabNbElementJeton       = new ArrayList<>();

		List<String> tabCompteLigneEtage1   = new ArrayList<>();
		List<String> tabCompteLigneEtage2   = new ArrayList<>();
		List<String> tabCompteLigneEtage3   = new ArrayList<>();

		//------------Calcul Pièce------------//

		scorePiece = tableauScoresPieces[this.nbPiece];



		// ------------Calcul Epice-----------//

		scoreCol1=0;
		scoreCol2=0;
		scoreCol3=0;
		scoreCol4=0;
		scoreCol5=0;

		// Le premier élement est mis à null pour de l'esthetique au niveau des comptes plus bas
		Integer[] scoreCol = {null, scoreCol1, scoreCol2, scoreCol3, scoreCol4, scoreCol5 };


		// Calcul des points gagné grâce aux colonnes

		for (int nbElement = 3; nbElement > 0; nbElement--)
		{
			for (Jeton jeton : this.tabJetonPresent)
			{
				if (jeton != null                                                     &&
						this.compteurJetons(this.tabJetonPresent, jeton) == nbElement &&
						!tabTestJetonPresent.contains(((Epice) jeton.getType()).getLibCourt()))
				{
					tabTestJetonPresent.add(((Epice) jeton.getType()).getLibCourt());
					tabNbElementJeton  .add(nbElement);
				}
			}
		}


		for ( int cpt = 0; cpt < tabNbElementJeton.size(); cpt++)
		{
			scoreCol[cpt+1] += tableauScoresColonne[tabNbElementJeton.get(cpt)];
		}



		// Calcul des points gagné grâce aux lignes

		for (Jeton jeton : this.tabJetonPresent)
		{
			if (jeton != null)
			{
				if (tabCompteLigneEtage1.contains(((Epice) jeton.getType()).getLibCourt()))
				{
					if (tabCompteLigneEtage2.contains(((Epice) jeton.getType()).getLibCourt()))
					{
						tabCompteLigneEtage3.add(((Epice) jeton.getType()).getLibCourt());
					}
					else
						tabCompteLigneEtage2.add(((Epice) jeton.getType()).getLibCourt());
				}
				else
					tabCompteLigneEtage1.add(((Epice) jeton.getType()).getLibCourt());
			}
		}

		scoreLig1 = tableauScoresligne[tabCompteLigneEtage1.size()];
		scoreLig2 = tableauScoresligne[tabCompteLigneEtage2.size()];
		scoreLig3 = tableauScoresligne[tabCompteLigneEtage3.size()];

		scoreEpice = scoreLig1 + scoreLig2 + scoreLig3 + scoreCol[1] + scoreCol[2] + scoreCol[3] + scoreCol[4] + scoreCol[5];

		this.detailScore += "Detail : \n" +
		                       "\tPièces        : " + String.format("%2d", scorePiece )  + " pt\n" +
		                       "\tColonne 1     : " + String.format("%2d", scoreCol[1])  + " pt\n" +
		                       "\tColonne 2     : " + String.format("%2d", scoreCol[2])  + " pt\n" +
		                       "\tColonne 3     : " + String.format("%2d", scoreCol[3])  + " pt\n" +
		                       "\tColonne 4     : " + String.format("%2d", scoreCol[4])  + " pt\n" +
		                       "\tColonne 5     : " + String.format("%2d", scoreCol[5])  + " pt\n" +
		                       "\tLigne 1       : " + String.format("%2d", scoreLig1  )  + " pt\n" +
		                       "\tLigne 2       : " + String.format("%2d", scoreLig2  )  + " pt\n" +
		                       "\tLigne 3       : " + String.format("%2d", scoreLig3  )  + " pt\n";

		this.score = scorePiece + scoreEpice;
	}





	public List<Jeton> trieTab(List<Jeton> listeJeton)
	{
		// Liste finale trié
		List<Jeton>   tabJetonPresentTriee     = new ArrayList<>();

		// Liste des Epice dans l'ordre de la plus grande à la plus petite
		List<Jeton>   tabJetonOrdreDecroissant = new ArrayList<>();

		// Liste des jetons déjà vérifier utilile pour ne pas refaire plusieurs fois les mêmes épices
		List<String>  tabTestJetonPresent      = new ArrayList<>();

		// liste qui compléte tabJetonOrdreDecroissant pour savoir combien de fois l'épice y est
		List<Integer> tabNbElementJeton        = new ArrayList<>();

		int nbElementDansTabElement = 0;
		int nbElementNull           = 0;


		// Rangement dans l'ordre Decroissant des épices dans tabJetonOrdreDecroissant.
		// Ajout du nombre de fois pour laquel l'épice est présente dans tabNbElementJeton
		// Ajout des épices déjà vu dans tabTestJetonPresent Obligé car les épice sont différentes mais pas leur nom

		for (int nbElement = 3; nbElement > 0; nbElement--)
		{
			for(Jeton jeton : listeJeton)
			{
				if ( jeton != null                                          &&
						this.compteurJetons(listeJeton, jeton) == nbElement &&
						!tabTestJetonPresent.contains(((Epice) jeton.getType()).getLibCourt()) )
				{
					tabTestJetonPresent     .add(((Epice) jeton.getType()).getLibCourt());
					tabJetonOrdreDecroissant.add(jeton);
					tabNbElementJeton       .add(nbElement);
				}
			}
		}


		// Pour chaque épice trié les complété avec le nombre d'épice qu'il y a en tous, pis mettre des null si nécéssaire
		for (int cpt=0; cpt < tabJetonOrdreDecroissant.size(); cpt ++)
		{
			nbElementDansTabElement = tabNbElementJeton.get(cpt);
			nbElementNull           = 3 - nbElementDansTabElement;

			while ( nbElementDansTabElement > 0)
			{
				tabJetonPresentTriee.add(tabJetonOrdreDecroissant.get(cpt));

				nbElementDansTabElement --;
			}

			while (nbElementNull > 0)
			{
				tabJetonPresentTriee.add(null);

				nbElementNull--;
			}

		}

		return tabJetonPresentTriee;
	}


	public List<Jeton> getListJeton()
	{
		return this.tabJetonPresent;
	}

	public int getNbPiece()
	{
		return this.nbPiece;
	}

	/**
	 * Affichage du plateau
	 * @return L'affichage du plateau ainsi que le nombre de piece.
	 */
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for (int cptLig = 2; cptLig >= 0; cptLig--)
		{
			sb.append("+-----+-----+-----+-----+-----+\n|");
			for (int cptCol = 0; cptCol < 5; cptCol++)
			{
				int index = cptCol * 3 + cptLig;
				if (index < tabJetonPresent.size() && tabJetonPresent.get(index) != null)
				{
					if (tabJetonPresent.get(index).getType() instanceof Epice)
					{
						sb.append(" ").append(((Epice) tabJetonPresent.get(index).getType()).getLibCourt()).append(" |");
					} else
						if (tabJetonPresent.get(index).getType() instanceof Piece)
					{
						sb.append(" ").append(((Piece) tabJetonPresent.get(index).getType()).getValeur()).append(" |");
					}
				}
				else
				{
					sb.append("     |");
				}
			}
			sb.append("\n");
		}
		sb.append("+-----+-----+-----+-----+-----+\n").append(nbPiece).append(" pièces\n");
		return sb.toString();
	}

}