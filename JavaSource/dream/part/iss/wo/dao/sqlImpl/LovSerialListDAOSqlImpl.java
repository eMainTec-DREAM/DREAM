package dream.part.iss.wo.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.part.iss.wo.dao.LovSerialListDAO;
import dream.part.iss.wo.dto.LovSerialListDTO;

/**
 * 질의 팝업
 * @author  hyosung
 * @version $Id: LovSerialListDAO.java,v 1.0 2016/01/18 00:16:44 hyosung Exp $
 * @since   1.0
 * @spring.bean id="lovSerialListDAOTarget"
 * @spring.txbn id="lovSerialListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovSerialListDAOSqlImpl extends BaseJdbcDaoSupportSql implements LovSerialListDAO
{
    /**
     * 질의 검색
     * @author  hyosung
     * @version $Id: LovSerialListDAO.java,v 1.0 2016/01/18 00:16:44 hyosung Exp $
     * @since   1.0
     * 
     * @param lovSerialListDTO
     * @param loginUser
     * @return
     */
    public List findSerialList(LovSerialListDTO lovSerialListDTO,User loginUser, Map<String, String> conditionMap)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                                                ");
        query.append("       a.serial_no    serialNo                                                        ");
        query.append("       ,a.equip_id equipId                                                            ");
        query.append("       ,a.item_no itemNo                                                              ");
        query.append("       ,a.description                                                                 ");
        query.append("       ,(SELECT aa.description                                                        ");
        query.append("          FROM TAEQUIPMENT aa                                                         ");
        query.append("          WHERE aa.equip_id=a.p_equip_id)   pequipDesc                                ");
        query.append("       ,b.part_id partId                                                              ");
        query.append("       ,c.description partDesc                                                        ");
        query.append("       ,c.part_no partNo                                                              ");
        query.append("FROM   TAEQUIPMENT a INNER JOIN TAEQDEVICE b ON a.equip_id = b.equip_id               ");
        query.append("       INNER JOIN TAPARTS c ON b.part_id = c.part_id                                  ");

        
        query.getLikeQuery("serial_no", lovSerialListDTO.getSerialNo());
        query.getAndQuery("a.comp_no", conditionMap);
        query.getAndQuery("b.part_id", conditionMap);
        query.getAndQuery("a.eq_status", conditionMap);
		query.getStringEqualQuery("a.is_last_version", "Y");

        query.append("ORDER BY  1                                                                           ");
                                                                     
        
        
        return getJdbcTemplate().queryForList(query.toString());
    }

  
    
}