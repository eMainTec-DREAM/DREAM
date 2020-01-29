package dream.work.list.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.work.list.dao.LovPmEquipAcListDAO;
import dream.work.list.dto.LovPmEquipAcListDTO;

/**
 * 생산품목 팝업
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="lovPmEquipAcListDAOTarget"
 * @spring.txbn id="lovPmEquipAcListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovPmEquipAcListDAOSqlImpl extends BaseJdbcDaoSupportSql implements LovPmEquipAcListDAO
{
    /**
     * 검색
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovPmEquipAcListDTO
     * @param loginUser
     * @return
     */
    public List findPmEquipAcList(LovPmEquipAcListDTO lovPmEquipAcListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                          ");
        query.append("    x.product_id    productId   ");
        query.append("  , (SELECT a.product_no FROM TAPRODUCT a WHERE a.product_id = x.product_id) productNo        ");
        query.append("  , (SELECT a.description FROM TAPRODUCT a WHERE a.product_id = x.product_id) productDesc     ");
        query.append("  , x.pm_id         pmId        ");
        query.append("  , x.pm_no         pmNo        ");
        query.append("  , x.description   pmDesc      ");
        query.append("  , x.REMARK        REMARK      ");
        query.append("  , y.pmequip_id    pmEquipId   ");

        query.append("  , (SELECT a.item_no           ");
        query.append("      FROM TAEQUIPMENT a        ");
        query.append("      WHERE a.equip_id = y.equip_id)      equipNo     ");
        query.append("  , (SELECT                                           ");
        query.append("        (SELECT full_desc                             ");
        query.append("         FROM TAEQLOC                                 ");
        query.append("         WHERE eqloc_id = a.eqloc_id                  ");
        query.append("           AND comp_no = a.comp_no)                   ");
        query.append("     FROM TAEQUIPMENT a                               ");
        query.append("     WHERE a.comp_no = x.comp_no                      ");
        query.append("       AND a.equip_id = y.equip_id)       eqLocDesc   ");
        query.append("   , (SELECT a.description                            ");
        query.append("      FROM TAEQUIPMENT a                              ");
        query.append("      WHERE a.equip_id = y.equip_id)      equipName   ");
        
        query.append("   , (SELECT emp_name FROM TAEMP WHERE emp_id = x.emp_id) empDesc     ");
        query.append("   , (select description from tadept where dept_id = x.dept_id) deptDesc          ");

        query.append("FROM TAPMLST x, TAPMEQUIP y     ");
        query.append("WHERE x.pm_id = y.pm_id         ");
        query.append("AND x.pm_type = 'CINS'          ");
        query.append("AND (SELECT a.is_use FROM TAPRODUCT a WHERE a.product_id = x.product_id) = 'Y'        ");
        query.append("AND x.is_active = 'Y'       ");

        return getJdbcTemplate().queryForList(query.toString());
        
    }

	@Override
	public List findPmEquipAcAcList(LovPmEquipAcListDTO lovPmEquipAcListDTO, User user, Map<String, String> conditionMap) {
	    QuerySqlBuffer query = new QuerySqlBuffer();
        
	    query.append("SELECT                          ");
        query.append("    x.product_id    productId   ");
        query.append("  , (SELECT a.product_no FROM TAPRODUCT a WHERE a.product_id = x.product_id) productNo        ");
        query.append("  , (SELECT a.description FROM TAPRODUCT a WHERE a.product_id = x.product_id) productDesc     ");
        query.append("  , x.pm_id         pmId        ");
        query.append("  , x.pm_no         pmNo        ");
        query.append("  , x.description   pmDesc      ");
        query.append("  , x.REMARK        REMARK      ");
        query.append("  , y.pmequip_id    pmEquipId   ");

        query.append("  , (SELECT a.item_no           ");
        query.append("      FROM TAEQUIPMENT a        ");
        query.append("      WHERE a.equip_id = y.equip_id)      equipNo     ");
        query.append("  , (SELECT                                           ");
        query.append("        (SELECT full_desc                             ");
        query.append("         FROM TAEQLOC                                 ");
        query.append("         WHERE eqloc_id = a.eqloc_id                  ");
        query.append("           AND comp_no = a.comp_no)                   ");
        query.append("     FROM TAEQUIPMENT a                               ");
        query.append("     WHERE a.comp_no = x.comp_no                      ");
        query.append("       AND a.equip_id = y.equip_id)       eqLocDesc   ");
        query.append("   , (SELECT a.description                            ");
        query.append("      FROM TAEQUIPMENT a                              ");
        query.append("      WHERE a.equip_id = y.equip_id)      equipName   ");
        
        query.append("   , (SELECT emp_name FROM TAEMP WHERE emp_id = x.emp_id) empDesc     ");
        query.append("   , (select description from tadept where dept_id = x.dept_id) deptDesc          ");

        query.append("FROM TAPMLST x, TAPMEQUIP y     ");
        query.append("WHERE x.pm_id = y.pm_id         ");
        query.append("AND x.pm_type = 'CINS'          ");
        query.append("AND (SELECT a.is_use FROM TAPRODUCT a WHERE a.product_id = x.product_id) = 'Y'        ");
        query.append("AND x.is_active = 'Y'         ");

        query.getAndQuery("x.comp_no", conditionMap);
        
        
        // 생산제품명
        if(!"".equals(lovPmEquipAcListDTO.getProductDesc()))
        {
            query.append("AND x.product_id IN (SELECT a.product_id                 ");
            query.append("                     FROM TAPRODUCT a                    ");
            query.append("                     WHERE a.product_id = x.product_id   ");
            query.getLikeQuery("a.description", lovPmEquipAcListDTO.getProductDesc());
            query.append("                     )                                   ");
        }

        
        
        // 생산제품코드
        if(!"".equals(lovPmEquipAcListDTO.getProductNo()))
        {
            query.append("AND x.product_id IN (SELECT a.product_id                 ");
            query.append("                     FROM TAPRODUCT a                    ");
            query.append("                     WHERE a.product_id = x.product_id   ");
            query.getLikeQuery("a.product_no", lovPmEquipAcListDTO.getProductNo());
            query.append("                     )                                   ");
        }
             
        // 설비 (ID/DESC)
        if(!"".equals(lovPmEquipAcListDTO.getEquipDesc()) && !"".equals(lovPmEquipAcListDTO.getEquipId()))
        {
            query.getAndQuery("y.equip_id", lovPmEquipAcListDTO.getEquipId());
        }
        else if(!"".equals(lovPmEquipAcListDTO.getEquipDesc()))
        {
            query.append("AND y.equip_id IN (SELECT b.equip_id       ");
            query.append("                      FROM TAEQUIPMENT b   ");
            query.append("                      WHERE 1=1            ");
            query.getLikeQuery("b.description", lovPmEquipAcListDTO.getEquipDesc());
            query.append("                   )                       ");
        }
        
        //부서명
        query.getDeptLevelQuery("x.dept_id", lovPmEquipAcListDTO.getDeptId(), lovPmEquipAcListDTO.getDeptDesc(), user.getCompNo());
        
        //담당자
        query.getCodeLikeQuery("x.emp_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.emp_id AND a.comp_no='"+user.getCompNo()+"')", 
                lovPmEquipAcListDTO.getEmpId(), lovPmEquipAcListDTO.getEmpDesc());
        
        query.append("ORDER by x.product_id  ");
        
        return getJdbcTemplate().queryForList(query.toString());
        
	}
}