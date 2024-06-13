package psyche.vue.map;

import psyche.Controleur;
import psyche.ControleurMap;
import psyche.metier.map.Mine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class FrameModifierRoute extends JFrame implements ActionListener, ItemListener
{
	/*-----------------*/
	/*    Donnees      */
	/*-----------------*/

	private boolean modificationComboBox;

	private JButton btnModifier;

	private JPanel panelGauche;
	private JPanel panelDroite;

	private JTable                tblDonnes;
	private GrlDonneesModelRoute  donnesTableau;

	private JLabel couleurDepart;
	private JLabel pointDepart;
	private JLabel couleurArrive;
	private JLabel pointArrive;
	private JLabel troncons;
	private JLabel lblVisu;

	private JComboBox<String> jcbDeroulanteDepartCouleur;
	private JComboBox<String> jcbDeroulanteDepartPoint;
	private JComboBox<String> jcbDeroulanteArriveCouleur;
	private JComboBox<String> jcbDeroulanteArrivePoint;
	private JComboBox<String> jcbDeroulanteTroncons;

	private ControleurMap ctrlMap;



	/*-----------------*/
	/*  Instruction    */
	/*-----------------*/

	public FrameModifierRoute(ControleurMap ctrlMap)
	{
		this.setTitle("Modifier route");
		this.setSize(600, 300);
		this.setLayout(new GridLayout(1,2,10,20));
		this.getContentPane().setBackground(Color.gray);

		this.setVisible(true);

		this.ctrlMap = ctrlMap;
		this.modificationComboBox = false;

		JScrollPane spTableau;

		/*-------------------------*/
		/* Création des composants */
		/*-------------------------*/


		this.panelGauche = new JPanel(new BorderLayout());
		this.panelDroite = new JPanel(new GridLayout(3,4,0,10));

		this.donnesTableau = new GrlDonneesModelRoute(this.ctrlMap);
		this.tblDonnes     = new JTable(this.donnesTableau);
		this.tblDonnes.setFillsViewportHeight(true);

		spTableau = new JScrollPane(this.tblDonnes);

		this.lblVisu = new JLabel("Visualisation des routes");

		/*-------------------------------*/
		/*         Couleur mine          */
		/*-------------------------------*/

		List<String> tabMenuDeroulantCouleur = new ArrayList<>();

		for ( Mine mine : this.ctrlMap.getMines())
		{
			if ( !tabMenuDeroulantCouleur.contains(mine.getCouleur().name()))
				tabMenuDeroulantCouleur.add(mine.getCouleur().name());
		}

		String[] tabCouleur  = new String[tabMenuDeroulantCouleur.size()];
		tabCouleur           = tabMenuDeroulantCouleur.toArray(tabCouleur);


		/*-------------------------------*/
		/*         Point des mines       */
		/*-------------------------------*/

		List<String> tabMenuDeroulantPoint = new ArrayList<>();

		for ( Mine mine : this.ctrlMap.getMines())
		{
			if ( tabCouleur[0].equals(mine.getCouleur().name()))
			{
				tabMenuDeroulantPoint.add(String.valueOf(mine.getPoint()));
			}
		}

		String[] tabPoint  = new String[tabMenuDeroulantPoint.size()];
		tabPoint           = tabMenuDeroulantPoint.toArray(tabPoint);



		this.jcbDeroulanteDepartCouleur = new JComboBox<>(tabCouleur);
		this.jcbDeroulanteDepartPoint   = new JComboBox<>(tabPoint);

		this.jcbDeroulanteArriveCouleur = new JComboBox<>(tabCouleur);
		this.jcbDeroulanteArrivePoint   = new JComboBox<>(tabPoint);


		String[] tabTroncon        = new String[] { "1", "2" };
		this.jcbDeroulanteTroncons = new JComboBox<>(tabTroncon);


		this.couleurDepart	= new JLabel(String.format("%21s", "Couleur mine de départ       : "));
		this.couleurDepart.setBackground(Color.lightGray);
		this.couleurDepart.setOpaque(true);

		this.pointDepart    = new JLabel(String.format("%21s", "Point mine de départ      : "));
		this.pointDepart.setBackground(Color.lightGray);
		this.pointDepart.setOpaque(true);

		this.couleurArrive 	= new JLabel(String.format("%21s", "Couleur mine de arrivée     : "));
		this.couleurArrive.setBackground(Color.lightGray);
		this.couleurArrive.setOpaque(true);

		this.pointArrive	= new JLabel(String.format("%21s", "Point mine de arrivée     : "));
		this.pointArrive.setBackground(Color.lightGray);
		this.pointArrive.setOpaque(true);


		this.troncons  = new JLabel(String.format("%21s", "Nombre de tronçons : "));
		this.troncons.setBackground(Color.lightGray);
		this.troncons.setOpaque(true);


		this.btnModifier =  new JButton("Modifier");
		this.btnModifier.setBackground(Color.WHITE);


		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/

		this.panelGauche.add(spTableau   , BorderLayout.CENTER);
		this.panelGauche.add(this.lblVisu, BorderLayout.SOUTH);

		this.panelDroite.add(this.couleurDepart);
		this.panelDroite.add(this.jcbDeroulanteDepartCouleur);

		this.panelDroite.add(this.pointDepart);
		this.panelDroite.add(this.jcbDeroulanteDepartPoint);

		this.panelDroite.add(this.couleurArrive);
		this.panelDroite.add(this.jcbDeroulanteArriveCouleur);

		this.panelDroite.add(this.pointArrive);
		this.panelDroite.add(this.jcbDeroulanteArrivePoint);

		this.panelDroite.add(this.troncons);
		this.panelDroite.add(this.jcbDeroulanteTroncons);

		this.panelDroite.add(new JLabel());
		this.panelDroite.add(this.btnModifier);

		this.add(this.panelGauche);
		this.add(this.panelDroite);

		/*-------------------------------*/
		/*   Activation des composants   */
		/*-------------------------------*/

		this.btnModifier .addActionListener(this);

		this.jcbDeroulanteDepartPoint  .addItemListener(this);
		this.jcbDeroulanteDepartCouleur.addItemListener(this);
		this.jcbDeroulanteArrivePoint  .addItemListener(this);
		this.jcbDeroulanteArriveCouleur.addItemListener(this);
		this.jcbDeroulanteTroncons     .addItemListener(this);


	}

	public void actionPerformed(ActionEvent e)
	{

		if(e.getSource() == this.btnModifier)
		{

			Mine mineDepart = this.ctrlMap.getMineParMineraiPoint( this.ctrlMap.getCouleur((String) this.jcbDeroulanteDepartCouleur.getSelectedItem()), Integer.parseInt(this.jcbDeroulanteDepartPoint.getSelectedItem().toString()));
			Mine mineArrive = this.ctrlMap.getMineParMineraiPoint( this.ctrlMap.getCouleur((String) this.jcbDeroulanteArriveCouleur.getSelectedItem()), Integer.parseInt(this.jcbDeroulanteArrivePoint.getSelectedItem().toString()));

			if ( this.ctrlMap.modifierRoute(mineDepart, mineArrive, Integer.parseInt(this.jcbDeroulanteTroncons.getSelectedItem().toString())) )
			{
				System.out.println("Modif");
				this.tblDonnes.setModel(new GrlDonneesModelRoute(this.ctrlMap));
			}
			else
				JOptionPane.showMessageDialog(this, "La route n'existe pas", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void itemStateChanged(ItemEvent e)
	{
		if (modificationComboBox || e.getStateChange() != ItemEvent.SELECTED || e.getItem() == null)
		{
			return;
		}

		if (e.getSource() == this.jcbDeroulanteDepartCouleur || e.getSource() == this.jcbDeroulanteArriveCouleur )
		{
			modificationComboBox = true;

			try
			{
				// Suppression de tous les éléments existants dans les JComboBox
				this.jcbDeroulanteDepartPoint.removeAllItems();
				this.jcbDeroulanteArrivePoint.removeAllItems();

				// Obtention des couleurs sélectionnées
				String couleurDepart  = (String) this.jcbDeroulanteDepartCouleur.getSelectedItem();
				String couleurArrivee = (String) this.jcbDeroulanteArriveCouleur.getSelectedItem();

				// Parcours de la liste des mines
				for (Mine mine : this.ctrlMap.getMines())
				{
					String mineCouleur = mine.getCouleur().name();
					String minePoint   = String.valueOf(mine.getPoint());

					// Ajout des points aux JComboBox en fonction des couleurs sélectionnées
					if (couleurDepart.equals(mineCouleur))
					{
						this.jcbDeroulanteDepartPoint.addItem(minePoint);
					}
					if (couleurArrivee.equals(mineCouleur))
					{
						this.jcbDeroulanteArrivePoint.addItem(minePoint);
					}
				}
			}
			finally
			{
				modificationComboBox = false;
			}
		}

	}
}
