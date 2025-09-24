-- init-schema.sql
-- Créer le schéma keycloak_schema
CREATE SCHEMA IF NOT EXISTS keycloak_schema;

-- Donner les permissions à l'utilisateur pleingaz
GRANT ALL PRIVILEGES ON SCHEMA keycloak_schema TO pleingaz;
