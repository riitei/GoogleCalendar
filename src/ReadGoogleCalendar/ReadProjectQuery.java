/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ReadGoogleCalendar;

import java.text.ParseException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author 李偉挺
 */
public class ReadProjectQuery {

    private String StartYear = "2010";
    private String StartMonths = "07";
    private String StartDay = "20";
    private String EndYear = "2010";
    private String EndMonths = "07";
    private String EndDay = "22";
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
    private String strListWhat;
    private String arrayWhat[];
    private String strListWhere;
    private String arrayWhere[];
    private String strListWhenStart;
    private String arrayWhenStart[];
    private String strListDescription;
    private String arrayDescription[];
    private String ListWhenEnd;
    private String arrayWhenEnd[];
    private int numTotal;
    private int numTotalArray = 0;

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
    public void setReadProject(String ID, String PW, String ProjectName) {



        String StartTime = StartYear + "-" + StartMonths + "-" + StartDay;
        String EndTime = EndYear + "-" + EndMonths + "-" + EndDay;
        Query q = new Query();
       // System.out.println("");

        q.setQuery(ID, PW, ProjectName, StartTime, EndTime);
        numTotal = q.TotalNum();
        System.out.println("@@@@@@@@@@@");
        System.out.println("總筆數: " + numTotal);
        System.out.println("@@@@@@@@@@@");
//~"~"~"~~"~"~"~"~"~"~"~"~"~"~"~"~"~"~"~"~~"~"~"~"~"~"~"~"~"~"~"~"~"~"~"~"~~"~"~"~"~"~"~"~"~"~"~"~
       /*     System.out.println("ListWhat  " + q.getWhat());
        System.out.println("ListWhat size " + q.getWhat().size());

        System.out.println("");
        System.out.println("ListWhere " + q.getWhere());
        System.out.println("ListWhere size " + q.getWhere().size());
        System.out.println("");
        
        System.out.println("ListWhenStart " + q.getWhenStart());
        System.out.println("ListWhenStart size " + q.getWhenStart().size());
        System.out.println("");
         */

   //     System.out.println("ListDescription " + q.getDescription());
   //     System.out.println("ListDescription size " + q.getDescription().size());
   //     System.out.println("");
//''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
//        int rf = resultFeed03.getTotalResults();

        ListProjectManager = new LinkedList<String>();//回傳每一個專案的專案經理
        ListProjectName = new LinkedList<String>();//回傳每一個專案名稱
        listProjectEvent = new LinkedList<String>();
        ListProjectMember = new LinkedList<String>();
        ListStringProjectCreateDate = new LinkedList<String>();//回傳每一個專案建立日期
        ListLongProjectCreateDate = new LinkedList<String>();
        arrayProjectEvent = new String[numTotal][];//每一個專案工作事件
        ListStringProjectDeadline = new LinkedList<String>();//回傳每一個專案截止日期
        ListLongProjectDeadline = new LinkedList<String>();
        arrayProjectMember = new String[numTotal][];//每一個專案成員名單
//''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''


        for (int i = 0; i < q.getWhenStart().size(); i++) {//群組取出
            //   System.out.println("ListWhenStartSize " + q.getWhenStart().size());
            //_What_++++++++++++++++++++++++++++++++++++++++++++++++++++++
            strListWhat = q.getWhat().get(i);
            arrayWhat = strListWhat.split(", ");
            // System.out.println("");
            // System.out.println("ii < " + i + " >");
            //System.out.println("");
            //where
            strListWhere = q.getWhere().get(i);
            //System.out.println("");
            //System.out.println("getWhere { " + i + " }= " + strListWhere);
            arrayWhere = strListWhere.split(",, ");
            //Description
            strListDescription = q.getDescription().get(i);
        //    System.out.println("");
        //    System.out.println("getDescription { " + i + " }= " + strListDescription);

            arrayDescription = strListDescription.split(",, ");
       //     System.out.println("");
          //  System.out.println("getDescription sieze= " + arrayDescription.length);
         //   System.out.println("");
            for (int ans01 = 1; ans01 < (arrayWhat.length - 1); ans01++) {//一筆一筆取出
                int show = numTotalArray + 1;
                // System.out.println("");
           //     System.out.println("資料 < " + show + " > 筆數");

                //what++++++++++++++++++++++++++++++++++++++++++++++++++++++

           //     System.out.println(" for ans01 {{" + ans01 + "}}= " + arrayWhat[ans01]);
                String a1[] = arrayWhat[ans01].split("; ");
                //System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                ListProjectManager.add(a1[0]);//listWhat 第一筆 專案經理帳號
                ListProjectName.add(a1[1]);//listWhat 第二筆 專案名稱
                ListLongProjectCreateDate.add(a1[2]);//第三筆 專案建立日期 long

                Calendar CreateDate = Calendar.getInstance();
                CreateDate.setTimeInMillis(Long.parseLong(a1[2]));
                int CreateDateY = CreateDate.get(Calendar.YEAR);
                int CreateDateM = CreateDate.get(Calendar.MONTH) + 1;
                int CreateDateD = CreateDate.get(Calendar.DATE);//存放過去日子
                int CreateDateHH = CreateDate.get(Calendar.HOUR_OF_DAY);
                int CreateDateMM = CreateDate.get(Calendar.MINUTE);
                String ProjectCreate = (CreateDateY + "/" + CreateDateM + "/" + CreateDateD + "-" + CreateDateHH + ":" + CreateDateMM);
                ListStringProjectCreateDate.add(ProjectCreate);





                //_Where_++++++++++++++++++++++++++++++++++++++++++++++++++++++
                //0:專案工作事件；1:專案截止日期
                //取出專案工作事件=============
            //    System.out.println("");
                String a2[] = arrayWhere[ans01].split("; ");
               // System.out.println("專案事件: " + a2[0]);
                String a21[] = a2[0].split(", ");
                for (int ans021 = 1; (ans021 < a21.length - 1); ans021++) {
          //          System.out.println("專案事件 " + ans021 + " = " + a21[ans021]);
                    listProjectEvent.add(a21[ans021]);//專案工作事件
                }
             //   System.out.println("截止日期: " + a2[1]);
                ListLongProjectDeadline.add(a2[1]);//專案截止日期
                //_0_專案工作事件
          //      System.out.println("");
         //       System.out.println("Array Size: " + numTotalArray + " project evnet: " + listProjectEvent.size());
         //       System.out.println("");
                arrayProjectEvent[numTotalArray] = new String[listProjectEvent.size()];
                int numlpe = 0;
                while (numlpe < listProjectEvent.size()) {
                    arrayProjectEvent[numTotalArray][numlpe] = listProjectEvent.get(numlpe);
   //                 System.out.println("**listProjectEvent Array: " + numTotalArray + " 筆中 " + numlpe + " 筆 ");
   //                 System.out.println(arrayProjectEvent[numTotalArray][numlpe]);
                    numlpe = numlpe + 1;
                }
                //_1_專案截止日
                String ProjectDeadline = a2[1];
                Calendar now = Calendar.getInstance();
                now.setTimeInMillis(Long.parseLong(ProjectDeadline));
                int y = now.get(Calendar.YEAR);
                int m = now.get(Calendar.MONTH) + 1;
                int d = now.get(Calendar.DATE);//存放過去日子
                int hh = now.get(Calendar.HOUR_OF_DAY);
                int mm = now.get(Calendar.MINUTE);
                ProjectDeadline = (y + "/" + m + "/" + d + "-" + hh + ":" + mm);
                ListStringProjectDeadline.add(ProjectDeadline);


           //     System.out.println("arrayDescription= " + arrayDescription[ans01]);

                String a30[] = arrayDescription[ans01].split(", ");
                for (int ans30 = 1; ans30 < (a30.length - 1); ans30++) {
          //          System.out.println("ans30 [" + ans30 + "]= " + a30[ans30]);
                    ListProjectMember.add(a30[ans30]);
                }

           //     System.out.println("*" + numTotalArray + "listDescription:" + ListProjectMember);
                arrayProjectMember[numTotalArray] = new String[ListProjectMember.size()];
         //       System.out.println("");
                int numlpm = 0;
                while (numlpm < ListProjectMember.size()) {
                    arrayProjectMember[numTotalArray][numlpm] = ListProjectMember.get(numlpm);
     //               System.out.println("**lpe Array: " + numTotalArray + " 筆中 " + numlpm + " 筆 ");
    //                System.out.println(arrayProjectMember[numTotalArray][numlpm]);
                    numlpm = numlpm + 1;
                }

        //        System.out.println("");
        //        System.out.println("下一筆");
         //       System.out.println("＄＄＄＄＄＄＄＄＄＄＄＄＄＄＄＄＄＄＄＄＄＄＄");
        //        System.out.println("");
                listProjectEvent.clear();
                ListProjectMember.clear();
                numTotalArray = numTotalArray + 1;


            }//for 一筆一筆取出
        //    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

            //_When_Start_++++++++++++++++++++++++++++++++++++++++++++++++++++++建立專案時間 往後推100年
            /*
            strListWhenStart = q.getWhenStart().get(i);
            arrayWhenStart = strListWhenStart.split("; ");
            /*
            When t = entry.getTimes().get(0);
            DateTime start = t.getStartTime();
            String_ProjectStartDate = start.toUiString();
            long_ProjectStartDate = start.getValue();
            String long_to_string_ProjectStartDate = String.valueOf(long_ProjectStartDate);
            DateTime end = t.getEndTime();
            String e = end.toUiString();
            long ee = end.getValue();
             */
            //ListStringProjectCreateDate.add(arrayWhenStart[0]);//第三筆 專案日期 str

            /*
            for (String ans04 : arrayWhenStart) {
            System.out.println("ans04 " + ans04);
            String a4[] = ans04.split(", ");
            for (String aa4 : a4) {
            System.out.println("aa4 " + aa4);
            }
            }
             */

            //_When_End_++++++++++++++++++++++++++++++++++++++++++++++++++++++
              /*
            ListWhenEnd = q.getWhenEnd().get(i);
            arrayWhenEnd = ListWhenEnd.split("; ");
            
            
            for (String ans05 : arrayWhenEnd) {
            System.out.println("ans05 " + ans05);
            String a5[] = ans05.split(", ");
            for (String aa5 : a5) {
            System.out.println("aa5 " + aa5);
            }
            }
             */

            //++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //        System.out.println("");
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

        }//     for (int i = 0; i < q.getWhenStart().size(); i++) 
//~"~"~"~~"~"~"~"~"~"~"~"~"~"~"~"~"~"~"~"~~"~"~"~"~"~"~"~"~"~"~"~"~"~"~"~"~~"~"~"~"~"~"~"~"~"~"~"~


        System.out.println("專案查詢成功");


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


