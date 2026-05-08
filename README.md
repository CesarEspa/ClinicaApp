# ClinicaApp 🏥

Sistema de Gestión de Turnos Médicos desarrollado en Java aplicando Programación Orientada a Objetos (OOP) y persistencia de datos mediante archivos CSV.


---

# 📌 Características

- Registro de pacientes
- Registro de médicos
- Asignación de turnos médicos
- Cancelación de turnos
- Cambio de estado de turnos
- Consulta de turnos por paciente
- Consulta de turnos por médico
- Listado de turnos del día
- Persistencia automática en archivos CSV
- Validaciones de negocio
- Arquitectura organizada por paquetes

---

# 🛠 Tecnologías utilizadas

- Java 17+
- IntelliJ IDEA
- Programación Orientada a Objetos (OOP)
- Persistencia con archivos CSV
- Java Collections Framework

---

# 📂 Estructura del proyecto

```bash
clinicaapp/
│
├── src/
│   └── co/generation/clinica/
│
│       ├── model/
│       │   ├── Paciente.java
│       │   ├── Medico.java
│       │   ├── Turno.java
│       │   ├── EstadoTurno.java
│       │   └── Especialidad.java
│       │
│       ├── interfaces/
│       │   ├── Registrable.java
│       │   └── Consultable.java
│       │
│       ├── service/
│       │   └── ClinicaService.java
│       │
│       ├── datos/
│       │   └── DatosCSV.java
│       │
│       └── Main.java
│
├── datos/
│   ├── pacientes.csv
│   ├── medicos.csv
│   └── turnos.csv
│
└── README.md
```

---

# 🧩 Modelo del sistema

## 👤 Paciente

Contiene:

- ID
- Cédula
- Nombre
- Apellido
- Teléfono

### Validaciones

- Cédula única
- Campos obligatorios
- Teléfono válido con regex:

```regex
^[0-9]{7,10}$
```

---

## 👨‍⚕️ Médico

Contiene:

- ID
- Nombre
- Apellido
- Especialidad

### Especialidades disponibles

- GENERAL
- PEDIATRIA
- CARDIOLOGIA
- URGENCIAS

---

## 📅 Turno

Relaciona:

- Paciente
- Médico
- Fecha y hora
- Estado del turno

### Estados disponibles

- PENDIENTE
- ATENDIDO
- CANCELADO

---

# ⚙️ Funcionalidades principales

## ✅ Registrar paciente

Permite:

- Validar datos obligatorios
- Evitar cédulas duplicadas
- Validar formato de teléfono

---

## ✅ Registrar médico

Permite registrar médicos evitando duplicados.

---

## ✅ Asignar turno

Valida:

- Existencia del paciente
- Existencia del médico
- Disponibilidad del médico

---

## ✅ Cancelar turno

Solo permite cancelar turnos en estado:

- PENDIENTE

---

## ✅ Cambiar estado del turno

Permite actualizar el estado del turno.

---

# 💾 Persistencia de datos

El sistema guarda automáticamente la información en:

- `pacientes.csv`
- `medicos.csv`
- `turnos.csv`

Los archivos se generan automáticamente en la carpeta:

```bash
datos/
```

---

# ▶️ Cómo ejecutar el proyecto

## 1️⃣ Clonar el repositorio

```bash
git clone https://github.com/CesarEspa/ClinicaApp.git
```

---

## 2️⃣ Abrir el proyecto en IntelliJ IDEA

1. Abrir IntelliJ IDEA
2. Seleccionar:
   - **Open**
3. Elegir la carpeta del proyecto

---

## 3️⃣ Configurar Java

Ir a:

```text
File > Project Structure > SDK
```

Seleccionar Java 17 o superior.

---

## 4️⃣ Ejecutar el proyecto

Ejecutar:

```text
Main.java
```

---

# 📋 Menú del sistema

```text
1. Registrar paciente
2. Registrar médico
3. Asignar turno
4. Listar turnos del día
5. Cancelar turno
6. Ver turnos por médico
7. Ver turnos por paciente
8. Cambiar estado de turno
9. Listar pacientes
10. Listar médicos
0. Salir
```

---

# 🧠 Conceptos de OOP implementados

- Encapsulamiento
- Abstracción
- Interfaces
- Enumeraciones (`enum`)
- Sobrescritura de métodos
- Validaciones en setters
- Relaciones entre objetos
- Manejo de colecciones
- Persistencia en archivos CSV

---

# 👥 Equipo de desarrollo — Grupo 3

- Cesar España
- Robinson Salamanca
- Cristian Ariza
- Daniel Vega

---

# 📌 Consideraciones

- Los archivos CSV se generan automáticamente
- El sistema funciona completamente en consola
- No utiliza frameworks externos
- No requiere base de datos

---

# 📄 Licencia

Proyecto académico desarrollado para el Hackathon de Generation Colombia.
