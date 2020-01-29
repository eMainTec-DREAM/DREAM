package dream.org.dept.dao;

import common.bean.User;
import dream.org.dept.dto.MaDeptDetailDTO;

/**
 * 보전부서 - 상세 dao
 * 
 * @author 
 * @version $Id:  $
 * @since 1.0
 */
public interface MaDeptDetailDAO
{
    /**
     * detail find
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param deptNo
     * @return
     */
    public MaDeptDetailDTO findDetail(User user, String deptId);
    
    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maDeptDetailDTO
     * @return
     */
    public int insertDetail(MaDeptDetailDTO maDeptDetailDTO);
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maDeptDetailDTO
     * @return
     */
    public int updateDetail(MaDeptDetailDTO maDeptDetailDTO);
    
    /**
     * valid deptNo
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maDeptDetailDTO
     * @return
     */
    public String validDeptNo(MaDeptDetailDTO maDeptDetailDTO);
}