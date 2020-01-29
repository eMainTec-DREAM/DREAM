package dream.consult.comp.dept.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
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
public class ConsultCompDeptListDAOSqlImpl extends BaseJdbcDaoSupportSql implements ConsultCompDeptListDAO
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
        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT  x.dept_id                               ID  ");
        query.append("       ,''                                      SEQNO              ");
        query.append("       ,''                                     ISDELCHECK         ");
        query.append("       ,x.comp_no                              COMPNO             ");
        query.append("       ,x.dept_id                              DEPTID             ");
        query.append("       ,x.dept_no                              DEPTNO             ");
        query.append("       ,x.description                          DESCRIPTION        ");
        query.append("       ,x.p_dept_id                            PDEPTID            ");
        query.append("       ,(select description from TACOMP y where x.comp_no=y.comp_no )  COMPDESC");
        query.append("       ,x.ord_no                               ORDNO              ");
        query.append("       ,dbo.SFACODE_TO_DESC(x.is_use, 'IS_USE', 'SYS', x.comp_no,'"+user.getLangId()+"') ISUSE    ");
        query.append("       ,dbo.SFACODE_TO_DESC(x.is_maint, 'IS_USE', 'SYS', x.comp_no,'"+user.getLangId()+"') ISMAINT    ");
        query.append("       ,MIN(y.lvl) OVER()    AS MINLVL    ");
        query.append("       ,y.lvl LEVEL                                              ");
        query.append("FROM   TADEPT x ,(SELECT * FROM dbo.SFADEPT_ALL('"+consultCompDeptCommonDTO.getFilterCompNo()+"','0')) y                                           ");
    	query.append("WHERE x.dept_id = y.dept_id                                                ");
    	query.getStringEqualQuery("x.comp_no", consultCompDeptCommonDTO.getFilterCompNo());
    	query.append(this.getWhere(consultCompDeptCommonDTO, user));
    	query.append("ORDER BY ISNULL(x.ord_no, '99999999')");
        
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
        QuerySqlBuffer query = new QuerySqlBuffer();
       
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
        QuerySqlBuffer query = new QuerySqlBuffer();
        
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