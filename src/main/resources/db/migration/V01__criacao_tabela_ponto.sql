-- SQL Manager for PostgreSQL 5.4.0.42613
-- ---------------------------------------
-- Host      : localhost
-- Database  : db_xy_inc
-- Version   : PostgreSQL 9.6.3, compiled by Visual C++ build 1800, 64-bit

SET search_path = public, pg_catalog;
DROP TABLE IF EXISTS public.ponto;
--
-- Structure for table ponto (OID = 50764) : 
--
CREATE TABLE public."ponto" (
    "pontoid" serial NOT NULL,
    nome text NOT NULL,
    "coordenadax" integer NOT NULL,
    "coordenaday" integer NOT NULL
)
WITH (oids = false);
ALTER TABLE ONLY public."ponto" ALTER COLUMN "coordenadax" SET STATISTICS 0;
--
-- Definition for index Ponto_pkey (OID = 50771) : 
--
ALTER TABLE ONLY "ponto"
    ADD CONSTRAINT "Ponto_pkey"
    PRIMARY KEY ("pontoid");
--
-- Data for sequence public."ponto_pontoid_seq" (OID = 50762)
--
SELECT pg_catalog.setval('"ponto_pontoid_seq"', 1, false);
--
-- Comments
--
COMMENT ON SCHEMA public IS 'standard public schema';
