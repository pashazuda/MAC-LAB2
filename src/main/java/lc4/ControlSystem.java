package lc4;

public class ControlSystem {
    private final DataContainer ds;

    public ControlSystem(DataContainer ds) {
        this.ds = ds;
    }

    public double  regulate(double controlAction){
        controlAction /=2;
        ds.set("CA", controlAction);
        System.out.println("Control action sucessufully finished");
        return ds.get("CA");
    }

}
