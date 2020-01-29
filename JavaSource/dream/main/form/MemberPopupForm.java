package dream.main.form;

import java.util.Collection;

import common.struts.BaseForm;
import dream.main.dto.MemberPopupDTO;

/**
 * mainȭ���� member button�� user detail ȭ��
 * @author  freeze
 * @version $Id: MemberPopupForm.java,v 1.1 2013/08/30 09:11:19 javaworker Exp $
 * @since   1.0
 *
 * @struts.form name="memberPopupForm"
 */
public class MemberPopupForm extends BaseForm
{
	/** �� DTO */
	private MemberPopupDTO memberPopupDTO = new MemberPopupDTO();
    /** User Group */
    private Collection userGroupOptions = null;
    /** ���� */
    private Collection craftOptions = null;
    
    public MemberPopupDTO getMemberPopupDTO()
    {
        return memberPopupDTO;
    }
    public void setMemberPopupDTO(MemberPopupDTO memberPopupDTO)
    {
        this.memberPopupDTO = memberPopupDTO;
    }
    public Collection getUserGroupOptions()
    {
        return userGroupOptions;
    }
    public void setUserGroupOptions(Collection userGroupOptions)
    {
        this.userGroupOptions = userGroupOptions;
    }
    public Collection getCraftOptions()
    {
        return craftOptions;
    }
    public void setCraftOptions(Collection craftOptions)
    {
        this.craftOptions = craftOptions;
    }
    

}
