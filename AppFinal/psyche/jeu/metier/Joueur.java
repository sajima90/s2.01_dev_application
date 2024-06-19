package psyche.jeu.metier;

import psyche.jeu.vue.PanelJoueur;

import java.util.ArrayList;
import java.util.List;

public class Joueur
{

	/*--------------*/
	/*  Données     */
	/*--------------*/

	// Nombre de case total pour les jetons
	private static final int NB_PIECE_MAX   = 8;
	private static final int NB_LIGNE_MAX   = 4;
	private static final int NB_COLONNE_MAX = 8;

	private static int nbJoueur = 0;
	private        int numJoueur;

	private List<Mine>    minesObtenues;
	private List<Route>   routesJoueur;
	private List<Jeton>   tabJetonPresent;


	private int nbPiece;
	private int             score;
	private int nbJetonPossession;
	private String			nom		;

	private boolean estSonTour;


	/*--------------*/
	/*  Méthodes    */
	/*--------------*/

	/**
	 * Constructeur de Joueur
	 *
	 */
	public Joueur(String nom)
	{

		this.nbPiece            = 0;
		this.nom		        = nom;
		this.score              = 0;
		this.nbJetonPossession  = 25;
		this.minesObtenues 	    = new ArrayList<Mine> ();
		this.tabJetonPresent 	= new ArrayList<Jeton>();
		this.routesJoueur 	    = new ArrayList<Route>();
		this.estSonTour         = false;


		this.numJoueur = ++Joueur.nbJoueur;

	}

	/*----------*/
	/*   Get    */
	/*----------*/

	public int                getScore            ()    {return this.score;              }
	public List<Mine>         getMinesObtenues    ()	{ return this.minesObtenues;     }
	public List<Jeton>        getJetonObtenues    ()    { return this.tabJetonPresent;   }
	public String             getNom			  ()    { return this.nom;               }
	public int 				  getNumJoueur        ()    { return this.numJoueur;	     }
	public int                getNbJetonPossession()    { return this.nbJetonPossession; }


	/*-------------------------*/
	/*   Set,     variateurs   */
	/*-------------------------*/

	/**
	 * Ajoute une mine à la liste des mines obtenues du joueur
	 *
	 * @param mine
	 * 		La mine à ajouter
	 */
	public void ajouterMine     ( Mine mine )     {this.minesObtenues.add(mine);}
	public void ajouterRoute    ( Route route )   {this.routesJoueur.add(route);}


	public void setNom              ( String nom )        { this.nom                = nom; }
	public void setNbJetonPossession( int    nbTroncons ) { this.nbJetonPossession -= nbTroncons; }



	/*---------------------*/
	/*   Autres méthodes   */
	/*---------------------*/

	public boolean estSonTour()
	{
		return this.estSonTour;
	}






