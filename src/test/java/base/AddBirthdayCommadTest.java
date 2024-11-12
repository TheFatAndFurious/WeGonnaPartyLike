package base;

import base.util.InputHelper;
import base.util.MessageHelper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddBirthdayCommadTest {
    private Database mockDatabase = mock(Database.class);
    private MessageHelper mockMessageHelper = mock(MessageHelper.class);
    private final InputHelper mockInputHelper = mock(InputHelper.class);

    @ParameterizedTest(name = "When input is ''{0}'', expect result {1}")
    @CsvSource({
            "'n',      false",
            "'N',      false",
            "'  n  ',  false",
            "'y',      true",
            "'Y',      true",
            "'yes',    true",
            "'no',     true",
            "'',       true",
            "'x',      true"
    })
    public void testAddAnotherBirthdayYes(String userInput, boolean expectedResult){
        when(mockInputHelper.getInputString(anyString())).thenReturn(userInput);
        when(mockMessageHelper.PrintFormattedMessage(any(Messages.class))).thenReturn("Do you want to add another birthday? (y/n)");

        AddBirthdayCommand addBirthdayCommand = new AddBirthdayCommand(mockDatabase, mockMessageHelper, mockInputHelper);

        boolean result = addBirthdayCommand.addAnotherBirthday();

        assertEquals(expectedResult, result);
    }


}
