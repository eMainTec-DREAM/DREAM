package dream.org.dept.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.org.dept.dao.MaDeptListDAO;
import dream.org.dept.dto.MaDeptCommonDTO;

/**
 * 보전부서 - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="maDeptListDAOTarget"
 * @spring.txbn id="maDeptListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaDeptListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaDeptListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maDeptCommonDTO
     * @return List
     */
    public List findDeptList(MaDeptCommonDTO maDeptCommonDTO, User user)
    { 
        QuerySqlBuffer query = new QuerySqlBuffer();
       
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  ");
        query.append("SELECT x.dept_id                              ID,         ");
        query.append("       ''                                     SEQNO,      ");
        query.append("       ''                                     ISDELCHECK, ");
        query.append("       x.comp_no                              COMPNO,     ");
        query.append("       x.dept_id                              DEPTID,     ");
        query.append("       x.dept_no                              DEPTNO,     ");
        query.append("       x.description                          DESCRIPTION,");
        query.append("       x.p_dept_id                            PDEPTID,    ");
        query.append("       dbo.SFAIDTODESC(x.p_dept_id, '', 'DEPT', x.comp_no)     PDEPTDESC, "); 
        query.append("       x.ord_no                               ORDNO,      ");
        query.append("       dbo.SFACODE_TO_DESC(x.is_use, 'IS_USE', 'SYS', x.comp_no,'"+user.getLangId()+"') ISUSE ,    ");
        query.append("       dbo.SFACODE_TO_DESC(x.is_maint, 'IS_USE', 'SYS', x.comp_no,'"+user.getLangId()+"') ISMAINT ,    ");
        query.append("       dbo.SFACODE_TO_DESC(x.is_monitoring, 'IS_USE', 'SYS', x.comp_no,'"+user.getLangId()+"') ISMONITORING ,    ");
        query.append("       MIN(y.lvl) OVER()    AS MINLVL,    ");
        query.append("       y.lvl AS LVL                                              ");
        query.append("FROM   TADEPT x ,(SELECT * FROM dbo.SFADEPT_ALL('"+maDeptCommonDTO.getCompNo()+"','0')) y                                           ");
    	query.append("WHERE x.dept_id = y.dept_id                                                ");
    	query.getStringEqualQuery("x.comp_no", maDeptCommonDTO.getCompNo());
    	query.append(this.getWhere(maDeptCommonDTO, user));
//    	query.append("ORDER BY y.sort");
    	query.append("ORDER BY ISNULL(x.ord_no, '99999999')");
    	
        return getJdbcTemplate().queryForList(query.toString());
    } 
    
    /**
     * Filter 조건
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param maDeptCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaDeptCommonDTO maDeptCommonDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getLikeQuery("x.dept_no", maDeptCommonDTO.getFilterDeptNo());
        query.getLikeQuery("x.description", maDeptCommonDTO.getFilterDescription());
        
        // 사용여부 
        query.getSysCdQuery("x.is_use", maDeptCommonDTO.getFilterIsUse(), maDeptCommonDTO.getFilterIsUseDesc(), "IS_USE", maDeptCommonDTO.getFilterCompNo(),user.getLangId());
        // 보전부서여부 
        query.getSysCdQuery("x.is_maint", maDeptCommonDTO.getFilterIsMaint(), maDeptCommonDTO.getFilterIsMaintDesc(), "IS_USE", maDeptCommonDTO.getFilterCompNo(),user.getLangId());
        
        if(!"".equals(maDeptCommonDTO.getFilterPdeptId()))
        {
            query.getAndQuery("x.p_dept_id", maDeptCommonDTO.getFilterPdeptId());
        }
        else if(!"".equals(maDeptCommonDTO.getFilterPdeptDesc()))
        {
            query.append("AND x.p_dept_id IN (SELECT a.dept_id              ");
            query.append("                    FROM   TADEPT a               ");
            query.append("                    WHERE  1=1                    ");
            query.getLikeQuery("a.description", maDeptCommonDTO.getFilterPdeptDesc());
            query.append("                    )                             ");
        }
        
        //공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+user.getCompNo()+"' AND a.plant = x.plant )", 
        		maDeptCommonDTO.getFilterPlantId(), maDeptCommonDTO.getFilterPlantDesc());

        return query.toString();
    }

    /**
     * 삭제
     * @author  ssong
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
        
        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }

    @Override
    public int mergeDept(String compNo, String id)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("UPDATE TADEPT SET                       ");
        query.append("       p_dept_id = (SELECT p_dept_id    ");
        query.append("                    FROM TADEPT         ");
        query.append("                    WHERE comp_no = ?   ");
        query.append("                    AND dept_id   = ?)  ");
        query.append("WHERE  comp_no   = ?                    ");
        query.append("  AND  p_dept_id = ?                    ");
        
        Object[] objects = new Object[] {
                compNo,
                id,
                compNo,
                id
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }

	@Override
	public String getData(User user, MaDeptCommonDTO maDeptCommonDTO) {
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("SELECT 										");
		query.append("    CASE WHEN convert(nvarchar,MIN(x.exceltab_id)) IS NOT NULL 	");
		query.append("             THEN convert(nvarchar,MIN(x.exceltab_id))+ '//+' + min(x.description) + '//+' + min(x.table_name) 	");
		query.append("             ELSE '0' 						");
		query.append("             END           					");
		query.append("FROM TAEXCELTAB x								");
		query.append("WHERE x.exceltab_no = ?						");
		
        Object[] objects = new Object[] {
        		maDeptCommonDTO.getExceltabNo()
        };

		return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),getObject(objects)));
	}
}