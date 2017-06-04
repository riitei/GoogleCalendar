/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ReadGoogleCalendar;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author 李偉挺
 */
public class ThreadQueue {

    private List<String> ListWhat01;
    private List<String> ListWhat02;
    private List<String> ListWhat03;
    private List<String> ListWhat04;
    private List<String> ListWhat05;
    private List<String> ListWhat06;
    private List<String> ListWhere01;
    private List<String> ListWhere02;
    private List<String> ListWhere03;
    private List<String> ListWhere04;
    private List<String> ListWhere05;
    private List<String> ListWhere06;
    private List<String> ListDescription01;
    private List<String> ListDescription02;
    private List<String> ListDescription03;
    private List<String> ListDescription04;
    private List<String> ListDescription05;
    private List<String> ListDescription06;
    private List<String> ListWhenStart01;
    private List<String> ListWhenStart02;
    private List<String> ListWhenStart03;
    private List<String> ListWhenStart04;
    private List<String> ListWhenStart05;
    private List<String> ListWhenStart06;
    private List<String> ListWhenEnd01;
    private List<String> ListWhenEnd02;
    private List<String> ListWhenEnd03;
    private List<String> ListWhenEnd04;
    private List<String> ListWhenEnd05;
    private List<String> ListWhenEnd06;
    private Queue<String> queue01;
    private Queue<String> queue02;
    private Queue<String> queue03;
    private Queue<String> queue04;
    private Queue<String> queue05;
    private Queue<String> queue06;
    private String str01;
    private String str02;
    private String str03;
    private String str04;
    private String str05;
    private String str06;

    public void ThreadQueue() {

        ListWhat01 = new LinkedList<>();
        ListWhat02 = new LinkedList<>();
        ListWhat03 = new LinkedList<>();
        ListWhat04 = new LinkedList<>();
        ListWhat05 = new LinkedList<>();
        ListWhat06 = new LinkedList<>();
        ListWhere01 = new LinkedList<>();
        ListWhere02 = new LinkedList<>();
        ListWhere03 = new LinkedList<>();
        ListWhere04 = new LinkedList<>();
        ListWhere05 = new LinkedList<>();
        ListWhere06 = new LinkedList<>();
        ListDescription01 = new LinkedList<>();
        ListDescription02 = new LinkedList<>();
        ListDescription03 = new LinkedList<>();
        ListDescription04 = new LinkedList<>();
        ListDescription05 = new LinkedList<>();
        ListDescription06 = new LinkedList<>();
        ListWhenStart01 = new LinkedList<>();
        ListWhenStart02 = new LinkedList<>();
        ListWhenStart03 = new LinkedList<>();
        ListWhenStart04 = new LinkedList<>();
        ListWhenStart05 = new LinkedList<>();
        ListWhenStart06 = new LinkedList<>();
        ListWhenEnd01 = new LinkedList<>();
        ListWhenEnd02 = new LinkedList<>();
        ListWhenEnd03 = new LinkedList<>();
        ListWhenEnd04 = new LinkedList<>();
        ListWhenEnd05 = new LinkedList<>();
        ListWhenEnd06 = new LinkedList<>();



    }

