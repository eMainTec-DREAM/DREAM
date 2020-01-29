package dream.work.list.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.work.list.dao.MaWoResultCraftListDAO;
import dream.work.list.dto.MaWoResultCraftListDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * 작업결과 작업자 목록 dao
 * @author  kim21017
 * @version $Id: MaWoResultCraftListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maWoResultCraftListDAOTarget"
 * @spring.txbn id="maWoResultCraftListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaWoResultCraftListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaWoResultCraftListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaWoResultCraftListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @param maWoResultCraftListDTO
     * @param loginUser
     * @return List
     */
    public List findCraftList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultCraftListDTO maWoResultCraftListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT											");
        query.append("       '' seqNo,									");
        query.append("       '' isDelCheck,								");
        query.append("       x.wkor_id 				wkOrId,				");
        query.append("       x.wocraft_id 			woCraftId,			");
        query.append("       (SELECT emp_no								");
        query.append("          FROM TAEMP								");
        query.append("         WHERE comp_no = x.comp_no				");
        query.append("           AND emp_id = x.emp_id) empNo,			");
        query.append("       (SELECT emp_name							");
        query.append("          FROM TAEMP								");
        query.append("         WHERE comp_no = x.comp_no				");
        query.append("           AND emp_id = x.emp_id) empDesc,		");
        query.append("		CONVERT(VARCHAR, CONVERT(DATE, x.start_date), 120)					startDate,	");
        query.append("		SUBSTRING(x.start_time,1,2)+':'+SUBSTRING(x.start_time,3,4)	startTime,	");
        query.append("		CONVERT(varchar, CONVERT(DATE, x.end_date), 120)					endDate,	");
        query.append("		SUBSTRING(x.end_time,1,2)+':'+SUBSTRING(x.end_time,3,4)		endTime,	");
        query.append("       x.work_time 			workTime,			");
        query.append("       x.remark 				remark				");
        query.append("FROM   TAWOCRAFT x								");
        query.append("WHERE  1=1										");
        query.append(this.getWhere(maWoResultMstrCommonDTO,maWoResultCraftListDTO,loginUser));
        query.getOrderByQuery("x.wocraft_id", "x.wkor_id", maWoResultCraftListDTO.getOrderBy(), maWoResultCraftListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maWoResultCraftListDTO.getIsLoadMaxCount(), maWoResultCraftListDTO.getFirstRow()));
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaWoResultCraftListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
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
    	
    	query.append("DELETE FROM TAWOCRAFT							");
    	query.append("WHERE  wocraft_id 	= '"+woCraftId+"'		");
    	query.append("  AND  comp_no		= '"+compNo+"'			");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultCraftListDTO maWoResultCraftListDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	query.getAndQuery("x.wkor_id", maWoResultMstrCommonDTO.getWkOrId());
    	query.getAndQuery("x.comp_no", loginUser.getCompNo());
    	if (!"".equals(maWoResultCraftListDTO.getWoCraftId()))
        {
            query.getAndQuery("x.wocraft_id", maWoResultCraftListDTO.getWoCraftId());
            return query.toString();
        }
    	
    	return query.toString();
    }

	@Override
	public String findTotalCount(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,
			MaWoResultCraftListDTO maWoResultCraftListDTO, User loginUser) throws Exception {

		QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT											");
        query.append("       COUNT(1)                                   ");
        query.append("FROM   TAWOCRAFT x								");
        query.append("WHERE  1=1										");
        query.append(this.getWhere(maWoResultMstrCommonDTO,maWoResultCraftListDTO,loginUser));
    
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
	}
	
}