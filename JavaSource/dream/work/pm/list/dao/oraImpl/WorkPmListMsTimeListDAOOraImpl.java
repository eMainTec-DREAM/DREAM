package dream.work.pm.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.pm.list.dao.WorkPmListMsTimeListDAO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.WorkPmListMsTimeListDTO;

/**
 * 작업시간 list dao
 * @author  js.lee
 * @version $Id: Exp $
 * @since   1.0
 * @spring.bean id="workPmListMsTimeListDAOTarget"
 * @spring.txbn id="workPmListMsTimeListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkPmListMsTimeListDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkPmListMsTimeListDAO
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
    public List findMsTimeList(MaPmMstrCommonDTO maPmMstrCommonDTO, WorkPmListMsTimeListDTO workPmListMsTimeListDTO, User user)
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
        query.append(this.getWhere(maPmMstrCommonDTO, workPmListMsTimeListDTO, user));
        query.getOrderByQuery("x.pmmeasuretime_id", "x.measure_time", workPmListMsTimeListDTO.getOrderBy(), workPmListMsTimeListDTO.getDirection());

        return getJdbcTemplate().queryForList(query.toString(workPmListMsTimeListDTO.getIsLoadMaxCount(), workPmListMsTimeListDTO.getFirstRow()));
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
    
    private String getWhere(MaPmMstrCommonDTO maPmMstrCommonDTO, WorkPmListMsTimeListDTO workPmListMsTimeListDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	// 그리드 한줄 재조회
        if (!"".equals(workPmListMsTimeListDTO.getPmMsTimeId()))
        {
            query.getAndQuery("x.pmmeasuretime_id", workPmListMsTimeListDTO.getPmMsTimeId());
            return query.toString();
        }
        
    	
    	query.getStringEqualQuery("x.pm_id", maPmMstrCommonDTO.getPmId());
    	query.getStringEqualQuery("x.comp_no", loginUser.getCompNo());
        
    	return query.toString();
    }

	@Override
	public String findTotalCount(MaPmMstrCommonDTO maPmMstrCommonDTO, WorkPmListMsTimeListDTO workPmListMsTimeListDTO, User loginUser) throws Exception {

        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT					");
        query.append("       COUNT(1)           ");
        query.append("FROM   TAPMMEASURETIME x		");
        query.append("WHERE 1=1					");
        query.append(this.getWhere(maPmMstrCommonDTO,workPmListMsTimeListDTO,loginUser));
	
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
	}
}