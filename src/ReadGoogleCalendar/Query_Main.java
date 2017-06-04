/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ReadGoogleCalendar;

import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;

/**
 *
 * @author 李偉挺
 */
public class Query_Main {

    private static String email = "google id";
    private static String password = "google pw";
    private static String StartYear = "2010";
    private static String StartMonths = "01";
    private static String StartDay = "18";
    private static String EndYear = "2010";
    private static String EndMonths = "07";
    private static String EndDay = "30";

    public static void main(String args[]) throws AuthenticationException, MalformedURLException, IOException, ServiceException, ParseException {

        String StartTime = StartYear + "-" + StartMonths + "-" + StartDay;
        String EndTime = EndYear + "-" + EndMonths + "-" + EndDay;
        Query q = new Query();
        System.out.println("");

        q.setQuery(email, password, "", StartTime, EndTime);


        System.out.println("ListWhat  " + q.getWhat());
        System.out.println("ListWhat size " + q.getWhat().size());

        System.out.println("");
        System.out.println("ListWhere " + q.getWhere());
        System.out.println("ListWhere size " + q.getWhere().size());
        System.out.println("");

        System.out.println("ListWhenStart " + q.getWhenStart());
        System.out.println("ListWhenStart size " + q.getWhenStart().size());
        System.out.println("");



        String ListWhat;
        String arrayWhat[];

        String ListWhere;
        String arrayWhere[];

        String ListWhenStart;
        String arrayWhenStart[];

        String ListDescription;
        String arrayDescription[];

        String ListWhenEnd;
        String arrayWhenEnd[];

        for (int i = 0; i < q.getWhenStart().size(); i++) {
            //   System.out.println("ListWhenStartSize " + q.getWhenStart().size());
            ListWhat = q.getWhat().get(i);
            ListWhere = q.getWhere().get(i);
            ListDescription = q.getDescription().get(i);
            ListWhenStart = q.getWhenStart().get(i);
            ListWhenEnd = q.getWhenEnd().get(i);
            System.out.println("");
            //    System.out.println("ListWhenStart " + i + " =" + ListWhenStart);

            arrayWhat = ListWhat.split("; ");
            arrayWhere = ListWhere.split("; ");
            arrayDescription = ListDescription.split("; ");
            arrayWhenStart = ListWhenStart.split("; ");
            arrayWhenEnd = ListWhenEnd.split("; ");



            for (String ans01 : arrayWhat) {
                System.out.println("ans01 " + ans01);
                String a1[] = ans01.split(", ");
                for (String aa1 : a1) {
                    System.out.println("aa1 " + aa1);
                }
            }
            for (String ans02 : arrayWhere) {
                System.out.println("ans02 " + ans02);
                String a2[] = ans02.split(", ");
                for (String aa2 : a2) {
                    System.out.println("aa2 " + aa2);
                }
            }
            for (String ans03 : arrayDescription) {
                System.out.println("ans03 " + ans03);
                String a3[] = ans03.split(", ");
                for (String aa3 : a3) {
                    System.out.println("aa3 " + aa3);
                }
            }
            for (String ans04 : arrayWhenStart) {
                System.out.println("ans04 " + ans04);
                String a4[] = ans04.split(", ");
                for (String aa4 : a4) {
                    System.out.println("aa4 " + aa4);
                }
            }
            for (String ans05 : arrayWhenEnd) {
                System.out.println("ans05 " + ans05);
                String a5[] = ans05.split(", ");
                for (String aa5 : a5) {
                    System.out.println("aa5 " + aa5);
                }
            }
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }

    }//main
}
