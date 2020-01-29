package dream.consult.program.menu.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
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
public class LovMenuListDAOOraImpl extends BaseJdbcDaoSupportOra implements LovMenuListDAO
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
        QueryBuffer query = new QueryBuffer();
        String lang = loginUser.getLangId();
        
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
        query.append("         AND  a.lang = '"+lang+"') description,");

        query.append("         x.ord_no ordNo,");
        query.append("         level lvl");
        query.append("FROM  TAMENU x, TAPAGE y                              ");
        query.append("WHERE x.page_id = y.page_id(+)                        ");
        query.append(" AND x.is_system = 'N'            	");
        query.append("    AND x.is_use = 'Y'              	");
        query.getLikeQuery("x.service_type", "WEB");
        if(!"".equals(loginUser.getUsrgrpId()))
            query.append("    AND x.menu_id IN(SELECT z.menu_id FROM TAUSRGRPMENU z where z.comp_no='"+loginUser.getCompNo()+"' and z.usrgrp_id='"+loginUser.getUsrgrpId()+"')");

        query.append("START WITH p_menu_id = '0'                            ");
        query.append("CONNECT BY PRIOR menu_id = p_menu_id ");
        query.append("order siblings by x.ord_no");
        query.append(" )b");
        query.append("WHERE 1=1												");
        query.getAndQuery("b.description", lovMenuListDTO.getMenuDesc());
        query.append(" order by lvl asc, ordno asc");

        
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
        query.append(" MIN(b.lvl) OVER() AS MINLVL     ");
        query.append("FROM              ");
        query.append("(                 ");
        query.append("SELECT            ");
        query.append("x.menu_id    AS ID     ");
        query.append(",x.menu_id         ");
        query.append(",x.p_menu_id      ");
        query.append(",      (SELECT a.key_name                                     ");
        query.append("       FROM   TALANG a                                        ");
        query.append("       WHERE  a.key_no = x.key_no                             ");
        query.append("         AND  a.key_type ='MENU'                              ");
        query.append("         AND  a.lang = '"+lang+"') description      ");
        query.append(",x.ord_no     ");
        query.append(",LEVEL lvl        ");
        query.append("FROM  TAMENU x        ");
        query.append("WHERE 1=1     ");
        query.append("	AND x.is_system = 'N'            							");
        query.append("	AND x.is_use = 'Y'              							");
        query.getAndQuery("x.service_type", conditionMap);
        query.append("  AND x.menu_id IN(SELECT z.menu_id FROM TAUSRGRPMENU z where z.comp_no='"+user.getCompNo()+"' and z.usrgrp_id='"+user.getUsrgrpId()+"')      		");

        if(!"".equals(lovMenuListDTO.getMenuDesc()))
        {
            query.append("START WITH p_menu_id IN (SELECT menu_id FROM TAMENU a INNER JOIN TALANG b ON a.key_no=b.key_no AND b.lang='"+lang+"' WHERE b.key_name LIKE '%"+lovMenuListDTO.getMenuDesc()+"%')     ");
        }
        else query.append("START WITH p_menu_id = '0'                            ");
        query.append("CONNECT BY PRIOR menu_id = p_menu_id      ");
        query.append("ORDER SIBLINGS BY x.ord_no        ");
        query.append(" )b       ");
        query.append("WHERE 1=1         ");
        query.append(" ORDER BY lvl ASC, ord_no ASC     ");

       
        return getJdbcTemplate().queryForList(query.toString());
    }
}