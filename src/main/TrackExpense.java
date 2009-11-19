/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import javax.microedition.io.*;
import javax.microedition.io.file.FileConnection;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
import org.netbeans.microedition.lcdui.WaitScreen;
import org.netbeans.microedition.util.SimpleCancellableTask;

/**
 * @author Z000DG8C
 */
public class TrackExpense extends MIDlet implements CommandListener,ItemStateListener {

    private boolean midletPaused = false;
    private String currFilter = "";
    private int[] currIndices = null;
    String deLimiter = "^";
    Vector exceptions = new Vector();

    //<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private Command exitCommand;
    private Command addExpenseCommand;
    private Command okCommand;
    private Command cleanExpensesCommand;
    private Command expensesummaryCommand;
    private Command exportExpensesCommand;
    private Command aboutCommand;
    private Command returnCommand;
    private Command detailsExpensesCommand;
    private Command deleteExpense;
    private Command expenseDetailsCommand;
    private Command deleteCommand;
    private Command backCommand;
    private Command exitWOReportCommand;
    private Command checkUpdateCommand;
    private Command reportBugsCommand;
    private Form form;
    private TextField amount;
    private TextField details;
    private ChoiceGroup choiceGroup;
    private DateField dateField;
    private List detailExpList;
    private List expSumList;
    private Alert About;
    private WaitScreen waitScreen;
    private Alert Delete;
    private Alert ReportBugs;
    private SimpleCancellableTask task;
    private Image image7;
    private Image image6;
    private Image image5;
    private Image image4;
    private Image image3;
    private Image image2;
    private Image image1;
    private Image image;
    private Image image16;
    private Image image9;
    private Image image8;
    private Image image11;
    private Image image10;
    private Image image13;
    private Image image12;
    private Image image15;
    private Image image14;
    private Image image17;
    //</editor-fold>//GEN-END:|fields|0|

