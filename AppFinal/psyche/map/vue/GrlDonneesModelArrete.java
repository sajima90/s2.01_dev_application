/**
 * @author CAUVIN Pierre, AUBIN Montagne, DELPECHE Nicolas, GUELLE Clément
 * Cette classe gère les routes.
 */
package psyche.map.vue;

import psyche.map.ControleurMap;
import psyche.map.metier.Arrete;

import javax.swing.table.AbstractTableModel;

public class GrlDonneesModelArrete extends AbstractTableModel
{

	private String[]   tabEntetes;
	private Object[][] tabDonnees;


	public GrlDonneesModelArrete(ControleurMap ctrlMap)
	{

		int cptVille = 0;

		this.tabEntetes = new String[]{"Mine Depart Couleur","Mine Depart Point","Mine Arrivé Couleur","Mine Arrivé Point","Nombres Tronçons"};
		this.tabDonnees = new Object[ctrlMap.getArretes().size()][this.tabEntetes.length];


		for ( Arrete route : ctrlMap.getArretes())
		{
			this.tabDonnees[cptVille] = new Object[]{ (String) route.getDepart().getCouleur().name(), route.getDepart().getPoint(), (String) route.getArrivee().getCouleur().name(), route.getArrivee().getPoint(), route.getTroncons() };
			cptVille ++;
		}

	}

	public String getColumnName (int col)
	{
		return this.tabEntetes[col];
	}

	public int    getRowCount	()
	{
		return this.tabDonnees.length;
	}

	public int    getColumnCount()
	{
		return this.tabEntetes.length;
	}

	public Object getValueAt    (int lig, int col)
	{
		return this.tabDonnees[lig][col];
	}

}
