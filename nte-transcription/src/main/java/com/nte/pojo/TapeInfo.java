package com.nte.pojo;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zhouyangying on 2017/9/22.
 */
public class TapeInfo {

    private BigDecimal audio_length;
    private List<Session> sessions;

    public BigDecimal getAudio_length() {
        return audio_length;
    }

    public void setAudio_length(BigDecimal audio_length) {
        this.audio_length = audio_length;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    @Override
    public String toString() {
        return "TapeInfo{" +
                "audio_length=" + audio_length +
                ", sessions=" + sessions +
                '}';
    }
}
