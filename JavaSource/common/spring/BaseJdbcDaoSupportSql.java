package common.spring;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import common.bean.User;
import common.util.QuerySqlBuffer;

/**
 * JDBC DAO 공통 구현 
 * @author  javaworker
 * @version $Id: BaseJdbcDaoSupport.java,v 1.1 2013/08/30 09:13:56 javaworker Exp $
 * @since   1.0
 *
 */
public class BaseJdbcDaoSupportSql
        extends JdbcDaoSupport implements BaseJdbcDaoSupportIntf
{    
    /**
     * 입력 혹은 수정된 Row가 없다면 
     * Exception 을 발생시킨다.
     * @author  javaworker
     * @version $Id: BaseJdbcDaoSupport.java,v 1.1 2013/08/30 09:13:56 javaworker Exp $
     * @since   1.0
     * 
     * @param result
     * @return
     * @throws Exception
     */
    protected int checkUpdateResult(int result) throws Exception
    {
        if (result == 0)
        {
            throw new Exception("No Row Update!");
        }
        
        return result;
    }
    
    public String getNextSequence(String sequenceName)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT NEXT VALUE FOR " + sequenceName + "");
        
        return (int)getJdbcTemplate().queryForObject(query.toString(), Integer.class)+""; 
    }
    
    public List getHeader(User user, String page_id, String list_id)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                    ");
        query.append("        z.column_id ID,                                   ");
        query.append("        z.width,                                          ");
        query.append("        z.align,                                          ");
        query.append("        z.SORT,                                           ");
        query.append("        z.TYPE,                                           ");
        query.append("        z.hidden,                                         ");
        query.append("        CASE WHEN UPPER(z.column_id) = 'ISDELCHECK' THEN '#master_checkbox' ELSE      ");
        query.append("        (SELECT a.key_name                                ");
        query.append("         FROM   TALANG a                                  ");
        query.append("         WHERE  a.key_no = z.key_no                       ");
        query.append("           AND  a.lang  = '"+user.getLangId()+"'          ");
        query.append("           AND  a.key_type = z.key_type                   ");
        query.append("        ) END value,                                      ");
        query.append("        x.height                                          ");
        query.append("FROM TAPGGRID x ,TAPGGRIDCOL z                            ");
        query.append("WHERE x.pggrid_id = z.pggrid_id                           ");
        query.getAndQuery("(SELECT b.file_name FROM TAPAGE b WHERE x.page_id = b.page_id)", page_id);
        query.getAndQuery("x.grid_obj_id", list_id);
        query.append("ORDER BY ISNULL(ord_no, '99999999')                       ");

        return getJdbcTemplate().queryForList(query.toString());
    }
    
	public List getUserHeader(User user, String list_id, String currentPageId) 
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
        
		query.append("SELECT                                                      ");
		query.append("        x.column_id ID,                                     ");
		query.append("        ISNULL(y.width , x.width) WIDTH,                    ");
		query.append("        ISNULL(y.align, x.align) ALIGN,                     ");
		query.append("        x.SORT,                                             ");
		query.append("        x.TYPE,                                             ");
		query.append("        CASE w.auth_limit_type                                                                                          ");
        query.append("        WHEN 'NV' THEN 'TRUE'                                                                                           ");
        query.append("        ELSE                                                                                                            ");
        query.append("            CASE ISNULL(UPPER(y.hidden), UPPER(x.hidden)) WHEN 'FALSE' THEN                                             ");
        query.append("                CASE ISNULL(UPPER(y.display_yn), UPPER(x.display_yn)) WHEN 'Y' THEN 'false' WHEN 'N' THEN 'true' END    ");
        query.append("            WHEN 'N' THEN                                                                                               ");
        query.append("                CASE ISNULL(UPPER(y.display_yn), UPPER(x.display_yn)) WHEN 'Y' THEN 'false' WHEN 'N' THEN 'true' END    ");
        query.append("            WHEN 'TRUE' THEN 'true'                                                                                     ");
        query.append("            WHEN 'Y' THEN 'true' END                                                                                    ");
        query.append("        END HIDDEN,                                                                                                     ");
		query.append("        CASE WHEN upper(x.column_id) = 'ISDELCHECK' THEN '#master_checkbox' ELSE      ");
		query.append("        (SELECT a.key_name                                  ");
		query.append("         FROM   TALANG a WITH (NOLOCK)                      ");
		query.append("         WHERE  a.key_no = ISNULL(y.key_no,x.key_no)        ");
        query.append("           AND  a.lang  = '"+user.getLangId()+"'            ");
		query.append("           AND  a.key_type = ISNULL(y.key_type, x.key_type) ");
		query.append("        ) END VALUE,                                        ");
		query.append("        ISNULL(z.height, a.height) height                              ");
		query.append("FROM    TAPGGRID a INNER JOIN TAPGGRIDCOL x ON a.pggrid_id = x.pggrid_id                                                  ");
        query.append("            INNER JOIN TAPAGE c ON a.page_id = c.page_id                                                                  ");
        query.append("            LEFT OUTER JOIN TAUSRPGGRID z ON x.pggrid_id = z.pggrid_id                                                    ");
        query.getAndKeyQuery("z.user_id", user.getUserId());
        query.getAndQuery("z.comp_no", user.getCompNo());
        query.append("            LEFT OUTER JOIN TAUSRPGGRIDCOL y ON  x.pggridcol_id = y.pggridcol_id AND z.usrpggrid_id = y.usrpggrid_id      ");
        query.append("            LEFT OUTER JOIN TAUGPGRIDCOLAU w ON c.page_id = w.page_id AND w.grid_obj_id = a.grid_obj_id AND x.column_id = w.column_id   ");
        query.getAndKeyQuery("w.usrgrp_id", user.getUsrgrpId());
        query.append("            WHERE 1 = 1                                                                                                   ");
        query.getAndQuery("a.grid_obj_id", list_id);
        query.getAndQuery("c.file_name", currentPageId);
		query.append("    ORDER BY ISNULL(convert(decimal, y.ord_no), convert(decimal, x.ord_no)) 	   ");

        return getJdbcTemplate().queryForList(query.toString());
	}
	
	public String convertString(Object obj)
    {
        String result = String.valueOf(obj);
        if(result.indexOf("||") > -1) result = result.replaceAll("\\|\\|", "+");
        if(result == "null") result = "";
        
        return result;
    }
	
    public List getGaiaUserHeader(User user, String listId, String currentPageId)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                        ");
        query.append("        z.grid_col_name ID,                                   ");
        query.append("        z.width,                                              ");
        query.append("        z.align,                                              ");
        query.append("        z.SORT,                                               ");
        query.append("        z.TYPE,                                               ");
        query.append("        z.ishidden hidden,                                    ");
        query.append("        CASE WHEN UPPER(z.column_id) = 'ISDELCHECK' THEN '#master_checkbox' ELSE      ");
        query.append("        (SELECT a.key_name                                    ");
        query.append("         FROM   TSLANG a                                      ");
        query.append("         WHERE  a.key_no = z.key_no                           ");
        query.append("           AND  a.lang  = '"+user.getLangId()+"'              ");
        query.append("           AND  a.key_type = z.key_type                       ");
        query.append("        ) END VALUE,                                          ");
        query.append("        x.height                                              ");
        query.append("FROM TSFILEGRID x ,TSFILEGRIDCOL z                            ");
        query.append("WHERE x.sfilegrid_id = z.sfilegrid_id                         ");
        query.getAndQuery("(SELECT b.sfile_name FROM TSFILE b WHERE x.sfile_id = b.sfile_id)", currentPageId);
        query.getAndQuery("x.object_name", listId);
        query.append("ORDER BY col_seq                  ");

        return getJdbcTemplate().queryForList(query.toString());
    }
    
    public Object[] getObject(Object[] objects)
    {
    	for(int i=0; i<objects.length; i++)
    	{
    		if(objects[i]==null) continue;

    		objects[i] = getObject(objects[i]);
    	}
		return objects;
    	
    }
    
    public Object getObject(Object object)
    {
    	if(object.toString().equals(""))
    	{
    		object = null;
    	}
    	
		return object;
    }
}
