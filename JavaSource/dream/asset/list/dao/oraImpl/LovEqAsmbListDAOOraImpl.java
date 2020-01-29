package dream.asset.list.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
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
public class LovEqAsmbListDAOOraImpl extends BaseJdbcDaoSupportOra implements LovEqAsmbListDAO
{

     /**
     * 설비부위 검색
     * @author  hyosun
     * @version $Id: LovEqAsmbListDAO.java,v 1.0 2016/01/18 00:16:44 hyosun Exp $
     * @since   1.0
     * 
     * @param lovEqAsmbListDTO
     * @param user
     * @param conditionMap
     * @return
     */
    public List findEqAsmbAcList(LovEqAsmbListDTO lovEqAsmbListDTO, User user,Map<String, String> conditionMap)
    {
        QueryBuffer query = new QueryBuffer();
        
        
        query.append("SELECT                                                   ");
        query.append("       eqasmb_id                                         ");
        query.append("       ,description                                      ");
        query.append("       ,NVL(full_desc, description)       AS FULL_DESC	");
        query.append("       ,is_eqtype_asmb                                   ");
        query.append("       , ord_no                                          ");
        query.append("       , eqasmb_id      id                               ");
        query.append("       , p_eqasmb_id    pEqAsmbId                        ");
        query.append("       , MIN(LEVEL) OVER()  minLvl      ");
        query.append("       , LEVEL                                           ");
        query.append("FROM TAEQASMB x                                          ");
        query.append("WHERE 1=1                                                ");
        query.getAndQuery("comp_no",user.getCompNo());
        query.getLikeQuery("description", lovEqAsmbListDTO.getEqAsmbDesc());
        query.getAndQuery("equip_id", conditionMap, "Y");
        if(!"".equals(lovEqAsmbListDTO.getEqasmbId()))
        	query.getAndQuery("x.eqasmb_id","-"+lovEqAsmbListDTO.getEqasmbId());
        query.getAndQuery("is_use", lovEqAsmbListDTO.getIsUse());
        query.append("START WITH p_eqasmb_id = '0'                             ");
        query.append("CONNECT BY PRIOR eqasmb_id = p_eqasmb_id                 ");

        query.append("ORDER BY x.ord_no                                          ");
        
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    public List findEqAsmbByPmAcList(LovEqAsmbListDTO lovEqAsmbListDTO, User user,Map<String, String> conditionMap)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                   ");
        query.append("         x.eqasmb_id          AS EQASMB_ID               ");
        query.append("       , x.description        AS DESCRIPTION             ");
        query.append("       , x.is_eqtype_asmb     AS IS_EQTYPE_ASMB          ");
        query.append("       , (SELECT a.description FROM TAEQUIPMENT a WHERE a.comp_no=x.comp_no AND a.equip_id=x.equip_id)  AS equipDesc  ");
        query.append("       , x.ord_no             AS ORD_NO                  ");
        query.append("       , x.eqasmb_id          AS ID                      ");
        query.append("       , x.p_eqasmb_id        AS PEQASMBID               ");
        query.append("       ,NVL(x.full_desc, x.description)       AS FULL_DESC	");
        query.append("       , MIN(LEVEL) OVER()  AS minLvl   ");
        query.append("       , LEVEL                                           ");
        query.append("FROM TAEQASMB x                                          ");
        query.append("WHERE 1=1                                                ");
        query.getAndQuery("x.comp_no",conditionMap);
        query.getLikeQuery("x.description", lovEqAsmbListDTO.getEqAsmbDesc());
        query.append("AND x.equip_id IN(SELECT a.equip_id                      ");
        query.append("                  FROM TAPMEQUIP a                       ");
        query.append("                  WHERE 1=1                              ");
        query.append("                    AND a.is_deleted ='N'                ");
        query.getAndQuery("a.comp_no",conditionMap);
        query.getAndQuery("a.pm_id", conditionMap);
        query.append("                             )                                ");
        query.append("START WITH p_eqasmb_id = '0'                             ");
        query.append("CONNECT BY PRIOR eqasmb_id = p_eqasmb_id                 ");
        query.append("ORDER BY x.ord_no                                          ");
        
//        query.append("SELECT        ");
//        query.append("       eqasmb_id                                              ");
//        query.append("       ,description                                           ");
//        query.append("       ,is_eqtype_asmb                                        ");
//        query.append("       , ord_no                                               ");
//        query.append("       , eqasmb_id      ID                                    ");
//        query.append("       , p_eqasmb_id          pEqAsmbId                               ");
//        query.append("       , MIN(LEVEL) OVER(ORDER BY y.ord_no)  minLvl           ");
//        query.append("       , LEVEL                                                ");
//        query.append("FROM      ");
//        query.append("(     ");
//        query.append("    (     ");
//        query.append("    SELECT                                                        ");
//        query.append("           eqasmb_id                                              ");
//        query.append("           ,description                                           ");
//        query.append("           ,is_eqtype_asmb                                        ");
//        query.append("           , ord_no                                                       ");
//        query.append("           , CASE p_eqasmb_id     ");
//        query.append("                WHEN 0 THEN equip_id      ");
//        query.append("                ELSE p_eqasmb_id      ");
//        query.append("             END                        p_eqasmb_id                               ");
//        query.append("    FROM TAEQASMB x                                               ");
//        query.append("    WHERE 1=1                 ");
//        query.getAndQuery("comp_no",conditionMap);
//        query.getLikeQuery("description", lovEqAsmbListDTO.getEqAsmbDesc());
//        query.append("    ) UNION ALL (     ");
//        query.append("    SELECT                                                        ");
//        query.append("           equip_id           eqasmb_id                                               ");
//        query.append("           ,(SELECT description FROM TAEQUIPMENT WHERE comp_no=x.comp_no AND equip_id=x.equip_id)   description                                           ");
//        query.append("           , ''                          is_eqtype_asmb                                           ");
//        query.append("           , '0'                             ord_no                                               ");
//        query.append("           , 0    p_eqasmb_id                             ");
//        query.append("    FROM TAPMEQUIP x                                                  ");
//        query.append("    WHERE 1=1                 ");
//        query.getAndQuery("comp_no",conditionMap);
//        query.getAndQuery("pm_id", conditionMap);
//        query.append("    )     ");
//        query.append(") y       ");
//        query.append("START WITH p_eqasmb_id = '0'                                  ");
//        query.append("CONNECT BY PRIOR eqasmb_id = p_eqasmb_id                      ");
//        query.append("ORDER BY ord_no               ");
        
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
}