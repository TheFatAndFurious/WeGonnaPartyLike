package base;

import base.util.MessageHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
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
    public void testDeleteBirthdaySuccess() throws SQLException {
        int idToDelete = 1;

        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);

        when(dataSource.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        boolean result = database.deleteBirthday(1);

        assertTrue(result);

        verify(mockConnection).prepareStatement("DELETE FROM birthdays WHERE id = ?");
        verify(mockPreparedStatement).setInt(1, idToDelete);
        verify(mockPreparedStatement).executeUpdate();
        verify(messageHelper).PrintFormattedMessage(Messages.BIRTHDAY_DELETED_SUCCESSFULLY);

        verify(mockPreparedStatement).close();
        verify(mockConnection).close();
    }

    @Test
    public void testDeleteBirthdayFailure(){}
    // TODO: implement method


    //    ============ WIP ===================
    @Test
    public void testGetAllBirthdaysSuccess() throws SQLException {

        Connection mockConnection = mock(dataSource.getConnection());
        PreparedStatement mockPrepareStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
    }

    @Test
    public void testGetAllBirthdaysFailure(){}
    // TODO: implement method

    @Test
    public void testAddBirthDaySuccess() throws SQLException {
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
        verify(mockConnection.prepareStatement("INSERT INTO birthdays (givenName, familyName, birthdate) VALUES (?, ?, ?)"));
        verify(messageHelper).PrintFormattedMessage(Messages.BIRTHDAY_ADDED_SUCCESSFULLY, "David", "Bowie");
    }

    @Test
    public void testAddBirthdayFailure(){}
    // TODO: implement method

    @Test
    public void testUpdateBirthdaySuccess(){}
    // TODO: implement method

    @Test
    public void testUpdateBirthdayFailure(){}
    // TODO: implement method

    @Test
    public void testGetBirthdayByDateSuccess(){}
    // TODO: implement method

    @Test
    public void testGetBirthdayByDateFailure(){}
    // TODO: implement method


}
