/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import java.io.Serializable;

/**
 *
 * @author Korisnik
 */
public class Response implements Serializable{
    private ResponseType responseType;
    private Object result;
    private Exception exception;

    public Response() {
    }
    
    public Response(ResponseType responseType, Object result, Exception exception) {
        this.responseType = responseType;
        this.result = result;
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }
    
    public void setException(Exception exception) {
        this.exception = exception;
    }

    public ResponseType getResponseType() {
        return responseType;
    }
    
    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
