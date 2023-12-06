package com.example.demo.vo;

import lombok.Data;

@Data
public class ResultDate {
	private String resultCode;
	private String msg;
	private Object data;
	
	public static ResultDate from(String resultCode, String msg) {
		return from(resultCode, msg, null);
	}
	
	public static ResultDate from(String resultCode, String msg, Object data) {
		
		ResultDate rd = new ResultDate();
		rd.resultCode = resultCode;
		rd.msg = msg;
		rd.data = data;
		
		return rd;
	}
	
	public boolean isSuccess() {
		return this.resultCode.startsWith("S-");
	}
	public boolean isFail() {
		return isSuccess() == false;
	}
}
