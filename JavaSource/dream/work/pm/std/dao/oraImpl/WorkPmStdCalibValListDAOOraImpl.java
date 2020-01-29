package dream.work.pm.std.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.pm.std.dao.WorkPmStdCalibValListDAO;
import dream.work.pm.std.dto.WorkPmStdCalibCommonDTO;
import dream.work.pm.std.dto.WorkPmStdCalibValListDTO;

/**
 * 표준교정값 - List DAO implements
 * @author kim21017
 * @version $Id: WorkPmStdCalibValListDAOOraImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workPmStdCalibValListDAOTarget"
 * @spring.txbn id="workPmStdCalibValListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkPmStdCalibValListDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkPmStdCalibValListDAO
{
	public List findList(WorkPmStdCalibCommonDTO workPmStdCalibCommonDTO, WorkPmStdCalibValListDTO workPmStdCalibValListDTO, User user) throws Exception
    { 
        QueryBuffer query = new QueryBuffer();
       
        query.append("SELECT													");
        query.append("		''								AS seqNo			");
        query.append("		,''								AS isDelCheck		");
        query.append("		,x.pmcalibstdvalue_id			AS pmCalibStdValueId");
        query.append("		,x.pmcalibstdtp_id				AS pmCalibStdTpId	");
        query.append("		,x.calib_point_type				AS calibPointTypeId	");
        query.append("		,SFACODE_TO_DESC(x.calib_point_type,'CALIB_POINT_TYPE','SYS',''				");
        query.append("							,'"+user.getLangId()+"') 			AS calibPointType	");
        query.append("		,x.calib_point					AS calibPoint		");
        query.append("		,x.allow_value					AS allowValue		");
        query.append("		,x.asf_std_value				AS asfStdValue		");
        query.append("		,x.asl_std_value				AS aslStdValue		");
        query.append("		,x.ord_no						AS ordNo			");
        query.append("		,x.remark						AS remark			");
        query.append("FROM TAPMCALIBSTDVALUE x									");
    	query.append("WHERE  1=1												");
    	query.getStringEqualQuery("x.comp_no", user.getCompNo());
    	query.getAndNumKeyQuery("x.pmcalibstdtp_id", workPmStdCalibCommonDTO.getPmCalibStdTpId());

    	if (!"".equals(workPmStdCalibValListDTO.getPmCalibStdValueId()))
        {
            query.getAndQuery("x.pmcalibstdvalue_id", workPmStdCalibValListDTO.getPmCalibStdValueId());
        }
        query.getOrderByQuery("x.ord_no", workPmStdCalibCommonDTO.getOrderBy(), workPmStdCalibCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workPmStdCalibCommonDTO.getIsLoadMaxCount(), workPmStdCalibCommonDTO.getFirstRow()));
    } 
    public int deleteList(String id, User user) throws Exception
    {
    	QueryBuffer query = new QueryBuffer();

        query.append("DELETE FROM TAPMCALIBSTDVALUE			");
        query.append("WHERE  comp_no 				= ?		");
        query.append("  AND  pmcalibstdvalue_id  	= ?		");
        
        Object[] objects = new Object[] {   
                user.getCompNo()
                ,id
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
	public String findTotalCount(WorkPmStdCalibCommonDTO workPmStdCalibCommonDTO, WorkPmStdCalibValListDTO workPmStdCalibValListDTO, User user)
			throws Exception {
		
		QueryBuffer query = new QueryBuffer();
        query.append("SELECT													");
        query.append("		COUNT(1)											");
        query.append("FROM TAPMCALIBSTDVALUE x									");
    	query.append("WHERE  1=1												");
    	query.getStringEqualQuery("x.comp_no", user.getCompNo());
    	query.getAndNumKeyQuery("x.pmcalibstdtp_id", workPmStdCalibCommonDTO.getPmCalibStdTpId());
    	
    	List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
	}
}