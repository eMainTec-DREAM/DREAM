package dream.work.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.list.dao.WorkListPtrlResultListDAO;
import dream.work.list.dto.WorkListPtrlResultCommonDTO;

/**
 * 순회점검 작업결과 목록 dao
 * @author  youngjoo38
 * @version $Id: WorkListPtrlResultListDAO.java,v 1.0 2017/09/15 10:22:27 youngjoo38 Exp $
 * @since   1.0
 * @spring.bean id="workListPtrlResultListDAOTarget"
 * @spring.txbn id="workListPtrlResultListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkListPtrlResultListDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkListPtrlResultListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id: WorkListPtrlResultListDAO.java,v 1.0 2017/09/15 10:22:27 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param workListPtrlResultCommonDTO
     * @param workListPtrlResultListDTO
     * @param loginUser
     * @return List
     */
    public List findList(WorkListPtrlResultCommonDTO workListPtrlResultCommonDTO, User loginUser) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                        ");
        query.append("      ''                              AS seqNo                ");
        query.append("      ,''                             AS isDelCheck           ");
        query.append("      , start_date                    AS startDate            "); 
        query.append("      , y.sched_date                  AS schedDate            ");
        query.append("      , x.pmptrlrsltlist_id             AS PMPTRLRSLTLISTID     ");
        query.append("      , x.pm_id                         AS ptrlWorkNo           ");
        query.append("      , x.description                   AS ptrlWorkTitle        ");
        query.append("      , x.dept_id                       AS deptId               ");
        query.append("      , (SELECT description FROM TADEPT a WHERE x.dept_id = a.dept_id)                AS dept        ");
        query.append("      , x.shift_type                  AS shiftType            ");
        query.append("      , x.pmsched_status                AS proStatusId          ");
        query.append("      , SFACODE_TO_DESC(x.pmsched_status, 'PMSCHED_STATUS', 'SYS', x.comp_no, '"+ loginUser.getCompNo()+"')   AS proStatus   ");
        query.append("      , x.end_date || ' ' || x.end_time   AS ptrlCompleteDate     ");
        query.append("      , x.emp_id                        AS managerId            ");
        query.append("      , (SELECT emp_name                                      ");
        query.append("         FROM TAEMP a                                         ");
        query.append("         WHERE x.emp_id = a.emp_id)   AS managerDesc          ");
        query.append("      , x.close_id                      AS ptrlInspectorId      ");
        query.append("      , (SELECT a.emp_name                                      ");
        query.append("         FROM TAEMP a                                         ");
        query.append("         WHERE x.close_id = a.emp_id) AS ptrlInspector        ");
        query.append("      , x.REMARK                        AS remark               ");
        query.append("FROM TAPMPTRLRSLTLIST x JOIN TAPMPTRLSCHED y                  ");
        query.append("ON x.pmptrlrsltlist_id = y.pmptrlrsltlist_id                  ");
        query.append("WHERE  1=1                                                    ");
        query.append(this.getWhere(workListPtrlResultCommonDTO,loginUser));
        query.getOrderByQuery("x.pm_id", workListPtrlResultCommonDTO.getOrderBy(), workListPtrlResultCommonDTO.getDirection());
        
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
        QueryBuffer query = new QueryBuffer();
        
        query.append("DELETE FROM TAPMPTRLRSLTLIST                ");
        query.append("WHERE  pmptrlrsltList_id  = ?               ");
        query.append("  AND  comp_no            = ?               ");
        Object[] objects = new Object[]{
                compNo
                , id
        };
        
        return this.getJdbcTemplate().update(query.toString(),objects);
    }
    
    private String getWhere(WorkListPtrlResultCommonDTO workListPtrlResultCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        if(!"".equals(workListPtrlResultCommonDTO.getPmPtrlRsltListId())){
            query.getAndQuery("x.pmptrlrsltlist_id", workListPtrlResultCommonDTO.getPmPtrlRsltListId());
        }
        // 예정일자
        query.getDateToDateQuery("x.start_date", "x.end_date", workListPtrlResultCommonDTO.getFilterPtrlStartDate(), workListPtrlResultCommonDTO.getFilterPtrlEndDate());
        
        // 순회업무명
        query.getLikeQuery("x.description", workListPtrlResultCommonDTO.getFilterPtrlWorkTitle());

        // 부서
        if (!"".equals(workListPtrlResultCommonDTO.getFilterDeptId()))
        {
            query.getAndQuery("x.dept_id", workListPtrlResultCommonDTO.getFilterDeptId());
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
        query.getAndQuery("x.wkctr_id", workListPtrlResultCommonDTO.getFilterPtrlWorkGrpId());
        
        // 담당자
        if (!"".equals(workListPtrlResultCommonDTO.getFilterManagerId()))
        {
            query.getAndQuery("x.emp_id", workListPtrlResultCommonDTO.getFilterManagerId());
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
        query.getLikeQuery("x.pm_id", workListPtrlResultCommonDTO.getFilterPtrlWorkNo());
        
        // 점검완료일자
        query.getDateToDateQuery("x.close_date", "x.close_date", workListPtrlResultCommonDTO.getFilterPtrlComStartDate(), workListPtrlResultCommonDTO.getFilterPtrlComEndDate());
        
        // 점검자
        if (!"".equals(workListPtrlResultCommonDTO.getFilterPtrlInspectorId()))
        {
            query.getAndQuery("x.close_id", workListPtrlResultCommonDTO.getFilterPtrlInspectorId());
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
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM TAPMPTRLRSLTLIST x                                   ");
        query.append("WHERE  1=1                                                ");
        query.append(this.getWhere(workListPtrlResultCommonDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }    
    
}