    private void showMsg(String msg,String title, AlertType type)
    {
        Alert a = new Alert(title, msg, null, type);
        a.setTimeout(Alert.FOREVER);
        Display.getDisplay(this).setCurrent(a);
    }
private String URLEncode(String s)
   {
      StringBuffer sbuf = new StringBuffer();
      int ch;
      for (int i = 0; i < s.length(); i++)
      {
         ch = s.charAt(i);
         switch(ch)
         {
            case ' ': { sbuf.append("+"); break;}
            case '!': { sbuf.append("%21"); break;}
            case '*': { sbuf.append("%2A"); break;}
            case '\'': { sbuf.append("%27"); break;}
            case '(': { sbuf.append("%28"); break;}
            case ')': { sbuf.append("%29"); break;}
            case ';': { sbuf.append("%3B"); break;}
            case ':': { sbuf.append("%3A"); break;}
            case '@': { sbuf.append("%40"); break;}
            case '&': { sbuf.append("%26"); break;}
            case '=': { sbuf.append("%3D"); break;}
            case '+': { sbuf.append("%2B"); break;}
            case '$': { sbuf.append("%24"); break;}
            case ',': { sbuf.append("%2C"); break;}
            case '/': { sbuf.append("%2F"); break;}
            case '?': { sbuf.append("%3F"); break;}
            case '%': { sbuf.append("%25"); break;}
            case '#': { sbuf.append("%23"); break;}
            case '[': { sbuf.append("%5B"); break;}
            case ']': { sbuf.append("%5D"); break;}
            default: sbuf.append((char)ch);
         }
      }
      return sbuf.toString();
   }
    private void postBug(String exception)
   {
        HttpConnection httpConn = null;
        String url = "http://webmediator.appspot.com/";
        InputStream is = null;
        OutputStream os = null;

        try {
            // Open an HTTP Connection object
            httpConn = (HttpConnection) Connector.open(url);
            // Setup HTTP Request to POST
            httpConn.setRequestMethod(HttpConnection.POST);

            httpConn.setRequestProperty("User-Agent",
                    "Profile/MIDP-1.0 Confirguration/CLDC-1.0");
            httpConn.setRequestProperty("Accept_Language", "en-US");
            //Content-Type is must to pass parameters in POST Request
            httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            String params;
            params = URLEncode("exception") + "=" + URLEncode(exception);
            try
            {
                params += "&" + URLEncode("model") + "=" +URLEncode(System.getProperty("microedition.platform"));
            } catch(Exception e){}

            httpConn.setRequestProperty("Content-length", ""+params.getBytes().length);

            os = httpConn.openOutputStream();
            os.write(params.getBytes());
            

            /**Caution: os.flush() is controversial. It may create unexpected behavior
            on certain mobile devices. Try it out for your mobile device **/
            //os.flush();
            // Read Response from the Server
            StringBuffer sb = new StringBuffer();
            is = httpConn.openDataInputStream();
            int chr;
            while ((chr = is.read()) != -1) {
                sb.append((char) chr);
            }

            String sout = sb.toString();

            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.close();
            }
            if (httpConn != null) {
                httpConn.close();
            }
        } catch (Exception e) {
        }
    }
    /**
     * The HelloMIDlet constructor.
     */
    public TrackExpense() {
    }

    //<editor-fold defaultstate="collapsed" desc=" Generated Methods ">//GEN-BEGIN:|methods|0|
    //</editor-fold>//GEN-END:|methods|0|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: initialize ">//GEN-BEGIN:|0-initialize|0|0-preInitialize
    /**
     * Initilizes the application.
     * It is called only once when the MIDlet is started. The method is called before the <code>startMIDlet</code> method.
     */
    private void initialize() {//GEN-END:|0-initialize|0|0-preInitialize
        // write pre-initialize user code here
//GEN-LINE:|0-initialize|1|0-postInitialize
        // write post-initialize user code here
    }//GEN-BEGIN:|0-initialize|2|
    //</editor-fold>//GEN-END:|0-initialize|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: startMIDlet ">//GEN-BEGIN:|3-startMIDlet|0|3-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Started point.
     */
    public void startMIDlet() {//GEN-END:|3-startMIDlet|0|3-preAction
        // write pre-action user code here
        switchDisplayable(null, getForm());//GEN-LINE:|3-startMIDlet|1|3-postAction
    }//GEN-BEGIN:|3-startMIDlet|2|
    //</editor-fold>//GEN-END:|3-startMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: resumeMIDlet ">//GEN-BEGIN:|4-resumeMIDlet|0|4-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Resumed point.
     */
    public void resumeMIDlet() {//GEN-END:|4-resumeMIDlet|0|4-preAction
        // write pre-action user code here
//GEN-LINE:|4-resumeMIDlet|1|4-postAction
        // write post-action user code here
    }//GEN-BEGIN:|4-resumeMIDlet|2|
    //</editor-fold>//GEN-END:|4-resumeMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: switchDisplayable ">//GEN-BEGIN:|5-switchDisplayable|0|5-preSwitch
    /**
     * Switches a current displayable in a display. The <code>display</code> instance is taken from <code>getDisplay</code> method. This method is used by all actions in the design for switching displayable.
     * @param alert the Alert which is temporarily set to the display; if <code>null</code>, then <code>nextDisplayable</code> is set immediately
     * @param nextDisplayable the Displayable to be set
     */
    public void switchDisplayable(Alert alert, Displayable nextDisplayable) {//GEN-END:|5-switchDisplayable|0|5-preSwitch
        // write pre-switch user code here
        Display display = getDisplay();//GEN-BEGIN:|5-switchDisplayable|1|5-postSwitch
        if (alert == null) {
            display.setCurrent(nextDisplayable);
        } else {
            display.setCurrent(alert, nextDisplayable);
        }//GEN-END:|5-switchDisplayable|1|5-postSwitch
        // write post-switch user code here
    }//GEN-BEGIN:|5-switchDisplayable|2|
    //</editor-fold>//GEN-END:|5-switchDisplayable|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Displayables ">//GEN-BEGIN:|7-commandAction|0|7-preCommandAction
    /**
     * Called by a system to indicated that a command has been invoked on a particular displayable.
     * @param command the Command that was invoked
     * @param displayable the Displayable where the command was invoked
     */
    public void commandAction(Command command, Displayable displayable) {//GEN-END:|7-commandAction|0|7-preCommandAction
        // write pre-action user code here
        if (displayable == Delete) {//GEN-BEGIN:|7-commandAction|1|144-preAction
            if (command == backCommand) {//GEN-END:|7-commandAction|1|144-preAction
                // write pre-action user code here
                switchDisplayable(null, getDetailExpList());//GEN-LINE:|7-commandAction|2|144-postAction
                // write post-action user code here
            } else if (command == deleteCommand) {//GEN-LINE:|7-commandAction|3|142-preAction
                // write pre-action user code here
                switchDisplayable(null, getDetailExpList());//GEN-LINE:|7-commandAction|4|142-postAction
                // write post-action user code here
                try {
                    int nIndex = getDetailExpList().getSelectedIndex();
                    if (nIndex >= 0) {
                        RecordStore rs = RecordStore.openRecordStore("MyExpenses", true);
                        int nRecIndex = currIndices[nIndex];
                        rs.deleteRecord(nRecIndex);
                        rs.closeRecordStore();
                        fillExpensesDetails(currFilter);
                    }
                } catch (Exception ex) {
                    reportBug(ex);
                }
            }//GEN-BEGIN:|7-commandAction|5|154-preAction
        } else if (displayable == ReportBugs) {
            if (command == exitWOReportCommand) {//GEN-END:|7-commandAction|5|154-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|6|154-postAction
                // write post-action user code here
            } else if (command == reportBugsCommand) {//GEN-LINE:|7-commandAction|7|152-preAction
                // write pre-action user code here
                switchDisplayable(null, null);
                ReportBugs.setTimeout(1);

                String strMessage = "";
                int nErrCount = 1;
                Enumeration e = exceptions.elements();
                while (e.hasMoreElements()) {
                        Exception excep = (Exception)e.nextElement();
                        strMessage += "[" + nErrCount +"]\n";
                        strMessage += excep.getMessage() + "\n";
                }
                try
                {
//                    MessageConnection clientConn;
//                    clientConn=(MessageConnection)Connector.open("sms://+919845258299");
//                    TextMessage textmessage = (TextMessage) clientConn.newMessage(MessageConnection.TEXT_MESSAGE);
//                    textmessage.setAddress("sms://+919845258299");
//                    textmessage.setPayloadText(strMessage);
//                    clientConn.send(textmessage);
                        postBug(strMessage);
                }
                catch(Exception ew){}

                exitMIDlet();//GEN-LINE:|7-commandAction|8|152-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|9|58-preAction
        } else if (displayable == detailExpList) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|9|58-preAction
                // write pre-action user code here
                detailExpListAction();//GEN-LINE:|7-commandAction|10|58-postAction
                // write post-action user code here
            } else if (command == deleteExpense) {//GEN-LINE:|7-commandAction|11|118-preAction
                // write pre-action user code here
                int nIndex = getExpSumList().getSelectedIndex();
                if (nIndex >= 0) {
                    switchDisplayable(null, getDelete());//GEN-LINE:|7-commandAction|12|118-postAction
                    // write post-action user code here
                }
            } else if (command == okCommand) {//GEN-LINE:|7-commandAction|13|61-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|14|61-postAction
                // write post-action user code here
                if(currFilter.equalsIgnoreCase("")) {
                    switchDisplayable(null, getForm());
                }
                else {
                    fillExpenseSummary();
                    switchDisplayable(null, getExpSumList());
                }
            }//GEN-BEGIN:|7-commandAction|15|86-preAction
        } else if (displayable == expSumList) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|15|86-preAction
                // write pre-action user code here
                expSumListAction();//GEN-LINE:|7-commandAction|16|86-postAction
                // write post-action user code here
                int nIndex = getExpSumList().getSelectedIndex();
                if (nIndex >= 0) {                // write pre-action user code here
                    switchDisplayable(null, getDetailExpList());
                    // write post-action user code here
                    String currEntry = getExpSumList().getString(nIndex);
                    String cols[] = split(currEntry, ":");
                    fillExpensesDetails(cols[0]);
                }
            } else if (command == detailsExpensesCommand) {//GEN-LINE:|7-commandAction|17|89-preAction
                int nIndex = getExpSumList().getSelectedIndex();
                if (nIndex >= 0) {                // write pre-action user code here
                    switchDisplayable(null, getDetailExpList());//GEN-LINE:|7-commandAction|18|89-postAction
                    // write post-action user code here
                    String currEntry = getExpSumList().getString(nIndex);
                    String cols[] = split(currEntry, ":");
                    fillExpensesDetails(cols[0]);
                }
            } else if (command == returnCommand) {//GEN-LINE:|7-commandAction|19|96-preAction
                // write pre-action user code here
                switchDisplayable(null, getForm());//GEN-LINE:|7-commandAction|20|96-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|21|83-preAction
        } else if (displayable == form) {
            if (command == aboutCommand) {//GEN-END:|7-commandAction|21|83-preAction
                // write pre-action user code here
                switchDisplayable(null, getAbout());//GEN-LINE:|7-commandAction|22|83-postAction
                // write post-action user code here
            } else if (command == addExpenseCommand) {//GEN-LINE:|7-commandAction|23|27-preAction
                    // write pre-action user code here
//GEN-LINE:|7-commandAction|24|27-postAction
                try
                {
                    if(0 != getAmount().getString().length())
                    {
                        //Date d = new Date();
                        Calendar c = Calendar.getInstance();
                        c.setTime(dateField.getDate());

                        String dt = c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH)+1) + "/" + c.get(Calendar.YEAR);

                        String expense = dt + deLimiter + getAmount().getString().replace(deLimiter.charAt(0), '#')
                                + deLimiter + getDetails().getString().replace(deLimiter.charAt(0), '#')
                                + deLimiter + choiceGroup.getString(choiceGroup.getSelectedIndex()).replace(deLimiter.charAt(0), '#') ;
                        RecordStore rs = RecordStore.openRecordStore("MyExpenses",true);
                        rs.addRecord(expense.getBytes(),0,expense.length());

                        String appt = "Accounted " + getAmount().getString() + " under " + choiceGroup.getString(choiceGroup.getSelectedIndex());
                        Display.getDisplay(this).setCurrentItem(amount);
                        amount.setString("");
                        details.setString("");
                        rs.closeRecordStore();
                        showMsg(appt,"Information",AlertType.INFO);
                    }
                }
                catch(Exception rse)
                {
                    reportBug(rse);
                    showMsg("Can't write","Error", AlertType.ERROR);
                }
            } else if (command == checkUpdateCommand) {//GEN-LINE:|7-commandAction|25|149-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|26|149-postAction
                // write post-action user code here
                try
                {
                    String currVersion = getAppProperty("MIDlet-Version");

                    HttpConnection httpConn = null;
                    String url = "http://trackmyexpense.googlecode.com/svn/trunk/distribution/S60Emulator/TrackExpenses.jad";
                    InputStream is = null;
                    OutputStream os = null;

                    try {
                        // Open an HTTP Connection object
                        httpConn = (HttpConnection) Connector.open(url);
                        // Setup HTTP Request to POST
                        httpConn.setRequestMethod(HttpConnection.GET);

                        httpConn.setRequestProperty("User-Agent",
                                "Profile/MIDP-1.0 Confirguration/CLDC-1.0");
                        httpConn.setRequestProperty("Accept_Language", "en-US");
                        //Content-Type is must to pass parameters in POST Request
                        httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");


                        os = httpConn.openOutputStream();

                        /**Caution: os.flush() is controversial. It may create unexpected behavior
                        on certain mobile devices. Try it out for your mobile device **/
                        //os.flush();
                        // Read Response from the Server
                        StringBuffer sb = new StringBuffer();
                        is = httpConn.openDataInputStream();
                        int chr;
                        while ((chr = is.read()) != -1) {
                            sb.append((char) chr);
                        }

                        //Delete me
                        //platformRequest("http://trackmyexpense.googlecode.com/svn/trunk/distribution/S60Emulator/TrackExpenses.jar");
                        String strHtmlOut = sb.toString();
                        String[] props = split(strHtmlOut,"\r\n");

                        String newVersion = "";
                        for(int nProp = 0; nProp < props.length; nProp++)
                        {
                            String[] cols = split(props[nProp],":");
                            String key = cols[0];
                            String value = cols[1];
                            key = key.trim();
                            value = value.trim();
                            if("MIDlet-Version".equalsIgnoreCase(key))
                            {
                                newVersion = value;
                                break;
                            }
                        }
                        if(newVersion.length() >0)
                        {
                            if( !newVersion.equalsIgnoreCase(currVersion)
                                    && (getVersionInt(newVersion) > getVersionInt(currVersion)))
                            {
                                exitMIDlet();
                                platformRequest("http://trackmyexpense.googlecode.com/svn/trunk/distribution/S60Emulator/TrackExpenses.jar");
                            }
                            else
                            {
                                showMsg("You have already the latest version", "Update", AlertType.INFO);
                            }
                        }
                        else
                        {
                            showMsg("Unable to determine the latest version", "Warning", AlertType.WARNING);
                        }
                    } finally {
                        if (is != null) {
                            is.close();
                        }
                        if (os != null) {
                            os.close();
                        }
                        if (httpConn != null) {
                            httpConn.close();
                        }
                    }

                }
                catch(Exception e){
                    reportBug(e);
                    showMsg("Unable to check for update.", "Error", AlertType.ERROR);
                }
            } else if (command == cleanExpensesCommand) {//GEN-LINE:|7-commandAction|27|67-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|28|67-postAction
                // write post-action user code here
                if (RecordStore.listRecordStores() != null) {
                    try {
                        RecordStore.deleteRecordStore("MyExpenses");
                        showMsg("Cleaned datastore","Information", AlertType.INFO);
                    } catch (Exception e) {
                        reportBug(e);
                        showMsg("Can't delete the datastore","Error", AlertType.ERROR);
                    }
                }
            } else if (command == exitCommand) {//GEN-LINE:|7-commandAction|29|19-preAction
                // write pre-action user code here
                if(exceptions.isEmpty())
                {
                    exitMIDlet();
                }
                else
                {
                    switchDisplayable(null, getReportBugs());//GEN-LINE:|7-commandAction|30|19-postAction
                    // write post-action user code here
                }
            } else if (command == expenseDetailsCommand) {//GEN-LINE:|7-commandAction|31|113-preAction
                // write pre-action user code here
                switchDisplayable(null, getDetailExpList());//GEN-LINE:|7-commandAction|32|113-postAction
                // write post-action user code here
                fillExpensesDetails("");
            } else if (command == expensesummaryCommand) {//GEN-LINE:|7-commandAction|33|80-preAction
                // write pre-action user code here
                switchDisplayable(null, getExpSumList());//GEN-LINE:|7-commandAction|34|80-postAction
                // write post-action user code here
                fillExpenseSummary();
            } else if (command == exportExpensesCommand) {//GEN-LINE:|7-commandAction|35|75-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|36|75-postAction
                // write post-action user code here
                RecordStore rs = null;
                try {
                    Date d = new Date();
                    Calendar c = Calendar.getInstance();
                    c.setTime(d);
                    String dt = c.get(Calendar.DAY_OF_MONTH) + "_" + (c.get(Calendar.MONTH) + 1) + "_" + c.get(Calendar.YEAR);
                    String fileName = System.getProperty("fileconn.dir.memorycard") + "myexpenses_" + dt + ".csv";
                    FileConnection fc = (FileConnection) Connector.open(fileName, Connector.READ_WRITE);
                    if (!fc.exists()) {
                        fc.create();
                    } else {
                        fc.delete();
                        fc.create();
                    }
                    OutputStream is = fc.openOutputStream();

                    rs = RecordStore.openRecordStore("MyExpenses", true);
                    if (0 == rs.getNumRecords()) {
                        showMsg("No records found", "Information", AlertType.INFO);
                        if (null != rs) {
                            rs.closeRecordStore();
                        }
                        return;
                    }
                    RecordEnumeration re = rs.enumerateRecords(null, null, false);

                    is.write(("Date,Amount,Description,Category\n").getBytes());
                    while (re.hasNextElement()) {
                        String data = new String(re.nextRecord());
                        String[] cols = split(data,deLimiter);
                        data = "\"" + cols[0] + "\"," + cols[1] + ",\"" + cols[2] + "\",\"" + cols[3] + "\"\n";
                        is.write((data).getBytes());
                    }

                    is.close();
                    fc.close();
                    showMsg("Exported as " + fileName,"Information", AlertType.INFO);
                } catch (Exception e) {
                    reportBug(e);
                    showMsg("Failed to export","Error", AlertType.ERROR);
                }
                if (null != rs) {
                    try {
                        rs.closeRecordStore();
                    } catch (Exception e) {
                        reportBug(e);
                    }
                }
            }//GEN-BEGIN:|7-commandAction|37|110-preAction
        } else if (displayable == waitScreen) {
            if (command == WaitScreen.FAILURE_COMMAND) {//GEN-END:|7-commandAction|37|110-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|38|110-postAction
                // write post-action user code here
            } else if (command == WaitScreen.SUCCESS_COMMAND) {//GEN-LINE:|7-commandAction|39|109-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|40|109-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|41|7-postCommandAction
        }//GEN-END:|7-commandAction|41|7-postCommandAction
        // write post-action user code here
    }//GEN-BEGIN:|7-commandAction|42|
    //</editor-fold>//GEN-END:|7-commandAction|42|

