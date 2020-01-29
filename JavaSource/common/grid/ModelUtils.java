package common.grid;

import com.fins.org.json.JSONObject;

/**
 * column¿¡ type Ãß°¡
 * @author  javaworker
 * @version $Id: ModelUtils.java,v 1.1 2013/08/30 09:12:56 javaworker Exp $
 * @since   1.0
 *
 */
public class ModelUtils extends com.fins.gt.util.ModelUtils 
{
    public static ColumnInfo createColumnInfo(JSONObject modelJS)
    {
        ColumnInfo info=new ColumnInfo();
        info.setId(modelJS.getString("id"));
        info.setHeader(modelJS.getString("header"));
        info.setFieldIndex(modelJS.getString("fieldIndex"));
        info.setSortOrder(modelJS.getString("sortOrder"));

        info.setHidden(modelJS.getBoolean("hidden"));
        info.setExportable(modelJS.getBoolean("exportable"));
        info.setPrintable(modelJS.getBoolean("printable"));
        
        info.setPrintable(modelJS.getBoolean("printable"));

        info.setType(modelJS.getString("type"));
        
        return info;
    }
}
