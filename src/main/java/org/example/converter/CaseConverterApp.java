package org.example.converter;

import org.example.converter.mbean.*;
import org.example.converter.aspectj.ConverterConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.management.*;
import java.lang.management.ManagementFactory;


@Component
public class CaseConverterApp {
    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConverterConfig.class);

        TextCaseConverter textCaseConverter = context.getBean("caseConverterBean", TextCaseConverter.class);

        LoggingControlMBean mbean = new LoggingControl();
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("org.example.converter.mbean:type=LoggingControl");
        mbs.registerMBean(mbean, name);

        UserInputProcessor userInputProcessor = new UserInputProcessor(textCaseConverter, mbean);
        userInputProcessor.processUserInput();

        context.close();

    }
}