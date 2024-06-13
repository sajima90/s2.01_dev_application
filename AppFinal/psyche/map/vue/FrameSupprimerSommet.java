package psyche.map.vue;

import psyche.map.ControleurMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameSupprimerSommet extends JFrame implements ActionListener
{
	private ControleurMap ctrlMap;

	private JTable                tblDonnes;
	private GrlDonneesModelSommet donnesTableau;
	private JButton 			  btnSupprimer;


	public FrameSupprimerSommet(ControleurMap ctrlMap)
	{

		JScrollPane spTableau;

		this.setTitle ("Suppression des mines");
		this.setSize  (600, 300	  );
		this.setLayout(new FlowLayout()   	  );
		this.getContentPane().setBackground(Color.gray);

		this.ctrlMap = ctrlMap;

		//Creation des composants
		this.donnesTableau = new GrlDonneesModelSommet(this.ctrlMap);
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

			this.ctrlMap.supprimerSommet(indice);
			this.tblDonnes.setModel(new GrlDonneesModelSommet(this.ctrlMap));
			this.ctrlMap.majIHM();
		}
	}
}
