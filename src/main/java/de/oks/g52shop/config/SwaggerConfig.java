package de.oks.g52shop.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Anwendungsshop",
                description = "Application for various operations with Customers and Products",
                version = "1.0.0",
                contact = @Contact(
                        name = "Oksana",
                        email = "patsiiopatsii@gmail.com",
                        url = "http://ait-tr.de"
                )
        )
)
public class SwaggerConfig {
}
