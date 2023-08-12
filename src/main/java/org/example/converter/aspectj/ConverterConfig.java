package org.example.converter.aspectj;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@Configuration
@ComponentScan ("org.example.converter")
@EnableAspectJAutoProxy
public class ConverterConfig {

}