int getVersionInt(String str)
{
    String[] cols = split(str,".");
    int ret = Integer.parseInt(cols[0])*10000 + Integer.parseInt(cols[1])*1000 + Integer.parseInt(cols[2]);
    return ret;
}

void reportBug(Exception e)
{
    exceptions.addElement(e);
}

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand ">//GEN-BEGIN:|18-getter|0|18-preInit
/**
 * Returns an initiliazed instance of exitCommand component.
 * @return the initialized component instance
 */
public Command getExitCommand() {
    if (exitCommand == null) {//GEN-END:|18-getter|0|18-preInit
            // write pre-init user code here
        exitCommand = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|18-getter|1|18-postInit
            // write post-init user code here
    }//GEN-BEGIN:|18-getter|2|
    return exitCommand;
}
//</editor-fold>//GEN-END:|18-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: form ">//GEN-BEGIN:|14-getter|0|14-preInit
/**
 * Returns an initiliazed instance of form component.
 * @return the initialized component instance
 */
public Form getForm() {
    if (form == null) {//GEN-END:|14-getter|0|14-preInit
            // write pre-init user code here
        form = new Form("TrackExpense", new Item[] { getAmount(), getDetails(), getChoiceGroup(), getDateField() });//GEN-BEGIN:|14-getter|1|14-postInit
        form.addCommand(getExitCommand());
        form.addCommand(getAddExpenseCommand());
        form.addCommand(getExpenseDetailsCommand());
        form.addCommand(getExpensesummaryCommand());
        form.addCommand(getCleanExpensesCommand());
        form.addCommand(getExportExpensesCommand());
        form.addCommand(getCheckUpdateCommand());
        form.addCommand(getAboutCommand());
        form.setCommandListener(this);//GEN-END:|14-getter|1|14-postInit
            // write post-init user code here
            form.setItemStateListener(this);
    }//GEN-BEGIN:|14-getter|2|
    return form;
}
//</editor-fold>//GEN-END:|14-getter|2|
    //</editor-fold>

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: amount ">//GEN-BEGIN:|22-getter|0|22-preInit
/**
 * Returns an initiliazed instance of amount component.
 * @return the initialized component instance
 */
