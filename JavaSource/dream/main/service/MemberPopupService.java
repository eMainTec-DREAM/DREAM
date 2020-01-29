package dream.main.service;

import dream.main.dto.MemberPopupDTO;

/**
 * main화면의 member button의 user detail 화면
 * @author  freeze
 * @version $Id: MemberPopupService.java,v 1.1 2013/08/30 09:11:41 javaworker Exp $
 * @since   1.0
 *
 */
public interface MemberPopupService 
{
    /**
     * 유저관리 상세 조회
     * @author  freeze
     * @version $Id: MemberPopupService.java,v 1.1 2013/08/30 09:11:41 javaworker Exp $
     * @since   1.0
     * 
     * @param userID
     * @return
     */
	public MemberPopupDTO findDetail(String userID);

	/**
	 * 유저관리 상세 수정
	 * @author  freeze
	 * @version $Id: MemberPopupService.java,v 1.1 2013/08/30 09:11:41 javaworker Exp $
	 * @since   1.0
	 * 
	 * @param memberPopupDTO
	 * @return
	 */
	public int updateDetail(MemberPopupDTO memberPopupDTO);
	
}
