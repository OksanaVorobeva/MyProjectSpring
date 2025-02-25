package by.javaguru.myproject.integration;



import by.javaguru.myproject.pool.ConnectionPool;
import org.springframework.boot.test.mock.mockito.SpyBean;

public class TestApplicationRunner {
    @SpyBean(name = "connectionPool2")
    private ConnectionPool connectionPool;
}
