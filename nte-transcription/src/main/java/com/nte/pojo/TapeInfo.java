package com.nte.pojo;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zhouyangying on 2017/9/22.
 */
public class TapeInfo {

    private BigDecimal audio_length;
    private Long transcription_time;
    private List<Session> sessions;

    public TapeInfo() {
    }

    public TapeInfo(BigDecimal audio_length, Long transcription_time, List<Session> sessions) {
        this.audio_length = audio_length;
        this.transcription_time = transcription_time;
        this.sessions = sessions;
    }

    public BigDecimal getAudio_length() {
        return audio_length;
    }

    public Long getTranscription_time() {
        return transcription_time;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setAudio_length(BigDecimal audio_length) {
        this.audio_length = audio_length;
    }

    public void setTranscription_time(Long transcription_time) {
        this.transcription_time = transcription_time;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    @Override
    public String toString() {
        return "TapeInfo{" +
                "audio_length=" + audio_length +
                ", transcription_time=" + transcription_time +
                ", sessions=" + sessions +
                '}';
    }
}
