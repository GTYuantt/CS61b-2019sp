package creatures;

import huglife.*;
import org.junit.Test;

import java.awt.*;
import java.util.HashMap;

import static org.junit.Assert.*;

public class TestClorus {

    @Test
    public void testBasics() {
        Clorus p = new Clorus(2);
        assertEquals(2, p.energy(), 0.01);
        assertEquals(new Color(34, 0, 231), p.color());
        p.move();
        assertEquals(1.97, p.energy(), 0.01);
        p.move();
        assertEquals(1.94, p.energy(), 0.01);
        p.stay();
        assertEquals(1.93, p.energy(), 0.01);
        p.stay();
        assertEquals(1.92, p.energy(), 0.01);
    }


    @Test
    public void testReplicate() {
        // TODO
        Clorus p = new Clorus(2);
        Clorus babyp = p.replicate();
        assertFalse( p == babyp);
        assertEquals(1,p.energy(),0.01);
        assertEquals(1,babyp.energy(),0.01);
    }

    @Test
    public void testChoose() {

        //If there are no empty squares, the Clorus will STAY (even if there are Plips nearby they could attack since plip
        Clorus p = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Plip());

        Action actual = p.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);


        //Otherwise, if any Plips are seen, the Clorus will ATTACK one of them randomly.
        p = new Clorus(1.2);
        HashMap<Direction, Occupant> topPlip = new HashMap<Direction, Occupant>();
        topPlip.put(Direction.TOP, new Plip());
        topPlip.put(Direction.BOTTOM, new Empty());
        topPlip.put(Direction.LEFT, new Impassible());
        topPlip.put(Direction.RIGHT, new Impassible());

        actual = p.chooseAction(topPlip);
        expected = new Action(Action.ActionType.ATTACK, Direction.TOP);

        assertEquals(expected, actual);


        //Otherwise, if the Clorus has energy greater than or equal to one, it will REPLICATE to a random empty square.
        p = new Clorus(1.2);
        HashMap<Direction, Occupant> rightEmpty = new HashMap<Direction, Occupant>();
        rightEmpty.put(Direction.TOP, new Impassible());
        rightEmpty.put(Direction.BOTTOM, new Impassible());
        rightEmpty.put(Direction.LEFT, new Impassible());
        rightEmpty.put(Direction.RIGHT, new Empty());

        Action actual1 = p.chooseAction(rightEmpty);
        Action expected1 = new Action(Action.ActionType.REPLICATE, Direction.RIGHT);

        assertEquals(expected1, actual1);


        //Otherwise, the Clorus will MOVE to a random empty square.
        p = new Clorus(.99);

        Action actual2 = p.chooseAction(rightEmpty);
        Action expected2 = new Action(Action.ActionType.MOVE, Direction.RIGHT);

        assertEquals(expected2, actual2);


    }
}
