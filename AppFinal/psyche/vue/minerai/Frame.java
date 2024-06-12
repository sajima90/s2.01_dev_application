package psyche.vue.minerai;

import psyche.Controleur;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame
{
	private JPanel panelHaut   = new JPanel();
	private JPanel panelDroit  = new JPanel();
	private JPanel panelBas    = new JPanel();
	private JPanel panelGauche = new JPanel();
	
	public Frame(Controleur ctrl)
	{

		this.setTitle("La route des épices");
		this.setSize(1200, 599);
		this.setLocation(50, 50);
		this.setLayout(new BorderLayout());

//		this.add(new Panel(ctrl));


		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setVisible(true);

		for (int i = 1; i <= 25; i++)
		{
			JButton bouton = new Bouton("" + i);
			panelHaut.add(bouton);
		}

		// Création des boutons pour le panneau droit
		for (int i = 26; i <= 50; i++)
		{
			JButton bouton = new Bouton("" + i);
			panelDroit.add(bouton);
		}

		// Création des boutons pour le panneau bas
		for (int i = 51; i <= 75; i++)
		{
			JButton bouton = new Bouton("" + i);
			panelBas.add(bouton);
		}

		// Création des boutons pour le panneau gauche
		for (int i = 51; i <= 75; i++)
		{
			JButton bouton = new Bouton("" + i);
			panelGauche.add(bouton);
		}

		this.add(panelHaut, BorderLayout.NORTH);
		this.add(panelDroit, BorderLayout.EAST);
		this.add(panelBas, BorderLayout.SOUTH);
		this.add(panelGauche, BorderLayout.WEST);

	}

}