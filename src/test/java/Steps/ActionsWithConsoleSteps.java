package Steps;

import Constants.ExpectedResultConstants;
import Constants.ParameterConstants;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.zeroturnaround.exec.ProcessExecutor;
import org.zeroturnaround.exec.ProcessResult;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

@Log4j2
public class ActionsWithConsoleSteps {

    private final String[] programs = {"cmd", "/c","start","cmd.exe","/K","calc_div.exe"};
    private final  Map<String,String> parametersMap = new HashMap<>();
    private static ProcessResult processResult;

    public ActionsWithConsoleSteps() {
    }

    @DataProvider()
    public static Object[][] parametersPairs() {
        log.info("Creating parameters pairs model");
        return new Object[][]{
                {ParameterConstants.firstIntegerPositiveParameter,ParameterConstants.firstIntegerPositiveParameterName,
                        ParameterConstants.secondIntegerPositiveParameter, ParameterConstants.secondIntegerPositiveParameterName,
                        ExpectedResultConstants.divisionOfTwoIntegerPositiveParameters},
                {ParameterConstants.firstIntegerPositiveParameter, ParameterConstants.firstIntegerPositiveParameterName,
                        ParameterConstants.secondIntegerNegativeParameter, ParameterConstants.secondIntegerNegativeParameterName,
                        ExpectedResultConstants.divisionOfIntegerPositiveAndNegativeParameters},
                {ParameterConstants.firstIntegerNegativeParameter, ParameterConstants.firstIntegerNegativeParameterName,
                        ParameterConstants.secondIntegerPositiveParameter, ParameterConstants.secondIntegerPositiveParameterName,
                        ExpectedResultConstants.divisionOfIntegerPositiveAndNegativeParameters},
                /*
                Аналогично будут прописываться другие связки параметров исходя из тейсткейсов
                 */

        };
    }


    @Step
    public ActionsWithConsoleSteps openApplicationAndPassingParameters(String firstParName, String firstParValue,String secondParName,String secondParValue)
            throws InterruptedException, TimeoutException, IOException {
        log.info("Open application in cmd , passing parameters, reading output");
        parametersMap.put(firstParName,firstParValue);
        parametersMap.put(secondParName,secondParValue);
        processResult = new ProcessExecutor().command(programs).environment(parametersMap).readOutput(true).execute();
        return this;
       /*
       Можно топорно ввести их передавая в метод кончики констант, но потом мне кажется, что невозможно просто считать.
        Robot r = new Robot();
        r.keyPress(KeyEvent.firstPar);
        r.keyPress(KeyEvent.VK_ENTER);
        r.keyPress(KeyEvent.secondPar);
        r.keyPress(KeyEvent.VK_ENTER);
        */
    }
    @Step
    public String getOutputAsUTF8String(){
        log.info("Changing output type to UTF8 String");
        /*
        Ответ вероятнее всего парсится , достается результат деления
         */
        return processResult.outputUTF8();
    }

    @Step
    public ActionsWithConsoleSteps isDivisionCorrect(String expectedResult){
        log.info("Checking that result of division is right");
        Assert.assertEquals(getOutputAsUTF8String(),expectedResult,"Division isn't correct");
        return this;
    }

}
