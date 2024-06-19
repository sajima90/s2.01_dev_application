package psyche.jeu.vue;

import psyche.jeu.ControleurJeu;
import psyche.jeu.metier.Joueur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FrameNom extends JFrame implements ActionListener
{
	private ControleurJeu ctrlJeu;


	private JButton btnCS;
	private JButton btnSA;

	private String premierJoueur;


	public FrameNom(ControleurJeu ctrlJeu)
	{

		this.setTitle("Choix des camps");
		this.setSize(500, 300);
		this.setLocation(250,250);
		this.setLayout(null);

		this.ctrlJeu = ctrlJeu;

		// CrÃ©ation des composants
		JLabel lblText = new JLabel("Joueur 1 choisit ton camp");
		lblText.setBounds(160, 50, 170, 50);

		this.btnCS = new JButton("Corporation Solaire");
		this.btnCS.setBounds(60, 120, 150, 50);
		this.btnCS.setForeground(Color.GREEN);

		this.btnSA = new JButton("Syndicat Astral");
		this.btnSA.setBounds(260, 120, 150, 50);
		this.btnSA.setForeground(Color.RED);

		// Ajout des composants au panel
		this.add(lblText);
		this.add(btnCS);
		this.add(btnSA);

		// Activation des composants
		this.btnCS.addActionListener(this);
		this.btnSA.addActionListener(this);


		// Fermeture
		this.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				if (ctrlJeu != null)
				{
					ctrlJeu.setVisible();
				}
				e.getWindow().setVisible(false);
			}
		});


		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnCS)
		{
			this.ctrlJeu.ajouterJoueur(new Joueur("CS"));
			this.ctrlJeu.setJoueur( new FrameJoueur(this.ctrlJeu.getJoueur(0), this.ctrlJeu) );
			this.premierJoueur = "CS";

			this.ctrlJeu.ajouterJoueur(new Joueur("SA"));
			this.ctrlJeu.setJoueur( new FrameJoueur(this.ctrlJeu.getJoueur(1), this.ctrlJeu ));

		}
		if (e.getSource() == this.btnSA)
		{
			this.ctrlJeu.ajouterJoueur(new Joueur("SA"));
			this.ctrlJeu.setJoueur( new FrameJoueur(this.ctrlJeu.getJoueur(0), this.ctrlJeu));
			this.premierJoueur = "SA";

			this.ctrlJeu.ajouterJoueur(new Joueur("CS"));
			this.ctrlJeu.setJoueur( new FrameJoueur(this.ctrlJeu.getJoueur(1), this.ctrlJeu));
		}

		this.setVisible(false);
		this.setResizable(false);
	}

	public String getPremierJoueur()
	{
		return this.premierJoueur;
	}

	public void setPremierJoueur(String nom)
	{
		this.premierJoueur = nom;
	}
}
