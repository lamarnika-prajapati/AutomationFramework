package pages;

import org.openqa.selenium.WebElement;

import java.util.Objects;

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

    public int convertDurationInMinutes(String duration) {
        duration = duration.trim();
        int hours = Integer.parseInt(duration.substring(0, duration.indexOf('h')));
        int minutes = Integer.parseInt(duration.substring(duration.indexOf(" ") + 1, duration.indexOf('m')));
        int totalMins = hours * 60 + minutes;
        return totalMins;
    }
   /* public int convertFlightPrice(String price)
    {
        price = price.replaceAll(",", "");
        return
    }*/

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        FlightDetails other = (FlightDetails) obj;
        return airlineName.equals(other.airlineName) && flightCode.equals(other.flightCode) && flightDepartureFrom.equals(other.flightDepartureFrom)
                && flightArriveTime.equals(other.flightArriveTime) && flightArriveDate.equals(other.flightArriveDate) && flightDuration.equals(other.flightDuration)
                && stopAndNon_Stop.equals(other.stopAndNon_Stop) && flightGoingTo.equals(other.flightGoingTo)
                && flightReachTime.equals(other.flightReachTime) && flightReachDate.equals(other.flightReachDate) && flightPrice == other.flightPrice; // compare values
//        return Objects.equals(airlineName,other.airlineName) && Objects.equals(flightCode,other.flightCode) && Objects.equals(flightDepartureFrom,other.flightDepartureFrom)
//                && Objects.equals(flightArriveTime,other.flightArriveTime) && Objects.equals(flightArriveDate,other.flightArriveDate) && Objects.equals(flightDuration,other.flightDuration)
//                && Objects.equals(stopAndNon_Stop,other.stopAndNon_Stop) && Objects.equals(flightGoingTo,other.flightGoingTo)
//                && Objects.equals(flightReachTime,other.flightReachTime) && Objects.equals(flightReachDate,other.flightReachDate) && Objects.equals(flightPrice,other.flightPrice); // compare values


    }

    /*public int hashCode() {
        return Objects.hash(airlineName,flightCode,flightDepartureFrom,flightArriveTime,flightArriveDate,flightDuration,stopAndNon_Stop,flightGoingTo,flightReachTime,flightReachDate,flightPrice);
    }*/
}
