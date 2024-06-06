package sae;

import sae.metier.Metier;
import sae.metier.Route;
import sae.metier.Ville;
import sae.vue.*;

import java.util.List;

public class Controleur
{
	private final Metier metier;
	private final Frame  frame;

	private FrameAjouterVille  frameAjouterVille;
	private FrameAjouterRoute  frameAjouterRoute;
	private FrameModifierVille frameModifierVille;

	private PanelGraph     panelGraph;
	private PanelInfoVille panelInfoVille;

	public Controleur()
	{
		this.metier         = new Metier();
		this.frame          = new Frame(this);
		this.panelGraph     = new PanelGraph(this);
		this.panelInfoVille = new PanelInfoVille(this);

	}

	public void ouvrirAjouterVille()  { this.frameAjouterVille  = new FrameAjouterVille(this); }

	public void ouvrirAjouterRoute()  { this.frameAjouterRoute  = new FrameAjouterRoute(this); }

	public void ouvrirModifierVille() { this.frameModifierVille = new FrameModifierVille(this);}

	public Ville ajouterVille(String nom, int x, int y)
	{
		this.majIHM();
		return this.metier.ajouterVille(nom, x, y);
	}

	public Route ajouterRoute(Ville depart, Ville arrivee, int troncons)
	{
		this.majIHM();
		return this.metier.ajouterRoute(depart, arrivee, troncons);
	}

	public void modifierVille(String nom, int x, int y)
	{
		this.metier.modifierVille(nom, x, y);
		this.majIHM();
	}

	public Ville getVilleParNom(String ville) { return this.metier.getVilleParNom(ville); }

	public List<Ville> getVilles()       { return this.metier.getVilles(); }

	public Ville getVille(String string) { return this.metier.getVilleParNom(string); }

	public List<Route> getRoutes()       { return this.metier.getRoutes(); }

	public List<Route> getRoute(Ville ville) { return ville.getRoutes(); }


	public void majIHM() { this.frame.getPanelGraph().repaint(); }

	public void majIHM(Ville villeSelect)
	{
		this.frame.getPanelGraph().repaint();
		this.frame.getPanelInfoVille().majVilleInfo(villeSelect.getNom(), villeSelect.getX(), villeSelect.getY());
	}


	//Fichier

	public void enregistrer() {
		this.metier.enregistrer();
		this.majIHM();
	}

	public void enregistrerSous() {
		this.majIHM();
		this.metier.enregistrerSous();
	}

	public String getFichierCharger() {
		return this.metier.getFicherCharger();
	}

	public void setFichierCharger(String path) {
		this.metier.setFichierCharger(path);
		this.majIHM();
	}

		public static void main(String[] args)
	{
		new Controleur();
	}


}
