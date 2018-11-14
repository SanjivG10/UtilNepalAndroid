import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TEST {

        public static void main(String []args){



            SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
            String inputString1 = "1 1 1944";
            String inputString2 = "14 11 2018";

            try {
                Date date1 = myFormat.parse(inputString1);
                Date date2 = myFormat.parse(inputString2);
                long diff = date2.getTime() - date1.getTime();
                System.out.println ("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
            } catch (ParseException e) {
                e.printStackTrace();
            }

    }


}
