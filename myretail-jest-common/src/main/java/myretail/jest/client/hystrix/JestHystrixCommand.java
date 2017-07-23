package myretail.jest.client.hystrix;

import myretail.jest.client.call.request.ElasticsearchCallRequest;
import myretail.jest.client.call.response.ElasticsearchCallResponse;

public interface JestHystrixCommand<REQ extends ElasticsearchCallRequest, RES extends ElasticsearchCallResponse> {

	RES executeJestCall(REQ request);
}
