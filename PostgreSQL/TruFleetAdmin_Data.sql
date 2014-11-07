--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.5
-- Dumped by pg_dump version 9.3.5
-- Started on 2014-11-03 17:45:44

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = public, pg_catalog;

--
-- TOC entry 1951 (class 0 OID 16689)
-- Dependencies: 170
-- Data for Name: organization; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY organization ("Name", tenantid, databaseurl, apiversion) FROM stdin;
TestOrg	46139f84-b7e3-4e35-afdc-849508d74c2f	\N	0.1
\.


--
-- TOC entry 1952 (class 0 OID 16696)
-- Dependencies: 171
-- Data for Name: userlogin; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY userlogin (username, password, organizationid) FROM stdin;
testuser1	test	46139f84-b7e3-4e35-afdc-849508d74c2f
\.


-- Completed on 2014-11-03 17:45:44

--
-- PostgreSQL database dump complete
--

