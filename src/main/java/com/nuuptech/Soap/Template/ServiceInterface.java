package com.nuuptech.Soap.Template;

import javax.jws.*;

 
@WebService
public interface ServiceInterface {

	@WebMethod
	String registro(@WebParam(name = "orderDes") Order orden);

}
