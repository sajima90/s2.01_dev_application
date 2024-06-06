/**
 * @author CAUVIN Pierre, AUBIN Montagne, DELPECHE Nicolas, GUELLE Clément
 * Cette classe gère les routes.
 */
package sae.vue;

import sae.Controleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FrameAjouterVille extends JFrame implements ActionListener
{
	private Controleur ctrl;

	private JPanel panelGauche;
	private JPanel panelDroite;

	private JTable                tblDonnes;
	private GrlDonneesModelVille  donnesTableau;

	private JButton    btnAjouter;

	private JTextField txtnom;
	private JTextField txtcordX;
	private JTextField txtcordY;

	private JLabel lblnom;
	private JLabel lblVisu;
	private JLabel lblcordX;
	private JLabel lblcordY;


	public FrameAjouterVille(Controleur ctrl)
	{

		this.setTitle("Ajouter Ville");
		this.setSize(600, 300);
		this.setLayout(new GridLayout(1,2,10,20));
		this.getContentPane().setBackground(Color.gray);

		this.ctrl  = ctrl;

		JScrollPane spTableau;

		/*-------------------------*/
		/* Création des composants */
		/*-------------------------*/

		Font font = new Font("Roboto Slab",Font.BOLD,10);

		this.panelGauche = new JPanel(new BorderLayout());
		this.panelDroite = new JPanel(new GridLayout(5,2,0,10));

		this.donnesTableau = new GrlDonneesModelVille(this.ctrl);
		this.tblDonnes     = new JTable(this.donnesTableau);
		this.tblDonnes.setFillsViewportHeight(true);

		spTableau = new JScrollPane(this.tblDonnes);

		this.lblVisu = new JLabel("Visualisation des villes");


		/* Création des Labels et leurs couleurs */
		this.lblnom = new JLabel("Nom      :");
		this.lblnom.setBackground(Color.lightGray);
		this.lblnom.setFont(new Font("Outfit", Font.BOLD, 12));
		this.lblnom.setOpaque(true);

		this.lblcordX = new JLabel("CoordX   :");
		this.lblcordX.setBackground(Color.lightGray);
		this.lblcordX.setFont(new Font("Outfit", Font.BOLD, 12));
		this.lblcordX.setOpaque(true);

		this.lblcordY = new JLabel("CoordY   :");
		this.lblcordY.setBackground(Color.lightGray);
		this.lblcordY.setFont(new Font("Outfit", Font.BOLD, 12));
		this.lblcordY.setOpaque(true);

		this.txtcordX = new JTextField();
		this.txtcordX.setFont(font);

		this.txtcordY = new JTextField();
		this.txtcordY.setFont(font);

		this.txtnom = new JTextField();
		this.txtnom.setFont(font);

		this.btnAjouter = new JButton("Ajouter");
		this.btnAjouter.setBackground(Color.WHITE);

		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/

		this.panelGauche.add(spTableau   , BorderLayout.CENTER);
		this.panelGauche.add(this.lblVisu, BorderLayout.SOUTH);

		this.panelDroite.add(this.lblnom);
		this.panelDroite.add(this.txtnom);

		this.panelDroite.add(this.lblcordX);
		this.panelDroite.add(this.txtcordX);

		this.panelDroite.add(this.lblcordY);
		this.panelDroite.add(this.txtcordY);

		this.panelDroite.add(new JLabel());
		this.panelDroite.add(this.btnAjouter);

		this.add(this.panelGauche);
		this.add(this.panelDroite);

		/*-------------------------------*/
		/*   Activation des composants   */
		/*-------------------------------*/
		this.btnAjouter.addActionListener(this);
		this.txtnom    .addActionListener(this);
		this.txtcordX  .addActionListener(this);
		this.txtcordY  .addActionListener(this);


		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnAjouter)
		{
			String nom   = this.txtnom  .getText();
			String cordX = this.txtcordX.getText();
			String cordY = this.txtcordY.getText();



			if (nom == null && cordX == null && cordY == null)
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

			this.ctrl.ajouterVille(nom, Integer.parseInt(cordX), Integer.parseInt(cordY));
			this.txtnom.setText("");
			this.txtcordX.setText("");
			this.txtcordY.setText("");
			this.ctrl.majIHM();

			this.majIHM();
		}
	}

	public void majIHM()
	{
		this.tblDonnes.setModel(new GrlDonneesModelVille(this.ctrl));
	}
}