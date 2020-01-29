package dream.mgr.usrcd.service;

import java.util.List;

import common.bean.User;
import dream.mgr.usrcd.dto.MaCdUsrDetailDTO;

/**
 * 사용자코드관리
 * @author 
 * @version $Id:$
 * @since 1.0
 */
public interface MaCdUsrDetailService
{
    /**
     * 코드 상세 조회
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
     * 사용자코드 Hdr 추가
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maCdUsrDetailDTO
     * @return
     */
    public int insertDetail(MaCdUsrDetailDTO maCdUsrDetailDTO);

	/**
	 * 사용자코드 Hdr 수정
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
