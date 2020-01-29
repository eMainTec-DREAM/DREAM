package dream.consult.comp.emp.dao.sqlImpl;

import java.io.IOException;
import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
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
public class ConsultCompEmpListDAOSqlImpl extends BaseJdbcDaoSupportSql implements ConsultCompEmpListDAO
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
        QuerySqlBuffer query = new QuerySqlBuffer(); 
        
        query.append("SELECT ''                                                                                                seqNo                ");
        query.append(",''                                                                                                          isDelCheck       ");
        query.append(",a.comp_no                                                                                               compNo             ");
        query.append(",b.description                                                                                       compDesc        ");
        query.append(",a.emp_no                                                                                                empNo              ");
        query.append(",a.emp_name                                                                                           empName           ");
        query.append(",dbo.SFAIDTODESC(a.dept_id, '', 'DEPT', a.comp_no)                                          deptDesc              ");
        query.append(",a.grade                                                                                                                            ");
        query.append(",a.m_phone                                                                                              mphone              ");
        query.append(",a.e_mail                                                                                                  email                ");
        query.append(",     ");
        query.getDate("a.join_date", "joinDate");
        query.append(",dbo.SFACODE_TO_DESC(a.is_join, 'IS_JOIN', 'SYS', a.comp_no,'')                        isJoin                 ");
        query.append(",     ");
        query.getDate("a.retire_date", "retireDate");
        query.append(",a.emp_id                                                                                                  empId                ");
        query.append("FROM TAEMP a INNER JOIN TACOMP b      ");
        query.append("ON a.comp_no=b.comp_no                ");
        query.append("WHERE  1=1                                                                                                             "); 
        query.append(this.getWhere(consultCompEmpCommonDTO, user));
        query.getOrderByQuery("a.emp_id", "a.emp_no", consultCompEmpCommonDTO.getOrderBy(), consultCompEmpCommonDTO.getDirection());
        
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
        QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo = consultCompEmpCommonDTO.getFilterCompNo();
        
        // CommonDTO의 equipNo가 있는경우는 상세에서 수정 되어서 List의 한 Row만을 재조회
        if (!"".equals(consultCompEmpCommonDTO.getEmpId()))
        {
            query.getAndQuery("a.emp_id", consultCompEmpCommonDTO.getEmpId());
            return query.toString();
        }
        
        //회사이름
        query.getCodeLikeQuery("a.comp_no", "b.description", compNo, consultCompEmpCommonDTO.getFilterCompDesc());
        
        query.getLikeQuery("a.emp_no", consultCompEmpCommonDTO.getFilterEmpNo());
        query.getLikeQuery("a.emp_name", consultCompEmpCommonDTO.getFilterEmpName());

        query.getDeptLevelQuery("a.dept_id", consultCompEmpCommonDTO.getFilterDeptId(), consultCompEmpCommonDTO.getFilterDeptDesc(), consultCompEmpCommonDTO.getCompNo());
        
//        query.getWkCtrLevelQuery("a.wkctr_id", consultCompEmpCommonDTO.getFilterWkCtrId(), consultCompEmpCommonDTO.getFilterWkCtrDesc(), consultCompEmpCommonDTO.getCompNo());
        
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
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("DELETE TAEMP                             ");
        query.append("WHERE  1 = 1                             ");
        query.append("  AND  emp_id   = ?                      ");

        Object[] objects = new Object[] {   
                empId
                };
        
        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    @Override
    public String findTotalCount(ConsultCompEmpCommonDTO consultCompEmpCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM TAEMP a INNER JOIN TACOMP b      ");
        query.append("ON a.comp_no=b.comp_no                ");
        query.append("WHERE  1=1                                                ");
        query.append(this.getWhere(consultCompEmpCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
}