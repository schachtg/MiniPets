package com.example.minipets.ui.fetch;

public interface FetchGameLogic {
    public FetchDirective petWasClicked();
    public FetchDirective timesUp();
    public FetchDirective getNewDirective();
}
