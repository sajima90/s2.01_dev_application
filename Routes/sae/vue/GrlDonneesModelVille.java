/**
 * @author CAUVIN Pierre, AUBIN Montagne, DELPECHE Nicolas, GUELLE Clément
 * Cette classe gère les routes.
 */

package sae.vue;

import sae.Controleur;
import sae.metier.Ville;

import javax.swing.table.AbstractTableModel;

public class GrlDonneesModelVille extends AbstractTableModel
{

	private String[]   tabEntetes;
	private Object[][] tabDonnees;


	public GrlDonneesModelVille(Controleur ctrl)
	{

		int cptVille = 0;

		this.tabEntetes = new String[]{"Numero","Nom","X", "Y"};
		this.tabDonnees = new Object[ctrl.getVilles().size()][this.tabEntetes.length];


		for ( Ville ville : ctrl.getVilles())
		{
			this.tabDonnees[cptVille] = new Object[]{ville.getId() , ville.getNom(),ville.getX(),ville.getY() };
			cptVille ++;
		}

	}

	public String getColumnName (int col)
	{
		return this.tabEntetes[col];
	}

	public int getRowCount()
	{
		return this.tabDonnees.length;
	}

	public int getColumnCount()
	{
		return this.tabEntetes.length;
	}

	public Object getValueAt(int lig, int col)
	{
		return this.tabDonnees[lig][col];
	}

}
