package base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
    public void testAddBirthDay() throws SQLException {
        BirthdaysManager newEntry = new BirthdaysManager();
        newEntry.setGivenName("David");
        newEntry.setFamilyName("Bowie");
        newEntry.setBirthdate(LocalDate.of(1990,6,6));

        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        when(dataSource.getConnection()).thenReturn(mockConnection)
    }
}
