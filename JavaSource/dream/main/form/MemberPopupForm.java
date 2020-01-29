package dream.main.form;

import java.util.Collection;

import common.struts.BaseForm;
import dream.main.dto.MemberPopupDTO;

/**
 * main화면의 member button의 user detail 화면
 * @author  freeze
 * @version $Id: MemberPopupForm.java,v 1.1 2013/08/30 09:11:19 javaworker Exp $
 * @since   1.0
 *
 * @struts.form name="memberPopupForm"
 */
public class MemberPopupForm extends BaseForm
{
	/** 상세 DTO */
	private MemberPopupDTO memberPopupDTO = new MemberPopupDTO();
    /** User Group */
    private Collection userGroupOptions = null;
    /** 직종 */
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
