# Water Reminder App - Aplicación de Recordatorio de Agua

## Planteamiento del Proyecto

### Contexto

Hoy en día sabemos que las aplicaciones móviles ya son parte de nuestras vidas; incluso se han vuelto esenciales en nuestro trabajo. Sin embargo, a menudo olvidamos algo muy importante: **el recordatorio de tomar agua**.  
Se estima que una persona debe consumir entre **2 y 3.7 litros de agua al día**, pero la realidad es que el 60% de la población solo toma alrededor de **1 litro diario**.  
Por ello, se propone desarrollar una aplicación que recuerde a los usuarios tomar agua de manera constante.

### Problema

Existen aplicaciones con este mismo objetivo, pero muchas no resultan llamativas y son poco utilizadas.  
La mayoría de las apps que tienen éxito se debe a que ofrecen una **recompensa** o bien son necesarias para el trabajo.  
Este proyecto busca mejorar esa experiencia ofreciendo algo más atractivo y motivador.

### Justificación

El desarrollo de la aplicación será distinto porque incluirá:

1. Fácil manejo.
2. Interfaz llamativa a la vista.
3. Factor de recompensa.
4. Beneficio directo a la salud del usuario.

### Propuesta de Solución

-  Se realizará una **versión beta** para un pequeño grupo de usuarios, con el fin de validar su fácil manejo.
-  Se diseñará con un **buen porte visual** para que sea amigable a la vista.
-  Cada vez que se complete la tarea diaria, se mostrará un **mensaje de ánimo**.
-  La salud del usuario mejorará al seguir los consejos de la app.

## Requerimientos Técnicos

### Especificaciones Técnicas Generales

-  **Plataforma**: Android nativo
-  **Lenguaje de programación**: Java
-  **API mínima**: Android API 28 (Android 9.0)
-  **API objetivo**: Android API 34 (Android 14)
-  **IDE**: Android Studio
-  **Arquitectura**: MVVM (Model-View-ViewModel)

### Requerimientos Funcionales por Tema

#### Tema 4: Fragmentos, flujo maestro-detalle y menú

-  **Fragmentos principales**:
   -  Fragment de inicio con estadísticas diarias
   -  Fragment de historial de consumo
   -  Fragment de configuración de perfil
   -  Fragment de consejos de hidratación
-  **Navegación maestro-detalle**:
   -  Lista de días de la semana → Detalle del consumo diario
   -  Lista de logros → Detalle del progreso
-  **Menú de navegación**:
   -  Navigation Drawer con acceso a todas las secciones
   -  Bottom Navigation para funciones principales
   -  Menú de opciones en ActionBar

#### Tema 5: Elementos de interfaz gráfica

-  **Componentes UI básicos**:
   -  TextView para mostrar cantidad de agua consumida
   -  Button para registrar vaso de agua consumido
   -  ImageView para mostrar iconos de hidratación
   -  ProgressBar circular para mostrar progreso diario
   -  SeekBar para ajustar meta diaria de agua
   -  CheckBox para recordatorios personalizados
   -  RadioButton para selección de tipos de recipientes

#### Tema 6: Más sobre interfaz gráfica

-  **Componentes avanzados**:
   -  RecyclerView para mostrar historial de consumo
   -  CardView para mostrar estadísticas diarias
   -  TabLayout para navegación entre secciones
   -  ViewPager2 para tutorial inicial
   -  SwipeRefreshLayout para actualizar datos
   -  FloatingActionButton para agregar consumo rápido
   -  Snackbar para confirmaciones y mensajes

#### Tema 7: Transiciones

-  **Animaciones implementadas**:
   -  Transiciones entre Activities con shared elements
   -  Animaciones de fade in/out para fragments
   -  Animaciones de slide para cambio de pestañas
   -  Animación de splash screen al iniciar
   -  Transiciones personalizadas para botones y cards
   -  Animación de progreso al completar metas

#### Tema 8: Uso de aplicaciones externas

-  **Intents implícitos**:
   -  Compartir progreso diario en redes sociales
   -  Enviar estadísticas por email o WhatsApp
   -  Abrir configuración de notificaciones del sistema
   -  Acceder a la cámara para foto de perfil
   -  Abrir navegador para consejos de hidratación externos

#### Tema 9: Emisiones, hilos y servicios

-  **Broadcast Receivers**:
   -  Receiver para detectar cambio de fecha (nuevo día)
   -  Receiver para notificaciones de recordatorio
   -  Receiver para detección de carga de batería
