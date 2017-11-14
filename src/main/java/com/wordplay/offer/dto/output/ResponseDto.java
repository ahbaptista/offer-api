package com.wordplay.offer.dto.output;

import java.io.Serializable;

public class ResponseDto implements Serializable{
	private String msg;

	public ResponseDto(){}

	public ResponseDto(String msg){
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
