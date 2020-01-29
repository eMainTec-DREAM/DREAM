package dream.fail.code.dao;

import common.bean.User;
import dream.fail.code.dto.MaFailureDetailDTO;

/**
 * 고장코드 - 상세 dao
 * 
 * @author 
 * @version $Id:  $
 * @since 1.0
 */
public interface MaFailureDetailDAO
{
    /**
     * detail find
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param failureId
     * @return
     */
    public MaFailureDetailDTO findDetail(User user, String failureId);
    
    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maFailureDetailDTO
     * @return
     */
    public int insertDetail(MaFailureDetailDTO maFailureDetailDTO);
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maFailureDetailDTO
     * @return
     */
    public int updateDetail(MaFailureDetailDTO  maFailureDetailDTO);
    /**
     * valid check
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maFailureDetailDTO
     * @return
     */
    public String validFailureNo(MaFailureDetailDTO maFailureDetailDTO);

	/**
	 * Make multi TALANG Data For Fail Code
	 * @param maFailureDetailDTO
	 */
	public void makeLangData(MaFailureDetailDTO maFailureDetailDTO);

	/**
	 * update single TALANG Data For Fail Code 
	 * @param maFailureDetailDTO
	 */
	public void updateLangData(MaFailureDetailDTO maFailureDetailDTO);
}