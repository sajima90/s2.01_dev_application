package psyche.jeu.vue;

import psyche.jeu.ControleurJeu;
import psyche.jeu.metier.Mine;
import psyche.jeu.metier.Route;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelCarte extends JPanel
{

	private final ControleurJeu ctrlJeu;
	private Mine mineSelect = null;

	// Ajouter des variables pour les images des pions des joueurs
	/**
	 * Constructeur du panel graphique.
	 *
	 * @param ctrlJeu
	 * 		Le contrôleur associé à ce panel.
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
	 * @param g
	 * 		L'objet Graphics utilisé pour dessiner.
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
		int rectWidth = 40;
		int rectHeight = 65;

		for (Mine mine : this.ctrlJeu.getMines())
		{
			if (!mine.getCouleur().name().equals("ROME"))
			{
				if (mine.estPrise())
				{
					g.drawImage(getToolkit().getImage(
									"../psyche/theme/images/transparent/" + mine.getCouleur().getLienImage() + "_clair.png"),
							mine.getX() - 10, mine.getY() - 10, rectWidth, rectHeight, this);
				}
				else
				{
					g.drawImage(getToolkit().getImage(
									"../psyche/theme/images/transparent/" + mine.getCouleur().getLienImage() + ".png"),
							mine.getX() - 10, mine.getY() - 10, rectWidth, rectHeight, this);
				}
				g2d.drawString(String.valueOf(mine.getPoint()), mine.getX() + 8, mine.getY() + 12);

				if (!mine.estPrise())
				{
					g.drawImage(getToolkit().getImage(
									"../psyche/theme/images/ressources/" + mine.getMinerai().getLienImage()), mine.getX(),
							mine.getY() + 30, 20, 20, this);
				}
			}
			else
			{
				g.drawImage(getToolkit().getImage(
								"../psyche/theme/images/transparent/" + mine.getCouleur().getLienImage() + ".png"),
						mine.getX() - 15, mine.getY() - 5, 51, 55, this);
			}

			g2d.drawString(String.valueOf(mine.getNom()), mine.getX(), mine.getY());
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

			// Calculer la distance entre les centres des mines
			double distance = Math.sqrt(
					Math.pow(ville2CenterX - ville1CenterX, 2) + Math.pow(ville2CenterY - ville1CenterY, 2));

			// Calculer les points de départ et d'arrivée des routes proportionnellement à la distance
			int offsetAdjustment = 28;

			int ville1EdgeX = (int) (ville1CenterX + (offsetAdjustment * (ville2CenterX - ville1CenterX) / distance));
			int ville1EdgeY = (int) (ville1CenterY + (offsetAdjustment * (ville2CenterY - ville1CenterY) / distance));
			int ville2EdgeX = (int) (ville2CenterX - (offsetAdjustment * (ville2CenterX - ville1CenterX) / distance));
			int ville2EdgeY = (int) (ville2CenterY - (offsetAdjustment * (ville2CenterY - ville1CenterY) / distance));

			// Dessiner la ligne continue entre les centres des rectangles
			g2d.setColor(Color.BLACK);
			g2d.setStroke(new BasicStroke(3.0f));
			g2d.drawLine(ville1EdgeX, ville1EdgeY, ville2EdgeX, ville2EdgeY);

			// Dessiner les points espacés le long de la ligne
			int troncons = route.getTroncons();
			double deltaX = (ville2EdgeX - ville1EdgeX) / (double) (troncons + 1);
			double deltaY = (ville2EdgeY - ville1EdgeY) / (double) (troncons + 1);

			// Ajouter les icônes des pions des joueurs sur les tronçons
			for (int i = 1; i <= troncons; i++)
			{
				int pionX = (int) (ville1EdgeX + i * deltaX - 5);
				int pionY = (int) (ville1EdgeY + i * deltaY - 5);

				// Charger les images des pions des joueurs
				Image pionJoueur1 = new ImageIcon("..psyche/theme/images/pion_joueur_1.png").getImage();
				Image pionJoueur2 = new ImageIcon("../psyche/theme/images/pion_joueur_2.png").getImage();
				if (i % 2 == 1)
				{
					g.drawImage(pionJoueur1, pionX, pionY, 20, 20, this);
				}
				else
				{
					g.drawImage(pionJoueur2, pionX, pionY, 20, 20, this);
				}
			}

			// Ajouter le point de début
			g2d.fillOval(ville1EdgeX - 5, ville1EdgeY - 5, 10, 10);

			// Ajouter le point de fin
			g2d.fillOval(ville2EdgeX - 5, ville2EdgeY - 5, 10, 10);
		}
	}

	/**
	 * Classe interne pour gérer les événements de souris.
	 */
	private class GereSouris extends MouseAdapter
	{
		private Mine mineSelect = null;

		/**
		 * Gère l'événement lorsque la souris est pressée.
		 *
		 * @param e
		 * 		L'événement de souris.
		 */
		@Override
		public void mousePressed(MouseEvent e)
		{
			int posX = e.getX();
			int posY = e.getY();

			for (Mine mine : ctrlJeu.getMines())
			{
				int diameter = 30;
				int centerX = mine.getX();
				int centerY = mine.getY();
				int distX = Math.abs(e.getX() - mine.getX());
				int distY = Math.abs(e.getY() - mine.getY());
				double distance = Math.sqrt(distX * distX + distY * distY);

				if (distance <= diameter)
				{
					if (mine.getMinerai() == null || mine.getNom().equals("ROME"))
					{
						//Première seléction
						this.mineSelect = mine;
						System.out.println("Mine sélectionnée 1: " + mine);
					}
					else if (this.mineSelect != mine && this.mineSelect != null && ctrlJeu.mineEstAdjacent(
							this.mineSelect, mine) && mine.getMinerai() != null)
					{
						System.out.println("Mine sélectionnée 2: " + mine);

						ctrlJeu.possederMine(this.mineSelect);
						mine.enleverMinerai();

						for (Route routeD : ctrlJeu.getRoute(this.mineSelect))
						{
							for (Route routeA : ctrlJeu.getRoute(mine))
							{
								if (routeD == routeA)
								{
									ctrlJeu.approprierRoute(ctrlJeu.getJoueurTour(), routeD);

									System.out.println("Route prise par " + ctrlJeu.getTourJoueur() + " :" + routeD);

									ctrlJeu.majIHM();
								}
							}
						}
						System.out.println("Mine prise par " + ctrlJeu.getTourJoueur() + " :" + mine);

						ctrlJeu.changerTour();
						this.mineSelect = null;



					}
				}
			}

			ctrlJeu.majIHM();
		}

		/**
		 * Gère l'événement lorsque la souris est déplacée tout en étant pressée.
		 *
		 * @param e
		 * 		L'événement de souris.
		 */
		public void mouseDragged(MouseEvent e)
		{
		}

		/**
		 * Gère l'événement lorsque la souris est relâchée.
		 *
		 * @param e
		 * 		L'événement de souris.
		 */
		public void mouseReleased(MouseEvent e)
		{
			ctrlJeu.majIHM();
		}
	}
}
