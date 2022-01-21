public class Main {
    public static void main(String[] args) {
        DriveDirection driveDirection = new DriveDirection(
            // Forward direction, 0 to 1
            0, 
            // Strafe (sideways) direction, 0 to 1
            0.1, 
            // Rotation, 0 to 1
            0, 
            // Current facing
            Math.toRadians(0)
        );

        driveDirection.zero();

        WheelsState wheelsState = new WheelsState(driveDirection);
        wheelsState.optimizePos(new WheelsState(
            // current wheels pos, FR, FL, BR, BL
            Math.toRadians(90), 
            Math.toRadians(90), 
            Math.toRadians(90), 
            Math.toRadians(90)
        ));

        double[] wheelAngles = wheelsState.getAngles();
        double FRa = Math.toDegrees(wheelAngles[0]);
        double FLa = Math.toDegrees(wheelAngles[1]);
        double BRa = Math.toDegrees(wheelAngles[2]);
        double BLa = Math.toDegrees(wheelAngles[3]);
        
        double[] wheelSpeeds = wheelsState.getSpeeds();
        double FRs = wheelSpeeds[0];
        double FLs = wheelSpeeds[1];
        double BRs = wheelSpeeds[2];
        double BLs = wheelSpeeds[3];


        double odo[] = Odemetry.getOdemetry(wheelsState, 0);

        System.out.println(Math.round(FLa) + " (" + FLs + ") \t " + Math.round(FRa) + " (" + FRs + ")");
        System.out.println();
        System.out.println(Math.round(BLa) + " (" + BLs + ") \t " + Math.round(BRa) + " (" + BRs + ")");

        System.out.println("\nOdemetry (m/s): " + odo[0] + ", " + odo[1]);
    }
}
