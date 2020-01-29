package dream.asset.list.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.asset.list.dao.LovEquipListDAO;
import dream.asset.list.dto.LovEquipListDTO;

/**
 * 설비 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="lovEquipListDAOTarget"
 * @spring.txbn id="lovEquipListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovEquipListDAOOraImpl extends BaseJdbcDaoSupportOra implements LovEquipListDAO
{
    /**
     * 설비 검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovEquipListDTO
     * @param loginUser
     * @return
     */
    public List findEquipList(LovEquipListDTO lovEquipListDTO, User loginUser, Map<String, String> conditionMap)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                            ");
        query.append("      x.equip_id                                  equipId,        ");
        query.append("      x.item_no                                   itemNo,         ");
        query.append("      x.description                               equipDesc,      ");
        query.append("      (SELECT full_desc                                           ");
        query.append("         FROM TAEQLOC                                             ");
        query.append("        WHERE comp_no = x.comp_no                                 ");
        query.append("          AND eqloc_id = x.eqloc_id)              eqLocDesc,      ");
        
        query.append("        x.p_equip_id                                parEquipId,       ");
        query.append("        (SELECT a.description FROM TAEQUIPMENT a WHERE a.comp_no = x.comp_no AND a.equip_id = x.p_equip_id) parEquipDesc,     ");
        query.append("        (SELECT b.description FROM TADEPT b WHERE b.comp_no = x.comp_no AND b.dept_id = (SELECT a.usage_dept FROM TAEQUIPMENT a WHERE a.comp_no = x.comp_no AND a.equip_id = x.p_equip_id)  ) parEqUsaDeptDesc,               ");
        query.append("        (SELECT a.eqloc_id FROM TAEQUIPMENT a WHERE a.comp_no = x.comp_no AND a.equip_id = x.p_equip_id) parEqLocId,      ");
        query.append("        (SELECT b.full_desc                                                   ");
        query.append("           FROM TAEQLOC b                                                     ");
        query.append("          WHERE b.comp_no = x.comp_no                                         ");
        query.append("            AND b.eqloc_id = (SELECT a.eqloc_id FROM TAEQUIPMENT a WHERE a.comp_no = x.comp_no AND a.equip_id = x.p_equip_id) )                parEqLocDesc,      ");

        query.append("      (SELECT full_desc                                           ");
        query.append("         FROM TAEQCTG                                             ");
        query.append("        WHERE comp_no = x.comp_no                                 ");
        query.append("          AND eqctg_id = x.eqctg_id)              eqCtgDesc,      ");
        query.append("      x.maker                                     maker,          ");
        query.append("      x.model_no                                  modelNo,        ");
        query.append("      SFACODE_TO_DESC(x.plf_type,'PLF_TYPE','SYS','','"+loginUser.getLangId()+"') plfTypeDesc,");
        query.append("       x.is_law_eq                                isLawEq,        ");
        query.append("      (SELECT description                                         ");
        query.append("         FROM TADEPT                                              ");
        query.append("        WHERE comp_no = x.comp_no                                 ");
        query.append("          AND dept_id = x.dept_id)                deptDesc,       ");
        query.append("      (SELECT emp_name                                            ");
        query.append("         FROM TAEMP                                               ");
        query.append("        WHERE comp_no = x.comp_no                                 ");
        query.append("          AND emp_id = x.main_mng_id)             mainMngName,    ");
        query.append("      (SELECT emp_name                                            ");
        query.append("         FROM TAEMP                                               ");
        query.append("        WHERE comp_no = x.comp_no                                 ");
        query.append("          AND emp_id = x.sub_mng_id)              subMngName,     ");
        query.append("      x.eqloc_id                                  eqLocId,        ");
        query.append("      x.eqctg_id                                  eqCtgId,        ");
        query.append("      SFACODE_TO_DESC(x.eq_status,'EQ_STATUS','SYS','','"+loginUser.getLangId()+"') eqStatusDesc,    ");
        query.append("      x.eq_status                                 eqStatusId,     ");
        query.append("      x.is_last_version                           isLastVersion,   ");
        query.append("      CASE WHEN x.ctctr_id IS NOT NULL                            ");
        query.append("                  THEN x.ctctr_id                                 ");
        query.append("              ELSE (SELECT a.ctctr_id                             ");
        query.append("                      FROM TADEPT a                               ");
        query.append("                      WHERE 1=1                                   ");
        query.append("                      AND a.comp_no = x.comp_no                   ");
        query.append("                      AND a.dept_id = x.dept_id                   ");
        query.append("                  )                                               ");
        query.append("          END ctCtrId,                                            ");
        query.append("      CASE WHEN x.ctctr_id IS NOT NULL                            ");
        query.append("                  THEN (SELECT a.description                      ");
        query.append("                          FROM TACTCTR a                          ");
        query.append("                          WHERE a.comp_no = x.comp_no             ");
        query.append("                          AND a.ctctr_id = x.ctctr_id             ");
        query.append("                      )                                           ");
        query.append("              ELSE (SELECT (SELECT b.description                  ");
        query.append("                              FROM TACTCTR b                      ");
        query.append("                              WHERE b.comp_no = a.comp_no         ");
        query.append("                              AND b.ctctr_id = a.ctctr_id         ");
        query.append("                              )                                   ");
        query.append("                      FROM TADEPT a                               ");
        query.append("                      WHERE 1=1                                   ");
        query.append("                      AND a.comp_no = x.comp_no                   ");
        query.append("                      AND a.dept_id = x.dept_id                   ");
        query.append("                  )                                               ");
        query.append("          END ctCtrDesc                                           ");
        query.append("      , x.usage_dept                          usage_dept          ");
        query.append("      , (SELECT a.description                                     ");
        query.append("           FROM TADEPT a                                          ");
        query.append("          WHERE a.comp_no = x.comp_no                             ");
        query.append("            AND a.dept_id = x.usage_dept)     usageDeptDesc       ");
        query.append("      , x.eqctg_type                          eqCtgType           ");
        query.append("      , SFACODE_TO_DESC(x.eqctg_type,'EQCTG_TYPE','SYS','','"+loginUser.getLangId()+"')   eqCtgTypeDesc   ");
        query.append("		,(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = x.comp_no AND a.plant = x.plant ) plantDesc	");
        query.append("FROM   TAEQUIPMENT x                                              ");
        query.append("WHERE 1=1                                                         ");
        query.append(this.getWhere(lovEquipListDTO, loginUser, conditionMap));
        //query.append("ORDER BY   ord_no                                                   ");
        query.getOrderByQuery("x.ord_no", lovEquipListDTO.getOrderBy(), lovEquipListDTO.getDirection());
        
        //return getJdbcTemplate().queryForList(query.toString());
        return getJdbcTemplate().queryForList(query.toString(lovEquipListDTO.getIsLoadMaxCount(), lovEquipListDTO.getFirstRow()));
        
    }
    
    private String getWhere(LovEquipListDTO lovEquipListDTO, User loginUser, Map<String, String> conditionMap)
    {
        QueryBuffer query = new QueryBuffer();
        String compNo = conditionMap.containsKey("comp_no")?conditionMap.get("comp_no"):loginUser.getCompNo();
        
        query.getStringEqualQuery("x.comp_no", compNo);
        query.getStringEqualQuery("x.IS_DELETED", "N");
        query.getStringEqualQuery("x.is_last_version", "Y");
        query.getAndQuery("eq_status", conditionMap);
        query.getAndQuery("eqctg_type", conditionMap);
        query.getAndQuery("plant", conditionMap);
        query.getDeptLevelQuery("x.dept_id", conditionMap, loginUser.getCompNo());
        
        query.getLikeQuery("x.item_no", lovEquipListDTO.getItemNo());
        query.getLikeQuery("x.tag_no", lovEquipListDTO.getTagNo());
        query.getLikeQuery("description", lovEquipListDTO.getSearchText());
        query.getLikeQuery("x.description", lovEquipListDTO.getEquipDesc());
        query.getLikeQuery("x.is_law_eq", lovEquipListDTO.getIsLawEq());
        query.getLikeQuery("x.eq_status", lovEquipListDTO.getEqStatusId());
        query.getLikeQuery("x.old_eq_no", lovEquipListDTO.getOldEqNo());
        //설비유형
        query.getSysCdQuery("x.eqctg_type", lovEquipListDTO.getEqCtgTypeId(), lovEquipListDTO.getEqCtgTypeDesc(), "EQCTG_TYPE", compNo,loginUser.getLangId());
        
        //위치
        query.getEqLocLevelQuery("x.eqloc_id", lovEquipListDTO.getEqLocId(), lovEquipListDTO.getEqLocDesc(), compNo);
        //종류
        query.getEqCtgLevelQuery("x.eqctg_id", lovEquipListDTO.getEqCtgId(), lovEquipListDTO.getEqCtgDesc(), compNo);
        query.getSysCdQuery("x.plf_type", lovEquipListDTO.getPlfTypeId(), lovEquipListDTO.getPlfTypeDesc(), "PLF_TYPE", compNo,loginUser.getLangId());
        query.getCodeLikeQuery("x.main_mng_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.main_mng_id AND a.comp_no='"+compNo+"')", 
                lovEquipListDTO.getMainMngId(), lovEquipListDTO.getMainMngName());
        query.getCodeLikeQuery("x.sub_mng_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.sub_mng_id AND a.comp_no='"+compNo+"')", 
                lovEquipListDTO.getSubMngId(), lovEquipListDTO.getSubMngName());
        query.getDeptLevelQuery("x.dept_id", lovEquipListDTO.getDeptId(), lovEquipListDTO.getDeptDesc(), compNo);
        
        //최신 version 여부
        query.getSysCdQuery("x.is_last_version", lovEquipListDTO.getFilterIsLastVersionId(), lovEquipListDTO.getFilterIsLastVersionDesc(), "IS_USE", compNo,loginUser.getLangId());
        
        //공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+compNo+"' AND a.plant = x.plant )", 
                lovEquipListDTO.getPlantId(), lovEquipListDTO.getPlantDesc());
        
        return query.toString();
    }

    @Override
    public List findEquipAcList(LovEquipListDTO lovEquipListDTO, User loginUser, Map<String, String> conditionMap) {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                            ");
        query.append("      x.equip_id                                  equip_id,       ");
        query.append("      x.item_no                                   item_no,            ");
        query.append("      x.description                               description,        ");
        query.append("      (SELECT full_desc                                           ");
        query.append("         FROM TAEQLOC                                             ");
        query.append("        WHERE comp_no = x.comp_no                                 ");
        query.append("          AND eqloc_id = x.eqloc_id)              eqLocDesc,      ");
        query.append("		(SELECT eqloc_no											");
        query.append("		   FROM TAEQLOC												");
        query.append("		  WHERE comp_no = x.comp_no									");
        query.append("		    AND eqloc_id = x.eqloc_id)				eqLoc_No,		"); 
        query.append("        x.p_equip_id                                parEquipId,       ");
        query.append("        (SELECT a.description FROM TAEQUIPMENT a WHERE a.comp_no = x.comp_no AND a.equip_id = x.p_equip_id) parEquipDesc,     ");
        query.append("        (SELECT b.description FROM TADEPT b WHERE b.comp_no = x.comp_no AND b.dept_id = (SELECT a.usage_dept FROM TAEQUIPMENT a WHERE a.comp_no = x.comp_no AND a.equip_id = x.p_equip_id)  ) parEqUsaDeptDesc,               ");
        query.append("        (SELECT a.eqloc_id FROM TAEQUIPMENT a WHERE a.comp_no = x.comp_no AND a.equip_id = x.p_equip_id) parEqLocId,      ");
        query.append("        (SELECT b.full_desc                                                   ");
        query.append("           FROM TAEQLOC b                                                     ");
        query.append("          WHERE b.comp_no = x.comp_no                                         ");
        query.append("            AND b.eqloc_id = (SELECT a.eqloc_id FROM TAEQUIPMENT a WHERE a.comp_no = x.comp_no AND a.equip_id = x.p_equip_id) )                parEqLocDesc,      ");
        
        query.append("      (SELECT full_desc                                           ");
        query.append("         FROM TAEQCTG                                             ");
        query.append("        WHERE comp_no = x.comp_no                                 ");
        query.append("          AND eqctg_id = x.eqctg_id)              eqCtgDesc,      ");
        query.append("      x.maker                                     maker,          ");
        query.append("      x.model_no                                  model_no,       ");
        query.append("      x.old_eq_no                                 oldEqNo,        ");
        query.append("      SFACODE_TO_DESC(x.plf_type,'PLF_TYPE','SYS','','"+loginUser.getLangId()+"') plfTypeDesc,");
        query.append("       x.is_law_eq                                is_law_eq,      ");
        query.append("      (SELECT description                                         ");
        query.append("         FROM TADEPT                                              ");
        query.append("        WHERE comp_no = x.comp_no                                 ");
        query.append("          AND dept_id = x.dept_id)                deptDesc,       ");
        query.append("      (SELECT emp_name                                            ");
        query.append("         FROM TAEMP                                               ");
        query.append("        WHERE comp_no = x.comp_no                                 ");
        query.append("          AND emp_id = x.main_mng_id)             mainMngName,    ");
        query.append("      (SELECT emp_name                                            ");
        query.append("         FROM TAEMP                                               ");
        query.append("        WHERE comp_no = x.comp_no                                 ");
        query.append("          AND emp_id = x.sub_mng_id)              subMngName,     ");
        query.append("      x.eqloc_id                                  eqloc_id,       ");
        query.append("      x.eqctg_id                                  eqctg_id,       ");
        query.append("      x.serial_no                                 serial_no,      ");
        query.append("      x.eqctg_type                                eqctg_type,     ");
        query.append("      SFACODE_TO_DESC(x.eq_status,'EQ_STATUS','SYS','','"+loginUser.getLangId()+"') eqStatusDesc,    ");
        query.append("      x.eq_status                                 eq_status,      ");
        query.append("      (SELECT pmcalibstdtp_id FROM TAEQTOOL WHERE comp_no = '"+loginUser.getCompNo()+"' AND equip_id = x.equip_id) AS pmcalibstdtp_id,");
        query.append("      CASE WHEN x.ctctr_id IS NOT NULL                            ");
        query.append("                  THEN x.ctctr_id                                 ");
        query.append("              ELSE (SELECT a.ctctr_id                             ");
        query.append("                      FROM TADEPT a                               ");
        query.append("                      WHERE 1=1                                   ");
        query.append("                      AND a.comp_no = x.comp_no                   ");
        query.append("                      AND a.dept_id = x.dept_id                   ");
        query.append("                  )                                               ");
        query.append("          END ctCtrId,                                            ");
        query.append("      CASE WHEN x.ctctr_id IS NOT NULL                            ");
        query.append("                  THEN (SELECT a.description                      ");
        query.append("                          FROM TACTCTR a                          ");
        query.append("                          WHERE a.comp_no = x.comp_no             ");
        query.append("                          AND a.ctctr_id = x.ctctr_id             ");
        query.append("                      )                                           ");
        query.append("              ELSE (SELECT (SELECT b.description                  ");
        query.append("                              FROM TACTCTR b                      ");
        query.append("                              WHERE b.comp_no = a.comp_no         ");
        query.append("                              AND b.ctctr_id = a.ctctr_id         ");
        query.append("                              )                                   ");
        query.append("                      FROM TADEPT a                               ");
        query.append("                      WHERE 1=1                                   ");
        query.append("                      AND a.comp_no = x.comp_no                   ");
        query.append("                      AND a.dept_id = x.dept_id                   ");
        query.append("                  )                                               ");
        query.append("          END ctCtrDesc                                           ");
        query.append("      , x.usage_dept                          usage_dept          ");
        query.append("      , (SELECT a.description                                     ");
        query.append("           FROM TADEPT a                                          ");
        query.append("          WHERE a.comp_no = x.comp_no                             ");
        query.append("            AND a.dept_id = x.usage_dept)     usageDeptDesc       ");
        query.append("      , x.eqctg_type                          eqCtgType           ");
        query.append("      , SFACODE_TO_DESC(x.eqctg_type,'EQCTG_TYPE','SYS','','"+loginUser.getLangId()+"')   eqCtgTypeDesc   ");
        query.append("		,(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = x.comp_no AND a.plant = x.plant ) plantDesc	");
        query.append("FROM   TAEQUIPMENT x                                              ");
        query.append("WHERE 1=1                                                         ");
        query.append(this.getWhere(lovEquipListDTO, loginUser, conditionMap));
        //query.append("ORDER BY   ord_no                                                   ");
        query.getOrderByQuery("x.ord_no, x.item_no, x.equip_id", lovEquipListDTO.getOrderBy(), lovEquipListDTO.getDirection());
        
        //return getJdbcTemplate().queryForList(query.toString());
        return getJdbcTemplate().queryForList(query.toString(lovEquipListDTO.getIsLoadMaxCount(), lovEquipListDTO.getFirstRow()));

    }
    
    public String findTotalCount(
            LovEquipListDTO lovEquipListDTO, User loginUser, Map<String, String> conditionMap) {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                            ");
        query.append("      count(1)        ");
        query.append("FROM   TAEQUIPMENT x  ");
        query.append("WHERE 1=1             ");
        query.append(this.getWhere(lovEquipListDTO, loginUser, conditionMap));
        query.append("ORDER BY   item_no     ");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }    
}