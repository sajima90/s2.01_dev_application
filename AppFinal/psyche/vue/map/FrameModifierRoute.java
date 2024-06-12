package psyche.vue.map;

import psyche.Controleur;
import psyche.metier.map.Mine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class FrameModifierRoute extends JFrame implements ActionListener, ItemListener
{
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

	private String[] tabPointMine1;
	private String[] tabPointMine2;
	private String[] tabPointMine3;


	private JTextField txttroncons;

	private Controleur ctrl;


	public FrameModifierRoute(Controleur ctrl)
	{
		System.out.println("Test Ouvrir modifier Route");
		this.setTitle("Modifier route");
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
		this.panelDroite = new JPanel(new GridLayout(3,4,0,10));

		this.donnesTableau = new GrlDonneesModelRoute(this.ctrl);
		this.tblDonnes     = new JTable(this.donnesTableau);
		this.tblDonnes.setFillsViewportHeight(true);

		spTableau = new JScrollPane(this.tblDonnes);

		this.lblVisu = new JLabel("Visualisation des routes");

		/*-------------------------------*/
		/*         Couleur mine          */
		/*-------------------------------*/

		List<String> tabMenuDeroulantCouleur = new ArrayList<>();

		for ( Mine mine : this.ctrl.getMines())
		{
			tabMenuDeroulantCouleur.add(mine.getCouleur().name());
		}


		String[] tabCouleur  = new String[tabMenuDeroulantCouleur.size()];
		tabCouleur           = tabMenuDeroulantCouleur.toArray(tabCouleur);


		/*-------------------------------*/
		/*         Point des mines       */
		/*-------------------------------*/
		tabPointMine1 = new String[] {"1","2","3","4","5"};	//or  , rouge , marron
		tabPointMine2 = new String[] {"2","3","4","6","8"};	//bleu, vert
		tabPointMine3 = new String[] {"0","1","2","3","4"}; //gris



		this.jcbDeroulanteDepartCouleur = new JComboBox<>(tabCouleur  );
		this.jcbDeroulanteDepartPoint   = new JComboBox<>(tabPointMine1);

		this.jcbDeroulanteArriveCouleur = new JComboBox<>(tabCouleur  );
		this.jcbDeroulanteArrivePoint   = new JComboBox<>(tabPointMine1);

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


		this.txttroncons = new JTextField(20);
		this.txttroncons.setEnabled(true);
		this.txttroncons.setBackground(new Color(0, 0, 0, 65));
		this.txttroncons.setOpaque(true);
		this.txttroncons.setFont( new Font("Roboto Slab", Font.BOLD, 10));


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
		this.panelDroite.add(this.txttroncons);

		this.panelDroite.add(new JLabel());
		this.panelDroite.add(this.btnModifier);

		this.add(this.panelGauche);
		this.add(this.panelDroite);

		/*-------------------------------*/
		/*   Activation des composants   */
		/*-------------------------------*/

		this.btnModifier .addActionListener(this);
		this.txttroncons.addActionListener(this);

		this.jcbDeroulanteDepartPoint  .addItemListener(this);
		this.jcbDeroulanteDepartCouleur.addItemListener(this);
		this.jcbDeroulanteArrivePoint  .addItemListener(this);
		this.jcbDeroulanteArriveCouleur.addItemListener(this);



	}
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnModifier)
		{
			int mineArr = -1;
			int mineDep = -1;

			if (this.txttroncons.getText() != null)
			{
				for(int i = 0; i < this.ctrl.getMines().size(); i++)
				{
					if (this.ctrl.getMines().get(i).getCouleur().name()
							.equals(this.couleurDepart.getText()) && this.ctrl.getMines().get(i)
							.getPoint() == Integer.parseInt(this.pointDepart.getText()))
					{
							mineArr = i;
					}
					if (this.ctrl.getMines().get(i).getCouleur().name()
							.equals(this.couleurArrive.getText()) && this.ctrl.getMines().get(i)
							.getPoint() == Integer.parseInt(this.pointArrive.getText()))
					{
							mineDep = i;
					}
				}
			}

			if (mineDep != -1 && mineArr != -1)
				this.ctrl.modifierRoute(this.ctrl.getMines().get(mineDep), this.ctrl.getMines().get(mineArr), Integer.parseInt(this.txttroncons.getText()));
		}

	}

	public void itemStateChanged(ItemEvent e)
	{

	}
}
