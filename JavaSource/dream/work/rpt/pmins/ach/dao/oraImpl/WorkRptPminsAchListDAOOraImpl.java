package dream.work.rpt.pmins.ach.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.CommonUtil;
import common.util.QueryBuffer;
import dream.work.rpt.pmins.ach.dao.WorkRptPminsAchListDAO;
import dream.work.rpt.pmins.ach.dto.WorkRptPminsAchCommonDTO;

/**
 * 예방점검 이행율(담당자) 목록 dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="workRptPminsAchListDAOTarget"
 * @spring.txbn id="workRptPminsAchListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkRptPminsAchListDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkRptPminsAchListDAO
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptPminsAchCommonDTO
     * @param loginUser
     * @return List
     */
    public List findList(WorkRptPminsAchCommonDTO workRptPminsAchCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT        ");
        query.append("    ''     SEQNO      ");
        query.append("    ,''     ISDELCHECK      ");
        query.append("    ,a.emp_id                                                                                                                                      empId         ");
        query.append("    ,(SELECT emp_name FROM TAEMP WHERE emp_id=a.emp_id)                                                                 empDesc     ");
        query.append("    ,a.dept_id                                                                                                                                    deptId         ");
        query.append("    ,(SELECT description FROM TADEPT WHERE dept_id=a.dept_id)                                                              deptDesc     ");
        query.append("    ,COUNT(a.pminslist_id)                                                                                                                    planed         ");
        query.append("    ,COUNT(CASE WHEN a.pmsched_status = 'C' THEN a.pminslist_id END)                                                     completed    ");
        query.append("    ,COUNT(CASE WHEN a.pmsched_status != 'C' THEN a.pminslist_id END)                                                    incompleted  ");
        query.append("    ,ROUND(COUNT(CASE WHEN a.pmsched_status = 'C' THEN a.pminslist_id END)/COUNT(a.pminslist_id)*100,2)  achievement ");
        query.append("FROM TAPMINSLIST a        ");
        query.append("WHERE 1=1                                                     ");
        query.append(this.getWhere(workRptPminsAchCommonDTO,loginUser));
        query.append("GROUP BY      ");
        query.append("    a.emp_id       ");
        query.append("    ,a.dept_id     ");
        query.append("    ,a.comp_no     																													");
        query.getOrderByQuery("a.comp_no", "COUNT(CASE WHEN a.pmsched_status = 'C' THEN a.pminslist_id END)/COUNT(a.pminslist_id)*100, (SELECT emp_name FROM TAEMP WHERE emp_id=a.emp_id)", workRptPminsAchCommonDTO.getOrderBy(), workRptPminsAchCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workRptPminsAchCommonDTO.getIsLoadMaxCount(), workRptPminsAchCommonDTO.getFirstRow()));
    }
    
    public String getWhere(WorkRptPminsAchCommonDTO workRptPminsAchCommonDTO,User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("a.comp_no", loginUser.getCompNo());
        query.getAndQuery("a.is_deleted", "N");
        
        query.append("AND a.wkor_date IS NOT NULL      ");
        query.append("AND a.emp_id IS NOT NULL      ");

        String fromDate = CommonUtil.dateToData(workRptPminsAchCommonDTO.getFilterStartDate());
        String toDate   = CommonUtil.dateToData(workRptPminsAchCommonDTO.getFilterEndDate());
        
        if (fromDate != null && !"".equals(fromDate) && !"null".equals(fromDate))
        {
            query.append("AND a.wkor_date >=  TO_CHAR(TO_DATE('"+fromDate+"','YYYY-MM'),'YYYYMMDD')     ");
        }
        
        if (toDate != null && !"".equals(toDate) && !"null".equals(toDate))
        {
            query.append("AND a.wkor_date <  TO_CHAR(ADD_MONTHS(TO_DATE('"+toDate+"','YYYY-MM'),1),'YYYYMMDD')        ");
        }        
        
        query.getDeptLevelQuery("a.dept_id", workRptPminsAchCommonDTO.getFilterDeptId(), workRptPminsAchCommonDTO.getFilterDeptDesc(), loginUser.getCompNo());
        
        //담당자
        query.getCodeLikeQuery("a.emp_id", "(SELECT b.emp_name FROM  TAEMP b WHERE b.emp_id = a.emp_id AND b.comp_no='"+loginUser.getCompNo()+"')", 
                workRptPminsAchCommonDTO.getFilterEmpId(), workRptPminsAchCommonDTO.getFilterEmpDesc());

        //공장코드
        query.getCodeLikeQuery("a.plant", "(SELECT description FROM  TAPLANT WHERE comp_no =a.comp_no AND plant = a.plant )", 
                workRptPminsAchCommonDTO.getFilterPlantId(), workRptPminsAchCommonDTO.getFilterPlantDesc());
        
        return query.toString();
    }
    
    @Override
    public String findTotalCount(WorkRptPminsAchCommonDTO workRptPminsAchCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM (                                                    ");
        query.append("SELECT        ");
        query.append("    a.emp_id                                                                                                                                      empId         ");
        query.append("    ,(SELECT emp_name FROM TAEMP WHERE emp_id=a.emp_id)                                                                 empDesc     ");
        query.append("    ,a.dept_id                                                                                                                                    deptId         ");
        query.append("    ,(SELECT description FROM TADEPT WHERE dept_id=a.dept_id)                                                              deptDesc     ");
        query.append("    ,COUNT(a.pminslist_id)                                                                                                                    planed         ");
        query.append("    ,COUNT(CASE WHEN a.pmsched_status = 'C' THEN a.pminslist_id END)                                                     completed    ");
        query.append("    ,ROUND(COUNT(CASE WHEN a.pmsched_status = 'C' THEN a.pminslist_id END)/COUNT(a.pminslist_id)*100,2)  achievement ");
        query.append("FROM TAPMINSLIST a        ");
        query.append("WHERE 1=1                                                     ");
        query.append(this.getWhere(workRptPminsAchCommonDTO,loginUser));
        query.append("GROUP BY      ");
        query.append("    a.emp_id       ");
        query.append("    ,a.dept_id     ");
        query.append(")                                                     ");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
    
}