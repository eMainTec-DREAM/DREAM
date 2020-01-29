package dream.mgr.usrcd.service;

import java.util.List;

import common.bean.User;
import dream.mgr.usrcd.dto.MaCdUsrDetailDTO;

/**
 * ������ڵ����
 * @author 
 * @version $Id:$
 * @since 1.0
 */
public interface MaCdUsrDetailService
{
    /**
     * �ڵ� �� ��ȸ
     * @author  
     * @version $Id: $
     * @since   1.0
     *  
     * @param compNo
     * @param dirType
     * @return
     */
    public MaCdUsrDetailDTO findDetail(User user, String dirType);
      
    /**
     * ������ڵ� Hdr �߰�
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maCdUsrDetailDTO
     * @return
     */
    public int insertDetail(MaCdUsrDetailDTO maCdUsrDetailDTO);

	/**
	 * ������ڵ� Hdr ����
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
     * @version $Id:$
     * @since   1.0
     * 
     * @param maCdUsrDetailDTO
     * @return
     * @throws Exception
     */
    public String validDirType(MaCdUsrDetailDTO maCdUsrDetailDTO) throws Exception;
}