-  **Hilos (Threading)**:
   -  AsyncTask para operaciones de base de datos
   -  Handler para actualización de UI en tiempo real
   -  ExecutorService para tareas en background
-  **Servicios básicos**:
   -  Service para tracking de consumo en background

#### Tema 10: Servicios

-  **Servicios implementados**:
   -  **Foreground Service**: Para recordatorios persistentes de hidratación
   -  **Background Service**: Para sincronización de datos
   -  **Bound Service**: Para comunicación con la actividad principal
   -  **JobScheduler**: Para tareas programadas de limpieza de datos
   -  **WorkManager**: Para tareas diferidas y persistentes

#### Tema 11: Bases de datos

-  **SQLite Database**:
   -  Tabla `usuarios` (id, nombre, peso, meta_diaria, fecha_registro)
   -  Tabla `consumo_agua` (id, fecha, cantidad_ml, hora, tipo_recipiente)
   -  Tabla `recordatorios` (id, hora, activo, mensaje_personalizado)
   -  Tabla `logros` (id, nombre, descripcion, fecha_obtenido, icono)
-  **Room Database** (opcional):
   -  Entities, DAOs y Database para manejo moderno de BD
   -  Queries complejas para estadísticas y reportes

#### Tema 12: Multimedia

-  **Elementos multimedia**:
   -  Sonidos de notificación personalizados para recordatorios
   -  Sonidos de éxito al completar metas diarias
   -  Imágenes animadas (GIFs) para celebrar logros
   -  Videos educativos sobre importancia de la hidratación
   -  Grabación de audio para notas personales sobre el consumo

#### Tema 13: Mapeo

-  **Funcionalidades de mapas**:
   -  Google Maps para localizar fuentes de agua cercanas
   -  Marcadores de lugares donde el usuario toma agua frecuentemente
   -  Rutas a gimnasios, parques o lugares de ejercicio
   -  Geolocalización para recordatorios basados en ubicación
   -  Mapeo de calor mostrando zonas de mayor consumo de agua

#### Tema 14: Sensor

-  **Sensores utilizados**:
   -  **Acelerómetro**: Detectar actividad física para ajustar recordatorios
   -  **Sensor de luz**: Adaptar brillo de la interfaz automáticamente
   -  **Sensor de proximidad**: Pausar recordatorios cuando el teléfono está en uso
   -  **Giroscopio**: Para gestos de navegación dentro de la app
   -  **Sensor de pasos**: Aumentar recordatorios según actividad física

#### Tema 15: Publicación en Google Play

-  **Preparación para publicación**:
   -  Firma de APK con keystore seguro
   -  Generación de App Bundle (AAB)
   -  Configuración de versiones (versionCode y versionName)
   -  Iconos adaptativos para diferentes densidades
   -  Screenshots y assets para Play Store
   -  Descripción optimizada con palabras clave
   -  Política de privacidad y términos de servicio

## Requerimientos No Funcionales

### Rendimiento

-  Tiempo de inicio de aplicación: máximo 3 segundos
-  Respuesta de UI: máximo 100ms para acciones básicas
-  Uso de memoria: máximo 50MB en uso normal
-  Consumo de batería: mínimo impacto en background

### Usabilidad

-  Interfaz intuitiva siguiendo Material Design Guidelines
-  Soporte para modo oscuro y claro
-  Accesibilidad completa (TalkBack, texto grande)
-  Soporte para múltiples idiomas (español e inglés)

### Seguridad

-  Encriptación de datos sensibles del usuario
-  Validación de entradas de usuario
-  Permisos mínimos necesarios
-  Backup seguro de datos

### Compatibilidad

-  Dispositivos con Android 9.0+ (API 28+)
-  Soporte para pantallas de 4" a 7"
-  Orientación portrait y landscape
-  Diferentes densidades de pantalla (mdpi a xxxhdpi)

## Roadmap del Proyecto

### Fase 1: Segundo Parcial (Temas 4-10)

**Entrega**: Final del 2do Parcial

#### Funcionalidades a Implementar:

-  **Tema 4 - Fragmentos y Navegación**:

   -  Fragment principal con estadísticas diarias
   -  Fragment de historial básico
   -  Navigation Drawer básico
   -  Flujo maestro-detalle simple (lista de días → detalle)

