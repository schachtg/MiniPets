package com.example.minipets.ui.fetch;

public interface FetchGameLogic {
    ThrowBallDirective ballReleased(float release_x, float release_y);
    int getPetX();
    int getPetY();
    void generateNewPosition();
}
