--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.5
-- Dumped by pg_dump version 9.3.5
-- Started on 2014-11-01 13:15:41

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
-- TOC entry 177 (class 3079 OID 16543)
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
-- TOC entry 170 (class 1259 OID 16465)
-- Name: AppRole; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE "AppRole" (
    "Id" integer NOT NULL,
    "Name" text
);


ALTER TABLE public."AppRole" OWNER TO postgres;

--
-- TOC entry 171 (class 1259 OID 16471)
-- Name: AppRole_Id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "AppRole_Id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."AppRole_Id_seq" OWNER TO postgres;

--
-- TOC entry 1981 (class 0 OID 0)
-- Dependencies: 171
-- Name: AppRole_Id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "AppRole_Id_seq" OWNED BY "AppRole"."Id";


--
-- TOC entry 172 (class 1259 OID 16473)
-- Name: AppUser; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE "AppUser" (
    "Id" integer NOT NULL,
    "FirstName" text,
    "LastName" text,
    "UserName" text NOT NULL
);


ALTER TABLE public."AppUser" OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 16479)
-- Name: AppUserRole; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE "AppUserRole" (
    "AppUserId" integer NOT NULL,
    "AppRoleId" integer NOT NULL
);


ALTER TABLE public."AppUserRole" OWNER TO postgres;

--
-- TOC entry 174 (class 1259 OID 16482)
-- Name: AppUser_Id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "AppUser_Id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."AppUser_Id_seq" OWNER TO postgres;

--
-- TOC entry 1982 (class 0 OID 0)
-- Dependencies: 174
-- Name: AppUser_Id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "AppUser_Id_seq" OWNED BY "AppUser"."Id";


--
-- TOC entry 175 (class 1259 OID 16484)
-- Name: AuthToken; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE "AuthToken" (
    "AppUserId" integer NOT NULL,
    "Token" text NOT NULL,
    "ExpirationDate" timestamp with time zone NOT NULL
);


ALTER TABLE public."AuthToken" OWNER TO postgres;

--
-- TOC entry 1851 (class 2604 OID 16490)
-- Name: Id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "AppRole" ALTER COLUMN "Id" SET DEFAULT nextval('"AppRole_Id_seq"'::regclass);


--
-- TOC entry 1852 (class 2604 OID 16491)
-- Name: Id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "AppUser" ALTER COLUMN "Id" SET DEFAULT nextval('"AppUser_Id_seq"'::regclass);


--
-- TOC entry 1854 (class 2606 OID 16493)
-- Name: AppRole_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "AppRole"
    ADD CONSTRAINT "AppRole_pkey" PRIMARY KEY ("Id");


--
-- TOC entry 1860 (class 2606 OID 16495)
-- Name: AppUserRole_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "AppUserRole"
    ADD CONSTRAINT "AppUserRole_pkey" PRIMARY KEY ("AppUserId", "AppRoleId");


--
-- TOC entry 1856 (class 2606 OID 16497)
-- Name: AppUser_UserName_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "AppUser"
    ADD CONSTRAINT "AppUser_UserName_key" UNIQUE ("UserName");


--
-- TOC entry 1858 (class 2606 OID 16499)
-- Name: AppUser_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "AppUser"
    ADD CONSTRAINT "AppUser_pkey" PRIMARY KEY ("Id");


--
-- TOC entry 1862 (class 2606 OID 16501)
-- Name: AuthToken_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "AuthToken"
    ADD CONSTRAINT "AuthToken_pkey" PRIMARY KEY ("Token");


--
-- TOC entry 1863 (class 2606 OID 16502)
-- Name: AppUserRole_AppRoleId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "AppUserRole"
    ADD CONSTRAINT "AppUserRole_AppRoleId_fkey" FOREIGN KEY ("AppRoleId") REFERENCES "AppRole"("Id");


--
-- TOC entry 1864 (class 2606 OID 16507)
-- Name: AppUserRole_AppUserId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "AppUserRole"
    ADD CONSTRAINT "AppUserRole_AppUserId_fkey" FOREIGN KEY ("AppUserId") REFERENCES "AppUser"("Id");


--
-- TOC entry 1978 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2014-11-01 13:15:41

--
-- PostgreSQL database dump complete
--

