package WG.by.fpmibsu.entity;

import java.util.Objects;

public class FlagQuiz extends ModePrototype {
    int flagQuizID;
    int answer;
    Country answerCountry;
    Country firstVariant;
    Country secondVariant;
    Country thirdVariant;
    Country foursVariant;

    public int getFlagQuizID() {
        return flagQuizID;
    }

    public void setFlagQuizID(int flagQuizID) {
        this.flagQuizID = flagQuizID;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public Country getAnswerCountry() {
        return answerCountry;
    }

    public void setAnswerCountry(Country answerCountry) {
        this.answerCountry = answerCountry;
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

    public FlagQuiz(int flagQuizID, Country answerCountry, Country firstVariant, Country secondVariant, Country thirdVariant, Country foursVariant) {
        this.flagQuizID = flagQuizID;
        this.answerCountry = answerCountry;

        this.firstVariant = firstVariant;
        this.secondVariant = secondVariant;
        this.thirdVariant = thirdVariant;
        this.foursVariant = foursVariant;

        if (answerCountry.getID() == firstVariant.getID()) {
            this.answer = 1;
        }
        if (answerCountry.getID() == secondVariant.getID()) {
            this.answer = 2;
        }
        if (answerCountry.getID() == thirdVariant.getID()) {
            this.answer = 3;
        }
        if (answerCountry.getID() == foursVariant.getID()) {
            this.answer = 4;
        }

        this.nameOfRegime = "FLAG QUIZ";
    }

    public boolean complete() {
        return true;
    }

    @Override
    public String toString() {
        return "FlagQuiz{" +
                "flagQuizID=" + flagQuizID +
                ", answer=" + answer +
                ", answerCountry=" + answerCountry +
                ", firstVariant=" + firstVariant +
                ", secondVariant=" + secondVariant +
                ", thirdVariant=" + thirdVariant +
                ", foursVariant=" + foursVariant +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlagQuiz flagQuiz = (FlagQuiz) o;
        return flagQuizID == flagQuiz.flagQuizID && answer == flagQuiz.answer && Objects.equals(answerCountry, flagQuiz.answerCountry) && Objects.equals(firstVariant, flagQuiz.firstVariant) && Objects.equals(secondVariant, flagQuiz.secondVariant) && Objects.equals(thirdVariant, flagQuiz.thirdVariant) && Objects.equals(foursVariant, flagQuiz.foursVariant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flagQuizID, answer, answerCountry, firstVariant, secondVariant, thirdVariant, foursVariant);
    }
}