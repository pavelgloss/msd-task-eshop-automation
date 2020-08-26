package cz.pavelgloss.homeworks.msd.testframework;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ShoppingCart extends Page {

    public ShoppingCart(WebDriver driver) {
        super(driver);
    }

    public void open() {
        String url = BASE_URL + "kosik/";
        driver.get(url);
    }

    public void verifyProductsInCart(List<String> productCodes) {
        WebElement basketDiv = driver.findElement(By.cssSelector("#basket-table"));
        List<WebElement> codeSpanList = basketDiv.findElements(By.cssSelector(".op-product .product-name .product-code"));

        Assertions.assertThat(codeSpanList).hasSameSizeAs(productCodes);

        for (WebElement codeSpan : codeSpanList) {
            String productCode = codeSpan.getText().trim();
            Assertions.assertThat(productCodes).contains(productCode);
        }
    }
}
