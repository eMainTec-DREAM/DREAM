package dream.consult.comp.emp.dao.oraImpl;

import java.io.IOException;
import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.consult.comp.emp.dao.ConsultCompEmpListDAO;
import dream.consult.comp.emp.dto.ConsultCompEmpCommonDTO;

/**
 * 사원 - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="consultCompEmpListDAOTarget"
 * @spring.txbn id="consultCompEmpListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultCompEmpListDAOOraImpl extends BaseJdbcDaoSupportOra implements ConsultCompEmpListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param consultCompEmpCommonDTO
     * @return List
     * @throws IOException 
     */
    public List findEmpList(ConsultCompEmpCommonDTO consultCompEmpCommonDTO, User user) throws IOException
    { 
        QueryBuffer query = new QueryBuffer(); 
        
        query.append("SELECT ''                                                                                                seqNo                ");
        query.append(",''                                                                                                          isDelCheck       ");
        query.append(",a.comp_no                                                                                               compNo             ");
        query.append(",b.description                                                                                       compDesc        ");
        query.append(",a.emp_no                                                                                                empNo              ");
        query.append(",a.emp_name                                                                                           empName           ");
        query.append(",SFAIDTODESC(a.dept_id, '', 'DEPT', a.comp_no)                                          deptDesc              ");
        query.append(",a.grade                                                                                                                            ");
        query.append(",a.m_phone                                                                                              mphone              ");
        query.append(",a.e_mail                                                                                                  email                ");
        query.append(",     ");
        query.append(" DECODE(a.join_date,'','',SUBSTR(a.join_date, 0, 4)||'-'||SUBSTR(a.join_date, 5, 2)||'-'||SUBSTR(a.join_date, 7, 2)) AS joinDate      ");
        query.append(",SFACODE_TO_DESC(a.is_join, 'IS_JOIN', 'SYS', a.comp_no,'')                        isJoin                 ");
        query.append(",     ");
        query.append(" DECODE(a.retire_date,'','',SUBSTR(a.retire_date, 0, 4)||'-'||SUBSTR(a.retire_date, 5, 2)||'-'||SUBSTR(a.retire_date, 7, 2)) AS retireDate        ");
        query.append(",a.emp_id                                                                                                  empId                ");
        query.append("FROM TAEMP a INNER JOIN TACOMP b      ");
        query.append("ON a.comp_no=b.comp_no                ");
        query.append("WHERE  1=1                                                                                                             "); 
        query.append(this.getWhere(consultCompEmpCommonDTO, user));
        query.getOrderByQuery("a.emp_id", consultCompEmpCommonDTO.getOrderBy(), consultCompEmpCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(consultCompEmpCommonDTO.getIsLoadMaxCount(), consultCompEmpCommonDTO.getFirstRow()));
    } 
    
    /**
     * Filter 조건
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param consultCompEmpCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(ConsultCompEmpCommonDTO consultCompEmpCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        String compNo = consultCompEmpCommonDTO.getFilterCompNo();
        
        if (!"".equals(consultCompEmpCommonDTO.getEmpId()))
        {
            query.getAndQuery("a.emp_id", consultCompEmpCommonDTO.getEmpId());
            return query.toString();
        }
        
        //회사이름
        query.getCodeLikeQuery("a.comp_no", "b.description", compNo, consultCompEmpCommonDTO.getFilterCompDesc());
//        query.getAndQuery("a.comp_no", consultCompEmpCommonDTO.getFilterCompNo());
//        
//        query.getSubQuery("a.comp_no", "comp_no", "description", consultCompEmpCommonDTO.getFilterCompDesc(), "TACOMP");
        
        query.getLikeQuery("a.emp_name", consultCompEmpCommonDTO.getFilterEmpName());

        checkCompNo:
        if (compNo == null || "".equals(compNo))
        {
            if(consultCompEmpCommonDTO.getFilterDeptDesc()==null||"".equals(consultCompEmpCommonDTO.getFilterDeptDesc())) break checkCompNo;
            
            if(consultCompEmpCommonDTO.getFilterDeptId()==null||"".equals(consultCompEmpCommonDTO.getFilterDeptId()))
            {
                query.append("AND a.dept_id IN (   SELECT dept_id                          ");
                query.append("                       FROM TADEPT                             ");
                query.append("                       WHERE comp_no IN (SELECT comp_no FROM TACOMP WHERE description LIKE '%"+consultCompEmpCommonDTO.getFilterCompDesc()+"%')             ");
                query.append("                       START wITH UPPER(description) LIKE UPPER('%"+consultCompEmpCommonDTO.getFilterDeptDesc()+"%')      ");
                query.append("                       CONNECT BY PRIOR dept_id = p_dept_id    ");
                query.append("                   )                                           ");
            }
            else
            {
                query.append("AND a.dept_id IN (   SELECT dept_id                          ");
                query.append("                       FROM TADEPT                             ");
                query.append("                       WHERE comp_no IN (SELECT comp_no FROM TACOMP WHERE description LIKE '%"+consultCompEmpCommonDTO.getFilterCompDesc()+"%')            ");
                query.append("                       START wITH dept_id = "+consultCompEmpCommonDTO.getFilterDeptId()+"      ");
                query.append("                       CONNECT BY PRIOR dept_id = p_dept_id    ");
                query.append("                   )                                           ");
            }
            
        }
        else 
        {
            query.getDeptLevelQuery("a.dept_id", consultCompEmpCommonDTO.getFilterDeptId(), consultCompEmpCommonDTO.getFilterDeptDesc(), compNo);
        }
        
        // 근무여부 
        query.getSysCdQuery("a.is_join", consultCompEmpCommonDTO.getFilterIsJoin(), consultCompEmpCommonDTO.getFilterIsJoinDesc(), "IS_JOIN", consultCompEmpCommonDTO.getCompNo(),user.getLangId());
        
        return query.toString();
    }

    /**
     * 삭제
     * @author ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param empId
     * @return
     */
    public int deleteEmp(String compNo, String empId)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("DELETE TAEMP                             ");
        query.append("WHERE  1= 1                              ");
        query.append("  AND  emp_id   = ?                      ");

        Object[] objects = new Object[] {   
                empId
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }

    @Override
    public String findTotalCount(ConsultCompEmpCommonDTO consultCompEmpCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM TAEMP a INNER JOIN TACOMP b      ");
        query.append("ON a.comp_no=b.comp_no                ");
        query.append("WHERE  1=1                                                ");
        query.append(this.getWhere(consultCompEmpCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
}