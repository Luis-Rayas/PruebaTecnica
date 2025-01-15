# Gestión de Usuarios con Spring Boot y MySQL

## Descripción

Este proyecto implementa un sistema de microservicios utilizando **Spring Boot** para la gestión de usuarios. El sistema permite realizar operaciones CRUD sobre usuarios almacenados en una base de datos **MySQL**, asegurando un manejo adecuado de excepciones y cumpliendo con los estándares de códigos HTTP para errores y respuestas exitosas.

## Funcionalidades Requeridas

1. **Inserción de un nuevo usuario**:
    - Endpoint para agregar un nuevo usuario con datos como nombre, correo electrónico y contraseña.

2. **Eliminación de un usuario**:
    - Endpoint para eliminar un usuario existente basado en su ID.

3. **Consulta general de usuarios**:
    - Endpoint para obtener todos los usuarios registrados en la base de datos.

4. **Consulta individual de usuarios**:
    - Endpoint para obtener detalles específicos de un usuario según su ID.

5. **Actualización general de un usuario**:
    - Endpoint para actualizar todos los campos de un usuario existente.

6. **Actualización parcial de un usuario**:
    - Endpoint para actualizar selectivamente algunos campos de un usuario (por ejemplo, solo actualizar el nombre o el correo electrónico).

## Consideraciones Técnicas

- **Base de Datos**:
    - Se utiliza **MySQL** como base de datos relacional para almacenar la información de los usuarios.

- **Manejo de Excepciones**:
    - Implementación de manejo de excepciones personalizado para capturar y gestionar errores de validación, integridad de datos y otros posibles problemas durante las operaciones CRUD.

- **Códigos HTTP**:
    - Utilización de códigos HTTP estándar para respuestas exitosas (por ejemplo, 200 OK) y para errores (como 404 Not Found).

- **Pruebas Unitarias**:
    - Se realizan pruebas unitarias utilizando frameworks como **JUnit** para asegurar el correcto funcionamiento de las funcionalidades implementadas.

## Contenido del repositorio

- **Código de Microservicios**:
    - Repositorio Git con el código fuente de los microservicios desarrollados.

- **Video Explicativo**:
    - Un video que describe las buenas prácticas de desarrollo aplicadas en el proyecto, destacando aspectos como la estructura del proyecto, el manejo de excepciones, los códigos HTTP utilizados y cualquier otra consideración relevante.
    - En el video se incluirá una demostración funcional del sistema o las peticiones realizadas utilizando herramientas pertinentes.
    - Se mostrarán las pruebas unitarias para verificar el correcto funcionamiento de las funcionalidades implementadas.

## Nota

Fomentamos la creatividad en el diseño de la arquitectura del proyecto y la implementación de las funcionalidades, siempre manteniendo un enfoque en la calidad del código y las prácticas de desarrollo.
