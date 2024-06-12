package psyche.vue.map;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelNom extends JPanel implements ActionListener
{
	private JButton btnCS;
	private JButton btnSA;

	public PanelNom()
	{
		// Création des composants
		this.btnCS = new JButton("Corporation Solaire");
		this.btnSA = new JButton("Syndicat Astral"	  );

		// Ajout des composants au panel
		this.add(btnCS);
		this.add(btnSA);

		// Activation des composants
		this.btnCS.addActionListener(this);
		this.btnSA.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnCS)
		{
			//creation du joueur Corporation Solaire
			//puis creation du joueur 2 Syndicat Astral
		}
		if (e.getSource() == this.btnSA)
		{
			//creation du joueur Syndicat Astral
			//puis creation du joueur 2 Corporation Solaire
		}
	}

}
