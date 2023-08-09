package com.yaga;

public class RandomShootStrategy implements ShootStrategy {
    @Override
    public Point getShootPoint() {
        return Point.getRandomPoint();
    }
}
