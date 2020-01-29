package dream.work.cal.pminsappr.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.work.cal.pminsappr.dao.WorkCalPmInsApprDetailDAO;
import dream.work.cal.pminsappr.dto.WorkCalPmInsApprCommonDTO;
import dream.work.cal.pminsappr.dto.WorkCalPmInsApprDetailDTO;

/**
 * øππÊ¡°∞À∞Ë»πΩ¬¿Œ - ªÛºº dao
 * 
 * @author kim21017
 * @version $Id$
 * @since 1.0
 * @spring.bean id="workCalPmInsApprDetailDAOTarget"
 * @spring.txbn id="workCalPmInsApprDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkCalPmInsApprDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkCalPmInsApprDetailDAO
{
    public WorkCalPmInsApprDetailDTO findDetail(WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT															");
        query.append("		x.pminsschedappr_id						AS pmInsSchedApprId	");
        query.append("		,x.pminsschedappr_no					AS pmInsSchedApprNo	");
        query.append("		,x.description							AS description		");
        query.append("		,x.start_date							AS startDate		");
        query.append("		,x.end_date								AS endDate			");
        query.append("		,x.wo_dept								AS deptId			");
        query.append("		,(SELECT a.description 										");
        query.append("			FROM TADEPT a											");
        query.append("			WHERE 1=1												");
        query.append("			AND a.comp_no = x.comp_no								");
        query.append("			AND a.dept_id = x.wo_dept)			AS deptDesc			");
        query.append("      ,(SELECT a.description                                      ");
        query.append("          FROM TAPLANT a                                          ");
        query.append("          WHERE 1=1                                               ");
        query.append("          AND a.comp_no = x.comp_no                               ");
        query.append("          AND a.plant = x.plant)          	AS plantDesc        ");
        query.append("      ,x.plant                         		AS plantId       	");
        query.append("		,x.upd_date								AS updDate			");
        query.append("		,x.upd_by								AS updBy			");
        query.append("		,(SELECT a.emp_name											");
        query.append("			FROM TAEMP a											");
        query.append("			WHERE 1=1												");
        query.append("			AND a.comp_no = x.comp_no								");
        query.append("			AND a.emp_id = x.upd_by)			AS updDesc			");
        query.append("		,x.pminsschedapp_status					AS pmInsSchedAppStatusId	");
        query.append("		,SFACODE_TO_DESC(x.pminsschedapp_status,'PMINSSCHEDAPP_STATUS','SYS','','"+user.getLangId()+"')	AS pmInsSchedAppStatusDesc	");
        query.append("		,x.remark								AS remark			");
        query.append("      ,x.yyyy                                 AS yyyy            	");
        query.append("      ,x.yyyymm                               AS yyyymm			");
        query.append("      ,x.pminsschedappr_type 					AS pminsschedapprType		");
        query.append("		,SFACODE_TO_DESC(x.pminsschedappr_type,'PMINSSCHEDAPPR_TYPE','SYS','','"+user.getLangId()+"')	AS pminsschedapprTypeDesc	");
        query.append("FROM TAPMINSSCHEDAPPR	x											");
        query.append("WHERE 1=1															");
        query.append("AND 	x.comp_no 				= ?									");
        query.append("AND 	x.pminsschedappr_id 	= ?									");
    
        Object[] objects = new Object[] {
        		user.getCompNo()
        		,workCalPmInsApprCommonDTO.getPmInsSchedApprId()
    	};
        
        WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO = 
        		(WorkCalPmInsApprDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new WorkCalPmInsApprDetailDTO()));
        
        return workCalPmInsApprDetailDTO;
    }
    /**
     * detail insert
     * @author kim21017
     * @version $Id$
     * @since   1.0
     * 
     * @param workCalPmInsApprDetailDTO
     * @return
     */
    public int insertDetail(WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO, WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("INSERT INTO TAPMINSSCHEDAPPR										");
    	query.append("	(comp_no		,pminsschedappr_id		,pminsschedappr_no		");
    	query.append("	 ,description	,start_date				,end_date				");
    	query.append("	 ,wo_dept		,pminsschedapp_status	,upd_by					");
    	query.append("	 ,upd_date		,remark					,plant					");
    	query.append("	 ,yyyy			,yyyymm					,pminsschedappr_type  	");
    	query.append("	)	VALUES														");
    	query.append("	(?				,?						,?						");
    	query.append("	 ,?				,?						,?						");
    	query.append("	 ,?				,?						,?						");
    	query.append("	 ,?				,?						,?						");
    	query.append("	 ,?				,?    					,?						");
    	query.append("	)																");
    	
    	Object[] objects = new Object[] {
    			user.getCompNo(),
    			workCalPmInsApprDetailDTO.getPmInsSchedApprId(),
    			workCalPmInsApprDetailDTO.getPmInsSchedApprNo(),
    			workCalPmInsApprDetailDTO.getDescription(),
    			workCalPmInsApprDetailDTO.getStartDate(),
    			workCalPmInsApprDetailDTO.getEndDate(),
    			workCalPmInsApprDetailDTO.getDeptId(),
    			"W",
    			workCalPmInsApprDetailDTO.getUpdBy(),
    			workCalPmInsApprDetailDTO.getUpdDate(),
    			workCalPmInsApprDetailDTO.getRemark(),
    			workCalPmInsApprDetailDTO.getPlantId(),
    			workCalPmInsApprDetailDTO.getYyyy(),
    			workCalPmInsApprDetailDTO.getYyyymm(),
    			workCalPmInsApprDetailDTO.getPminsschedapprType()
    	};
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    public int updateDetail(WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO, WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("UPDATE TAPMINSSCHEDAPPR SET		");
    	query.append("	description				= ?,	");
    	query.append("	start_date				= ?,	");
    	query.append("	end_date				= ?,	");
    	query.append("	wo_dept					= ?,	");
    	query.append("	upd_by					= ?,	");
    	query.append("	upd_date				= ?,	");
    	query.append("  yyyy                    = ?,    ");
    	query.append("  yyyymm                  = ?,    ");
    	query.append("  pminsschedappr_type     = ?,    ");
    	query.append("  plant                   = ?,    ");
    	query.append("	remark					= ?		");
    	query.append("WHERE pminsschedappr_id	= ?		");
    	query.append("  AND comp_no				= ?		");
    	
    	Object[] objects = new Object[] {
    			workCalPmInsApprDetailDTO.getDescription(),
    			workCalPmInsApprDetailDTO.getStartDate(),
    			workCalPmInsApprDetailDTO.getEndDate(),
    			workCalPmInsApprDetailDTO.getDeptId(),
    			workCalPmInsApprDetailDTO.getUpdBy(),
    			workCalPmInsApprDetailDTO.getUpdDate(),
    			workCalPmInsApprDetailDTO.getYyyy(),
    			workCalPmInsApprDetailDTO.getYyyymm(),
    			workCalPmInsApprDetailDTO.getPminsschedapprType(),
    			workCalPmInsApprDetailDTO.getPlantId(),
    			workCalPmInsApprDetailDTO.getRemark(),
    			workCalPmInsApprDetailDTO.getPmInsSchedApprId(),
    			user.getCompNo()
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    public int updateStatus(WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("UPDATE TAPMINSSCHEDAPPR SET		");
    	query.append("	pminsschedapp_status	= 'C'	");
    	query.append("WHERE pminsschedappr_id	= ?		");
    	query.append("  AND comp_no				= ?		");
    	
    	Object[] objects = new Object[] {
    			workCalPmInsApprDetailDTO.getPmInsSchedApprId(),
    			user.getCompNo()
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    public String checkAppr(WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO,User user) {

		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT COUNT(*)				");
		query.append("FROM TAPMINSSCHEDAPPR 		");
		query.append("WHERE 1=1						");
		query.append("AND pminsschedappr_id !=	"+workCalPmInsApprDetailDTO.getPmInsSchedApprId()+"");
		query.append("AND (							");
		query.append("		start_date BETWEEN '"+workCalPmInsApprDetailDTO.getStartDate()+"' AND '"+workCalPmInsApprDetailDTO.getEndDate()+"'	");
		query.append("		OR						");
		query.append("		end_date BETWEEN '"+workCalPmInsApprDetailDTO.getStartDate()+"' AND '"+workCalPmInsApprDetailDTO.getEndDate()+"'	");
		query.append("		)						");
		query.getAndQuery("wo_dept", workCalPmInsApprDetailDTO.getDeptId());
		
		return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
    
    @Override
    public int setStatus(AppReqDetailDTO appReqDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("UPDATE TAPMINSSCHEDAPPR SET           ");
        query.append("       pminsschedapp_status  = CASE WHEN pminsschedapp_status = 'C' THEN 'C' ELSE ? END             		");
        query.append("WHERE  pminsschedappr_id     = ?		");
        query.append("  AND  comp_no       		   = ?      ");
        
        Object[] objects = new Object[] {
                appReqDetailDTO.getParentStatus(),
                appReqDetailDTO.getObjectId(),
                user.getCompNo()
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
}