	/**
	 * La méthode ajoute la ressource au plateau, tout en vérifiant si elle peut y être ajouté ou non.
	 * @param ressource qui est soit une épice, soit une piece
	 * @return un boolean pour savoir si la ressource peut être ajouté
	 */
	public boolean ajouterRessource(Jeton ressource)
	{
		int valPiece = 0;

		if (ressource == null) {
			return false;
		}

		for (int i = 0; i < this.getListJeton().size()-1 ; i++)
		{
//			if ( this.getListJeton().get(i) != null )
//				System.out.println("\n\n\n LE MINERAI AJOUTER" + ((Minerai) this.getListJeton().get(i).getType()).getNom() + "\n\n\n");
		}

		if(ressource.getType() instanceof Minerai)
		{
			if (this.compteurJetons(this.tabJetonPresent, ressource) == 0)
			{
				if (this.getNbEpicesDifferentPlateau() < Joueur.NB_COLONNE_MAX)
				{
					this.tabJetonPresent.add(ressource);
					this.tabJetonPresent = this.trieTab(this.tabJetonPresent);

					return true;
				}
			}
			else
			{

				if (this.compteurJetons(this.tabJetonPresent, ressource) < Joueur.NB_LIGNE_MAX)
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
					case NR -> {valPiece = 1;}
				}

				if (nbPiece + valPiece <= Joueur.NB_PIECE_MAX)
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
		List<Minerai> listeMinerais = new ArrayList<>();
		for (Jeton jeton : this.tabJetonPresent)
		{
			if ( jeton != null)
			{
				Minerai minerai = (Minerai) jeton.getType();
				if (!listeMinerais.contains(minerai))
				{
					listeMinerais.add(minerai);
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
		int[] tableauScoresligne   = { 0, 0, 2, 5,  9, 14, 20, 32, 64 };
		int[] tableauScoresColonne = { 0, 0, 2,10, 20 };

		int scorePiece;
		int scoreEpice;

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


		List<String>  tabTestJetonPresent     = new ArrayList<>();
		List<Integer> tabNbElementJeton       = new ArrayList<>();

		List<String> tabCompteLigneEtage1   = new ArrayList<>();
		List<String> tabCompteLigneEtage2   = new ArrayList<>();
		List<String> tabCompteLigneEtage3   = new ArrayList<>();
		List<String> tabCompteLigneEtage4   = new ArrayList<>();

		//------------Calcul Pièce------------//

		scorePiece = tableauScoresPieces[this.nbPiece];



		// ------------Calcul Epice-----------//

		scoreCol1=0;
		scoreCol2=0;
		scoreCol3=0;
		scoreCol4=0;
		scoreCol5=0;
		scoreCol6=0;
		scoreCol7=0;
		scoreCol8=0;

		// Le premier élement est mis à null pour de l'esthetique au niveau des comptes plus bas
		Integer[] scoreCol = {null, scoreCol1, scoreCol2, scoreCol3, scoreCol4, scoreCol5 ,scoreCol6,scoreCol7,scoreCol8};


		// Calcul des points gagné grâce aux colonnes

		for (int nbElement = Joueur.NB_LIGNE_MAX; nbElement > 0; nbElement--)
		{
			for (Jeton jeton : this.tabJetonPresent)
			{
				if (jeton != null                                                     &&
						this.compteurJetons(this.tabJetonPresent, jeton) == nbElement &&
						!tabTestJetonPresent.contains(((Minerai) jeton.getType()).getNom()))
				{
					tabTestJetonPresent.add(((Minerai) jeton.getType()).getNom());
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
				if (tabCompteLigneEtage1.contains(((Minerai) jeton.getType()).getNom()))
				{
					if (tabCompteLigneEtage2.contains(((Minerai) jeton.getType()).getNom()))
					{
						if (tabCompteLigneEtage3.add(((Minerai) jeton.getType()).getNom()))
						{
							tabCompteLigneEtage4.add(((Minerai) jeton.getType()).getNom());
						}
						else
							tabCompteLigneEtage3.add(((Minerai) jeton.getType()).getNom());
					}
					else
						tabCompteLigneEtage2.add(((Minerai) jeton.getType()).getNom());
				}
				else
					tabCompteLigneEtage1.add(((Minerai) jeton.getType()).getNom());
			}
		}

		scoreLig1 = tableauScoresligne[tabCompteLigneEtage1.size()];
		scoreLig2 = tableauScoresligne[tabCompteLigneEtage2.size()];
		scoreLig3 = tableauScoresligne[tabCompteLigneEtage3.size()];
		scoreLig4 = tableauScoresligne[tabCompteLigneEtage4.size()];

		scoreEpice = scoreLig1 + scoreLig2 + scoreLig3 + scoreLig4 + scoreCol[1] + scoreCol[2] + scoreCol[3] + scoreCol[4] + scoreCol[5] + scoreCol[6] + scoreCol[7] + scoreCol[8];

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

		for (int nbElement = Joueur.NB_LIGNE_MAX; nbElement > 0; nbElement--)
		{
			for(Jeton jeton : listeJeton)
			{
				if ( jeton != null                                          &&
						this.compteurJetons(listeJeton, jeton) == nbElement &&
						!tabTestJetonPresent.contains(((Minerai) jeton.getType()).getNom()) )
				{
					tabTestJetonPresent     .add(((Minerai) jeton.getType()).getNom());
					tabJetonOrdreDecroissant.add(jeton);
					tabNbElementJeton       .add(nbElement);
				}
			}
		}


		// Pour chaque épice trié les complété avec le nombre d'épice qu'il y a en tous, pis mettre des null si nécéssaire
		for (int cpt=0; cpt < tabJetonOrdreDecroissant.size(); cpt ++)
		{
			nbElementDansTabElement = tabNbElementJeton.get(cpt);
			nbElementNull           = 4 - nbElementDansTabElement;

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
	 * Renvoie un affichage contenant les informations du joueur.
	 *
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
					if (tabJetonPresent.get(index).getType() instanceof Minerai)
					{
						sb.append(" ").append(((Minerai) tabJetonPresent.get(index).getType()).getNom()).append(" |");
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



	public boolean PossedeMine (Mine mine)
	{

		for (Mine mineTraitee : this.minesObtenues)
		{
			if (mine == mineTraitee)
				return true;
		}
		return false;
	}

	public void setTour(boolean b)
	{
		this.estSonTour = b;
	}
}