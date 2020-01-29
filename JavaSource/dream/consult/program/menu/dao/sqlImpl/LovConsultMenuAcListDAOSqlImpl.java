package dream.consult.program.menu.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.consult.program.menu.dao.LovConsultMenuAcListDAO;
import dream.consult.program.menu.dto.LovConsultMenuAcListDTO;

/**
 * 컨설트용 메뉴 팝업
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="lovConsultMenuAcListDAOTarget"
 * @spring.txbn id="lovConsultMenuAcListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovConsultMenuAcListDAOSqlImpl extends BaseJdbcDaoSupportSql implements LovConsultMenuAcListDAO
{
    /**
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovConsultMenuAcListDTO
     * @param loginUser
     * @return
     */
    public List findMenuList(LovConsultMenuAcListDTO lovConsultMenuAcListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String lang = loginUser.getLangId();
        
        query.append("SELECT                                                        ");
        query.append("b.*,                                                          ");
        query.append(" MIN(b.LVL) OVER() AS MINLVL                                                 ");                                              
        query.append("FROM                                                          ");
        query.append("(                                                             ");
        query.append("SELECT                                                        ");
        query.append("x.menu_id as ID,                                              ");
        query.append("x.menu_id menuId,                                             ");
        query.append("x.p_menu_id pMenuId                                           ");                                        
        query.append(",x.page_id             AS page_id                             ");
        query.append(",(SELECT file_name FROM TAPAGE WHERE page_id = x.page_id) as file_name        ");

        query.append(",      (SELECT a.key_name                                     ");
        query.append("       FROM   TALANG a                                        ");
        query.append("       WHERE  a.key_no = x.key_no                             ");
        query.append("         AND  a.key_type ='MENU'                              ");
        query.append("         AND  a.lang = '"+lang+"') description               ");

        query.append("         ,x.ord_no AS ORDNO                                   ");
        query.append("         ,z.lvl AS LVL                                        ");
        query.append("     FROM  TAMENU x LEFT JOIN TAPAGE y ON x.page_id = y.page_id   ");
        query.append("           ,(SELECT * FROM dbo.SFAMENU_ALL('0')) z                ");
        query.append("     WHERE 1=1                                                    ");
        query.append("     AND   x.menu_id = z.menu_id                                  ");
        query.append("     AND   x.is_system = 'N'                                      ");
        query.append("     AND   x.is_use = 'Y'                                         ");
        query.getLikeQuery("x.service_type", "WEB");                        
        if(!"".equals(loginUser.getUsrgrpId()))
            query.append("    AND x.menu_id IN(SELECT z.menu_id FROM TAUSRGRPMENU z)");

        query.append(" )b                                                           ");
        query.append("WHERE 1=1                                                     ");
        query.getAndQuery("b.description", lovConsultMenuAcListDTO.getMenuDesc());
        query.append(" order by lvl asc, ordno asc                                  ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    @Override
    public List findMenuAcList(LovConsultMenuAcListDTO lovConsultMenuAcListDTO, User user,
            Map<String, String> conditionMap)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String lang = user.getLangId();
        
        query.append("SELECT                                                        ");
        query.append("b.*,                                                          ");
        query.append(" MIN(b.LVL) OVER() AS MINLVL                                                 ");
        query.append("FROM                                                          ");
        query.append("(                                                             ");
        query.append("SELECT                                                        ");
        query.append("x.menu_id    AS ID                                            ");
        query.append(",x.menu_id    AS MENU_ID                                        ");
        query.append(",x.p_menu_id  AS P_MENU_ID                                                ");
        query.append(",x.page_id             AS PAGE_ID                             ");
        query.append(",(SELECT file_name FROM TAPAGE WHERE page_id = x.page_id) as file_name        ");
        query.append(",      (SELECT a.key_name                                     ");
        query.append("       FROM   TALANG a                                        ");
        query.append("       WHERE  a.key_no = x.key_no                             ");
        query.append("         AND  a.key_type ='MENU'                              ");
        query.append("         AND  a.lang = '"+lang+"') DESCRIPTION                ");
        query.append(",x.ord_no        ORD_NO                                        ");
        query.append(",LVL                                                     ");
        query.append("FROM  TAMENU x,(SELECT * FROM dbo.SFAMENU_ALL('0') )y         ");
        query.append("WHERE 1=1                                                     ");
        query.append("  AND x.menu_id = y.menu_id 		                			");
        query.append("  AND x.is_system = 'N'                                       ");
//        query.getAndQuery("x.service_type", "WEB");
        query.getAndQuery("x.is_use", conditionMap);
        if(conditionMap.containsKey("AUTH") || conditionMap.containsKey("auth"))
        {
            String auth = conditionMap.get("AUTH") == null?conditionMap.get("auth"):conditionMap.get("AUTH");
            if("Y".equals(auth))  query.append("  AND x.menu_id IN(SELECT z.menu_id FROM TAUSRGRPMENU z)      ");
        }
        else query.append("  AND x.menu_id IN(SELECT z.menu_id FROM TAUSRGRPMENU z)      ");
        if(!"".equals(lovConsultMenuAcListDTO.getMenuDesc()))
        {
            query.append("AND x.p_menu_id IN (SELECT menu_id FROM TAMENU a INNER JOIN TALANG b ON a.key_no=b.key_no AND b.lang='"+lang+"' WHERE b.key_name LIKE '%"+lovConsultMenuAcListDTO.getMenuDesc()+"%')     ");
        }
        query.append(" )b                                                           ");
        query.append("WHERE 1=1                                                     ");
        query.append(" ORDER BY lvl ASC, ord_no ASC                                 ");

        return getJdbcTemplate().queryForList(query.toString());
    }
}