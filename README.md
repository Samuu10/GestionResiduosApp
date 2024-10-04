# Gestión de Residuos App

## Participantes

* Inés Gómez
* Marcos Ruiz
* Pedro Velasco
* Samuel Muñoz

## Objetivo

Esta aplicación Android tiene como objetivo ayudar a los usuarios a gestionar sus residuos de forma más eficiente y responsable. 
Proporciona información sobre el reciclaje, puntos de reciclaje cercanos y permite a los usuarios establecer recordatorios para sacar la basura.

## Estructura del proyecto

El proyecto se divide en tres módulos principales:

**1. Calendario:** Permite a los usuarios añadir recordatorios para diferentes tipos de residuos.
    * **`PantallaCalendario.java`:** Actividad principal del módulo. Gestiona la vista del calendario, la adición y eliminación de recordatorios.

**2. Estadísticas:** Muestra estadísticas sobre la cantidad de residuos reciclados.
    * **`PantallaEstadisicas.java`:** Actividad principal del módulo. Muestra la gráfica de estadísticas.
    * **`Grafica.java`:** Clase personalizada que dibuja la gráfica de barras.
    * **`MyViewModel.java`:** ViewModel que proporciona los datos para la gráfica.
    * **`Pair.java`:** Clase que representa un par de valores (nombre del residuo, cantidad).

**3. Mapa:** Muestra la ubicación de los puntos de reciclaje cercanos.
    * **`PantallaMapa.java`:** Actividad principal del módulo. Muestra una imagen de mapa y un botón para ver la lista de puntos de reciclaje.
    * **`ListaReciclajeActivity.java`:**  Actividad que muestra la lista de puntos de reciclaje.
    * **`RecyclingPoint.java`:** Clase que representa un punto de reciclaje con nombre y dirección.
    * **`RecyclingPointAdapter.java`:** Adaptador para el `RecyclerView` que muestra la lista de puntos de reciclaje.

**4. Pantalla principal:** 
    * **`PantallaPrincipal.java`:** Actividad principal de la aplicación. Muestra el menú principal con botones para acceder a los diferentes módulos.

## Archivos XML

* **`calendario_border.xml`:** Define un borde para el TextView en la pantalla principal.
* **`recordatorio_border.xml`:** Define un borde para los recordatorios en la pantalla del calendario.
* **`pantalla_calendario.xml`:** Define la interfaz de usuario del calendario, incluyendo el `CalendarView`, botones y la lista de recordatorios.
* **`dialog_input.xml`:** Define la interfaz de usuario del diálogo para añadir un nuevo recordatorio.
* **`pantalla_estadisticas.xml`:** Define la interfaz de usuario para las estadísticas, incluyendo la vista de la gráfica y el botón de volver.
* **`pantalla_mapa.xml`:** Define la interfaz de usuario para el mapa, incluyendo la imagen del mapa y los botones.
* **`activity_lista_reciclaje.xml`:** Define la interfaz de usuario para la lista de puntos de reciclaje, incluyendo el `RecyclerView`.
* **`pantalla_principal.xml`:** Define la interfaz de usuario de la pantalla principal, incluyendo los botones del menú.
* **`item_recycling_point.xml`:** Define la interfaz de usuario para cada elemento de la lista de puntos de reciclaje.

Link al repositorio base: https://github.com/Samuu10/GestionResiduosApp.git
Link al repositorio de la organización: https://github.com/GrupoProgramacionEventos/GestionResiduosApp.git
