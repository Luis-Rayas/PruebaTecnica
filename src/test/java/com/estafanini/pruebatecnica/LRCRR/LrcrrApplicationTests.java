package com.estafanini.pruebatecnica.LRCRR;

import com.estafanini.pruebatecnica.LRCRR.Exeptions.UserNotFoundException;
import com.estafanini.pruebatecnica.LRCRR.entities.User;
import com.estafanini.pruebatecnica.LRCRR.repositories.UserRepository;
import com.estafanini.pruebatecnica.LRCRR.services.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
class LrcrrApplicationTests {

	@Autowired
	private UserService userService;

	@Test
	public void createNewUser() {
		// Creamos un nuevo usuario con datos válidos
		User user = User.builder()
				.nombre("Esteban Perez")
				.email("JnQp8@example.com")
				.password("123456")
				.build();

		// Verificamos que no se lance ninguna excepción al guardar el usuario
		Assertions.assertDoesNotThrow(() -> {
			userService.save(user);
		}, "No debería lanzarse ninguna excepción al guardar el usuario con datos válidos");
	}

	@Test
	public void createNewUserFailure() {
		// Creamos un usuario con datos incompletos (falta la contraseña)
		User user = User.builder()
				.nombre("Esteban Perez")
				.email("JnQp8@example.com")
				.build();

		// Verificamos que se lance una excepción cuando tratamos de guardar un usuario incompleto
		Assertions.assertThrows(Exception.class, () -> {
			userService.save(user);
		}, "Se esperaba una excepción debido a la falta de la contraseña en el usuario");
	}

	@Test
	public void deleteUser() {
		// Creamos un usuario para luego eliminarlo
		User user = User.builder()
				.nombre("Esteban Perez")
				.email("JnQp8@example.com")
				.password("123456")
				.build();

		try {
			// Guardamos el usuario en la base de datos
			user = userService.save(user);

			// Verificamos que el usuario fue eliminado correctamente (soft delete)
			Assertions.assertTrue(userService.delete(user.getId()), "El usuario debería haberse eliminado correctamente");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void updateUser() {
		// Creamos un usuario original con datos iniciales
		User userOld = User.builder()
				.nombre("Esteban Perez")
				.email("JnQp8@example.com")
				.password("123456")
				.build();

		try {
			// Guardamos el usuario original
			User userNew = userService.save(userOld);

			// Realizamos cambios en los datos del usuario
			String nombreNew = "Esteban Perez 2";
			userNew.setNombre(nombreNew);

			// Actualizamos el usuario con los nuevos datos
			userNew = userService.update(userNew);

			// Verificamos que el nombre del usuario ha cambiado correctamente
			Assertions.assertEquals(nombreNew, userNew.getNombre(), "El nombre del usuario debería haberse actualizado correctamente");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getFindUserById() {
		// Creamos un nuevo usuario para guardarlo y luego obtenerlo
		User userOld = User.builder()
				.nombre("Esteban Perez")
				.email("JnQp8@example.com")
				.password("123456")
				.build();

		try {
			// Guardamos el usuario en la base de datos
			userOld = userService.save(userOld);

			// Verificamos que el usuario guardado se puede recuperar correctamente
			Assertions.assertNotNull(userService.getUserById(userOld.getId()), "El usuario debería ser recuperable por su ID");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Test
	public void getFindUserByIdFailure() {
		User user = User.builder()
				.nombre("Esteban Perez")
				.email("JnQp8@example.com")
				.password("123456")
				.build();

		User savedUser = null;
		try {
			savedUser = userService.save(user);
			Assertions.assertNotNull(savedUser, "El usuario debería ser guardado correctamente.");
		} catch (Exception e) {
			e.printStackTrace();
			Assertions.fail("El usuario no pudo ser guardado: " + e.getMessage());
		}
		Optional<User> userOptional = userService.getUserById(savedUser.getId() + 1L);
		Assertions.assertTrue(userOptional.isEmpty(), "El usuario con el ID " + (savedUser.getId() + 1L) + " no debería ser encontrado.");

	}
}
