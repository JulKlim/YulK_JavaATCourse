package com.epam.tc.hw6.runners;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.epam.tc.hw6.common.HomePage;
import com.epam.tc.hw6.common.HomePageHeader;
import com.epam.tc.hw6.common.HomePageIndex;
import com.epam.tc.hw6.common.HomePageLeftSideBar;
import com.epam.tc.hw6.common.HomePageMainContent;
import com.epam.tc.hw6.common.WebDriverSetUp;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Test;


@Severity(SeverityLevel.CRITICAL)
@Epic("Selenium UI exercises")
@Feature("Ex1")
@Story("Ex1: Log in and checking home page")
public class ExerciseOne extends WebDriverSetUp {

    //1 Open test site by URL
    @Test(priority = 1)
    public void openHomePage() {
        HomePage loginPage = new HomePage(driver);
        loginPage.openHomePage();
    }

    //3 Perform login
    @Test(priority = 3)
    public void performLogin() {
        HomePage loginPage = new HomePage(driver);
        loginPage.performLogin("Roman", "Jdi1234");
    }

    //4 Assert Username is logged
    @Test(priority = 4)
    public void assertUsername() {
        HomePage homePage = new HomePage(driver);
        assertThat(homePage.assertUsername()).isEqualTo("ROMAN IOVLEV");
    }

    //5 Assert that there are 4 items on the header section are displayed,
    //and they have proper
    //texts

    @Test(priority = 5)
    public void assertHeaderItems() {
        HomePageHeader homePageHeader = new HomePageHeader(driver);
        String[] expectedHeaderButtonsTexts = {"HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS"};

        assertThat(homePageHeader.verifyItemsCount()).isEqualTo(4);
        assertThat(homePageHeader.areItemsNamesCorrect(
                expectedHeaderButtonsTexts)).as("Header items have correct names").isTrue();
    }

    //6 Assert there are 4 images on the index page and all of them are displayed
    @Test(priority = 6)
    public void assertIconsCountAndDisplay() {
        HomePageIndex homePageIndex = new HomePageIndex(driver);
        assertThat(homePageIndex.isImagesCountCorrect()).isEqualTo(4);
        assertThat(homePageIndex.areImagesDisplayed()).as("All images are displayed");
    }

    //7 Assert there are 4 texts under icons, each of them contains proper text

    @Test(priority = 7)
    public void asserTexts() {
        HomePageIndex homePageIndex = new HomePageIndex(driver);
        assertThat(homePageIndex.isTextsCountCorrect()).isEqualTo(4);

        String firstText = "To include good practices\nand ideas from successful\nEPAM project";
        String secondText = "To be flexible and\ncustomizable";
        String thirdText = "To be multiplatform";
        String fourText = "Already have good base\n(about 20 internal and\nsome external projects),\nwish to get more…";
        String[] expectedTexts = {firstText, secondText, thirdText, fourText};
        assertThat(homePageIndex.areTextsCorrect(
                expectedTexts)).as("Texts under icons contain proper text").isTrue();
    }

    //8 Assert that iframe exists and contains Frame button

    @Test(priority = 8)
    public void checkIframe() {
        HomePageMainContent homePageMainContent = new HomePageMainContent(driver);

        //9 Switch to the iframe and check that there is
        //“Frame Button” in the iframe

        homePageMainContent.switchToIframe();
        assertThat(homePageMainContent.ifFrameButtonExists()).as(
                "Iframe button exists").isTrue();
    }

    //10 Switching to original window

    @Test(priority = 9)
    public void switchToIframe() {
        HomePageMainContent homePageMainContent = new HomePageMainContent(driver);
        homePageMainContent.switchToDefaultContent();
    }

    //11 Assert there are 5 items in the Left section displayed and contain proper texts

    @Test(priority = 10)
    public void itemsLeftSection() {
        HomePageLeftSideBar homePageLeftSideBar = new HomePageLeftSideBar(driver);
        assertThat(homePageLeftSideBar.isItemsLeftSideBarCountCorrect()).isEqualTo(5);
        assertThat(homePageLeftSideBar.areItemsLeftSideBarDisplayed()).as(
                "Items on the left side bar are displayed").isTrue();

        String[] expectedSections = {"Home", "Contact form", "Service", "Metals & Colors", "Elements packs"};
        assertThat(homePageLeftSideBar.ifSectionNamesMatch(expectedSections)).as(
                "Section names of the left side bar are correct").isTrue();
    }
    //12 Closing browser
}

