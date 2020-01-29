package dream.part.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.part.list.dao.MaPtMstrUsedDeptListDAO;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.dto.MaPtMstrUsedDeptListDTO;

/**
 * 何前荤侩何辑 格废 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPtMstrUsedDeptListDAOTarget"
 * @spring.txbn id="maPtMstrUsedDeptListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPtMstrUsedDeptListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPtMstrUsedDeptListDAO
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtMstrCommonDTO
     * @param loginUser
     * @return List
     */
    public List findList(MaPtMstrCommonDTO maPtMstrCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT											        ");
        query.append("       ''                             seqNo,		        ");
        query.append("       ''                             isDelCheck,         ");
        query.append("       x.comp_no                      compNo,	            ");
        query.append("       x.ptuseddept_id                ptUsedDeptId,       ");
        query.append("       x.part_id                      partId,             ");
        query.append("       x.dept_id                      deptId,             ");
        query.append("       y.dept_no                      deptNo,             ");
        query.append("       SFAIDTODESC(x.dept_id, '', 'DEPT', x.comp_no) deptDesc "); 
        query.append("FROM   TAPTUSEDDEPT x, TADEPT y                           ");
        query.append("WHERE  x.comp_no = y.comp_no                              ");
        query.append("  AND  x.dept_id = y.dept_id                              ");
        query.append(this.getWhere(maPtMstrCommonDTO,loginUser));
        query.getOrderByQuery("x.ptuseddept_id", maPtMstrCommonDTO.getOrderBy(), maPtMstrCommonDTO.getDirection());
    	
        return getJdbcTemplate().queryForList(query.toString(maPtMstrCommonDTO.getIsLoadMaxCount(), maPtMstrCommonDTO.getFirstRow()));
    }
    
    /**
     * delete
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param eqPartId
     * @return
     */
    public int deleteList(MaPtMstrUsedDeptListDTO maPtMstrUsedDeptListDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("DELETE FROM TAPTUSEDDEPT                                   ");
    	query.append("WHERE  comp_no       = '"+loginUser.getCompNo()+"'         ");
    	query.append("  AND  ptuseddept_id = '"+maPtMstrUsedDeptListDTO.getPtUsedDeptId()+"' ");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(MaPtMstrCommonDTO maPtMstrCommonDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	query.getAndQuery("x.part_id", maPtMstrCommonDTO.getPartId());
    	query.getAndQuery("x.comp_no", loginUser.getCompNo());
    	if (!"".equals(maPtMstrCommonDTO.getPtUsedDeptId()))
        {
            query.getAndQuery("x.ptuseddept_id", maPtMstrCommonDTO.getPtUsedDeptId());
            return query.toString();
        }
    	
    	return query.toString();
    }

	@Override
	public String findTotalCount(MaPtMstrCommonDTO maPtMstrCommonDTO, User user) {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT											        ");
        query.append("  COUNT(1)                           				        ");
        query.append("FROM   TAPTUSEDDEPT x, TADEPT y                           ");
        query.append("WHERE  x.comp_no = y.comp_no                              ");
        query.append("  AND  x.dept_id = y.dept_id                              ");
        query.append(this.getWhere(maPtMstrCommonDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    
	}
}