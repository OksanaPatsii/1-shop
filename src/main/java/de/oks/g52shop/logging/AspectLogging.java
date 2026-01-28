package de.oks.g52shop.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectLogging {

    private Logger logger = LoggerFactory.getLogger(AspectLogging.class);


    @Pointcut("execution(* de.oks.g52shop.service.ProductServiceImpl.save(de.oks.g52shop.domain.dto.ProductDto))")
    public void saveProduct() {}

    @Before("saveProduct()")
    public void beforeSavingProduct(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        logger.info("Method save of the class ProductServiceImpl called with argument {}", args[0]);
    }

    @After("saveProduct()")
    public void afterSavingProduct() {
        logger.info("Method save of the class ProductServiceImpl finished its work");
    }

    @Pointcut("execution(* de.oks.g52shop.service.ProductServiceImpl.getById(Long))")
    public void getProductById() {}

    @AfterReturning(
            pointcut = "getProductById()",
            returning = "result"
    )
    public void afterReturningProductById(Object result) {
        logger.info("Method getById of the ProductServiceImpl successfully returned product: {}", result);
    }

    @AfterThrowing(
            pointcut = "getProductById()",
            throwing = "e"
    )
    public void afterThrowingExceptionWhileGettingProduct(Exception e) {
        logger.warn("Method getById of the ProductServiceImpl threw an exception: {}", e.getMessage());
    }
}
