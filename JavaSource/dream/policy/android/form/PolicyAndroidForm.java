package dream.policy.android.form;

import dream.comm.form.MaFinderAcForm;
import dream.policy.android.dto.PolicyAndroidDTO;
/**
 * 안드로이드 정책 Form
 * @author kim21017
 * @version $Id: PolicyAndroidForm.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts.form name="policyAndroidForm"
 * */

public class PolicyAndroidForm extends MaFinderAcForm{
	
	private PolicyAndroidDTO policyAndroidDTO = new PolicyAndroidDTO();

	public PolicyAndroidDTO getPolicyAndroidDTO() {
		return policyAndroidDTO;
	}

	public void setPolicyAndroidDTO(PolicyAndroidDTO policyAndroidDTO) {
		this.policyAndroidDTO = policyAndroidDTO;
	}

	
}
