/**
 * @author CAUVIN Pierre, AUBIN Montagne, DELPECHE Nicolas, GUELLE Clément
 * Cette classe gère les routes.
 */
package sae.vue;

import sae.Controleur;
import sae.metier.Route;

import javax.swing.table.AbstractTableModel;

public class GrlDonneesModelRoute extends AbstractTableModel
{

	private String[]   tabEntetes;
	private Object[][] tabDonnees;


	public GrlDonneesModelRoute(Controleur ctrl)
	{

		int cptVille = 0;

		this.tabEntetes = new String[]{"Ville Depart","Ville Arrivé","Nombres Tronçons"};
		this.tabDonnees = new Object[ctrl.getRoutes().size()][this.tabEntetes.length];


		for ( Route route : ctrl.getRoutes())
		{
			this.tabDonnees[cptVille] = new Object[]{route.getDepart().getNom(),route.getArrivee().getNom(),route.getTroncons()};
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
