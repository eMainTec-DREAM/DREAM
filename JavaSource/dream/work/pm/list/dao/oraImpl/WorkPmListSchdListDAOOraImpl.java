package dream.work.pm.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.pm.list.dao.WorkPmListSchdListDAO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;

/**
 * 예방작업 일자 목록 dao
 * @author  kim21017
 * @version $Id: WorkPmListSchdListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="workPmListSchdListDAOTarget"
 * @spring.txbn id="workPmListSchdListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkPmListSchdListDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkPmListSchdListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: WorkPmListSchdListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPmMstrCommonDTO
     * @param loginUser
     * @return List
     */
    public List findSchList(MaPmMstrCommonDTO maPmMstrCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT												");
        query.append("       '' 							seqNo,			");
        query.append("       '' 							isDelCheck,		");
        query.append("       x.plan_date 					planDate,		");
        query.append("       x.pmeventsched_id				pmEventSchedId	");
        query.append("FROM   TAPMEVENTSCHED x	 							");
        query.append("WHERE 1=1												");
        query.append(this.getWhere(maPmMstrCommonDTO,loginUser));
        query.getOrderByQuery("x.pmeventsched_id", maPmMstrCommonDTO.getOrderBy(), maPmMstrCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maPmMstrCommonDTO.getIsLoadMaxCount(), maPmMstrCommonDTO.getFirstRow()));
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: WorkPmListSchdListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteSchList(String id, String compNo)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("DELETE FROM TAPMEVENTSCHED				");
    	query.append("WHERE  pmeventsched_id 	= '"+id+"'		");
    	query.append("  AND  comp_no		= '"+compNo+"'		");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(MaPmMstrCommonDTO maPmMstrCommonDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	query.getAndQuery("x.pm_id", maPmMstrCommonDTO.getPmId());
    	query.getAndQuery("x.comp_no", loginUser.getCompNo());
    	if (!"".equals(maPmMstrCommonDTO.getPmEventSchedId()))
        {
            query.getAndQuery("x.pmeventsched_id", maPmMstrCommonDTO.getPmEventSchedId());
            return query.toString();
        }
    	
    	return query.toString();
    }

	public String findTotalCount(MaPmMstrCommonDTO maPmMstrCommonDTO, User loginUser) throws Exception 
	{
		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT                    ");
        query.append("       COUNT(1)           ");
        query.append("FROM   TAPMEVENTSCHED x	");
        query.append("WHERE 1=1					");
        query.append(this.getWhere(maPmMstrCommonDTO,loginUser));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
	}
}