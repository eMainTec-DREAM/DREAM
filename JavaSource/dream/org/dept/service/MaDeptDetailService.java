package dream.org.dept.service;

import common.bean.User;
import dream.org.dept.dto.MaDeptDetailDTO;

/**
 * 보전부서 - 상세 service
 * 
 * @author 
 * @version $Id:$
 * @since 1.0
 */
public interface MaDeptDetailService
{    
    /**
     * detail find
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param deptNo
     * @return
     * @throws Exception
     */
    public MaDeptDetailDTO findDetail(User user, String deptNo)throws Exception;
    
    /**
     * detail insert
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param maDeptDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaDeptDetailDTO maDeptDetailDTO) throws Exception;
    
    /**
     * detail update
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param maDeptDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaDeptDetailDTO maDeptDetailDTO) throws Exception;
    
    /**
     * valid deptNo
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param maDeptDetailDTO
     * @return
     * @throws Exception
     */
    public String validDeptNo(MaDeptDetailDTO maDeptDetailDTO) throws Exception;
}
