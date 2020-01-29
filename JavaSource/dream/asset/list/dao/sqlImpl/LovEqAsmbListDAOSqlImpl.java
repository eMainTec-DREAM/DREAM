package dream.asset.list.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.asset.list.dao.LovEqAsmbListDAO;
import dream.asset.list.dto.LovEqAsmbListDTO;

/**
 * 설비부위 팝업
 * @author  hyosun
 * @version $Id: LovEqAsmbListDAO.java,v 1.0 2016/01/18 00:16:44 hyosun Exp $
 * @since   1.0
 * @spring.bean id="lovEqAsmbListDAOTarget"
 * @spring.txbn id="lovEqAsmbListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovEqAsmbListDAOSqlImpl extends BaseJdbcDaoSupportSql implements LovEqAsmbListDAO
{
    /**
     * 설비부위 검색
     * @author  hyosun
     * @version $Id: LovEqAsmbListDAO.java,v 1.0 2016/01/18 00:16:44 hyosun Exp $
     * @since   1.0
     * 
     * @param lovEqAsmbListDTO
     * @param loginUser
     * @param conditionMap
     * @return
     */
    public List findEqAsmbAcList(LovEqAsmbListDTO lovEqAsmbListDTO, User user, Map<String, String> conditionMap)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
    	
        query.append("SELECT                                                   ");
        query.append("       x.eqasmb_id         as  EQASMB_ID                 ");
        query.append("       ,x.description      as  DESCRIPTION               ");
        query.append("       ,isnull(x.full_desc,x.description)         AS FULL_DESC                ");
        query.append("       ,x.is_eqtype_asmb   as  IS_EQTYPE_ASMB            ");
        query.append("       , x.ord_no          as  ORD_NO                    ");
        query.append("       , x.eqasmb_id       as  ID                        ");
        query.append("       , x.p_eqasmb_id     as  PEQASMBID                 ");
        query.append("       , MIN(y.lvl) OVER()    AS MINLVL ");
        query.append("       , y.lvl 			 as  LEVEL                       ");
        query.append("  FROM TAEQASMB x 													");
        query.append(" INNER JOIN (SELECT * 												");
        query.append(" 				 FROM dbo.SFAEQASMB_ALL('"+user.getCompNo()+"','0')) y	");
        query.append("    ON x.comp_no   = y.comp_no   										");
        query.append("   AND x.eqasmb_id = y.eqasmb_id 										");
        query.append(" WHERE 1 = 1 															");
        query.getLikeQuery("x.description", lovEqAsmbListDTO.getEqAsmbDesc());
        query.getAndQuery("x.equip_id", conditionMap, "Y");
        query.getAndQuery("x.is_use", lovEqAsmbListDTO.getIsUse());
        if(!"".equals(lovEqAsmbListDTO.getEqasmbId()))
        	query.getAndQuery("x.eqasmb_id","-"+lovEqAsmbListDTO.getEqasmbId());
        query.append("ORDER BY ISNULL(x.ord_no, '99999999')");
  
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    public List findEqAsmbByPmAcList(LovEqAsmbListDTO lovEqAsmbListDTO, User user,Map<String, String> conditionMap)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
        query.append("SELECT                                                   ");
        query.append("         x.eqasmb_id          AS EQASMB_ID               ");
        query.append("       , x.description        AS DESCRIPTION             ");
        query.append("       , x.is_eqtype_asmb     AS IS_EQTYPE_ASMB          ");
        query.append("       , (SELECT a.description FROM TAEQUIPMENT a WHERE a.comp_no=x.comp_no AND a.equip_id=x.equip_id)  AS equipDesc  ");
        query.append("       , x.ord_no             AS ORD_NO                  ");
        query.append("       , x.eqasmb_id          AS ID                      ");
        query.append("       , x.p_eqasmb_id        AS PEQASMBID               ");
        query.append("       , ISNULL(x.full_desc,x.description)         AS FULL_DESC                ");
        query.append("       , MIN(y.LVL) OVER()  AS MINLVL   ");
        query.append("       , y.LVL                AS LEVEL                   ");
        query.append("FROM TAEQASMB x, (SELECT * FROM dbo.SFAEQASMB_ALL('"+user.getCompNo()+"','0')) y ");
        query.append("WHERE 1=1                                                ");
        query.append("AND  x.eqasmb_id = y.eqasmb_id			               ");
        query.getAndQuery("x.comp_no",conditionMap);
        query.getLikeQuery("x.description", lovEqAsmbListDTO.getEqAsmbDesc());
        query.append("AND x.equip_id IN (SELECT a.equip_id                      ");
        query.append("                   FROM TAPMEQUIP a                       ");
        query.append("                   WHERE 1=1                              ");
        query.append("                     AND a.is_deleted ='N'                ");
        query.getAndQuery("a.comp_no",conditionMap);
        query.getAndQuery("a.pm_id", conditionMap);
        query.append("                  )                                       ");
        query.append("ORDER BY x.ord_no                                         ");

        return getJdbcTemplate().queryForList(query.toString());
    }
}