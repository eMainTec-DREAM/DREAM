package dream.part.list.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.part.list.dao.MaPtMstrUsedDeptDetailDAO;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.dto.MaPtMstrUsedDeptDetailDTO;

/**
 * 何前荤侩何辑 惑技 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPtMstrUsedDeptDetailDAOTarget"
 * @spring.txbn id="maPtMstrUsedDeptDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPtMstrUsedDeptDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPtMstrUsedDeptDetailDAO
{
    /**
     * detail find
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtMstrCommonDTO
     * @param loginUser
     * @return
     */
    public MaPtMstrUsedDeptDetailDTO findDetail(MaPtMstrCommonDTO maPtMstrCommonDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
        
		query.append("SELECT x.comp_no                      compNo,             ");
        query.append("       x.ptuseddept_id                ptUsedDeptId,       ");
        query.append("       x.part_id                      partId,	            ");
        query.append("       x.dept_id                      deptId,	            ");
        query.append("       y.dept_no                      deptNo,             ");
        query.append("       SFAIDTODESC(x.dept_id, '', 'DEPT', x.comp_no) deptDesc "); 
        query.append("FROM   TAPTUSEDDEPT x, TADEPT y                           ");
        query.append("WHERE  x.comp_no = y.comp_no                              ");
        query.append("  AND  x.dept_id = y.dept_id                              ");
        query.append("  AND  x.comp_no = '"+loginUser.getCompNo()+"'            ");
        query.append("  AND  x.ptuseddept_id = '"+maPtMstrCommonDTO.getPtUsedDeptId()+"' ");
    
        MaPtMstrUsedDeptDetailDTO maPtMstrUsedDeptDetailDTO = 
        		(MaPtMstrUsedDeptDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaPtMstrUsedDeptDetailDTO()));
        
        return maPtMstrUsedDeptDetailDTO;
    }
    
    /**
     * detail update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtMstrUsedDeptDetailDTO
     * @param loginUser
     * @return
     */
    public int updateDetail(MaPtMstrUsedDeptDetailDTO maPtMstrUsedDeptDetailDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAPTUSEDDEPT SET		        ");
    	query.append("	     dept_id        = ? 	        ");
    	query.append("WHERE  comp_no        = ?		        ");
    	query.append("  AND  ptuseddept_id  = ?		        ");
    	
    	Object[] objects = new Object[] {
    			maPtMstrUsedDeptDetailDTO.getDeptId(),
    			maPtMstrUsedDeptDetailDTO.getCompNo(),
    			maPtMstrUsedDeptDetailDTO.getPtUsedDeptId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtMstrUsedDeptDetailDTO
     * @param loginUser
     * @return
     */
    public int insertDetail(MaPtMstrUsedDeptDetailDTO maPtMstrUsedDeptDetailDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAPTUSEDDEPT (				                ");
    	query.append("	comp_no, ptuseddept_id, part_id,    dept_id             ");
    	query.append(")	VALUES (                                                ");
    	query.append("  ?,	     ?,             ?,          ?                   ");
    	query.append(")									                        ");
    	
    	Object[] objects = new Object[] {
    	        maPtMstrUsedDeptDetailDTO.getCompNo(),
    			maPtMstrUsedDeptDetailDTO.getPtUsedDeptId(),
    			maPtMstrUsedDeptDetailDTO.getPartId(),
    			maPtMstrUsedDeptDetailDTO.getDeptId()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
}