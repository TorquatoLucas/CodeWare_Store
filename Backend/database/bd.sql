-- Cria o banco de dados
CREATE DATABASE codeware;

-- Cria usuário adm com senha adm e privilégios de superusuário
CREATE ROLE adm WITH LOGIN PASSWORD 'adm' SUPERUSER;
