package com.m1enkrafftman.rogue.misc;

import com.m1enkrafftman.rogue.entity.Entity;

public class CollisionHelper {

    public static boolean intersects(int minX, int maxX, int minY, int maxY, int minX2, int maxX2, int minY2, int maxY2) {
        return (maxX2 > minX && maxX + 1 > minX2) && (maxY2 > minY - 1 && maxY > minY2);
    }

    public static boolean intersects(Entity ent1, Entity ent2) {
        return intersects(ent1.getMinX(), ent1.getMinX() + ent1.getWidth(), ent1.getMinY(), ent1.getMinY() + ent1.getWidth(),
        		ent2.getMinX(), ent2.getMinX() + ent1.getWidth(), ent2.getMinY(), ent2.getMinY() + ent1.getWidth());
    }

    public static boolean intersects(Entity ent, int minX, int maxX, int minY, int maxY) {
        return intersects(ent.getMinX(), ent.getMinX() + ent.getWidth(), ent.getMinY(), ent.getMinY() + ent.getWidth(), minX, maxX, minY, maxY);
    }
}