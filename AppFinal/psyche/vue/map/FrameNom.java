package psyche.vue.map;

import psyche.Controleur;

import javax.swing.*;
import java.awt.*;

public class FrameNom extends JFrame
{
	private Controleur ctrl;
	private PanelNom   panelNom;

	public FrameNom(Controleur ctrl)
	{
		this.setTitle("Choix des camps");
		this.setLayout(new GridLayout(2,1,10,10));
		this.setSize(200,100);


		this.ctrl = ctrl;
		this.panelNom = new PanelNom();

		this.add(new JLabel("Choisisez votre camp : "));
		this.add(new JLabel());
		this.add(panelNom);

		this.setVisible(true);
	}
}
