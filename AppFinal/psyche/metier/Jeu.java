/**
 * @author  Pierre Cauvin, Clément Guelle, Aubin Montagne, Nicolas Delpech
 *          Henri Rougeolle, Yanis Yachir, Jules Bouquet
 *
 */

package psyche.metier;

public class Jeu
{
	// 1 plateau principal
	// 2 plateaux indiv
	
}
/*
import psyche.Controleur;

import javax.swing.*;
import java.awt.*;
/*
*
* this.panelHaut      = new PanelHautBas(this.ctrl,0,25);
		this.panelHaut.setBackground(new Color(0,0,0,0));
		this.panelHaut.setOpaque(false);

		this.panelBas      = new PanelHautBas(this.ctrl,75,51);
		this.panelBas.setBackground(new Color(0,0,0,0));
		this.panelBas.setOpaque(false);

		this.panelGauche   = new PanelHautBas(this.ctrl,100,76);
		this.panelGauche.setBackground(new Color(0,0,0,0));
		this.panelGauche.setOpaque(false);

		this.panelDroite  = new PanelHautBas(this.ctrl,26,50);
		this.panelDroite.setBackground(new Color(0,0,0,0));
		this.panelDroite.setOpaque(false);
*
*		panelGraph.add(panelHaut,BorderLayout.NORTH);
		panelGraph.add(panelBas,BorderLayout.SOUTH);
		panelGraph.add(panelGauche,BorderLayout.WEST);
		panelGraph.add(panelDroite,BorderLayout.EAST);
*
*
*
*
* */
/*
public class PanelHautBas extends JPanel
{

	private int borne1,borne2;
	private Controleur ctrl;

	public PanelHautBas(Controleur ctrl,int borne1, int borne2)
	{

		this.ctrl = ctrl ;
		if (borne1 <= 0 && borne2 >= 25 || borne1 <=75 && borne2 >= 51)
		{
			this.setLayout(new GridLayout(1, 25));
		}
		else
		{
			this.setLayout(new GridLayout(25,1));
		}
		if ( borne1 < borne2)
		{
			for (int i = borne1; i <= borne2; i++)
			{
				JLabel contour = new JLabel("" + i);
				this.add(contour);
				contour.setOpaque(false);
			}
		}
		else
		{
			for (int i = borne1; i >= borne2; i--)
			{
				JLabel contour = new JLabel("" + i);
				this.add(contour);
				contour.setOpaque(false);
			}
		}



	}

}
*/
