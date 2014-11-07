--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.5
-- Dumped by pg_dump version 9.3.5
-- Started on 2014-11-03 17:43:35

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 176 (class 3079 OID 11750)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 1979 (class 0 OID 0)
-- Dependencies: 176
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 177 (class 3079 OID 16592)
-- Name: uuid-ossp; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA public;


--
-- TOC entry 1980 (class 0 OID 0)
-- Dependencies: 177
-- Name: EXTENSION "uuid-ossp"; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION "uuid-ossp" IS 'generate universally unique identifiers (UUIDs)';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 170 (class 1259 OID 16603)
-- Name: approle; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE approle (
    id integer NOT NULL,
    name text
);


ALTER TABLE public.approle OWNER TO postgres;

--
-- TOC entry 171 (class 1259 OID 16609)
-- Name: approle_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE approle_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.approle_id_seq OWNER TO postgres;

--
-- TOC entry 1981 (class 0 OID 0)
-- Dependencies: 171
-- Name: approle_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE approle_id_seq OWNED BY approle.id;


--
-- TOC entry 172 (class 1259 OID 16611)
-- Name: appuser; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE appuser (
    id integer NOT NULL,
    firstname text,
    lastname text,
    username text NOT NULL
);


ALTER TABLE public.appuser OWNER TO postgres;

--
-- TOC entry 174 (class 1259 OID 16620)
-- Name: appuser_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE appuser_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.appuser_id_seq OWNER TO postgres;

--
-- TOC entry 1982 (class 0 OID 0)
-- Dependencies: 174
-- Name: appuser_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE appuser_id_seq OWNED BY appuser.id;


--
-- TOC entry 173 (class 1259 OID 16617)
-- Name: appuserrole; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE appuserrole (
    appuserid integer NOT NULL,
    approleid integer NOT NULL
);


ALTER TABLE public.appuserrole OWNER TO postgres;

--
-- TOC entry 175 (class 1259 OID 16622)
-- Name: authtoken; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE authtoken (
    appuserid integer NOT NULL,
    token text NOT NULL,
    expirationdate timestamp with time zone NOT NULL
);


ALTER TABLE public.authtoken OWNER TO postgres;

--
-- TOC entry 1851 (class 2604 OID 16628)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY approle ALTER COLUMN id SET DEFAULT nextval('approle_id_seq'::regclass);


--
-- TOC entry 1852 (class 2604 OID 16629)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY appuser ALTER COLUMN id SET DEFAULT nextval('appuser_id_seq'::regclass);


--
-- TOC entry 1854 (class 2606 OID 16631)
-- Name: approle_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY approle
    ADD CONSTRAINT approle_pkey PRIMARY KEY (id);


--
-- TOC entry 1856 (class 2606 OID 16637)
-- Name: appuser_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY appuser
    ADD CONSTRAINT appuser_pkey PRIMARY KEY (id);


--
-- TOC entry 1858 (class 2606 OID 16635)
-- Name: appuser_username_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY appuser
    ADD CONSTRAINT appuser_username_key UNIQUE (username);


--
-- TOC entry 1860 (class 2606 OID 16633)
-- Name: appuserrole_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY appuserrole
    ADD CONSTRAINT appuserrole_pkey PRIMARY KEY (appuserid, approleid);


--
-- TOC entry 1862 (class 2606 OID 16639)
-- Name: authtoken_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY authtoken
    ADD CONSTRAINT authtoken_pkey PRIMARY KEY (token);


--
-- TOC entry 1863 (class 2606 OID 16640)
-- Name: appuserrole_approleid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY appuserrole
    ADD CONSTRAINT appuserrole_approleid_fkey FOREIGN KEY (approleid) REFERENCES approle(id);


--
-- TOC entry 1864 (class 2606 OID 16645)
-- Name: appuserrole_appuserid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY appuserrole
    ADD CONSTRAINT appuserrole_appuserid_fkey FOREIGN KEY (appuserid) REFERENCES appuser(id);


--
-- TOC entry 1978 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2014-11-03 17:43:35

--
-- PostgreSQL database dump complete
--

