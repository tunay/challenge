package org.challenge.rest.webhooks;

public class DeliveryCard {

    public String tracking_id;
    public String delivery_status;
    public String delivery_date;
    public String delivery_return_reason;
    public String delivery_address;

    // Getters e Setters
    public String getTracking_id() {
        return tracking_id;
    }

    public void setTracking_id(String tracking_id) {
        this.tracking_id = tracking_id;
    }

    public String getDelivery_status() {
        return delivery_status;
    }

    public void setDelivery_status(String delivery_status) {
        this.delivery_status = delivery_status;
    }

    public String getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(String delivery_date) {
        this.delivery_date = delivery_date;
    }

    public String getDelivery_return_reason() {
        return delivery_return_reason;
    }

    public void setDelivery_return_reason(String delivery_return_reason) {
        this.delivery_return_reason = delivery_return_reason;
    }

    public String getDelivery_address() {
        return delivery_address;
    }

    public void setDelivery_address(String delivery_address) {
        this.delivery_address = delivery_address;
    }

    @Override
    public String toString() {
        return "DeliveryInfo{" +
                "tracking_id='" + tracking_id + '\'' +
                ", delivery_status='" + delivery_status + '\'' +
                ", delivery_date='" + delivery_date + '\'' +
                ", delivery_return_reason='" + delivery_return_reason + '\'' +
                ", delivery_address='" + delivery_address + '\'' +
                '}';
    }
}