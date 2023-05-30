package WG.by.fpmibsu.entity;

import java.sql.Time;
public abstract class ModePrototype {
    Time modeTimer;
    protected String nameOfRegime;
    VisitorPrototype userNow;
    public abstract boolean complete();
}
