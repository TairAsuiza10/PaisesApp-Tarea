# 🌎 Catálogo de Países App

Aplicación móvil para Android que consume la API pública de [RestCountries](https://restcountries.com/) para mostrar un catálogo interactivo de países. Desarrollada como proyecto académico.

##  Características Principales
* **Vista de Catálogo:** Muestra una lista de todos los países con su nombre común y bandera.
* **Pantalla de Detalles:** Al seleccionar un país, se despliega una vista con **10 atributos específicos**:
  1. Nombre Oficial
  2. Nombre Común
  3. Capital
  4. Región
  5. Subregión
  6. Población
  7. Área (km²)
  8. Continentes
  9. Inicio de semana
  10. Zonas Horarias
* **Manejo de estado:** Indicador visual de carga mientras se obtienen los datos de la red.

##  Tecnologías Utilizadas
* **Lenguaje:** Kotlin
* **Interfaz Gráfica:** Jetpack Compose (Diseño moderno y declarativo)
* **Consumo de API:** Retrofit2 y GSON
* **Carga de Imágenes:** Coil (Para renderizar las banderas)

##  Cómo ejecutar el proyecto
1. Clona este repositorio en tu computadora.
2. Abre el proyecto con **Android Studio**.
3. Ejecuta la aplicación en un emulador o dispositivo físico asegurándote de tener conexión a internet.
