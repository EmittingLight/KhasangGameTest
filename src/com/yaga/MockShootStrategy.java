package com.yaga;

public class MockShootStrategy implements ShootStrategy {
    @Override
    public Point getShootPoint() {
        return new Point(0, 0);
    }
}
