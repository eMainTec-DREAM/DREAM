package dream.work.list.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.work.list.dao.WorkListPtrlResultListDAO;
import dream.work.list.dto.WorkListPtrlResultCommonDTO;

/**
 * 순회점검 작업결과 목록 dao
 * @author  youngjoo38
 * @version $Id: WorkListPtrlResultListDAO.java,v 1.0 2015/12/02 09:14:12 youngjoo38 Exp $
 * @since   1.0
 * @spring.bean id="workListPtrlResultListDAOTarget"
 * @spring.txbn id="workListPtrlResultListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkListPtrlResultListDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkListPtrlResultListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id: WorkListPtrlResultListDAO.java,v 1.0 2015/12/02 09:14:12 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param workListPtrlResultCommonDTO
     * @param workListPtrlResultListDTO
     * @param loginUser
     * @return List
     */
    public List findList(WorkListPtrlResultCommonDTO workListPtrlResultCommonDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
    	query.append("SELECT                                                        ");
        query.append("      ''                              AS seqNo                ");
        query.append("      ,''                             AS isDelCheck           ");
        query.append("      , start_date                    AS schedDate            "); 
        query.append("      , pmptrlrsltlist_id             AS PMPTRLRSLTLISTID     ");
        query.append("      , pm_id                         AS ptrlWorkNo           ");
        query.append("      , description                   AS ptrlWorkTitle        ");
        query.append("      , dept_id                       AS deptId               ");
        query.append("      , (SELECT description FROM TADEPT a WHERE x.dept_id = a.dept_id)                AS dept        ");
        query.append("      , x.shift_type                  AS shiftType            ");
        query.append("      , pmsched_status                AS proStatusId          ");
        query.append("      , dbo.SFACODE_TO_DESC(x.pmsched_status, 'PMSCHED_STATUS', 'SYS', x.comp_no, '"+ loginUser.getLangId() +"')   AS proStatus   ");
        query.append("      , end_date + ' ' + end_time   	AS ptrlCompleteDate     ");
        query.append("      , emp_id                        AS managerId            ");
        query.append("      , (SELECT emp_name                                      ");
        query.append("         FROM TAEMP a                                         ");
        query.append("         WHERE x.emp_id = a.emp_id)   AS managerDesc          ");
        query.append("      , close_id                      AS ptrlInspectorId      ");
        query.append("      , (SELECT emp_name                                      ");
        query.append("         FROM TAEMP a                                         ");
        query.append("         WHERE x.close_id = a.emp_id) AS ptrlInspector        ");
        query.append("      , REMARK                        AS remark               ");
        query.append("FROM TAPMPTRLRSLTLIST x                                       ");
        query.append("WHERE  1=1                                                    ");
        query.append(this.getWhere(workListPtrlResultCommonDTO,loginUser));
        query.getOrderByQuery("x.pmptrlrsltlist_id", "x.pm_id", workListPtrlResultCommonDTO.getOrderBy(), workListPtrlResultCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workListPtrlResultCommonDTO.getIsLoadMaxCount(), workListPtrlResultCommonDTO.getFirstRow()));

    }
    
    /**
     * delete
     * @author youngjoo38
     * @version $Id: WorkListPtrlResultListDAO.java,v 1.0 2017/09/15 10:22:27 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteList(String id, String compNo)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("DELETE FROM TAPMPTRLRSLTLIST              ");
        query.append("WHERE  pmptrlrsltList_id  = '"+id+"'      ");
        query.append("  AND  comp_no            = '"+compNo+"'  ");

        return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(WorkListPtrlResultCommonDTO workListPtrlResultCommonDTO, User loginUser)
    {
    	
        QuerySqlBuffer query = new QuerySqlBuffer();
        if (!"".equals(workListPtrlResultCommonDTO.getPmPtrlRsltListId()))
        {
            query.getAndQuery("x.pmptrlrsltlist_id", workListPtrlResultCommonDTO.getPmPtrlRsltListId());
        }
        
        // 예정일자
        query.getDateToDateQuery("start_date", "end_date", workListPtrlResultCommonDTO.getFilterPtrlStartDate(), workListPtrlResultCommonDTO.getFilterPtrlEndDate());
        
        // 순회업무명
        query.getLikeQuery("description", workListPtrlResultCommonDTO.getFilterPtrlWorkTitle());

        // 부서
        if (!"".equals(workListPtrlResultCommonDTO.getFilterDeptId()))
        {
            query.getAndQuery("dept_id", workListPtrlResultCommonDTO.getFilterDeptId());
        }
        else if(!"".equals(workListPtrlResultCommonDTO.getFilterDeptDesc()))
        {
            query.append("AND x.dept_id IN (SELECT a.dept_id        ");
            query.append("                    FROM   TAEMP a        ");
            query.append("                    WHERE  1=1            ");
            query.getLikeQuery("a.description", workListPtrlResultCommonDTO.getFilterDeptDesc());
            query.append("                    )                     ");
        }
        
        // 작업그룹
        query.getAndQuery("wkctr_id", workListPtrlResultCommonDTO.getFilterPtrlWorkGrpId());
        
        // 담당자
        if (!"".equals(workListPtrlResultCommonDTO.getFilterManagerId()))
        {
            query.getAndQuery("emp_id", workListPtrlResultCommonDTO.getFilterManagerId());
        }
        else if(!"".equals(workListPtrlResultCommonDTO.getFilterManagerDesc()))
        {
            query.append("AND x.emp_id IN (SELECT a.emp_id          ");
            query.append("                    FROM   TAEMP a        ");
            query.append("                    WHERE  1=1            ");
            query.getLikeQuery("a.emp_name", workListPtrlResultCommonDTO.getFilterManagerDesc());
            query.append("                    )                     ");
        }
        
        // 순회업무#
        query.getLikeQuery("pmptrlrsltlist_id", workListPtrlResultCommonDTO.getFilterPtrlWorkNo());
        
        // 점검완료일자
        query.getDateToDateQuery("close_date", "close_date", workListPtrlResultCommonDTO.getFilterPtrlComStartDate(), workListPtrlResultCommonDTO.getFilterPtrlComEndDate());
        
        // 점검자
        if (!"".equals(workListPtrlResultCommonDTO.getFilterPtrlInspectorId()))
        {
            query.getAndQuery("close_id", workListPtrlResultCommonDTO.getFilterPtrlInspectorId());
        }
        else if(!"".equals(workListPtrlResultCommonDTO.getFilterPtrlInspectorDesc()))
        {
            query.append("AND x.close_id IN (SELECT a.emp_id        ");
            query.append("                    FROM   TAEMP a        ");
            query.append("                    WHERE  1=1            ");
            query.getLikeQuery("a.emp_name", workListPtrlResultCommonDTO.getFilterPtrlInspectorDesc());
            query.append("                    )                     ");
        }
        
        return query.toString();
    }
    
    public String findTotalCount(
            WorkListPtrlResultCommonDTO workListPtrlResultCommonDTO, User user) {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM TAPMPTRLRSLTLIST x                                   ");
        query.append("WHERE  1=1                                                ");
        query.append(this.getWhere(workListPtrlResultCommonDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }    
}