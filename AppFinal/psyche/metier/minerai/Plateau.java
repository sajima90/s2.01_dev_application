package psyche.metier.minerai;


import java.util.ArrayList;
import java.util.List;
import psyche.metier.Joueur;
/**
 * La classe Plateau gère le score, et la répresentation métier du plateau comportant les jetons
 * @author Groupe 4 SAE2.01 : Guelle Clément, Cauvin Pierre, Montagne Aubin, Delpech Nicolas
 * 							  Bouquet Jules, Rougeolle Henri, Yachir Yanis
 */


public class Plateau
{

	/*-------------*/
	/*   Données   */
	/*-------------*/

	// constantes :

	// Dimensions du tableau
	private static final int NB_COLONNES = 8;
	private static final int NB_LIGNES   = 4;

	// Nombre de cases totales pour les jetons
	private static final int NB_PIECE_MAX = 15;


	// variables  :
	private List<Jeton> tabJetonPresent;

	private Joueur[] pourScore;
	private int nbPiece;
	private int score;

	private String detailScore;


	// constantes
	private static final int[] TABLEAU_SCORES_PIECES  = { 0, 0, 4, 9, 16, 25, 36, 49, 64 };
	private static final int[] TABLEAU_SCORES_LIGNE   = { 0, 0, 2, 5, 9, 14, 20, 32, 46 } ;
	private static final int[] TABLEAU_SCORES_COLONNE = { 0, 0, 2, 10, 20 }               ;

	/*----------------*/
	/*  Instructions  */
	/*----------------*/


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



	/*--------------*/
	/*     Get      */
	/*--------------*/

	public int         getScore      () { return this.score;           }
	public String      getDetailScore() { return this.detailScore;     }
	public List<Jeton> getListJeton  () { return this.tabJetonPresent; }
	public int         getNbPiece    () { return this.nbPiece;         }


	/**
	 * Méthode permettant de savoir le nombre de minerais différents qu'il y a sur le plateau du joueur
	 * @return le nombre de pièces différentes
	 */
	private int getNbMineraisDifferentPlateau()
	{

		int           nbMineraisDifferent = 0;
		List<Minerai> listeMinerais       = new ArrayList<>();


		for (Jeton jeton : this.tabJetonPresent)
		{
			if ( jeton != null)
			{
				Minerai Minerai = (Minerai) jeton.getType();
				if (!listeMinerais.contains(Minerai))
				{
					listeMinerais.add(Minerai);
					nbMineraisDifferent++;
				}
			}

		}
		return nbMineraisDifferent;
	}




	/*-----------------*/
	/* Autres méthodes */
	/*-----------------*/


	/**
	 * La méthode ajoute la ressource au plateau, tout en vérifiant si elle peut y être ajouté ou non.
	 * @param ressource qui est soit une épice, soit une piece
	 * @return un boolean pour savoir si la ressource peut être ajouté
	 */
	public boolean ajouterRessource(Jeton ressource)
	{
		int valPiece = 0;


		if(ressource.getType() instanceof Minerai)
		{

			if (this.compteurJetons(ressource) == 0)
			{
				if (this.getNbMineraisDifferentPlateau() < Plateau.NB_COLONNES)
				{
					this.tabJetonPresent.add(ressource);
					this.tabJetonPresent = this.trieTab(this.tabJetonPresent);

					return true;
				}
			}
			else
			{
				if (this.compteurJetons(ressource) < Plateau.NB_LIGNES)
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
				valPiece = 1;

				if (nbPiece + valPiece <= Plateau.NB_COLONNES)
				{
					this.nbPiece += valPiece;
					return true;
				}
			}
		}

		return false;
	}



