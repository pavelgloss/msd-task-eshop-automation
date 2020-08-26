package cz.pavelgloss.homeworks.msd;

import cz.pavelgloss.homeworks.msd.testframework.ProductCategory;
import cz.pavelgloss.homeworks.msd.testframework.ProductListingPage;
import cz.pavelgloss.homeworks.msd.testframework.ShoppingCart;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Unit test for simple App.
 */
public class CategoryTest {
    private WebDriver driver;

    @Before
    public void init() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1024, 768));
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    /**
     * automate this test case: Go to your favorite e-shop, navigate to some category, and add two most expensive items to the shopping cart from this category.
     */
    @Test
    public void twoMostExpensiveGamingCardsShouldBePlacedIntoCart() {
        // given
        ProductListingPage gamingCardsPage = new ProductListingPage(driver, ProductCategory.GAMING_GRAPHICS_CARDS);
        gamingCardsPage.open();

        // when
        gamingCardsPage.orderProductsByPriceDesc();
        gamingCardsPage.addToCartByIndex(0);
        gamingCardsPage.continueShopping();
        gamingCardsPage.addToCartByIndex(1);
        gamingCardsPage.continueShopping();

        // then
        ShoppingCart shoppingCart = new ShoppingCart(driver);
        shoppingCart.open();
        shoppingCart.verifyProductsInCart(gamingCardsPage.getProductsInCart());
    }

}
