package dream.work.pm.std.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.work.pm.std.dao.WorkPmStdCalibListDAO;
import dream.work.pm.std.dto.WorkPmStdCalibCommonDTO;

/**
 * 교정표준값 타입 - List DAO implements
 * @author kim21017
 * @version $Id: WorkPmStdCalibListDAOSqlImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workPmStdCalibListDAOTarget"
 * @spring.txbn id="workPmStdCalibListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkPmStdCalibListDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkPmStdCalibListDAO
{
	public List findList(WorkPmStdCalibCommonDTO workPmStdCalibCommonDTO, User user) throws Exception
    { 
        QuerySqlBuffer query = new QuerySqlBuffer();
       
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  ");
        query.append("SELECT																			");
        query.append("		''														AS seqNo			");
        query.append("		,''														AS isDelCheck		");
        query.append("		,x.pmcalibstdtp_id										AS pmCalibStdTpId	");
        query.append("		,x.pmcalibstdtp_no										AS pmCalibStdTpNo	");
        query.append("		,x.pmc_type												AS pmcTypeId		");
        query.append("		,dbo.SFACODE_TO_DESC(x.pmc_type,'PMC_TYPE','SYS',''							");
        query.append("							,'"+user.getLangId()+"') 			AS pmcTypeDesc		");
        query.append("		,x.measure_range										AS measureRange		");
        query.append("		,x.remark												AS remark			");
        query.append("FROM TAPMCALIBSTDTP x																");
    	query.append("WHERE  1=1																		");
    	query.append(this.getWhere(workPmStdCalibCommonDTO, user));
        query.getOrderByQuery("x.pmcalibstdtp_id", "x.pmcalibstdtp_no", workPmStdCalibCommonDTO.getOrderBy(), workPmStdCalibCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workPmStdCalibCommonDTO.getIsLoadMaxCount(), workPmStdCalibCommonDTO.getFirstRow()));
    } 

	private String getWhere(WorkPmStdCalibCommonDTO workPmStdCalibCommonDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        if(!"".equals(workPmStdCalibCommonDTO.getPmCalibStdTpId())){
        	query.getAndNumKeyQuery("x.pmcalibstdtp_id", workPmStdCalibCommonDTO.getPmCalibStdTpId());
        	return query.toString();
        }
        //검교정 작업 타입
    	query.getSysCdQuery("x.pmc_type", workPmStdCalibCommonDTO.getFilterPmcTypeId(), workPmStdCalibCommonDTO.getFilterPmcTypeDesc(), "PMC_TYPE", user.getCompNo(),user.getLangId());
      
        //교정표준값 No
        query.getLikeQuery("x.pmcalibstdtp_no", workPmStdCalibCommonDTO.getFilterPmCalibStdTpNo());
        
        //중복확인용
        if(!"".equals(workPmStdCalibCommonDTO.getValidId()))
     	   query.getAndQuery("x.pmcalibstdtp_id", "+-"+workPmStdCalibCommonDTO.getValidId());
        
        query.getLikeQuery("x.pmc_type", workPmStdCalibCommonDTO.getValidPmcTypeId());
        

    	return query.toString();
    }

    public int deleteList(String id, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("DELETE FROM TAPMCALIBSTDTP			");
        query.append("WHERE  comp_no 				= ?		");
        query.append("  AND  pmcalibstdtp_id		= ?		");
        
        Object[] objects = new Object[] {   
                user.getCompNo()
                ,id
        	};
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    public int deleteValList(String id, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("DELETE FROM TAPMCALIBSTDVALUE			");
        query.append("WHERE  comp_no 			= ?			");
        query.append("  AND  pmcalibstdtp_id  	= ?			");
        
        Object[] objects = new Object[] {   
                user.getCompNo()
                ,id
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    public String findTotalCount(WorkPmStdCalibCommonDTO workPmStdCalibCommonDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  ");
        query.append("SELECT						");
        query.append("		COUNT(1)				");
        query.append("FROM TAPMCALIBSTDTP x			");
    	query.append("WHERE  1=1					");
    	query.append(this.getWhere(workPmStdCalibCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
}