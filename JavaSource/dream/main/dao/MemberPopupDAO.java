package dream.main.dao;


import dream.main.dto.MemberPopupDTO;

/**
 * mainȭ���� member button�� user detail ȭ��
 * @author  freeze
 * @version $Id: MemberPopupDAO.java,v 1.1 2013/08/30 09:13:03 javaworker Exp $
 * @since   1.0
 */
public interface MemberPopupDAO
{
    /**
     * ���� �� ��ȸ
     * @author  freeze
     * @version $Id: MemberPopupDAO.java,v 1.1 2013/08/30 09:13:03 javaworker Exp $
     * @since   1.0
     * 
     * @param userID
     * @return
     */
	public MemberPopupDTO findDetail(String userID) ;
	
    /**
     * �������� �� ����
     * @author  freeze
     * @version $Id: MemberPopupDAO.java,v 1.1 2013/08/30 09:13:03 javaworker Exp $
     * @since   1.0
     * 
     * @param mgUserDetailDTO
     * @return
     */
	public int updateDetail(MemberPopupDTO memberPopupDTO);
}