package psyche.jeu.vue;
import psyche.jeu.ControleurJeu;
import psyche.jeu.metier.Couleur;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PanelTabScore extends JPanel
{
	private JLabel lblMineJaune;
	private JLabel lblMineBleu;
	private JLabel lblMineGris;
	private JLabel lblMineVert;
	private JLabel lblMineRouge;
	private JLabel lblMineMarron;

	private ControleurJeu ctrlJeu;


	public PanelTabScore(ControleurJeu ctrlJeu)
	{
		this.setLayout(new GridLayout(22, 3));
		this.ctrlJeu = ctrlJeu;

		// Chargement des images depuis le dossier theme/transparent
		ImageIcon mineJaune = new ImageIcon("../psyche/theme/images/transparent/Mine_Jaune_lite.png");
		ImageIcon mineBleu = new ImageIcon("../psyche/theme/images/transparent/Mine_Bleu_lite.png");
		ImageIcon mineGris = new ImageIcon("../psyche/theme/images/transparent/Mine_Gris_lite.png");
		ImageIcon mineVert = new ImageIcon("../psyche/theme/images/transparent/Mine_Vert_lite.png");
		ImageIcon mineRouge = new ImageIcon("../psyche/theme/images/transparent/Mine_Rouge_lite.png");
		ImageIcon mineMarron = new ImageIcon("../psyche/theme/images/transparent/Mine_Marron_lite.png");

		// Chargement des images pour les labels "Corporation Solaire" et "Syndicat Astral"
		ImageIcon corpSolImage = new ImageIcon("../psyche/theme/images/pion_joueur_1.png");
		ImageIcon syndAstralImage = new ImageIcon("../psyche/theme/images/pion_joueur_2.png");



		// Initialisation des JLabels avec les images chargées
		this.lblMineJaune  = createLabeledIcon(mineJaune);
		this.lblMineJaune.setOpaque(true);
		this.lblMineBleu   = createLabeledIcon(mineBleu);
		this.lblMineBleu.setOpaque(true);
		this.lblMineGris   = createLabeledIcon(mineGris);
		this.lblMineGris.setOpaque(true);
		this.lblMineVert   = createLabeledIcon(mineVert);
		this.lblMineVert.setOpaque(true);
		this.lblMineRouge  = createLabeledIcon(mineRouge);
		this.lblMineRouge.setOpaque(true);
		this.lblMineMarron = createLabeledIcon(mineMarron);
		this.lblMineMarron.setOpaque(true);

		// Création des labels avec texte et icône
		JLabel corpSolLabel = createLabeledIconWithText(corpSolImage, "Corporation Solaire");
		JLabel syndAstralLabel = createLabeledIconWithText(syndAstralImage, "Syndicat Astral");


		// Ajout des composants dans le JPanel avec des bordures pour chaque cellule
		this.addEmptyLabel();
		this.add(corpSolLabel);
		this.add(syndAstralLabel);

		this.addEmptyLabel();
		this.addEmptyLabel();
		this.addEmptyLabel();

		this.addLabeledComponent("Points des Mines", new Color(232, 221, 36));
		this.addLabeledComponent(" ",new Color(232, 221, 36));
		this.addLabeledComponent(" ",new Color(232, 221, 36));

		this.add(this.lblMineJaune).setPreferredSize(new Dimension(500,500));
		this.addLabeledComponent(" " + this.ctrlJeu.calculerScoreMineJ1(Couleur.JAUNE));
		this.addLabeledComponent(" " + this.ctrlJeu.calculerScoreMineJ2(Couleur.JAUNE));

		this.add(this.lblMineBleu);
		this.addLabeledComponent(" " + this.ctrlJeu.calculerScoreMineJ1(Couleur.BLEU_CLAIR));
		this.addLabeledComponent(" " + this.ctrlJeu.calculerScoreMineJ2(Couleur.BLEU_CLAIR));

		this.add(this.lblMineGris);
		this.addLabeledComponent(" " + this.ctrlJeu.calculerScoreMineJ1(Couleur.GRIS));
		this.addLabeledComponent(" " + this.ctrlJeu.calculerScoreMineJ2(Couleur.GRIS));

		this.add(this.lblMineVert);
		this.addLabeledComponent(" " + this.ctrlJeu.calculerScoreMineJ1(Couleur.VERT));
		this.addLabeledComponent(" " + this.ctrlJeu.calculerScoreMineJ2(Couleur.VERT));

		this.add(this.lblMineRouge);
		this.addLabeledComponent(" " + this.ctrlJeu.calculerScoreMineJ1(Couleur.ROUGE));
		this.addLabeledComponent(" " + this.ctrlJeu.calculerScoreMineJ2(Couleur.ROUGE));

		this.add(this.lblMineMarron);
		this.addLabeledComponent(" " + this.ctrlJeu.calculerScoreMineJ1(Couleur.MARRON));
		this.addLabeledComponent(" " + this.ctrlJeu.calculerScoreMineJ2(Couleur.MARRON));

		this.addLabeledComponent("S/Total", new Color(232, 221, 36));
		this.addLabeledComponent(" " + this.ctrlJeu.calculerScoreMinesTotaleJ1(),new Color(232, 221, 36));
		this.addLabeledComponent(" " + this.ctrlJeu.calculerScoreMinesTotaleJ2(),new Color(232, 221, 36));

		this.addEmptyLabel();
		this.addEmptyLabel();
		this.addEmptyLabel();

		this.addLabeledComponent("Plateau Individuel" ,new Color(232, 221, 36));
		this.addLabeledComponent(" ",new Color(232, 221, 36));
		this.addLabeledComponent(" ",new Color(232, 221, 36));

		this.addLabeledComponent("Score Pièces");
		this.addLabeledComponent(" " + this.ctrlJeu.calculerScorePiece1());
		this.addLabeledComponent(" " + this.ctrlJeu.calculerScorePiece2());

		this.addLabeledComponent("Score des Colonnes");
		this.addLabeledComponent(" " + this.ctrlJeu.getPointsColonnesJ1());
		this.addLabeledComponent(" " + this.ctrlJeu.getPointsColonnesJ2());

		this.addLabeledComponent("Score des Lignes");
		this.addLabeledComponent(" " + this.ctrlJeu.getPointsLignesJ1());
		this.addLabeledComponent(" " + this.ctrlJeu.getPointsLignesJ2());

		this.addLabeledComponent("S/Totale",new Color(232, 221, 36));
		this.addLabeledComponent(" " + (this.ctrlJeu.calculerScorePiece1() + this.ctrlJeu.calculerScoreMineraiJ1()),new Color(232, 221, 36));
		this.addLabeledComponent(" "+ (this.ctrlJeu.calculerScorePiece2() + this.ctrlJeu.calculerScoreMineraiJ2()),new Color(232, 221, 36));

		this.addEmptyLabel();
		this.addEmptyLabel();
		this.addEmptyLabel();

		this.addLabeledComponent("Jetons Possession restants");
		this.addLabeledComponent(" " + this.ctrlJeu.getJetonPossessionJ1());
		this.addLabeledComponent(" " + this.ctrlJeu.getJetonPossessionJ2());

		this.addLabeledComponent("Bonus",new Color(232, 221, 36));
		this.addLabeledComponent(" " + this.ctrlJeu.pointBonusJ1(),new Color(232, 221, 36));
		this.addLabeledComponent(" " + this.ctrlJeu.pointBonusJ2(),new Color(232, 221, 36));

		this.addEmptyLabel();
		this.addEmptyLabel();
		this.addEmptyLabel();

		this.addLabeledComponent("Total",new Color(232, 183, 36));
		this.addLabeledComponent(" " + this.ctrlJeu.scoreTotalJ1(),new Color(232, 183, 36));
		this.addLabeledComponent(" " + this.ctrlJeu.scoreTotalJ2(),new Color(232, 183, 36));
	}

	private JLabel createLabeledIcon(ImageIcon icon)
	{
		JLabel label = new JLabel();
		label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		label.setIcon(icon);
		label.setOpaque(true);

		return label;
	}

	private JLabel createLabeledIconWithText(ImageIcon icon, String text)
	{
		JLabel label = new JLabel(text);
		label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		label.setIcon(icon);
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.BOTTOM);
		label.setOpaque(true);
		return label;
	}

	private void addLabeledComponent(String text)
	{
		addLabeledComponent(text, null);
	}

	private void addLabeledComponent(String text, Color backgroundColor)
	{
		JLabel label = new JLabel(text);
		label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		if (backgroundColor != null) {
			label.setBackground(backgroundColor);
			label.setOpaque(true);
		}
		this.add(label);
	}

	private void addEmptyLabel()
	{
		JLabel emptyLabel = new JLabel();
		emptyLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		this.add(emptyLabel);
	}
}
