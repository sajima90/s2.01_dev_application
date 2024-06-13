/**
 * @author CAUVIN Pierre, AUBIN Montagne, DELPECHE Nicolas, GUELLE Clément
 * Cette classe gère les routes.
 */
package psyche.map.vue;

import psyche.map.ControleurMap;
import psyche.map.metier.Sommet;
import psyche.map.metier.Couleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

public class FrameModifierSommet extends JFrame implements ActionListener, ItemListener
{
	private ControleurMap ctrlMap;

	private boolean modificationComboBox;


	private JPanel panelGauche;
	private JPanel panelDroite;

	private JTable tblDonnes;
	private GrlDonneesModelSommet donnesTableau;

	private JButton btnModifier;

	private JTextField txtCoordX;
	private JTextField txtCoordY;

	private JLabel lblVisu;
	private JLabel lblcordX;
	private JLabel lblcordY;

	private JComboBox<String> jcbDeroulanteCouleur;
	private JComboBox<String> jcbDeroulantePoint;


	public FrameModifierSommet(ControleurMap ctrlMap)
	{

		this.setTitle("Modifier Sommet");
		this.setSize(600, 300);
		this.setLayout(new GridLayout(1, 2, 10, 20));
		this.getContentPane().setBackground(Color.gray);

		this.ctrlMap = ctrlMap;
		this.modificationComboBox = false;

		JScrollPane spTableau;

		/*-------------------------*/
		/* Création des composants */
		/*-------------------------*/

		Font font = new Font("Roboto Slab", Font.BOLD, 10);


		/*-------------------------------*/
		/*       Couleur des mines       */
		/*-------------------------------*/

		List<String> tabMenuDeroulantCouleur = new ArrayList<>();

		for ( Sommet sommet : this.ctrlMap.getSommets())
		{
			if ( !tabMenuDeroulantCouleur.contains(sommet.getCouleur().name()))
				tabMenuDeroulantCouleur.add(sommet.getCouleur().name());
		}

		String[] tabCouleur  = new String[tabMenuDeroulantCouleur.size()];
		tabCouleur           = tabMenuDeroulantCouleur.toArray(tabCouleur);

		/*-------------------------------*/
		/*         Point des mines       */
		/*-------------------------------*/

		List<String> tabMenuDeroulantPoint = new ArrayList<>();

		for ( Sommet sommet : this.ctrlMap.getSommets())
		{
			if ( tabCouleur[0].equals(sommet.getCouleur().name()))
			{
				tabMenuDeroulantPoint.add(String.valueOf(sommet.getPoint()));
			}
		}

		String[] tabPoint  = new String[tabMenuDeroulantPoint.size()];
		tabPoint           = tabMenuDeroulantPoint.toArray(tabPoint);


		this.jcbDeroulanteCouleur = new JComboBox<>(tabCouleur);
		this.jcbDeroulantePoint   = new JComboBox<>(tabPoint);

		this.panelGauche = new JPanel(new BorderLayout());
		this.panelDroite = new JPanel(new GridLayout(5, 2, 0, 10));

		this.donnesTableau = new GrlDonneesModelSommet(this.ctrlMap);
		this.tblDonnes     = new JTable(this.donnesTableau);
		this.tblDonnes.setFillsViewportHeight(true);

		spTableau = new JScrollPane(this.tblDonnes);

		this.lblVisu = new JLabel("Visualisation des Sommets");


		/* Création des Labels et leurs couleurs */

		this.lblcordX = new JLabel("CoordX   :");
		this.lblcordX.setBackground(Color.lightGray);
		this.lblcordX.setFont(new Font("Outfit", Font.BOLD, 12));

		this.lblcordY = new JLabel("CoordY   :");
		this.lblcordY.setBackground(Color.lightGray);
		this.lblcordY.setFont(new Font("Outfit", Font.BOLD, 12));

		this.txtCoordX = new JTextField();
		this.txtCoordX.setFont(font);

		this.txtCoordY = new JTextField();
		this.txtCoordY.setFont(font);

		this.btnModifier = new JButton("Modifier");
		this.btnModifier.setBackground(Color.WHITE);

		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/

		this.panelGauche.add(spTableau, BorderLayout.CENTER);
		this.panelGauche.add(this.lblVisu, BorderLayout.SOUTH);

		this.panelDroite.add(this.lblcordX);
		this.panelDroite.add(this.txtCoordX);

		this.panelDroite.add(this.lblcordY);
		this.panelDroite.add(this.txtCoordY);

		this.panelDroite.add(this.jcbDeroulanteCouleur);
		this.panelDroite.add(this.jcbDeroulantePoint);

		this.panelDroite.add(new JLabel());
		this.panelDroite.add(this.btnModifier);

		this.add(this.panelGauche);
		this.add(this.panelDroite);


		/*-------------------------------*/
		/*   Activation des composants   */
		/*-------------------------------*/

		this.jcbDeroulanteCouleur.addItemListener(this);
		this.jcbDeroulantePoint.addItemListener(this);

		this.btnModifier.addActionListener(this);
		this.txtCoordX  .addActionListener(this);
		this.txtCoordY  .addActionListener(this);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnModifier)
		{
			String cordX = this.txtCoordX.getText();
			String cordY = this.txtCoordY.getText();

			if (cordX == null && cordY == null)
				JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);

			try
			{
				cordX = String.valueOf(Integer.parseInt(cordX));
				cordY = String.valueOf(Integer.parseInt(cordY));
			}
			catch (NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(this, "Erreur pour la saisie des coordonnées", "Erreur", JOptionPane.ERROR_MESSAGE);
				return;
			}

			if ( this.jcbDeroulanteCouleur.getSelectedItem().equals("ROME") )
				this.ctrlMap.modifierSommet(Integer.parseInt(cordX), Integer.parseInt(cordY), this.ctrlMap.getSommet(this.ctrlMap.getCouleur(this.jcbDeroulanteCouleur.getSelectedItem().toString()),-1).getNom());
			else
				this.ctrlMap.modifierSommet(Integer.parseInt(cordX), Integer.parseInt(cordY), this.ctrlMap.getSommet(this.ctrlMap.getCouleur(this.jcbDeroulanteCouleur.getSelectedItem().toString()),
					Integer.parseInt((String) this.jcbDeroulantePoint.getSelectedItem())).getNom());

			{
				this.txtCoordX.setText("");
			}
			this.txtCoordY.setText("");
			this.ctrlMap.majIHM();

			this.majIHM();
		}
	}

	public void majIHM()
	{
		this.tblDonnes.setModel(new GrlDonneesModelSommet(this.ctrlMap));
	}

	public void itemStateChanged(ItemEvent e)
	{

		if (modificationComboBox || e.getStateChange() != ItemEvent.SELECTED || e.getItem() == null)
		{
			return;
		}

		if (e.getSource() == this.jcbDeroulanteCouleur)
		{

			modificationComboBox = true;

			try
			{
				// Suppression de tous les éléments existants dans les JComboBox
				this.jcbDeroulantePoint.removeAllItems();

				// Obtention des couleurs sélectionnées
				String couleur = (String) this.jcbDeroulanteCouleur.getSelectedItem();

				if ( !couleur.equals("ROME") )
				{
					// Parcours de la liste des sommets
					for (Sommet sommet : this.ctrlMap.getSommets())
					{
						String mineCouleur = sommet.getCouleur().name();
						String minePoint   = String.valueOf(sommet.getPoint());

						// Ajout des points aux JComboBox en fonction des couleurs sélectionnées
						if (couleur.equals(mineCouleur))
						{
							this.jcbDeroulantePoint.addItem(minePoint);
						}
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