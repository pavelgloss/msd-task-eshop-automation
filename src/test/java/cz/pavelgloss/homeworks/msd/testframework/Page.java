package cz.pavelgloss.homeworks.msd.testframework;

import org.openqa.selenium.WebDriver;

public abstract class Page {
    protected WebDriver driver;
    public static final String BASE_URL = "https://www.czc.cz/";

    public Page(WebDriver driver) {
        this.driver = driver;
    }

    public abstract void open();
}
