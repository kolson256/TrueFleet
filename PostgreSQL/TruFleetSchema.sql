--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.5
-- Dumped by pg_dump version 9.4beta3
-- Started on 2014-11-23 14:14:19

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 181 (class 3079 OID 11750)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2024 (class 0 OID 0)
-- Dependencies: 181
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 182 (class 3079 OID 41671)
-- Name: uuid-ossp; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA public;


--
-- TOC entry 2025 (class 0 OID 0)
-- Dependencies: 182
-- Name: EXTENSION "uuid-ossp"; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION "uuid-ossp" IS 'generate universally unique identifiers (UUIDs)';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 170 (class 1259 OID 41682)
-- Name: approle; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE approle (
    id integer NOT NULL,
    name text
);


ALTER TABLE approle OWNER TO postgres;

--
-- TOC entry 171 (class 1259 OID 41688)
-- Name: approle_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE approle_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE approle_id_seq OWNER TO postgres;

--
-- TOC entry 2026 (class 0 OID 0)
-- Dependencies: 171
-- Name: approle_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE approle_id_seq OWNED BY approle.id;


--
-- TOC entry 172 (class 1259 OID 41690)
-- Name: appuser; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE appuser (
    id integer NOT NULL,
    firstname text,
    lastname text,
    username text NOT NULL,
    registrationid text
);


ALTER TABLE appuser OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 41696)
-- Name: appuser_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE appuser_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE appuser_id_seq OWNER TO postgres;

--
-- TOC entry 2027 (class 0 OID 0)
-- Dependencies: 173
-- Name: appuser_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE appuser_id_seq OWNED BY appuser.id;


--
-- TOC entry 174 (class 1259 OID 41698)
-- Name: appuserrole; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE appuserrole (
    appuserid integer NOT NULL,
    approleid integer NOT NULL
);


ALTER TABLE appuserrole OWNER TO postgres;

--
-- TOC entry 175 (class 1259 OID 41701)
-- Name: authtoken; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE authtoken (
    appuserid integer NOT NULL,
    token text NOT NULL,
    expirationdate timestamp with time zone NOT NULL
);


ALTER TABLE authtoken OWNER TO postgres;

--
-- TOC entry 176 (class 1259 OID 41707)
-- Name: contactentry; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE contactentry (
    id uuid NOT NULL,
    name text,
    address text,
    notes text
);


ALTER TABLE contactentry OWNER TO postgres;

--
-- TOC entry 177 (class 1259 OID 41713)
-- Name: containerload; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE containerload (
    containerid text NOT NULL,
    seal text,
    pieces integer,
    weight integer,
    shipdate text
);


ALTER TABLE containerload OWNER TO postgres;

--
-- TOC entry 178 (class 1259 OID 41719)
-- Name: imtorder; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE imtorder (
    internalid uuid NOT NULL,
    externalid text,
    containerid text,
    receipttime bigint,
    ordertype text,
    railline text,
    pickupcontact text,
    dropoffcontact text,
    deliverywindowopen text,
    deliverywindowclose text
);


ALTER TABLE imtorder OWNER TO postgres;

--
-- TOC entry 179 (class 1259 OID 41725)
-- Name: intermodalcontainer; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE intermodalcontainer (
    id text NOT NULL
);


ALTER TABLE intermodalcontainer OWNER TO postgres;

--
-- TOC entry 180 (class 1259 OID 41731)
-- Name: phoneentry; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE phoneentry (
    type text,
    number text
);


ALTER TABLE phoneentry OWNER TO postgres;

--
-- TOC entry 1876 (class 2604 OID 41737)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY approle ALTER COLUMN id SET DEFAULT nextval('approle_id_seq'::regclass);


--
-- TOC entry 1877 (class 2604 OID 41738)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY appuser ALTER COLUMN id SET DEFAULT nextval('appuser_id_seq'::regclass);



--
-- TOC entry 2028 (class 0 OID 0)
-- Dependencies: 171
-- Name: approle_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('approle_id_seq', 1, false);




--
-- TOC entry 2029 (class 0 OID 0)
-- Dependencies: 173
-- Name: appuser_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('appuser_id_seq', 1, false);


--
-- TOC entry 1879 (class 2606 OID 41740)
-- Name: approle_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY approle
    ADD CONSTRAINT approle_pkey PRIMARY KEY (id);


--
-- TOC entry 1881 (class 2606 OID 41742)
-- Name: appuser_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY appuser
    ADD CONSTRAINT appuser_pkey PRIMARY KEY (id);


--
-- TOC entry 1883 (class 2606 OID 41744)
-- Name: appuser_username_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY appuser
    ADD CONSTRAINT appuser_username_key UNIQUE (username);


--
-- TOC entry 1885 (class 2606 OID 41746)
-- Name: appuserrole_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY appuserrole
    ADD CONSTRAINT appuserrole_pkey PRIMARY KEY (appuserid, approleid);


--
-- TOC entry 1887 (class 2606 OID 41748)
-- Name: authtoken_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY authtoken
    ADD CONSTRAINT authtoken_pkey PRIMARY KEY (token);


--
-- TOC entry 1889 (class 2606 OID 41760)
-- Name: contactentry_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY contactentry
    ADD CONSTRAINT contactentry_pkey PRIMARY KEY (id);


--
-- TOC entry 1891 (class 2606 OID 41762)
-- Name: containerload_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY containerload
    ADD CONSTRAINT containerload_pkey PRIMARY KEY (containerid);


--
-- TOC entry 1893 (class 2606 OID 41764)
-- Name: imtorder_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY imtorder
    ADD CONSTRAINT imtorder_pkey PRIMARY KEY (internalid);


--
-- TOC entry 1895 (class 2606 OID 41766)
-- Name: intermodalcontainer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY intermodalcontainer
    ADD CONSTRAINT intermodalcontainer_pkey PRIMARY KEY (id);


--
-- TOC entry 1896 (class 2606 OID 41749)
-- Name: appuserrole_approleid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY appuserrole
    ADD CONSTRAINT appuserrole_approleid_fkey FOREIGN KEY (approleid) REFERENCES approle(id);


--
-- TOC entry 1897 (class 2606 OID 41754)
-- Name: appuserrole_appuserid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY appuserrole
    ADD CONSTRAINT appuserrole_appuserid_fkey FOREIGN KEY (appuserid) REFERENCES appuser(id);


--
-- TOC entry 1898 (class 2606 OID 41767)
-- Name: imtorder_containerid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY imtorder
    ADD CONSTRAINT imtorder_containerid_fkey FOREIGN KEY (containerid) REFERENCES containerload(containerid);


--
-- TOC entry 2023 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2014-11-23 14:14:19

--
-- PostgreSQL database dump complete
--

