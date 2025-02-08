package org.challenge.rest.webhooks;

public class CVVUpdate {
    public String account_id;
    public String card_id;
    public int next_cvv;
    public String expiration_date;

    // Getters e Setters
    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public int getNext_cvv() {
        return next_cvv;
    }

    public void setNext_cvv(int next_cvv) {
        this.next_cvv = next_cvv;
    }

    public String getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(String expiration_date) {
        this.expiration_date = expiration_date;
    }

    @Override
    public String toString() {
        return "CVVUpdateInfo{" +
                "account_id='" + account_id + '\'' +
                ", card_id='" + card_id + '\'' +
                ", next_cvv=" + next_cvv +
                ", expiration_date='" + expiration_date + '\'' +
                '}';
    }
}