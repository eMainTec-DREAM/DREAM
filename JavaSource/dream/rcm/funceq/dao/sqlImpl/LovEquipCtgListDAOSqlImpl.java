package dream.rcm.funceq.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.rcm.funceq.dao.LovEquipCtgListDAO;
import dream.rcm.funceq.dto.LovEquipCtgListDTO;

/**
 * 설비 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="lovEquipCtgListDAOTarget"
 * @spring.txbn id="lovEquipCtgListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovEquipCtgListDAOSqlImpl extends BaseJdbcDaoSupportSql implements LovEquipCtgListDAO
{
    /**
     * 설비 검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovEquipCtgListDTO
     * @param loginUser
     * @return
     */
    public List findEquipList(LovEquipCtgListDTO lovEquipCtgListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append(" SELECT 		");
        query.append("   '' seqNo		");
        query.append("   ,a.item_no itemNo		");
        query.append("   ,a.description itemDesc		");
        query.append("   ,b.description asmbDesc		");
        query.append("   ,a.equip_id equipId		");
        query.append("   ,ISNULL(CONVERT(VARCHAR(4),b.rcmeqasmb_id,112),'null') rcmEqAsmbId		");
        query.append("  FROM taequipment a LEFT OUTER JOIN tarcmeqasmb b ON a.comp_no = b.comp_no AND a.equip_id = (SELECT x.equip_id FROM TAEQASMB x WHERE x.eqasmb_id=b.eqasmb_id) 		");
        query.append("   WHERE 1=1		");
        query.append(this.getWhere(lovEquipCtgListDTO, loginUser));
        query.append("  ORDER BY a.equip_id		");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    private String getWhere(LovEquipCtgListDTO lovEquipCtgListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo = loginUser.getCompNo();
        
        query.getLikeQuery("description", lovEquipCtgListDTO.getSearchText());

        query.getAndQuery("a.comp_no", compNo);
        query.getAndQuery("a.item_no", lovEquipCtgListDTO.getItemNo());
        query.getLikeQuery("a.description", lovEquipCtgListDTO.getEquipDesc());
      
        return query.toString();
    }
}