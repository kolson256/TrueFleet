--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.5
-- Dumped by pg_dump version 9.3.5
-- Started on 2014-11-03 17:44:30

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = public, pg_catalog;

--
-- TOC entry 1972 (class 0 OID 16603)
-- Dependencies: 170
-- Data for Name: approle; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY approle (id, name) FROM stdin;
\.


--
-- TOC entry 1982 (class 0 OID 0)
-- Dependencies: 171
-- Name: approle_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('approle_id_seq', 1, false);


--
-- TOC entry 1974 (class 0 OID 16611)
-- Dependencies: 172
-- Data for Name: appuser; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY appuser (id, firstname, lastname, username) FROM stdin;
1	test	user1	testuser1
\.


--
-- TOC entry 1983 (class 0 OID 0)
-- Dependencies: 174
-- Name: appuser_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('appuser_id_seq', 1, true);


--
-- TOC entry 1975 (class 0 OID 16617)
-- Dependencies: 173
-- Data for Name: appuserrole; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY appuserrole (appuserid, approleid) FROM stdin;
\.


--
-- TOC entry 1977 (class 0 OID 16622)
-- Dependencies: 175
-- Data for Name: authtoken; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY authtoken (appuserid, token, expirationdate) FROM stdin;
\.


-- Completed on 2014-11-03 17:44:30

--
-- PostgreSQL database dump complete
--

