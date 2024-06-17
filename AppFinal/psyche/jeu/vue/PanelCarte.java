/**
 * Cette classe gère le panel où les villes sont représentées. Elle permet de dessiner des villes et des routes sur un
 * panneau, ainsi que de gérer les interactions avec la souris pour sélectionner et déplacer les villes.
 *
 * @author CAUVIN Pierre, MONTAGNE Aubin, DELPECHE Nicolas, GUELLE Clément BOUQUET Jules, YACHIR Yanis, ROUGEOLLE
 * 		Henri
 */
package psyche.jeu.vue;

import psyche.jeu.ControleurJeu;
import psyche.jeu.metier.Mine;
import psyche.jeu.metier.Route;
import psyche.jeu.metier.Couleur;
import psyche.map.metier.Sommet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class PanelCarte extends JPanel
{

	private final ControleurJeu ctrlJeu;
	private       Mine mineSelect = null;


	/**
	 * Constructeur du panel graphique.
	 *
	 * @param ctrlJeu Le contrôleur associé à ce panel.
	 */
	public PanelCarte(ControleurJeu ctrlJeu)
	{
		this.ctrlJeu = ctrlJeu;
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());

		GereSouris gereSouris = new GereSouris();
		this.addMouseListener(gereSouris);
		this.addMouseMotionListener(gereSouris);

	}

	/**
	 * Redessine les composants graphiques du panel.
	 *
	 * @param g L'objet Graphics utilisé pour dessiner.
	 */
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		// Charger l'image de fond
		ImageIcon imageIcon = new ImageIcon("../psyche/theme/images/Plateau_vierge.png");
		Image backgroundImage = imageIcon.getImage();

		// Dessiner l'image de fond
		g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);

		Graphics2D g2d = (Graphics2D) g;
		g.drawRect(0, 0, 1000, 800);

		// Largeur et hauteur des rectangles représentant les mines
		int rectWidth = 50;
		int rectHeight = 100;

		for (Mine mine : this.ctrlJeu.getMines())
		{
			g2d.setColor(mine.getCouleur().getColor());
			g2d.fillRect(mine.getX() - 10, mine.getY() - 10, rectWidth, rectHeight);
			g2d.setColor(Color.BLACK);
			g2d.drawString(mine.getNom(), mine.getX() - 10, mine.getY() - 20);
		}

		for (Route route : this.ctrlJeu.getRoutes())
		{

			Mine ville1 = route.getDepart();
			Mine ville2 = route.getArrivee();

			// Calculer les coordonnées centrales des rectangles représentant les mines
			int ville1CenterX = ville1.getX() + rectWidth / 2 - 10;
			int ville1CenterY = ville1.getY() + rectHeight / 2 - 10;
			int ville2CenterX = ville2.getX() + rectWidth / 2 - 10;
			int ville2CenterY = ville2.getY() + rectHeight / 2 - 10;

			// Dessiner la ligne continue entre les centres des rectangles
			g2d.setColor(Color.BLACK);
			g2d.setStroke(new BasicStroke(2.0f));
			g2d.drawLine(ville1CenterX, ville1CenterY, ville2CenterX, ville2CenterY);

			// Dessiner les points espacés le long de la ligne
			int troncons = route.getTroncons();
			double deltaX = (ville2CenterX - ville1CenterX) / (double) (troncons + 1);
			double deltaY = (ville2CenterY - ville1CenterY) / (double) (troncons + 1);

			// Vérifier s'il y a plus d'un tronçon pour dessiner les points intermédiaires
			if (troncons > 1)
			{
				int centerX = (ville1CenterX + ville2CenterX) / 2;
				int centerY = (ville1CenterY + ville2CenterY) / 2;
				g2d.fillOval(centerX - 5, centerY - 5, 10, 10);
			}
			else if (troncons == 1)
			{
				// Si un seul tronçon, ne pas dessiner de points intermédiaires
				g2d.setColor(Color.BLACK);
				g2d.setStroke(new BasicStroke(2.0f));
				g2d.drawLine(ville1CenterX, ville1CenterY, ville2CenterX, ville2CenterY);

			}

			// Ajouter le point de début
			g2d.fillOval(ville1CenterX - 5, ville1CenterY - 5, 10, 10);

			// Ajouter le point de fin
			g2d.fillOval(ville2CenterX - 5, ville2CenterY - 5, 10, 10);
		}
	}

	/**
	 * Classe interne pour gérer les événements de souris.
	 */
	private class GereSouris extends MouseAdapter
	{
		private int posX, posY;

		/**
		 * Gère l'événement lorsque la souris est pressée.
		 *
		 * @param e L'événement de souris.
		 */
		@Override
		public void mousePressed(MouseEvent e)
		{
			posX = e.getX();
			posY = e.getY();
			mineSelect = null;
			int setX = 0;
			int setY = 0;

			for (Mine mine : ctrlJeu.getMines())
			{
				int diameter = 30;
				int radius = diameter / 2;
				int centerX = mine.getX();
				int centerY = mine.getY();
				int distX = Math.abs(e.getX() - centerX);
				int distY = Math.abs(e.getY() - centerY);
				double distance = Math.sqrt(distX * distX + distY * distY);

				if (distance <= radius)
				{
					if (ctrlJeu.mineEstAdjacent(mineSelect, mine))
					{
						if (mineSelect != null)
						{
							mineSelect = mine;
							//Changer la couleur de la mine selectionée
							break;
						}
						else
						{
							ctrlJeu.possederMine(mineSelect);
							ctrlJeu.changerTour();
							mineSelect = null;
						}
					}
				}

			}

			ctrlJeu.majIHM();
		}



		/**
		 * Gère l'événement lorsque la souris est déplacée tout en étant pressée.
		 *
		 * @param e
		 *          L'événement de souris.
		 */
		public void mouseDragged(MouseEvent e)
		{
		}

		/**
		 * Gère l'événement lorsque la souris est relâchée.
		 *
		 * @param e
		 *          L'événement de souris.
		 */
		public void mouseReleased(MouseEvent e)
		{
			ctrlJeu.majIHM();
		}
	}

}
