package pages;

import org.openqa.selenium.WebElement;

public class FlightDetails {
    String airlineName;
    String flightCode;
    String flightDepartureFrom;
    String flightArriveTime;
    String flightArriveDate;
    String flightDuration;
    String stopAndNon_Stop;
    String flightGoingTo;
    String flightReachTime;
    String flightReachDate;
    int flightPrice;
    String flightViewAndHideFareDetails;
    WebElement flightBookNowButton;


    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public String getFlightDepartureFrom() {
        return flightDepartureFrom;
    }

    public void setFlightDepartureFrom(String flightDepartureFrom) {
        this.flightDepartureFrom = flightDepartureFrom;
    }

    public String getFlightArriveTime() {
        return flightArriveTime;
    }

    public void setFlightArriveTime(String flightArriveTime) {
        this.flightArriveTime = flightArriveTime;
    }

    public String getFlightArriveDate() {
        return flightArriveDate;
    }

    public void setFlightArriveDate(String flightArriveDate) {
        this.flightArriveDate = flightArriveDate;
    }

    public String getFlightDuration() {
        return flightDuration;
    }

    public void setFlightDuration(String flightDuration) {
        this.flightDuration = flightDuration;
    }

    public String getStopAndNon_Stop() {
        return stopAndNon_Stop;
    }

    public void setStopAndNon_Stop(String stopAndNon_Stop) {
        this.stopAndNon_Stop = stopAndNon_Stop;
    }

    public String getFlightGoingTo() {
        return flightGoingTo;
    }

    public void setFlightGoingTo(String flightGoingTo) {
        this.flightGoingTo = flightGoingTo;
    }

    public String getFlightReachDate() {
        return flightReachDate;
    }

    public void setFlightReachDate(String flightReachDate) {
        this.flightReachDate = flightReachDate;
    }

    public int getFlightPrice() {
        return flightPrice;
    }

    public void setFlightPrice(int flightPrice) {
        this.flightPrice = flightPrice;
    }

    public String getFlightReachTime() {
        return flightReachTime;
    }

    public void setFlightReachTime(String flightReachTime) {
        this.flightReachTime = flightReachTime;
    }

    public String getFlightViewAndHideFareDetails() {
        return flightViewAndHideFareDetails;
    }

    public void setFlightViewAndHideFareDetails(String flightViewAndHideFareDetails) {
        this.flightViewAndHideFareDetails = flightViewAndHideFareDetails;
    }

    public WebElement getFlightBookNowButton() {
        return flightBookNowButton;
    }

    public void setFlightBookNowButton(WebElement flightBookNowButton) {
        this.flightBookNowButton = flightBookNowButton;
    }
}
