package org.shop.stroage.mongo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mongodb.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import java.util.*;

/**
 * Mongo 数据源
 * 
 * @author VIC
 *
 */
public final class MongoDataSource {

	private static final Logger log = LoggerFactory.getLogger(MongoDataSource.class);
	private static Map<String, MongoBaseDao> newMongodbBaseDaoMap = Maps.newHashMap();

	public static MongoBaseDao getNewMongodbBaseDao(MongoDbKey mongoDbKey) {

		if (newMongodbBaseDaoMap.get(mongoDbKey.getValue()) == null) {
			MongoConfiguration mongoConfiguration = null; //ConfigurationService.getMongoConfiguration(mongoDbKey.getValue());
			if (mongoConfiguration == null) {
				log.error("Get mongodb config error");
				return null;
			}
			MongoTemplate mongoTemplate = getMongoTemplate(mongoConfiguration);
			newMongodbBaseDaoMap.put(mongoDbKey.getValue().toLowerCase(), new MongoBaseDaoImpl(mongoTemplate));
		}
		return newMongodbBaseDaoMap.get(mongoDbKey.getValue().toLowerCase());
	}

	private static MongoTemplate getMongoTemplate(MongoConfiguration mongoConfiguration) {

		try {
			MongoClientOptions options = new MongoClientOptions.Builder().writeConcern(WriteConcern.SAFE)
					.connectionsPerHost(mongoConfiguration.getConnectionsPerHost())
					.threadsAllowedToBlockForConnectionMultiplier(
							mongoConfiguration.getThreadsAllowedToBlockForConnectionMultiplier())
					.build();
			List<MongoCredential> credentials = new ArrayList<MongoCredential>();
			credentials.add(MongoCredential.createMongoCRCredential(mongoConfiguration.getUsername(), "admin",
					mongoConfiguration.getPassword().toCharArray()));
			MongoClient mongoClient = new MongoClient(buildServerAddress(mongoConfiguration), credentials, options);
			return new MongoTemplate(mongoClient.getDB(mongoConfiguration.getKey()).getMongo(),
					mongoConfiguration.getKey());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Get mongo template error, input: %s", mongoConfiguration.toJson());
			return null;
		}
	}

	private static List<ServerAddress> buildServerAddress(MongoConfiguration mongoConfiguration) {

		List<ServerAddress> servers = Lists.newArrayList();
		try {
			servers.add(new ServerAddress(mongoConfiguration.getHost(), mongoConfiguration.getPort()));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Build mongodb server address error.");
		}
		return servers;
	}

}
