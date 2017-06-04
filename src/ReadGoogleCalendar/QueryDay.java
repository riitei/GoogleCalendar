/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ReadGoogleCalendar;

import com.google.gdata.client.calendar.CalendarQuery;
import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.Content;
import com.google.gdata.data.DateTime;
import com.google.gdata.data.TextContent;
import com.google.gdata.data.calendar.CalendarEventEntry;
import com.google.gdata.data.calendar.CalendarEventFeed;
import com.google.gdata.data.extensions.When;
import com.google.gdata.data.extensions.Where;

import com.google.gdata.util.ServiceException;
import java.io.IOException;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 李偉挺
 */
public class QueryDay implements Runnable {

    private List<String> ListWhat;
    private List<String> ListWhere;
    private List<String> ListDescription;
    private List<String> ListWhenStart;
    private List<String> ListWhenEnd;
    private int size;

    public void run() {
        try {
            ListWhat = new LinkedList<String>();
            ListWhere = new LinkedList<String>();
            ListDescription = new LinkedList<String>();
            ListWhenStart = new LinkedList<String>();
            ListWhenEnd = new LinkedList<String>();

            String[] array = Thread.currentThread().getName().split(", ");
            CalendarService myService = new CalendarService("exampleCo-exampleApp-1");
            myService.setUserCredentials(array[0], array[1]);
            URL feedUrl = new URL("http://www.google.com/calendar/feeds/default/private/full");
            CalendarQuery myQuery = new CalendarQuery(feedUrl);
            myQuery.setMinimumStartTime(DateTime.parseDateTime(array[2] + "T00:00:00"));
            myQuery.setMaximumStartTime(DateTime.parseDateTime(array[3] + "T23:59:59"));
            myQuery.setFullTextQuery(array[4]);

            CalendarEventFeed resultFeed = myService.query(myQuery, CalendarEventFeed.class);

            size = resultFeed.getTotalResults();
//            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//            System.out.println(" 共有 " + size + " 幾筆資料 ");
//            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            int num = 0;
            ListWhat.add("0");
            ListWhere.add("0,");
            ListDescription.add("0,");
            ListWhenStart.add("0");
            ListWhenEnd.add("0");
            if (size != 0) {
                for (int i = size - 1; i > -1; i--) {
                    num = num + 1;

                    CalendarEventEntry entry = resultFeed.getEntries().get(i);
                    String title = entry.getTitle().getPlainText();//日曆事件
                    ListWhat.add(title);
                    Content content = entry.getContent();

                    TextContent tc = (TextContent) content;
                    String Description = tc.getContent().getPlainText();
                    ListDescription.add(Description+",");
//=============================================================================
                    Where ee = entry.getLocations().get(0);
                    String Locations = ee.getValueString();//地點
                    ListWhere.add(Locations+",");
//=============================================================================
                    When t = entry.getTimes().get(0);

                    DateTime start = t.getStartTime();

                    String s = start.toUiString();
                    ListWhenStart.add(s);
                    DateTime end = t.getEndTime();
                    String e = end.toUiString();

                    ListWhenEnd.add(e);


//                    System.out.println();
//                    System.out.println("開始 " + s);
//                    System.out.println("事件 " + num + " " + title);
//                    System.out.println("地點 " + num + " " + Locations);//地點
//                    System.out.println("說明 " + num + " " + Description);
//                    System.out.println("結束 " + e);
//                    System.out.println("花費時間 " + dd + "小時");
//                    System.out.println("~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");

                }//for
            }//if
            ListWhat.add("0");
            ListWhere.add("0");
            ListDescription.add("0");
            ListWhenStart.add("0");
            ListWhenEnd.add("0");
        } catch (IOException ex) {
            Logger.getLogger(QueryDay.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServiceException ex) {
            Logger.getLogger(QueryDay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//run

    public List<String> getWhat() {
        return ListWhat;
    }

    public List<String> getWhere() {
        return ListWhere;
    }

    public List<String> getWhenStart() {
        return ListWhenStart;
    }

    public List<String> getWhenEnd() {
        return ListWhenEnd;
    }

    public List<String> getDescription() {
        return ListDescription;
    }
}
