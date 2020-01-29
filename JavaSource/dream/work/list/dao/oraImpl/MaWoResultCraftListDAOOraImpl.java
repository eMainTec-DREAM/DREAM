package dream.work.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
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
public class MaWoResultCraftListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaWoResultCraftListDAO
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
        QueryBuffer query = new QueryBuffer();
        
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
        query.append("		TO_CHAR(TO_DATE (x.start_date,'yyyy-mm-dd'),'yyyy-mm-dd')	startDate,	");
        query.append("		TO_CHAR(TO_DATE (x.start_time,'hh24mi'),'hh24:mi')		startTime,	");
        query.append("		TO_CHAR(TO_DATE (x.end_date,'yyyy-mm-dd'),'yyyy-mm-dd')		endDate,	");
        query.append("		TO_CHAR(TO_DATE (x.end_time,'hh24mi'),'hh24:mi')		endTime,	");
        query.append("       x.work_time 			workTime,			");
        query.append("       x.remark 				remark				");
        query.append("FROM   TAWOCRAFT x								");
        query.append("WHERE  1=1										");
        query.append(this.getWhere(maWoResultMstrCommonDTO,maWoResultCraftListDTO,loginUser));
        query.getOrderByQuery("x.wkor_id", maWoResultCraftListDTO.getOrderBy(), maWoResultCraftListDTO.getDirection());
        
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
    	QueryBuffer query = new QueryBuffer();

    	String woCraftId=id;
    	
    	query.append("DELETE FROM TAWOCRAFT							");
    	query.append("WHERE  wocraft_id 	= '"+woCraftId+"'		");
    	query.append("  AND  comp_no		= '"+compNo+"'			");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultCraftListDTO maWoResultCraftListDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
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

		QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT											");
        query.append("       COUNT(1)                                   ");
        query.append("FROM   TAWOCRAFT x								");
        query.append("WHERE  1=1										");
        query.append(this.getWhere(maWoResultMstrCommonDTO,maWoResultCraftListDTO,loginUser));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);

	}
	
}