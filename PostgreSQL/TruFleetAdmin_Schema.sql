--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.5
-- Dumped by pg_dump version 9.3.5
-- Started on 2014-11-01 13:16:19

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 173 (class 3079 OID 11750)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 1961 (class 0 OID 0)
-- Dependencies: 173
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 174 (class 3079 OID 16532)
-- Name: uuid-ossp; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA public;


--
-- TOC entry 1962 (class 0 OID 0)
-- Dependencies: 174
-- Name: EXTENSION "uuid-ossp"; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION "uuid-ossp" IS 'generate universally unique identifiers (UUIDs)';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 170 (class 1259 OID 16513)
-- Name: Organization; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE "Organization" (
    "Name" text,
    "TenantId" uuid DEFAULT uuid_generate_v4() NOT NULL,
    "DatabaseURL" text,
    "APIVersion" text,
    "Id" integer NOT NULL
);


ALTER TABLE public."Organization" OWNER TO postgres;

--
-- TOC entry 171 (class 1259 OID 16563)
-- Name: Organization_Id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "Organization_Id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Organization_Id_seq" OWNER TO postgres;

--
-- TOC entry 1963 (class 0 OID 0)
-- Dependencies: 171
-- Name: Organization_Id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "Organization_Id_seq" OWNED BY "Organization"."Id";


--
-- TOC entry 172 (class 1259 OID 16577)
-- Name: UserLogin; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE "UserLogin" (
    "UserName" text NOT NULL,
    "Password" text NOT NULL,
    "OrganizationId" uuid NOT NULL
);


ALTER TABLE public."UserLogin" OWNER TO postgres;

--
-- TOC entry 1841 (class 2604 OID 16565)
-- Name: Id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "Organization" ALTER COLUMN "Id" SET DEFAULT nextval('"Organization_Id_seq"'::regclass);


--
-- TOC entry 1843 (class 2606 OID 16576)
-- Name: Organization_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "Organization"
    ADD CONSTRAINT "Organization_pkey" PRIMARY KEY ("TenantId");


--
-- TOC entry 1845 (class 2606 OID 16584)
-- Name: UserLogin_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "UserLogin"
    ADD CONSTRAINT "UserLogin_pkey" PRIMARY KEY ("UserName");


--
-- TOC entry 1846 (class 2606 OID 16585)
-- Name: UserLogin_OrganizationId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "UserLogin"
    ADD CONSTRAINT "UserLogin_OrganizationId_fkey" FOREIGN KEY ("OrganizationId") REFERENCES "Organization"("TenantId");


--
-- TOC entry 1960 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2014-11-01 13:16:19

--
-- PostgreSQL database dump complete
--

