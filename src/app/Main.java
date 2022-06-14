package app;

import java.lang.reflect.Field;

import app.model.Person;

public class Main {

	public static void main(String[] args)
			throws NoSuchFieldException, SecurityException {

		final Person arnold = new Person("Arnold");

		final Field field = Person.class.getDeclaredField("name");

		// ------------------------------------------------------------------------------------

		System.out.println(getName(field, arnold)); // Affiche le message d'erreur car name est privé.

		field.setAccessible(true); // Change l'accès de name.

		System.out.println(getName(field, arnold)); // Affiche le nom d'arnold.

		// ------------------------------------------------------------------------------------

		setName(field, arnold, "Rebecca");

		System.out.println(getName(field, arnold)); // Le nom d'arnold est maintenant Rebecca.

	}

	private static String getName(final Field field, Person person) {

		try {

			return (String) field.get(person);

		} catch (IllegalArgumentException | IllegalAccessException e) {

			return "Accès refusé, name est privé";
		}

	}

	private static void setName(final Field field, Person person, final String newName) {

		try {

			field.set(person, newName);

		} catch (IllegalArgumentException | IllegalAccessException e) {

			System.out.println("Accès refusé, name est privé");

		}

	}

}