    public void setThreadQueue(String array) {
        queue01 = new LinkedList<String>();
        queue02 = new LinkedList<String>();
        queue03 = new LinkedList<String>();
        queue04 = new LinkedList<String>();
        queue05 = new LinkedList<String>();
        queue06 = new LinkedList<String>();
        String[] temp = array.split(", ");
        int num = Integer.parseInt(temp[5]);
//        System.out.println("");

        if (num % 6 == 1) {
            //           System.out.println("ThreadQueue num 1 ----- " + num);
            queue01.offer(array);
//            System.out.println(queue01.size());
//            System.out.println("");
        }
        if (num % 6 == 2) {
            //          System.out.println("ThreadQueue num 2 ----- " + num);
            queue02.offer(array);
//            System.out.println(queue02.size());
            //         System.out.println("");
        }
        if (num % 6 == 3) {
//            System.out.println("ThreadQueue num 3 ----- " + num);
            queue03.offer(array);
//            System.out.println(queue03.size());
            //          System.out.println("");
        }
        if (num % 6 == 4) {
            //         System.out.println("ThreadQueue num 4 ----- " + num);
            queue04.offer(array);
            //          System.out.println(queue04.size());
            //          System.out.println("");
        }
        if (num % 6 == 5) {
            //          System.out.println("ThreadQueue num 5 ----- " + num);
            queue05.offer(array);
            //           System.out.println(queue05.size());
            //          System.out.println("");
        }
        if (num % 6 == 0) {
            //         System.out.println("ThreadQueue num 6 ----- " + num);
            queue06.offer(array);
            //         System.out.println(queue06.size());
            //         System.out.println("");
        }



        //*********************************************


        int ii = 1;
        QueryDay qd01 = new QueryDay();
        Thread t1 = new Thread(qd01);
        QueryDay qd02 = new QueryDay();
        Thread t2 = new Thread(qd02);
        QueryDay qd03 = new QueryDay();
        Thread t3 = new Thread(qd03);
        QueryDay qd04 = new QueryDay();
        Thread t4 = new Thread(qd04);
        QueryDay qd05 = new QueryDay();
        Thread t5 = new Thread(qd05);
        QueryDay qd06 = new QueryDay();
        Thread t6 = new Thread(qd06);

        while ((str01 = queue01.poll()) != null) {
            //  System.out.println("Thread01 " + ii + " " + str01);
            t1.setName(str01);
            t1.start();
            try {
                t1.join();
                ListWhat01.add(qd01.getWhat().toString() + ";, ");
                ListWhere01.add(qd01.getWhere().toString() + ";, ");
                ListDescription01.add(qd01.getDescription() + ";, ");
                ListWhenStart01.add(qd01.getWhenStart().toString() + ";, ");
                ListWhenEnd01.add(qd01.getWhenEnd().toString() + ";, ");
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadQueue.class.getName()).log(Level.SEVERE, null, ex);
            }
            //     System.out.println();
            //     System.out.println(queue01.size());
            // ii = ii + 1;
        }//while01

