package creatures;

import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

import static huglife.HugLifeUtils.randomEntry;

public class Clorus extends Creature {
    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    /**
     * fraction of energy to retain when replicating.
     */
    private double repEnergyRetained = 0.5;
    /**
     * fraction of energy to give to offspring.
     */
    private double repEnergyGiven = 0.5;

    /**
     * creates clorus with energy equal to E.
     */
    public Clorus(double e){
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    /**
     * creates clorus with energy equal to 1.
     */
    public Clorus(){
        this(1);
    }

    @Override
    public void move() {
        energy-=0.03;
        if(energy < 0){
            energy = 0;
        }
    }

    @Override
    public void attack(Creature c) {
        energy = energy + c.energy();
    }

    @Override
    public Clorus replicate() {
        double babyEnergy = repEnergyGiven * energy;
        energy = energy * repEnergyRetained;
        return new Clorus(babyEnergy);
    }

    @Override
    public void stay() {
        energy-=0.01;
        if(energy < 0){
            energy = 0;
        }
    }

    /**Cloruses should obey exactly the following behavioral rules:
     *1.If there are no empty squares, the Clorus will STAY (even if there are Plips nearby they could attack since plip
     * squares do not count as empty squares).
     *2.Otherwise, if any Plips are seen, the Clorus will ATTACK one of them randomly.
     *3.Otherwise, if the Clorus has energy greater than or equal to one, it will REPLICATE to a random empty square.
     *4.Otherwise, the Clorus will MOVE to a random empty square.
     */
    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();
        boolean anyPlips = false;
        for(Direction d : neighbors.keySet()){
            if (neighbors.get(d).name().equals("empty")) {
                emptyNeighbors.add(d);
            }
        }
        for(Direction d : neighbors.keySet()){
            if (neighbors.get(d).name().equals("plip")){
                plipNeighbors.add(d);
            }
        }

        if (!plipNeighbors.isEmpty()){
            anyPlips = true;
        }
        if (emptyNeighbors.isEmpty()){
            return new Action(Action.ActionType.STAY);
        }
        else if (anyPlips == true){
            return new Action(Action.ActionType.ATTACK,randomEntry(plipNeighbors));
        }
        else if (energy >= 1.0){
            return new Action(Action.ActionType.REPLICATE,randomEntry(emptyNeighbors));
        }
        else {
            return new Action(Action.ActionType.MOVE,randomEntry(emptyNeighbors));
        }
    }

    @Override
    public Color color() {
        r = 34;
        g = 0;
        b = 231;
        return color(r, g, b);

    }
}
