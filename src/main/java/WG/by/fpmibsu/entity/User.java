package WG.by.fpmibsu.entity;

import java.util.Objects;

public class User extends VisitorPrototype {
    int ID;
    String password;
    double flagsPercent;
    double compareFactsPercent;
    double factQuizPercent;
    double mapPercent;
    double userMark;
    boolean isAdmin;
    int flagPassed;
    int mapPassed;
    int factQuizPassed;
    int compareFactsPassed;
    int flagFailed;
    int mapFailed;
    int factQuizFailed;
    int compareFactsFailed;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getFlagsPercent() {
        return flagsPercent;
    }

    public void setFlagsPercent(double flagsPercent) {
        this.flagsPercent = flagsPercent;
    }

    public double getCompareFactsPercent() {
        return compareFactsPercent;
    }

    public void setCompareFactsPercent(double compareFactsPercent) {
        this.compareFactsPercent = compareFactsPercent;
    }

    public double getFactQuizPercent() {
        return factQuizPercent;
    }

    public void setFactQuizPercent(double factQuizPercent) {
        this.factQuizPercent = factQuizPercent;
    }

    public double getMapPercent() {
        return mapPercent;
    }

    public void setMapPercent(double mapPercent) {
        this.mapPercent = mapPercent;
    }

    public double getUserMark() {
        return userMark;
    }

    public void setUserMark(double userMark) {
        this.userMark = userMark;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public int getFlagPassed() {
        return flagPassed;
    }

    public void setFlagPassed(int flagPassed) {
        this.flagPassed = flagPassed;
    }

    public int getMapPassed() {
        return mapPassed;
    }

    public void setMapPassed(int mapPassed) {
        this.mapPassed = mapPassed;
    }

    public int getFactQuizPassed() {
        return factQuizPassed;
    }

    public void setFactQuizPassed(int factQuizPassed) {
        this.factQuizPassed = factQuizPassed;
    }

    public int getCompareFactsPassed() {
        return compareFactsPassed;
    }

    public void setCompareFactsPassed(int compareFactsPassed) {
        this.compareFactsPassed = compareFactsPassed;
    }

    public int getFlagFailed() {
        return flagFailed;
    }

    public void setFlagFailed(int flagFailed) {
        this.flagFailed = flagFailed;
    }

    public int getMapFailed() {
        return mapFailed;
    }

    public void setMapFailed(int mapFailed) {
        this.mapFailed = mapFailed;
    }

    public int getFactQuizFailed() {
        return factQuizFailed;
    }

    public void setFactQuizFailed(int factQuizFailed) {
        this.factQuizFailed = factQuizFailed;
    }

    public int getCompareFactsFailed() {
        return compareFactsFailed;
    }

    public void setCompareFactsFailed(int compareFactsFailed) {
        this.compareFactsFailed = compareFactsFailed;
    }
    String nickname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public User(int ID, String nickname, String password,
                int flagPassed, int mapPassed, int factQuizPassed, int compareFactsPassed,
                int flagFailed, int mapFailed, int factQuizFailed, int compareFactsFailed,
                double userMark, boolean isAdmin) {
        this.ID = ID;
        this.nickname = nickname;
        this.password = password;

        this.flagPassed = flagPassed;
        this.mapPassed = mapPassed;
        this.factQuizPassed = factQuizPassed;
        this.compareFactsPassed =compareFactsPassed;

        this.flagFailed = flagFailed;
        this.mapFailed = mapFailed;
        this.factQuizFailed = factQuizFailed;
        this.compareFactsFailed = compareFactsFailed;

        if (flagFailed+flagPassed != 0) {
            this.flagsPercent = (double) (flagPassed / (flagPassed + flagFailed));
        } else this.flagsPercent = 0;

        if (factQuizFailed+factQuizPassed != 0) {
            this.factQuizPercent = (double) (factQuizPassed / (factQuizPassed + factQuizFailed));
        } else this.factQuizPercent = 0;

        if (compareFactsPassed+compareFactsFailed != 0) {
            this.compareFactsPercent = (double) (compareFactsPassed / (compareFactsFailed + compareFactsPassed));
        } else this.mapPercent = 0;

        if (isAdmin) {
            this.userMark = 10;
            this.isAdmin = true;
        } else {
            this.userMark = userMark;
            this.isAdmin = false;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", password='" + password + '\'' +
                ", flagsPercent=" + flagsPercent +
                ", compareFactsPercent=" + compareFactsPercent +
                ", factQuizPercent=" + factQuizPercent +
                ", mapPercent=" + mapPercent +
                ", userMark=" + userMark +
                ", isAdmin=" + isAdmin +
                ", flagPassed=" + flagPassed +
                ", mapPassed=" + mapPassed +
                ", factQuizPassed=" + factQuizPassed +
                ", compareFactsPassed=" + compareFactsPassed +
                ", flagFailed=" + flagFailed +
                ", mapFailed=" + mapFailed +
                ", factQuizFailed=" + factQuizFailed +
                ", compareFactsFailed=" + compareFactsFailed +
                ", nickname='" + nickname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return ID == user.ID && Double.compare(user.flagsPercent, flagsPercent) == 0 && Double.compare(user.compareFactsPercent, compareFactsPercent) == 0 && Double.compare(user.factQuizPercent, factQuizPercent) == 0 && Double.compare(user.mapPercent, mapPercent) == 0 && Double.compare(user.userMark, userMark) == 0 && isAdmin == user.isAdmin && flagPassed == user.flagPassed && mapPassed == user.mapPassed && factQuizPassed == user.factQuizPassed && compareFactsPassed == user.compareFactsPassed && flagFailed == user.flagFailed && mapFailed == user.mapFailed && factQuizFailed == user.factQuizFailed && compareFactsFailed == user.compareFactsFailed && Objects.equals(password, user.password) && Objects.equals(nickname, user.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, password, flagsPercent, compareFactsPercent, factQuizPercent, mapPercent, userMark, isAdmin, flagPassed, mapPassed, factQuizPassed, compareFactsPassed, flagFailed, mapFailed, factQuizFailed, compareFactsFailed, nickname);
    }
}
