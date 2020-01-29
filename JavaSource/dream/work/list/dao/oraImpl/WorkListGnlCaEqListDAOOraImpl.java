package dream.work.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.list.dao.WorkListGnlCaEqListDAO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * 작업상세  - 검교정 - 측정값 목록 dao
 * @author  kim21017
 * @version $Id: WorkListGnlCaEqListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="workListGnlCaEqListDAOTarget"
 * @spring.txbn id="workListGnlCaEqListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkListGnlCaEqListDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkListGnlCaEqListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: WorkListGnlCaEqListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @param workListGnlCaEqListDTO
     * @param loginUser
     * @return List
     */
    public List findCavalList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT											");
        query.append("       '' seqNo,									");
        query.append("       '' isDelCheck								");
        query.append("       ,y.description								");
        query.append("       ,y.item_no itemNo							");
        query.append("       ,x.serial_no serialNo						");
        query.append("       ,x.wo_no woNo								");
        query.append("       ,x.next_plan_date nextPlanDate				");
        query.append("       ,wocalibstdeq_id wocalibstdeqId			");
        query.append("       ,wkor_id wkorId							");
        query.append("       ,sopdoc_no calibSopdocNo					");
        query.append("		 ,SFACODE_TO_DESC(sopdoc_no,'CALIB_SOPDOC_NO','USR','"+loginUser.getCompNo()+"','"+loginUser.getLangId()+"')	calibSopdocNoDesc	");
        query.append("FROM   TAWOCALIBSTDEQ x INNER JOIN TAEQUIPMENT y 	");
        query.append("  ON   x.equip_id = y.equip_id					");
        query.append("WHERE  1=1									");
        query.append(this.getWhere(maWoResultMstrCommonDTO,loginUser));
        query.getOrderByQuery("x.wocalibstdeq_id", maWoResultMstrCommonDTO.getOrderBy(), maWoResultMstrCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maWoResultMstrCommonDTO.getIsLoadMaxCount(), maWoResultMstrCommonDTO.getFirstRow()));
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: WorkListGnlCaEqListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteCavalList(String id, String compNo)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("DELETE FROM TAWOCALIBSTDEQ				");
    	query.append("WHERE  wocalibstdeq_id 	= '"+id+"'		");
    	query.append("  AND  comp_no			= '"+compNo+"'	");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	query.getAndQuery("x.wkor_id", maWoResultMstrCommonDTO.getWkOrId());
    	query.getAndQuery("x.comp_no", loginUser.getCompNo());
    	if (!"".equals(maWoResultMstrCommonDTO.getWocalibstdeqId()))
        {
            query.getAndQuery("x.wocalibstdeq_id", maWoResultMstrCommonDTO.getWocalibstdeqId());
            return query.toString();
        }
    	
    	return query.toString();
    }

	public String findTotalCount(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User loginUser) throws Exception 
	{
		QueryBuffer query = new QueryBuffer();
		
		query.append(" SELECT count(1)              ");
        query.append("FROM   TAWOCALIBSTDEQ x INNER JOIN TAEQUIPMENT y 	");
        query.append("  ON   x.equip_id = y.equip_id					");
        query.append("WHERE  1=1									");
        query.append(this.getWhere(maWoResultMstrCommonDTO,loginUser));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
	}
}