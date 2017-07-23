package myretail.jest.client.hystrix;

import java.io.IOException;

import javax.annotation.Resource;

import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import myretail.jest.client.call.request.ElasticsearchCallRequest;
import myretail.jest.client.call.response.ElasticsearchCallResponse;

@Slf4j
public abstract class AbstractJestHystrixCommand<REQ extends ElasticsearchCallRequest, RES extends ElasticsearchCallResponse>
		implements JestHystrixCommand<REQ, RES> {

	@Setter
	@Resource(name = "jestClient")
	protected JestClient jestClient;

	protected SearchResult executeSearch(Search search) {
		try {

			SearchResult result = jestClient.execute(search);
			log.debug(result.getJsonString());
			return result;

		} catch (IOException e) {
			log.error("Error encountered performing query against elasticsearch", e.getMessage());

		} finally {

		}
		return null;
	}
}
