package dream.mgr.usrgrp.service;

import java.util.List;
import java.util.Map;

import org.codehaus.jettison.json.JSONException;

import common.bean.User;
import dream.mgr.usrgrp.dto.MaUsrGrpCommonDTO;

/**
 * 권한그룹 - 목록 service
 * @author  
 * @version $Id:  $
 * @since   1.0
 */
public interface MaUsrGrpAuthListService
{         
    public List findUseGrpAuthList(MaUsrGrpCommonDTO maUsrGrpCommonDTO, User user);

    public int deleteList(MaUsrGrpCommonDTO maUsrGrpCommonDTO, String[] keyNoArr, String[] keyTypeArr, String[] stateArr) throws Exception;

	public void authMenu(MaUsrGrpCommonDTO maUsrGrpCommonDTO, User user) throws JSONException;

	/**
	 * Find All Page List Under selected menu ID.
	 * @param maUsrGrpCommonDTO
	 * @return
	 */
	public List<Map<String,String>> findUseGrpPageList(MaUsrGrpCommonDTO maUsrGrpCommonDTO);

	/**
	 * Save All Authority Under specific Page
	 * @param maUsrGrpCommonDTO
	 * @param user
	 * @throws JSONException 
	 */
	public void savePageAuthList(MaUsrGrpCommonDTO maUsrGrpCommonDTO, User user) throws JSONException;

	public void saveBtnAuthList(MaUsrGrpCommonDTO maUsrGrpCommonDTO, User user) throws JSONException;

	public List findUseGrpBtnList(MaUsrGrpCommonDTO maUsrGrpCommonDTO);
	
	public String[] getAdminUsrGrp(String compNo);
}
