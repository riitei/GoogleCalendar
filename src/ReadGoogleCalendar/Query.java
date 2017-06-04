package ReadGoogleCalendar;

import com.google.gdata.client.calendar.CalendarQuery;
import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.DateTime;
import com.google.gdata.data.calendar.CalendarEventFeed;
import com.google.gdata.util.ServiceException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Query {

    private String[][] aa00;
    private String strcm021;
    private String strcd021;
    private String strMiddleTime02;
    private String aa02[][];
    private int cy021 = 0;
    private int cm021 = 0;
    private int cd021 = 0;
    private int nii = 0;
    private int arraylength01 = 0;
    private int a2;
    private List<String> StartDate;
    private List<String> EndDate;
    private List<String> ListWhat;
    private List<String> ListWhere;
    private List<String> ListDescription;
    private List<String> ListWhenStart;
    private List<String> ListWhenEnd;
    private String temp;
    private String strQuery;
    private int num;

    public void setQuery(String ID, String PW, String ProjectName, String StartTime, String EndTime) {
        try {

            StartDate = new LinkedList<>();
            EndDate = new LinkedList<>();
            ListWhat = new LinkedList<>();
            ListWhere = new LinkedList<>();
            ListDescription = new LinkedList<>();
            ListWhenStart = new LinkedList<>();
            ListWhenEnd = new LinkedList<>();
            ThreadQueue tq = new ThreadQueue();
            tq.ThreadQueue();
            CalendarService myService = new CalendarService("exampleCo-exampleApp-1");
            myService.setUserCredentials(ID, PW);
            URL feedUrl = new URL("http://www.google.com/calendar/feeds/default/private/full");
            CalendarQuery myQuery = new CalendarQuery(feedUrl);
            myQuery.setMinimumStartTime(DateTime.parseDateTime(StartTime + "T00:00:00"));
            myQuery.setMaximumStartTime(DateTime.parseDateTime(EndTime + "T23:59:59"));

            String strNull = new String();

            //     System.out.println("================================");
            if (ProjectName.equals(strNull) == false && ID.equals(strNull) == true) {
                strQuery = ProjectName;
                myQuery.setFullTextQuery(strQuery);
            } else if (ID.equals(strNull) == false && ProjectName.equals(strNull) == true) {
                strQuery = ID + ";";
                myQuery.setFullTextQuery(strQuery);
            } else if (ID.equals(strNull) == true && ProjectName.equals(strNull) == true) {
                strQuery = ID + "; " + ProjectName + ";";
                myQuery.setFullTextQuery(strQuery);
            }
            CalendarEventFeed resultFeed = myService.query(myQuery, CalendarEventFeed.class);
            num = resultFeed.getTotalResults();

//            System.out.println("Total " + num);
//            System.out.println("");
            aa00 = new String[1][];
            aa00[0] = new String[2];
            aa00[0][0] = StartTime;
            aa00[0][1] = EndTime;

            System.out.println("aa00[0][0] = " + StartTime);
            System.out.println("aa00[0][1] = " + EndTime);
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            int num21 = 0;

            if (num > 25) {//大於25
                while (true) {

                    a2 = aa00.length * 2;
                    System.out.println("While");
//                    System.out.println("aa00.length " + aa00.length);
//                    System.out.println("aa00.length a2 " + a2);
                    aa02 = new String[a2][];
                    int num25_02 = 0;
//                    System.out.println("getArray02~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    for (int ii = 0; ii < aa00.length; ii++) {
//                        System.out.println("開始日期_aa01[" + ii + "][0]= " + aa00[ii][0]);
//                        System.out.println("結束日期_aa01[" + ii + "][0]= " + aa00[ii][1]);

                        System.out.println("for (int ii = 0; ii < aa00.length; ii++)");
                        if (aa00[ii][0].endsWith("null") == true) {

                            nii = 2 * ii;
                            aa02[nii] = new String[2];
                            aa02[nii][0] = "null";
                            aa02[nii][1] = "null";
//                            System.out.println("null_01_aa02[" + nii + "][0]=" + aa02[nii][0]);
//                            System.out.println("null_02_aa02[" + nii + "][1]=" + aa02[nii][1]);
//                            System.out.println("瘝� getArray02 nii_01 " + nii + " " + aa02[nii][0] + " " + aa02[nii][1]);
                            nii = nii + 1;
                            aa02[nii] = new String[2];
                            aa02[nii][0] = "null";
                            aa02[nii][1] = "null";
//                            System.out.println("");
//                            System.out.println("null_01_aa02[" + nii + "][0]=" + aa02[nii][0]);
//                            System.out.println("null_02_aa02[" + nii + "][1]=" + aa02[nii][1]);
//                            System.out.println("---------------------------------------");
//                            System.out.println("瘝� getArray02 nii_02 " + nii + " " + aa02[nii][0] + " " + aa02[nii][1]);
                        } else {

                            // if (aa01[ii][0].endsWith("null") == true) false
                            myQuery.setMinimumStartTime(DateTime.parseDateTime(aa00[ii][0] + "T00:00:00"));
                            myQuery.setMaximumStartTime(DateTime.parseDateTime(aa00[ii][1] + "T23:59:59"));
                            myQuery.setFullTextQuery(strQuery);
                            CalendarEventFeed resultFeed021 = myService.query(myQuery, CalendarEventFeed.class);
                            num21 = resultFeed021.getTotalResults();
//                            System.out.println("~~~~~~~~~~~~~~蝑~~~~~~~~~~~~~~~~~~~~");
//                            System.out.println("for " + ii);
//                            System.out.println("aa00[" + ii + "][0]=" + aa00[ii][0]);
//                            System.out.println("aa00[" + ii + "][1]=" + aa00[ii][1]);
//                            System.out.println(" num21 " + num21);
//                            System.out.println("~~~~~~~~~~~~~~蝑~~~~~~~~~~~~~~~~~~~~");
//                            System.out.println("for " + ii);
                            int n = 0;
                            if (num21 > 25) {
                                System.out.println("");
                                System.out.println("超過25筆");
                                System.out.println("切割時間");
                                System.out.println("");
                                num25_02 = num25_02 + 1;
                                Calendar c02 = Calendar.getInstance();
                                SimpleDateFormat formatter02 = new SimpleDateFormat("yyyy" + "-" + "MM" + "-" + "dd");
                                Date ST02 = formatter02.parse(aa00[ii][0]);
                                Date ET02 = formatter02.parse(aa00[ii][1]);
                                long MiddleTime = (ST02.getTime() + ET02.getTime()) / 2;
                                c02.setTimeInMillis(MiddleTime);
                                while (n < 2) {
                                    cy021 = c02.get(Calendar.YEAR);
                                    cm021 = c02.get(Calendar.MONTH) + 1;
                                    cd021 = c02.get(Calendar.DATE);
                                    if (cm021 < 10) {
                                        strcm021 = "0" + String.valueOf(cm021);
                                    } else {
                                        strcm021 = String.valueOf(cm021);
                                    }
                                    if (cd021 < 10) {
                                        strcd021 = "0" + String.valueOf(cd021);
                                    } else {
                                        strcd021 = String.valueOf(cd021);
                                    }
                                    if (n == 0) {
                                        nii = ii * 2;
                                        aa02[nii] = new String[2];
                                        strMiddleTime02 = cy021 + "-" + strcm021 + "-" + strcd021;
                                        aa02[nii][0] = aa00[ii][0];
                                        aa02[nii][1] = strMiddleTime02;
                                        System.out.println("for " + ii);
                                        System.out.println("getArray02_1 超過 25_01 aa02[" + nii + "][0] " + aa02[nii][0]);
                                        System.out.println("getArray02_1 超過 25_02 aa02[" + nii + "][1] " + aa02[nii][1]);
                                        c02.add(Calendar.DAY_OF_YEAR, 1);
                                    } else {
                                        nii = ii * 2 + 1;
                                        aa02[nii] = new String[2];
                                        //System.out.println("nii " + nii);
                                        strMiddleTime02 = cy021 + "-" + strcm021 + "-" + strcd021;
                                        aa02[nii][0] = strMiddleTime02;
                                        aa02[nii][1] = aa00[ii][1];
                                        System.out.println("");
                                        System.out.println("getArray02_2 超過 25_01 aa02[" + nii + "][0] " + aa02[nii][0]);
                                        System.out.println("getArray02_2 超過 25_02 aa02[" + nii + "][1] " + aa02[nii][1]);
                                    } //if (n == 0)
                                    //if (n == 0)
                                    n = n + 1;
                                } // while (n < 2)
                                System.out.println("----------------------------------------------------");
                                // while (n < 2)
                            } else {
                                // if (num21 < 24)

                                nii = ii * 2;
                                aa02[nii] = new String[2];
                                aa02[nii][0] = aa00[ii][0];
                                aa02[nii][1] = aa00[ii][1];
                                StartDate.add(aa02[nii][0]);
                                EndDate.add(aa02[nii][1]);
                                //                             System.out.println("arraylength01 " + arraylength01);
                                //                              System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                                System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                                System.out.println("");

                                if (num21 == 0) {
                                    System.out.println("筆數0不執行");
                                    System.out.println("筆數0不執行　25_01 aa02[" + nii + "][0] " + aa02[nii][0]);
                                    System.out.println("筆數0不執行　25_01 aa02[" + nii + "][1] " + aa02[nii][1]);
                                    System.out.println("");

                                    //                                   System.out.println("瘝���);
                                } else {
                                    //                                   System.out.println("No_Null");
                                    arraylength01 = arraylength01 + 1;
//                                    System.out.println("----------------撠25-------------");
//                                    System.out.println("�瑁�蝝航�蝮賣 " + arraylength01);
                                    System.out.println("----------------小於25-------------");
                                    System.out.println("getArray02 開始日期 aa02[" + nii + "][0] " + aa02[nii][0]);
                                    System.out.println("getArray02 結束日期 aa02[" + nii + "][1] " + aa02[nii][1]);
                                    System.out.println("筆數= " + num21);
                                    System.out.println("----------------小於25-------------");
                                    System.out.println("");
                                    temp = ID + ", " + PW + ", " + aa02[nii][0] + ", " + aa02[nii][1] + ", " + ProjectName + ", " + arraylength01;

                                    System.out.println("temp: " + temp);
                                    tq.setThreadQueue(temp);
                                    System.out.println("");
                                    System.out.println("讀取後設定 null ");
                                    aa02[nii][0] = "null";
                                    aa02[nii][1] = "null";

                                }
//                                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//                                System.out.println("arraylength01 " + arraylength01);
//                                System.out.println("  nii " + nii);
                                System.out.println("讀取後設定 null ");

                                nii = nii + 1;

                                aa02[nii] = new String[2];
                                aa02[nii][0] = "null";
                                aa02[nii][1] = "null";

//                                System.out.println("  nii " + nii);
//                                System.out.println("�恍 " + arraylength01);
//                                System.out.println("getArray02 撠25_02 aa02[" + nii + "][0] " + aa02[nii][0]);
//                                System.out.println("getArray02 撠25_02 aa02[" + nii + "][1] " + aa02[nii][1]);
                            } // if (num21 > 24)

                        } //null

//                        System.out.println("");
//                        System.out.println("iiiiiiiiiiiiiii  " + ii);
                    } //

//                    System.out.println("getArray02 ");
//                    System.out.println("aa02.length " + aa02.length);
//                    System.out.println("");
//                    for (int zz = 0; zz < aa02.length; zz++) {
//                        System.out.println("列印全部");
//                        System.out.println("[" + zz + "][0]= " + aa02[zz][0]);
//                        System.out.println("[" + zz + "][1]= " + aa02[zz][1]);
//                    }
                    System.out.println("+-+-+-+-+-+-+-+-+-+-");
                    if (num25_02 > 0) {
//                        System.out.println("~~~~~~~~new~~~~~~~~~");
//                        System.out.println("num25_02 " + num25_02);
                        aa00 = aa02;//呼叫自己

                    }
                    if (num25_02 == 0) {

                        break;
                    }
//                    System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
                } //while

            } else {//小於25

                StartDate.add(aa00[0][0]);
                EndDate.add(aa00[0][1]);
                System.out.println("");
                System.out.println("*******************************************");
                System.out.println("小於等於25筆");
                System.out.println("StartDate" + aa00[0][0]);
                System.out.println("EndDate  " + aa00[0][1]);
                System.out.println("num: " + num);
                System.out.println("*******************************************");
                System.out.println("");
                temp = ID + ", " + PW + ", " + aa00[0][0] + ", " + aa00[0][1] + ", " + ProjectName + ", " + "1";
                tq.setThreadQueue(temp);
            }

//
//            System.out.println("arraylength01 " + arraylength01);
//
//            System.out.println("##############################################");
//            int getWhenStartsize01 = tq.getWhenStart01().size();//12
//            System.out.println("getWhat01size " + getWhenStartsize01);
//            System.out.println("##############################################");
//            int getWhenStartsize02 = tq.getWhenStart02().size();//13
//            System.out.println("getWhat02size " + getWhenStartsize02);
//            System.out.println("##############################################");
//            int getWhenStartsize03 = tq.getWhenStart03().size();//13
//            System.out.println("getWhat03size " + getWhenStartsize03);
//            System.out.println("##############################################");
//            int getWhenStartsize04 = tq.getWhenStart04().size();//13
//            System.out.println("getWhat04size " + getWhenStartsize04);
//            System.out.println("##############################################");
//            int getWhenStartsize05 = tq.getWhenStart05().size();//13
//            System.out.println("getWhat05size " + getWhenStartsize05);
//            System.out.println("##############################################");
//            int getWhenStartsize06 = tq.getWhenStart06().size();//13
//            System.out.println("getWhat06size " + getWhenStartsize06);
//            System.out.println("##############################################");
            for (int i01 = 0; i01 < tq.getWhenStart01().size(); i01++) {
                //  ListWhenStart.add(" (1) (" + i01 + ")=" + tq.getWhenStart01().get(i01));// 1, 7
                ListWhat.add(tq.getWhat01().get(i01));
                ListWhere.add(tq.getWhere01().get(i01));
                ListDescription.add(tq.getDescription01().get(i01));
                ListWhenStart.add(tq.getWhenStart01().get(i01));// 1, 7
                ListWhenEnd.add(tq.getWhenEnd01().get(i01));

                if (i01 < tq.getWhenStart02().size() || i01 == (tq.getWhenStart02().size()) - 1) {
                    //   ListWhenStart.add(" (2) (" + i01 + ")=" + tq.getWhenStart02().get(i01));// 2
                    ListWhat.add(tq.getWhat02().get(i01));
                    ListWhere.add(tq.getWhere02().get(i01));
                    ListDescription.add(tq.getDescription02().get(i01));
                    ListWhenStart.add(tq.getWhenStart02().get(i01));// 2
                    ListWhenEnd.add(tq.getWhenEnd02().get(i01));
                }

                if (i01 < tq.getWhenStart03().size() || i01 == (tq.getWhenStart03().size() - 1)) {
                    //    ListWhenStart.add(" (3) (" + i01 + ")=" + tq.getWhenStart03().get(i01));// 3
                    ListWhat.add(tq.getWhat03().get(i01));
                    ListWhere.add(tq.getWhere03().get(i01));
                    ListDescription.add(tq.getDescription03().get(i01));
                    ListWhenStart.add(tq.getWhenStart03().get(i01));// 3
                    ListWhenEnd.add(tq.getWhenEnd03().get(i01));
                }

                if (i01 < tq.getWhenStart04().size() || i01 == (tq.getWhenStart04().size() - 1)) {
                    //  ListWhenStart.add(" (4) (" + i01 + ")=" + tq.getWhenStart04().get(i01));// 4
                    ListWhat.add(tq.getWhat04().get(i01));
                    ListWhere.add(tq.getWhere04().get(i01));
                    ListDescription.add(tq.getDescription04().get(i01));
                    ListWhenStart.add(tq.getWhenStart04().get(i01));// 4
                    ListWhenEnd.add(tq.getWhenEnd04().get(i01));
                }

                if (i01 < tq.getWhenStart05().size() || i01 == (tq.getWhenStart05().size() - 1)) {
                    //   ListWhenStart.add(" (5) (" + i01 + ")=" + tq.getWhenStart05().get(i01));// 5
                    ListWhat.add(tq.getWhat05().get(i01));
                    ListWhere.add(tq.getWhere05().get(i01));
                    ListDescription.add(tq.getDescription05().get(i01));
                    ListWhenStart.add(tq.getWhenStart05().get(i01));// 5
                    ListWhenEnd.add(tq.getWhenEnd05().get(i01));
                }

                if (i01 < tq.getWhenStart06().size() || i01 == (tq.getWhenStart06().size() - 1)) {
                    //ListWhenStart.add(" (6) (" + i01 + ")=" + tq.getWhenStart06().get(i01));// 6
                    ListWhat.add(tq.getWhat06().get(i01));
                    ListWhere.add(tq.getWhere06().get(i01));
                    ListDescription.add(tq.getDescription06().get(i01));
                    ListWhenStart.add(tq.getWhenStart06().get(i01));// 6
                    ListWhenEnd.add(tq.getWhenEnd06().get(i01));
                }
            }

        } catch (ParseException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServiceException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int TotalNum() {
        return num;
    }

    public List<String> getWhat() {
        return ListWhat;
    }

    public List<String> getWhere() {
        return ListWhere;
    }

    public List<String> getDescription() {
        return ListDescription;
    }

    public List<String> getWhenStart() {
        return ListWhenStart;
    }

    public List<String> getWhenEnd() {
        return ListWhenEnd;
    }
}
