package dream.work.pm.std.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.work.pm.std.dao.WorkPmStdCalibValDetailDAO;
import dream.work.pm.std.dto.WorkPmStdCalibCommonDTO;
import dream.work.pm.std.dto.WorkPmStdCalibValDetailDTO;
import dream.work.pm.std.dto.WorkPmStdCalibValListDTO;

/**
 * 표준교정값 - Detail DAO implements
 * @author kim21017
 * @version $Id: WorkPmStdCalibValDetailDAOOraImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workPmStdCalibValDetailDAOTarget"
 * @spring.txbn id="workPmStdCalibValDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkPmStdCalibValDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkPmStdCalibValDetailDAO
{
	
    public WorkPmStdCalibValDetailDTO findDetail(WorkPmStdCalibCommonDTO workPmStdCalibCommonDTO, WorkPmStdCalibValListDTO workPmStdCalibValListDTO, User user) 
    {
    	QueryBuffer query = new QueryBuffer();


    	query.append("SELECT													");
        query.append("		x.pmcalibstdvalue_id			AS pmCalibStdValueId");
        query.append("		,x.pmcalibstdtp_id				AS pmCalibStdTpId	");
        query.append("		,x.calib_point_type				AS calibPointTypeId	");
        query.append("		,SFACODE_TO_DESC(x.calib_point_type,'CALIB_POINT_TYPE','SYS',''	");
        query.append("							,?) 		AS calibPointTypeDesc	");
        query.append("		,x.calib_point					AS calibPoint		");
        query.append("		,x.allow_value					AS allowValue		");
        query.append("		,x.asf_std_value				AS asfStdValue		");
        query.append("		,x.asl_std_value				AS aslStdValue		");
        query.append("		,x.ord_no						AS ordNo			");
        query.append("		,x.remark						AS remark			");
        query.append("FROM TAPMCALIBSTDVALUE x									");
    	query.append("WHERE  1=1												");
    	query.append("AND  x.pmcalibstdtp_id	= ?								");
    	query.append("AND  x.pmcalibstdvalue_id	= ?								");
    	query.append("AND  x.comp_no    		= ?								");
        
        Object[] objects = new Object[] {
        		user.getLangId()
        		,workPmStdCalibCommonDTO.getPmCalibStdTpId()
        		,workPmStdCalibValListDTO.getPmCalibStdValueId()
    			,user.getCompNo()
    	};
    
        WorkPmStdCalibValDetailDTO workPmStdCalibValDetailDTO = 
        		(WorkPmStdCalibValDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new WorkPmStdCalibValDetailDTO()));
        
        return workPmStdCalibValDetailDTO;
        
    }

    public int insertDetail(WorkPmStdCalibCommonDTO workPmStdCalibCommonDTO,WorkPmStdCalibValDetailDTO workPmStdCalibValDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;

    	query.append("INSERT INTO TAPMCALIBSTDVALUE(	");
    	query.append("	comp_no							");
    	query.append("	,pmcalibstdvalue_id				");
    	query.append("	,pmcalibstdtp_id				");
    	query.append("	,calib_point_type				");
    	query.append("	,calib_point					");
    	query.append("	,allow_value					");
    	query.append("	,asf_std_value					");
    	query.append("	,asl_std_value					");
    	query.append("	,ord_no							");
    	query.append("	,remark							");
    	query.append(")VALUES(							");
    	query.append("	?								");
    	query.append("	,?								");
    	query.append("	,?								");
    	query.append("	,?								");
    	query.append("	,?								");
    	query.append("	,?								");
    	query.append("	,?								");
    	query.append("	,?								");
    	query.append("	,?								");
    	query.append("	,?								");
    	query.append(")									");
    	
    	Object[] objects = new Object[] {
    			 user.getCompNo()
     			,workPmStdCalibValDetailDTO.getPmCalibStdValueId()
    			,workPmStdCalibCommonDTO.getPmCalibStdTpId()
    			,workPmStdCalibValDetailDTO.getCalibPointTypeId()
    			,workPmStdCalibValDetailDTO.getCalibPoint()
    			,workPmStdCalibValDetailDTO.getAllowValue()
    			,workPmStdCalibValDetailDTO.getAsfStdValue()
    			,workPmStdCalibValDetailDTO.getAslStdValue()
    			,workPmStdCalibValDetailDTO.getOrdNo()
    			,workPmStdCalibValDetailDTO.getRemark()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    public int updateDetail(WorkPmStdCalibCommonDTO workPmStdCalibCommonDTO,WorkPmStdCalibValDetailDTO workPmStdCalibValDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	int rtnValue  = 0;

    	query.append("UPDATE TAPMCALIBSTDVALUE SET					");
    	query.append("	calib_point_type			= ?				");
    	query.append("	,calib_point				= ?				");
    	query.append("	,allow_value				= ?				");
    	query.append("	,asf_std_value				= ?				");
    	query.append("	,asl_std_value				= ?				");
    	query.append("	,ord_no						= ?				");
    	query.append("	,remark						= ?				");
    	query.append("WHERE  comp_no				= ?				");
    	query.append("  AND  pmcalibstdtp_id		= ?				");
    	query.append("  AND  pmcalibstdvalue_id		= ?				");
    	
    	Object[] objects = new Object[] {
    			workPmStdCalibValDetailDTO.getCalibPointTypeId()
    			,workPmStdCalibValDetailDTO.getCalibPoint()
    			,workPmStdCalibValDetailDTO.getAllowValue()
    			,workPmStdCalibValDetailDTO.getAsfStdValue()
    			,workPmStdCalibValDetailDTO.getAslStdValue()
    			,workPmStdCalibValDetailDTO.getOrdNo()
    			,workPmStdCalibValDetailDTO.getRemark()
    			,user.getCompNo()
    			,workPmStdCalibCommonDTO.getPmCalibStdTpId()
    			,workPmStdCalibValDetailDTO.getPmCalibStdValueId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
}