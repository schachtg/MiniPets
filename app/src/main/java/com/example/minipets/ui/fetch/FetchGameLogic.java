package com.example.minipets.ui.fetch;

public interface FetchGameLogic {
    ThrowBallDirective ballReleased(float release_x, float release_y);
    /*int*/float getPetX();
    /*int*/float getPetY();
    void generateNewPosition();
}
