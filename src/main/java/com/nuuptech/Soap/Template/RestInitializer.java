/*
 * Copyright 2016 Red Hat, Inc.
 * <p>
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 *
 */
package com.nuuptech.Soap.Template;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RestInitializer extends RouteBuilder {

	@Value("${enviroment.urlmapping}")
	private String CAMEL_URL_MAPPING;
	@Value("${enviroment.servlentname}")
	private String CAMEL_SERVLET_NAME;

	@Bean
	public ServletRegistrationBean camelServletRegistrationBean() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new CamelHttpTransportServlet(),
				CAMEL_URL_MAPPING);
		registration.setName(CAMEL_SERVLET_NAME);
		return registration;
	}

	@Override
	public void configure() throws Exception {

		restConfiguration().component("servlet").bindingMode(RestBindingMode.json);

		rest().get("/order/register").outType(Order.class).to("direct:getOrder");

		from("direct:getOrder").log(LoggingLevel.INFO, "Rest Request").process(exchange -> {
			Order p = new Order();
			p.setAmount(1000);
			p.setId(12);
			p.setDescription("order");
			p.setItem("1");
			exchange.getIn().setBody(p);
		});
	}
}
