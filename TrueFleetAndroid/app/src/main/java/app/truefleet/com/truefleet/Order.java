package app.truefleet.com.truefleet;

/**
 * Created by night_000 on 10/28/2014.
 */
public class Order {
    private  final String trailerAddress;
    private final String trailerNumber;
    private final String releaseNumber;
    private final String deliveryAddress;
    private final String pickupNumbers;
    private final String specialInstructions;
    private final String orderType;

    Order(String trailerAddress, String  trailerNumber, String releaseNumber, String deliveryAddress, String pickupNumbers, String specialInstructions, String orderType) {
        this.trailerAddress = trailerAddress;
        this.trailerNumber = trailerNumber;
        this.releaseNumber = releaseNumber;
        this.deliveryAddress = deliveryAddress;
        this.pickupNumbers = pickupNumbers;
        this.specialInstructions = specialInstructions;
        this.orderType = orderType;
    }

   public String getTrailerAddress() {
       return trailerAddress;
   }
    public String getTrailerNumber() {
        return trailerNumber;
    }
    public String getDeliveryAddress() {
        return deliveryAddress;
    }
    public String getPickupNumbers() {
        return pickupNumbers;
    }
    public String getSpecialInstructions() {
        return specialInstructions;
    }
}
