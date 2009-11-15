/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import javax.microedition.io.Connector;
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
public class TrackExpense extends MIDlet implements CommandListener {

    private boolean midletPaused = false;
    private String currFilter = "";
    private int[] currIndices = null;
    String deLimiter = "^";

    //<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private java.util.Hashtable __previousDisplayables = new java.util.Hashtable();
    private Command exitCommand;
    private Command addExpenseCommand;
    private Command okCommand;
    private Command cleanExpensesCommand;
    private Command expensesummaryCommand;
    private Command exportExpensesCommand;
    private Command aboutCommand;
    private Command returnCommand;
    private Command detailsExpensesCommand;
    private Command expenseDetailsCommand;
    private Command deleteExpense;
    private Form form;
    private TextField amount;
    private TextField details;
    private ChoiceGroup choiceGroup;
    private DateField dateField;
    private List detailExpList;
    private List expSumList;
    private Alert About;
    private WaitScreen waitScreen;
    private SimpleCancellableTask task;
    //</editor-fold>//GEN-END:|fields|0|

    private void showMsg(String msg,String title, AlertType type)
    {
        Alert a = new Alert(title, msg, null, type);
        a.setTimeout(Alert.FOREVER);
        Display.getDisplay(this).setCurrent(a);
    }
    /**
     * The HelloMIDlet constructor.
     */
    public TrackExpense() {
    }

