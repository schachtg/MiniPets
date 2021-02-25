package com.example.minipets.ui.fetch;

// these are possible locations where a target could be placed
// these aid to determine how the target decides if a ball on a given
// trajectory will hit it.
//------------------------------------------------------------------------
public enum TargetPosition {
    TOP_LEFT,
    TOP_MID,        //target sits with some corners to the left, and some corners to the right of the origine
    TOP_RIGHT,
    MID_LEFT,
    MID_MID,
    MID_RIGHT,
    BOTTOM_LEFT,
    BOTTOM_MID,
    BOTTOM_RIGHT;
}
