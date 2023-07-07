# Weatter app UCR

## Descripcion

Este repositorio sigue los principios de la Arquitectura Limpia (Clean Architecture), la Arquitectura MVVM y utiliza los patrones de Objeto de Acceso a Datos (DAO), Objeto de Transferencia de Datos (DTO) y Repositorio. A continuación, se presenta una breve explicación de cada concepto:

## Patrones usados en el workshop

### Clean Architecture

La Arquitectura Limpia sigue la Regla de Dependencia, la cual establece que el código fuente solo puede depender de componentes dentro de círculos internos y no puede conocer nada sobre componentes en círculos externos. La arquitectura consta de las siguientes capas:

1. Entidades (Entities): Estas encapsulan las reglas comerciales de toda la empresa. Pueden ser objetos con métodos o conjuntos de estructuras y funciones utilizados por diferentes aplicaciones dentro de la empresa.

2. Casos de uso (Use Cases): Estos contienen reglas comerciales específicas de una aplicación, orquestan el flujo de datos entre entidades y aplican las reglas comerciales de toda la empresa para lograr objetivos.

3. Adaptadores de interfaz (Interface Adapters): Estos convierten datos entre formatos convenientes para casos de uso/entidades y formatos convenientes para entidades externas (por ejemplo, bases de datos). También pueden incluir adaptadores para servicios externos.

4. Marcos y controladores (Frameworks and Drivers): Esta capa más externa consiste en marcos y herramientas externas, como bases de datos y marcos web. Los detalles técnicos específicos se mantienen en esta capa para minimizar el impacto en otras capas.

Siguiendo estas reglas, se crea un sistema fácil de probar y flexible, lo que permite reemplazar partes obsoletas sin afectar otras áreas del sistema.

### Arquitectura MVVM

La arquitectura MVVM (Modelo-Vista-Modelo de Vista) sugiere separar la lógica de presentación de datos (vistas o interfaces de usuario) de la parte principal de la lógica de negocio de la aplicación. Las capas en MVVM son las siguientes:

1. Modelo (Model): Esta capa abstrae las fuentes de datos subyacentes, como bases de datos o servicios web. Define la estructura de los datos y las operaciones necesarias para obtener y guardar esa información. El modelo actúa como una representación de la lógica de negocio y proporciona métodos y propiedades para interactuar con los datos.

2. Vista (View): Esta capa se centra en presentar la interfaz de usuario al usuario final. Es responsable de mostrar los datos al usuario y capturar las interacciones del usuario, como clics de botones o entradas de formularios. La vista no contiene lógica de negocio, sino que se limita a reflejar el estado actual de los datos proporcionados por el modelo. Su objetivo principal es ofrecer una experiencia visual atractiva y usable para el usuario.

3. Modelo de Vista (ViewModel): El ViewModel actúa como intermediario entre el modelo y la vista. Su propósito principal es exponer los datos y comandos relevantes para la vista, de modo que la interfaz de usuario pueda enlazarlos y mostrarlos correctamente. El ViewModel se encarga de la transformación y preparación de los datos provenientes del modelo para que sean adecuados para su visualización en la vista. También maneja las interacciones del usuario y coordina las acciones necesarias con el modelo. En resumen, el ViewModel proporciona una representación de los datos que la vista puede consumir y mostrar de manera adecuada, sin conocer los detalles de la lógica de negocio subyacente en el modelo.

### Patrón de Objeto de Acceso a Datos (DAO)

El patrón de Objeto de Acceso a Datos (DAO, por sus siglas en inglés) es un patrón estructural que permite aislar la capa de aplicación/negocio de la capa de persistencia (generalmente una base de datos relacional, aunque podría ser cualquier otro mecanismo de persistencia) mediante el uso de una API abstracta. La API oculta a la aplicación toda la complejidad de realizar operaciones CRUD en el mecanismo de almacenamiento subyacente. Esto permite que ambas capas evolucionen por separado sin conocer nada una de la otra.

### Patrón de Objeto de Transferencia de Datos (DTO)

El patrón de Objeto de Transferencia de Datos (DTO) es una forma de agrupar datos que se enviarán en una llamada. Es necesario que el DTO sea serializable para poder enviarlo a través de una conexión. Se utiliza un ensamblador en el lado del servidor para transferir datos entre el DTO y los objetos de dominio. Una ventaja del uso de un DTO es que permite combinar múltiples llamadas remotas en una sola llamada, lo que mejora el rendimiento. Además, el DTO encapsula la lógica de serialización, lo que mantiene esa responsabilidad separada del resto del código y facilita cambios futuros en la forma en que se realiza la serialización. En resumen, el DTO es una forma eficiente de transferir datos entre componentes, reduciendo el número de llamadas y encapsulando la lógica de serialización.

### Patrón de Repositorio (Repository)

Patrón de Repositorio (Repository)
El patrón de Repositorio es una estrategia de diseño que busca mantener una separación clara entre la capa de datos y las demás partes de una aplicación. La capa de datos, a menudo conocida como capa de persistencia, se encarga de gestionar todas las operaciones relacionadas con el almacenamiento y recuperación de datos, así como la implementación de la lógica de negocio asociada. Esta capa proporciona una interfaz consistente y unificada a través de la cual las otras partes de la aplicación pueden acceder y manipular los datos.

El objetivo principal del patrón de Repositorio es proporcionar una abstracción entre la lógica de negocio y la forma en que se almacenan y recuperan los datos. Esto permite que el código de la lógica de negocio sea independiente de los detalles de implementación de la capa de datos, lo que facilita el mantenimiento, la modularidad y la flexibilidad de la aplicación en su conjunto.

Al utilizar el patrón de Repositorio, se definen interfaces y clases concretas que representan las entidades de datos de la aplicación, como usuarios, productos o pedidos. Estas interfaces y clases concretas actúan como una fachada que oculta la complejidad subyacente de cómo se almacenan y acceden a los datos. Además, el repositorio proporciona métodos y operaciones específicas para realizar consultas y modificaciones en los datos de manera coherente y eficiente.

Siéntete libre de explorar el código de este repositorio, ya que sigueestos principios arquitectónicos y patrones para crear una aplicación modular y mantenible.

> Este repositorio esta basado en el trabajo del developer AhmetOcak, modificado para fines educativos.

## Fuentes

1. [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)

2. [MVVM](https://www.geeksforgeeks.org/mvvm-model-view-viewmodel-architecture-pattern-in-android/)

3. [DAO](https://www.digitalocean.com/community/tutorials/dao-design-pattern)

4. [DTO](https://martinfowler.com/eaaCatalog/dataTransferObject.html)

5. [Repository](https://martinfowler.com/eaaCatalog/repository.html)

## Articulos importantes

[now in android](https://developer.android.com/series/now-in-android)

[now in android repositorio](https://developer.android.com/series/now-in-android)
