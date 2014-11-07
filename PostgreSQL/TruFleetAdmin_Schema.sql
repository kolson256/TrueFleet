--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.5
-- Dumped by pg_dump version 9.3.5
-- Started on 2014-11-03 17:45:23

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 172 (class 3079 OID 11750)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 1958 (class 0 OID 0)
-- Dependencies: 172
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 173 (class 3079 OID 16678)
-- Name: uuid-ossp; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA public;


--
-- TOC entry 1959 (class 0 OID 0)
-- Dependencies: 173
-- Name: EXTENSION "uuid-ossp"; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION "uuid-ossp" IS 'generate universally unique identifiers (UUIDs)';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 170 (class 1259 OID 16689)
-- Name: organization; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE organization (
    "name" text,
    tenantid text DEFAULT uuid_generate_v4() NOT NULL,
    databaseurl text,
    apiversion text
);


ALTER TABLE public.organization OWNER TO postgres;

--
-- TOC entry 171 (class 1259 OID 16696)
-- Name: userlogin; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE userlogin (
    username text NOT NULL,
    password text NOT NULL,
    organizationid text NOT NULL
);


ALTER TABLE public.userlogin OWNER TO postgres;

--
-- TOC entry 1840 (class 2606 OID 16703)
-- Name: organization_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY organization
    ADD CONSTRAINT organization_pkey PRIMARY KEY (tenantid);


--
-- TOC entry 1842 (class 2606 OID 16705)
-- Name: userlogin_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY userlogin
    ADD CONSTRAINT userlogin_pkey PRIMARY KEY (username);


--
-- TOC entry 1843 (class 2606 OID 16706)
-- Name: userlogin_organizationid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY userlogin
    ADD CONSTRAINT userlogin_organizationid_fkey FOREIGN KEY (organizationid) REFERENCES organization(tenantid);


--
-- TOC entry 1957 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2014-11-03 17:45:23

--
-- PostgreSQL database dump complete
--

