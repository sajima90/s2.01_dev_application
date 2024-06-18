package psyche.scenario;

import javax.swing.*;
import javax.swing.plaf.ActionMapUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameScenario extends JFrame implements ActionListener
{
	private JButton btnSuivant;
	private JButton btnPredecent;
	private JTextField txtEtapes;

	private  JLabel lblEtapes;

	private JPanel panelScenario;

	private ControleurScenario ctrl;

	public FrameScenario(ControleurScenario ctrl)
	{
		this.ctrl = ctrl;

		this.setTitle("Scénario");

		this.setSize(300,300);
		this.setResizable(false);

		/*-------------------------*/
		/* Création des composants */
		/*-------------------------*/
		this.panelScenario = new JPanel();

		this.panelScenario.setLayout(new FlowLayout());

		this.btnSuivant = new JButton("Suivant");
		this.btnPredecent = new JButton("Précédent");
		this.txtEtapes = new JTextField("0", 10);

		/*-------------------------*/
		/* Position des composants */
		/*-------------------------*/
		this.panelScenario.add(this.btnPredecent);
		this.panelScenario.add(this.txtEtapes);
		this.panelScenario.add(this.btnSuivant);

		/*---------------------------*/
		/* Activation des composants */
		/*---------------------------*/
		this.btnPredecent.addActionListener(this);
		this.btnSuivant.addActionListener(this);

		this.add(this.panelScenario);
		this.setVisible(true);
		this.pack();
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnSuivant)
		{
			this.txtEtapes.setText((Integer.parseInt(this.txtEtapes.getText()) + 1) + "");
			ctrl.effectuerAction((Integer.parseInt(this.txtEtapes.getText())));
		}

		if (e.getSource() == btnPredecent)
		{
			this.txtEtapes.setText((Integer.parseInt(this.txtEtapes.getText()) - 1) + "");
			ctrl.effectuerAction((Integer.parseInt(this.txtEtapes.getText())));
		}
	}

}
