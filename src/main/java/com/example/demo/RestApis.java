package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.ElasticConfig;
import com.example.demo.elastic.ElasticController;
import com.example.demo.obj.CurrentApp;

import co.elastic.clients.elasticsearch.ElasticsearchClient;

@RestController
@RequestMapping("/hello")
public class RestApis {
	
	@Autowired
	ElasticConfig elasticConfig;

	// curl http://localhost:9090/hello
	@RequestMapping(method=RequestMethod.GET)
	public String hello() {
		String certFilePath = elasticConfig.getCertFilePath();
		String login = elasticConfig.getLogin();
		String pw = elasticConfig.getPw();
		String host = elasticConfig.getHost();
		int port = elasticConfig.getPort();
		
		System.out.println("certFilePath:" + certFilePath);
		System.out.println("login:" + login);
		System.out.println("pw:" + pw);
		System.out.println("host:" + host);
		System.out.println("port:" + port);		
		return "Hello World!";
	}
	
	// curl -X POST -H "Content-Type: application/json" -d '{[{"packageName":"A", "totalTime":"B", "firstTime":"C", "lastTime":"D"}, {"packageName":"A", "totalTime":"B", "firstTime":"C", "lastTime":"D"}]}' http://localhost:9090/hello
//	@RequestMapping(method=RequestMethod.POST)
//	public List<UsageApps> hello(@RequestBody List<UsageApps> param) {
//		System.out.println("---------------------------------------------------");
//		for(UsageApps apps : param) {
//			System.out.println(apps.getPackageName());
//		}
//		return param;
//	}
	
	//curl -X POST -H "Content-Type: application/json" -d '{"lastTime":"A", "packageName":"B"}' http://localhost:9090/hello
	@RequestMapping(method=RequestMethod.POST)
	public CurrentApp hello(@RequestBody CurrentApp param) {
		System.out.println("---------------------------------------------------");
		System.out.println("Time:" + param.getTime() + " App:" + param.getPackageName());
		
		String certFilePath = elasticConfig.getCertFilePath();
		String login = elasticConfig.getLogin();
		String pw = elasticConfig.getPw();
		String host = elasticConfig.getHost();
		int port = elasticConfig.getPort();
		ElasticsearchClient client = ElasticController.openConnection(certFilePath, login, pw, host, port);
		ElasticController.createIndex(client, param);
		ElasticController.closeConnection(client);
		
		return param;
	}
}
