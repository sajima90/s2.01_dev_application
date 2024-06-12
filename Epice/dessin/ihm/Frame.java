package dessin.ihm;


import dessin.Controleur;

import javax.swing.*;



public class Frame extends JFrame
{
	
	public Frame(Controleur ctrl)
	{

		this.setTitle("La route des épices");
		this.setSize(880, 487);
		this.setLocation(50, 50);

		// Création et ajout du Panel
		this.add(new Panel(ctrl));

		// Gestion de la fermeture de la fenêtre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setVisible(true);

	}

}