public TextField getAmount() {
    if (amount == null) {//GEN-END:|22-getter|0|22-preInit
            // write pre-init user code here
        amount = new TextField("Amount", null, 32, TextField.NUMERIC);//GEN-LINE:|22-getter|1|22-postInit
            // write post-init user code here
    }//GEN-BEGIN:|22-getter|2|
    return amount;
}
//</editor-fold>//GEN-END:|22-getter|2|


    //</editor-fold>

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: details ">//GEN-BEGIN:|25-getter|0|25-preInit
/**
 * Returns an initiliazed instance of details component.
 * @return the initialized component instance
 */
public TextField getDetails() {
    if (details == null) {//GEN-END:|25-getter|0|25-preInit
            // write pre-init user code here
        details = new TextField("Details", null, 32, TextField.ANY);//GEN-LINE:|25-getter|1|25-postInit
            // write post-init user code here
    }//GEN-BEGIN:|25-getter|2|
    return details;
}
//</editor-fold>//GEN-END:|25-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: addExpenseCommand ">//GEN-BEGIN:|26-getter|0|26-preInit
/**
 * Returns an initiliazed instance of addExpenseCommand component.
 * @return the initialized component instance
 */
public Command getAddExpenseCommand() {
    if (addExpenseCommand == null) {//GEN-END:|26-getter|0|26-preInit
            // write pre-init user code here
        addExpenseCommand = new Command("Add Expense", Command.OK, 0);//GEN-LINE:|26-getter|1|26-postInit
            // write post-init user code here
    }//GEN-BEGIN:|26-getter|2|
    return addExpenseCommand;
}
//</editor-fold>//GEN-END:|26-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: choiceGroup ">//GEN-BEGIN:|29-getter|0|29-preInit
/**
 * Returns an initiliazed instance of choiceGroup component.
 * @return the initialized component instance
 */
public ChoiceGroup getChoiceGroup() {
    if (choiceGroup == null) {//GEN-END:|29-getter|0|29-preInit
            // write pre-init user code here
        choiceGroup = new ChoiceGroup("Category", Choice.POPUP);//GEN-BEGIN:|29-getter|1|29-postInit
        choiceGroup.append("Travel", getImage());
        choiceGroup.append("Petrol", getImage15());
        choiceGroup.append("Groceries", getImage1());
        choiceGroup.append("Vegetables", getImage2());
        choiceGroup.append("Apparels", getImage3());
        choiceGroup.append("Eat out", getImage4());
        choiceGroup.append("Medical", getImage5());
        choiceGroup.append("Phone/Water/Elec", getImage6());
        choiceGroup.append("Child/School", getImage7());
        choiceGroup.append("Vehicle", getImage8());
        choiceGroup.append("Home Improvement", getImage9());
        choiceGroup.append("Loan", getImage10());
        choiceGroup.append("Investment", getImage11());
        choiceGroup.append("Movie/Entertainment", getImage12());
        choiceGroup.append("Donation/Offerings", getImage13());
        choiceGroup.append("Other", getImage14());
        choiceGroup.setSelectedFlags(new boolean[] { false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false });//GEN-END:|29-getter|1|29-postInit
            // write post-init user code here
    }//GEN-BEGIN:|29-getter|2|
    return choiceGroup;
}
//</editor-fold>//GEN-END:|29-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand ">//GEN-BEGIN:|60-getter|0|60-preInit
/**
 * Returns an initiliazed instance of okCommand component.
 * @return the initialized component instance
 */
public Command getOkCommand() {
    if (okCommand == null) {//GEN-END:|60-getter|0|60-preInit
            // write pre-init user code here
        okCommand = new Command("Back", Command.BACK, 0);//GEN-LINE:|60-getter|1|60-postInit
            // write post-init user code here
    }//GEN-BEGIN:|60-getter|2|
    return okCommand;
}
//</editor-fold>//GEN-END:|60-getter|2|
    //</editor-fold>

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: detailExpList ">//GEN-BEGIN:|56-getter|0|56-preInit
/**
 * Returns an initiliazed instance of detailExpList component.
 * @return the initialized component instance
 */
