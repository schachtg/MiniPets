package com.example.minipets.logic;

import com.example.minipets.ui.fetch.IFetchDirective;

public interface IFetchGameLogic {
    public IFetchDirective getNewDirective();
    public void definePetState(int petWidth, int petHeight, int xPos, int yPos);
    public boolean clickDetected(int xPos, int yPos);
    public int getPoints();
    public int gameIsClosing();
}
