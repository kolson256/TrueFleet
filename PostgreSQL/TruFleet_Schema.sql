--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.5
-- Dumped by pg_dump version 9.3.5
-- Started on 2014-11-01 03:57:57

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
-- TOC entry 1968 (class 0 OID 0)
-- Dependencies: 176
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 173 (class 1259 OID 16417)
-- Name: AppRole; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE "AppRole" (
    "Id" integer NOT NULL,
    "Name" text
);


ALTER TABLE public."AppRole" OWNER TO postgres;

--
-- TOC entry 172 (class 1259 OID 16415)
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
-- TOC entry 1969 (class 0 OID 0)
-- Dependencies: 172
-- Name: AppRole_Id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "AppRole_Id_seq" OWNED BY "AppRole"."Id";


--
-- TOC entry 170 (class 1259 OID 16399)
-- Name: AppUser; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE "AppUser" (
    "Id" integer NOT NULL,
    "FirstName" text,
    "LastName" text,
    "UserName" text,
    "Password" text
);


ALTER TABLE public."AppUser" OWNER TO postgres;

--
-- TOC entry 175 (class 1259 OID 16449)
-- Name: AppUserRole; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE "AppUserRole" (
    "AppUserId" integer NOT NULL,
    "AppRoleId" integer NOT NULL
);


ALTER TABLE public."AppUserRole" OWNER TO postgres;

--
-- TOC entry 171 (class 1259 OID 16402)
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
-- TOC entry 1970 (class 0 OID 0)
-- Dependencies: 171
-- Name: AppUser_Id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "AppUser_Id_seq" OWNED BY "AppUser"."Id";


--
-- TOC entry 174 (class 1259 OID 16441)
-- Name: AuthToken; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE "AuthToken" (
    "AppUserId" integer NOT NULL,
    "Token" text NOT NULL,
    "ExpirationDate" timestamp with time zone NOT NULL
);


ALTER TABLE public."AuthToken" OWNER TO postgres;

--
-- TOC entry 1841 (class 2604 OID 16420)
-- Name: Id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "AppRole" ALTER COLUMN "Id" SET DEFAULT nextval('"AppRole_Id_seq"'::regclass);


--
-- TOC entry 1840 (class 2604 OID 16404)
-- Name: Id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "AppUser" ALTER COLUMN "Id" SET DEFAULT nextval('"AppUser_Id_seq"'::regclass);


--
-- TOC entry 1847 (class 2606 OID 16425)
-- Name: AppRole_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "AppRole"
    ADD CONSTRAINT "AppRole_pkey" PRIMARY KEY ("Id");


--
-- TOC entry 1851 (class 2606 OID 16453)
-- Name: AppUserRole_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "AppUserRole"
    ADD CONSTRAINT "AppUserRole_pkey" PRIMARY KEY ("AppUserId", "AppRoleId");


--
-- TOC entry 1843 (class 2606 OID 16414)
-- Name: AppUser_UserName_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "AppUser"
    ADD CONSTRAINT "AppUser_UserName_key" UNIQUE ("UserName");


--
-- TOC entry 1845 (class 2606 OID 16409)
-- Name: AppUser_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "AppUser"
    ADD CONSTRAINT "AppUser_pkey" PRIMARY KEY ("Id");


--
-- TOC entry 1849 (class 2606 OID 16448)
-- Name: AuthToken_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "AuthToken"
    ADD CONSTRAINT "AuthToken_pkey" PRIMARY KEY ("Token");


--
-- TOC entry 1852 (class 2606 OID 16454)
-- Name: AppUserRole_AppRoleId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "AppUserRole"
    ADD CONSTRAINT "AppUserRole_AppRoleId_fkey" FOREIGN KEY ("AppRoleId") REFERENCES "AppRole"("Id");


--
-- TOC entry 1853 (class 2606 OID 16459)
-- Name: AppUserRole_AppUserId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "AppUserRole"
    ADD CONSTRAINT "AppUserRole_AppUserId_fkey" FOREIGN KEY ("AppUserId") REFERENCES "AppUser"("Id");


--
-- TOC entry 1967 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2014-11-01 03:57:57

--
-- PostgreSQL database dump complete
--

