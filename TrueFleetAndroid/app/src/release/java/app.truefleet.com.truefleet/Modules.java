package app.truefleet.com.truefleet;

import app.truefleet.com.truefleet.TrueFleetApp;

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
