package psyche.jeu.vue;

import psyche.jeu.ControleurJeu;
import psyche.jeu.metier.Joueur;
import psyche.jeu.metier.Mine;
import psyche.jeu.metier.Route;
import psyche.jeu.metier.Minerai;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PanelJoueur extends JPanel
{
	private Joueur joueur;
	private ControleurJeu ctrlJeu;

	private Graphics2D g2;

	private Image imgFond;

	private JLabel[][] tabMinerais;
	private JLabel[]   tabPiece;

	private int[][] coordsMinerais ={   {  64, 20  }, { 116,  20 }, { 167,  20 }, { 218,  20 }, { 270,  20 }, { 321,  20 }, { 373,  20 }, { 424,  20 },
			                            {  64, 69  }, { 116,  69 }, { 167,  69 }, { 218,  69 }, { 270,  69 }, { 321,  69 }, { 373,  69 }, { 424,  69 },
			                            {  64, 118 }, { 116, 118 }, { 167, 118 }, { 218, 118 }, { 270, 118 }, { 321, 118 }, { 373, 118 }, { 424, 118 },
			                            {  64, 167 }, { 116, 224 }, { 167, 167 }, { 218, 167 }, { 270, 167 }, { 321, 167 }, { 373, 167 }, { 424, 167 }};

	private int[] coordsPiece = { 64, 116, 167, 218, 270, 321, 373, 424, 763 }; //266

	public PanelJoueur(Joueur joueur, ControleurJeu ctrlJeu)
	{
		this.joueur = joueur;
		this.ctrlJeu = ctrlJeu;

		this.setLayout(null);

		this.tabMinerais = new JLabel[4][8];
		this.tabPiece    = new JLabel[8];
		for(int lig = 0; lig < 4;lig++)
		{
			for (int col = 0; col < 8; col++)
			{
				this.tabMinerais[lig][col] = new JLabel();
				this.tabMinerais[lig][col].setBounds(this.coordsMinerais[lig][0], this.coordsMinerais[lig][1], 80,
						80); // Positionnement manuel
				this.add(this.tabMinerais[lig][col]);
			}
		}


		for (int cpt = 0 ;cpt<8;cpt++)
		{
			this.tabPiece[cpt] = new JLabel();
			this.tabPiece[cpt].setBounds(this.coordsPiece[cpt],266,  100, 100); // Positionnement manuel
			this.add(this.tabPiece[cpt]);
		}
	}

	/*public void ajouterMinerais(Minerai m)
	{
		for(int i = 0; i < this.coordsMinerais[i].length; i++)
		{
			if(this.tabMinerais[i][0] == m)
			{
				for(int j = 0; j < this.tabMinerais.length; j++)
				{
					if (!(this.tabMinerais[j][i] == null))
					{
						this.tabMinerais[j][i] = m;
						this.repaint();

						for(int cpt1 = 0; cpt1<this.tabMinerais.length;cpt1++)
						{
							for(int cpt2 = 0; cpt2<this.tabMinerais[cpt1].length; cpt2++)
							{
								System.out.println(this.tabMinerais[cpt1][cpt2].getNom() + " ");
							}
							System.out.println("\n");
						}
						return;
					}
				}
			}
		}
	}*/

	public void actionPerformed(ActionEvent evt)
	{
		this.repaint();
	}

	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		// Charger l'image de fond

		if (this.joueur.getNumJoueur() == 1)
		{
			ImageIcon imageIcon = new ImageIcon("../psyche/theme/images/plateau_joueur_1.png");

			Image backgroundImage = imageIcon.getImage();
			g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);

			this.ajoutMinerais();

		}
		else
		{
			ImageIcon imageIcon = new ImageIcon("../psyche/theme/images/plateau_joueur_2.png");

			Image backgroundImage = imageIcon.getImage();

			g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);

			this.ajoutMinerais();

		}

	}

	public void ajoutMinerais()
	{
		int indexMinerai = 0;

		if ( this.joueur.getJetonObtenues().get(0) != null )
			System.out.println(((Minerai) this.joueur.getJetonObtenues().get(0).getType()).getNom() + "-----------------------------------------------------------");


		for (int lig = 0; lig <= this.tabMinerais.length - 1; lig++)
		{
			int compteurMinerai = 3-lig;

			for (int col = 0; col <= this.tabMinerais[lig].length - 1; col++)
			{

				this.tabMinerais[lig][col].setBounds(this.coordsMinerais[indexMinerai][0], this.coordsMinerais[indexMinerai][1], 100, 100); // Positionnement manuel

				if ( this.ctrlJeu.getMinerais(compteurMinerai) != null )
				{
					Icon img = new ImageIcon(this.ctrlJeu.getMinerais(compteurMinerai));

					this.tabMinerais[lig][col].setIcon(img);
					this.tabMinerais[lig][col].setOpaque(false);
				}

				compteurMinerai+=4;
				indexMinerai   ++ ;
			}

		}


		for (int indice = 0; indice <= this.ctrlJeu.getNbPiece(); indice++)
		{
			Icon img = new ImageIcon(this.ctrlJeu.getPiece());

			this.tabPiece[indice].setBounds(this.coordsPiece[indice],266,  100, 100); // Positionnement manuel
			this.tabPiece[indice].setIcon(img);
			this.tabPiece[indice].setOpaque(false);
		}

	}
}