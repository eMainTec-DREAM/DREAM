package dream.pers.mamylink.dao.oraImpl;

import java.util.List;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.pers.mamylink.dao.MaMyLinkDAO;
import dream.pers.mamylink.dto.MaMyLinkDTO;

/**
 * 권한그룹 - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="maMyLinkDAOTarget"
 * @spring.txbn id="maMyLinkDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaMyLinkDAOOraImpl extends BaseJdbcDaoSupportOra implements MaMyLinkDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maMyLinkDTO
     * @param admin 
     * @return List
     */
    public List findUsrGrpAuthList(MaMyLinkDTO maMyLinkDTO)
    { 
        QueryBuffer query = new QueryBuffer(); 

        query.append("SELECT	");
        query.append("b.*,		");
        query.append(" MIN(b.lvl) OVER() AS MINLVL");
        query.append("FROM		");
        query.append("(			");
        query.append("SELECT	");
        query.append("x.menu_id as ID,	");
        query.append("x.menu_id menuId,	");
        query.append("x.p_menu_id pMenuId");

        query.append(",      (SELECT a.key_name                             ");
        query.append("       FROM   TALANG a                                ");
        query.append("       WHERE  a.key_no = x.key_no                     ");
        query.append("         AND  a.key_type ='MENU'                      ");
        query.append("         AND  a.lang = '"+maMyLinkDTO.getUserLang()+"') description,");
        
        query.append("      NVL( ( SELECT '1' FROM TALINKEDMENU y                ");
        query.append("            WHERE  y.menu_id = '"+maMyLinkDTO.getMenuId()+"'            ");
        query.append("            AND y.linked_menu_id=x.menu_id");
        query.append("              AND  y.comp_no ='"+maMyLinkDTO.getCompNo()+"'              ");
        query.append("              AND  y.user_id ='"+maMyLinkDTO.getUserId()+"'), '') auth , ");

        query.append("         x.ord_no ordNo,");
        query.append("         level lvl");
        query.append("FROM  TAMENU x, TAPAGE y                              ");
        query.append("WHERE x.page_id = y.page_id(+)                        ");
        query.append(" AND x.is_system = 'N'            	");
        query.append("    AND x.is_use = 'Y'              	");
        query.append("    AND x.menu_id IN(SELECT z.menu_id FROM TAUSRGRPMENU z where z.comp_no='"+maMyLinkDTO.getCompNo()+"' and z.usrgrp_id='"+maMyLinkDTO.getUsrGrpId()+"')");

        query.append("START WITH p_menu_id = '0'                            ");
        query.append("CONNECT BY PRIOR menu_id = p_menu_id ");
        query.append("order siblings by x.ord_no");
        query.append(" )b");
        query.append(" order by lvl asc, ordno asc");

        return getJdbcTemplate().queryForList(query.toString());
    } 



    public int insertMenuAuth(String menu_id, MaMyLinkDTO maMyLinkDTO)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("INSERT INTO TALINKEDMENU			    ");
    	query.append("	(linkedmenu_id,	comp_no,			");
    	query.append("	 user_id,		menu_id,		    ");
    	query.append("	 linked_menu_id,   ord_no           ");
    	query.append("	)	VALUES							");
    	query.append("	(SQALINKEDMENU_ID.NEXTVAL,	?,		    ");
    	query.append("	 ?,				?,					");
    	query.append("   ?,( select MAX(ord_no)+10 as ordNo from TAUSRMENU where comp_no=? and user_id=?)        ");
    	query.append("	)									");
    	
    	Object[] objects = new Object[] {
    			maMyLinkDTO.getCompNo(),
    			maMyLinkDTO.getUserId(),
    			maMyLinkDTO.getMenuId(),
    			menu_id,
    			maMyLinkDTO.getCompNo(),
    			maMyLinkDTO.getUserId()
    	};
        
        return getJdbcTemplate().update(query.toString(), objects);
        
    }

    public int deleteMenuAuth(String menu_id, MaMyLinkDTO maMyLinkDTO)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("DELETE FROM TALINKEDMENU                                     	");
        query.append("WHERE  user_id   = '"+maMyLinkDTO.getUserId()+"'     			");
        query.append("  AND  menu_id   = '"+maMyLinkDTO.getMenuId()+"'          	");
        query.append("  AND  linked_menu_id =  '"+menu_id+"'                      	");
        return this.getJdbcTemplate().update(query.toString());
    }

}