package base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

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

        when(dataSource.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString(), anyInt())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.getGeneratedKeys()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt(1)).thenReturn(1);

        BirthdaysManager result = database.addBirthday(newEntry);

        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(messageHelper).PrintFormattedMessage(Messages.BIRTHDAY_ADDED_SUCCESSFULLY, "David", "Bowie");
    }
}
