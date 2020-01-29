package dream.consult.comp.user.service;

import java.util.List;
import java.util.Map;

import org.codehaus.jettison.json.JSONException;

import common.bean.User;
import dream.consult.comp.user.dto.ConsultCompUsrGrpCommonDTO;

/**
 * 권한그룹 - 목록 service
 * @author  
 * @version $Id:  $
 * @since   1.0
 */
public interface ConsultCompUsrGrpAuthListService
{         
    public List findUseGrpAuthList(ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO, User user);

    public int deleteList(ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO, String[] keyNoArr, String[] keyTypeArr, String[] stateArr) throws Exception;

	public void authMenu(ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO, User user) throws JSONException;

	/**
	 * Find All Page List Under selected menu ID.
	 * @param consultCompUsrGrpCommonDTO
	 * @return
	 */
	public List<Map<String,String>> findUseGrpPageList(ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO);

	/**
	 * Save All Authority Under specific Page
	 * @param consultCompUsrGrpCommonDTO
	 * @param user
	 * @throws JSONException 
	 */
	public void savePageAuthList(ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO, User user) throws JSONException;

	public void saveBtnAuthList(ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO, User user) throws JSONException;

	public List findUseGrpBtnList(ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO);
	
	public String[] getAdminUsrGrp(String compNo);
}
