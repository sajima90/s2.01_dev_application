package psyche.jeu.vue;

import psyche.jeu.ControleurJeu;
import psyche.jeu.metier.Mine;
import psyche.jeu.metier.Minerai;
import psyche.jeu.metier.Piece;
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
//		super.paintComponent(g);


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
					g.drawImage(getToolkit().getImage("../psyche/theme/images/transparent/" + mine.getCouleur().getLienImage() + "_clair.png"), mine.getX() - 10, mine.getY() - 10, rectWidth, rectHeight, this);
				}
				else
				{
					g.drawImage(getToolkit().getImage("../psyche/theme/images/transparent/" + mine.getCouleur().getLienImage() + ".png"), mine.getX() - 10, mine.getY() - 10, rectWidth, rectHeight, this);
				}

				g2d.drawString(String.valueOf(mine.getPoint()), mine.getX() + 8, mine.getY() + 12);

				if (!mine.estPrise() && mine.getJeton() != null)
				{
					// Add null check here
					if (mine.getJeton().getType() instanceof Minerai)
					{
						Minerai m = (Minerai) mine.getJeton().getType();
						g.drawImage(getToolkit().getImage("../psyche/theme/images/ressources/" + m.getLienImage()), mine.getX(), mine.getY() + 30, 20, 20, this);
					}
					if (mine.getJeton().getType() instanceof Piece)
					{
						Piece m = (Piece) mine.getJeton().getType();
						g.drawImage(getToolkit().getImage("../psyche/theme/images/ressources/" + m.name() + ".png"), mine.getX(), mine.getY() + 30, 20, 20, this);
					}
				}
			}
			else
			{
				g.drawImage(getToolkit().getImage("../psyche/theme/images/transparent/" + mine.getCouleur().getLienImage() + ".png"), mine.getX() - 15, mine.getY() - 5, 51, 55, this);
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

			}



			// Ajouter le point de début
			g2d.fillOval(ville1EdgeX - 5, ville1EdgeY - 5, 10, 10);

			// Ajouter le point de fin
			g2d.fillOval(ville2EdgeX - 5, ville2EdgeY - 5, 10, 10);

			if (route.getProprietaire() != null)
			{
				// Determine the icon based on the player
				String iconPath = route.getProprietaire().getNom().equals("SA") ? "../psyche/theme/images/pion_joueur_2.png" : "../psyche/theme/images/pion_joueur_1.png";
				Image icon = getToolkit().getImage(iconPath);

				// Calculate the middle point of the route
				int midPointX = (ville1EdgeX + ville2EdgeX) / 2;
				int midPointY = (ville1EdgeY + ville2EdgeY) / 2;

				// Draw the icon in the middle of the route
				g.drawImage(icon, midPointX - 10, midPointY - 10, 20, 20, this);

				// If the route has two sections, draw another icon
				if (route.getTroncons() > 1) {
					// Calculate the quarter point of the route
					int quarterPointX = midPointX / 2;
					int quarterPointY = midPointY / 2;

					// Draw the icon at the quarter point of the route
					g.drawImage(icon, quarterPointX - 10, quarterPointY - 10, 20, 20, this);
				}
			}





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
					if (mine.getJeton() == null || mine.getNom().equals("ROME") && this.mineSelect == null)
					{
						//Première seléction
						this.mineSelect = mine;
						System.out.println("Mine sélectionnée 1: " + mine + mine.estPrise());
					}
					else if (this.mineSelect != mine && ctrlJeu.mineEstAdjacent(this.mineSelect, mine) && !mine.getNom().equals("ROME"))
					{
						System.out.println("Mine sélectionnée 2: " + mine  + mine.estPrise());


						for (Route routeD : ctrlJeu.getRoute(this.mineSelect))
						{
							for (Route routeA : ctrlJeu.getRoute(mine))
							{
								if (routeD == routeA)
								{
									ctrlJeu.approprierRoute(ctrlJeu.getJoueurTour(), routeD);

									System.out.println("Route prise par " + ctrlJeu.getTourJoueur() + " :" + routeD);
									ctrlJeu.setProprietaireRoute(ctrlJeu.getJoueurTour(), routeD);
									ctrlJeu.setProprietaireRoute(ctrlJeu.getJoueurTour(), routeA);


								}
							}
						}
						System.out.println("Mine prise par " + ctrlJeu.getTourJoueur() + " :" + mine);

						ctrlJeu.possederMine(mine);

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
			ctrlJeu.majIHM();
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
