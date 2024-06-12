/**
 * @author CAUVIN Pierre, AUBIN Montagne, DELPECHE Nicolas, GUELLE Clément
 * Cette classe gère les routes.
 */

package psyche.vue.map;

import psyche.Controleur;
import psyche.metier.map.Mine;

import javax.swing.table.AbstractTableModel;

public class GrlDonneesModelVille extends AbstractTableModel
{

	private String[]   tabEntetes;
	private Object[][] tabDonnees;


	public GrlDonneesModelVille(Controleur ctrl)
	{

		int cptMine = 0;

		this.tabEntetes = new String[] { "Numero", "Couleur", "Point", "X", "Y" };
		this.tabDonnees = new Object[ctrl.getMines().size()][this.tabEntetes.length];

		for (Mine mine : ctrl.getMines())
		{
			this.tabDonnees[cptMine] = new Object[] { mine.getId(), mine.getCouleur(), mine.getPoint(), mine.getX(), mine.getY() };
			cptMine++;
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
