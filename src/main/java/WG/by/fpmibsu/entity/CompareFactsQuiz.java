package WG.by.fpmibsu.entity;

import java.util.Objects;

public class CompareFactsQuiz extends ModePrototype {
    int compareFactsQuizID;
    String firstQuestion;
    String secondQuestion;
    String thirdQuestion;
    String foursQuestion;

    Country firstVariant;
    Country secondVariant;
    Country thirdVariant;
    Country foursVariant;
    int firstAnswer;
    int secondAnswer;
    int thirdAnswer;
    int foursAnswer;

    public int getCompareFactsQuizID() {
        return compareFactsQuizID;
    }

    public void setCompareFactsQuizID(int compareFactsQuizID) {
        this.compareFactsQuizID = compareFactsQuizID;
    }

    public String getFirstQuestion() {
        return firstQuestion;
    }

    public void setFirstQuestion(String firstQuestion) {
        this.firstQuestion = firstQuestion;
    }

    public String getSecondQuestion() {
        return secondQuestion;
    }

    public void setSecondQuestion(String secondQuestion) {
        this.secondQuestion = secondQuestion;
    }

    public String getThirdQuestion() {
        return thirdQuestion;
    }

    public void setThirdQuestion(String thirdQuestion) {
        this.thirdQuestion = thirdQuestion;
    }

    public String getFourthQuestion() {
        return foursQuestion;
    }

    public void setFourthQuestion(String foursQuestion) {
        this.foursQuestion = foursQuestion;
    }

    public Country getFirstVariant() {
        return firstVariant;
    }

    public void setFirstVariant(Country firstVariant) {
        this.firstVariant = firstVariant;
    }

    public Country getSecondVariant() {
        return secondVariant;
    }

    public void setSecondVariant(Country secondVariant) {
        this.secondVariant = secondVariant;
    }

    public Country getThirdVariant() {
        return thirdVariant;
    }

    public void setThirdVariant(Country thirdVariant) {
        this.thirdVariant = thirdVariant;
    }

    public Country getFourthVariant() {
        return foursVariant;
    }

    public void setFourthVariant(Country foursVariant) {
        this.foursVariant = foursVariant;
    }

    public int getFirstAnswer() {
        return firstAnswer;
    }

    public void setFirstAnswer(int firstAnswer) {
        this.firstAnswer = firstAnswer;
    }

    public int getSecondAnswer() {
        return secondAnswer;
    }

    public void setSecondAnswer(int secondAnswer) {
        this.secondAnswer = secondAnswer;
    }

    public int getThirdAnswer() {
        return thirdAnswer;
    }

    public void setThirdAnswer(int thirdAnswer) {
        this.thirdAnswer = thirdAnswer;
    }

    public int getFourthAnswer() {
        return foursAnswer;
    }

    public void setFourthAnswer(int foursAnswer) {
        this.foursAnswer = foursAnswer;
    }

    public CompareFactsQuiz(int compareFactsQuizID,
                            String firstQuestion, String secondQuestion, String thirdQuestion, String foursQuestion,
                            Country firstVariant, Country secondVariant, Country thirdVariant, Country foursVariant,
                            int firstAnswer, int secondAnswer, int thirdAnswer, int foursAnswer) {
        this.compareFactsQuizID = compareFactsQuizID;
        this.firstQuestion = firstQuestion;
        this.secondQuestion = secondQuestion;
        this.thirdQuestion = thirdQuestion;
        this.foursQuestion = foursQuestion;

        this.firstVariant = firstVariant;
        this.secondVariant = secondVariant;
        this.thirdVariant = thirdVariant;
        this.foursVariant = foursVariant;

        this.firstAnswer = firstAnswer;
        this.secondAnswer = secondAnswer;
        this.thirdAnswer = thirdAnswer;
        this.foursAnswer = foursAnswer;

        this.nameOfRegime = "CompareFactsQuiz";
    }

    public boolean complete() {
        return true;
    }

    @Override
    public String toString() {
        return "CompareFactsQuiz{" +
                "compareFactsQuizID=" + compareFactsQuizID +
                ", firstQuestion='" + firstQuestion + '\'' +
                ", secondQuestion='" + secondQuestion + '\'' +
                ", thirdQuestion='" + thirdQuestion + '\'' +
                ", foursQuestion='" + foursQuestion + '\'' +
                ", firstVariant=" + firstVariant +
                ", secondVariant=" + secondVariant +
                ", thirdVariant=" + thirdVariant +
                ", foursVariant=" + foursVariant +
                ", firstAnswer=" + firstAnswer +
                ", secondAnswer=" + secondAnswer +
                ", thirdAnswer=" + thirdAnswer +
                ", foursAnswer=" + foursAnswer +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompareFactsQuiz that = (CompareFactsQuiz) o;
        return compareFactsQuizID == that.compareFactsQuizID && firstAnswer == that.firstAnswer && secondAnswer == that.secondAnswer && thirdAnswer == that.thirdAnswer && foursAnswer == that.foursAnswer && Objects.equals(firstQuestion, that.firstQuestion) && Objects.equals(secondQuestion, that.secondQuestion) && Objects.equals(thirdQuestion, that.thirdQuestion) && Objects.equals(foursQuestion, that.foursQuestion) && Objects.equals(firstVariant, that.firstVariant) && Objects.equals(secondVariant, that.secondVariant) && Objects.equals(thirdVariant, that.thirdVariant) && Objects.equals(foursVariant, that.foursVariant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(compareFactsQuizID, firstQuestion, secondQuestion, thirdQuestion, foursQuestion, firstVariant, secondVariant, thirdVariant, foursVariant, firstAnswer, secondAnswer, thirdAnswer, foursAnswer);
    }
}
