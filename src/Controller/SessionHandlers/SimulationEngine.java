package Controller.SessionHandlers;

/**
 * Created by Etrag on 27/01/2017.
 */
public  interface SimulationEngine {
    public boolean beginSimulation();
    public Object getState();
    public boolean executeStep();

}
