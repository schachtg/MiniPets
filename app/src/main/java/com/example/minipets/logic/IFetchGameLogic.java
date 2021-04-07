package com.example.minipets.logic;

import com.example.minipets.ui.fetch.UiFetchDirective;

public interface IFetchGameLogic {
    public UiFetchDirective getNewDirective();
    public void definePetState(int petWidth, int petHeight, int x_pos, int y_pos);
    public boolean clickDetected(int x_pos, int y_pos);
    public void gameIsClosing();
}
