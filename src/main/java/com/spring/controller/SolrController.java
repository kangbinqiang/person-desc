package com.spring.controller;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping("solr")
public class SolrController {

    private static final Logger logger = LoggerFactory.getLogger(SolrController.class);

    @Autowired
    private SolrClient client;

    @RequestMapping("/getSolrData")
    public String testSolr() throws IOException, SolrServerException {
        logger.info("调用solr查询开始：=========================");
        SolrDocument document = client.getById("MyCore","1");
        System.out.println(document);
        return document.toString();
    }
}