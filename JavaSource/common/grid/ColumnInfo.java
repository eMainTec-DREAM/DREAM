package common.grid;

/**
 * Column¿¡ type Ãß°¡
 * @author  javaworker
 * @version $Id: ColumnInfo.java,v 1.1 2013/08/30 09:12:56 javaworker Exp $
 * @since   1.0
 *
 */
public class ColumnInfo extends com.fins.gt.model.ColumnInfo 
{
    private String type;

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }
}