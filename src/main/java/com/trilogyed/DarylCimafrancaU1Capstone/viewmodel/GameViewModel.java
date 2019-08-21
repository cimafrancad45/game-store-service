package com.trilogyed.DarylCimafrancaU1Capstone.viewmodel;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;

public class GameViewModel {
    private int gameId;
    @NotEmpty(message = "Please enter the game's title.")
    private String title;
    @NotEmpty(message = "Please enter the game's ERSB rating.")
    private String ersbRating;
    @NotEmpty(message = "Please enter the game's description.")
    private String description;
    @NotNull(message = "Please the enter the game's price.")
    @PositiveOrZero(message = "Price is invalid")
    @DecimalMax(value = "9999.99", inclusive = true)
    private BigDecimal price;
    @NotEmpty(message = "Please enter the game's studio.")
    private String studio;
    @Positive(message = "Quantity must be a non-negative number.")
    private int quantity;

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getErsbRating() {
        return ersbRating;
    }

    public void setErsbRating(String ersbRating) {
        this.ersbRating = ersbRating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object g) {
        if (this == null) return true;
        if (g == null || getClass() != g.getClass()) return false;
        GameViewModel game = (GameViewModel) g;

        return gameId == game.gameId &&
                title.equals(game.title) &&
                ersbRating.equals(game.ersbRating) &&
                description.equals(game.description) &&
                price.equals(game.price) &&
                quantity == game.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, title, ersbRating, description, price, studio, quantity);
    }
}
