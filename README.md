# Foodie **:fork_and_knife:**

## Estructura de carpetas MVVM

Este modelo utiliza el patrón arquitectónico MVVM para organizar el proyecto. La estructura se organiza en las siguientes carpetas:

- `core`: esta carpeta contiene todo lo que es común y esencial para el proyecto. Aquí se incluyen las clases básicas que pueden ser utilizadas por todo el proyecto, como por ejemplo, clases de utilidades, extensiones de Kotlin, constantes, etc.

- `data`: aquí se almacenan todas las fuentes de datos. Se divide en las siguientes subcarpetas:
  - `model`: esta carpeta contiene todas las clases que representan los datos que se utilizan en la aplicación. Aquí se definen las entidades y los objetos de transferencia de datos (DTOs).
  - `remote`: aquí se encuentra todo lo relacionado con las fuentes de datos remotas. Se incluyen los clientes de API, los servicios web y otros componentes que se conectan con una fuente de datos remota.
  - `repository`: esta carpeta contiene los repositorios que se encargan de coordinar los datos que vienen de diferentes fuentes y los convierte en objetos que pueden ser utilizados por la vista.

- `di`: aquí se almacenan todos los módulos de inyección de dependencias. Esta carpeta es responsable de proporcionar instancias de clases en toda la aplicación. Aquí se define cómo se instancian las clases y cómo se resuelven sus dependencias.

- `domain`: en esta carpeta se definen las clases de lógica de negocio, así como los casos de uso de la aplicación. Estos casos de uso son los que coordinan el trabajo entre diferentes fuentes de datos (pero no siempre se usa).

- `navigation`: aquí se almacena todo lo relacionado con la navegación de la aplicación, como la definición de rutas y la configuración del controlador de navegación.

- `presentation`: en esta carpeta se encuentran todas las clases relacionadas con la presentación de la aplicación. Aquí se definen los componentes de la vista(screens) y viewmodel .

Es importante destacar que esta estructura de carpetas es solo una sugerencia y puede variar según las necesidades de cada proyecto. El objetivo de esta organización es facilitar la comprensión del código y permitir una fácil navegación entre los archivos.
