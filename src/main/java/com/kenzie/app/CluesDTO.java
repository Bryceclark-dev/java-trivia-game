package com.kenzie.app;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
@JsonPropertyOrder({"id", "answer", "question", "value", "categoryId", "gameId", "invalidCount", "category", "game", "cannon"})
public class CluesDTO {
    @JsonProperty("id")
    private int id;
    @JsonProperty("answer")
    private String answer;
    @JsonProperty("question")
    private String question;
    @JsonProperty("value")
    private int value;
    @JsonProperty("categoryId")
    private int categoryId;
    @JsonProperty("gameId")
    private int gameId;
    @JsonProperty("invalidCount")
    private int invalidCount;
    @JsonProperty("category")
    private Category category;
    @JsonProperty("game")
    private Game game;
    @JsonProperty("canon")
    private boolean canon;

    public boolean isCanon() {
        return canon;
    }

    @Override
    public String toString() {
        return "CluesDTO{" +
                "id=" + id +
                ", answer='" + answer + '\'' +
                ", question='" + question + '\'' +
                ", value=" + value +
                ", categoryId=" + categoryId +
                ", gameId=" + gameId +
                ", invalidCount=" + invalidCount +
                ", category=" + category.toString() +
                ", game=" + game.toString() +
                ", canon=" + canon +
                '}';
    }

    public void setCanon(boolean canon) {
        this.canon = canon;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getInvalidCount() {
        return invalidCount;
    }

    public void setInvalidCount(int invalidCount) {
        this.invalidCount = invalidCount;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static class Game {
        @JsonProperty("aired")
        private String aired;

        @JsonProperty("canon")
        private boolean canon;

        @Override
        public String toString() {
            return "Game{" +
                    "aired='" + aired + '\'' +
                    ", canon=" + canon +
                    '}';
        }

        public boolean getCanon() {
            return canon;
        }

        public void setCanon(boolean canon) {
            this.canon = canon;
        }

        public String getAired() {
            return aired;
        }

        public void setAired(String aired) {
            this.aired = aired;
        }
    }

    public static class Category {
        @JsonProperty("id")
        private int id;
        @JsonProperty("title")
        private String title;
        @JsonProperty("canon")
        private boolean canon;

        @Override
        public String toString() {
            return "Category{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", canon=" + canon +
                    '}';
        }

        public boolean getCanon() {
            return canon;
        }

        public void setCanon(boolean canon) {
            this.canon = canon;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}


