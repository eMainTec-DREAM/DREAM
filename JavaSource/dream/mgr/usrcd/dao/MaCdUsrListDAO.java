package dream.mgr.usrcd.dao;

import java.util.List;

import common.bean.User;
import dream.mgr.usrcd.dto.MaCdUsrCommonDTO;

/**
 * ������ڵ����
 * @author 
 * @version $Id: $
 * @since 1.0
 */
public interface MaCdUsrListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maCdUsrCommonDTO
     * @return 
     */
    public List findSheet(MaCdUsrCommonDTO maCdUsrCommonDTO,User user);
	
    /**
     * ������ڵ� Master, Detail ��� ����
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maCdUsrGridDTO
     * @return
     */
    public int deleteCdUsr(String compNo, String cdusrm_id);

    public String findTotalCount(MaCdUsrCommonDTO maCdUsrCommonDTO, User user);
    
}