	/**
	 * Méthode permettant de compter combien de fois apparaît le jeton
	 * @param jetonRechercher
	 * @return le nombre de foi qu'apparait le jeton
	 */
	private int compteurJetons(Jeton jetonRechercher)
	{

		int compteurDoublons = 0;


		for (Jeton jeton : this.tabJetonPresent)
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

	public void score()
	{
		int scorePiece = calculerScorePiece();
		int scoreMinerai = calculerScoreMinerai();

		this.score = scorePiece + scoreMinerai;
		afficherDetailScore(scorePiece, scoreMinerai);
	}

	/*
	private int calculerScoreMinesPoss()
	{
		methode local aubin
	}
	*/

	// Calcul du score des pièces
	private int calculerScorePiece()
	{
		return TABLEAU_SCORES_PIECES[this.nbPiece];
	}

	// Calcul du score des minerais
	private int calculerScoreMinerai()
	{
		List<String> tabTestJetonPresent = new ArrayList<>();
		List<Integer> tabNbElementJeton = new ArrayList<>();

		List<String> tabCompteLigneEtage1 = new ArrayList<>();
		List<String> tabCompteLigneEtage2 = new ArrayList<>();
		List<String> tabCompteLigneEtage3 = new ArrayList<>();
		List<String> tabCompteLigneEtage4 = new ArrayList<>();

		int[] scoreCols = new int[9];// Utiliser un tableau pour les scores des colonnes

		// Calcul des points gagnés grâce aux colonnes
		calculerPointsColonnes(tabTestJetonPresent, tabNbElementJeton, scoreCols);

		// Calcul des points gagnés grâce aux lignes
		calculerPointsLignes(tabCompteLigneEtage1, tabCompteLigneEtage2, tabCompteLigneEtage3, tabCompteLigneEtage4);

		int scoreLig1 = TABLEAU_SCORES_LIGNE[tabCompteLigneEtage1.size()];
		int scoreLig2 = TABLEAU_SCORES_LIGNE[tabCompteLigneEtage2.size()];
		int scoreLig3 = TABLEAU_SCORES_LIGNE[tabCompteLigneEtage3.size()];
		int scoreLig4 = TABLEAU_SCORES_LIGNE[tabCompteLigneEtage4.size()];

		int scoreMinerai = scoreLig1 + scoreLig2 + scoreLig3 + scoreLig4 +
				scoreCols[1] + scoreCols[2] + scoreCols[3] + scoreCols[4] +
				scoreCols[5] + scoreCols[6] + scoreCols[7] + scoreCols[8];

		return scoreMinerai;
	}

	// Calcul des points des colonnes
	private void calculerPointsColonnes(List<String> tabTestJetonPresent, List<Integer> tabNbElementJeton, int[] scoreCols)
	{
		for (int nbElement = NB_LIGNES; nbElement > 0; nbElement--)
		{
			for (Jeton jeton : this.tabJetonPresent)
			{
				if (jeton != null && this.compteurJetons(jeton) == nbElement &&
						!tabTestJetonPresent.contains(((Minerai) jeton.getType()).getSymbole()))
				{
					tabTestJetonPresent.add(((Minerai) jeton.getType()).getSymbole());
					tabNbElementJeton.add(nbElement);
				}
			}
		}

		for (int cpt = 0; cpt < tabNbElementJeton.size(); cpt++){
			scoreCols[cpt + 1] += TABLEAU_SCORES_COLONNE[tabNbElementJeton.get(cpt)];
		}
	}

// Calcul des points des lignes
	private void calculerPointsLignes(List<String> tabCompteLigneEtage1, List<String> tabCompteLigneEtage2,
			List<String> tabCompteLigneEtage3, List<String> tabCompteLigneEtage4)
	{
		for (Jeton jeton : this.tabJetonPresent)
		{
			if (jeton != null)
			{
				String symbole = ((Minerai) jeton.getType()).getSymbole();
				if (tabCompteLigneEtage1.contains(symbole))
				{
					if (tabCompteLigneEtage2.contains(symbole))
					{
						if (tabCompteLigneEtage3.contains(symbole))
						{
							tabCompteLigneEtage4.add(symbole);
						}
						else
						{
							tabCompteLigneEtage3.add(symbole);
						}
					}
					else
					{
						tabCompteLigneEtage2.add(symbole);
					}
				}
				else
				{
					tabCompteLigneEtage1.add(symbole);
				}
			}
		}
	}

	// Affichage du détail du score
	private void afficherDetailScore(int scorePiece, int scoreMinerai)
	{
		this.detailScore = "Detail : \n" +
				"\tPièces        : " + String.format("%2d", scorePiece) + " pt\n" +
				"\tColonne 1     : " + String.format("%2d", scoreMinerai) + " pt\n" +
				"\tColonne 2     : " + String.format("%2d", scoreMinerai) + " pt\n" +
				"\tColonne 3     : " + String.format("%2d", scoreMinerai) + " pt\n" +
				"\tColonne 4     : " + String.format("%2d", scoreMinerai) + " pt\n" +
				"\tColonne 5     : " + String.format("%2d", scoreMinerai) + " pt\n" +
				"\tColonne 6     : " + String.format("%2d", scoreMinerai) + " pt\n" +
				"\tColonne 7     : " + String.format("%2d", scoreMinerai) + " pt\n" +
				"\tColonne 8     : " + String.format("%2d", scoreMinerai) + " pt\n" +
				"\tLigne 1       : " + String.format("%2d", scorePiece) + " pt\n" +
				"\tLigne 2       : " + String.format("%2d", scorePiece) + " pt\n" +
				"\tLigne 3       : " + String.format("%2d", scorePiece) + " pt\n" +
				"\tLigne 4       : " + String.format("%2d", scorePiece) + " pt\n";
	}


	/**
	 * Méthode permettant le calcul total du score sur le plateau du joueur
	 */
	// simplifier les calculs via d'autres méthodes
	/*
	public void score()
	{

		// Un 0 est ajouté pour prendre en compte le fait qu'il n'y ait pas de pièce
		int[] tableauScoresPieces  = { 0, 0, 4, 9, 16, 25, 36, 49, 64 };
		int[] tableauScoresligne   = { 0, 0, 2, 5,  9, 14, 20, 32, 46 };
		int[] tableauScoresColonne = { 0, 0, 2,10, 20                 };

		int scorePiece;
		int scoreMinerai;

		int scoreLig1;
		int scoreLig2;
		int scoreLig3;
		int scoreLig4;

		int scoreCol1;
		int scoreCol2;
		int scoreCol3;
		int scoreCol4;
		int scoreCol5;
		int scoreCol6;
		int scoreCol7;
		int scoreCol8;


		List<String>  tabTestJetonPresent = new ArrayList<>();
		List<Integer> tabNbElementJeton   = new ArrayList<>();

		List<String> tabCompteLigneEtage1 = new ArrayList<>();
		List<String> tabCompteLigneEtage2 = new ArrayList<>();
		List<String> tabCompteLigneEtage3 = new ArrayList<>();
		List<String> tabCompteLigneEtage4 = new ArrayList<>();


		//------------Calcul Pièce------------//

		scorePiece = tableauScoresPieces[this.nbPiece];



		// ------------Calcul Minerai-----------//

		scoreCol1 = 0;
		scoreCol2 = 0;
		scoreCol3 = 0;
		scoreCol4 = 0;
		scoreCol5 = 0;
		scoreCol6 = 0;
		scoreCol7 = 0;
		scoreCol8 = 0;

		// Le premier élement est mis à null pour de l'esthetique au niveau des comptes plus bas
		// L'élément dans le tableau à l'emplacement 1 aura comme cpt 1
		Integer[] scoreCol = { null, scoreCol1, scoreCol2, scoreCol3, scoreCol4, scoreCol5,scoreCol6, scoreCol7, scoreCol8 };


		// Calcul des points gagné grâce aux colonnes

		for (int nbElement = NB_LIGNES; nbElement > 0; nbElement--)
		{
			for (Jeton jeton : this.tabJetonPresent)
			{
				if (jeton != null                               &&
					this.compteurJetons(jeton) == nbElement     &&
					!tabTestJetonPresent.contains(((Minerai) jeton.getType()).getSymbole()))
				{
					tabTestJetonPresent.add(((Minerai) jeton.getType()).getSymbole());
					tabNbElementJeton  .add(nbElement);
				}
			}
		}

		for ( int cpt = 0; cpt < tabNbElementJeton.size(); cpt++)
		{
			scoreCol[cpt+1] += tableauScoresColonne[tabNbElementJeton.get(cpt)];
		}

		// Calcul des points gagné grâce aux lignes
		/* On regarde pour chaque ligne si le minerai est dedans et si oui, on regarde la ligne du dessus
		   sinon on l'ajoute à l'arrayList. Le but est de savoir combien il y a d'élément sur la ligne */
		/*for (Jeton jeton : this.tabJetonPresent)
		{
			if (jeton != null)
			{
				if (tabCompteLigneEtage1.contains(((Minerai) jeton.getType()).getSymbole()))
				{
					if (tabCompteLigneEtage2.contains(((Minerai) jeton.getType()).getSymbole()))
					{
						if (tabCompteLigneEtage3.contains(((Minerai) jeton.getType()).getSymbole()))
						{
							tabCompteLigneEtage4.add(((Minerai) jeton.getType()).getSymbole());
						}
						else
							tabCompteLigneEtage3.add(((Minerai) jeton.getType()).getSymbole());
					}
					else
						tabCompteLigneEtage2.add(((Minerai) jeton.getType()).getSymbole());
				}
				else
					tabCompteLigneEtage1.add(((Minerai) jeton.getType()).getSymbole());
			}
		}

		scoreLig1 = tableauScoresligne[tabCompteLigneEtage1.size()];
		scoreLig2 = tableauScoresligne[tabCompteLigneEtage2.size()];
		scoreLig3 = tableauScoresligne[tabCompteLigneEtage3.size()];
		scoreLig4 = tableauScoresligne[tabCompteLigneEtage4.size()];


		scoreMinerai = scoreLig1   + scoreLig2   + scoreLig3   + scoreLig4   + scoreCol[1] + scoreCol[2] +
					   scoreCol[3] + scoreCol[4] + scoreCol[5] + scoreCol[6] + scoreCol[7] + scoreCol[8];


		// Affichage du score
		this.detailScore += "Detail : \n" +
		                       "\tPièces        : " + String.format("%2d", scorePiece )  + " pt\n" +
		                       "\tColonne 1     : " + String.format("%2d", scoreCol[1])  + " pt\n" +
		                       "\tColonne 2     : " + String.format("%2d", scoreCol[2])  + " pt\n" +
		                       "\tColonne 3     : " + String.format("%2d", scoreCol[3])  + " pt\n" +
		                       "\tColonne 4     : " + String.format("%2d", scoreCol[4])  + " pt\n" +
		                       "\tColonne 5     : " + String.format("%2d", scoreCol[5])  + " pt\n" +
							   "\tColonne 6     : " + String.format("%2d", scoreCol[6])  + " pt\n" +
							   "\tColonne 7     : " + String.format("%2d", scoreCol[7])  + " pt\n" +
							   "\tColonne 8     : " + String.format("%2d", scoreCol[8])  + " pt\n" +
		                       "\tLigne 1       : " + String.format("%2d", scoreLig1  )  + " pt\n" +
		                       "\tLigne 2       : " + String.format("%2d", scoreLig2  )  + " pt\n" +
		                       "\tLigne 3       : " + String.format("%2d", scoreLig3  )  + " pt\n" +
							   "\tLigne 4       : " + String.format("%2d", scoreLig4  )  + " pt\n";

		this.score = scorePiece + scoreMinerai;
	}
	*/

	/**
	 * Trie une liste de jetons en fonction de leur fréquence d'apparition dans la liste initiale.
	 * Les jetons les plus fréquents sont placés en premier, suivis des jetons moins fréquents.
	 * Si des jetons sont moins nombreux que 3, des valeurs null sont ajoutées pour compléter.
	 *
	 * @param listeJeton La liste des jetons à trier.
	 * @return Une liste triée de jetons avec des valeurs null ajoutées pour compléter les groupes de trois.
	 */
	public List<Jeton> trieTab(List<Jeton> listeJeton)
	{
		// Liste finale trié
		List<Jeton>   tabJetonPresentTriee     = new ArrayList<>();

		// Liste des Minerais dans l'ordre de la plus grande à la plus petite
		List<Jeton>   tabJetonOrdreDecroissant = new ArrayList<>();

		// Liste des jetons déjà vérifier utilile pour ne pas refaire plusieurs fois les mêmes épices
		List<String>  tabTestJetonPresent      = new ArrayList<>();

		// liste qui compléte tabJetonOrdreDecroissant pour savoir combien de fois l'épice y est
		List<Integer> tabNbElementJeton        = new ArrayList<>();

		int nbElementDansTabElement = 0;
		int nbElementNull           = 0;


		// Rangement dans l'ordre Decroissant des épices dans tabJetonOrdreDecroissant.
		// Ajout du nombre de fois pour laquel l'épice est présente dans tabNbElementJeton
		// Ajout des épices déjà vu dans tabTestJetonPresent Obligé, car les épice sont différentes, mais pas leur nom

		for (int nbElement = Plateau.NB_LIGNES; nbElement > 0; nbElement--)
		{
			for(Jeton jeton : listeJeton)
			{
				if ( jeton != null                              &&
					this.compteurJetons(jeton) == nbElement     &&
					!tabTestJetonPresent.contains(((Minerai) jeton.getType()).getSymbole()) )
				{
					tabTestJetonPresent     .add(((Minerai) jeton.getType()).getSymbole());
					tabJetonOrdreDecroissant.add(jeton);
					tabNbElementJeton       .add(nbElement);
				}
			}
		}


		// Pour chaque épice triée, les compléter avec le nombre d'épices qu'il y a en tout, puis mettre des null si nécéssaire
		for (int cpt = 0; cpt < tabJetonOrdreDecroissant.size(); cpt ++)
		{
			nbElementDansTabElement = tabNbElementJeton.get(cpt);
			nbElementNull           = Plateau.NB_LIGNES - nbElementDansTabElement;

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


	/**
	 * Affichage du plateau
	 * @return L'affichage du plateau ainsi que le nombre de piece.
	 */
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for (int cptLig = Plateau.NB_LIGNES - 1; cptLig >= 0; cptLig--)
		{
			sb.append("+-----+-----+-----+-----+-----+-----+-----+-----+\n|");
			for (int cptCol = 0; cptCol < Plateau.NB_COLONNES; cptCol++)
			{
				int index = cptCol * Plateau.NB_LIGNES + cptLig;

				if (index < tabJetonPresent.size() && tabJetonPresent.get(index) != null)
				{
					if (tabJetonPresent.get(index).getType() instanceof Minerai)
					{
						sb.append(" ").append(((Minerai) tabJetonPresent.get(index).getType()).getSymbole()).append("  |");
					}
					else if (tabJetonPresent.get(index).getType() instanceof Piece)
					{
						sb.append(" ").append(((Piece) tabJetonPresent.get(index).getType()).getValeur()).append("  |");
					}
				}
				else
				{
					sb.append("     |");
				}
			}
			sb.append("\n");
		}
		sb.append("+-----+-----+-----+-----+-----+-----+-----+-----+\n").append(nbPiece).append(" pièces\n");
		return sb.toString();
	}

}