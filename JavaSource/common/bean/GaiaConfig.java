package common.bean;

import java.util.Hashtable;

/**
 * @author  jung7126
 * @version $Id: MwareConfig.java,v 1.2 2015/01/09 00:16:44 pochul2423 Exp $
 * @since   1.0
 *
 */
public class GaiaConfig
{
    //==========================================================
    /** �ش� PAGE�� BUTTON */
    private static Hashtable buttonTable = null;
    /** Tab page */
    private static Hashtable tabPagesTable = null;
    /** Page */
    private static Hashtable pagesTable = null;
    /** MENU PATH */
    private static Hashtable menuPathTable = null;
    /** Page Field Setting */
    private static Hashtable pageFieldTable = null;
    //==========================================================
    
    public static Hashtable getButtonTable()
    {
        return buttonTable;
    }
    public static void setButtonTable(Hashtable buttonTable)
    {
        GaiaConfig.buttonTable = buttonTable;
    }
    public static Hashtable getTabPagesTable()
    {
        return tabPagesTable;
    }
    public static void setTabPagesTable(Hashtable tabPagesTable)
    {
        GaiaConfig.tabPagesTable = tabPagesTable;
    }
    public static Hashtable getPagesTable()
    {
        return pagesTable;
    }
    public static void setPagesTable(Hashtable pagesTable)
    {
        GaiaConfig.pagesTable = pagesTable;
    }
    public static Hashtable getMenuPathTable()
    {
        return menuPathTable;
    }
    public static void setMenuPathTable(Hashtable menuPathTable)
    {
        GaiaConfig.menuPathTable = menuPathTable;
    }
    public static Hashtable getPageFieldTable()
    {
        return pageFieldTable;
    }
    public static void setPageFieldTable(Hashtable pageFieldTable)
    {
        GaiaConfig.pageFieldTable = pageFieldTable;
    }

}