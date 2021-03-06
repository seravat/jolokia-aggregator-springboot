package com.nullendpoint.jolokia.aggregator.http

import org.apache.camel.Exchange
import org.apache.camel.processor.aggregate.AggregationStrategy
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

/**
 * Created by swinchester on 7/07/16.
 */
@Component(value="jsonAggregationStrategy")
class JsonAggregationStrategy implements AggregationStrategy{

    Logger log = LoggerFactory.getLogger(this.class)

    @Override
    Exchange aggregate(Exchange oldExchange, Exchange newExchange) {

        if(oldExchange == null){

            def bodyList = []
            bodyList << newExchange.in.body
            newExchange.in.body = bodyList
            log.debug("new exchange : ${newExchange.in.body}")
            return newExchange
        }else{
            log.debug("new exchange : ${newExchange.in.body} , old exchange: ${oldExchange.in.body}")
            oldExchange.in.body << newExchange.in.body
            return oldExchange
        }
    }
}