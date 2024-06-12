package psyche.vue.map;

import psyche.Controleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameSupprimerMine extends JFrame implements ActionListener
{
	private Controleur ctrl;

	private JTable                tblDonnes;
	private GrlDonneesModelVille  donnesTableau;
	private JButton 			  btnSupprimer;


	public FrameSupprimerMine(Controleur ctrl)
	{

		JScrollPane spTableau;

		this.setTitle ("Suppression des mines");
		this.setSize  (600, 300	  );
		this.setLayout(new FlowLayout()   	  );
		this.getContentPane().setBackground(Color.gray);

		this.ctrl = ctrl;

		//Creation des composants
		this.donnesTableau = new GrlDonneesModelVille(this.ctrl);
		this.tblDonnes     = new JTable     (this.donnesTableau);
		spTableau          = new JScrollPane(this.tblDonnes	   );

		this.btnSupprimer  = new JButton("Supprimer");

		//Positionnement des composants
		this.add(btnSupprimer);
		this.add(spTableau   );

		//Activation des composants
		this.btnSupprimer.addActionListener(this);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnSupprimer)
		{
			int indice = this.tblDonnes.getSelectedColumn();
			this.ctrl.supprimerMine(indice);
//			this.ctrl.supprimerRoute(indice;
			this.tblDonnes.setModel(new GrlDonneesModelVille(this.ctrl));
			this.ctrl.majIHM();
		}
	}
}
