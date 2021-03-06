package dream.pers.priv.pgm.dao.sqlImpl;

import java.util.List;

import com.fins.org.json.JSONObject;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.pers.priv.pgm.dao.PersPrivUsrFieldListDAO;
import dream.pers.priv.pgm.dto.PersPrivUsrFieldCommonDTO;

/**
 * 화면별 필드 목록 dao
 * @author  kim21017
 * @version $Id: MaPgUsrFieldListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="persPrivUsrFieldListDAOTarget"
 * @spring.txbn id="persPrivUsrFieldListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class PersPrivUsrFieldListDAOSqlImpl extends BaseJdbcDaoSupportSql implements PersPrivUsrFieldListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaPgUsrFieldListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngCommonDTO
     * @param maPgUsrFieldListDTO
     * @return List
     */
    public List findFieldList(PersPrivUsrFieldCommonDTO persPrivUsrFieldCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                            ");
        query.append("       '' seqNo,                                                  ");
        query.append("       x.pgfield_id pgFieldId,                                    ");
        query.append("       (SELECT key_name                                           ");
        query.append("        FROM TALANG                                               ");
        query.append("        WHERE key_no = ISNULL(y.key_no,x.key_no)                  ");
        query.append("          AND key_type = ISNULL(y.key_type, x.key_type)          	");
        query.append("          AND lang = '"+user.getLangId()+"'						");
        query.append("        ) keyName,        										");
        query.append("       x.description fieldDesc,       							");
        query.append("       ISNULL(y.display_yn, x.display_yn) displayYn,     			");
        query.append("		 dbo.SFACODE_TO_DESC(ISNULL(y.field_option, x.field_option),'FIELD_OPTION','SYS','','"+user.getLangId()+"')	fieldOptionDesc,");
        query.append("       x.field_id fieldId,                                        ");
        query.append("       x.readonly_yn readonlyYn,                                  ");
        query.append("       (SELECT key_name                                           ");
        query.append("        FROM TALANG                                               ");
        query.append("        WHERE key_no = ISNULL(y.group_key_no,x.group_key_no)      ");
        query.append("         AND key_type = ISNULL(y.group_key_type, x.group_key_type)");
        query.append("         AND lang = '"+user.getLangId()+"'						");
        query.append("        ) groupKeyName,        										");
        query.append("		 dbo.SFACODE_TO_DESC(ISNULL(y.group_option, x.group_option),'GROUP_OPTION','SYS','','"+user.getLangId()+"')	groupOptionDesc,");
        query.append("       ISNULL(y.ord_no, x.ord_no) ordNo                           ");
        query.append("       ,x.check_yn checkYn                                        ");
        query.append("FROM   TAPGFIELD x INNER JOIN TAPAGE d ON x.page_id = d.page_id   ");
        query.append("       INNER JOIN TAUSER z ON 1 = 1                               ");
        query.getAndNumKeyQuery("z.user_id", user.getUserId());
        query.append(" LEFT OUTER JOIN TAUSRPGFIELD y ON x.pgfield_id = y.pgfield_id AND z.user_id = y.user_id                                      ");
        query.append(" LEFT OUTER JOIN TAUGPGFIELDAU c ON c.usrgrp_id = z.usrgrp_id AND x.page_id = c.page_id AND c.field_id = x.field_id           ");
        query.append(" WHERE CASE c.auth_limit_type WHEN 'NV' THEN 'Y' ELSE  x.hidden_yn END  ='N'                                                  ");
        query.append(this.getWhere(persPrivUsrFieldCommonDTO));                     
        query.getOrderByQuery("x.pgfield_id","ISNULL(y.ord_no, x.ord_no)", persPrivUsrFieldCommonDTO.getOrderBy(), "".equals(persPrivUsrFieldCommonDTO.getDirection())?"des":persPrivUsrFieldCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(persPrivUsrFieldCommonDTO.getIsLoadMaxCount(), persPrivUsrFieldCommonDTO.getFirstRow()));
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaPgUsrFieldListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteFieldList(String id)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	String pgFieldId=id;
    	
    	query.append("DELETE FROM TAPGFIELD						");
    	query.append("WHERE  pgfield_id = '"+pgFieldId+"'		");
    	
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(PersPrivUsrFieldCommonDTO persPrivUsrFieldCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	if (!"".equals(persPrivUsrFieldCommonDTO.getPgFieldId()))
        {
            query.getAndQuery("x.pgfield_id", persPrivUsrFieldCommonDTO.getPgFieldId());
            return query.toString();
        }
    	
    	query.getAndQuery("d.file_name", persPrivUsrFieldCommonDTO.getPageId());
    	
    	return query.toString();
    }

    public void createField(JSONObject order, String fileName, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("DECLARE @t1 TABLE( 														");
        query.append("	fileName NVARCHAR(1000) 												");
        query.append("	,pageId NVARCHAR(1000) 													");
        query.append("	,fieldId NVARCHAR(1000) 												");
        query.append("	,keyNo NVARCHAR(1000) 													");
        query.append("	,keyType NVARCHAR(1000) 												");
        query.append("	,fieldLabel NVARCHAR(1000) 												");
        query.append("	,isCheck NVARCHAR(1000) 												");
        query.append("	,fieldOption NVARCHAR(1000) 											");
        query.append("	,readonlyYn NVARCHAR(1000) 												");
        query.append("	,ordNo NVARCHAR(1000) 													");
        query.append("  ,hiddenYn NVARCHAR(1000)                                                ");
        query.append(") 																		");
        query.append(" 																			");
        query.append("INSERT @t1 VALUES( 														");
        query.append("	? 																		");
        query.append("	,(SELECT c.page_id FROM TAPAGE c WHERE c.file_name = ? ) 				");
        query.append("	,? 																		");
        query.append("	,(SELECT TOP 1 c.key_no FROM TALANG c WHERE c.key_name = ?) 			");
        query.append("	,(SELECT TOP 1 c.key_type FROM TALANG c WHERE c.key_name = ?) 			");
        query.append("	,?,?,?,?,?,?															");
        query.append(") 																		");
        query.append(" 																			");
        query.append("IF EXISTS( 																");
        query.append("    SELECT 1 																");
        query.append("      FROM TAPGFIELD a JOIN @t1 b 										");
        query.append("        ON  a.page_id = b.pageId                                          ");
        query.append("       AND a.field_id = b.fieldId 										");
        query.append(") 																		");
        query.append("    BEGIN 																");
        query.append("        UPDATE TAPGFIELD SET                                              ");
        query.append("               check_yn = b.isCheck 										");
        query.append("        	FROM TAPGFIELD a join @t1 b 									");
        query.append("            ON a.page_id = b.pageId                                       ");
        query.append("           AND a.field_id = b.fieldId 									");
        query.append("    END 																	");
        query.append("ELSE 																		");
        query.append("    BEGIN 																");
        query.append("        INSERT INTO TAPGFIELD 	   										");
        query.append("        (pgfield_id,       page_id,       field_id,      					");
        query.append("          description,      hidden_yn,     ord_no,                        ");
        query.append("          display_yn,       key_type,      key_no,                        ");
        query.append("          check_yn,         readonly_yn,   field_option                   ");
        query.append("		  , file_name		                								");
        query.append("         )                                                                ");
        query.append("        SELECT  															");
        query.append("            NEXT VALUE FOR SQAPGFIELD_ID,     b.pageId,       b.fieldId,	");
        query.append("            b.fieldLabel,        b.hiddenYn,         b.ordNo,             ");
        query.append("            'Y',          b.keyType,          b.keyNo,                    ");
        query.append("            b.isCheck,    b.readonlyYn,       b.fieldOption               ");
        query.append("		  , b.fileName		            									");
        query.append("         FROM @t1 b 														");
        query.append("    END 																	");

        Object[] objects = new Object[] {
                
                fileName,
                fileName,
                order.get("name"),
                order.get("label"),
                order.get("label"),
                order.get("label"),
                String.valueOf(order.get("check")).equals("true")?"Y":"N",
                order.get("option"),
                convertString(order.get("readonly")).indexOf("read")>0?"Y":"N",
                order.get("ordNo"),
                order.get("hiddenYn")
        };
        
        getJdbcTemplate().update(query.toString(), getObject(objects));
    }

    public int hideFieldList(String id, User user, boolean hide)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("MERGE INTO TAUSRPGFIELD a                     	");
        query.append("USING(    SELECT              					");
        query.append("                  x.ord_no ordNo, 				");
        query.append("                  x.key_type keyType, 			");
        query.append("                  x.key_no keyNo, 				");
        query.append("                  x.field_option fieldOption,     ");
        query.append("                  ? pgfieldId,    				");
        query.append("                  ? userId,   					");
        query.append("                  ? compNo,   					");
        query.append("                  ? displayYn     				");
        query.append("          FROM TAPGFIELD x    					");
        query.append("          WHERE x.pgfield_id = ?) b               ");
        query.append("ON(   a.pgfield_id = b.pgfieldId              	");
        query.append("  AND a.comp_no = b.compNo                		");
        query.append("  AND a.user_id = b.userId )              		");
        query.append("WHEN MATCHED THEN                         		");
        query.append("  UPDATE SET                              		");
        query.append("         a.display_yn = b.displayYn       		");
        query.append("WHEN NOT MATCHED THEN                     		");
        query.append("  INSERT (                                		");
        query.append("     usrpgfield_id,    pgfield_id,                ");
        query.append("     comp_no,          user_id,                   ");
        query.append("     ord_no,           display_yn,                ");
        query.append("     key_type,         key_no,                    ");
        query.append("     field_option                                 ");
        query.append("         )                                       	");
        query.append(" VALUES(  										");
        query.append("    NEXT VALUE FOR SQAUSRPGFIELD_ID, b.pgfieldId,	");
        query.append("    b.compNo, b.userId,   						");
        query.append("    b.ordNo, b.displayYn, 						");
        query.append("    b.keyType, b.keyNo,   						");
        query.append("    b.fieldOption 								");
        query.append(" );    											");


        
        Object[] objects = new Object[] {
                id,
                user.getUserId(),
                user.getCompNo(),
                hide?"N":"Y",
                id
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }

    @Override
    public String findTotalCount(PersPrivUsrFieldCommonDTO persPrivUsrFieldCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                            ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM   TAPGFIELD x INNER JOIN TAPAGE d ON x.page_id = d.page_id   ");
        query.append("       INNER JOIN TAUSER z ON 1 = 1                               ");
        query.getAndNumKeyQuery("z.user_id", user.getUserId());
        query.append(" LEFT OUTER JOIN TAUSRPGFIELD y ON x.pgfield_id = y.pgfield_id AND z.user_id = y.user_id                                      ");
        query.append(" LEFT OUTER JOIN TAUGPGFIELDAU c ON c.usrgrp_id = z.usrgrp_id AND x.page_id = c.page_id AND c.field_id = x.field_id           ");
        query.append(" WHERE CASE c.auth_limit_type WHEN 'NV' THEN 'Y' ELSE  x.hidden_yn END  ='N'                                                  ");
        query.append(this.getWhere(persPrivUsrFieldCommonDTO));                 
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
}