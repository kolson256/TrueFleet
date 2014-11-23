--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- Name: uuid-ossp; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA public;


--
-- Name: EXTENSION "uuid-ossp"; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION "uuid-ossp" IS 'generate universally unique identifiers (UUIDs)';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: approle; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE approle (
    id integer NOT NULL,
    name text
);


ALTER TABLE public.approle OWNER TO postgres;

--
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
-- Name: approle_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE approle_id_seq OWNED BY approle.id;


--
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
-- Name: appuser_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE appuser_id_seq OWNED BY appuser.id;


--
-- Name: appuserrole; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE appuserrole (
    appuserid integer NOT NULL,
    approleid integer NOT NULL
);


ALTER TABLE public.appuserrole OWNER TO postgres;

--
-- Name: authtoken; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE authtoken (
    appuserid integer NOT NULL,
    token text NOT NULL,
    expirationdate timestamp with time zone NOT NULL
);


ALTER TABLE public.authtoken OWNER TO postgres;

--
-- Name: contactentry; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE contactentry (
    id uuid NOT NULL,
    name text,
    address text,
    notes text
);


ALTER TABLE public.contactentry OWNER TO postgres;

--
-- Name: containerload; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE containerload (
    containerid text,
    seal text,
    pieces integer,
    weight integer,
    shipdate text
);


ALTER TABLE public.containerload OWNER TO postgres;

--
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


ALTER TABLE public.imtorder OWNER TO postgres;

--
-- Name: intermodalcontainer; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE intermodalcontainer (
    id text NOT NULL
);


ALTER TABLE public.intermodalcontainer OWNER TO postgres;

--
-- Name: phoneentry; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE phoneentry (
    type text,
    number text
);


ALTER TABLE public.phoneentry OWNER TO postgres;

--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY approle ALTER COLUMN id SET DEFAULT nextval('approle_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY appuser ALTER COLUMN id SET DEFAULT nextval('appuser_id_seq'::regclass);


--
-- Name: approle_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY approle
    ADD CONSTRAINT approle_pkey PRIMARY KEY (id);


--
-- Name: appuser_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY appuser
    ADD CONSTRAINT appuser_pkey PRIMARY KEY (id);


--
-- Name: appuser_username_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY appuser
    ADD CONSTRAINT appuser_username_key UNIQUE (username);


--
-- Name: appuserrole_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY appuserrole
    ADD CONSTRAINT appuserrole_pkey PRIMARY KEY (appuserid, approleid);


--
-- Name: authtoken_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY authtoken
    ADD CONSTRAINT authtoken_pkey PRIMARY KEY (token);


--
-- Name: appuserrole_approleid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY appuserrole
    ADD CONSTRAINT appuserrole_approleid_fkey FOREIGN KEY (approleid) REFERENCES approle(id);


--
-- Name: appuserrole_appuserid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY appuserrole
    ADD CONSTRAINT appuserrole_appuserid_fkey FOREIGN KEY (appuserid) REFERENCES appuser(id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

