package myretail.jest.client.model.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties("myretail.elasticsearch")
public class ElasticsearchProperties {

	private String indexName = "";
	private int typeName;
}
