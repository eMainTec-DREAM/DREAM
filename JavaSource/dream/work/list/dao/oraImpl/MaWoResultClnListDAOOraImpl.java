package dream.work.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.list.dao.MaWoResultClnListDAO;
import dream.work.list.dto.MaWoResultClnListDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * 작업결과 설비 목록 dao
 * @author  kim21017
 * @version $Id: MaWoResultClnListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maWoResultClnListDAOTarget"
 * @spring.txbn id="maWoResultClnListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaWoResultClnListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaWoResultClnListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaWoResultClnListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @param maWoResultClnListDTO
     * @param loginUser
     * @return List
     */
    public List findClnList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultClnListDTO maWoResultClnListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
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
        query.append("       x.woequip_id 					woEquipId		");
        query.append("FROM   TAWOEQUIP x	 								");
        query.append("WHERE 1=1												");
        query.append(this.getWhere(maWoResultMstrCommonDTO,maWoResultClnListDTO,loginUser));
        query.getOrderByQuery("x.woequip_id", maWoResultClnListDTO.getOrderBy(), maWoResultClnListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maWoResultClnListDTO.getIsLoadMaxCount(), maWoResultClnListDTO.getFirstRow()));
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaWoResultClnListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteClnList(String id, String compNo)
    {
    	QueryBuffer query = new QueryBuffer();

    	String woEquipId=id;

    	query.append("DELETE FROM TAWOEQUIP							");
    	query.append("WHERE  woequip_id 	= '"+woEquipId+"'		");
    	query.append("  AND  comp_no		= '"+compNo+"'			");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultClnListDTO maWoResultClnListDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	query.getAndQuery("x.wkor_id", maWoResultMstrCommonDTO.getWkOrId());
    	query.getAndQuery("x.comp_no", loginUser.getCompNo());
    	if (!"".equals(maWoResultClnListDTO.getWoEquipId()))
        {
            query.getAndQuery("x.woequip_id", maWoResultClnListDTO.getWoEquipId());
            return query.toString();
        }
    	
    	return query.toString();
    }

    public String findTotalCount(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultClnListDTO maWoResultClnListDTO, User loginUser) throws Exception 
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("SELECT                ");
        query.append("       COUNT(1)       ");
        query.append("FROM   TAWOEQUIP x	");
        query.append("WHERE 1=1				");
        query.append(this.getWhere(maWoResultMstrCommonDTO,maWoResultClnListDTO,loginUser));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
}