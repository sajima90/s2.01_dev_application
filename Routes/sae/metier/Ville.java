package sae.metier;

import java.util.ArrayList;
import java.util.List;

public class Ville
{

	private static int NbVilles = 0;
	private int id;
	private String nom;
	private int x;
	private int y;
	private List<Route> routes;

	/**
	 * Constructeur de la Ville
	 *
	 * @param nom
	 * 		Nom de la ville
	 * @param x
	 * 		Coordonnée X de la ville
	 * @param y
	 * 		Coordonnée Y de la ville
	 */
	private Ville(String nom, int x, int y)
	{
		// Auto-incrémentation de l'id de la ville
		this.id = ++Ville.NbVilles;
		this.nom = nom.trim();
		this.x = x;
		this.y = y;
		this.routes = new ArrayList<>();

	}

	/**
	 * Factory de Ville, vérifie que les paramètres soit valide et créé la ville sinon retourne null
	 *
	 * @param nom
	 * 		Nom de la ville
	 * @param x
	 * 		coordonnée X de la ville
	 * @param y
	 * 		coordonnée Y de la ville
	 * @return La Ville créé ou null en fonction de la validité des paramètres
	 */
	public static Ville creerVille(String nom, int x, int y)
	{
		if (nom == null || nom.isEmpty())
			return null;

		if (x < 0 || x > 1000 || y < 0 || y > 800)
			return null;

		return new Ville(nom, x, y);
	}

	public static void resetId()
	{
		Ville.NbVilles = 0;
	}

	public int getId()
	{
		return this.id;
	}

	public String getNom()
	{
		return this.nom;
	}

	public void setNom(String nom)
	{
		this.nom = nom;
	}

	public int getX()
	{
		return this.x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return this.y;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public List<Route> getRoutes()
	{
		return this.routes;
	}

	public void addRoute(Route route)
	{
		this.routes.add(route);
	}


    public String toString() {
        return "Ville{" +
                "id=" + this.id +
                ", nom=" + this.nom +
                ", x=" + this.x +
                ", y=" + this.y +
                ", routes=" + this.routes.size() + "}";
    }
}
