package dream.pers.mamymenu.dao.sqlImpl;

import java.util.List;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.pers.mamymenu.dao.MaMyMenuDAO;
import dream.pers.mamymenu.dto.MaMyMenuDTO;

/**
 * 사용자메뉴 - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="maMyMenuDAOTarget"
 * @spring.txbn id="maMyMenuDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaMyMenuDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaMyMenuDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maMyMenuDTO
     * @param admin 
     * @return List
     */
    public List findUsrGrpAuthList(MaMyMenuDTO maMyMenuDTO)
    { 
        QuerySqlBuffer query = new QuerySqlBuffer(); 

        query.append("SELECT																				");
        query.append("		b.*,																			");
        query.append(" 		MIN(b.LVL) OVER() AS MINLVL																	");
        query.append("  FROM																				");
        query.append("		(																				");
        query.append("		 SELECT	TOP 100 PERCENT															");
        query.append("				x.menu_id as ID,														");
        query.append("				x.menu_id MENUID,														");
        query.append("				x.p_menu_id PMENUID,													");
        query.append("				(SELECT a.key_name                             							");
        query.append("       		   FROM TALANG a                                						");
        query.append("         		  WHERE a.key_no = x.key_no                     						");
        query.append("         	 		AND  a.key_type ='MENU'                      						");
        query.append("         	 		AND  a.lang = '"+maMyMenuDTO.getUserLang()+"') DESCRIPTION,			");
        query.append("      		ISNULL( (SELECT '1' FROM TAUSRMENU y            						");
        query.append("            			  WHERE  y.menu_id = x.menu_id              					");
        query.append("              			AND  y.comp_no ='"+maMyMenuDTO.getCompNo()+"'				");
        query.append("              			AND  y.user_id ='"+maMyMenuDTO.getUserId()+"'), '') AUTH,	");
        query.append("         		x.ord_no ORDNO,															");
        query.append("         		z.lvl LVL																");
        query.append("		   FROM TAMENU x LEFT OUTER JOIN TAPAGE y                              			");
        query.append("			 ON x.page_id = y.page_id	                       							");
        query.append("				,(SELECT * FROM dbo.SFAMENU_ALL('0')) z	                       			");
        query.append("		  WHERE x.menu_id = z.menu_id                        							");
        query.append(" 			AND x.is_system = 'N'            											");
        query.append("    		AND x.is_use = 'Y'              											");
        query.append("    		AND x.menu_id IN(SELECT z.menu_id FROM TAUSRGRPMENU z where z.comp_no='"+maMyMenuDTO.getCompNo()+"' and z.usrgrp_id='"+maMyMenuDTO.getUsrGrpId()+"')");
        query.append("		  ORDER by ISNULL(x.ord_no, '99999999')											");
        query.append(" 		)b																				");
        query.append(" ORDER BY lvl asc, ordno asc															");



         
        return getJdbcTemplate().queryForList(query.toString());
    }     


    public int insertMenuAuth(String menu_id, MaMyMenuDTO maMyMenuDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("INSERT INTO TAUSRMENU			        ");
    	query.append("	(usrmenu_id,	comp_no,			");
    	query.append("	 user_id,		menu_id,		    ");
    	query.append("	 ord_no				                ");
    	query.append("	)	VALUES							");
    	query.append("	(NEXT VALUE FOR SQAUSRMENU_ID,	?,	");
    	query.append("	 ?,				?,					");
    	query.append("   ( select MAX(ord_no)+10 as ordNo from TAUSRMENU where comp_no=? and user_id=?)        ");
    	query.append("	)									");
    	
    	Object[] objects = new Object[] {
    			maMyMenuDTO.getCompNo(),
    			maMyMenuDTO.getUserId(),
    			menu_id,
    			maMyMenuDTO.getCompNo(),
    			maMyMenuDTO.getUserId()
    	};
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
        
    }

    public int deleteMenuAuth(String menu_id, MaMyMenuDTO maMyMenuDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("DELETE FROM TAUSRMENU                                     ");
        query.append("WHERE  user_id   = '"+maMyMenuDTO.getUserId()+"'     		");
        query.append("  AND  menu_id     = '"+menu_id+"'                        ");
        
        return this.getJdbcTemplate().update(query.toString());
    }

}