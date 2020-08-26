package cz.pavelgloss.homeworks.msd.testframework;

public enum ProductCategory {
    GAMING_GRAPHICS_CARDS("herni-graficke-karty/produkty");

    private String relativeUrl;

    ProductCategory(String relativeUrl) {
        this.relativeUrl = relativeUrl;
    }

    public String getRelativeUrl() {
        return relativeUrl;
    }
}
