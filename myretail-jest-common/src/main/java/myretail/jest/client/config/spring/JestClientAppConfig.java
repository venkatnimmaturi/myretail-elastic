package myretail.jest.client.config.spring;

import java.util.concurrent.TimeUnit;

import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import lombok.Getter;
import lombok.Setter;
import myretail.jest.client.gson.adapter.MoneyAdapter;
import myretail.jest.client.model.common.ElasticsearchProperties;

@Configuration
@EnableConfigurationProperties(ElasticsearchProperties.class)
@ComponentScan(basePackages = { "myretail.jest.client.hystrix" })
public class JestClientAppConfig {

	@Autowired
	@Getter
	@Setter
	private ElasticsearchProperties properties;

	@Bean
	public JestClient jestClient() throws Exception {
		return jestClientFactory().getObject();
	}

	JestClientFactory jestClientFactory() {

		JestClientFactory factory = new JestClientFactory();
		factory.setHttpClientConfig(httpClientConfig());
		return factory;
	}

	private HttpClientConfig httpClientConfig() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Money.class, new MoneyAdapter());
		Gson gson = gsonBuilder.create();
		return new HttpClientConfig.Builder(properties.getServerUri()).multiThreaded(true).gson(gson)
				.maxConnectionIdleTime(properties.getIdleTimeout(), TimeUnit.SECONDS)
				.connTimeout(properties.getConnectionTimeout())
				.defaultMaxTotalConnectionPerRoute(properties.getMaxConnections()).build();
	}
}
