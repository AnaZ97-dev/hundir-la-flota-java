# Hundir la Flota (Java)

Proyecto de programación en Java que implementa una versión simplificada del clásico juego **Hundir la Flota**. El jugador debe hundir todos los barcos del ordenador en un número limitado de intentos.

## 🛠️ Tecnologías y Herramientas
*   **Lenguaje:** Java (JDK 17)
*   **IDE:** Apache NetBeans
*   **Entorno:** Consola de texto

## 🎯 Funcionalidades y Conceptos Aplicados
*   **Tablero 10x10:** Representación del tablero con matrices (`char[][]` o `String[][]`).
*   **Tipos de barcos:** Lancha (L), Buque (B), Acorazado (Z) y Portaaviones (P).
*   **Colocación aleatoria:** Uso de `Math.random()` para posicionar los barcos sin solaparse.
*   **Sistema de disparos:** El usuario introduce coordenadas (fila y columna) y el programa responde con "Tocado", "Agua" o "Hundido".
*   **Niveles de dificultad:** Fácil, Medio, Difícil y Personalizado (con diferente número de barcos e intentos).
*   **Funciones:** Uso de métodos para estructurar el código (crear_tablero, insertar_barco, disparo, mostrar_tablero, etc.).
*   **Manejo de errores:** Validación de entradas del usuario (coordenadas fuera del tablero, etc.).

## 🚀 Cómo ejecutar el proyecto
1. Abre el proyecto en Apache NetBeans.
2. Ejecuta la clase principal (`Main` o la que corresponda).
3. Selecciona el nivel de dificultad y empieza a disparar introduciendo las coordenadas.
