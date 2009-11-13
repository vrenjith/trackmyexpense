/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hello;

import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
import org.netbeans.microedition.lcdui.SimpleTableModel;

/**
 * @author Z000DG8C
 */
public class TrackExpense extends MIDlet implements CommandListener {

    private boolean midletPaused = false;

    //<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private java.util.Hashtable __previousDisplayables = new java.util.Hashtable();
    private Command exitCommand;
    private Command okCommand;
    private Command screenCommand;
    private Command screenCommand1;
    private Command okCommand1;
    private Command screenCommand2;
    private Command okCommand2;
    private Command screenCommand3;
    private Command screenCommand4;
    private Command screenCommand6;
    private Command screenCommand5;
    private Command screenCommand7;
    private Command screenCommand8;
    private Form form;
    private TextField amount;
    private TextField details;
    private ChoiceGroup choiceGroup;
    private List list;
    private SimpleTableModel simpleTableModel;
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
        // write post-action user code here
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
        if (displayable == form) {//GEN-BEGIN:|7-commandAction|1|19-preAction
            if (command == exitCommand) {//GEN-END:|7-commandAction|1|19-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|2|19-postAction
                // write post-action user code here
            } else if (command == okCommand) {//GEN-LINE:|7-commandAction|3|27-preAction
                    // write pre-action user code here
//GEN-LINE:|7-commandAction|4|27-postAction
                try
                {
                    if(0 != getAmount().getString().length())
                    {
                        Date d = new Date();
                        Calendar c = Calendar.getInstance();
                        c.setTime(d);
                        String dt = c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH)+1) + "/" + c.get(Calendar.YEAR);
                        String expense = dt + "," + getAmount().getString() + "," + getDetails().getString() + "," + choiceGroup.getString(choiceGroup.getSelectedIndex());
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
                    showMsg(rse.getMessage(),"Can't write", AlertType.ERROR);
                }
            } else if (command == screenCommand4) {//GEN-LINE:|7-commandAction|5|67-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|6|67-postAction
                // write post-action user code here
                if (RecordStore.listRecordStores() != null) {
                    try {
                        RecordStore.deleteRecordStore("MyExpenses");
                        showMsg("Cleaned datastore","Information", AlertType.INFO);
                    } catch (Exception e) {
                        showMsg(e.getMessage(),"Can't delete the datastore", AlertType.ERROR);
                    }
                }
            } else if (command == screenCommand5) {//GEN-LINE:|7-commandAction|7|75-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|8|75-postAction
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
                    showMsg("Failed to export, " + e.getMessage(),"Error", AlertType.ERROR);
                }
                if (null != rs) {
                    try {
                        rs.closeRecordStore();
                    } catch (Exception e) {
                    }
                }
            } else if (command == screenCommand7) {//GEN-LINE:|7-commandAction|9|80-preAction
                // write pre-action user code here
                switchDisplayable(null, getList());//GEN-LINE:|7-commandAction|10|80-postAction
                // write post-action user code here
                RecordStore rs = null;
                try {
                    list.deleteAll();
                    if (null == rs) {
                        rs = RecordStore.openRecordStore("MyExpenses", false);
                    }
                    RecordEnumeration re = rs.enumerateRecords(null, null, false);
                    String[] allElements= new String[200];

                    int i = 0;
                    while (re.hasNextElement()) {
                        allElements[i] = new String(re.nextRecord());
                        if(++i >= 200)
                        {
                            showMsg("Displaying the last 200 entries. Tip: Export & Clean!","Too many records", AlertType.WARNING);
                            break;
                        }
                    }
                    for (int j = i-1; j >= 0 ; j--){
                        list.append(allElements[j], null);
                    }

                } catch (Exception e) {
                    showMsg(e.getMessage(), "Can't open datastore",AlertType.ERROR);
                }
                if (null != rs) {
                    try {
                        rs.closeRecordStore();
                    } catch (Exception e) {
                    }
                }
            } else if (command == screenCommand8) {//GEN-LINE:|7-commandAction|11|83-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|12|83-postAction
                // write post-action user code here
                showMsg("Developed by Renjith V [v.renjith@gmail.com]", "About",AlertType.INFO);
            }//GEN-BEGIN:|7-commandAction|13|58-preAction
        } else if (displayable == list) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|13|58-preAction
                // write pre-action user code here
                listAction();//GEN-LINE:|7-commandAction|14|58-postAction
                // write post-action user code here
            } else if (command == okCommand2) {//GEN-LINE:|7-commandAction|15|61-preAction
                // write pre-action user code here
                switchToPreviousDisplayable();//GEN-LINE:|7-commandAction|16|61-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|17|7-postCommandAction
        }//GEN-END:|7-commandAction|17|7-postCommandAction
        // write post-action user code here
    }//GEN-BEGIN:|7-commandAction|18|
    //</editor-fold>//GEN-END:|7-commandAction|18|




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
            form = new Form("TrackExpense", new Item[] { getAmount(), getDetails(), getChoiceGroup() });//GEN-BEGIN:|14-getter|1|14-postInit
            form.addCommand(getExitCommand());
            form.addCommand(getOkCommand());
            form.addCommand(getScreenCommand4());
            form.addCommand(getScreenCommand5());
            form.addCommand(getScreenCommand7());
            form.addCommand(getScreenCommand8());
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

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand ">//GEN-BEGIN:|26-getter|0|26-preInit
    /**
     * Returns an initiliazed instance of okCommand component.
     * @return the initialized component instance
     */
    public Command getOkCommand() {
        if (okCommand == null) {//GEN-END:|26-getter|0|26-preInit
            // write pre-init user code here
            okCommand = new Command("Add Expense", Command.OK, 0);//GEN-LINE:|26-getter|1|26-postInit
            // write post-init user code here
        }//GEN-BEGIN:|26-getter|2|
        return okCommand;
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

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: screenCommand ">//GEN-BEGIN:|38-getter|0|38-preInit
    /**
     * Returns an initiliazed instance of screenCommand component.
     * @return the initialized component instance
     */
    public Command getScreenCommand() {
        if (screenCommand == null) {//GEN-END:|38-getter|0|38-preInit
            // write pre-init user code here
            screenCommand = new Command("Show Expenses", Command.SCREEN, 0);//GEN-LINE:|38-getter|1|38-postInit
            // write post-init user code here
        }//GEN-BEGIN:|38-getter|2|
        return screenCommand;
    }
    //</editor-fold>//GEN-END:|38-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: screenCommand1 ">//GEN-BEGIN:|40-getter|0|40-preInit
    /**
     * Returns an initiliazed instance of screenCommand1 component.
     * @return the initialized component instance
     */
    public Command getScreenCommand1() {
        if (screenCommand1 == null) {//GEN-END:|40-getter|0|40-preInit
            // write pre-init user code here
            screenCommand1 = new Command("Cleanup Expenses", Command.SCREEN, 0);//GEN-LINE:|40-getter|1|40-postInit
            // write post-init user code here
        }//GEN-BEGIN:|40-getter|2|
        return screenCommand1;
    }
    //</editor-fold>//GEN-END:|40-getter|2|





    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: simpleTableModel ">//GEN-BEGIN:|45-getter|0|45-preInit
    /**
     * Returns an initiliazed instance of simpleTableModel component.
     * @return the initialized component instance
     */
    public SimpleTableModel getSimpleTableModel() {
        if (simpleTableModel == null) {//GEN-END:|45-getter|0|45-preInit
            // write pre-init user code here
            simpleTableModel = new SimpleTableModel(new java.lang.String[][] {}, new java.lang.String[] { "Amount", "Description", "Category" });//GEN-LINE:|45-getter|1|45-postInit
            // write post-init user code here
        }//GEN-BEGIN:|45-getter|2|
        return simpleTableModel;
    }
    //</editor-fold>//GEN-END:|45-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand1 ">//GEN-BEGIN:|46-getter|0|46-preInit
    /**
     * Returns an initiliazed instance of okCommand1 component.
     * @return the initialized component instance
     */
    public Command getOkCommand1() {
        if (okCommand1 == null) {//GEN-END:|46-getter|0|46-preInit
            // write pre-init user code here
            okCommand1 = new Command("Ok", Command.OK, 0);//GEN-LINE:|46-getter|1|46-postInit
            // write post-init user code here
        }//GEN-BEGIN:|46-getter|2|
        return okCommand1;
    }
    //</editor-fold>//GEN-END:|46-getter|2|



    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: screenCommand2 ">//GEN-BEGIN:|53-getter|0|53-preInit
    /**
     * Returns an initiliazed instance of screenCommand2 component.
     * @return the initialized component instance
     */
    public Command getScreenCommand2() {
        if (screenCommand2 == null) {//GEN-END:|53-getter|0|53-preInit
            // write pre-init user code here
            screenCommand2 = new Command("Screen", Command.SCREEN, 0);//GEN-LINE:|53-getter|1|53-postInit
            // write post-init user code here
        }//GEN-BEGIN:|53-getter|2|
        return screenCommand2;
    }
    //</editor-fold>//GEN-END:|53-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand2 ">//GEN-BEGIN:|60-getter|0|60-preInit
    /**
     * Returns an initiliazed instance of okCommand2 component.
     * @return the initialized component instance
     */
    public Command getOkCommand2() {
        if (okCommand2 == null) {//GEN-END:|60-getter|0|60-preInit
            // write pre-init user code here
            okCommand2 = new Command("Ok", Command.OK, 0);//GEN-LINE:|60-getter|1|60-postInit
            // write post-init user code here
        }//GEN-BEGIN:|60-getter|2|
        return okCommand2;
    }
    //</editor-fold>//GEN-END:|60-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: list ">//GEN-BEGIN:|56-getter|0|56-preInit
    /**
     * Returns an initiliazed instance of list component.
     * @return the initialized component instance
     */
    public List getList() {
        if (list == null) {//GEN-END:|56-getter|0|56-preInit
            // write pre-init user code here
            list = new List("list", Choice.IMPLICIT);//GEN-BEGIN:|56-getter|1|56-postInit
            list.addCommand(getOkCommand2());
            list.setCommandListener(this);//GEN-END:|56-getter|1|56-postInit
            // write post-init user code here
        }//GEN-BEGIN:|56-getter|2|
        return list;
    }
    //</editor-fold>//GEN-END:|56-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: listAction ">//GEN-BEGIN:|56-action|0|56-preAction
    /**
     * Performs an action assigned to the selected list element in the list component.
     */
    public void listAction() {//GEN-END:|56-action|0|56-preAction
        // enter pre-action user code here
        String __selectedString = getList().getString(getList().getSelectedIndex());//GEN-LINE:|56-action|1|56-postAction
        // enter post-action user code here
    }//GEN-BEGIN:|56-action|2|
    //</editor-fold>//GEN-END:|56-action|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: screenCommand3 ">//GEN-BEGIN:|64-getter|0|64-preInit
    /**
     * Returns an initiliazed instance of screenCommand3 component.
     * @return the initialized component instance
     */
    public Command getScreenCommand3() {
        if (screenCommand3 == null) {//GEN-END:|64-getter|0|64-preInit
            // write pre-init user code here
            screenCommand3 = new Command("Clean Expenses", "<null>", Command.SCREEN, 0);//GEN-LINE:|64-getter|1|64-postInit
            // write post-init user code here
        }//GEN-BEGIN:|64-getter|2|
        return screenCommand3;
    }
    //</editor-fold>//GEN-END:|64-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: screenCommand4 ">//GEN-BEGIN:|66-getter|0|66-preInit
    /**
     * Returns an initiliazed instance of screenCommand4 component.
     * @return the initialized component instance
     */
    public Command getScreenCommand4() {
        if (screenCommand4 == null) {//GEN-END:|66-getter|0|66-preInit
            // write pre-init user code here
            screenCommand4 = new Command("Clean Expenses", "Clean Expenses", Command.SCREEN, 0);//GEN-LINE:|66-getter|1|66-postInit
            // write post-init user code here
        }//GEN-BEGIN:|66-getter|2|
        return screenCommand4;
    }
    //</editor-fold>//GEN-END:|66-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: screenCommand5 ">//GEN-BEGIN:|74-getter|0|74-preInit
    /**
     * Returns an initiliazed instance of screenCommand5 component.
     * @return the initialized component instance
     */
    public Command getScreenCommand5() {
        if (screenCommand5 == null) {//GEN-END:|74-getter|0|74-preInit
            // write pre-init user code here
            screenCommand5 = new Command("Export expenses", Command.SCREEN, 0);//GEN-LINE:|74-getter|1|74-postInit
            // write post-init user code here
        }//GEN-BEGIN:|74-getter|2|
        return screenCommand5;
    }
    //</editor-fold>//GEN-END:|74-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: screenCommand6 ">//GEN-BEGIN:|77-getter|0|77-preInit
    /**
     * Returns an initiliazed instance of screenCommand6 component.
     * @return the initialized component instance
     */
    public Command getScreenCommand6() {
        if (screenCommand6 == null) {//GEN-END:|77-getter|0|77-preInit
            // write pre-init user code here
            screenCommand6 = new Command("About", Command.SCREEN, 0);//GEN-LINE:|77-getter|1|77-postInit
            // write post-init user code here
        }//GEN-BEGIN:|77-getter|2|
        return screenCommand6;
    }
    //</editor-fold>//GEN-END:|77-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: screenCommand7 ">//GEN-BEGIN:|79-getter|0|79-preInit
    /**
     * Returns an initiliazed instance of screenCommand7 component.
     * @return the initialized component instance
     */
    public Command getScreenCommand7() {
        if (screenCommand7 == null) {//GEN-END:|79-getter|0|79-preInit
            // write pre-init user code here
            screenCommand7 = new Command("Show Expenses", Command.SCREEN, 0);//GEN-LINE:|79-getter|1|79-postInit
            // write post-init user code here
        }//GEN-BEGIN:|79-getter|2|
        return screenCommand7;
    }
    //</editor-fold>//GEN-END:|79-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: screenCommand8 ">//GEN-BEGIN:|82-getter|0|82-preInit
    /**
     * Returns an initiliazed instance of screenCommand8 component.
     * @return the initialized component instance
     */
    public Command getScreenCommand8() {
        if (screenCommand8 == null) {//GEN-END:|82-getter|0|82-preInit
            // write pre-init user code here
            screenCommand8 = new Command("About", Command.SCREEN, 0);//GEN-LINE:|82-getter|1|82-postInit
            // write post-init user code here
        }//GEN-BEGIN:|82-getter|2|
        return screenCommand8;
    }
    //</editor-fold>//GEN-END:|82-getter|2|





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
