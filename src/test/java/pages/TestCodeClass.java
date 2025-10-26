package pages;

public class TestCodeClass {
    public static void main(String[] args) {
        String duration="10h 00m";
        duration=duration.trim();
        int hours=Integer.parseInt(duration.substring(0, duration.indexOf('h')));
        System.out.println(hours);
        int minutes=Integer.parseInt(duration.substring(duration.indexOf(" ")+1,duration.indexOf('m')));
        System.out.println(minutes);
        int totalMins=hours*60+minutes;
        System.out.println(totalMins);

    }



}
