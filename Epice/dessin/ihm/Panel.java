package dessin.ihm;

import javax.swing.*;

import dessin.Controleur;

import java.awt.*;
import java.awt.event.ActionEvent;

public class Panel extends JPanel 
{
	private Controleur ctrl;
	private Graphics2D g2;

	private Image imgFond;

	private JLabel[][] tabEpice;
	private JLabel[]   tabPiece;

	public Panel(Controleur ctrl)
	{

		this.ctrl = ctrl;

		this.imgFond = getToolkit().getImage(this.ctrl.getImageFond());


		this.setLayout(null);

		// Coordonnées des cases pour les épices
		int[][] coordsEpice = { {  80,   1 }, { 239,   1 }, { 390,   1 }, { 550,   1 }, { 708,   1 }, 
		                        {  80, 113 }, { 239, 113 }, { 390, 113 }, { 550, 113 }, { 708, 113 }, 
		                        {  80, 224 }, { 239, 224 }, { 390, 224 }, { 550, 224 }, { 708, 224 } };

		// Coordonnées pour les pièces 
		int[] coordsPiece = { 75, 159, 242, 326, 409, 492, 576, 660, 760 };

		// création des composants;


		this.tabEpice = new JLabel[3][5];                       // A CHANGE LES VALEURS EN DUR
		this.tabPiece = new JLabel[8];                          // A CHANGE LES VALEURS EN DUR

		int indexEpice = 0;
		for (int lig = 0; lig <= this.tabEpice.length - 1; lig++)
		{
			int compteurEpice = 2-lig;

			for (int col = 0; col <= this.tabEpice[lig].length - 1; col++)
			{

				this.tabEpice[lig][col] = new JLabel();
				this.tabEpice[lig][col].setBounds(coordsEpice[indexEpice][0], coordsEpice[indexEpice][1], 100, 100); // Positionnement manuel

				if ( ctrl.getEpice(compteurEpice) != null )
				{
					Icon img = new ImageIcon(ctrl.getEpice(compteurEpice));

					this.tabEpice[lig][col].setIcon(img);
					this.tabEpice[lig][col].setOpaque(false);
				}
				
				compteurEpice+=3;
				indexEpice   ++ ;
			}

		}


		System.out.println(ctrl.getNbPiece());

		for (int indice = 0; indice <= ctrl.getNbPiece(); indice++)
		{
			Icon img = new ImageIcon(ctrl.getPiece());

			this.tabPiece[indice] = new JLabel();
			this.tabPiece[indice].setBounds(coordsPiece[indice],354,  100, 100); // Positionnement manuel
			this.tabPiece[indice].setIcon(img);
			this.tabPiece[indice].setOpaque(false);

		}



		// positionnement des composants
		

		for (int lig = 0; lig < tabEpice.length; lig++)
			for (int col = 0; col < tabEpice[lig].length; col++)
			{
				this.add(this.tabEpice[lig][col]);
			}

		for (int indice = 0; indice < ctrl.getNbPiece(); indice++)
		{
			this.add(this.tabPiece[indice]);
		}

	}

	public void actionPerformed(ActionEvent evt)
	{
		this.repaint();
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		g2 = (Graphics2D) g;

		// Ajout de l'image du fond
		g2.drawImage(imgFond, 0, 0,this.getWidth(), this.getHeight(), this);
	}

}