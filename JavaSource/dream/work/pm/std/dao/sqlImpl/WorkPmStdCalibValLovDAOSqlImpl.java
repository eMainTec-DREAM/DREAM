package dream.work.pm.std.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.work.pm.std.dao.WorkPmStdCalibValLovDAO;
import dream.work.pm.std.dto.WorkPmStdCalibValLovDTO;

/**
 * 교정표준값 LOV - List DAO implements
 * @author kim21017
 * @version $Id: WorkPmStdCalibValLovDAOSqlImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workPmStdCalibValLovDAOTarget"
 * @spring.txbn id="workPmStdCalibValLovDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkPmStdCalibValLovDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkPmStdCalibValLovDAO
{
	public List findList(WorkPmStdCalibValLovDTO workPmStdCalibValLovDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap) throws Exception
    { 
        QuerySqlBuffer query = new QuerySqlBuffer();
       
        query.append("SELECT																			");
        query.append("		x.pmcalibstdtp_id										AS pmCalibStdTpId	");
        query.append("		,x.pmcalibstdtp_no										AS pmCalibStdTpNo	");
        query.append("		,x.measure_range										AS measureRange		");
        query.append("		,x.pmc_type												AS pmcTypeId		");
        query.append("		,dbo.SFACODE_TO_DESC(x.pmc_type,'PMC_TYPE','SYS',''								");
        query.append("							,'"+user.getLangId()+"') 			AS pmcTypeDesc		");
        query.append("FROM TAPMCALIBSTDTP x																");
    	query.append("WHERE  1=1																		");
    	query.append(this.getWhere(workPmStdCalibValLovDTO, user, columnMap, conditionMap));
        query.getOrderByQuery("x.pmcalibstdtp_id", "x.pmcalibstdtp_no", workPmStdCalibValLovDTO.getOrderBy(), workPmStdCalibValLovDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workPmStdCalibValLovDTO.getIsLoadMaxCount(), workPmStdCalibValLovDTO.getFirstRow()));
    } 

	private String getWhere(WorkPmStdCalibValLovDTO workPmStdCalibValLovDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getAndQuery("comp_no", conditionMap);
        query.getAndQuery("measure_range", workPmStdCalibValLovDTO.getFilterPmCalibStdTpDesc());
        query.getAndQuery("pmc_type", conditionMap);

    	return query.toString();
    }

    public String findTotalCount(WorkPmStdCalibValLovDTO workPmStdCalibValLovDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT						");
        query.append("		COUNT(1)				");
        query.append("FROM TAPMCALIBSTDTP x			");
    	query.append("WHERE  1=1					");
    	query.append(this.getWhere(workPmStdCalibValLovDTO, user, columnMap, conditionMap));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
}