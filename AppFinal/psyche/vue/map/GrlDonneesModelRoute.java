/**
 * @author CAUVIN Pierre, AUBIN Montagne, DELPECHE Nicolas, GUELLE Clément
 * Cette classe gère les routes.
 */
package psyche.vue.map;

import psyche.Controleur;
import psyche.ControleurMap;
import psyche.metier.map.Route;

import javax.swing.table.AbstractTableModel;

public class GrlDonneesModelRoute extends AbstractTableModel
{

	private String[]   tabEntetes;
	private Object[][] tabDonnees;


	public GrlDonneesModelRoute(ControleurMap ctrlMap)
	{

		int cptVille = 0;

		this.tabEntetes = new String[]{"Mine Depart Couleur","Mine Depart Point","Mine Arrivé Couleur","Mine Arrivé Point","Nombres Tronçons"};
		this.tabDonnees = new Object[ctrlMap.getRoutes().size()][this.tabEntetes.length];


		for ( Route route : ctrlMap.getRoutes())
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
