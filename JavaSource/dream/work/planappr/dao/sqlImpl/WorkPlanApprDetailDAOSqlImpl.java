package dream.work.planappr.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.work.planappr.dao.WorkPlanApprDetailDAO;
import dream.work.planappr.dto.WorkPlanApprCommonDTO;
import dream.work.planappr.dto.WorkPlanApprDetailDTO;

/**
 * ÀÛ¾÷°èÈ¹½ÂÀÎ - »ó¼¼ dao
 * 
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @spring.bean id="workPlanApprDetailDAOTarget"
 * @spring.txbn id="workPlanApprDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkPlanApprDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkPlanApprDetailDAO
{
    public WorkPlanApprDetailDTO findDetail(WorkPlanApprCommonDTO workPlanApprCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                            ");
        query.append("      x.woplanappr_id                         AS woPlanApprId     ");
        query.append("      ,x.description                          AS description      ");
        query.append("      ,x.start_date                           AS startDate        ");
        query.append("      ,x.end_date                             AS endDate          ");
        query.append("      ,x.dept_id                              AS deptId           ");
        query.append("      ,(SELECT a.description                                      ");
        query.append("          FROM TADEPT a                                           ");
        query.append("          WHERE 1=1                                               ");
        query.append("          AND a.comp_no = x.comp_no                               ");
        query.append("          AND a.dept_id = x.dept_id)          AS deptDesc         ");
        query.append("      ,(SELECT a.description                                      ");
        query.append("          FROM TAPLANT a                                          ");
        query.append("          WHERE 1=1                                               ");
        query.append("          AND a.comp_no = x.comp_no                               ");
        query.append("          AND a.plant = x.plant)          AS plantDesc            ");
        query.append("      ,x.plant plantId                                            ");
        query.append("      ,x.upd_date                             AS updDate          ");
        query.append("      ,x.upd_by                               AS updBy            ");
        query.append("      ,(SELECT a.emp_name                                         ");
        query.append("          FROM TAEMP a                                            ");
        query.append("          WHERE 1=1                                               ");
        query.append("          AND a.comp_no = x.comp_no                               ");
        query.append("          AND a.emp_id = x.upd_by)            AS updDesc          ");
        query.append("      ,x.woplanappr_status                    AS woPlanApprStatusId           ");
        query.append("      ,dbo.SFACODE_TO_DESC(x.woplanappr_status,'WOPLANAPPR_STATUS','SYS','','"+user.getLangId()+"') AS woPlanApprStatusDesc   ");
        query.append("      ,x.remark                               AS remark           ");
        query.append("      ,x.yyyy                                                     ");
        query.append("      ,x.yyyymm                                                   ");
        query.append("      ,x.woplanappr_type woplanapprType                           ");
        query.append("      ,? woType                                                   ");
        query.append("FROM TAWOPLANAPPR x                                               ");
        query.append("WHERE 1=1                                                         ");
        query.append("AND   x.comp_no           = ?                                     ");
        query.append("AND   x.woplanappr_id     = ?                                     ");
    
        Object[] objects = new Object[] {
                workPlanApprCommonDTO.getWoType()
                ,user.getCompNo()
                ,workPlanApprCommonDTO.getWoPlanApprId()
    	};
        
        WorkPlanApprDetailDTO workPlanApprDetailDTO = 
        		(WorkPlanApprDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new WorkPlanApprDetailDTO()));
        
        return workPlanApprDetailDTO;
    }
    /**
     * detail insert
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param workPlanApprDetailDTO
     * @return
     */
    public int insertDetail(WorkPlanApprDetailDTO workPlanApprDetailDTO, WorkPlanApprCommonDTO workPlanApprCommonDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
        
        query.append("INSERT INTO TAWOPLANAPPR                                              ");
        query.append("  (comp_no, woplanappr_id,     description,   start_date, end_date    ");
        query.append("  ,dept_id, woplanappr_status, upd_by,        upd_date,   remark      ");
        query.append("  ,plant,   yyyy,              yyyymm,        woplanappr_type         ");
        query.append("  )   VALUES  (                                                       ");
        query.append("   ?,?,?,?,?                                                          ");
        query.append("  ,?,?,?,?,?                                                          ");
        query.append("  ,?,?,?,?                                                            ");
        query.append("  )                                                                   ");
        
        Object[] objects = new Object[] {
                user.getCompNo(),
                workPlanApprDetailDTO.getWoPlanApprId(),
                workPlanApprDetailDTO.getDescription(),
                workPlanApprDetailDTO.getStartDate(),
                workPlanApprDetailDTO.getEndDate(),
                workPlanApprDetailDTO.getDeptId(),
                "W",
                workPlanApprDetailDTO.getUpdBy(),
                workPlanApprDetailDTO.getUpdDate(),
                workPlanApprDetailDTO.getRemark(),
                workPlanApprDetailDTO.getPlantId(),
                workPlanApprDetailDTO.getYyyy(),
                workPlanApprDetailDTO.getYyyymm(),
                workPlanApprCommonDTO.getWoplanapprType()
        };
        
    	rtnValue = getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    	return rtnValue;
    }
    
    public int updateDetail(WorkPlanApprDetailDTO workPlanApprDetailDTO, WorkPlanApprCommonDTO workPlanApprCommonDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
        
        query.append("UPDATE TAWOPLANAPPR SET           ");
        query.append("  description             = ?,    ");
        query.append("  start_date              = ?,    ");
        query.append("  end_date                = ?,    ");
        query.append("  dept_id                 = ?,    ");
        query.append("  upd_by                  = ?,    ");
        query.append("  upd_date                = ?,    ");
        query.append("  yyyy                    = ?,    ");
        query.append("  yyyymm                  = ?,    ");
//        query.append("  woplanappr_type         = ?,    ");
        query.append("  plant                   = ?,    ");
        query.append("  remark                  = ?     ");
        query.append("WHERE woplanappr_id       = ?     ");
        query.append("  AND comp_no             = ?     ");
        
        Object[] objects = new Object[] {
                workPlanApprDetailDTO.getDescription(),
                workPlanApprDetailDTO.getStartDate(),
                workPlanApprDetailDTO.getEndDate(),
                workPlanApprDetailDTO.getDeptId(),
                workPlanApprDetailDTO.getUpdBy(),
                workPlanApprDetailDTO.getUpdDate(),
                workPlanApprDetailDTO.getYyyy(),
                workPlanApprDetailDTO.getYyyymm(),
//                workPlanApprCommonDTO.getWoplanapprType(),
                workPlanApprDetailDTO.getPlantId(),
                workPlanApprDetailDTO.getRemark(),
                workPlanApprDetailDTO.getWoPlanApprId(),
                user.getCompNo()
        };
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    	return rtnValue;
    }
    public int updateStatus(WorkPlanApprDetailDTO workPlanApprDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	query.append("UPDATE TAWOPLANAPPR SET		");
    	query.append("	woplanappr_status	= 'C'	");
    	query.append("WHERE woplanappr_id	= ?		");
    	query.append("  AND comp_no			= ?		");
    	
    	Object[] objects = new Object[] {
    			workPlanApprDetailDTO.getWoPlanApprId(),
    			user.getCompNo()
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    	return rtnValue;
    }
    public String checkAppr(WorkPlanApprDetailDTO workPlanApprDetailDTO,User user) {

		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("SELECT COUNT(*)				");
		query.append("FROM TAWOPLANAPPR 			");
		query.append("WHERE 1=1						");
		query.append("AND woplanappr_id !=	"+workPlanApprDetailDTO.getWoPlanApprId()+"");
		query.append("AND (							");
		query.append("		start_date BETWEEN '"+workPlanApprDetailDTO.getStartDate()+"' AND '"+workPlanApprDetailDTO.getEndDate()+"'	");
		query.append("		OR						");
		query.append("		end_date BETWEEN '"+workPlanApprDetailDTO.getStartDate()+"' AND '"+workPlanApprDetailDTO.getEndDate()+"'	");
		query.append("		)						");
		query.getAndQuery("dept_id", workPlanApprDetailDTO.getDeptId());
		
		return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }

    @Override
    public int setStatus(AppReqDetailDTO appReqDetailDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("UPDATE TAWOPLANAPPR SET           ");
        query.append("       woplanappr_status  = CASE WHEN woplanappr_status = 'C' THEN 'C' ELSE ? END             		");
        query.append("WHERE  woplanappr_id      = ?		");
        query.append("  AND  comp_no       		= ?     ");
                
        Object[] objects = new Object[] {
                appReqDetailDTO.getParentStatus(),
                appReqDetailDTO.getObjectId(),
                user.getCompNo()
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }

}