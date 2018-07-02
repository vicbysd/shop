package org.shop.stroage.mongo;

/**
 *
 * Mongo database name
 * Created by VIC on 2016-05-10.
 */
public enum MongoDbKey {

    OFFICIAL_WEBSITE("official_website");

    private String value;

    private MongoDbKey(String value){
        this.value=value;
    }

    public String getValue(){
        return value;
    }

}
