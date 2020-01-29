package dream.main.dao;


import dream.main.dto.MemberPopupDTO;

/**
 * main화면의 member button의 user detail 화면
 * @author  freeze
 * @version $Id: MemberPopupDAO.java,v 1.1 2013/08/30 09:13:03 javaworker Exp $
 * @since   1.0
 */
public interface MemberPopupDAO
{
    /**
     * 유저 상세 조회
     * @author  freeze
     * @version $Id: MemberPopupDAO.java,v 1.1 2013/08/30 09:13:03 javaworker Exp $
     * @since   1.0
     * 
     * @param userID
     * @return
     */
	public MemberPopupDTO findDetail(String userID) ;
	
    /**
     * 유저정보 상세 수정
     * @author  freeze
     * @version $Id: MemberPopupDAO.java,v 1.1 2013/08/30 09:13:03 javaworker Exp $
     * @since   1.0
     * 
     * @param mgUserDetailDTO
     * @return
     */
	public int updateDetail(MemberPopupDTO memberPopupDTO);
}