        while ((str02 = queue02.poll()) != null) {
            // System.out.println("Thread02 " + ii + " " + str02);
            t2.setName(str02);
            t2.start();
            try {
                t2.join();
                ListWhat02.add(qd02.getWhat().toString() + ";, ");
                ListWhere02.add(qd02.getWhere().toString() + ";, ");
                ListDescription02.add(qd02.getDescription() + ";, ");
                ListWhenStart02.add(qd02.getWhenStart().toString() + ";, ");
                ListWhenEnd02.add(qd02.getWhenEnd().toString() + ";, ");
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadQueue.class.getName()).log(Level.SEVERE, null, ex);
            }
            // System.out.println();
            // System.out.println(queue02.size());
            //   ii = ii + 1;
        }//while02

        while ((str03 = queue03.poll()) != null) {
            //   System.out.println("Thread03 " + ii + " " + str03);
            t3.setName(str03);
            t3.start();
            try {
                t3.join();
                ListWhat03.add(qd03.getWhat().toString() + ";, ");
                ListWhere03.add(qd03.getWhere().toString() + ";, ");
                ListDescription03.add(qd03.getDescription() + ";, ");
                ListWhenStart03.add(qd03.getWhenStart().toString() + ";, ");
                ListWhenEnd03.add(qd03.getWhenEnd().toString() + ";, ");
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadQueue.class.getName()).log(Level.SEVERE, null, ex);
            }
            //    System.out.println();
            //    System.out.println(queue03.size());
            //    ii = ii + 1;
        }//while03

        while ((str04 = queue04.poll()) != null) {
            //  System.out.println("Thread04 " + ii + " " + str04);
            t4.setName(str04);
            t4.start();
            try {
                t4.join();
                ListWhat04.add(qd04.getWhat().toString() + ";, ");
                ListWhere04.add(qd04.getWhere().toString() + ";, ");
                ListDescription04.add(qd04.getDescription() + ";, ");
                ListWhenStart04.add(qd04.getWhenStart().toString() + ";, ");
                ListWhenEnd04.add(qd04.getWhenEnd().toString() + ";, ");
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadQueue.class.getName()).log(Level.SEVERE, null, ex);
            }
            //    System.out.println();
            //     System.out.println(queue04.size());
            // ii=ii+1;
        }//while04

        while ((str05 = queue05.poll()) != null) {
            //  System.out.println("Thread05 " + ii + " " + str05);
            t5.setName(str05);
            t5.start();
            try {
                t5.join();
                ListWhat05.add(qd05.getWhat().toString() + ";, ");
                ListWhere05.add(qd05.getWhere().toString() + ";, ");
                ListDescription05.add(qd05.getDescription() + ";, ");
                ListWhenStart05.add(qd05.getWhenStart().toString() + ";, ");
                ListWhenEnd05.add(qd05.getWhenEnd().toString() + ";, ");
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadQueue.class.getName()).log(Level.SEVERE, null, ex);
            }
            //  System.out.println();
            //  System.out.println(queue05.size());
            //   ii = ii + 1;
        }//while05

        while ((str06 = queue06.poll()) != null) {
            // System.out.println("Thread06 " + ii + " " + str06);
            t6.setName(str06);
            t6.start();
            try {
                t6.join();
                ListWhat06.add(qd06.getWhat().toString() + ";, ");
                ListWhere06.add(qd06.getWhere().toString() + ";, ");
                ListDescription06.add(qd06.getDescription() + ";, ");
                ListWhenStart06.add(qd06.getWhenStart().toString() + ";, ");
                ListWhenEnd06.add(qd06.getWhenEnd().toString() + ";, ");
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadQueue.class.getName()).log(Level.SEVERE, null, ex);
            }
            //  System.out.println();
            //  System.out.println(queue06.size());
            // ii = ii + 1;
        }//while06
    }

    //************************************************
    public List<String> getWhat01() {
        return ListWhat01;
    }

    public List<String> getWhat02() {
        return ListWhat02;
    }

    public List<String> getWhat03() {
        return ListWhat03;
    }

    public List<String> getWhat04() {
        return ListWhat04;
    }

    public List<String> getWhat05() {
        return ListWhat05;
    }

    public List<String> getWhat06() {
        return ListWhat06;
    }
    //************************************************

    public List<String> getWhere01() {
        return ListWhere01;
    }

    public List<String> getWhere02() {
        return ListWhere02;
    }

    public List<String> getWhere03() {
        return ListWhere03;
    }

    public List<String> getWhere04() {
        return ListWhere04;
    }

    public List<String> getWhere05() {
        return ListWhere05;
    }

    public List<String> getWhere06() {
        return ListWhere06;
    }
    //************************************************

    public List<String> getDescription01() {
        return ListDescription01;
    }

    public List<String> getDescription02() {
        return ListDescription02;
    }

    public List<String> getDescription03() {
        return ListDescription03;
    }

    public List<String> getDescription04() {
        return ListDescription04;
    }

    public List<String> getDescription05() {
        return ListDescription05;
    }

    public List<String> getDescription06() {
        return ListDescription06;
    }
//************************************************

    public List<String> getWhenStart01() {
        return ListWhenStart01;
    }

    public List<String> getWhenStart02() {
        return ListWhenStart02;
    }

    public List<String> getWhenStart03() {
        return ListWhenStart03;
    }

    public List<String> getWhenStart04() {
        return ListWhenStart04;
    }

    public List<String> getWhenStart05() {
        return ListWhenStart05;
    }

    public List<String> getWhenStart06() {
        return ListWhenStart06;
    }
//************************************************

    public List<String> getWhenEnd01() {
        return ListWhenEnd01;
    }

    public List<String> getWhenEnd02() {
        return ListWhenEnd02;
    }

    public List<String> getWhenEnd03() {
        return ListWhenEnd03;
    }

    public List<String> getWhenEnd04() {
        return ListWhenEnd04;
    }

    public List<String> getWhenEnd05() {
        return ListWhenEnd05;
    }

    public List<String> getWhenEnd06() {
        return ListWhenEnd06;
    }
}

