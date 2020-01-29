package dream.work.pm.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.pm.list.dao.WorkPmMsTimeUInsListDAO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.WorkPmMsTimeUInsListDTO;

/**
 * 작업시간 list dao
 * @author  js.lee
 * @version $Id: Exp $
 * @since   1.0
 * @spring.bean id="workPmMsTimeUInsListDAOTarget"
 * @spring.txbn id="workPmMsTimeUInsListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkPmMsTimeUInsListDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkPmMsTimeUInsListDAO
{
    /**
     * grid find
     * @author  js.lee
     * @version $Id: Exp $
     * @since   1.0
     * 
     * @param maPmMstrCommonDTO
     * @param loginUser
     * @return List
     */
    public List findMsTimeList(MaPmMstrCommonDTO maPmMstrCommonDTO, WorkPmMsTimeUInsListDTO workPmMsTimeUInsListDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                ");
        query.append("       ''                             seqNo     		");
        query.append("       ,''                            isDelCheck		");
        query.append("       ,x.pmmeasuretime_id   			pmMsTimeId		");
        query.append("       ,(select key_name								");
        query.append("       from talang									");
        query.append("       where key_type = 'CODESET' and lang = '"+user.getLangId()+"'	");
        query.append("         and key_no = 'MEASURE_TIME.'|| x.measure_time)  measureTime	");
        query.append("       ,x.remark                    	remark			");
        query.append("FROM   TAPMMEASURETIME x                              ");
        query.append("WHERE 1=1                                             ");
        query.append(this.getWhere(maPmMstrCommonDTO, workPmMsTimeUInsListDTO, user));
        query.getOrderByQuery("x.pmmeasuretime_id", "x.measure_time asc", workPmMsTimeUInsListDTO.getOrderBy(), workPmMsTimeUInsListDTO.getDirection());

        return getJdbcTemplate().queryForList(query.toString(workPmMsTimeUInsListDTO.getIsLoadMaxCount(), workPmMsTimeUInsListDTO.getFirstRow()));
    }
    
    /**
     * delete
     * @author js.lee
     * @version $Id: Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteMsTimeList(String id, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("  DELETE FROM TAPMMEASURETIME     ");
    	query.append("  WHERE COMP_NO 			= ?     ");
    	query.append("    AND PMMEASURETIME_ID	= ?     ");

    	
    	Object[]  objects = new Object[] {
    			loginUser.getCompNo()
    			,id
        };
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    private String getWhere(MaPmMstrCommonDTO maPmMstrCommonDTO, WorkPmMsTimeUInsListDTO workPmMsTimeUInsListDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	// 그리드 한줄 재조회
        if (!"".equals(workPmMsTimeUInsListDTO.getPmMsTimeId()))
        {
            query.getAndQuery("x.pmmeasuretime_id", workPmMsTimeUInsListDTO.getPmMsTimeId());
            return query.toString();
        }
        
    	
    	query.getStringEqualQuery("x.pm_id", maPmMstrCommonDTO.getPmId());
    	query.getStringEqualQuery("x.comp_no", loginUser.getCompNo());
        
    	return query.toString();
    }

	@Override
	public String findTotalCount(MaPmMstrCommonDTO maPmMstrCommonDTO, WorkPmMsTimeUInsListDTO workPmMsTimeUInsListDTO, User loginUser) throws Exception {

        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT					");
        query.append("       COUNT(1)           ");
        query.append("FROM   TAPMMEASURETIME x		");
        query.append("WHERE 1=1					");
        query.append(this.getWhere(maPmMstrCommonDTO,workPmMsTimeUInsListDTO,loginUser));
	
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
	}
}