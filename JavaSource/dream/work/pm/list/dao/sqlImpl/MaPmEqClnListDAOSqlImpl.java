package dream.work.pm.list.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.work.pm.list.dao.MaPmEqClnListDAO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;

/**
 * 예방설비  dao
 * @author  kim21017
 * @version $Id: MaPmEqClnListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maPmEqClnListDAOTarget"
 * @spring.txbn id="maPmEqClnListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPmEqClnListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaPmEqClnListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaPmEqClnListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPmMstrCommonDTO
     * @param loginUser
     * @return List
     */
    public List findClnList(MaPmMstrCommonDTO maPmMstrCommonDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT												");
        query.append("       '' 							seqNo,			");
        query.append("       '' 							isDelCheck,		");
        query.append("       (SELECT item_no								");
        query.append("          FROM TAEQUIPMENT							");
        query.append("         WHERE comp_no = x.comp_no					");
        query.append("           AND equip_id = x.equip_id) equipNo,		");
        query.append("       (SELECT (SELECT full_desc 						");
        query.append("                 FROM TAEQLOC 						");
        query.append("                 WHERE eqloc_id = a.eqloc_id			");
        query.append("                   AND comp_no = a.comp_no)			");
        query.append("          FROM TAEQUIPMENT a							");
        query.append("         WHERE a.comp_no = x.comp_no					");
        query.append("           AND a.equip_id = x.equip_id) eqLocDesc,	");
        query.append("       (SELECT description							");
        query.append("          FROM TAEQUIPMENT							");
        query.append("         WHERE comp_no = x.comp_no					");
        query.append("           AND equip_id = x.equip_id) equipDesc,		");
        query.append("       x.pmequip_id 					pmEquipId		");
        query.append("FROM   TAPMEQUIP x	 								");
        query.append("WHERE 1=1												");
        query.append(this.getWhere(maPmMstrCommonDTO,loginUser));
        query.getOrderByQuery("x.pmequip_id", "x.pmequip_id", maPmMstrCommonDTO.getOrderBy(), maPmMstrCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maPmMstrCommonDTO.getIsLoadMaxCount(), maPmMstrCommonDTO.getFirstRow()));
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaPmEqClnListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteClnList(String id, String compNo)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	String pmEquipId=id;
    	
    	query.append("DELETE FROM TAPMEQUIP							");
    	query.append("WHERE  pmequip_id 	= '"+pmEquipId+"'		");
    	query.append("  AND  comp_no		= '"+compNo+"'			");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(MaPmMstrCommonDTO maPmMstrCommonDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	query.getAndQuery("x.pm_id", maPmMstrCommonDTO.getPmId());
    	query.getAndQuery("x.comp_no", loginUser.getCompNo());
    	if (!"".equals(maPmMstrCommonDTO.getPmEquipId()))
        {
            query.getAndQuery("x.pmequip_id", maPmMstrCommonDTO.getPmEquipId());
            return query.toString();
        }
    	
    	return query.toString();
    }

	public String findTotalCount(MaPmMstrCommonDTO maPmMstrCommonDTO, User user) throws Exception 
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
        
		query.append("SELECT                    ");
		query.append("       COUNT(1)           ");
		query.append("FROM   TAPMEQUIP x		");
        query.append("WHERE 1=1					");
        query.append(this.getWhere(maPmMstrCommonDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);

	}
}