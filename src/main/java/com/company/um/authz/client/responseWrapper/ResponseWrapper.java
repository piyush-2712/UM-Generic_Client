package com.company.um.authz.client.responseWrapper;

public class ResponseWrapper<T> {

	private Status status;
	private T result;
	
	private static final Status successStatus = new Status(200, "Success");
	
	//private static final Status failureStatus = new Status(500, "Error");
	
	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}
	/**
	 * @return the result
	 */
	public T getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(T result) {
		this.result = result;
	}
	
	public static <P> ResponseWrapper<P> getSuccessResponse(P result){
		ResponseWrapper<P> res = new ResponseWrapper<P>();
		res.setStatus(successStatus);
		res.setResult(result);
		return res;
	}
	
	public static <P> ResponseWrapper<P> getFailureResponse(Status status) {
		ResponseWrapper<P> res = new ResponseWrapper<P>();
		res.setStatus(status);
		res.setResult(null);

		return res;

	}
}