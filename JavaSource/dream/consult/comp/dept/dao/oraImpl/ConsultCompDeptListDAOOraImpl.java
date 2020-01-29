package dream.consult.comp.dept.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.consult.comp.dept.dao.ConsultCompDeptListDAO;
import dream.consult.comp.dept.dto.ConsultCompDeptCommonDTO;

/**
 * 보전부서 - 목록 dao
 * @author  hyosung
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="consultCompDeptListDAOTarget"
 * @spring.txbn id="consultCompDeptListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultCompDeptListDAOOraImpl extends BaseJdbcDaoSupportOra implements ConsultCompDeptListDAO
{
    /**
     * grid find
     * @author  hyosung
     * @version $Id: $
     * @since   1.0
     * 
     * @param consultCompDeptCommonDTO
     * @return List
     */
    public List findDeptList(ConsultCompDeptCommonDTO consultCompDeptCommonDTO, User user)
    { 
        
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT  x.dept_id                               id  ");
        query.append("       ,''                                      seqNo              ");
        query.append("       ,''                                     isDelCheck         ");
        query.append("       ,x.comp_no                              compNo             ");
        query.append("       ,x.dept_id                              deptId             ");
        query.append("       ,x.dept_no                              deptNo             ");
        query.append("       ,x.description                          description        ");
        query.append("       ,x.p_dept_id                            pdeptId            ");
        query.append("       ,(select description from TACOMP y where x.comp_no=y.comp_no )  compDesc");
        query.append("       ,x.ord_no                               ordNo              ");
        query.append("       ,SFACODE_TO_DESC(x.is_use, 'IS_USE', 'SYS', x.comp_no,'"+user.getLangId()+"') isUse    ");
        query.append("       ,SFACODE_TO_DESC(x.is_maint, 'IS_USE', 'SYS', x.comp_no,'"+user.getLangId()+"') isMaint    ");
        query.append("       ,MIN(LEVEL) OVER()    AS minLvl             ");
        query.append("       ,LEVEL                                                     ");
        query.append("FROM   TADEPT x                                                   ");
        query.append("WHERE  1=1                                                        ");
        query.append("       AND x.comp_no ='"+consultCompDeptCommonDTO.getFilterCompNo()+"'  ");
        query.append(this.getWhere(consultCompDeptCommonDTO, user));
        query.append("START WITH p_dept_id = '0'                                ");
        query.append("CONNECT BY PRIOR dept_id = p_dept_id                      ");
        query.append("ORDER BY x.ord_no");
        
        return getJdbcTemplate().queryForList(query.toString());
    } 
    
    /**
     * Filter 조건
     * @author  hyosung
     * @version $Id: $
     * @since   1.0
     * 
     * @param consultCompDeptCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(ConsultCompDeptCommonDTO consultCompDeptCommonDTO, User user)
    {        
        
        //where절 검색은 여기서 처리.
        QueryBuffer query = new QueryBuffer();
       
        if(!"".equals(consultCompDeptCommonDTO.getDeptId())){
        query.getAndQuery("x.dept_id", consultCompDeptCommonDTO.getDeptId());
        query.append("AND x.p_dept_id=(select xx.p_dept_id from TADEPT xx where xx.dept_id='"+consultCompDeptCommonDTO.getDeptId()+"')");

        return query.toString();
        }
        //부서코드 
        query.getLikeQuery("x.dept_no", consultCompDeptCommonDTO.getFilterDeptNo());
        //부서명 
        query.getLikeQuery("x.description", consultCompDeptCommonDTO.getFilterDescription());
        // 사용여부 
        query.getSysCdQuery("x.is_use", consultCompDeptCommonDTO.getFilterIsUse(), consultCompDeptCommonDTO.getFilterIsUseDesc(), "IS_USE", consultCompDeptCommonDTO.getFilterCompNo(),user.getLangId());
        // 보전부서여부 
        query.getSysCdQuery("x.is_maint", consultCompDeptCommonDTO.getFilterIsMaint(), consultCompDeptCommonDTO.getFilterIsMaintDesc(), "IS_USE", consultCompDeptCommonDTO.getFilterCompNo(),user.getLangId());
       
        
        return query.toString();
    }

    /**
     * 삭제
     * @author  hyosung
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param deptId
     * @return
     */
    public int deleteDept(String compNo, String deptId)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("DELETE TADEPT          ");
        query.append("WHERE  comp_no  = ?    ");
        query.append("  AND  dept_id  = ?    ");      
        
        Object[] objects = new Object[] {   
                compNo,
                deptId
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
}