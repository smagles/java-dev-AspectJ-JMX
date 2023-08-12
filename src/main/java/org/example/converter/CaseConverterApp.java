package org.example.converter;

import org.example.converter.mbean.*;
import org.example.converter.aspectj.ConverterConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.util.Scanner;

@Component
public class CaseConverterApp {
    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConverterConfig.class);

        TextCaseConverter textCaseConverter = context.getBean("caseConverterBean", TextCaseConverter.class);

        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("org.example.converter.mbean:type=LoggingControl");
        LoggingControlMBean mbean = new LoggingControl();
        mbs.registerMBean(mbean, name);

        Scanner scanner = new Scanner(System.in);

        boolean flag = true;

        while (flag) {
            System.out.println("Введіть 'exit' для завершення або 'toggleLogging' для зміни логування:");
            System.out.println("Введіть рядок:");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                flag = false;
            } else if (input.equalsIgnoreCase("toggleLogging")) {
                try {
                    if (mbean.isLoggingEnabled()) {
                        mbean.disableLogging();
                        System.out.println("Логування вимкнено.");
                    } else {
                        mbean.enableLogging();
                        System.out.println("Логування увімкнено.");
                    }
                } catch (Exception e) {
                    System.out.println("Помилка при зміні стану логування: " + e.getMessage());
                }
            } else {
                if (mbean.isLoggingEnabled()) {
                    String output = textCaseConverter.convertCase(input);
                    System.out.println("Результат: " + output);
                } else {
                    System.out.println("Логування вимкнено. Неможливо виконати перетворення рядка.");
                }
            }
        }

        context.close();
    }


}
