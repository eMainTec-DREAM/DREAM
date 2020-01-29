package dream.work.rpt.pmins.deptach.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.CommonUtil;
import common.util.QuerySqlBuffer;
import dream.work.rpt.pmins.deptach.dao.WorkRptPminsDeptAchListDAO;
import dream.work.rpt.pmins.deptach.dto.WorkRptPminsDeptAchCommonDTO;

/**
 * 예방점검 이행율(부서) 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="workRptPminsDeptAchListDAOTarget"
 * @spring.txbn id="workRptPminsDeptAchListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkRptPminsDeptAchListDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkRptPminsDeptAchListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptPminsDeptAchCommonDTO
     * @param loginUser
     * @return List
     */
    public List findList(WorkRptPminsDeptAchCommonDTO workRptPminsDeptAchCommonDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  ");
        query.append("SELECT        ");
        query.append("    ''     SEQNO      ");
        query.append("    ,''     ISDELCHECK      ");
        query.append("    ,a.dept_id                                                                                                                                    deptId         ");
        query.append("    ,(SELECT description FROM TADEPT WHERE dept_id=a.dept_id)                                                              deptDesc     ");
        query.append("    ,COUNT(a.pminslist_id)                                                                                                                    planed         ");
        query.append("    ,COUNT(CASE WHEN a.pmsched_status = 'C' THEN a.pminslist_id END)                                                     completed    ");
        query.append("    ,COUNT(CASE WHEN a.pmsched_status != 'C' THEN a.pminslist_id END)                                                    incompleted  ");
        query.append("    ,ROUND(COUNT(CASE WHEN a.pmsched_status = 'C' THEN a.pminslist_id END)/CONVERT(FLOAT,COUNT(a.pminslist_id))*100,2)  achievement ");
        query.append("FROM TAPMINSLIST a        ");
        query.append("WHERE 1=1                                                     ");
        query.append(this.getWhere(workRptPminsDeptAchCommonDTO,loginUser));
        query.append("GROUP BY      ");
        query.append("    a.dept_id     				");
        query.append("    ,a.comp_no     		");
        query.getOrderByQuery("a.comp_no", "COUNT(CASE WHEN a.pmsched_status = 'C' THEN a.pminslist_id END)/COUNT(a.pminslist_id)*100", workRptPminsDeptAchCommonDTO.getOrderBy(), workRptPminsDeptAchCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workRptPminsDeptAchCommonDTO.getIsLoadMaxCount(), workRptPminsDeptAchCommonDTO.getFirstRow()));
    }
    
    public String getWhere(WorkRptPminsDeptAchCommonDTO workRptPminsDeptAchCommonDTO,User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getAndQuery("a.comp_no", loginUser.getCompNo());
        query.getAndQuery("a.is_deleted", "N");
        
        query.append("AND a.wkor_date IS NOT NULL      ");

        String fromDate = CommonUtil.dateToData(workRptPminsDeptAchCommonDTO.getFilterStartDate()+"-01");
        String toDate   = CommonUtil.dateToData(workRptPminsDeptAchCommonDTO.getFilterEndDate()+"-01");
        
        if (fromDate != null && !"".equals(fromDate) && !"null".equals(fromDate))
        {
            query.append("AND a.wkor_date >=  CONVERT(VARCHAR,'"+fromDate+"')     ");
        }
        
        if (toDate != null && !"".equals(toDate) && !"null".equals(toDate))
        {
            query.append("AND a.wkor_date <=  CONVERT(VARCHAR, DATEADD(DAY, -1, DATEADD(MONTH, 1, '"+toDate+"')), 112)        ");
        }        
        
        query.getDeptLevelQuery("a.dept_id", workRptPminsDeptAchCommonDTO.getFilterDeptId(), workRptPminsDeptAchCommonDTO.getFilterDeptDesc(), loginUser.getCompNo());
        
        //공장코드
        query.getCodeLikeQuery("a.plant", "(SELECT description FROM  TAPLANT WHERE comp_no =a.comp_no AND plant = a.plant )", 
                workRptPminsDeptAchCommonDTO.getFilterPlantId(), workRptPminsDeptAchCommonDTO.getFilterPlantDesc());
        
        return query.toString();
    }
    
    @Override
    public String findTotalCount(WorkRptPminsDeptAchCommonDTO workRptPminsDeptAchCommonDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  ");
        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM (                                                    ");
        query.append("SELECT        ");
        query.append("     a.dept_id                                                                                                                                    deptId         ");
        query.append("    ,(SELECT description FROM TADEPT WHERE dept_id=a.dept_id)                                                              deptDesc     ");
        query.append("    ,COUNT(a.pminslist_id)                                                                                                                    planed         ");
        query.append("    ,COUNT(CASE WHEN a.pmsched_status = 'C' THEN a.pminslist_id END)                                                     completed    ");
        query.append("    ,ROUND(COUNT(CASE WHEN a.pmsched_status = 'C' THEN a.pminslist_id END)/CONVERT(FLOAT,COUNT(a.pminslist_id))*100,2)  achievement ");
        query.append("FROM TAPMINSLIST a        ");
        query.append("WHERE 1=1                                                     ");
        query.append(this.getWhere(workRptPminsDeptAchCommonDTO,loginUser));
        query.append("GROUP BY      ");
        query.append("    a.dept_id     				");
        query.append("    ,a.comp_no     		");
        query.append(") a                                                   ");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
    
}