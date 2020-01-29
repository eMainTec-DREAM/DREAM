package dream.work.list.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.DateUtil;
import common.util.QueryBuffer;
import dream.work.cal.pmptrlmonth.dto.WorkCalPmPtrlMonthCommonDTO;
import dream.work.list.dao.WorkListPtrlResultMstrDetailDAO;
import dream.work.list.dto.WorkListPtrlResultCommonDTO;
import dream.work.list.dto.WorkListPtrlResultMstrDetailDTO;

/**
 * 순회점검 작업결과 상세 dao
 * @author  youngjoo38
 * @version $Id: WorkListPtrlResultMstrDetailDAO.java,v 1.0 2017/09/15 10:22:27 youngjoo38 Exp $
 * @since   1.0
 * @spring.bean id="workListPtrlResultMstrDetailDAOTarget"
 * @spring.txbn id="workListPtrlResultMstrDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkListPtrlResultMstrDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkListPtrlResultMstrDetailDAO
{
    /**
     * detail find
     * @author youngjoo38
     * @version $Id: WorkListPtrlResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param workListPtrlResultCommonDTO
     * @param user
     * @return
     */
    public WorkListPtrlResultMstrDetailDTO findDetail(WorkListPtrlResultCommonDTO workListPtrlResultCommonDTO,WorkCalPmPtrlMonthCommonDTO workCalPmPtrlMonthCommonDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

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
        query.append("      , SFACODE_TO_DESC(x.shift_type, 'SHIFT_TYPE', 'SYS', x.comp_no, ?)   AS ptrlShiftTypeDesc             ");
        query.append("      , x.pmsched_status                AS ptrlStatusId         ");
        query.append("      , SFACODE_TO_DESC(x.pmsched_status, 'PMSCHED_STATUS', 'SYS', x.comp_no, ?)   AS ptrlStatusDesc   ");
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
        query.append("      , y.pmptrlsched_id                 AS pmPtrlSchedId       ");
        query.append("		,( SELECT param2  										");
        query.append("		   FROM TACDSYSD  										");
        query.append("		   WHERE cdsysd_no=x.pm_type 							");
        query.append("		    AND list_type= x.wo_type||'_TYPE' )	AS pmParam		");
        query.append("FROM TAPMPTRLRSLTLIST x  JOIN TAPMPTRLSCHED y                 ");
        query.append("ON x.pmptrlrsltlist_id = y.pmptrlrsltlist_id                  ");
    	query.append("WHERE	 1=1                       					            ");
        query.append(this.getWhere(workListPtrlResultCommonDTO,workCalPmPtrlMonthCommonDTO,user));

    	query.append("  AND	 x.comp_no 				= ?					            ");

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
        QueryBuffer query = new QueryBuffer();
        
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
     * @version $Id: WorkListPtrlResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param workListPtrlResultMstrDetailDTO
     * @param workListPtrlResultCommonDTO
     * @return
     */
    public int updateDetail(WorkListPtrlResultMstrDetailDTO workListPtrlResultMstrDetailDTO,User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAPMPTRLRSLTLIST SET	     ");
    	query.append("	 close_id	            = ?    	 "); 
    	query.append(" , remark				    = ?		 ");
    	query.append("WHERE pmptrlrsltlist_id	= ?		 ");
    	query.append("  AND comp_no				= ?		 ");
    	
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
        QueryBuffer query = new QueryBuffer();
        
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
        QueryBuffer query = new QueryBuffer();
        
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
        QueryBuffer query = new QueryBuffer();
        
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

    /**
     * completeSched
     * @author syyang
     * @version $Id: WorkPmiDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param workListPtrlResultMstrDetailDTO
     * @param user
     * @return
     */
    public int completeSched(WorkListPtrlResultMstrDetailDTO workListPtrlResultMstrDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
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
        
        rtnValue = getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }
    
    /**
     *  SP_PM_UPDATE_LASTCPLT_DATE 프로시져 호출 
     * @author  syyang
     * @version $Id:$
     * @since   1.0
     * 
     * @param workListPtrlResultMstrDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int executePmUpdate(WorkListPtrlResultMstrDetailDTO workListPtrlResultMstrDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("begin                                                     ");
        query.append("SP_PM_UPDATE_LASTCPLT_DATE('"+user.getCompNo()+"', '"+workListPtrlResultMstrDetailDTO.getPtrlWorkNo()+"','"+workListPtrlResultMstrDetailDTO.getPmPtrlSchedId()+"' );          ");
        query.append("end;                                                      ");
      
        this.getJdbcTemplate().execute(query.toString());
        
        return 0;
    }

    @Override
    public String checkDetail(WorkListPtrlResultMstrDetailDTO workListPtrlResultMstrDetailDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT NVL(val,0) FROM (select to_char(sum(decode(x.pm_point_rslt_status,null,1,0))) val");
        query.append("from TAPMPTRLRSLTPOINT x                      ");
        query.append("WHERE 1=1                                     ");
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        query.getStringEqualQuery("x.pmptrlrsltlist_id", workListPtrlResultMstrDetailDTO.getPmPtrlRsltListId());
        query.append(")                                             ");
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
    
    
}