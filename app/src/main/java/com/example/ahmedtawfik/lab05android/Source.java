package com.example.ahmedtawfik.lab05android;

public class Source {
    private int _sourceId;
    private String sourceName;

    public Source() {
    }

    public Source(int _sourceId, String sourceName) {
        this._sourceId = _sourceId;
        this.sourceName = sourceName;
    }

    @Override
    public String toString() {
        return "source{" +
                "sourceName='" + sourceName + '\'' +
                '}';
    }
}
