package com.dang.crawler.resources.mongodb;

import com.dang.crawler.resources.utils.PropertiesUtils;
import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import org.apache.commons.lang3.StringUtils;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * @author ddangqihe
 *
 */
public class MongoManagerFactory {
	// 库名称
	private String dbname;
	// 地址
	private String[] ipList;
	// 连接池大小
	private int poolsize;

	private String username;
	private String password;

	private String realDbName;

	private String adminDbName;

	private int port;

	private int minConnectionsPerHost;

	private int connectTimeOut;

	private int maxWaitTime;

	private int threadsAllowedToBlockForConnectionMultiplier;

	private static MongoManagerFactory mongoInstance = new MongoManagerFactory();
	private MongoClient mongoClient;

	public static MongoManagerFactory getInstance() {
		return mongoInstance;
	}

	public String getRealDbName() {
		return realDbName;
	}

	// 私有构造函数
	private MongoManagerFactory() {
		try {
			init();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public DB getDB() throws UnknownHostException {
		return mongoClient.getDB(realDbName);
	}
	public MongoDatabase getDatabase() throws UnknownHostException {
		return mongoClient.getDatabase(realDbName);
	}


	public MongoDatabase getAdminDatabase(){
		return mongoClient.getDatabase(adminDbName);
	}

	public synchronized void init() throws UnknownHostException {

		if (mongoClient == null) {
			port = Integer.valueOf(PropertiesUtils.getProperty("hanukkah.mongo.port"));
			connectTimeOut = Integer.valueOf(PropertiesUtils.getProperty("hanukkah.mongo.connectTimeOut"));
			ipList = PropertiesUtils.getProperty("hanukkah.mongo.hostName").split(",");
			dbname = PropertiesUtils.getProperty("hanukkah.mongo.defaultDB");
			username = PropertiesUtils.getProperty("hanukkah.mongo.userName");
			password = PropertiesUtils.getProperty("hanukkah.mongo.passWord");
			poolsize = Integer
					.valueOf(PropertiesUtils.getProperty("hanukkah.mongo.poolSize"));
			realDbName = PropertiesUtils.getProperty("hanukkah.mongo.realDB");
			adminDbName = PropertiesUtils.getProperty("hanukkah.mongo.adminDB");
			minConnectionsPerHost = Integer.valueOf(
					PropertiesUtils.getProperty("hanukkah.mongo.minConnectionsPerHost"));
			maxWaitTime = Integer
					.valueOf(PropertiesUtils.getProperty("hanukkah.mongo.maxWaitTime"));
			threadsAllowedToBlockForConnectionMultiplier = Integer.valueOf(PropertiesUtils.getProperty("hanukkah.mongo.threadsAllowedToBlockForConnectionMultiplier"));

			MongoClientOptions clientOptions = new MongoClientOptions.Builder().socketKeepAlive(true)
					.threadsAllowedToBlockForConnectionMultiplier(threadsAllowedToBlockForConnectionMultiplier)
					.maxWaitTime(maxWaitTime).minConnectionsPerHost(minConnectionsPerHost).connectionsPerHost(poolsize)
					.readPreference(ReadPreference.primary()).connectTimeout(connectTimeOut).build();
			if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
				List<MongoCredential> lstCredentials = Arrays
						.asList(MongoCredential.createScramSha1Credential(username, dbname, password.toCharArray()));

				List<ServerAddress> serverAddressList = new ArrayList<>();
				for(String ip : ipList){
					serverAddressList.add(new ServerAddress(ip, port));
				}
				mongoClient = new MongoClient(serverAddressList, lstCredentials, clientOptions);
			} else {
				List<ServerAddress> serverAddressList = new ArrayList<>();
				for(String ip : ipList){
					serverAddressList.add(new ServerAddress(ip, port));
				}
				mongoClient = new MongoClient(serverAddressList, clientOptions);
			}
		}
	}

	public synchronized void close() {
		if (mongoClient != null) {
			mongoClient.close();
		}
	}

	@Override
	protected void finalize() throws Throwable {
		close();
		super.finalize();
	}
}
