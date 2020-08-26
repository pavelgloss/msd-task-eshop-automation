package cz.pavelgloss.homeworks.msd.testframework;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class ProductListingPage extends Page {
    private ProductCategory category;
    private List<String> productsInCart;

    public ProductListingPage(WebDriver driver, ProductCategory category) {
        super(driver);
        this.category = category;
        this.productsInCart = new ArrayList<>();
    }

    public void open() {
        String url = BASE_URL + category.getRelativeUrl();
        driver.get(url);
    }

    public void orderProductsByPriceDesc() {
        WebElement sortedByOrderTab = driver.findElement(By.cssSelector("ul.order-by li[data-order-by=PRICE][data-direction=DESC]"));
        sortedByOrderTab.click();
        WebDriverWait wait = new WebDriverWait(driver, 6);    // observed, that this operation can take very long
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.loader")));
    }

    /**
     * Adds product into cart
     * contains verification, if product is placed into cart
     *
     * @param index
     */
    public void addToCartByIndex(int index) {
        List<WebElement> productTiles = driver.findElements(By.cssSelector("#tiles .new-tile"));
        if (productTiles.size() <= index) {
            throw new RuntimeException("Cannot find product tile with index " + index);
        }

        WebElement productTile = productTiles.get(index);
        addIntoCartAndVerify(productTile);
    }

    private void addIntoCartAndVerify(WebElement productTile) {
        WebElement addCartButton = productTile.findElement(By.cssSelector("button.btn-buy"));
        String productCode = addCartButton.getAttribute("data-product-code").trim();
        this.productsInCart.add(productCode);

        addCartButton.click();

        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".buy-mode__container")));

        WebElement buyProductLink = driver.findElement(By.cssSelector(".buy-mode__content .buy-mode-product__desc a"));
        String urlWithProductCode = buyProductLink.getAttribute("href");

        Assertions.assertThat(urlWithProductCode).contains(productCode);
    }

    public void continueShopping() {
        WebElement continueShoppingButton = driver.findElement(By.cssSelector(".buy-mode__content button.btn-signal"));
        continueShoppingButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 1);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".buy-mode__container")));
    }

    public List<String> getProductsInCart() {
        return productsInCart;
    }
}
