package app.truefleet.com.truefleet;

final class Modules {
    static Object[] list(TrueFleetApp app) {
        return new Object[] {
                new TrueFleetModule(app)
        };
    }

    private Modules() {
        // No instances.
    }
}
