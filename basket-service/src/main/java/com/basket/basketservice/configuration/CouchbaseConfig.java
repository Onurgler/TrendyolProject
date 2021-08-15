package com.basket.basketservice.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;

@Configuration
public class CouchbaseConfig extends AbstractCouchbaseConfiguration{
	
	@Override
    public String getConnectionString() {
        return "localhost";
    }
    
    @Override
    public String getUserName() {
        return "buggerspace";
    }

    @Override
    public String getPassword() {
        return "123321";
    }

    @Override
    public String getBucketName() {
        return "notificationBucket";
    }

}
