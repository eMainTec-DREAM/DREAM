package dream.work.list.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.work.list.dao.WoPlanCraftListDAO;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanCraftListDTO;

/**
 * 작업계획목록 작업자 목록 dao
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @spring.bean id="woPlanCraftListDAOTarget"
 * @spring.txbn id="woPlanCraftListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WoPlanCraftListDAOSqlImpl extends BaseJdbcDaoSupportSql implements WoPlanCraftListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanCommonDTO
     * @param woPlanCraftListDTO
     * @param loginUser
     * @return List
     */
    public List findCraftList(WoPlanCommonDTO woPlanCommonDTO, WoPlanCraftListDTO woPlanCraftListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT											");
        query.append("       '' seqNo,									");
        query.append("       '' isDelCheck,								");
        query.append("       x.wkor_id 				wkOrId,				");
        query.append("       x.woplancraft_id 		woCraftId,			");
        query.append("       (SELECT emp_no								");
        query.append("          FROM TAEMP								");
        query.append("         WHERE comp_no = x.comp_no				");
        query.append("           AND emp_id = x.emp_id) empNo,			");
        query.append("       (SELECT emp_name							");
        query.append("          FROM TAEMP								");
        query.append("         WHERE comp_no = x.comp_no				");
        query.append("           AND emp_id = x.emp_id) empDesc,		");
        query.append("		CONVERT(VARCHAR, CONVERT(DATE, x.start_date), 120)					startDate,	");
        query.append("		SUBSTRING(x.start_time,1,2)+':'+SUBSTRING(x.start_time,3,4)+':00'	startTime,	");
        query.append("		CONVERT(varchar, CONVERT(DATE, x.end_date), 120)					endDate,	");
        query.append("		SUBSTRING(x.end_time,1,2)+':'+SUBSTRING(x.end_time,3,4)+':00'		endTime,	");
        query.append("       x.work_time 			workTime,			");
        query.append("       x.remark 				remark				");
        query.append("FROM   TAWOPLANCRAFT x							");
        query.append("WHERE  1=1										");
        query.append(this.getWhere(woPlanCommonDTO,woPlanCraftListDTO,loginUser));
        query.getOrderByQuery("x.woplancraft_id", "x.wkor_id", woPlanCraftListDTO.getOrderBy(), woPlanCraftListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(woPlanCraftListDTO.getIsLoadMaxCount(), woPlanCraftListDTO.getFirstRow()));

    }
    
    /**
     * delete
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteCraftList(String id, String compNo)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	String woCraftId=id;
    	
    	query.append("DELETE FROM TAWOPLANCRAFT						");
    	query.append("WHERE  woplancraft_id 	= '"+woCraftId+"'	");
    	query.append("  AND  comp_no		= '"+compNo+"'			");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(WoPlanCommonDTO woPlanCommonDTO, WoPlanCraftListDTO woPlanCraftListDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	query.getAndQuery("x.wkor_id", woPlanCommonDTO.getWkOrId());
    	query.getAndQuery("x.comp_no", loginUser.getCompNo());
    	if (!"".equals(woPlanCraftListDTO.getWoCraftId()))
        {
            query.getAndQuery("x.woplancraft_id", woPlanCraftListDTO.getWoCraftId());
            return query.toString();
        }
    	
    	return query.toString();
    }

	@Override
	public String findTotalCount(WoPlanCommonDTO woPlanCommonDTO, WoPlanCraftListDTO woPlanCraftListDTO, User loginUser)
			throws Exception {
		QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT						");
        query.append("       COUNT(1)               ");
        query.append("FROM   TAWOPLANCRAFT x		");
        query.append("WHERE  1=1					");
        query.append(this.getWhere(woPlanCommonDTO,woPlanCraftListDTO,loginUser));
	
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);

	}
}