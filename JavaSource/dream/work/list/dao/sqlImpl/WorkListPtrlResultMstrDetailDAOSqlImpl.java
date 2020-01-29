package dream.work.list.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.DateUtil;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.work.cal.pmptrlmonth.dto.WorkCalPmPtrlMonthCommonDTO;
import dream.work.list.dao.WorkListPtrlResultMstrDetailDAO;
import dream.work.list.dto.WorkListPtrlResultCommonDTO;
import dream.work.list.dto.WorkListPtrlResultMstrDetailDTO;

/**
 * 순회점검 작업결과 상세 dao
 * @author  youngjoo38
 * @version $Id: WorkListPtrlResultMstrDetailDAO.java,v 1.0 2015/12/04 08:10:27 youngjoo38 Exp $
 * @since   1.0
 * @spring.bean id="workListPtrlResultMstrDetailDAOTarget"
 * @spring.txbn id="workListPtrlResultMstrDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkListPtrlResultMstrDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkListPtrlResultMstrDetailDAO
{
    /**
     * detail find
     * @author youngjoo38
     * @version $Id: WorkListPtrlResultMstrDetailDAO.java,v 1.0 2017/09/15 10:22:27 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param workListPtrlResultCommonDTO
     * @param user
     * @return
     */
    public WorkListPtrlResultMstrDetailDTO findDetail(WorkListPtrlResultCommonDTO workListPtrlResultCommonDTO,WorkCalPmPtrlMonthCommonDTO workCalPmPtrlMonthCommonDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("SELECT                                                          ");
        query.append("      ''                                AS seqNo                ");
        query.append("      ,''                               AS isDelCheck           ");
        query.append("      , x.start_date                    AS startDate            "); // 예정일 
        query.append("      , sched_date                      AS ptrlDate             "); // 스케쥴일자 (시작일)
        query.append("      , x.pmptrlrsltlist_id             AS PMPTRLRSLTLISTID     ");
        query.append("      , x.pm_id                         AS ptrlWorkNo           ");
        query.append("      , x.description                   AS ptrlWorkTitle        ");
        query.append("      , x.dept_id                       AS deptId               ");
        query.append("      , (SELECT description FROM TADEPT a WHERE x.dept_id = a.dept_id)                AS deptDesc        ");
        query.append("      , x.shift_type                    AS ptrlShiftTypeId      ");
        query.append("      , dbo.SFACODE_TO_DESC(x.shift_type, 'SHIFT_TYPE', 'SYS', x.comp_no, ?)   AS ptrlShiftTypeDesc             ");
        query.append("      , x.pmsched_status                AS ptrlStatusId         ");
        query.append("      , dbo.SFACODE_TO_DESC(x.pmsched_status, 'PMSCHED_STATUS', 'SYS', x.comp_no, ?)   AS ptrlStatusDesc   ");
        query.append("      , x.end_date                      AS ptrlComDate          ");
        query.append("      , x.end_time                      AS ptrlComTime          ");
        query.append("      , x.emp_id                        AS managerId            ");
        query.append("      , (SELECT emp_name                                        ");
        query.append("         FROM TAEMP a                                           ");
        query.append("         WHERE x.emp_id = a.emp_id)     AS managerDesc          ");
        query.append("      , x.close_id                      AS ptrlInspectorId      ");
        query.append("      , (SELECT emp_name                                        ");
        query.append("         FROM TAEMP a                                           ");
        query.append("         WHERE x.close_id = a.emp_id)   AS ptrlInspectorDesc    ");
        query.append("      , x.REMARK                        AS remark               ");
        query.append("      , y.pmptrlsched_id                 AS pmPtrlSchedId        ");
        query.append("		,( SELECT param2  										");
        query.append("		   FROM TACDSYSD  										");
        query.append("		   WHERE cdsysd_no=x.pm_type 							");
        query.append("		    AND list_type= x.wo_type+'_TYPE' )	AS pmParam		");
        query.append("FROM TAPMPTRLRSLTLIST x  JOIN TAPMPTRLSCHED y                 ");
        query.append("ON x.pmptrlrsltlist_id = y.pmptrlrsltlist_id                  ");
        query.append("WHERE  1=1                                                    ");
        query.append(this.getWhere(workListPtrlResultCommonDTO,workCalPmPtrlMonthCommonDTO,user));

        query.append("  AND  x.comp_no              = ?                             ");

        Object[] objects = new Object[] {
                  user.getLangId()
                , user.getLangId()
                , user.getCompNo()
        };
        
        WorkListPtrlResultMstrDetailDTO workListPtrlResultMstrDetailDTO = 
                (WorkListPtrlResultMstrDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new WorkListPtrlResultMstrDetailDTO()));
        
        return workListPtrlResultMstrDetailDTO;
    }
    
    private String getWhere(WorkListPtrlResultCommonDTO workListPtrlResultCommonDTO, WorkCalPmPtrlMonthCommonDTO workCalPmPtrlMonthCommonDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        if(!"".equals(workCalPmPtrlMonthCommonDTO.getPmPtrlRsltListId()))
        {
            query.getAndQuery("x.pmptrlrsltlist_id", workCalPmPtrlMonthCommonDTO.getPmPtrlRsltListId());
        }
        
        if (!"".equals(workListPtrlResultCommonDTO.getPmPtrlRsltListId()))
        {
            query.getAndQuery("x.pmptrlrsltlist_id", workListPtrlResultCommonDTO.getPmPtrlRsltListId());
        }
        return query.toString();
    }
    
    
    /**
     * detail update
     * @author youngjoo38
     * @version $Id: WorkListPtrlResultMstrDetailDAO.java,v 1.0 2017/09/15 10:22:27 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param workListPtrlResultMstrDetailDTO
     * @param workListPtrlResultCommonDTO
     * @return
     */
    public int updateDetail(WorkListPtrlResultMstrDetailDTO workListPtrlResultMstrDetailDTO,User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAPMPTRLRSLTLIST SET        ");
        query.append("   close_id               = ?      ");
        query.append(" , remark                 = ?      ");
        query.append("WHERE pmptrlrsltlist_id   = ?      ");
        query.append("  AND comp_no             = ?      ");
        
        Object[] objects = new Object[] {
                workListPtrlResultMstrDetailDTO.getPtrlInspectorId()
                , workListPtrlResultMstrDetailDTO.getRemark()
                , workListPtrlResultMstrDetailDTO.getPmPtrlRsltListId()
                , user.getCompNo()
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
    
    @Override
    public int ptrlCompletedDetail(
            WorkListPtrlResultMstrDetailDTO workListPtrlResultMstrDetailDTO,
            User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        workListPtrlResultMstrDetailDTO.setPtrlComDate(DateUtil.getDate());
        workListPtrlResultMstrDetailDTO.setPtrlComTime(DateUtil.getDateTime().substring(8,14).trim());

        int rtnValue  = 0;

        query.append("UPDATE TAPMPTRLRSLTLIST SET       ");
        query.append("   pmsched_status         = 'C'   ");
        query.append(" , close_date             = ?     ");
        query.append(" , end_date               = ?     ");
        query.append(" , end_time               = ?     ");
        query.append(" , emp_id  = (SELECT emp_id from TAUSER where user_id = ? )     "); // 담당자 manager
        query.append(" , close_id               = ?     "); // 점검자 inspector
        query.append("WHERE  comp_no            = ?     ");
        query.append("  AND  pmptrlrsltList_id  = ?     ");
        
        Object[] objects = new Object[] {
                workListPtrlResultMstrDetailDTO.getPtrlComDate().replace("-","")
              , workListPtrlResultMstrDetailDTO.getPtrlComDate().replace("-","")
              , workListPtrlResultMstrDetailDTO.getPtrlComTime().replace(":", "").trim()
              , user.getUserId()    // 담당자
              , workListPtrlResultMstrDetailDTO.getPtrlInspectorId()    // 점검자
              , user.getCompNo()
              , workListPtrlResultMstrDetailDTO.getPmPtrlRsltListId()

        };
        
        rtnValue =  getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }
    
    public String getPtrlComDate(WorkListPtrlResultMstrDetailDTO workListPtrlResultMstrDetailDTO, User user) 
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                ");
        query.append("     end_date                     AS ptrlComDate      ");
        query.append("FROM TAPMPTRLRSLTLIST x                               ");
        query.append("WHERE  1=1                                            ");
        query.append("AND    x.comp_no    = ?                               ");
        query.append("AND    x.pmptrlrsltList_id    = ?                     ");

        
        Object[] objects = new Object[] {
                user.getCompNo()
                , workListPtrlResultMstrDetailDTO.getPmPtrlRsltListId()
        };
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(), objects));
    }

    public String getPtrlComTime(WorkListPtrlResultMstrDetailDTO workListPtrlResultMstrDetailDTO, User user) 
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                ");
        query.append("    end_time                      AS ptrlComTime      ");
        query.append("FROM TAPMPTRLRSLTLIST x                               ");
        query.append("WHERE  1=1                                            ");
        query.append("AND    x.comp_no    = ?                               ");
        query.append("AND    x.pmptrlrsltList_id    = ?                     ");
        
        
        Object[] objects = new Object[] {
                user.getCompNo()
                , workListPtrlResultMstrDetailDTO.getPmPtrlRsltListId()
        };
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(), objects));
    }

	@Override
	public int completeSched(WorkListPtrlResultMstrDetailDTO workListPtrlResultMstrDetailDTO, User user)
	{
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        workListPtrlResultMstrDetailDTO.setPtrlComDate(DateUtil.getDate());
        workListPtrlResultMstrDetailDTO.setPtrlComTime(DateUtil.getDateTime().substring(8,14).trim());

        int rtnValue  = 0;

        query.append("UPDATE TAPMPTRLSCHED SET       	");
        query.append("	  pmsched_status      	= 'C'  	");
        query.append("  , actual_date         	= ?    	");
        query.append("	, actual_time         	= ?    	");
        query.append("	, check_by            	= ?     ");
        query.append("WHERE  comp_no    		= ?     ");
        query.append("  AND  pmptrlrsltList_id  = ?     ");
        
        
        Object[] objects = new Object[] {
                workListPtrlResultMstrDetailDTO.getPtrlComDate().replace("-","")
              , workListPtrlResultMstrDetailDTO.getPtrlComTime().replace(":", "").trim()
              , workListPtrlResultMstrDetailDTO.getPtrlInspectorId()    // 점검자
              , user.getCompNo()
              , workListPtrlResultMstrDetailDTO.getPmPtrlRsltListId()

        };
        
        rtnValue =  getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
	}

	@Override
	public int executePmUpdate(WorkListPtrlResultMstrDetailDTO workListPtrlResultMstrDetailDTO, User user)
	{
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("EXEC dbo.SP_PM_UPDATE_LASTCPLT_DATE                   ");
        query.append("    '"+user.getCompNo()+"'            ");
        query.append("  , '"+workListPtrlResultMstrDetailDTO.getPtrlWorkNo()+"'              ");
        query.append("  , '"+workListPtrlResultMstrDetailDTO.getPmPtrlSchedId()+"'     ");
      
        this.getJdbcTemplate().execute(query.toString());
        
        return 0;
	}

    @Override
    public String checkDetail(WorkListPtrlResultMstrDetailDTO workListPtrlResultMstrDetailDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT ISNULL(val,0)                                                  ");
        query.append("FROM  (SELECT CONVERT(VARCHAR, sum(                                   ");
        query.append("                      CASE x.pm_point_rslt_status                     ");
        query.append("                      WHEN null THEN 1 ELSE 0 END                     ");
        query.append("                  )) val                                              ");
        query.append("         FROM TAPMPTRLRSLTPOINT x                                     ");
        query.append("         WHERE 1=1                                                    ");
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        query.getStringEqualQuery("x.pmptrlrsltlist_id", workListPtrlResultMstrDetailDTO.getPmPtrlRsltListId());
        query.append("      ) te                                                            ");
        
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
    
}