public List getDetailExpList() {
    if (detailExpList == null) {//GEN-END:|56-getter|0|56-preInit
            // write pre-init user code here
        detailExpList = new List("Expense Details", Choice.IMPLICIT);//GEN-BEGIN:|56-getter|1|56-postInit
        detailExpList.addCommand(getOkCommand());
        detailExpList.addCommand(getDeleteExpense());
        detailExpList.setCommandListener(this);//GEN-END:|56-getter|1|56-postInit
            // write post-init user code here
    }//GEN-BEGIN:|56-getter|2|
    return detailExpList;
}
//</editor-fold>//GEN-END:|56-getter|2|

    private String[] split(String original) {
        return split(original, ",");
    }
    private String[] split(String original, String separator) {
        Vector nodes = new Vector();
        int index = original.indexOf(separator);
        while (index >= 0) {
            nodes.addElement(original.substring(0, index));
            original = original.substring(index + separator.length());
            index = original.indexOf(separator);
        }
        // Get the last node
        nodes.addElement(original);

        String[] result = new String[nodes.size()];
        if (nodes.size() > 0) {
            for (int loop = 0; loop < nodes.size(); loop++) {
                result[loop] = (String) nodes.elementAt(loop);
                System.out.println(result[loop]);
            }
        }
        return result;
    }

    private void fillExpenseSummary()
    {
        //Display.getDisplay(this).setCurrent(waitScreen);
        Hashtable catExp = new Hashtable();
        RecordStore rs = null;
        try {
            expSumList.deleteAll();
            if (null == rs) {
                rs = RecordStore.openRecordStore("MyExpenses", true);
            }
            if(0 == rs.getNumRecords())
            {
                //showMsg("No records found", "Information", AlertType.INFO);
                if(null != rs)
                {
                    rs.closeRecordStore();
                }
                return;
            }
            RecordEnumeration re = rs.enumerateRecords(null, null, false);
            while (re.hasNextElement()) {
                String oneRow = new String(re.nextRecord());
                String[] cols = split(oneRow, deLimiter);
                Integer catSum = (Integer)catExp.get(cols[3]);
                if(null != catSum)
                {
                    catSum = new Integer(catSum.intValue() + Integer.parseInt(cols[1]));
                }
                else
                {
                    catSum = new Integer(Integer.parseInt(cols[1]));
                }
                catExp.put(cols[3], catSum);
            }
            Enumeration en = catExp.keys();
            while (en.hasMoreElements())
            {
                String key = (String)en.nextElement();
                Integer sum = (Integer)catExp.get(key);
                expSumList.append(key + " : " + sum, null);
            }

        } catch (Exception e) {
            reportBug(e);
            //showMsg("Can't open datastore", "Error", AlertType.ERROR);
        }
        if (null != rs) {
            try {
                rs.closeRecordStore();
            } catch (Exception e) {
            }
        }
    }
    private void fillExpensesDetails(String filter)
    {
        filter = filter.trim();
        currFilter = filter;
        currIndices = new int[300];
        RecordStore rs = null;
        try {
            detailExpList.deleteAll();
            if (null == rs) {
                rs = RecordStore.openRecordStore("MyExpenses", true);
            }
            if(0 == rs.getNumRecords())
            {
                //showMsg("No records found", "Information", AlertType.INFO);
                if(null != rs)
                {
                    rs.closeRecordStore();
                }
                return;
            }
            RecordEnumeration re = rs.enumerateRecords(null, null, false);

            int listIndex = 0;
            while (re.hasPreviousElement()) {
                int recId = re.previousRecordId();
                String oneRow = new String(rs.getRecord(recId));
                String cols[] = split(oneRow,deLimiter);
                if(0 != filter.compareTo(""))
                {
                    if(0 == cols[3].compareTo(filter))
                    {
                        //detailExpList.append(allElements[j], null);
                        detailExpList.append(cols[0] + ":" + cols[1] + "(" + cols[2] + ")", null);
                        currIndices[listIndex] = recId;
                        listIndex++;
                    }
                }
                else
                {
                    detailExpList.append(cols[0] + ":" + cols[1] + "(" + cols[2] + ")[" + cols[3] +"]", null);
                    currIndices[listIndex] = recId;
                    listIndex++;
                }

                if (listIndex >= 300) {
                    showMsg("Displaying the last 300 entries. Tip: Export & Clean!", "Too many records", AlertType.WARNING);
                    break;
                }
            }
            //getDetailExpList().setSelectedIndex(0, true);
            
        } catch (Exception e) {
            reportBug(e);
            //showMsg("Can't open datastore", "Error", AlertType.ERROR);
        }
        if (null != rs) {
            try {
                rs.closeRecordStore();
            } catch (Exception e) {
                reportBug(e);
            }
        }
    }
    //<editor-fold defaultstate="collapsed" desc=" Generated Method: detailExpListAction ">//GEN-BEGIN:|56-action|0|56-preAction
    /**
     * Performs an action assigned to the selected list element in the detailExpList component.
     */
    public void detailExpListAction() {//GEN-END:|56-action|0|56-preAction
        // enter pre-action user code here
        String __selectedString = getDetailExpList().getString(getDetailExpList().getSelectedIndex());//GEN-LINE:|56-action|1|56-postAction
        // enter post-action user code here
    }//GEN-BEGIN:|56-action|2|
    //</editor-fold>//GEN-END:|56-action|2|



    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: cleanExpensesCommand ">//GEN-BEGIN:|66-getter|0|66-preInit
    /**
     * Returns an initiliazed instance of cleanExpensesCommand component.
     * @return the initialized component instance
     */
    public Command getCleanExpensesCommand() {
        if (cleanExpensesCommand == null) {//GEN-END:|66-getter|0|66-preInit
            // write pre-init user code here
            cleanExpensesCommand = new Command("Clean Expenses", "Clean Expenses", Command.SCREEN, 0);//GEN-LINE:|66-getter|1|66-postInit
            // write post-init user code here
        }//GEN-BEGIN:|66-getter|2|
        return cleanExpensesCommand;
    }
    //</editor-fold>//GEN-END:|66-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: exportExpensesCommand ">//GEN-BEGIN:|74-getter|0|74-preInit
    /**
     * Returns an initiliazed instance of exportExpensesCommand component.
     * @return the initialized component instance
     */
    public Command getExportExpensesCommand() {
        if (exportExpensesCommand == null) {//GEN-END:|74-getter|0|74-preInit
            // write pre-init user code here
            exportExpensesCommand = new Command("Export expenses", Command.SCREEN, 0);//GEN-LINE:|74-getter|1|74-postInit
            // write post-init user code here
        }//GEN-BEGIN:|74-getter|2|
        return exportExpensesCommand;
    }
    //</editor-fold>//GEN-END:|74-getter|2|



    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: expensesummaryCommand ">//GEN-BEGIN:|79-getter|0|79-preInit
    /**
     * Returns an initiliazed instance of expensesummaryCommand component.
     * @return the initialized component instance
     */
    public Command getExpensesummaryCommand() {
        if (expensesummaryCommand == null) {//GEN-END:|79-getter|0|79-preInit
            // write pre-init user code here
            expensesummaryCommand = new Command("Expense Summary", Command.SCREEN, 0);//GEN-LINE:|79-getter|1|79-postInit
            // write post-init user code here
        }//GEN-BEGIN:|79-getter|2|
        return expensesummaryCommand;
    }
    //</editor-fold>//GEN-END:|79-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: aboutCommand ">//GEN-BEGIN:|82-getter|0|82-preInit
    /**
     * Returns an initiliazed instance of aboutCommand component.
     * @return the initialized component instance
     */
    public Command getAboutCommand() {
        if (aboutCommand == null) {//GEN-END:|82-getter|0|82-preInit
            // write pre-init user code here
            aboutCommand = new Command("About", Command.SCREEN, 0);//GEN-LINE:|82-getter|1|82-postInit
            // write post-init user code here
        }//GEN-BEGIN:|82-getter|2|
        return aboutCommand;
    }
    //</editor-fold>//GEN-END:|82-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: detailsExpensesCommand ">//GEN-BEGIN:|88-getter|0|88-preInit
    /**
     * Returns an initiliazed instance of detailsExpensesCommand component.
     * @return the initialized component instance
     */
    public Command getDetailsExpensesCommand() {
        if (detailsExpensesCommand == null) {//GEN-END:|88-getter|0|88-preInit
            // write pre-init user code here
            detailsExpensesCommand = new Command("Show Details", Command.SCREEN, 0);//GEN-LINE:|88-getter|1|88-postInit
            // write post-init user code here
        }//GEN-BEGIN:|88-getter|2|
        return detailsExpensesCommand;
    }
    //</editor-fold>//GEN-END:|88-getter|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: expSumList ">//GEN-BEGIN:|85-getter|0|85-preInit
    /**
     * Returns an initiliazed instance of expSumList component.
     * @return the initialized component instance
     */
    public List getExpSumList() {
        if (expSumList == null) {//GEN-END:|85-getter|0|85-preInit
            // write pre-init user code here
            expSumList = new List("Expense Summary", Choice.IMPLICIT);//GEN-BEGIN:|85-getter|1|85-postInit
            expSumList.addCommand(getDetailsExpensesCommand());
            expSumList.addCommand(getReturnCommand());
            expSumList.setCommandListener(this);//GEN-END:|85-getter|1|85-postInit
            // write post-init user code here
        }//GEN-BEGIN:|85-getter|2|
        return expSumList;
    }
    //</editor-fold>//GEN-END:|85-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: expSumListAction ">//GEN-BEGIN:|85-action|0|85-preAction
    /**
     * Performs an action assigned to the selected list element in the expSumList component.
     */
    public void expSumListAction() {//GEN-END:|85-action|0|85-preAction
        // enter pre-action user code here
        String __selectedString = getExpSumList().getString(getExpSumList().getSelectedIndex());//GEN-LINE:|85-action|1|85-postAction
        // enter post-action user code here
    }//GEN-BEGIN:|85-action|2|
    //</editor-fold>//GEN-END:|85-action|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: About ">//GEN-BEGIN:|91-getter|0|91-preInit
    /**
     * Returns an initiliazed instance of About component.
     * @return the initialized component instance
     */
    public Alert getAbout() {
        if (About == null) {//GEN-END:|91-getter|0|91-preInit
            // write pre-init user code here
            About = new Alert("About", "Developed by Renjith V [v.renjith@gmail.com]", null, AlertType.INFO);//GEN-BEGIN:|91-getter|1|91-postInit
            About.setTimeout(Alert.FOREVER);//GEN-END:|91-getter|1|91-postInit
            // write post-init user code here
        }//GEN-BEGIN:|91-getter|2|
        return About;
    }
    //</editor-fold>//GEN-END:|91-getter|2|



    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: returnCommand ">//GEN-BEGIN:|95-getter|0|95-preInit
    /**
     * Returns an initiliazed instance of returnCommand component.
     * @return the initialized component instance
     */
    public Command getReturnCommand() {
        if (returnCommand == null) {//GEN-END:|95-getter|0|95-preInit
            // write pre-init user code here
            returnCommand = new Command("Back", Command.BACK, 0);//GEN-LINE:|95-getter|1|95-postInit
            // write post-init user code here
        }//GEN-BEGIN:|95-getter|2|
        return returnCommand;
    }
    //</editor-fold>//GEN-END:|95-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: dateField ">//GEN-BEGIN:|104-getter|0|104-preInit
    /**
     * Returns an initiliazed instance of dateField component.
     * @return the initialized component instance
     */
    public DateField getDateField() {
        if (dateField == null) {//GEN-END:|104-getter|0|104-preInit
            // write pre-init user code here
            dateField = new DateField("Date", DateField.DATE);//GEN-BEGIN:|104-getter|1|104-postInit
            dateField.setDate(new java.util.Date(System.currentTimeMillis()));//GEN-END:|104-getter|1|104-postInit
            // write post-init user code here
        }//GEN-BEGIN:|104-getter|2|
        return dateField;
    }
    //</editor-fold>//GEN-END:|104-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: waitScreen ">//GEN-BEGIN:|106-getter|0|106-preInit
    /**
     * Returns an initiliazed instance of waitScreen component.
     * @return the initialized component instance
     */
    public WaitScreen getWaitScreen() {
        if (waitScreen == null) {//GEN-END:|106-getter|0|106-preInit
            // write pre-init user code here
            waitScreen = new WaitScreen(getDisplay());//GEN-BEGIN:|106-getter|1|106-postInit
            waitScreen.setTitle("Loading...");
            waitScreen.setCommandListener(this);
            waitScreen.setTask(getTask());//GEN-END:|106-getter|1|106-postInit
            // write post-init user code here
        }//GEN-BEGIN:|106-getter|2|
        return waitScreen;
    }
    //</editor-fold>//GEN-END:|106-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: task ">//GEN-BEGIN:|111-getter|0|111-preInit
    /**
     * Returns an initiliazed instance of task component.
     * @return the initialized component instance
     */
    public SimpleCancellableTask getTask() {
        if (task == null) {//GEN-END:|111-getter|0|111-preInit
            // write pre-init user code here
            task = new SimpleCancellableTask();//GEN-BEGIN:|111-getter|1|111-execute
            task.setExecutable(new org.netbeans.microedition.util.Executable() {
                public void execute() throws Exception {//GEN-END:|111-getter|1|111-execute
                    // write task-execution user code here
                }//GEN-BEGIN:|111-getter|2|111-postInit
            });//GEN-END:|111-getter|2|111-postInit
            // write post-init user code here
        }//GEN-BEGIN:|111-getter|3|
        return task;
    }
    //</editor-fold>//GEN-END:|111-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: expenseDetailsCommand ">//GEN-BEGIN:|112-getter|0|112-preInit
    /**
     * Returns an initiliazed instance of expenseDetailsCommand component.
     * @return the initialized component instance
     */
    public Command getExpenseDetailsCommand() {
        if (expenseDetailsCommand == null) {//GEN-END:|112-getter|0|112-preInit
            // write pre-init user code here
            expenseDetailsCommand = new Command("Expense Details", Command.SCREEN, 0);//GEN-LINE:|112-getter|1|112-postInit
            // write post-init user code here
        }//GEN-BEGIN:|112-getter|2|
        return expenseDetailsCommand;
    }
    //</editor-fold>//GEN-END:|112-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: deleteExpense ">//GEN-BEGIN:|117-getter|0|117-preInit
    /**
     * Returns an initiliazed instance of deleteExpense component.
     * @return the initialized component instance
     */
    public Command getDeleteExpense() {
        if (deleteExpense == null) {//GEN-END:|117-getter|0|117-preInit
            // write pre-init user code here
            deleteExpense = new Command("Delete Expense", Command.SCREEN, 0);//GEN-LINE:|117-getter|1|117-postInit
            // write post-init user code here
        }//GEN-BEGIN:|117-getter|2|
        return deleteExpense;
    }
    //</editor-fold>//GEN-END:|117-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: image ">//GEN-BEGIN:|120-getter|0|120-preInit
    /**
     * Returns an initiliazed instance of image component.
     * @return the initialized component instance
     */
    public Image getImage() {
        if (image == null) {//GEN-END:|120-getter|0|120-preInit
            // write pre-init user code here
            try {//GEN-BEGIN:|120-getter|1|120-@java.io.IOException
                image = Image.createImage("/images/travel.png");
            } catch (java.io.IOException e) {//GEN-END:|120-getter|1|120-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|120-getter|2|120-postInit
            // write post-init user code here
        }//GEN-BEGIN:|120-getter|3|
        return image;
    }
    //</editor-fold>//GEN-END:|120-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: image1 ">//GEN-BEGIN:|121-getter|0|121-preInit
    /**
     * Returns an initiliazed instance of image1 component.
     * @return the initialized component instance
     */
    public Image getImage1() {
        if (image1 == null) {//GEN-END:|121-getter|0|121-preInit
            // write pre-init user code here
            try {//GEN-BEGIN:|121-getter|1|121-@java.io.IOException
                image1 = Image.createImage("/images/groceries.png");
            } catch (java.io.IOException e) {//GEN-END:|121-getter|1|121-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|121-getter|2|121-postInit
            // write post-init user code here
        }//GEN-BEGIN:|121-getter|3|
        return image1;
    }
    //</editor-fold>//GEN-END:|121-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: image2 ">//GEN-BEGIN:|122-getter|0|122-preInit
    /**
     * Returns an initiliazed instance of image2 component.
     * @return the initialized component instance
     */
    public Image getImage2() {
        if (image2 == null) {//GEN-END:|122-getter|0|122-preInit
            // write pre-init user code here
            try {//GEN-BEGIN:|122-getter|1|122-@java.io.IOException
                image2 = Image.createImage("/images/vegetable1.png");
            } catch (java.io.IOException e) {//GEN-END:|122-getter|1|122-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|122-getter|2|122-postInit
            // write post-init user code here
        }//GEN-BEGIN:|122-getter|3|
        return image2;
    }
    //</editor-fold>//GEN-END:|122-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: image3 ">//GEN-BEGIN:|123-getter|0|123-preInit
    /**
     * Returns an initiliazed instance of image3 component.
     * @return the initialized component instance
     */
    public Image getImage3() {
        if (image3 == null) {//GEN-END:|123-getter|0|123-preInit
            // write pre-init user code here
            try {//GEN-BEGIN:|123-getter|1|123-@java.io.IOException
                image3 = Image.createImage("/images/apparels.png");
            } catch (java.io.IOException e) {//GEN-END:|123-getter|1|123-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|123-getter|2|123-postInit
            // write post-init user code here
        }//GEN-BEGIN:|123-getter|3|
        return image3;
    }
    //</editor-fold>//GEN-END:|123-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: image4 ">//GEN-BEGIN:|124-getter|0|124-preInit
    /**
     * Returns an initiliazed instance of image4 component.
     * @return the initialized component instance
     */
    public Image getImage4() {
        if (image4 == null) {//GEN-END:|124-getter|0|124-preInit
            // write pre-init user code here
            try {//GEN-BEGIN:|124-getter|1|124-@java.io.IOException
                image4 = Image.createImage("/images/eatout.png");
            } catch (java.io.IOException e) {//GEN-END:|124-getter|1|124-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|124-getter|2|124-postInit
            // write post-init user code here
        }//GEN-BEGIN:|124-getter|3|
        return image4;
    }
    //</editor-fold>//GEN-END:|124-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: image5 ">//GEN-BEGIN:|125-getter|0|125-preInit
    /**
     * Returns an initiliazed instance of image5 component.
     * @return the initialized component instance
     */
    public Image getImage5() {
        if (image5 == null) {//GEN-END:|125-getter|0|125-preInit
            // write pre-init user code here
            try {//GEN-BEGIN:|125-getter|1|125-@java.io.IOException
                image5 = Image.createImage("/images/medical1.png");
            } catch (java.io.IOException e) {//GEN-END:|125-getter|1|125-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|125-getter|2|125-postInit
            // write post-init user code here
        }//GEN-BEGIN:|125-getter|3|
        return image5;
    }
    //</editor-fold>//GEN-END:|125-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: image6 ">//GEN-BEGIN:|126-getter|0|126-preInit
    /**
     * Returns an initiliazed instance of image6 component.
     * @return the initialized component instance
     */
    public Image getImage6() {
        if (image6 == null) {//GEN-END:|126-getter|0|126-preInit
            // write pre-init user code here
            try {//GEN-BEGIN:|126-getter|1|126-@java.io.IOException
                image6 = Image.createImage("/images/electricity.png");
            } catch (java.io.IOException e) {//GEN-END:|126-getter|1|126-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|126-getter|2|126-postInit
            // write post-init user code here
        }//GEN-BEGIN:|126-getter|3|
        return image6;
    }
    //</editor-fold>//GEN-END:|126-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: image7 ">//GEN-BEGIN:|127-getter|0|127-preInit
    /**
     * Returns an initiliazed instance of image7 component.
     * @return the initialized component instance
     */
    public Image getImage7() {
        if (image7 == null) {//GEN-END:|127-getter|0|127-preInit
            // write pre-init user code here
            try {//GEN-BEGIN:|127-getter|1|127-@java.io.IOException
                image7 = Image.createImage("/images/child.png");
            } catch (java.io.IOException e) {//GEN-END:|127-getter|1|127-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|127-getter|2|127-postInit
            // write post-init user code here
        }//GEN-BEGIN:|127-getter|3|
        return image7;
    }
    //</editor-fold>//GEN-END:|127-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: image8 ">//GEN-BEGIN:|128-getter|0|128-preInit
    /**
     * Returns an initiliazed instance of image8 component.
     * @return the initialized component instance
     */
    public Image getImage8() {
        if (image8 == null) {//GEN-END:|128-getter|0|128-preInit
            // write pre-init user code here
            try {//GEN-BEGIN:|128-getter|1|128-@java.io.IOException
                image8 = Image.createImage("/images/car.png");
            } catch (java.io.IOException e) {//GEN-END:|128-getter|1|128-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|128-getter|2|128-postInit
            // write post-init user code here
        }//GEN-BEGIN:|128-getter|3|
        return image8;
    }
    //</editor-fold>//GEN-END:|128-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: image9 ">//GEN-BEGIN:|129-getter|0|129-preInit
    /**
     * Returns an initiliazed instance of image9 component.
     * @return the initialized component instance
     */
    public Image getImage9() {
        if (image9 == null) {//GEN-END:|129-getter|0|129-preInit
            // write pre-init user code here
            try {//GEN-BEGIN:|129-getter|1|129-@java.io.IOException
                image9 = Image.createImage("/images/home.png");
            } catch (java.io.IOException e) {//GEN-END:|129-getter|1|129-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|129-getter|2|129-postInit
            // write post-init user code here
        }//GEN-BEGIN:|129-getter|3|
        return image9;
    }
    //</editor-fold>//GEN-END:|129-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: image10 ">//GEN-BEGIN:|130-getter|0|130-preInit
    /**
     * Returns an initiliazed instance of image10 component.
     * @return the initialized component instance
     */
    public Image getImage10() {
        if (image10 == null) {//GEN-END:|130-getter|0|130-preInit
            // write pre-init user code here
            try {//GEN-BEGIN:|130-getter|1|130-@java.io.IOException
                image10 = Image.createImage("/images/loan.png");
            } catch (java.io.IOException e) {//GEN-END:|130-getter|1|130-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|130-getter|2|130-postInit
            // write post-init user code here
        }//GEN-BEGIN:|130-getter|3|
        return image10;
    }
    //</editor-fold>//GEN-END:|130-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: image11 ">//GEN-BEGIN:|131-getter|0|131-preInit
    /**
     * Returns an initiliazed instance of image11 component.
     * @return the initialized component instance
     */
    public Image getImage11() {
        if (image11 == null) {//GEN-END:|131-getter|0|131-preInit
            // write pre-init user code here
            try {//GEN-BEGIN:|131-getter|1|131-@java.io.IOException
                image11 = Image.createImage("/images/investment.png");
            } catch (java.io.IOException e) {//GEN-END:|131-getter|1|131-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|131-getter|2|131-postInit
            // write post-init user code here
        }//GEN-BEGIN:|131-getter|3|
        return image11;
    }
    //</editor-fold>//GEN-END:|131-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: image12 ">//GEN-BEGIN:|132-getter|0|132-preInit
    /**
     * Returns an initiliazed instance of image12 component.
     * @return the initialized component instance
     */
    public Image getImage12() {
        if (image12 == null) {//GEN-END:|132-getter|0|132-preInit
            // write pre-init user code here
            try {//GEN-BEGIN:|132-getter|1|132-@java.io.IOException
                image12 = Image.createImage("/images/movie.png");
            } catch (java.io.IOException e) {//GEN-END:|132-getter|1|132-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|132-getter|2|132-postInit
            // write post-init user code here
        }//GEN-BEGIN:|132-getter|3|
        return image12;
    }
    //</editor-fold>//GEN-END:|132-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: image13 ">//GEN-BEGIN:|133-getter|0|133-preInit
    /**
     * Returns an initiliazed instance of image13 component.
     * @return the initialized component instance
     */
    public Image getImage13() {
        if (image13 == null) {//GEN-END:|133-getter|0|133-preInit
            // write pre-init user code here
            try {//GEN-BEGIN:|133-getter|1|133-@java.io.IOException
                image13 = Image.createImage("/images/church.png");
            } catch (java.io.IOException e) {//GEN-END:|133-getter|1|133-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|133-getter|2|133-postInit
            // write post-init user code here
        }//GEN-BEGIN:|133-getter|3|
        return image13;
    }
    //</editor-fold>//GEN-END:|133-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: image14 ">//GEN-BEGIN:|134-getter|0|134-preInit
    /**
     * Returns an initiliazed instance of image14 component.
     * @return the initialized component instance
     */
    public Image getImage14() {
        if (image14 == null) {//GEN-END:|134-getter|0|134-preInit
            // write pre-init user code here
            try {//GEN-BEGIN:|134-getter|1|134-@java.io.IOException
                image14 = Image.createImage("/images/other.png");
            } catch (java.io.IOException e) {//GEN-END:|134-getter|1|134-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|134-getter|2|134-postInit
            // write post-init user code here
        }//GEN-BEGIN:|134-getter|3|
        return image14;
    }
    //</editor-fold>//GEN-END:|134-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: image15 ">//GEN-BEGIN:|135-getter|0|135-preInit
    /**
     * Returns an initiliazed instance of image15 component.
     * @return the initialized component instance
     */
    public Image getImage15() {
        if (image15 == null) {//GEN-END:|135-getter|0|135-preInit
            // write pre-init user code here
            try {//GEN-BEGIN:|135-getter|1|135-@java.io.IOException
                image15 = Image.createImage("/images/petrol.png");
            } catch (java.io.IOException e) {//GEN-END:|135-getter|1|135-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|135-getter|2|135-postInit
            // write post-init user code here
        }//GEN-BEGIN:|135-getter|3|
        return image15;
    }
    //</editor-fold>//GEN-END:|135-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: Delete ">//GEN-BEGIN:|136-getter|0|136-preInit
    /**
     * Returns an initiliazed instance of Delete component.
     * @return the initialized component instance
     */
    public Alert getDelete() {
        if (Delete == null) {//GEN-END:|136-getter|0|136-preInit
            // write pre-init user code here
            Delete = new Alert("Confirm Delete", "Delete the expense?", getImage16(), AlertType.CONFIRMATION);//GEN-BEGIN:|136-getter|1|136-postInit
            Delete.addCommand(getDeleteCommand());
            Delete.addCommand(getBackCommand());
            Delete.setCommandListener(this);
            Delete.setTimeout(Alert.FOREVER);//GEN-END:|136-getter|1|136-postInit
            // write post-init user code here
        }//GEN-BEGIN:|136-getter|2|
        return Delete;
    }
    //</editor-fold>//GEN-END:|136-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: image16 ">//GEN-BEGIN:|137-getter|0|137-preInit
    /**
     * Returns an initiliazed instance of image16 component.
     * @return the initialized component instance
     */
    public Image getImage16() {
        if (image16 == null) {//GEN-END:|137-getter|0|137-preInit
            // write pre-init user code here
            try {//GEN-BEGIN:|137-getter|1|137-@java.io.IOException
                image16 = Image.createImage("/images/delete.png");
            } catch (java.io.IOException e) {//GEN-END:|137-getter|1|137-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|137-getter|2|137-postInit
            // write post-init user code here
        }//GEN-BEGIN:|137-getter|3|
        return image16;
    }
    //</editor-fold>//GEN-END:|137-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: deleteCommand ">//GEN-BEGIN:|141-getter|0|141-preInit
    /**
     * Returns an initiliazed instance of deleteCommand component.
     * @return the initialized component instance
     */
    public Command getDeleteCommand() {
        if (deleteCommand == null) {//GEN-END:|141-getter|0|141-preInit
            // write pre-init user code here
            deleteCommand = new Command("Delete", Command.OK, 0);//GEN-LINE:|141-getter|1|141-postInit
            // write post-init user code here
        }//GEN-BEGIN:|141-getter|2|
        return deleteCommand;
    }
    //</editor-fold>//GEN-END:|141-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand ">//GEN-BEGIN:|143-getter|0|143-preInit
    /**
     * Returns an initiliazed instance of backCommand component.
     * @return the initialized component instance
     */
    public Command getBackCommand() {
        if (backCommand == null) {//GEN-END:|143-getter|0|143-preInit
            // write pre-init user code here
            backCommand = new Command("Back", Command.BACK, 0);//GEN-LINE:|143-getter|1|143-postInit
            // write post-init user code here
        }//GEN-BEGIN:|143-getter|2|
        return backCommand;
    }
    //</editor-fold>//GEN-END:|143-getter|2|



    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: checkUpdateCommand ">//GEN-BEGIN:|148-getter|0|148-preInit
    /**
     * Returns an initiliazed instance of checkUpdateCommand component.
     * @return the initialized component instance
     */
    public Command getCheckUpdateCommand() {
        if (checkUpdateCommand == null) {//GEN-END:|148-getter|0|148-preInit
            // write pre-init user code here
            checkUpdateCommand = new Command("Check for update", Command.SCREEN, 0);//GEN-LINE:|148-getter|1|148-postInit
            // write post-init user code here
        }//GEN-BEGIN:|148-getter|2|
        return checkUpdateCommand;
    }
    //</editor-fold>//GEN-END:|148-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: reportBugsCommand ">//GEN-BEGIN:|151-getter|0|151-preInit
    /**
     * Returns an initiliazed instance of reportBugsCommand component.
     * @return the initialized component instance
     */
    public Command getReportBugsCommand() {
        if (reportBugsCommand == null) {//GEN-END:|151-getter|0|151-preInit
            // write pre-init user code here
            reportBugsCommand = new Command("Ok", Command.OK, 0);//GEN-LINE:|151-getter|1|151-postInit
            // write post-init user code here
        }//GEN-BEGIN:|151-getter|2|
        return reportBugsCommand;
    }
    //</editor-fold>//GEN-END:|151-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitWOReportCommand ">//GEN-BEGIN:|153-getter|0|153-preInit
    /**
     * Returns an initiliazed instance of exitWOReportCommand component.
     * @return the initialized component instance
     */
    public Command getExitWOReportCommand() {
        if (exitWOReportCommand == null) {//GEN-END:|153-getter|0|153-preInit
            // write pre-init user code here
            exitWOReportCommand = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|153-getter|1|153-postInit
            // write post-init user code here
        }//GEN-BEGIN:|153-getter|2|
        return exitWOReportCommand;
    }
    //</editor-fold>//GEN-END:|153-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: ReportBugs ">//GEN-BEGIN:|150-getter|0|150-preInit
    /**
     * Returns an initiliazed instance of ReportBugs component.
     * @return the initialized component instance
     */
    public Alert getReportBugs() {
        if (ReportBugs == null) {//GEN-END:|150-getter|0|150-preInit
            // write pre-init user code here
            ReportBugs = new Alert("Report Bugs", "There we some exceptions during the program execution. Do you like to report them to improve the program quality?", getImage17(), AlertType.CONFIRMATION);//GEN-BEGIN:|150-getter|1|150-postInit
            ReportBugs.addCommand(getReportBugsCommand());
            ReportBugs.addCommand(getExitWOReportCommand());
            ReportBugs.setCommandListener(this);
            ReportBugs.setTimeout(Alert.FOREVER);//GEN-END:|150-getter|1|150-postInit
            // write post-init user code here
        }//GEN-BEGIN:|150-getter|2|
        return ReportBugs;
    }
    //</editor-fold>//GEN-END:|150-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: image17 ">//GEN-BEGIN:|157-getter|0|157-preInit
    /**
     * Returns an initiliazed instance of image17 component.
     * @return the initialized component instance
     */
    public Image getImage17() {
        if (image17 == null) {//GEN-END:|157-getter|0|157-preInit
            // write pre-init user code here
            try {//GEN-BEGIN:|157-getter|1|157-@java.io.IOException
                image17 = Image.createImage("/images/bug.png");
            } catch (java.io.IOException e) {//GEN-END:|157-getter|1|157-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|157-getter|2|157-postInit
            // write post-init user code here
        }//GEN-BEGIN:|157-getter|3|
        return image17;
    }
    //</editor-fold>//GEN-END:|157-getter|3|







    /**
     * Returns a display instance.
     * @return the display instance.
     */
    public Display getDisplay () {
        return Display.getDisplay(this);
    }

    /**
     * Exits MIDlet.
     */
    public void exitMIDlet() {
        switchDisplayable (null, null);
        destroyApp(true);
        notifyDestroyed();
    }

    /**
     * Called when MIDlet is started.
     * Checks whether the MIDlet have been already started and initialize/starts or resumes the MIDlet.
     */
    public void startApp() {
        if (midletPaused) {
            resumeMIDlet ();
        } else {
            initialize ();
            startMIDlet ();
        }
        midletPaused = false;
    }

    /**
     * Called when MIDlet is paused.
     */
    public void pauseApp() {
        midletPaused = true;
    }

    /**
     * Called to signal the MIDlet to terminate.
     * @param unconditional if true, then the MIDlet has to be unconditionally terminated and all resources has to be released.
     */
    public void destroyApp(boolean unconditional) {
    }

    public void itemStateChanged(Item item) {
        if(item == amount)
        {
            dateField.setDate(new Date());
        }
    }

}
