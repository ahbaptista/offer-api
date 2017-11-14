package com.wordplay.util

import groovyx.net.http.ContentType
import groovyx.net.http.RESTClient


class OfferApiClient {

	def client

	OfferApiClient(port){
		client = initiateClient(port)
	}

	def initiateClient(port){
		def client = new RESTClient("http://localhost:$port/api/v1/")
		client.handler.failure = { resp, data ->
			resp.setData(data)
			return resp
		}
		return client
	}

	def makeCreationCall(body){
		return client.post([
				path               : 'offer',
				requestContentType : ContentType.JSON,
				body               : body
		])
	}

	def makeListCall(){
		return client.get([
				path    : 'offer'
		])
	}

	def makeDeleteCall(id){
		return client.delete([
				path    : 'offer',
				query   : ['id' : id]
		])
	}
}
