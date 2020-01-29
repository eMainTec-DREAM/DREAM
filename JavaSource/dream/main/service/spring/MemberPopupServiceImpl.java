package dream.main.service.spring;

import dream.main.dao.MemberPopupDAO;
import dream.main.dto.MemberPopupDTO;
import dream.main.service.MemberPopupService;

/**
 * main화면의 member button의 user detail 화면
 * @author  freeze
 * @version $Id: MemberPopupServiceImpl.java,v 1.1 2013/08/30 09:11:49 javaworker Exp $
 * @since   1.0
 *
 * @spring.bean id="memberPopupServiceTarget"
 * @spring.txbn id="memberPopupService"
 * @spring.property name="memberPopupDAO" ref="memberPopupDAO"
 */
public class MemberPopupServiceImpl implements MemberPopupService
{
	private MemberPopupDAO memberPopupDAO;
	

	public MemberPopupDTO findDetail(String userID) 
	{
		return memberPopupDAO.findDetail(userID);
	}
	
	public int updateDetail(MemberPopupDTO memberPopupDTO)
	{
		return memberPopupDAO.updateDetail(memberPopupDTO);
	}
	
    public MemberPopupDAO getMemberPopupDAO()
    {
        return memberPopupDAO;
    }

    public void setMemberPopupDAO(MemberPopupDAO memberPopupDAO)
    {
        this.memberPopupDAO = memberPopupDAO;
    }
	
	
}
