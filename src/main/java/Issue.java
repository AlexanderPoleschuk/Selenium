import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class Issue extends TestBase {


    @Test(testName = "Search")
    public void keyWordSearchTest()
    {
        actions().moveToElement($(".gsfi")).perform();
    }
}