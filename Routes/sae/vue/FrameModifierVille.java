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

public class FrameModifierVille extends JFrame implements ActionListener, ItemListener
{
	private Controleur ctrl;

	private JLabel     nom;
	private JLabel     coordX;
	private JLabel     coordY;

	private JTextField txtNom;
	private JTextField txtCoordX;
	private JTextField txtCoordY;

	private JButton btnModifier;

	private JComboBox<String> jcbDeroulante;

	public FrameModifierVille(Controleur ctrl)
	{

		this.ctrl = ctrl;

		this.setTitle("Modifier Ville");
		this.setSize(300, 200);
		this.setLocation(150, 150);


		this.setVisible(true);


		this.setLayout(new GridLayout(5, 2, 0, 10));



		/* ----------------------------------  */
		/*       CRÉATION DES COMPOSANTS       */
		/* ----------------------------------  */


		List<String> tabMenuDeroulant = new ArrayList<>();

		for ( Ville ville : this.ctrl.getVilles())
		{
			tabMenuDeroulant.add(ville.getNom());
		}

		tabMenuDeroulant.add(0, "Menu des villes");

		String[] tabtest = new String[tabMenuDeroulant.size()];
		tabtest = tabMenuDeroulant.toArray(tabtest);


		this.jcbDeroulante = new JComboBox<>(tabtest);

		this.nom       = new JLabel("Nom     : ");
		this.nom.setBackground(Color.lightGray);
		this.nom.setOpaque(true);

		this.coordX    = new JLabel("Coord X : ");
		this.coordX.setBackground(Color.lightGray);
		this.coordX.setOpaque(true);

		this.coordY    = new JLabel("Coord Y : ");
		this.coordY.setBackground(Color.lightGray);
		this.coordY.setOpaque(true);

		this.txtNom    = new JTextField(20);
		this.txtNom.setEnabled(false);
		this.txtNom.setBackground((Color.lightGray));

		this.txtCoordX = new JTextField(20);
		this.txtCoordX.setEnabled(false);
		this.txtCoordX.setBackground((Color.lightGray));

		this.txtCoordY = new JTextField(20);
		this.txtCoordY.setEnabled(false);
		this.txtCoordY.setBackground((Color.lightGray));


		this.btnModifier = new JButton("Modifier");
		this.btnModifier.setBackground(Color.WHITE);


		/* ----------------------------------  */
		/*    POSITIONNEMENT DES COMPOSANTS    */
		/* ----------------------------------  */


		this.add(this.jcbDeroulante);
		this.add(new JLabel());

		this.add(this.nom);
		this.add(this.txtNom);

		this.add(this.coordX);
		this.add(this.txtCoordX);

		this.add(this.coordY);
		this.add(this.txtCoordY);

		this.add(new JLabel());
		this.add(this.btnModifier);



		/* ----------------------------------  */
		/*      ACTIVATION DES COMPOSANTS      */
		/* ----------------------------------  */


		this.jcbDeroulante.addItemListener(this);

		this.txtNom     .addActionListener(this);
		this.txtCoordX  .addActionListener(this);
		this.txtCoordY  .addActionListener(this);
		this.btnModifier.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{

		Integer coordX = null;
		Integer coordY = null;

		if (e.getSource() == this.btnModifier)
		{
			try
			{
				coordX = Integer.parseInt(this.txtCoordX.getText());
				coordY = Integer.parseInt(this.txtCoordY.getText());
			}
			catch ( NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(this, "Erreur pour la saisie des coordonnées", "Erreur", JOptionPane.ERROR_MESSAGE);
			}


			if ( this.jcbDeroulante.getSelectedItem() != null && this.txtNom.getText() != null && coordX != null && coordY != null )
			{
				System.out.println("nom : " + this.jcbDeroulante.getSelectedItem() + " coordX : " + coordX + " coordY : " + coordY);
				this.ctrl.modifierVille(this.jcbDeroulante.getSelectedItem().toString(), coordX, coordY);
			}
		}
	}


	public void itemStateChanged(ItemEvent e)
	{
		if (e.getSource() == this.jcbDeroulante && this.jcbDeroulante.getSelectedIndex() != 0 )
		{
			this.txtNom.setText(this.jcbDeroulante.getSelectedItem().toString());
			this.txtNom.setBackground((Color.WHITE));

			this.txtCoordX.setText(String.valueOf(this.ctrl.getVille(this.jcbDeroulante.getSelectedItem().toString()).getX()));
			this.txtCoordX.setEnabled(true);
			this.txtCoordX.setBackground((Color.WHITE));

			this.txtCoordY.setText(String.valueOf(this.ctrl.getVille(this.jcbDeroulante.getSelectedItem().toString()).getY()));
			this.txtCoordY.setEnabled(true);
			this.txtCoordY.setBackground((Color.WHITE));

			this.txtNom   .setText(this.jcbDeroulante.getSelectedItem().toString());
			this.txtCoordX.setText(String.valueOf(this.ctrl.getVille(this.jcbDeroulante.getSelectedItem().toString()).getX()));
			this.txtCoordY.setText(String.valueOf(this.ctrl.getVille(this.jcbDeroulante.getSelectedItem().toString()).getY()));
		}
		else
		{
			this.txtNom.setText("");
			this.txtNom.setBackground(Color.lightGray);

			this.txtCoordX.setText("");
			this.txtCoordX.setEnabled(false);
			this.txtCoordX.setBackground(Color.lightGray);

			this.txtCoordY.setText("");
			this.txtCoordY.setEnabled(false);
			this.txtCoordY.setBackground(Color.lightGray);
		}
	}

}

