public class Odemetry {
    private final static double DRIVETRAIN_LENGTH_METERS = 1;
    private final static double DRIVETRAIN_WIDTH_METERS = 2;
    private final static double DRIVETRAIN_DIAMETER = Math.sqrt((DRIVETRAIN_LENGTH_METERS * DRIVETRAIN_LENGTH_METERS) + (DRIVETRAIN_WIDTH_METERS * DRIVETRAIN_WIDTH_METERS));
    private final static double MAX_VELOCITY_METERS_PER_SECOND = 10;

    public static double[] getOdemetry(WheelsState currentState, double gyro) {
        double[] out = new double[2];

        // FR, FL, BR, BL
        double angles[] = currentState.getAngles();
        double speeds[] = currentState.getSpeeds();

        for (int i = 0; i < speeds.length; i++)
            speeds[i] *= MAX_VELOCITY_METERS_PER_SECOND;

        double bFL = Math.sin(angles[1]) * speeds[1];
        double bFR = Math.sin(angles[0]) * speeds[0];
        double aRL = Math.sin(angles[3]) * speeds[3];
        double aRR = Math.sin(angles[2]) * speeds[2];

        double dFL = Math.cos(angles[1]) * speeds[1];
        double cFR = Math.cos(angles[0]) * speeds[0];
        double dRL = Math.cos(angles[3]) * speeds[3];
        double cRR = Math.cos(angles[2]) * speeds[2];

        double a = (aRR + aRL) / 2;
        double b = (bFL + bFR) / 2;
        double c = (cFR + cRR) / 2;
        double d = (dFL + dRL) / 2;

        double l = (DRIVETRAIN_LENGTH_METERS / DRIVETRAIN_DIAMETER);
        double w = (DRIVETRAIN_WIDTH_METERS / DRIVETRAIN_DIAMETER);

        double rot1 = (b - a) / l;
        double rot2 = (c - d) / w;
        double rot = (rot1 + rot2) / 2;

        double fwd1 = rot * (l / 2) + a;
        double fwd2 = -rot * (l / 2) + b;
        double fwd = (fwd1 + fwd2) / 2;

        double str1 = rot * (w / 2) + c;
        double str2 = -rot * (w / 2) + d;
        double str = (str1 + str2) / 2;

        out[1] = fwd * Math.cos(gyro) + str * Math.sin(gyro); // FWD
        out[0] = str * Math.cos(gyro) - fwd * Math.sin(gyro); // STR

        return out;
    }
}