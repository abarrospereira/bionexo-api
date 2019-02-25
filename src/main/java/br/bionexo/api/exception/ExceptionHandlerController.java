package br.bionexo.api.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionResponse> invalidInput(MethodArgumentNotValidException ex) {
		 BindingResult result = ex.getBindingResult();
	        ExceptionResponse response = new ExceptionResponse();
	        response.setErrorCode("Validation Error");
	        response.setErrorMessage("Invalid inputs.");
	        response.setErros(getErrors(result));
	        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
	}
	
	
	private Map<String, List<String>> getErrors(BindingResult result){
		Map<String, List<String>> mapErrors = new HashedMap<>();
		
		
		for(FieldError error: result.getFieldErrors()) {
			List<String> errors = new ArrayList<>();
			if(mapErrors.containsKey(error.getField())) {
				mapErrors.get(error.getField()).add(error.getDefaultMessage());
			}
			else
			{
				errors.add(error.getDefaultMessage());
				mapErrors.put(error.getField(),errors);
				
			}
		}
		
		return mapErrors;
		
	}
	

}
