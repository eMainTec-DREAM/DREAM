package dream.org.rpt.empmttr.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.org.rpt.empmttr.dao.OrgRptEmpMttrListDAO;
import dream.org.rpt.empmttr.dto.OrgRptEmpMttrCommonDTO;

/**
 * MTTR(담당자) 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="orgRptEmpMttrListDAOTarget"
 * @spring.txbn id="orgRptEmpMttrListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class OrgRptEmpMttrListDAOOraImpl extends BaseJdbcDaoSupportOra implements OrgRptEmpMttrListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param orgRptEmpMttrCommonDTO
     * @param loginUser
     * @return List
     */
    public List findList(OrgRptEmpMttrCommonDTO orgRptEmpMttrCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                ");
        query.append("    x.empId           ");
        query.append("    , x.empName       ");
        query.append("    , x.deptId        ");
        query.append("    , x.deptDesc      ");
        query.append("    , x.workTime      ");
        query.append("    , x.failCnt       ");
        query.append("    , x.workTimeAll   ");
        query.append("    , x.failCntAll    ");
        query.append("    , ROUND((x.workTime/DECODE(x.failCnt,0,1, x.failCnt)),2) AS mttr     ");
        query.append(" FROM                 ");
        query.append("    (                 ");
        query.append("        SELECT        ");
        query.append("            x.emp_id empId                        ");
        query.append("            , (SELECT a.emp_name FROM TAEMP a WHERE a.emp_id = x.emp_id AND a.comp_no = x.comp_no) empName          ");
        query.append("            , x.dept_id deptId                    ");
        query.append("            , (SELECT a.description FROM TADEPT a WHERE a.dept_id = x.dept_id AND a.comp_no = x.comp_no) deptDesc   ");
        query.append("            , SUM(CASE WHEN x.wo_type='BM' THEN x.work_time ELSE NULL END) workTime           ");
        query.append("            , SUM(CASE WHEN x.wo_type='BM' THEN 1 ELSE NULL END)  failCnt       ");
        query.append("            , ROUND(SUM(x.work_time)/60,2) workTimeAll           ");
        query.append("            , SUM(CASE WHEN x.eqhistory_id IS NULL THEN NULL ELSE 1 END)  failCntAll       ");
        query.append("        FROM TAEQHISTORY x INNER JOIN TAEQUIPMENT y                                    ");
        query.append("        ON x.item_no = y.item_no AND x.comp_no = y.comp_no AND y.is_last_version = 'Y' AND y.is_deleted = 'N' ");
        query.append(this.getWhere(orgRptEmpMttrCommonDTO, loginUser));
        query.append("        RIGHT OUTER JOIN TAMONTH mon                                    ");
        query.append("        ON x.wkor_date LIKE mon.tmonth||'%'                                    ");
        query.append("        WHERE 1=1                                   ");
        query.getAndDateQuery("mon.tmonth", orgRptEmpMttrCommonDTO.getFilterStartDate(), orgRptEmpMttrCommonDTO.getFilterEndDate());
        query.append("        GROUP BY x.comp_no, x.emp_id, x.dept_id");
        query.append("    ) x                                           ");
        query.getOrderByQuery("ROUND((x.workTime/DECODE(x.failCnt,0,1, x.failCnt)),2)", orgRptEmpMttrCommonDTO.getOrderBy(), orgRptEmpMttrCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(orgRptEmpMttrCommonDTO.getIsLoadMaxCount(), orgRptEmpMttrCommonDTO.getFirstRow()));
    }
    
    public String getWhere(OrgRptEmpMttrCommonDTO orgRptEmpMttrCommonDTO,User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.getStringEqualQuery("x.comp_no", loginUser.getCompNo());
        
        query.getStringEqualQuery("x.self_vendor_type", "SELF");
        
        query.getDeptLevelQuery("y.usage_dept", orgRptEmpMttrCommonDTO.getFilterUsageDept(), orgRptEmpMttrCommonDTO.getFilterUsageDeptDesc(), loginUser.getCompNo());
        
        query.getSysCdQuery("y.eq_grade", orgRptEmpMttrCommonDTO.getFilterEqGrade(), orgRptEmpMttrCommonDTO.getFilterEqGradeDesc(), "EQ_GRADE", loginUser.getCompNo(), loginUser.getLangId());
        // 부서
        query.getDeptLevelQuery("x.dept_id", orgRptEmpMttrCommonDTO.getFilterDeptId(), orgRptEmpMttrCommonDTO.getFilterDeptDesc(), loginUser.getCompNo());

        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM TAPLANT a WHERE a.plant = x.plant AND a.comp_no = x.comp_no)", orgRptEmpMttrCommonDTO.getFilterPlantId(), orgRptEmpMttrCommonDTO.getFilterPlantDesc());
        
        return query.toString();
    }
    
    @Override
    public String findTotalCount(OrgRptEmpMttrCommonDTO orgRptEmpMttrCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                ");
        query.append("       COUNT(1)       ");
        query.append("FROM (                ");
        query.append("  SELECT              ");
        query.append("    x.empId           ");
        query.append("  FROM                ");
        query.append("    (                 ");
        query.append("        SELECT        ");
        query.append("            x.emp_id empId        ");
        query.append("        FROM TAEQHISTORY x INNER JOIN TAEQUIPMENT y                                    ");
        query.append("        ON x.item_no = y.item_no AND x.comp_no = y.comp_no AND y.is_last_version = 'Y' AND y.is_deleted = 'N' ");
        query.append(this.getWhere(orgRptEmpMttrCommonDTO, loginUser));
        query.append("        RIGHT OUTER JOIN TAMONTH mon                                    ");
        query.append("        ON x.wkor_date LIKE mon.tmonth||'%'                                    ");
        query.append("        WHERE 1=1                                   ");
        query.getAndDateQuery("mon.tmonth", orgRptEmpMttrCommonDTO.getFilterStartDate(), orgRptEmpMttrCommonDTO.getFilterEndDate());
        query.append("        GROUP BY x.comp_no, x.emp_id, x.dept_id      ");
        query.append("    ) x                                   ");
        query.append(") a                                       ");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
    
}