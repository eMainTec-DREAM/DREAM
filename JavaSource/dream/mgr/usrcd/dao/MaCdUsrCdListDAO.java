package dream.mgr.usrcd.dao;

import java.util.List;

import common.bean.User;
import dream.mgr.usrcd.dto.MaCdUsrCommonDTO;

/**
 * ������ڵ���� �� ���
 * @author 
 * @version $Id: $
 * @since 1.0
 */
public interface MaCdUsrCdListDAO
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
     * ���õ� ���� ������ ���� 
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maCdUsrCdGridDTO
     * @return
     */
    public int deleteCdUsrCdList(String compNo, String cdUsrdId);
    
    public String findTotalCount(MaCdUsrCommonDTO maCdUsrCommonDTO, User user);
}