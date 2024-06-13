package psyche.map.vue;

import psyche.map.ControleurMap;
import psyche.map.metier.Arrete;
import psyche.map.metier.Sommet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Cette classe gère le panel où les villes sont représentées. Elle permet de dessiner des villes et des routes sur un
 * panneau, ainsi que de gérer les interactions avec la souris pour sélectionner et déplacer les villes.
 *
 * @author CAUVIN Pierre, MONTAGNE Aubin, DELPECHE Nicolas, GUELLE Clément BOUQUET Jules, YACHIR Yanis, ROUGEOLLE Henri
 */
public class PanelGraph extends JPanel
{
	private ControleurMap ctrlMap;
	private Sommet mineSelect = null;

	/**
	 * Constructeur du panel graphique.
	 *
	 * @param ctrlMap
	 * 		Le contrôleur associé à ce panel.
	 */
	public PanelGraph(ControleurMap ctrlMap)
	{
		this.ctrlMap = ctrlMap;
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());

		GereSouris gereSouris = new GereSouris();
		this.addMouseListener(gereSouris);
		this.addMouseMotionListener(gereSouris);
	}

	/**
	 * Redessine les composants graphiques du panel.
	 *
	 * @param g
	 * 		L'objet Graphics utilisé pour dessiner.
	 */
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g.drawRect(0, 0, 1000, 800);

		// Largeur et hauteur des rectangles représentant les mines
		int rectWidth = 50;
		int rectHeight = 100;

		for (Sommet mine : this.ctrlMap.getSommets())
		{
			g2d.setColor(mine.getCouleur().getColor());
			g2d.fillRect(mine.getX() - 10, mine.getY() - 10, rectWidth, rectHeight);
			g2d.setColor(Color.BLACK);
			g2d.drawString(mine.getNom(), mine.getX() - 10, mine.getY() - 20);
		}

		for (Arrete arrete : this.ctrlMap.getArretes())
		{
			Sommet ville1 = arrete.getDepart();
			Sommet ville2 = arrete.getArrivee();

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
			int troncons = arrete.getTroncons();
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
		 * @param e
		 * 		L'événement de souris.
		 */
		public void mousePressed(MouseEvent e)
		{
			posX = e.getX();
			posY = e.getY();

			mineSelect = null;
			for (Sommet mine : ctrlMap.getSommets())
			{
				if (posX >= mine.getX() - 10 && posX <= mine.getX() + 40 && posY >= mine.getY() - 10 && posY <= mine.getY() + 90)
				{
					mineSelect = mine;
					break;
				}
			}

			ctrlMap.majIHM();
		}

		/**
		 * Gère l'événement lorsque la souris est déplacée tout en étant pressée.
		 *
		 * @param e
		 * 		L'événement de souris.
		 */
		public void mouseDragged(MouseEvent e)
		{
			if (mineSelect != null)
			{
				int newX = e.getX();
				int newY = e.getY();

				ctrlMap.modifierSommet(newX, newY, mineSelect.getNom());
				ctrlMap.majIHM(mineSelect);
				ctrlMap.enregistrer();
			}
		}

		/**
		 * Gère l'événement lorsque la souris est relâchée.
		 *
		 * @param e
		 * 		L'événement de souris.
		 */
		public void mouseReleased(MouseEvent e)
		{
			ctrlMap.majIHM();
		}
	}
}

