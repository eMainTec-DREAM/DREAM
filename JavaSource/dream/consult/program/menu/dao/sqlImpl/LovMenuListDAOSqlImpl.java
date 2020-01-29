package dream.consult.program.menu.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.consult.program.menu.dao.LovMenuListDAO;
import dream.consult.program.menu.dto.LovMenuListDTO;

/**
 * ¸Þ´º ÆË¾÷
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="lovMenuListDAOTarget"
 * @spring.txbn id="lovMenuListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovMenuListDAOSqlImpl extends BaseJdbcDaoSupportSql implements LovMenuListDAO
{
    /**
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovMenuListDTO
     * @param loginUser
     * @return
     */
    public List findMenuList(LovMenuListDTO lovMenuListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String lang = loginUser.getLangId();
        
        query.append("SELECT															");
        query.append("		 b.*,														");
        query.append(" 		MIN(b.LVL) OVER() AS MINLVL												");
        query.append("FROM																");
        query.append("	  (																");
        query.append("	   SELECT														");
        query.append("			  x.menu_id AS ID										");
        query.append("			 ,x.menu_id AS MENUID									");
        query.append("			 ,x.p_menu_id AS PMENUID								");
        query.append("			 ,(SELECT a.key_name                             		");
        query.append("     		   FROM  TALANG a                                		");
        query.append("     		   WHERE a.key_no = x.key_no                     		");
        query.append("     		   AND   a.key_type ='MENU'                      		");
        query.append("     		   AND   a.lang = '"+lang+"'							");
        query.append("     		  ) AS DESCRIPTION										");
        query.append("         	 ,x.ord_no AS ORDNO										");
        query.append("         	 ,z.lvl AS LVL											");
        query.append("	   FROM  TAMENU x LEFT JOIN TAPAGE y ON x.page_id = y.page_id 	");
        query.append("     		 ,(SELECT * FROM dbo.SFAMENU_ALL('0')) z  				");
        query.append("	   WHERE 1=1  													");
        query.append("	   AND 	 x.menu_id = z.menu_id 									");
        query.append("	   AND 	 x.is_system = 'N'            							");
        query.append("	   AND 	 x.is_use = 'Y'              							");
        query.getAndQuery("x.service_type", "WEB");
        if(!"".equals(loginUser.getUsrgrpId()))
            query.append("    AND x.menu_id IN(SELECT z.menu_id FROM TAUSRGRPMENU z where z.comp_no='"+loginUser.getCompNo()+"' and z.usrgrp_id='"+loginUser.getUsrgrpId()+"')");
        query.append(" )b																");
        query.append("WHERE 1=1															");
        query.getAndQuery("b.description", lovMenuListDTO.getMenuDesc());
        query.append("ORDER BY lvl ASC, ordno ASC										");

        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    @Override
    public List findMenuAcList(LovMenuListDTO lovMenuListDTO, User user,
            Map<String, String> conditionMap)
    {
        QueryBuffer query = new QueryBuffer();
        String lang = user.getLangId();
        
        query.append("SELECT            ");
        query.append("b.*,              ");
        query.append(" MIN(b.LVL) OVER() AS MINLVL     ");
        query.append("FROM              ");
        query.append("(                 ");
        query.append("SELECT            ");
        query.append("x.menu_id    AS ID     ");
        query.append(",x.menu_id        AS MENU_ID ");
        query.append(",x.p_menu_id    AS P_MENU_ID  ");
        query.append(",      (SELECT a.key_name                                     ");
        query.append("       FROM   TALANG a                                        ");
        query.append("       WHERE  a.key_no = x.key_no                             ");
        query.append("         AND  a.key_type ='MENU'                              ");
        query.append("         AND  a.lang = '"+lang+"') DESCRIPTION      ");
        query.append(",x.ord_no    ORD_NO ");
        query.append(",LVL         ");
        query.append("FROM  TAMENU x      ,(SELECT * FROM dbo.SFAMENU_ALL('0') )y         ");
        query.append("WHERE 1=1                                                     ");
        query.append("  AND x.menu_id = y.menu_id 		                			");
        query.append("  AND x.menu_id IN(SELECT z.menu_id FROM TAUSRGRPMENU z where z.comp_no='"+user.getCompNo()+"' and z.usrgrp_id='"+user.getUsrgrpId()+"')      		");
        query.getAndQuery("x.service_type", conditionMap);
        query.append("	AND x.is_system = 'N'            							");
        query.append("	AND x.is_use = 'Y'              							");
        
        if(!"".equals(lovMenuListDTO.getMenuDesc()))
        {
            query.append("AND x.p_menu_id IN (SELECT a.menu_id FROM TAMENU a INNER JOIN TALANG b ON a.key_no=b.key_no AND b.lang='"+lang+"' WHERE b.key_name LIKE '%"+lovMenuListDTO.getMenuDesc()+"%')     ");
        }
        query.append(" )b       ");
        query.append("WHERE 1=1         ");
        query.append(" ORDER BY lvl ASC, ord_no ASC     ");

       
        return getJdbcTemplate().queryForList(query.toString());
    }
}