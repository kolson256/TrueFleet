ALTER TABLE ONLY contactentry
    ADD CONSTRAINT contactentry_pkey PRIMARY KEY (id);

ALTER TABLE ONLY containerload 
    ADD CONSTRAINT containerload_pkey PRIMARY KEY(containerid);


ALTER TABLE ONLY imtorder
    ADD CONSTRAINT imtorder_pkey PRIMARY KEY(internalid);

ALTER TABLE ONLY intermodalcontainer
    ADD CONSTRAINT intermodalcontainer_pkey PRIMARY KEY(id);

ALTER TABLE ONLY imtorder
    ADD CONSTRAINT phoneentry PRIMARY KEY(internalid);


ALTER TABLE ONLY imtorder
    ADD CONSTRAINT imtorder_containerid_fkey FOREIGN KEY (containerid) REFERENCES containerload(containerid);