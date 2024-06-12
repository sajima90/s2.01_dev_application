/**
 * Cette classe gère les routes.
 *
 * Auteurs : CAUVIN Pierre, AUBIN Montagne, DELPECHE Nicolas, GUELLE Clément
 */
package psyche.vue.map;

import psyche.Controleur;
import psyche.metier.minerai.Couleur;

import javax.swing.*;
import java.awt.*;

public class PanelInfoVille extends JPanel
{

	private Controleur ctrl;

	private JLabel lblX, lblCoordX, lblY, lblCoordY, lblCoul, lblAffCoul, lblPoint, lblAffPoint;

	private JPanel panelDroite;



	/**
	 * Constructeur de la classe PanelInfoVille.
	 *
	 * @param ctrl Le contrôleur de l'application.
	 */
	public PanelInfoVille(Controleur ctrl)
	{

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
		this.lblX        = new JLabel("<html><b>X :</b></html>");
		this.lblCoordX   = new JLabel("<html><i>-</i></html>");

		this.lblY        = new JLabel("<html><b>Y :</b></html>");
		this.lblCoordY   = new JLabel("<html><i>-</i></html>");

		this.lblCoul     = new JLabel("<html><b>Couleur :</b></html>");
		this.lblAffCoul  = new JLabel("<html><i>-</i></html>");

		this.lblPoint    = new JLabel("<html><b>Point :</b></html>");
		this.lblAffPoint = new JLabel("<html><i>-</i></html>");



		// Positionnement des composants dans le panneau central
		this.panelDroite.add(this.lblX);
		this.panelDroite.add(this.lblCoordX);
		this.panelDroite.add(this.lblY);
		this.panelDroite.add(this.lblCoordY);
		this.panelDroite.add(this.lblCoul);
		this.panelDroite.add(this.lblAffCoul);
		this.panelDroite.add(this.lblPoint);
		this.panelDroite.add(this.lblAffPoint);


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
	 * @param x La coordonnée X de la ville.
	 * @param y La coordonnée Y de la ville.
	 */
	public void majVilleInfo(int x, int y, Couleur couleur, int point)
	{
		this.lblCoordX  .setText("<html><i>" + String.valueOf(x)       + "</i></html>");
		this.lblCoordY  .setText("<html><i>" + String.valueOf(y)       + "</i></html>");
		this.lblAffCoul .setText("<html><i>" + String.valueOf(couleur) + "</i></html>");
		this.lblAffPoint.setText("<html><i>" + String.valueOf(point)   + "</i></html>");

	}
}
