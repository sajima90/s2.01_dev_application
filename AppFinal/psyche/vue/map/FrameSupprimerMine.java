package psyche.vue.map;

import psyche.Controleur;
import psyche.ControleurMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameSupprimerMine extends JFrame implements ActionListener
{
	private ControleurMap ctrlMap;

	private JTable                tblDonnes;
	private GrlDonneesModelVille  donnesTableau;
	private JButton 			  btnSupprimer;


	public FrameSupprimerMine(ControleurMap ctrlMap)
	{

		JScrollPane spTableau;

		this.setTitle ("Suppression des mines");
		this.setSize  (600, 300	  );
		this.setLayout(new FlowLayout()   	  );
		this.getContentPane().setBackground(Color.gray);

		this.ctrlMap = ctrlMap;

		//Creation des composants
		this.donnesTableau = new GrlDonneesModelVille(this.ctrlMap);
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
			int indice = this.tblDonnes.getSelectedRow();

			this.ctrlMap.supprimerMine(indice);
			this.tblDonnes.setModel(new GrlDonneesModelVille(this.ctrlMap));
			this.ctrlMap.majIHM();
		}
	}
}
