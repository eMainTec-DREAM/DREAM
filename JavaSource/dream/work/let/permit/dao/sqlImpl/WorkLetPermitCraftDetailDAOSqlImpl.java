package dream.work.let.permit.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.work.let.permit.dao.WorkLetPermitCraftDetailDAO;
import dream.work.let.permit.dto.WorkLetPermitCraftDetailDTO;
import dream.work.let.permit.dto.WorkLetPermitDetailDTO;

/**
 * 안전작업허가서유형 - 작업자 상세 dao
 * @author  syyang
 * @version $Id: WorkLetPermitCraftDetailDAO.java,v 1.0 2015/12/04 08:10:27 syyang Exp $
 * @since   1.0
 * @spring.bean id="workLetPermitCraftDetailDAOTarget"
 * @spring.txbn id="workLetPermitCraftDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkLetPermitCraftDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkLetPermitCraftDetailDAO
{
    /**
     * detail find
     * @author syyang
     * @version $Id: WorkLetPermitCraftDetailDAO.java,v 1.0 20155/12/02 08:25:47 syyang Exp $
     * @since   1.0
     * 
     * @param wkOrId
     * @param woCraftId
     * @param compNo
     * @return
     */
    public WorkLetPermitCraftDetailDTO findDetail(String woLetListId, String woLetListCraftId, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT													");
        query.append("		x.woletlist_id 					AS woLetListId		");
        query.append("		,x.woletlistcraft_id 			AS woLetListCraftId	");
        query.append("		,x.step_num 					AS stepNum			");
        query.append("		,x.craft_type 					AS craftType		");
        query.append("		,dbo.SFACODE_TO_DESC(x.craft_type, 'CRAFT_TYPE', 'SYS', '', '"+user.getLangId()+"')	AS craftTypeDesc	");
        query.append("		,x.work_name 					AS workName			");
        query.append("      ,x.work_time 					AS workTime			");
        query.append("		,x.start_date					AS startDate		");
        query.append("		,x.start_time					AS startTime		");
        query.append("		,x.end_date						AS endDate			");
        query.append("		,x.end_time						AS endTime			");
        query.append("      ,x.work_time 					AS workTime			");
        query.append("      ,x.remark 						AS remark			");
        query.append("FROM   TAWOLETLISTCRAFT x									");
        query.append("WHERE	 1=1												");
        query.append("  AND  x.comp_no				= '"+user.getCompNo()+"'	");
        query.append("  AND  x.woletlistcraft_id	= '"+woLetListCraftId+"'	");
    
        WorkLetPermitCraftDetailDTO workLetPermitCraftDetailDTO = 
        		(WorkLetPermitCraftDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new WorkLetPermitCraftDetailDTO()));
        
        return workLetPermitCraftDetailDTO;
    }
    /**
     * detail update
     * @author syyang
     * @version $Id: WorkLetPermitCraftDetailDAO.java,v 1.0 20155/12/02 08:25:47 syyang Exp $
     * @since   1.0
     * 
     * @param workLetPermitCraftDetailDTO
     * @param workLetCommonDTO
     * @return
     */
    public int updateDetail(WorkLetPermitDetailDTO workLetPermitDetailDTO, WorkLetPermitCraftDetailDTO workLetPermitCraftDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAWOLETLISTCRAFT SET		");
    	query.append("		step_num			= ?		");
    	query.append("		,craft_type			= ?		");
    	query.append("		,work_name			= ?		");
    	query.append("		,start_date			= ?		");
    	query.append("		,start_time			= ?		");
    	query.append("		,end_date			= ?		");
    	query.append("		,end_time			= ?		");
    	query.append("		,work_time			= ?		");
    	query.append("		,remark				= ?		");
    	query.append("		,upd_time			= ?		");
    	query.append("		,upd_by				= ?		");
    	query.append("WHERE comp_no				= ?		");
    	query.append(" AND woletlistcraft_id	= ?		");
    	
    	Object[] objects = new Object[] {
    			workLetPermitCraftDetailDTO.getStepNum()
    			,workLetPermitCraftDetailDTO.getCraftType()
    			,workLetPermitCraftDetailDTO.getWorkName()
    			,workLetPermitCraftDetailDTO.getStartDate()
    			,workLetPermitCraftDetailDTO.getStartTime()
    			,workLetPermitCraftDetailDTO.getEndDate()
    			,workLetPermitCraftDetailDTO.getEndTime()
    			,workLetPermitCraftDetailDTO.getWorkTime()
    			,workLetPermitCraftDetailDTO.getRemark()
    			,workLetPermitCraftDetailDTO.getUpdTime()
    			,user.getEmpId()
    			,user.getCompNo()
    			,workLetPermitCraftDetailDTO.getWoLetListCraftId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail insert
     * @author syyang
     * @version $Id: WorkLetPermitCraftDetailDAO.java,v 1.0 20155/12/02 08:25:47 syyang Exp $
     * @since   1.0
     * 
     * @param workLetPermitCraftDetailDTO
     * @param workLetCommonDTO
     * @return
     */
    public int insertDetail(WorkLetPermitDetailDTO workLetPermitDetailDTO, WorkLetPermitCraftDetailDTO workLetPermitCraftDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAWOLETLISTCRAFT				");
    	query.append("(	  comp_no			,woletlistcraft_id	");
    	query.append("	 ,woletlist_id		,step_num			");
    	query.append("	 ,craft_type		,work_name			");
    	query.append("	 ,start_date		,start_time			");
    	query.append("	 ,end_date			,end_time			");
    	query.append("	 ,work_time			,remark				");
    	query.append("	 ,cre_time			,cre_by				");
    	query.append("	 ,upd_time			,upd_by				");
    	query.append(")	VALUES									");
    	query.append("(   ?					,?					");
    	query.append("	 ,?					,?					");
    	query.append("	 ,?					,?					");
    	query.append("	 ,?					,?					");
    	query.append("	 ,?					,?					");
    	query.append("	 ,?					,?					");
    	query.append("	 ,?					,?					");
    	query.append("	 ,?					,?					");
    	query.append("	)										");
    	
    	Object[] objects = new Object[] {
    			user.getCompNo()
    			,workLetPermitCraftDetailDTO.getWoLetListCraftId()
    			,workLetPermitDetailDTO.getWoLetListId()
    			,workLetPermitCraftDetailDTO.getStepNum()
    			,workLetPermitCraftDetailDTO.getCraftType()
    			,workLetPermitCraftDetailDTO.getWorkName()
    			,workLetPermitCraftDetailDTO.getStartDate()
    			,workLetPermitCraftDetailDTO.getStartTime()
    			,workLetPermitCraftDetailDTO.getEndDate()
    			,workLetPermitCraftDetailDTO.getEndTime()
    			,workLetPermitCraftDetailDTO.getWorkTime()
    			,workLetPermitCraftDetailDTO.getRemark()
    			,workLetPermitCraftDetailDTO.getCreTime()
    			,user.getEmpId()
    			,workLetPermitCraftDetailDTO.getUpdTime()
    			,user.getEmpId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
}