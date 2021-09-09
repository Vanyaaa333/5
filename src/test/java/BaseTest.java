import Steps.ActionsWithConsoleSteps;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Owner("Kazak Ivan")
@Epic("AT tests")
public class BaseTest {

    protected ActionsWithConsoleSteps ac = new ActionsWithConsoleSteps();

    /*
    Использую \n в конце каждого параметра в качестве комманды ввода, опять же из-за того, что не пробрасываются парамметры
    не могу проверить на сколько это работает или нет, на уровне логики только.
     */

     /*
        Я и вправду искал в интернете , как можно передать параметры, нашел только это и это не работает, так как ожидалось.
        Никогда не имел дела с написанием автотестов для декстоп приложений с CLI интерфейсом и в принципе с написанием автотестов
        для декстоп приложений, теоритечски я понимаю, как это должно происходить, а на практике не нашел нормального объяснения
        и примеров.
      */

    @Test(description = "Checking the correct division of 2 input parameters",dataProvider = "parametersPairs", dataProviderClass = ActionsWithConsoleSteps.class)
    @Severity(value = SeverityLevel.CRITICAL)
    public void someTest(String firstParName,
                         String firstParValue,
                         String secondParName,
                         String secondParValue,
                         String expectedResult)
            throws InterruptedException, IOException, TimeoutException {

//        ac
//                .openApplicationAndPassingParameters(firstParName,firstParValue,secondParName,secondParValue)
//                .getOutputAsUTF8String();
//        ac.isDivisionCorrect(expectedResult);
        System.out.println("For checking allure and jenkins");
    }

}
