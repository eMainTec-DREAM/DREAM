package dream.org.dept.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.org.dept.dao.MaDeptListDAO;
import dream.org.dept.dto.MaDeptCommonDTO;

/**
 * �����μ� - ��� dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="maDeptListDAOTarget"
 * @spring.txbn id="maDeptListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaDeptListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaDeptListDAO
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
        QueryBuffer query = new QueryBuffer();
       
        query.append("SELECT b.*,  MIN(lvl) OVER() minLvl FROM (            ");
        query.append("SELECT x.dept_id                              id,         ");
        query.append("       ''                                     seqNo,      ");
        query.append("       ''                                     isDelCheck, ");
        query.append("       x.comp_no                              compNo,     ");
        query.append("       x.dept_id                              deptId,     ");
        query.append("       x.dept_no                              deptNo,     ");
        query.append("       x.description                          description,");
        query.append("       x.p_dept_id                            pdeptId,    ");
        query.append("       SFAIDTODESC(x.p_dept_id, '', 'DEPT', x.comp_no)     pdeptDesc, "); 
        query.append("       x.ord_no                               ordNo,      ");
        query.append("       SFACODE_TO_DESC(x.is_use, 'IS_USE', 'SYS', x.comp_no,'"+user.getLangId()+"') isUse ,    ");
        query.append("       SFACODE_TO_DESC(x.is_maint, 'IS_USE', 'SYS', x.comp_no,'"+user.getLangId()+"') isMaint ,    ");
        query.append("       SFACODE_TO_DESC(x.is_monitoring, 'IS_USE', 'SYS', x.comp_no,'"+user.getLangId()+"') isMonitoring ,    ");
    //    query.append("       MIN(LEVEL) OVER()    AS minLvl,    ");
        query.append("       LEVEL AS LVL                                       ");
        query.append("FROM   TADEPT x                                           ");
    	query.append("WHERE  1=1                                                 ");
    	query.getStringEqualQuery("x.comp_no", maDeptCommonDTO.getCompNo());
    	query.append(this.getWhere(maDeptCommonDTO, user));
        query.append("START WITH p_dept_id = '0'                                ");
        query.append("CONNECT BY PRIOR dept_id = p_dept_id                      ");
        query.append("ORDER SIBLINGS BY x.ord_no");
        query.append(" ) b                                                                  ");
        query.append("WHERE 1 = 1                                                           ");
        
        return getJdbcTemplate().queryForList(query.toString());
    } 
    
    /**
     * Filter ����
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
        QueryBuffer query = new QueryBuffer();
        
        query.getLikeQuery("x.dept_no", maDeptCommonDTO.getFilterDeptNo());
        query.getLikeQuery("x.description", maDeptCommonDTO.getFilterDescription());
        
        // ��뿩�� 
        query.getSysCdQuery("x.is_use", maDeptCommonDTO.getFilterIsUse(), maDeptCommonDTO.getFilterIsUseDesc(), "IS_USE", maDeptCommonDTO.getFilterCompNo(),user.getLangId());
        // �����μ����� 
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
        
        //�����ڵ�
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+user.getCompNo()+"' AND a.plant = x.plant )", 
        		maDeptCommonDTO.getFilterPlantId(), maDeptCommonDTO.getFilterPlantDesc());

        return query.toString();
    }

    /**
     * ����
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

    @Override
    public int mergeDept(String compNo, String id)
    {
        QueryBuffer query = new QueryBuffer();
        
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
        
        return getJdbcTemplate().update(query.toString(), objects);
    }

	@Override
	public String getData(User user, MaDeptCommonDTO maDeptCommonDTO) {

		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT 										");
		query.append("    CASE WHEN MIN(x.exceltab_id) IS NOT NULL 	");
		query.append("             THEN MIN(x.exceltab_id)|| '//+' || min(x.description) || '//+' || min(x.table_name) 	");
		query.append("             ELSE '0' 						");
		query.append("             END           					");
		query.append("FROM TAEXCELTAB x								");
		query.append("WHERE x.exceltab_no = ?						");
		
        Object[] objects = new Object[] {
        		maDeptCommonDTO.getExceltabNo()
        };

		return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),objects));
	}
}