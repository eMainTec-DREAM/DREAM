package dream.work.let.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.work.let.dao.WorkLetDetailDAO;
import dream.work.let.dto.WorkLetCommonDTO;
import dream.work.let.dto.WorkLetDetailDTO;

/**
 * 작업계획목록 - 상세 dao
 * 
 * @author syyang
 * @version $Id$
 * @since 1.0
 * @spring.bean id="workLetDetailDAOTarget"
 * @spring.txbn id="workLetDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkLetDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkLetDetailDAO
{
    /**
     * detail find
     * @author syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param workLetCommonDTO
     * @return
     */
    public WorkLetDetailDTO findDetail(WorkLetCommonDTO workLetCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        String lang = user.getLang();
        
        query.append("SELECT																			");
        query.append("		x.wolet_id													woLetId			");
        query.append("		,x.wolet_no													woLetNo			");
        query.append("		,x.wolet_status												woLetStatus		");
        query.append("		,SFACODE_TO_DESC(x.wolet_status,'WOLET_STATUS','SYS','','"+lang+"')				woLetStatusDesc		");
        query.append("		,x.plant													plant			");
        query.append("		,(SELECT description														");
        query.append("		  FROM TAPLANT																");
        query.append("		  WHERE comp_no = x.comp_no													");
        query.append("		   AND plant = x.plant)										plantDesc		");
        query.append("		,x.wkor_id													wkOrId			");
        query.append("		,(SELECT wo_no FROM TAWORKORDER aa WHERE aa.comp_no = x.comp_no AND aa.wkor_id = x.wkor_id)	woNo	");
        query.append("		,x.start_date												startDate		");
        query.append("		,x.start_time												startTime		");
        query.append("		,x.end_date													endDate			");
        query.append("		,x.end_time													endTime			");
        query.append("		,x.work_time												workTime		");
        query.append("		,x.place													place			");
        query.append("		,x.item_desc												itemDesc		");
        query.append("		,x.description												woLetDesc		");
        query.append("		,x.req_dept													reqDeptId		");
        query.append("		,(SELECT description														");
        query.append("		  FROM TADEPT																");
        query.append("		  WHERE comp_no = x.comp_no													");
        query.append("		   AND dept_id = x.req_dept)								reqDeptDesc		");
        query.append("		,x.req_by													reqById			");
        query.append("		,(SELECT emp_name															");
        query.append("		  FROM TAEMP																");
        query.append("		  WHERE comp_no = x.comp_no													");
        query.append("		   AND emp_id = x.req_by)									reqByDesc		");
        query.append("		,x.rec_dept													recDeptId		");
        query.append("		,(SELECT description														");
        query.append("		  FROM TADEPT																");
        query.append("		  WHERE comp_no = x.comp_no													");
        query.append("		   AND dept_id = x.rec_dept)								recDeptDesc		");
        query.append("		,x.rec_by													recById			");
        query.append("		,(SELECT emp_name															");
        query.append("		  FROM TAEMP																");
        query.append("		  WHERE comp_no = x.comp_no													");
        query.append("		   AND emp_id = x.rec_by)									recByDesc		");
        query.append("		,x.remark													remark			");
        query.append("		,x.cre_time													creTime			");
        query.append("		,x.upd_time													updTime			");
        query.append("      ,(SELECT user_name                                                          ");
        query.append("        FROM TAUSER                                                               ");
        query.append("        WHERE comp_no = x.comp_no                                              	");
        query.append("         AND user_id = x.let_by)                          		letBy   		");
        query.append("		,x.let_date													letDate			");
        query.append("FROM TAWOLET x																	");
        query.append("WHERE 1=1																			");
        query.append("  AND x.comp_no = ?																");
        query.append("  AND x.wolet_id = ?																");
    
        Object[] objects = new Object[] {
        		user.getCompNo()
        		,workLetCommonDTO.getWoLetId()
    	};
        
        WorkLetDetailDTO workLetDetailDTO = 
        		(WorkLetDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new WorkLetDetailDTO()));
        
        return workLetDetailDTO;
    }
    /**
     * detail insert
     * @author syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param workLetDetailDTO
     * @return
     */
    public int insertDetail(WorkLetDetailDTO workLetDetailDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	int rtnValue  = 0;
    	
    	query.append("INSERT INTO TAWOLET							    ");
    	query.append("	(comp_no		,wolet_id		,wolet_no		");
    	query.append("	 ,wolet_status	,plant			,wkor_id		");
    	query.append("	 ,start_date	,start_time		,end_date		");
    	query.append("	 ,end_time		,work_time		,place			");
    	query.append("	 ,item_desc		,description	,req_dept		");
    	query.append("	 ,req_by      	,rec_dept		,rec_by			");
    	query.append("   ,remark		,cre_time		,cre_by			");
    	query.append("   ,upd_time     	,upd_by	        				");
    	query.append("	)	VALUES										");
    	query.append("	(?				,?				,?				");
    	query.append("	 ,?				,?				,?				");
    	query.append("	 ,?				,?				,?				");
    	query.append("	 ,?				,?				,?				");
    	query.append("	 ,?				,?				,?				");
    	query.append("	 ,?				,?				,?				");
    	query.append("   ,?				,?				,?				");
    	query.append("   ,?				,?								");
    	query.append("	)												");
    	
    	Object[] objects = new Object[] {
    			loginUser.getCompNo()
    			,workLetDetailDTO.getWoLetId()
    			,workLetDetailDTO.getWoLetNo()
    			,workLetDetailDTO.getWoLetStatus()
    			,workLetDetailDTO.getPlant()
    			,workLetDetailDTO.getWkOrId()
    			,workLetDetailDTO.getStartDate()
    			,workLetDetailDTO.getStartTime()
    			,workLetDetailDTO.getEndDate()
    			,workLetDetailDTO.getEndTime()
    			,workLetDetailDTO.getWorkTime()
    			,workLetDetailDTO.getPlace()
    			,workLetDetailDTO.getItemDesc()
    			,workLetDetailDTO.getWoLetDesc()
    			,workLetDetailDTO.getReqDeptId()
    			,workLetDetailDTO.getReqById()
    			,workLetDetailDTO.getRecById()
    			,workLetDetailDTO.getRecById()
    			,workLetDetailDTO.getRemark()
    			,workLetDetailDTO.getCreTime()
    			,loginUser.getEmpId()
    			,workLetDetailDTO.getUpdTime()
    			,loginUser.getEmpId()
    	};
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    /**
     * detail update
     * @author syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param workLetDetailDTO
     * @return
     */
    public int updateDetail(WorkLetDetailDTO workLetDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("UPDATE TAWOLET SET		    ");
    	query.append("	 description		= ?		");
    	query.append("	,item_desc			= ?		");
    	query.append("	,place				= ?		");
    	query.append("	,start_date			= ?		");
    	query.append("	,start_time			= ?		");
    	query.append("	,end_date			= ?		");
    	query.append("	,end_time			= ?		");
    	query.append("	,work_time			= ?		");
    	query.append("	,req_by				= ?		");
    	query.append("	,req_dept			= ?		");
    	query.append("	,rec_by				= ?		");
    	query.append("	,rec_dept			= ?		");
    	query.append("	,plant				= ?		");
    	query.append("	,wkor_id			= ?		");
    	query.append("	,remark				= ?		");
    	query.append("  ,upd_time	        = ?   	");
    	query.append("  ,upd_by             = ?    	");
    	query.append("WHERE comp_no 		= ?		");
    	query.append("  AND wolet_id		= ?		");
    	
    	Object[] objects = new Object[] {
    			workLetDetailDTO.getWoLetDesc()
    			,workLetDetailDTO.getItemDesc()
    			,workLetDetailDTO.getPlace()
    			,workLetDetailDTO.getStartDate()
    			,workLetDetailDTO.getStartTime()
    			,workLetDetailDTO.getEndDate()
    			,workLetDetailDTO.getEndTime()
    			,workLetDetailDTO.getWorkTime()
    			,workLetDetailDTO.getReqById()
    			,workLetDetailDTO.getReqDeptId()
    			,workLetDetailDTO.getRecById()
    			,workLetDetailDTO.getRecDeptId()
    			,workLetDetailDTO.getPlant()
    			,workLetDetailDTO.getWkOrId()
    			,workLetDetailDTO.getRemark()
    			,workLetDetailDTO.getUpdTime()
    			,user.getEmpId()
    			,user.getCompNo()
    			,workLetDetailDTO.getWoLetId()
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    /**
     * detail update
     * @author syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param workLetDetailDTO
     * @return
     */
    public int completeDetail(WorkLetDetailDTO workLetDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("UPDATE TAWOLET SET		");
    	query.append("	wolet_status	= ?		");
    	query.append("	,let_by			= ?  	");
    	query.append("	,let_date		= ?		");
    	query.append("WHERE wolet_id 	= ?		");
    	query.append("  AND comp_no		= ?		");
    	
    	Object[] objects = new Object[] {
    			workLetDetailDTO.getWoLetStatus()
    			,user.getEmpId()
    			,workLetDetailDTO.getLetDate()
    			,workLetDetailDTO.getWoLetId()
    			,user.getCompNo()
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    @Override
    public String getWoLetStatus(WorkLetDetailDTO workLetDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("SELECT										");
    	query.append("		CASE WHEN ( SELECT COUNT(*)				");
    	query.append("    				FROM TAWOLETLIST			");
    	query.append("    				WHERE 1=1					");
    	query.append("  				AND comp_no = ?				");
    	query.append("    				AND woletlist_status='COM'	");
    	query.append("    				AND wolet_id = ?			");
    	query.append("    			  ) = 0							");
    	query.append("        	THEN 'WRK'							");
    	query.append("        	ELSE 'DNG'							");
    	query.append("    	END                                 	");
    	query.append("FROM TAWOLET									");
    	query.append("WHERE 1=1					                    ");
    	query.append("  AND comp_no		= ?		                    ");
    	query.append("  ANd wolet_id 	= ?		                    ");
    	
    	Object[] objects = new Object[] {
    			user.getCompNo()
    			,workLetDetailDTO.getWoLetId()
    			,user.getCompNo()
    			,workLetDetailDTO.getWoLetId()
     	};
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),objects));
    }

    
}