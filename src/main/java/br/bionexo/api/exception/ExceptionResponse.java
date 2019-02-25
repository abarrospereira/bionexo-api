package br.bionexo.api.exception;

import java.util.List;
import java.util.Map;

public class ExceptionResponse {
    private String errorCode;
	private String errorMessage;
    private Map<String, List<String>> erros;

    public String getErrorCode() {
    	return errorCode;
    }
    public void setErrorCode(String errorCode) {
    	this.errorCode = errorCode;
    }
    public String getErrorMessage() {
    	return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
    	this.errorMessage = errorMessage;
    }
	public Map<String, List<String>> getErros() {
		return erros;
	}
	public void setErros(Map<String, List<String>> erros) {
		this.erros = erros;
	}
}
