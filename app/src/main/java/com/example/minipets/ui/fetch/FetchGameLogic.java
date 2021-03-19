package com.example.minipets.ui.fetch;

public interface FetchGameLogic {
    public FetchDirective getNewDirective();
    public boolean clickDetected(int x_pos, int y_pos);
    public void gameIsClosing();
}
