/**
 * @author CAUVIN Pierre, AUBIN Montagne, DELPECHE Nicolas, GUELLE Clément Cette classe gère les routes.
 */
package psyche.vue.map;

import psyche.Controleur;
import psyche.metier.map.Mine;
import psyche.metier.map.Route;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Cette classe gère le panel où les villes sont représentées. Elle permet de
 * dessiner des villes et des routes sur un panneau, ainsi que de gérer les
 * interactions avec la souris pour sélectionner et déplacer les villes.
 *
 * @author CAUVIN Pierre, MONTAGNE Aubin, DELPECHE Nicolas, GUELLE Clément
 *         BOUQUET Jules, YACHIR Yanis, ROUGEOLLE Henri
 */
public class PanelGraph extends JPanel
{
	private Controleur ctrl;
	private Mine mineSelect = null;

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
		this.setLayout(new BorderLayout());

		GereSouris gereSouris = new GereSouris();
		this.addMouseListener(gereSouris);
		this.addMouseMotionListener(gereSouris);
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

		for (Mine mine : this.ctrl.getMines())
		{
//			if (mine.equals(mineSelect))
//			{
//				g2d.setColor(Color.GREEN);
//			}
//			else
//			{
				g2d.setColor(new Color(50, 140, 255));
//			}

			g2d.setColor(mine.getCouleur().getColor());
			g2d.fillRect(mine.getX() - 10, mine.getY() - 10, 50, 100);
			g2d.setColor(Color.BLACK);
			g2d.drawString(mine.getCouleur().name() + " : " + mine.getPoint(), mine.getX() - 10, mine.getY() - 20);


		}

		for (Route route : this.ctrl.getRoutes())
		{
			Mine ville1 = route.getDepart();
			Mine ville2 = route.getArrivee();

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

			if (mineSelect != null)
			{
				mineSelect = null;
				ctrl.majIHM();
			}

			for (Mine mine : ctrl.getMines())
			{
				if (posX >= mine.getX() - 10 && posX <= mine.getX() + 10 && posY >= mine.getY() - 10
						&& posY <= mine.getY() + 10)
				{
					mineSelect = mine;

					ctrl.modifierMine(e.getX(), e.getY(), mine.getCouleur(), mine.getPoint());

					ctrl.majIHM(mineSelect);
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
			if (mineSelect != null)
			{
				ctrl.modifierMine( e.getX(), e.getY(), mineSelect.getCouleur(), mineSelect.getPoint());
				ctrl.majIHM(mineSelect);
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
