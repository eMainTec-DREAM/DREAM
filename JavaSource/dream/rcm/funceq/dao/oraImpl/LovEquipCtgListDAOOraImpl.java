package dream.rcm.funceq.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
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
public class LovEquipCtgListDAOOraImpl extends BaseJdbcDaoSupportOra implements LovEquipCtgListDAO
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
        QueryBuffer query = new QueryBuffer();

        query.append(" SELECT 		");
        query.append("   '' seqNo		");
        query.append("   ,a.item_no itemNo		");
        query.append("   ,a.description itemDesc		");
        query.append("   ,b.description asmbDesc		");
        query.append("   ,a.equip_id equipId		");
        query.append("   ,NVL(TO_CHAR(b.eqasmb_id),'null') rcmEqAsmbId  		");
        query.append("  FROM taequipment a LEFT OUTER JOIN TAEQASMB b ON a.comp_no = b.comp_no AND a.equip_id = b.equip_id		");
        query.append("  WHERE 1=1	");
        query.append(this.getWhere(lovEquipCtgListDTO, loginUser));
        query.append("  ORDER BY a.equip_id		");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    private String getWhere(LovEquipCtgListDTO lovEquipCtgListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        String compNo = loginUser.getCompNo();
        
        query.getLikeQuery("description", lovEquipCtgListDTO.getSearchText());

        query.getAndQuery("a.comp_no", compNo);
        query.getAndQuery("a.item_no", lovEquipCtgListDTO.getItemNo());
        query.getLikeQuery("a.description", lovEquipCtgListDTO.getEquipDesc());
      
        return query.toString();
    }
}