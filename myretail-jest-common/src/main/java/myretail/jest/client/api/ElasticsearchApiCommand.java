package myretail.jest.client.api;

import myretail.jest.client.api.request.ApiRequest;
import myretail.jest.client.api.response.ApiResponse;

public interface ElasticsearchApiCommand<APIREQ extends ApiRequest, APIRESP extends ApiResponse> {

	APIRESP execute(APIREQ request);
}
