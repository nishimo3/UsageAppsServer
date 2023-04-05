package com.example.demo.elastic;

import java.io.File;
import java.io.IOException;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder.HttpClientConfigCallback;

import com.example.demo.obj.CurrentApp;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.indices.ExistsRequest;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.TransportUtils;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import co.elastic.clients.transport.rest_client.RestClientTransport;

public class ElasticController {
	
	public static ElasticsearchClient openConnection(String certFilePath, String login, String pw, String host, int port) {		
		try {
			File certFile = new File(certFilePath);
			SSLContext sslContext = TransportUtils.sslContextFromHttpCaCrt(certFile);
			BasicCredentialsProvider credsProv = new BasicCredentialsProvider(); 
			credsProv.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(login, pw));
			
			RestClient restClient = RestClient.builder(new HttpHost(host, port, "https"))
					.setHttpClientConfigCallback(new HttpClientConfigCallback() {
						@Override
						public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
							return httpClientBuilder.setSSLContext(sslContext).setDefaultCredentialsProvider(credsProv);
						}
					})
					.build();
			ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
			return new ElasticsearchClient(transport);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	public static void closeConnection(ElasticsearchClient client) {
		try {
			client._transport().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isExistIndex(ElasticsearchClient client, String indexName) {
		try {
			BooleanResponse result = client.indices().exists(ExistsRequest.of(s -> s.index(indexName)));
			return result.value();
		} catch (ElasticsearchException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void createIndex(ElasticsearchClient client, CurrentApp app) {
		IndexRequest<Object> indexRequest = new IndexRequest.Builder<>()
				.index("currentapp")
				.id(app.getTime())
				.document(app)
				.build();
		try {
			client.index(indexRequest);
		} catch (ElasticsearchException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	public static void createIndex(ElasticsearchClient client, UsageApps apps) {
//		IndexRequest<Object> indexRequest = new IndexRequest.Builder<>()
//				.index("usageapps")
//				.id(apps.getFirstTime())
//				.document(apps)
//				.build();
//		try {
//			client.index(indexRequest);
//		} catch (ElasticsearchException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
