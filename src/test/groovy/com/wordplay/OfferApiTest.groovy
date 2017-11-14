package com.wordplay

import com.wordplay.util.OfferApiClient
import com.wordplay.util.RequestBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.test.context.jdbc.Sql

@Sql(["/sql/clean_up.sql", "/sql/test_data.sql", ])
class OfferApiTest extends BaseSpec{

	@Value('${local.server.port}')
	def port

	def apiClient

	def setup(){
		apiClient = new OfferApiClient(port)
	}

	def "Created offers should be listed"(){

		given: "A valid offer request"

		def body = RequestBuilder.withDummyRequest()

		when: "An offer is requested to be created"

		def response = apiClient.makeCreationCall(body)

		then: "The offer should be created"

		response.status == 200
		response.responseData.name == 'offerName1'

		when: "List offers gets called"

		response = apiClient.makeListCall()

		then: "The offer should be available"

		response.status == 200
		response.responseData[0].name == 'offerName1'
	}

	def "Deleted offers should not be listed"(){

		given: "A valid offer request"

		def body = RequestBuilder.withDummyRequest()

		when: "An offer is requested to be created"

		def response = apiClient.makeCreationCall(body)

		then: "The is be created"

		response.status == 200
		response.responseData.name == 'offerName1'

		when: "The offer is requested to be deleted"

		apiClient.makeDeleteCall(response.responseData.id)

		then: "The offer should be deleted"

		response.status == 200

		when: "List offers gets called"

		response = apiClient.makeListCall()

		then: "The offer should not be available"

		response.status == 200
		response.responseData.empty
	}

	def "Expired offers should not be listed"(){

		when: "List offers gets called"

		def response = apiClient.makeListCall()

		then: "Result is empty"

		response.status == 200
		response.responseData.empty

	}

}