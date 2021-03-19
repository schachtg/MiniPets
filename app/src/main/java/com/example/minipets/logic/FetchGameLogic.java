package com.example.minipets.logic;

import com.example.minipets.ui.fetch.UiFetchDirective;

public interface FetchGameLogic {
    public UiFetchDirective getNewDirective();
    public boolean clickDetected(int x_pos, int y_pos);
    public void gameIsClosing();
}
