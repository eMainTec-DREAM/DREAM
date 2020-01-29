package dream.work.let.permit.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.let.permit.dao.WorkLetPermitCraftListDAO;
import dream.work.let.permit.dto.WorkLetPermitCraftListDTO;
import dream.work.let.permit.dto.WorkLetPermitDetailDTO;

/**
 * 안전작업허가서유형 - 작업자 목록 dao
 * @author  syyang
 * @version $Id: WorkLetPermitCraftListDAO.java,v 1.0 2015/12/02 09:14:12 syyang Exp $
 * @since   1.0
 * @spring.bean id="workLetPermitCraftListDAOTarget"
 * @spring.txbn id="workLetPermitCraftListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkLetPermitCraftListDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkLetPermitCraftListDAO
{
    /**
     * grid find
     * @author  syyang
     * @version $Id: WorkLetPermitCraftListDAO.java,v 1.0 2015/12/02 09:14:12 syyang Exp $
     * @since   1.0
     * 
     * @param workLetPermitDetailDTO
     * @param workLetPermitCraftListDTO
     * @param loginUser
     * @return List
     */
    public List findCraftList(WorkLetPermitDetailDTO workLetPermitDetailDTO, WorkLetPermitCraftListDTO workLetPermitCraftListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT															");
        query.append("		'' 										AS isDelCheck		");
        query.append("		,x.step_num 							AS stepNum			");
        query.append("		,x.craft_type 							AS craftType		");
        query.append("		,SFACODE_TO_DESC(x.craft_type, 'CRAFT_TYPE', 'SYS', '', '"+loginUser.getLangId()+"')	AS craftTypeDesc	");
        query.append("		,x.work_name 							AS workName			");
        query.append("		,TO_CHAR(TO_DATE(x.start_date,'yyyy-mm-dd'),'yyyy-mm-dd')	AS startDate	");
        query.append("		,TO_CHAR(TO_DATE(x.start_time,'hh24mi'),'hh24:mi')			AS startTime	");
        query.append("		,TO_CHAR(TO_DATE(x.end_date,'yyyy-mm-dd'),'yyyy-mm-dd')		AS endDate	");
        query.append("		,TO_CHAR(TO_DATE(x.end_time,'hh24mi'),'hh24:mi')			AS endTime	");
        query.append("		,x.work_time 							AS workTime			");
        query.append("		,x.remark 								AS remark			");
        query.append("		,x.woletlistcraft_id 					AS woLetListCraftId	");
        query.append("		,x.woletlist_id 						AS woLetListId		");
        query.append("FROM   TAWOLETLISTCRAFT x											");
        query.append("WHERE  1=1														");
        query.append(this.getWhere(workLetPermitDetailDTO,workLetPermitCraftListDTO,loginUser));
        query.getOrderByQuery("x.step_num", workLetPermitCraftListDTO.getOrderBy(), workLetPermitCraftListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workLetPermitCraftListDTO.getIsLoadMaxCount(), workLetPermitCraftListDTO.getFirstRow()));
    }
    
    /**
     * delete
     * @author syyang
     * @version $Id: WorkLetPermitCraftListDAO.java,v 1.0 20155/12/02 08:25:47 syyang Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteCraftList(String id, String compNo)
    {
    	QueryBuffer query = new QueryBuffer();

    	String woLetListCraftId = id;
    	
    	query.append("DELETE FROM TAWOLETLISTCRAFT							");
    	query.append("WHERE  woletlistcraft_id 	= '"+woLetListCraftId+"'	");
    	query.append("  AND  comp_no		= '"+compNo+"'					");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(WorkLetPermitDetailDTO workLetPermitDetailDTO, WorkLetPermitCraftListDTO workLetPermitCraftListDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.getAndQuery("x.comp_no", loginUser.getCompNo());
    	
    	query.getAndQuery("x.woletlist_id", workLetPermitDetailDTO.getWoLetListId());
    	
    	if (!"".equals(workLetPermitCraftListDTO.getWoLetListCraftId()))
        {
            query.getAndQuery("x.woletlistcraft_id", workLetPermitCraftListDTO.getWoLetListCraftId());
            return query.toString();
        }
    	
    	return query.toString();
    }

	@Override
	public String findTotalCount(WorkLetPermitDetailDTO workLetPermitDetailDTO,
			WorkLetPermitCraftListDTO workLetPermitCraftListDTO, User loginUser) throws Exception {

		QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT											");
        query.append("       COUNT(1)                                   ");
        query.append("FROM   TAWOLETLISTCRAFT x							");
        query.append("WHERE  1=1										");
        query.append(this.getWhere(workLetPermitDetailDTO,workLetPermitCraftListDTO,loginUser));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);

	}
	
}