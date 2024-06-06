/**
 * @author CAUVIN Pierre, AUBIN Montagne, DELPECHE Nicolas, GUELLE Clément
 * Cette classe gère les routes.
 */
package sae.vue;

import sae.Controleur;
import sae.metier.Ville;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

public class FrameAjouterRoute extends JFrame implements ActionListener, ItemListener
{
	/*-----------------*/
	/*    Donnees      */
	/*-----------------*/

	private JButton btnAjouter;

	private JPanel panelGauche;
	private JPanel panelDroite;

	private JTable                tblDonnes;
	private GrlDonneesModelRoute  donnesTableau;

	private JLabel depart;
	private JLabel arrive;
	private JLabel troncons;
	private JLabel lblVisu;
	
	private JComboBox<String> jcbDeroulanteArrive;
	private JComboBox<String> jcbDeroulanteDepart;

	private JTextField txttroncons;

	private Controleur ctrl;



	/*-----------------*/
	/*  Instruction    */
	/*-----------------*/

	public FrameAjouterRoute(Controleur ctrl)
	{
		this.setTitle("Ajouter route");
		this.setSize(600, 300);
		this.setLayout(new GridLayout(1,2,10,20));
		this.getContentPane().setBackground(Color.gray);

		this.setVisible(true);

		this.ctrl = ctrl;

		JScrollPane spTableau;

		/*-------------------------*/
		/* Création des composants */
		/*-------------------------*/


		this.panelGauche = new JPanel(new BorderLayout());
		this.panelDroite = new JPanel(new GridLayout(5,2,0,10));

		this.donnesTableau = new GrlDonneesModelRoute(this.ctrl);
		this.tblDonnes     = new JTable(this.donnesTableau);
		this.tblDonnes.setFillsViewportHeight(true);

		spTableau = new JScrollPane(this.tblDonnes);

		this.lblVisu = new JLabel("Visualisation des routes");


		List<String> tabMenuDeroulant = new ArrayList<>();

		for ( Ville ville : this.ctrl.getVilles())
		{
			tabMenuDeroulant.add(ville.getNom());
		}

		tabMenuDeroulant.add(0, "Menu des villes");

		String[] tabtest = new String[tabMenuDeroulant.size()];
		tabtest = tabMenuDeroulant.toArray(tabtest);


		this.jcbDeroulanteArrive = new JComboBox<>(tabtest);
		this.jcbDeroulanteDepart = new JComboBox<>(tabtest);

		this.depart    = new JLabel(String.format("%21s", "Ville départ       : "));
		this.depart.setBackground(Color.lightGray);
		this.depart.setOpaque(true);

		this.arrive    = new JLabel(String.format("%21s", "Ville arrivé       : "));
		this.arrive.setBackground(Color.lightGray);
		this.arrive.setOpaque(true);

		this.troncons  = new JLabel(String.format("%21s", "Nombre de tronçons : "));
		this.troncons.setBackground(Color.lightGray);
		this.troncons.setOpaque(true);

		this.txttroncons = new JTextField(20);
		this.txttroncons.setEnabled(false);
		this.txttroncons.setBackground(new Color(0, 0, 0, 65));

		this.btnAjouter =  new JButton("Ajouter");
		this.btnAjouter.setBackground(Color.WHITE);


		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/

		this.panelGauche.add(spTableau   , BorderLayout.CENTER);
		this.panelGauche.add(this.lblVisu, BorderLayout.SOUTH);

		this.panelDroite.add(this.depart);
		this.panelDroite.add(this.jcbDeroulanteDepart);

		this.panelDroite.add(this.arrive);
		this.panelDroite.add(this.jcbDeroulanteArrive);

		this.panelDroite.add(this.troncons);
		this.panelDroite.add(this.txttroncons);

		this.panelDroite.add(new JLabel());
		this.panelDroite.add(this.btnAjouter);

		this.add(this.panelGauche);
		this.add(this.panelDroite);

		/*-------------------------------*/
		/*   Activation des composants   */
		/*-------------------------------*/

		this.btnAjouter .addActionListener(this);
		this.txttroncons.addActionListener(this);

		this.jcbDeroulanteArrive.addItemListener(this);
		this.jcbDeroulanteDepart.addItemListener(this);

	}

	public void actionPerformed(ActionEvent e)
	{
		String troncons = null;
		
		if(e.getSource() == this.btnAjouter)
		{
			troncons = this.txttroncons.getText();

			try
			{
				troncons = String.valueOf(Integer.parseInt(troncons));
			}
			catch (NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(this, "Erreur de saisie pour les tronçons", "Erreur", JOptionPane.ERROR_MESSAGE);
				return;
			}

			if ( this.jcbDeroulanteDepart.getSelectedItem() != null && this.jcbDeroulanteArrive.getSelectedItem() != null && troncons != null )
			{

				if (this.jcbDeroulanteDepart.getSelectedItem() != this.jcbDeroulanteArrive.getSelectedItem())
				{

					this.ctrl.ajouterRoute( this.ctrl.getVilleParNom((String) this.jcbDeroulanteDepart.getSelectedItem()), this.ctrl.getVilleParNom((String) this.jcbDeroulanteArrive.getSelectedItem()), Integer.parseInt(troncons));
					this.tblDonnes.setModel(new GrlDonneesModelRoute(this.ctrl));
				}
				else
					JOptionPane.showMessageDialog(this, "Ne pas selectionner deux fois la même ville", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void itemStateChanged(ItemEvent e)
	{
		if ( e.getSource() == this.jcbDeroulanteDepart || e.getSource() == this.jcbDeroulanteArrive )
		{
			if (this.jcbDeroulanteDepart.getSelectedIndex() != 0 && this.jcbDeroulanteArrive.getSelectedIndex() != 0)
			{
				this.txttroncons.setEnabled(true);
				this.txttroncons.setBackground((Color.WHITE));
			}
			else
			{
				this.txttroncons.setText("");
				this.txttroncons.setEnabled(false);
				this.txttroncons.setBackground((new Color(0, 0, 0, 65)));
			}
		}

	}
}

