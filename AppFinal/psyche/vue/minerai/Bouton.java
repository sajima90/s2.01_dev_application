package psyche.vue.minerai;

import javax.swing.JButton;

public class Bouton extends JButton
{
	private static int insBouton=0;
	private int numero;

	public Bouton(String label)
	{
		super(label);
		this.numero = insBouton++;
	}

	public int getNumeroBouton()
	{
		return this.numero;
	}

	public String toString()
	{
		return ""+this.numero;
	}
}