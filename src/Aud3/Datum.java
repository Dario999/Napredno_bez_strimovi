package Aud3;

public class Datum {

    private int days;

    private static final int FIRST_YEAR = 1800;
    private static final int LAST_YEAR = 2500;
    private static final int DAYS_IN_YEAR = 365;

    private static final int[] daysOfMonth = {
            31,28,31,30,31,30,31,31,30,31,30,31
    };

    private static int[] daysFromJan1;

    private static boolean isLeapyear(int year){
        return (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0));
    }

    public Datum(int days){
        this.days = days;
    }

    public Datum(int day,int month,int year){
        int days = 0;

        //dali e nadvor od opsegot
        if(year < FIRST_YEAR || year > LAST_YEAR){
            throw new RuntimeException();
        }
        //gi dodava denovite od site godini od FIRST_YEAR do year(vo argumentot)
        for (int i = FIRST_YEAR; i < year ; i++) {
            if(isLeapyear(i)){
                days += 366;
            }else {
                days += 365;
            }
        }

        //gi dodava denoviete od site meseci vo poslednata godina osven posledniot
        for (int i = 1; i < month; i++) {
            if(!isLeapyear(year)) {
                days += daysOfMonth[i - 1];
            }else {
                if(i == 2){
                    days += 29;
                }else {
                    day += daysOfMonth[i - 1];
                }
            }
        }

        //dodava denovite od posledniot mesec
        days += day;

        this.days = days;
    }

    public int subtract(Datum datum){
        return this.days - datum.days;
    }

    public Datum increment(int days){
        return new Datum(this.days + days);
    }

    @Override
    public boolean equals(Object obj) {
        Datum datum = (Datum)obj;
        return this.days == datum.days;
    }

    public int compareTo(Datum other){
        return this.days - other.days;
    }

    @Override
    public String toString() {
        int days_temp = this.days;
        int day,month,year;

        year = days_temp / 365;
        days_temp %= 365;
        int i = 0;
        int counter = 0;
        while (days_temp - daysOfMonth[i] >= 0){
            counter++;
        }
        day = days_temp;
        month = counter;

        return String.format("%02d.%02d.%04d", day,month,year);
    }

    public static void main(String[] args) {

        Date sample = new Date(1,10,2012);
        System.out.println("1: " + sample.substract(new Date(1,1,2000)));
        System.out.println("2: " + sample);
        sample = new Date(1,1,1800);
        System.out.println("3: " + sample);
        sample = new Date(31,12,2500);
        //System.out.println("4: " + daysTillJan1[daysTillJan1.length-1]);
        //System.out.println("5: " + sample.days);
        System.out.println("6: " + sample);
        sample = new Date(30,11,2012);
        System.out.println("7: " + sample);
        sample = sample.increment(100);
        System.out.println("8: " + sample);

    }

}
