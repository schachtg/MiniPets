package com.example.minipets.logic;

import com.example.minipets.ui.fetch.IFetchDirective;

public interface IFetchGameLogic {
    public IFetchDirective getNewDirective();
    public void definePetState(int petWidth, int petHeight, int x_pos, int y_pos);
    public boolean clickDetected(int x_pos, int y_pos);
    public int getPoints();
    public void gameIsClosing();
}
