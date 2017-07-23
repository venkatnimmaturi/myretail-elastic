package myretail.jest.client.api.impl;

import myretail.jest.client.api.request.ApiRequest;
import myretail.jest.client.api.response.ApiResponse;
import myretail.jest.client.call.request.ElasticsearchCallRequest;
import myretail.jest.client.call.response.ElasticsearchCallResponse;
import myretail.jest.client.hystrix.JestHystrixCommand;

public abstract class AbstractElasticsearchApiCommand<REQ extends ElasticsearchCallRequest, RESP extends ElasticsearchCallResponse, APIREQ extends ApiRequest, APIRESP extends ApiResponse> {

	protected JestHystrixCommand<REQ, RESP> hystrixCommand;

	protected abstract REQ preProcess(APIREQ apiRequest);

	protected abstract RESP executeJestCall(REQ apiRequest);

	protected abstract APIRESP postProcess(RESP apiRequest);

	public AbstractElasticsearchApiCommand(JestHystrixCommand<REQ, RESP> hystrixCommand) {
		super();
		this.hystrixCommand = hystrixCommand;
	}

	public APIRESP execute(APIREQ apiRequest) {

		REQ callRequest = preProcess(apiRequest);

		RESP callResponse = hystrixCommand.executeJestCall(callRequest);

		APIRESP response = postProcess(callResponse);

		return response;

	}

}