    //<editor-fold defaultstate="collapsed" desc=" Generated Methods ">//GEN-BEGIN:|methods|0|
    /**
     * Switches a display to previous displayable of the current displayable.
     * The <code>display</code> instance is obtain from the <code>getDisplay</code> method.
     */
    private void switchToPreviousDisplayable() {
        Displayable __currentDisplayable = getDisplay().getCurrent();
        if (__currentDisplayable != null) {
            Displayable __nextDisplayable = (Displayable) __previousDisplayables.get(__currentDisplayable);
            if (__nextDisplayable != null) {
                switchDisplayable(null, __nextDisplayable);
            }
        }
    }
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
        Displayable __currentDisplayable = display.getCurrent();
        if (__currentDisplayable != null  &&  nextDisplayable != null) {
            __previousDisplayables.put(nextDisplayable, __currentDisplayable);
        }
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
        if (displayable == detailExpList) {//GEN-BEGIN:|7-commandAction|1|58-preAction
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|1|58-preAction
                // write pre-action user code here
                detailExpListAction();//GEN-LINE:|7-commandAction|2|58-postAction
                // write post-action user code here
            } else if (command == deleteExpense) {//GEN-LINE:|7-commandAction|3|118-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|4|118-postAction
                // write post-action user code here
                try {
                    RecordStore rs = RecordStore.openRecordStore("MyExpenses", false);
                    int nIndex = getDetailExpList().getSelectedIndex();
                    int nRecIndex = currIndices[nIndex];
                    rs.deleteRecord(nRecIndex);
                    rs.closeRecordStore();
                    fillExpensesDetails(currFilter);
                } catch (Exception ex) {
                    showMsg("Unable to delete", "Error",AlertType.ERROR);
                }
                fillExpensesDetails(currFilter);
            } else if (command == okCommand) {//GEN-LINE:|7-commandAction|5|61-preAction
                // write pre-action user code here
                switchToPreviousDisplayable();//GEN-LINE:|7-commandAction|6|61-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|7|86-preAction
        } else if (displayable == expSumList) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|7|86-preAction
                // write pre-action user code here
                expSumListAction();//GEN-LINE:|7-commandAction|8|86-postAction
                // write post-action user code here
            } else if (command == detailsExpensesCommand) {//GEN-LINE:|7-commandAction|9|89-preAction
                // write pre-action user code here
                switchDisplayable(null, getDetailExpList());//GEN-LINE:|7-commandAction|10|89-postAction
                // write post-action user code here
                String currEntry = getExpSumList().getString(getExpSumList().getSelectedIndex());
                String cols[] = split(currEntry, ":");
                fillExpensesDetails(cols[0]);
            } else if (command == returnCommand) {//GEN-LINE:|7-commandAction|11|96-preAction
                // write pre-action user code here
                switchDisplayable(null, getForm());//GEN-LINE:|7-commandAction|12|96-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|13|83-preAction
        } else if (displayable == form) {
            if (command == aboutCommand) {//GEN-END:|7-commandAction|13|83-preAction
                // write pre-action user code here
                switchDisplayable(null, getAbout());//GEN-LINE:|7-commandAction|14|83-postAction
                // write post-action user code here
            } else if (command == addExpenseCommand) {//GEN-LINE:|7-commandAction|15|27-preAction
                    // write pre-action user code here
//GEN-LINE:|7-commandAction|16|27-postAction
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
                    rse.printStackTrace();
                    showMsg("Can't write","Error", AlertType.ERROR);
                }
            } else if (command == cleanExpensesCommand) {//GEN-LINE:|7-commandAction|17|67-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|18|67-postAction
                // write post-action user code here
                if (RecordStore.listRecordStores() != null) {
                    try {
                        RecordStore.deleteRecordStore("MyExpenses");
                        showMsg("Cleaned datastore","Information", AlertType.INFO);
                    } catch (Exception e) {
                        showMsg("Can't delete the datastore","Error", AlertType.ERROR);
                    }
                }
            } else if (command == exitCommand) {//GEN-LINE:|7-commandAction|19|19-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|20|19-postAction
                // write post-action user code here
            } else if (command == expenseDetailsCommand) {//GEN-LINE:|7-commandAction|21|113-preAction
                // write pre-action user code here
                switchDisplayable(null, getDetailExpList());//GEN-LINE:|7-commandAction|22|113-postAction
                // write post-action user code here
                fillExpensesDetails("");
            } else if (command == expensesummaryCommand) {//GEN-LINE:|7-commandAction|23|80-preAction
                // write pre-action user code here
                switchDisplayable(null, getExpSumList());//GEN-LINE:|7-commandAction|24|80-postAction
                // write post-action user code here
                fillExpenseSummary();
            } else if (command == exportExpensesCommand) {//GEN-LINE:|7-commandAction|25|75-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|26|75-postAction
                // write post-action user code here
                RecordStore rs = null;
                try {
                    Date d = new Date();
                    Calendar c = Calendar.getInstance();
                    c.setTime(d);
                    String dt = c.get(Calendar.DAY_OF_MONTH) + "_" + (c.get(Calendar.MONTH) + 1) + "_" + c.get(Calendar.YEAR);
                    String fileName = "file:///e:/myexpenses_" + dt + ".csv";
                    FileConnection fc = (FileConnection) Connector.open(fileName, Connector.READ_WRITE);
                    if (!fc.exists()) {
                        fc.create();
                    } else {
                        fc.delete();
                        fc.create();
                    }
                    OutputStream is = fc.openOutputStream();

                    rs = RecordStore.openRecordStore("MyExpenses", false);
                    RecordEnumeration re = rs.enumerateRecords(null, null, false);

                    is.write(("Date,Amount,Description,Category\n").getBytes());
                    while (re.hasNextElement()) {
                        String data = new String(re.nextRecord());
                        is.write((data + "\n").getBytes());
                    }

                    is.close();
                    fc.close();
                    showMsg("Exported as " + fileName,"Information", AlertType.INFO);
                } catch (Exception e) {
                    showMsg("Failed to export","Error", AlertType.ERROR);
                }
                if (null != rs) {
                    try {
                        rs.closeRecordStore();
                    } catch (Exception e) {
                    }
                }
            }//GEN-BEGIN:|7-commandAction|27|110-preAction
        } else if (displayable == waitScreen) {
            if (command == WaitScreen.FAILURE_COMMAND) {//GEN-END:|7-commandAction|27|110-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|28|110-postAction
                // write post-action user code here
            } else if (command == WaitScreen.SUCCESS_COMMAND) {//GEN-LINE:|7-commandAction|29|109-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|30|109-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|31|7-postCommandAction
        }//GEN-END:|7-commandAction|31|7-postCommandAction
        // write post-action user code here
    }//GEN-BEGIN:|7-commandAction|32|
    //</editor-fold>//GEN-END:|7-commandAction|32|




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
            form.addCommand(getAboutCommand());
            form.setCommandListener(this);//GEN-END:|14-getter|1|14-postInit
            // write post-init user code here
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
            choiceGroup.append("Travel", null);
            choiceGroup.append("Petrol", null);
            choiceGroup.append("Groceries", null);
            choiceGroup.append("Vegetables", null);
            choiceGroup.append("Apparels", null);
            choiceGroup.append("Eat out", null);
            choiceGroup.append("Medical", null);
            choiceGroup.append("Phone/Water/Elec", null);
            choiceGroup.append("Child/School", null);
            choiceGroup.append("Vehicle", null);
            choiceGroup.append("Home Improvement", null);
            choiceGroup.append("Loan", null);
            choiceGroup.append("Investment", null);
            choiceGroup.append("Movie/Entertainment", null);
            choiceGroup.append("Other", null);
            choiceGroup.setSelectedFlags(new boolean[] { false, false, true, false, false, false, false, false, false, false, false, false, false, false, false });//GEN-END:|29-getter|1|29-postInit
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
                rs = RecordStore.openRecordStore("MyExpenses", false);
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
            showMsg("Can't open datastore", "Error", AlertType.ERROR);
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
                rs = RecordStore.openRecordStore("MyExpenses", false);
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
            getDetailExpList().setSelectedIndex(0, true);
            
        } catch (Exception e) {
            showMsg("Can't open datastore", "Error", AlertType.ERROR);
        }
        if (null != rs) {
            try {
                rs.closeRecordStore();
            } catch (Exception e) {
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

}
