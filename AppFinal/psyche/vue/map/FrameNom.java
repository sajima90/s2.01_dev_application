package psyche.vue.map;

import psyche.Controleur;
import psyche.ControleurMap;

import javax.swing.*;
import java.awt.*;

public class FrameNom extends JFrame
{
	private ControleurMap ctrlMap;
	private PanelNom   panelNom;

	public FrameNom(ControleurMap ctrlMap)
	{
		this.setTitle("Choix des camps");
		this.setLayout(new GridLayout(2,1,10,10));
		this.setSize(200,100);


		this.ctrlMap = ctrlMap;
		this.panelNom = new PanelNom();

		this.add(new JLabel("Choisisez votre camp : "));
		this.add(new JLabel());
		this.add(panelNom);

		this.setVisible(true);
	}
}
