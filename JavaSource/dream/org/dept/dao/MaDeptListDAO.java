package dream.org.dept.dao;

import java.util.List;

import common.bean.User;
import dream.org.dept.dto.MaDeptCommonDTO;

/**
 * �����μ� - ��� dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface MaDeptListDAO
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
    public List findDeptList(MaDeptCommonDTO maDeptCommonDTO, User user);
    
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
    public int deleteDept(String compNo, String deptId);

    public int mergeDept(String compNo, String id);

	public String getData(User user, MaDeptCommonDTO maDeptCommonDTO);
}