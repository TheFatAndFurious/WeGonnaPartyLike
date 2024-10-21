package base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import static org.mockito.Mockito.mock;

public class DatabaseTest {
    private Database database;
    private DataSource dataSource;
    private MessageHelper messageHelper;

    @BeforeEach
    public void setUp(){
        dataSource = mock(DataSource.class);
        messageHelper = mock(MessageHelper.class);
        database = new Database(dataSource, messageHelper);
    }

    @Test

}
