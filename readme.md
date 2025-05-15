# Valet Parking App

Una aplicación de gestión de valet parking para Android, que permite gestionar la entrada y salida
de vehículos, así como el control de espacios de estacionamiento disponibles.

## Descripción General

Valet Parking permite realizar las siguiente acciones:

- Registrar la entrada de vehículos con información del conductor y del vehículo.
- Asignar posiciones de estacionamiento
- Visualizar los vehículos actualmente estacionados
- Procesar la salida de vehículos
- Calcular automáticamente el costo en función del tiempo de estacionamiento
- Mantener un historial de los vehículos que han utilizado el servicio.

## Tecnologías Utilizadas

- **Kotlin**: Lenguaje principal de desarrollo
- **Jetpack Compose**: Framework moderno para UI
- **Material Design 3**: Directrices de diseño implementadas con Material 3
- **MVVM** : Para manejar la comunicacion de las vistas con los casos de usos.
- **Hilt**: Inyección de dependencias
- **Room**: Base de datos para el almacenamiento local
- **Kotlin Coroutines & Flow**: Programación asíncrona y manejo de flujos de datos reactivos
- **Jetpack Navigation**: Navegación entre pantallas
- **KotlinX DateTime**: Manejo de fechas y cálculos de tiempo

## Estructura del proyecto

La aplicación sigue los principios de Clean Architecture, dividiendo en carpetas:

- **di**: Clase para instanciar la Inyección de dependencias con hilt.
- **data**: Definimos aquellas clases que no permite utilizar herramientas de terceros (Room). Podra
  encontrar datasource, mapper y la Implementación de los repositorios.
- **domain**: Manejo de la logica de negocio, nuestros modelos propios, interfaces de repositorio y
  los casos de usos.
- **presentation**: Pantalla de la aplicación; así como la definicion de los estados, viewmodel y
  eventos.
- **utils**: Utilidades de la aplicación.
- **root**: Definicion Navegación y nuestro composable principal de la aplicación.

## Pantallas Principales

- **Home**: Puedes ver el listado de estacionamiento ocupados por los vehiculos. Se muestra el
  listado actual de los estacionamientos ocupados. Puedes darle clic a la tarjeta y ver el detalle
  del aparcamiento (Datos de conductor, vehiculo y calculo de las horas y costo hasta el momento).
- **Nuevo Ingreso**: Pantalla para registrar un nuevo vehículo, se solicita primero información del
  conductor y luego el detalle del auto.

    * Se valida la placa para ver si existe actualmente en el registro y esta ocupando un
      estacionamiento en el momento; en caso de existir se envia un mensaje al conductor que esa
      auto se encuentra registrado.
    * Se muestra los estacionamientos disponibles; una vez escogido este pasa a un estado ocupado en
      la base de datos. Para la proximo registro no saldra en la lista.

- **Historial**: Pantalla donde se muestra el listado de vehiculos que usaron los estacionamientos y
  ya tienen fecha de salida.

## Requisitos del Sistema

- Android 8.0 (API level 26) o superior
- Android Studio 5 o superior
