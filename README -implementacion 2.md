# Water Reminder App
# Descripción del proyecto

Esta aplicación está diseñada para ayudar a los usuarios a llevar un control de su consumo diario de agua, fomentar hábitos de hidratación 
saludables y personalizar su experiencia mediante un perfil de usuario con información individual almacenada en una base de datos local.

# Cambios agregados en esta rama
# Tema 6: Base de datos, persistencia y perfil de usuario

# Base de datos Room integrada:
  Se implementó una entidad User para almacenar los datos personales del usuario.
  La información incluye: nombre, edad, peso, género y meta diaria de consumo (ml).
  Se utiliza un DAO y un ViewModel para manejar operaciones CRUD de forma estructurada y eficiente.

# Gestión del usuario en el Fragmento de Perfil:
  El formulario de registro permite al usuario ingresar y guardar su información.
  Los datos se almacenan en la base de datos Room y se recuperan automáticamente al abrir la aplicación.
  Se permite un solo registro, evitando duplicados.
  Debajo del formulario se muestra una sección visual (tipo lista) con el perfil guardado, actualizable en tiempo real.

# Interfaz de usuario mejorada:
  Diseño basado en Material Design, con campos de texto, botones y selección de género mediante RadioButtons.
  Visualización clara del perfil registrado, con formato tipo tarjeta.
  Comportamiento dinámico: si el usuario modifica su información, la vista inferior se actualiza al instante.

# Tema 5 (actualizado): Elementos de interfaz gráfica

# Componentes UI utilizados:
  EditText para los campos del formulario de perfil.
  RadioButton para la selección de género.
  Button para guardar o actualizar el perfil.
  TextView para mostrar la información del usuario guardado.
  LinearLayout para estructurar la visualización del perfil guardado.
  ScrollView para permitir desplazamiento fluido dentro del fragmento.

# Flujo de funcionamiento
  El usuario ingresa sus datos personales en el formulario del Perfil.
  Al presionar “Guardar perfil”, la aplicación guarda la información en la base de datos.
  Debajo del formulario aparece un resumen del perfil registrado con los datos ingresados.
  Si el usuario vuelve a modificar los campos y guarda de nuevo, los datos se actualizan automáticamente.
  La información se conserva incluso al cerrar y volver a abrir la aplicación.

# Fragmentos actuales de la aplicación
Inicio → muestra progreso y estadísticas del día.
Historial → lista de registros de consumo de agua.
Perfil de usuario → formulario y vista del perfil almacenado (nuevo).
Consejos → recomendaciones para mantener una buena hidratación.

# Navegación
  Navigation Drawer con acceso rápido a todas las secciones.
  Bottom Navigation para las funciones principales (Inicio, Historial, Perfil).
  ActionBar Menu con opciones adicionales.

# Próximos pasos
  Implementar almacenamiento de consumo diario de agua asociado al usuario.
  Integrar notificaciones personalizadas basadas en la meta diaria.
  Añadir animaciones y tarjetas visuales para el perfil y el progreso.
  Permitir editar o restablecer el perfil desde la misma pantalla.