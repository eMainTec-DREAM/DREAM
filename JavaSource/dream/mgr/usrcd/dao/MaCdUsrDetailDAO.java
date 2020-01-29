package dream.mgr.usrcd.dao;

import common.bean.User;
import dream.mgr.usrcd.dto.MaCdUsrDetailDTO;

/**
 * ������ڵ����
 * @author 
 * @version $Id: $
 * @since 1.0
 */
public interface MaCdUsrDetailDAO
{
    /**
     * �ڵ��
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param cdUsrmId
     * @return
     */
    public MaCdUsrDetailDTO findDetail(User user, String cdUsrmId);
    
    /**
     * grid insert
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maCdUsrDetailDTO
     * @return
     */
    public int insertDetail(MaCdUsrDetailDTO maCdUsrDetailDTO);
    
    /**
     * grid update
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maCdUsrDetailDTO
     * @return
     */
    public int updateDetail(MaCdUsrDetailDTO maCdUsrDetailDTO);
      
    /**
     * valid dirType
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maCdUsrDetailDTO
     * @return
     */
    public String validDirType(MaCdUsrDetailDTO maCdUsrDetailDTO);
}