package workbench;

import java.util.Scanner;

public class Sql {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		ConexionJoseba con = new ConexionJoseba();

		con.MostrarUsuarios();

		System.out.println("Introduce el id del centro: ");
		int id_c = sc.nextInt();

		System.out.println("Introduce el nombre de la empresa: ");
		String nombre = sc.nextLine();

		System.out.println("Tipo de sector: ");
		String tipo = sc.nextLine();

		System.out.println("m2? : ");
		int m2 = sc.nextInt();

		System.out.println("Temperatura_media: ");
		float t_m = sc.nextFloat();

		Sector s = new Sector(id_c, nombre, tipo, m2, t_m);

	}

}
