package dream.work.let.permit.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.work.let.dto.WorkLetCommonDTO;
import dream.work.let.dto.WorkLetDetailDTO;
import dream.work.let.permit.dao.WorkLetPermitDetailDAO;
import dream.work.let.permit.dto.WorkLetPermitDetailDTO;

/**
 * 안전작업 - 안전작업허가서 상세 dao
 * @author  syyang
 * @version $Id$
 * @since   1.0
 * @spring.bean id="workLetPermitDetailDAOTarget"
 * @spring.txbn id="workLetPermitDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkLetPermitDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkLetPermitDetailDAO
{
    /**
     * detail find
     * @author syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param woLetId
     * @param woLetListId
     * @param compNo
     * @return
     */
    public WorkLetPermitDetailDTO findDetail(String woLetId, String woLetListId, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	String lang = user.getLangId();

    	query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;             ");
        query.append("SELECT														");
        query.append("		x.woletlist_id 						AS woLetListId		");
        query.append("		,x.woletlist_no 					AS woLetListNo		");
        query.append("		,x.woletlist_status 				AS woLetListStatus	");
        query.append("		,dbo.SFACODE_TO_DESC(x.woletlist_status,'WOLETLIST_STATUS','SYS','','"+lang+"')	AS woLetListStatusDesc		");
        query.append("		,x.woletctg_type 					AS woLetCtgType		");
        query.append("		,dbo.SFACODE_TO_DESC(x.woletctg_type,'WOLETCTG_TYPE','SYS','','"+lang+"')			AS woLetCtgTypeDesc		");
        query.append("		,x.place							AS place			");
        query.append("		,x.start_date						AS startDate		");
        query.append("		,x.start_time						AS startTime		");
        query.append("		,x.end_date							AS endDate			");
        query.append("		,x.end_time							AS endTime			");
        query.append("		,x.wolet_date						AS woLetDate		");
        query.append("		,x.remark 							AS remark			");
        query.append("		,x.cre_time							AS creTime			");
        query.append("		,x.upd_time							As updTime			");
        query.append("FROM  TAWOLETLIST x											");
        query.append("WHERE 1=1 													");
        query.getAndQuery("x.comp_no", user.getCompNo());
        query.getAndQuery("x.wolet_id", woLetId);
        query.getAndQuery("x.woletlist_id", woLetListId);
    
        WorkLetPermitDetailDTO workLetPermitDetailDTO = 
        		(WorkLetPermitDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new WorkLetPermitDetailDTO()));
        
        return workLetPermitDetailDTO;
    }
    /**
     * detail update
     * @author syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param workLetCommonDTO
     * @param workLetPermitDetailDTO
     * @param user
     * @return
     */
    public int updateDetail(WorkLetCommonDTO workLetCommonDTO, WorkLetPermitDetailDTO workLetPermitDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAWOLETLIST SET			");
    	query.append("		woletctg_type		= ?		");
    	query.append("		,place				= ?		");
    	query.append("		,start_date			= ?		");
    	query.append("		,start_time			= ?		");
    	query.append("		,end_date			= ?		");
    	query.append("		,end_time			= ?		");
    	query.append("		,wolet_date			= ?		");
    	query.append("		,remark				= ?		");
    	query.append(" 	 	,upd_time	        = ?   	");
    	query.append("  	,upd_by             = ?    	");
    	query.append("WHERE comp_no				= ?		");
    	query.append("  and woletlist_id		= ?		");
    	query.append("  and wolet_id			= ?		");
    	
    	Object[] objects = new Object[] {
    			workLetPermitDetailDTO.getWoLetCtgType()
    			,workLetPermitDetailDTO.getPlace()
    			,workLetPermitDetailDTO.getStartDate()
    			,workLetPermitDetailDTO.getStartTime()
    			,workLetPermitDetailDTO.getEndDate()
    			,workLetPermitDetailDTO.getEndTime()
    			,workLetPermitDetailDTO.getWoLetDate()
    			,workLetPermitDetailDTO.getRemark()
    			,workLetPermitDetailDTO.getUpdTime()
    			,user.getEmpId()
    			,user.getCompNo()
    			,workLetPermitDetailDTO.getWoLetListId()
    			,workLetCommonDTO.getWoLetId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail insert
     * @author syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param workLetPermitDetailDTO
     * @param user
     * @return
     */
    public int insertDetail(WorkLetCommonDTO workLetCommonDTO, WorkLetPermitDetailDTO workLetPermitDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAWOLETLIST							    		");
    	query.append("	(comp_no			,woletlist_id			,woletlist_no	");
    	query.append("	 ,wolet_id			,woletlist_status		,woletctg_type	");
    	query.append("	 ,start_date		,start_time				,end_date		");
    	query.append("	 ,end_time			,wolet_date				,place			");
    	query.append("	 ,remark			,cre_time				,cre_by			");
    	query.append("   ,upd_time     		,upd_by	        						");
    	query.append("	)	VALUES													");
    	query.append("	(?					,?						,?				");
    	query.append("	 ,?					,?						,?				");
    	query.append("	 ,?					,?						,?				");
    	query.append("	 ,?					,?						,?				");
    	query.append("	 ,?					,?						,?				");
    	query.append("	 ,?					,?										");
    	query.append("	)															");
    	
    	Object[] objects = new Object[] {
    			user.getCompNo()
    			,workLetPermitDetailDTO.getWoLetListId()
    			,workLetPermitDetailDTO.getWoLetListNo()
    			,workLetCommonDTO.getWoLetId()
    			,workLetPermitDetailDTO.getWoLetListStatus()
    			,workLetPermitDetailDTO.getWoLetCtgType()
    			,workLetPermitDetailDTO.getStartDate()
    			,workLetPermitDetailDTO.getStartTime()
    			,workLetPermitDetailDTO.getEndDate()
    			,workLetPermitDetailDTO.getEndTime()
    			,workLetPermitDetailDTO.getWoLetDate()
    			,workLetPermitDetailDTO.getPlace()    			
    			,workLetPermitDetailDTO.getRemark()
    			,workLetPermitDetailDTO.getCreTime()
    			,user.getEmpId()
    			,workLetPermitDetailDTO.getUpdTime()
    			,user.getEmpId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
	@Override
	public int completeDetail(WorkLetDetailDTO workLetDetailDTO, WorkLetPermitDetailDTO workLetPermitDetailDTO, User user)
	{
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	int rtnValue  = 0;
    	
    	query.append("UPDATE TAWOLETLIST SET			");
    	query.append("		woletlist_status	= ?		");
    	query.append(" 	 	,upd_time	        = ?   	");
    	query.append("  	,upd_by             = ?    	");
    	query.append("WHERE comp_no				= ?		");
    	query.append("  and woletlist_id		= ?		");
    	query.append("  and wolet_id			= ?		");
    	
    	Object[] objects = new Object[] {
    			workLetPermitDetailDTO.getWoLetListStatus()
    			,workLetPermitDetailDTO.getUpdTime()
    			,user.getEmpId()
    			,user.getCompNo()
    			,workLetPermitDetailDTO.getWoLetListId()
    			,workLetDetailDTO.getWoLetId()
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);

    	return rtnValue;
	}
	
}