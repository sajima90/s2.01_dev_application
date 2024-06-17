package psyche.jeu.metier;

public enum Minerai
{
	Aluminium ("Al"),
	Argent    ("Ag"),
	Or        ("Au"),
	Cobalt    ("Co"),
	Fer       ("Fe"),
	Nickel    ("Ni"),
	Platine   ("Pl"),
	Titane    ("Ti");

	private final String nom;

	Minerai(String nom)
	{
		this.nom = nom;
	}

	public String getNom()
	{
		return this.nom;
	}

}
