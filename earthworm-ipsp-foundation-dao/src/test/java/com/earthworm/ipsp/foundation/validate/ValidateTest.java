package com.earthworm.ipsp.foundation.validate;

import com.earthworm.ipsp.foundation.dao.FoundationApplication;
import com.earthworm.postgres.validate.ValidateHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.Validator;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { FoundationApplication.class }, properties = { "application.properties" })
public class ValidateTest {
    private static Logger logger = LoggerFactory.getLogger(ValidateTest.class);

//    @Test
//    public void testUniqueValidatorHelper() {
//        Validator validator = ValidateHelper.getValidator();
//        AppMenu menu = new AppMenu();
//        menu.setUrl("/ipsp/billsCategory/view");
//
//        Map<String, String> rs = ValidateHelper.getMessage(validator.validate(menu));
//        logger.info("=====================Test complete========================");
//        boolean isEmpty = rs.isEmpty();
//        if(!isEmpty) {
//            rs.forEach((k,v)->logger.info("key:{},msg:{}",k,v));
//        }
//        logger.info("isEmpty:{}",isEmpty);
//    }

//    @Test
//    public void testUniqueValidator() {
//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        Validator validator = factory.getValidator();
//        AppMenu menu = new AppMenu();
//        menu.setUrl("/ipsp/billsCategory/view%");
//        Set<ConstraintViolation<AppMenu>> violations = validator.validate(menu);
//        logger.info("=====================Test complete========================");
//        boolean isEmpty = violations.isEmpty();
//        if(!isEmpty) {
//            violations.forEach(violation->logger.info("path:{},msg:{}",
//                    violation.getPropertyPath().toString(),violation.getMessage()));
//        }
//        logger.info("isEmpty:{}",isEmpty);
//    }

//    @Test
//    public void testSQLInjectValidator() {
//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        Validator validator = factory.getValidator();
//        AppMenu menu = new AppMenu();
//        menu.setUrl("abcdefg");
//        Set<ConstraintViolation<AppMenu>> violations = validator.validate(menu);
//        logger.info("=====================Test complete========================");
//        boolean isEmpty = violations.isEmpty();
//        if(!isEmpty) {
//            violations.forEach(violation->logger.info("path:{},msg:{}",
//                    violation.getPropertyPath().toString(),violation.getMessage()));
//        }
//        logger.info("isEmpty:{}",isEmpty);
//    }

    @Test
    public void testSQLInjectValidatorHelper() {
        Validator validator = ValidateHelper.getValidator();

        AppMenu menu = new AppMenu();
        menu.setUrl("/ipsp/billsCategory/view%%()");

        Map<String, String> rs = ValidateHelper.getMessage(validator.validate(menu));

        logger.info("=====================Test complete========================");
        boolean isEmpty = rs.isEmpty();
        if(!isEmpty) {
            rs.forEach((k,v)->logger.info("key:{},msg:{}",k,v));
        }
        logger.info("isEmpty:{}",isEmpty);
    }
}