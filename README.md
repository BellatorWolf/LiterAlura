# LiterAlura - Aplicación consola con Spring Boot y PostgreSQL en Docker

Proyecto de ejemplo que permite buscar y gestionar libros usando Spring Boot en consola, conectado a PostgreSQL, todo corriendo en contenedores Docker.

---

## Requisitos

- Docker y Docker Compose instalados
- JDK 21 para build local (opcional)
- Maven para build local (opcional)

---

## Estructura del proyecto

- `Dockerfile` : Construye la imagen de la app Java
- `docker-compose.yml` : Orquesta los servicios (PostgreSQL + app)
- Código fuente en `src/main/java`

---

## Construcción y ejecución

### Build del JAR (local)

Desde la raíz del proyecto, en IntelliJ o terminal:

```bash
./mvnw clean package -DskipTests
```

Esto generará el archivo JAR ejecutable en `target/literalura-1.0.0.jar`.

---

### Uso de Docker Compose

El archivo `docker-compose.yml` define dos servicios:

- `liter-postgre`: base de datos PostgreSQL con volumen persistente
- `liter-app`: aplicación Java construida a partir del `Dockerfile`

#### Levantar los servicios

Para construir las imágenes y levantar los servicios en primer plano (ver consola e interactuar con la app):

```bash
docker compose up --build
```

Si no quieres construir imágenes (ya están listas) y solo quieres levantar:

```bash
docker compose up
```

#### Ejecutar en segundo plano (detached)

Si prefieres que los servicios corran en background (sin mostrar consola ni interacción directa):

```bash
docker compose up -d
```

Para ver los logs después:

```bash
docker compose logs -f
```

#### Detener los servicios

Para parar todos los servicios levantados con Docker Compose:

```bash
docker compose down
```

Esto detiene y elimina los contenedores creados por el compose.

---

## Configuración de base de datos

El servicio PostgreSQL está configurado con volumen persistente para no perder datos:

```yaml
volumes:
  pgdata:
```

El volumen está montado en `/var/lib/postgresql/data`.

---

## Comandos útiles Docker

Listar contenedores activos:

```bash
docker ps
```

Listar todos (incluyendo parados):

```bash
docker ps -a
```

Detener contenedor:

```bash
docker stop liter-app
```

Eliminar contenedor parado:

```bash
docker rm liter-app
```

Ver logs de contenedor:

```bash
docker logs -f liter-app
```

---

## Notas importantes

- La app es consola, no tiene interfaz web ni puerto expuesto.
- Para interactuar, debe ejecutarse con terminal activa (`stdin_open: true`, `tty: true`).
- No usar `-d` si quieres ver y usar la app desde la consola.
- Para convertir esta app en una aplicación web, se requiere modificar para exponer un puerto y agregar controladores HTTP.

---

## Autor

**HEAM** - Héctor Elihú Arias Mercado  
`virtuosi.digitales.ballatores.liter`  
**Fecha:** 2025-08-04