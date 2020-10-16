package com.jnsvera.osworks.api.exeptionhandle;
 
import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jnsvera.osworks.api.domain.exception.NegocioExeption;
 
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(NegocioExeption.class)
	public ResponseEntity<Object> handleNegocio(NegocioExeption ex,WebRequest req) {
		
		var status = HttpStatus.BAD_REQUEST;
		Exeption prob = new Exeption(status.value(),LocalDate.now(),ex.getMessage());
		
		return super.handleExceptionInternal(ex,prob,new HttpHeaders(), status, req);
	 
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		   Exeption prob = new Exeption(status.value(),LocalDate.now(),"Campos em invalidos");
		   
		   ArrayList<Exeption.Campo> campos = new ArrayList<>();
		   for (ObjectError err : ex.getBindingResult().getAllErrors()) {
			   String nome = ((FieldError)err).getField(); 
			   String msg = messageSource.getMessage(err, LocaleContextHolder.getLocale());
			   campos.add(new Exeption.Campo(nome,msg));
			   
		   }
		   prob.setCampos(campos);
		   
		return super.handleExceptionInternal(ex,prob,headers, status, request);
	}

}
