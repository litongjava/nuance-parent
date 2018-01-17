package com.nte.pojo;
import java.math.BigDecimal;

public class Session {
    private String speaker;
    private BigDecimal start;
    private BigDecimal end;
    private String text;

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public BigDecimal getStart() {
        return start;
    }

    public void setStart(BigDecimal start) {
        this.start = start;
    }

    public BigDecimal getEnd() {
        return end;
    }

    public void setEnd(BigDecimal end) {
        this.end = end;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Session{" +
                "speaker='" + speaker + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", text='" + text + '\'' +
                '}';
    }
}