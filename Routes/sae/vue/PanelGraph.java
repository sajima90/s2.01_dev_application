/**
 * @author CAUVIN Pierre, AUBIN Montagne, DELPECHE Nicolas, GUELLE Clément
 * Cette classe gère les routes.
 */
package sae.vue;

import sae.Controleur;
import sae.metier.Route;
import sae.metier.Ville;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Cette classe gère le panel où les villes sont représentées. Elle permet de
 * dessiner des villes et des routes sur un panneau, ainsi que de gérer les
 * interactions avec la souris pour sélectionner et déplacer les villes.
 *
 * @author CAUVIN Pierre, MONTAGNE Aubin, DELPECH Nicolas, GUELLE Clément
 */
public class PanelGraph extends JPanel
{
	private Controleur ctrl;
	private int villeX;
	private int villeY;
	private Ville villeSelect = null;

	/**
	 * Constructeur du panel graphique.
	 *
	 * @param ctrl
	 *            Le contrôleur associé à ce panel.
	 */
	public PanelGraph(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setBackground(Color.WHITE);

		GereSouris gereSouris = new GereSouris();
		this.addMouseListener(gereSouris);
		this.addMouseMotionListener(gereSouris);
	}

	/**
	 * Définit les coordonnées d'une ville.
	 *
	 * @param x
	 *            La coordonnée X de la ville.
	 * @param y
	 *            La coordonnée Y de la ville.
	 */
	public void setVilleCoords(int x, int y)
	{
		this.villeX = x;
		this.villeY = y;
		this.ctrl.majIHM();
	}

	/**
	 * Redessine les composants graphiques du panel.
	 *
	 * @param g
	 *            L'objet Graphics utilisé pour dessiner.
	 */

	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g.drawRect(0, 0, 1000, 800);

		for (Ville ville : this.ctrl.getVilles())
		{
			if (ville.equals(villeSelect))
			{
				g2d.setColor(Color.GREEN);
			}
			else
			{
				g2d.setColor(new Color(50, 140, 255));
			}

			g2d.fillOval(ville.getX() - 10, ville.getY() - 10, 20, 20);
			g2d.setColor(Color.BLACK);
			g2d.drawOval(ville.getX() - 10, ville.getY() - 10, 20, 20);
			g2d.drawString(ville.getNom(), ville.getX() - 10, ville.getY() - 10);
		}

		for (Route route : this.ctrl.getRoutes())
		{
			Ville ville1 = route.getDepart();
			Ville ville2 = route.getArrivee();

			double distance = Math
					.sqrt(Math.pow(ville1.getX() - ville2.getX(), 2) + Math.pow(ville1.getY() - ville2.getY(), 2));
			double tailleSegment = distance / (route.getTroncons() * 2 - 1);
			float[] pattern = { (float) tailleSegment, (float) tailleSegment };

			g2d.setColor(Color.BLACK);
			g2d.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, pattern, 0.0f));
			g2d.drawLine(ville1.getX(), ville1.getY(), ville2.getX(), ville2.getY());
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
		 *            L'événement de souris.
		 */
		public void mousePressed(MouseEvent e)
		{
			posX = e.getX();
			posY = e.getY();

			if (villeSelect != null)
			{
				villeSelect = null;
				ctrl.majIHM();
			}

			for (Ville ville : ctrl.getVilles())
			{
				if (posX >= ville.getX() - 10 && posX <= ville.getX() + 10 && posY >= ville.getY() - 10
						&& posY <= ville.getY() + 10)
				{
					villeSelect = ville;

					ctrl.modifierVille(String.valueOf(villeSelect.getNom()), e.getX(), e.getY());

					ctrl.majIHM(villeSelect);
				}
			}
		}

		/**
		 * Gère l'événement lorsque la souris est déplacée tout en étant
		 * pressée.
		 *
		 * @param e
		 *            L'événement de souris.
		 */
		public void mouseDragged(MouseEvent e)
		{
			if (villeSelect != null)
			{
				ctrl.modifierVille(String.valueOf(villeSelect.getNom()), e.getX(), e.getY());
				ctrl.majIHM(villeSelect);
				ctrl.enregistrer();
			}
		}

		/**
		 * Gère l'événement lorsque la souris est relâchée.
		 *
		 * @param e
		 *            L'événement de souris.
		 */
		public void mouseReleased(MouseEvent e)
		{
			ctrl.majIHM();
		}
	}
}
