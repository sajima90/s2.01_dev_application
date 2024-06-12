/**
 * Cette classe gère les routes.
 *
 * Auteurs : CAUVIN Pierre, AUBIN Montagne, DELPECHE Nicolas, GUELLE Clément
 */
package sae.vue;

import sae.Controleur;

import javax.swing.*;
import java.awt.*;

public class PanelInfoVille extends JPanel
{

	private Controleur ctrl;

	private JLabel dNom, dX, dY;

	private JPanel panelDroite;



	/**
	 * Constructeur de la classe PanelInfoVille.
	 *
	 * @param ctrl Le contrôleur de l'application.
	 */
	public PanelInfoVille(Controleur ctrl) {
		this.ctrl = ctrl;

		// Utilisation d'un BorderLayout pour une taille stable du panel
		this.setLayout(new BorderLayout());
		this.setBackground(Color.lightGray);

		// Panneau central avec GridLayout pour aligner les composants verticalement
		JPanel centerPanel = new JPanel();
		this.panelDroite   = new JPanel(new GridLayout(4, 2, 5, 5));

		centerPanel.setOpaque(false);
		this.panelDroite.setOpaque(false);


		// Création des composants
		JLabel nom = new JLabel("<html><b>Nom Ville :</b></html>");

		this.dNom = new JLabel("<html><i>-</i></html>");

		JLabel x = new JLabel("<html><b>X :</b></html>");
		this.dX  = new JLabel("<html><i>-</i></html>");

		JLabel y = new JLabel("<html><b>Y :</b></html>");
		this.dY  = new JLabel("<html><i>-</i></html>");




		// Positionnement des composants dans le panneau central
		this.panelDroite.add(nom);
		this.panelDroite.add(this.dNom);
		this.panelDroite.add(x);
		this.panelDroite.add(this.dX);
		this.panelDroite.add(y);
		this.panelDroite.add(this.dY);

		// Ajouter des marges autour du panneau central
		JPanel marginPanel = new JPanel(new BorderLayout());
		marginPanel.setOpaque(false);
		marginPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		marginPanel.add(centerPanel, BorderLayout.CENTER);
		marginPanel.add(this.panelDroite, BorderLayout.EAST);


		// Ajouter le panneau avec marges au panneau principal
		this.add(marginPanel);
	}

	/**
	 * Met à jour les informations de la ville affichée.
	 *
	 * @param nom Le nom de la ville.
	 * @param x La coordonnée X de la ville.
	 * @param y La coordonnée Y de la ville.
	 */
	public void majVilleInfo(String nom, int x, int y)
	{
		this.dNom.setText("<html><i>" + nom + "</i></html>");
		this.dX.setText("<html><i>" + String.valueOf(x) + "</i></html>");
		this.dY.setText("<html><i>" + String.valueOf(y) + "</i></html>");
	}
}
