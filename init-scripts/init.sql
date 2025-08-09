-- Crear usuario con contraseña
CREATE USER bibliotecario WITH PASSWORD 'liter41ur4';

-- Crear esquema propio para el usuario dentro de la base 'liter'
CREATE SCHEMA bibliotecario AUTHORIZATION bibliotecario;

-- Otorgar todos los privilegios sobre el esquema al usuario
GRANT ALL PRIVILEGES ON SCHEMA bibliotecario TO bibliotecario;
