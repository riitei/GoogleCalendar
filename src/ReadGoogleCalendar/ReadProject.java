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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author 李偉挺
 */
public class ReadProject {

    private String StartYear = "2010";
    private String StartMonths = "07";
    private String StartDay = "19";
    private String EndYear = "2010";
    private String EndMonths = "07";
    private String EndDay = "20";
    private String strProjectEndYear;
    private String strProjectEndMonth;
    private String strProjectEndDay;
    private CalendarEventFeed resultFeed03;
    private String String_ProjectStartDate;
    private long long_ProjectStartDate;
    private List<String> listProjectEvent;
    private List<String> ListProjectManager;//回傳每一個專案的專案經理
    private List<String> ListProjectMember;//
    private List<String> ListProjectName;//回傳每一個專案名稱
    private List<String> ListStringProjectCreateDate;//回傳每一個專案建立日期
    private List<String> ListLongProjectCreateDate;
    private String arrayProjectEvent[][];//回傳每一個專案工作事件
    private List<String> ListStringProjectDeadline;//回傳每一個專案截止日期
    private List<String> ListLongProjectDeadline;
    private String arrayProjectMember[][];//回傳每一個專案成員名單

    /**
     *
     * @param myService
     * @param ID
     * @param ProjectName
     * @param ProjectEndYear
     * @param ProjectEndMonth
     * @param ProjectEndDay
     * @throws ParseException
     */
    public void setReadProject(CalendarService myService, String ID, String ProjectName, int ProjectEndYear, int ProjectEndMonth, int ProjectEndDay) throws ParseException {
        try {

            URL feedUrl = new URL("http://www.google.com/calendar/feeds/default/private/full");
            //=============================================================================
            CalendarQuery myQuery01 = new CalendarQuery(feedUrl);
            myQuery01.setMinimumStartTime(DateTime.parseDateTime(StartYear + "-" + StartMonths + "-" + StartDay + "T00:00:00"));
            myQuery01.setMaximumStartTime(DateTime.parseDateTime(EndYear + "-" + EndMonths + "-" + EndDay + "T23:59:59"));
            myQuery01.setFullTextQuery(ID + " " + ProjectName);
            //System.out.println("myQuery01:" + ID);//搜尋
            //            int num = resultFeed.getTotalResults();
            //System.out.println("num:" + num);
            //System.out.println("-----------");

            int intProjectEndYear = ProjectEndYear;
            int intProjectEndMonth = ProjectEndMonth;
            int intProjectEndDay = ProjectEndDay;


            if (intProjectEndYear == 0) {
                intProjectEndYear = 0;
            } else if (intProjectEndYear < 10) {
                strProjectEndYear = "000" + String.valueOf(intProjectEndYear);
                //System.out.println("strProjectEndYear " + strProjectEndYear);
            } else if (intProjectEndYear < 100) {
                strProjectEndYear = "00" + String.valueOf(intProjectEndYear);
                //System.out.println("strProjectEndYear " + strProjectEndYear);
            } else if (intProjectEndYear < 1000) {
                strProjectEndYear = "0" + String.valueOf(intProjectEndYear);
                //System.out.println("strProjectEndYear " + strProjectEndYear);
            } else {
                strProjectEndYear = String.valueOf(intProjectEndYear);
                //System.out.println("strProjectEndYear " + strProjectEndYear);
            }

            if (intProjectEndMonth == 0) {
                intProjectEndMonth = 0;
            } else if (intProjectEndMonth < 10) {
                strProjectEndMonth = "0" + String.valueOf(intProjectEndMonth);
                // System.out.println("strProjectEndMonth " + strProjectEndMonth);
            } else {
                strProjectEndMonth = String.valueOf(intProjectEndMonth);
                // System.out.println("strProjectEndMonth " + strProjectEndMonth);
            }

            if (intProjectEndDay == 0) {
                intProjectEndDay = 0;
            } else if (intProjectEndDay < 10) {
                strProjectEndDay = "0" + String.valueOf(intProjectEndDay);
                //System.out.println("strProjectEndDay " + strProjectEndDay);
            } else {
                strProjectEndDay = String.valueOf(intProjectEndDay);
                //System.out.println("strProjectEndDay " + strProjectEndDay);
            }


            if (intProjectEndYear == 0 || intProjectEndMonth == 0 || intProjectEndDay == 0) {
                //System.out.println("resultFeed03-1: " + intProjectEndYear + " " + intProjectEndMonth + " " + intProjectEndDay);
                resultFeed03 = myService.query(myQuery01, CalendarEventFeed.class);
            } else {
                //System.out.println("resultFeed03-2: " + intProjectEndYear + " " + intProjectEndMonth + " " + intProjectEndDay);
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(strProjectEndYear + "-" + strProjectEndMonth + "-" + strProjectEndDay);
                String EndDate = String.valueOf(date.getTime());
                //System.out.println("EndDate: " + EndDate);
                CalendarQuery myQuery03 = new CalendarQuery(feedUrl);
                myQuery03.setMinimumStartTime(DateTime.parseDateTime(StartYear + "-" + StartMonths + "-" + StartDay + "T00:00:00"));
                myQuery03.setMaximumStartTime(DateTime.parseDateTime(EndYear + "-" + EndMonths + "-" + EndDay + "T23:59:59"));
                myQuery03.setFullTextQuery(EndDate);
                resultFeed03 = myService.query(myQuery03, CalendarEventFeed.class);
            }
            //System.out.println("Total: " + resultFeed03.getTotalResults());
            //System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            int rf = resultFeed03.getTotalResults();

            ListProjectManager = new LinkedList<String>();//回傳每一個專案的專案經理
            ListProjectName = new LinkedList<String>();//回傳每一個專案名稱
            listProjectEvent = new LinkedList<String>();
            ListProjectMember = new LinkedList<String>();
            ListStringProjectCreateDate = new LinkedList<String>();//回傳每一個專案建立日期
            ListLongProjectCreateDate = new LinkedList<String>();
            arrayProjectEvent = new String[rf][];//每一個專案工作事件
            ListStringProjectDeadline = new LinkedList<String>();//回傳每一個專案截止日期
            ListLongProjectDeadline = new LinkedList<String>();
            arrayProjectMember = new String[rf][];//每一個專案成員名單
///////////////////////////////////////////////////////////////////////////////////////////////////            
            for (int i = 0; i < resultFeed03.getEntries().size(); i++) {
                //============================================================================

                CalendarEventEntry entry = resultFeed03.getEntries().get(i);

                String what = entry.getTitle().getPlainText(); //日曆事件
                String arrayWhat[] = what.split("; ");

                //=============================================================================
                Where Location = entry.getLocations().get(0);
                String where = Location.getValueString(); //地點
                String arrayWhere[] = where.split("; ");
                //0:專案工作事件；1:專案截止日期
                //取出專案工作事件=============
                String event[] = arrayWhere[0].split(", ");

                for (int ii = 1; ii < (event.length - 1); ii++) {
                    System.out.println("ii " + ii + " =" + event[ii]);
                    listProjectEvent.add(event[ii]);
                    //listProjectEvent.add(tempEvent[ii]);
                }
                Content content = entry.getContent();
                TextContent tc = (TextContent) content;
                String Description = tc.getContent().getPlainText();
                String tempDescription[] = Description.split(", ");
                for (int numDescription = 1; numDescription < (tempDescription.length - 1); numDescription++) {
                    ListProjectMember.add(tempDescription[numDescription]);
                }
                //=============================================================================
                When t = entry.getTimes().get(0);
                DateTime start = t.getStartTime();
                String_ProjectStartDate = start.toUiString();
                long_ProjectStartDate = start.getValue();
                String long_to_string_ProjectStartDate = String.valueOf(long_ProjectStartDate);
                DateTime end = t.getEndTime();
                String e = end.toUiString();
                long ee = end.getValue();
                //==============================================================================
                ListProjectManager.add(arrayWhat[0]);//listWhat 第一筆 專案經理帳號
                ListProjectName.add(arrayWhat[1]);//listWhat 第二筆 專案名稱
                ListStringProjectCreateDate.add(String_ProjectStartDate);//第三筆 專案日期
                ListLongProjectCreateDate.add(long_to_string_ProjectStartDate);
                //專案工作事件
                System.out.println("* " + i + " *listProjectEvent: " + listProjectEvent.size());
                arrayProjectEvent[i] = new String[listProjectEvent.size()];
                int numlpe = 0;
                while (numlpe < listProjectEvent.size()) {
                    arrayProjectEvent[i][numlpe] = listProjectEvent.get(numlpe);
                    System.out.println("**listProjectEvent Array: " + i + " 筆中 " + numlpe + " 筆 ");
                    System.out.println(arrayProjectEvent[i][numlpe]);
                    numlpe = numlpe + 1;
                }
                listProjectEvent.clear();
                //專案截止日
                String ProjectDeadline = arrayWhere[1];
                Calendar now = Calendar.getInstance();
                now.setTimeInMillis(Long.parseLong(ProjectDeadline));
                int y = now.get(Calendar.YEAR);
                int m = now.get(Calendar.MONTH) + 1;
                int d = now.get(Calendar.DATE);//存放過去日子
                int hh = now.get(Calendar.HOUR_OF_DAY);
                int mm = now.get(Calendar.MINUTE);
                ProjectDeadline = (y + "/" + m + "/" + d + "-" + hh + ":" + mm);
                ListStringProjectDeadline.add(ProjectDeadline);
                ListLongProjectDeadline.add(arrayWhere[1]);
                //專案成員名單
                System.out.println("*" + i + "listDescription:" + ListProjectMember);
                arrayProjectMember[i] = new String[ListProjectMember.size()];
                System.out.println("");
                int numlpm = 0;
                while (numlpm < ListProjectMember.size()) {
                    arrayProjectMember[i][numlpm] = ListProjectMember.get(numlpm);
                    System.out.println("**lpe Array: " + i + " 筆中 " + numlpm + " 筆 ");
                    System.out.println(arrayProjectMember[i][numlpm]);
                    numlpm = numlpm + 1;
                }
                ListProjectMember.clear();
                //==============================================================================
                /*
                System.out.println("開始 " + String_ProjectStartDate + " long:" + long_ProjectStartDate);

                System.out.println("事件 " + i);
                System.out.println("    專案經理帳號 " + listWhat.get(0));
                System.out.println("    專案名稱 " + listWhat.get(1));
                System.out.println("    專案建立日期 " + listWhat.get(2));
                System.out.println("地點 ");//地點
                System.out.println("    專案工作事件 " + listProjectEvent);
                System.out.println("    專案截止日期 " + listWhere.get(1));
                System.out.println("說明 ");
                System.out.println("    專案成員名單 " + listDescription);
                System.out.println("結束 " + e + " long:" + ee);
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
                 */
                //==============================================================================

            }//for

            System.out.println("專案查詢成功");
        } catch (IOException ex) {
            Logger.getLogger(ReadProject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServiceException ex) {
            Logger.getLogger(ReadProject.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//setAccount

    /**
     *
     * @return
     */
    public List<String> getProjectManager() {//專案經理
        return ListProjectManager;
    }

    /**
     *
     * @return
     */
    public List<String> getProjectName() {
        return ListProjectName;
    }

    /**
     *
     * @return
     */
    public List<String> getStringProjectCreateDate() {
        return ListStringProjectCreateDate;
    }

    /**
     *
     * @return
     */
    public List<String> getLongProjectCreateDate() {
        return ListLongProjectCreateDate;
    }

    /**
     *
     * @return
     */
    public String[][] getArrayProjectEvent() { //專案工作事件
        return arrayProjectEvent;
    }

    /**
     *
     * @return
     */
    public List<String> getStringProjectDeadline() {//專案截止日期
        return ListStringProjectDeadline;
    }

    /**
     *
     * @return
     */
    public List<String> getLongProjectDeadline() {
        return ListLongProjectDeadline;
    }

    /**
     *
     * @return
     */
    public String[][] getArrayProjectMember() {//專案成員名單
        return arrayProjectMember;
    }
}//listWhere


