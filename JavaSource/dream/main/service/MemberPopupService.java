package dream.main.service;

import dream.main.dto.MemberPopupDTO;

/**
 * mainȭ���� member button�� user detail ȭ��
 * @author  freeze
 * @version $Id: MemberPopupService.java,v 1.1 2013/08/30 09:11:41 javaworker Exp $
 * @since   1.0
 *
 */
public interface MemberPopupService 
{
    /**
     * �������� �� ��ȸ
     * @author  freeze
     * @version $Id: MemberPopupService.java,v 1.1 2013/08/30 09:11:41 javaworker Exp $
     * @since   1.0
     * 
     * @param userID
     * @return
     */
	public MemberPopupDTO findDetail(String userID);

	/**
	 * �������� �� ����
	 * @author  freeze
	 * @version $Id: MemberPopupService.java,v 1.1 2013/08/30 09:11:41 javaworker Exp $
	 * @since   1.0
	 * 
	 * @param memberPopupDTO
	 * @return
	 */
	public int updateDetail(MemberPopupDTO memberPopupDTO);
	
}
