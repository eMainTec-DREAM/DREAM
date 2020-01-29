package dream.work.pm.std.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.work.pm.std.dao.WorkPmStdCalibDetailDAO;
import dream.work.pm.std.dto.WorkPmStdCalibCommonDTO;
import dream.work.pm.std.dto.WorkPmStdCalibDetailDTO;

/**
 * 교정표준값 타입 - Detail DAO implements
 * @author kim21017
 * @version $Id: WorkPmStdCalibDetailDAOOraImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workPmStdCalibDetailDAOTarget"
 * @spring.txbn id="workPmStdCalibDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkPmStdCalibDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkPmStdCalibDetailDAO
{
	
    public WorkPmStdCalibDetailDTO findDetail(WorkPmStdCalibCommonDTO workPmStdCalibCommonDTO, User user) 
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("SELECT																			");
        query.append("		x.pmcalibstdtp_id										AS pmCalibStdTpId	");
        query.append("		,x.pmcalibstdtp_no										AS pmCalibStdTpNo	");
        query.append("		,x.pmc_type												AS pmcTypeId		");
        query.append("		,SFACODE_TO_DESC(x.pmc_type,'PMC_TYPE','SYS',''								");
        query.append("							,?) 								AS pmcTypeDesc		");
        query.append("		,x.measure_range										AS measureRange		");
        query.append("		,x.remark												AS remark			");
        query.append("FROM TAPMCALIBSTDTP x																");
    	query.append("WHERE  1=1																		");
    	query.append("AND  pmcalibstdtp_id 	= ?															");
    	query.append("AND  comp_no    		= ?															");
        
        Object[] objects = new Object[] {
        		user.getLangId()
        		,workPmStdCalibCommonDTO.getPmCalibStdTpId()
    			,user.getCompNo()
    	};
    
        WorkPmStdCalibDetailDTO workPmStdCalibDetailDTO = 
        		(WorkPmStdCalibDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new WorkPmStdCalibDetailDTO()));
        
        return workPmStdCalibDetailDTO;
        
    }

    public int insertDetail(WorkPmStdCalibDetailDTO workPmStdCalibDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;

    	query.append("INSERT INTO TAPMCALIBSTDTP(	");
    	query.append("	comp_no						");
    	query.append("	,pmcalibstdtp_id			");
    	query.append("	,pmcalibstdtp_no			");
    	query.append("	,pmc_type					");
    	query.append("	,measure_range				");
    	query.append("	,remark						");
    	query.append(")VALUES(						");
    	query.append("	?							");
    	query.append("	,?							");
    	query.append("	,?							");
    	query.append("	,?							");
    	query.append("	,?							");
    	query.append("	,?							");
    	query.append(")								");
    	
    	Object[] objects = new Object[] {
    			 user.getCompNo()
    			,workPmStdCalibDetailDTO.getPmCalibStdTpId()
    			,workPmStdCalibDetailDTO.getPmCalibStdTpNo()
    			,workPmStdCalibDetailDTO.getPmcTypeId()
    			,workPmStdCalibDetailDTO.getMeasureRange()
    			,workPmStdCalibDetailDTO.getRemark()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    public int updateDetail(WorkPmStdCalibDetailDTO workPmStdCalibDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	int rtnValue  = 0;

    	query.append("UPDATE TAPMCALIBSTDTP SET					");
    	query.append("	pmc_type				= ?				");
    	query.append("	,measure_range			= ?				");
    	query.append("	,remark					= ?				");
    	query.append("WHERE  comp_no			= ?				");
    	query.append("  AND  pmcalibstdtp_id	= ?				");
    	
    	Object[] objects = new Object[] {
    			workPmStdCalibDetailDTO.getPmcTypeId()
    			,workPmStdCalibDetailDTO.getMeasureRange()
    			,workPmStdCalibDetailDTO.getRemark()
    			,user.getCompNo()
    			,workPmStdCalibDetailDTO.getPmCalibStdTpId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
}