-  **Tema 5 - Elementos UI Básicos**:

   -  TextView para mostrar cantidad de agua
   -  Button para registrar consumo
   -  ProgressBar circular para progreso diario
   -  ImageView para iconos de hidratación
   -  SeekBar para ajustar meta diaria

-  **Tema 6 - UI Avanzada**:

   -  RecyclerView para historial de consumo
   -  CardView para mostrar estadísticas
   -  FloatingActionButton para agregar agua rápidamente
   -  Snackbar para confirmaciones

-  **Tema 7 - Transiciones**:

   -  Splash screen con animación
   -  Transiciones básicas entre fragments
   -  Animaciones de botones al presionar
   -  Animación de progreso al completar meta

-  **Tema 8 - Apps Externas**:

   -  Intent para compartir progreso en redes sociales
   -  Intent para enviar estadísticas por email
   -  Intent para acceder a configuración del sistema

-  **Tema 9 - Emisiones e Hilos**:

   -  Broadcast Receiver para cambio de fecha
   -  AsyncTask para operaciones básicas
   -  Handler para actualización de UI en tiempo real

-  **Tema 10 - Servicios**:
   -  Foreground Service para recordatorios persistentes
   -  Bound Service para comunicación con la actividad
   -  Notificaciones básicas de recordatorio

#### Entregables Fase 1:

-  ✅ APK funcional con todas las características mencionadas
-  ✅ Código fuente documentado
-  ✅ Interfaz responsive y atractiva
-  ✅ Sistema de recordatorios funcional
-  ✅ Navegación completa entre pantallas

---

### Fase 2: Tercer Parcial (Temas 11-15)

**Entrega**: Final del 3er Parcial

#### Funcionalidades a Implementar:

-  **Tema 11 - Bases de Datos**:

   -  SQLite con tablas: usuarios, consumo_agua, recordatorios, logros
   -  Implementación de Room Database
   -  Queries complejas para estadísticas avanzadas
   -  Persistencia completa de datos del usuario

-  **Tema 12 - Multimedia**:

   -  Sonidos personalizados para notificaciones
   -  Sonidos de éxito al completar metas
   -  Soporte para reproducir videos educativos
   -  Grabación de notas de audio del usuario
   -  Galería de imágenes motivacionales

-  **Tema 13 - Mapeo**:

   -  Integración con Google Maps
   -  Localización de fuentes de agua cercanas
   -  Marcadores de lugares frecuentes de consumo
   -  Geolocalización para recordatorios contextuales
   -  Rutas a lugares de ejercicio

-  **Tema 14 - Sensores**:

   -  Acelerómetro para detectar actividad física
   -  Sensor de luz para ajuste automático de brillo
   -  Sensor de proximidad para pausar recordatorios
   -  Sensor de pasos para ajustar metas de hidratación
   -  Giroscopio para gestos de navegación

-  **Tema 15 - Publicación Google Play**:
   -  Firma segura del APK/AAB
   -  Iconos adaptativos para todas las densidades
   -  Screenshots profesionales para Play Store
   -  Descripción optimizada con keywords
   -  Política de privacidad y términos de servicio
   -  Preparación completa para publicación

#### Entregables Fase 2:

-  ✅ APK/AAB listo para publicación en Play Store
-  ✅ Base de datos completa con persistencia avanzada
-  ✅ Integración multimedia funcional
-  ✅ Mapas y geolocalización implementados
-  ✅ Sensores integrados y funcionales
-  ✅ Documentación completa para publicación
-  ✅ Assets y materiales de marketing listos

---

### Criterios de Evaluación por Fase

#### Segundo Parcial (60% de la calificación):

-  **Implementación técnica** (40%): Correcta implementación de temas 4-10
-  **Interfaz de usuario** (25%): Diseño atractivo y usabilidad
-  **Funcionalidad** (25%): Características básicas funcionando correctamente
-  **Documentación** (10%): Código comentado y README actualizado

#### Tercer Parcial (40% de la calificación):

-  **Integración avanzada** (35%): Implementación correcta de temas 11-15
-  **Base de datos** (20%): Diseño y implementación de BD completa
-  **Características avanzadas** (25%): Multimedia, mapas y sensores
-  **Preparación para producción** (20%): APK listo para Play Store

### Entrega Final

-  **Aplicación completa** con todos los temas implementados
-  **Código fuente** en repositorio GitHub
-  **APK firmado** listo para instalación
-  **Documentación técnica** completa
-  **Video demostración** de 5-10 minutos mostrando todas las funcionalidades

