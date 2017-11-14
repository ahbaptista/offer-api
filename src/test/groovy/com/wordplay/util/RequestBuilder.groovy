package com.wordplay.util


class RequestBuilder {

	static withDummyRequest(){
		return [
				'price': '15.00',
				'currency': 'USD',
				'name': 'offerName1',
				'description': 'Offer description 1'
		]
	}
}
