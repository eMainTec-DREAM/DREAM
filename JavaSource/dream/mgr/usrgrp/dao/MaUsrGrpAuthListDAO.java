package dream.mgr.usrgrp.dao;

import java.util.List;

import dream.mgr.usrgrp.dto.MaUsrGrpCommonDTO;

/**
 * 권한그룹 - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface MaUsrGrpAuthListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maUsrGrpCommonDTO
     * @param admin 
     * @return List
     */
    public List findUsrGrpAuthList(MaUsrGrpCommonDTO maUsrGrpCommonDTO, boolean admin);
    
    public List findUsrGrpAuthPageList(MaUsrGrpCommonDTO maUsrGrpCommonDTO);
    
    public List findUsrGrpAuthButtonList(MaUsrGrpCommonDTO maUsrGrpCommonDTO);
    
    public int insertMenuAuth(String menu_id, MaUsrGrpCommonDTO maUsrGrpCommonDTO);

    public int deleteMenuAuth(String menu_id, MaUsrGrpCommonDTO maUsrGrpCommonDTO);

    public int insertTabAuth(String pgPageId, MaUsrGrpCommonDTO maUsrGrpCommonDTO);

    public int deleteTabAuth(String pgPageId, MaUsrGrpCommonDTO maUsrGrpCommonDTO);
    
    public int chkTabAuth(String keyNo, MaUsrGrpCommonDTO maUsrGrpCommonDTO);

    public int insertBtnAuth(String pgbtn_id, MaUsrGrpCommonDTO maUsrGrpCommonDTO);

    public int deleteBtnAuth(String pgbtn_id, MaUsrGrpCommonDTO maUsrGrpCommonDTO);
    
    public int chkBtnAuth(String keyNo, MaUsrGrpCommonDTO maUsrGrpCommonDTO);

    public List findNoMenuUsrGrpAuthPageList(  MaUsrGrpCommonDTO maUsrGrpCommonDTO);

    public int insertPageAuth(String pageId,MaUsrGrpCommonDTO maUsrGrpCommonDTO);
    
    public int deletePageAuth(String pageId, MaUsrGrpCommonDTO maUsrGrpCommonDTO);
    
	public int chkPageAuth(String pageId, MaUsrGrpCommonDTO maUsrGrpCommonDTO);

	public int chkMenuAuth(String keyNo, MaUsrGrpCommonDTO maUsrGrpCommonDTO);

	/**
	 * Delete All Button Authority 
	 * @param menuId
	 * @param maUsrGrpCommonDTO
	 * @return 
	 */
	public int deleteAllBtnAuth(String menuId, MaUsrGrpCommonDTO maUsrGrpCommonDTO);

	/**
	 * Delete All Tab Authority Under specific menu ID
	 * @param menuId
	 * @param maUsrGrpCommonDTO
	 * @return 
	 */
	public int deleteAllTabAuth(String menuId, MaUsrGrpCommonDTO maUsrGrpCommonDTO);

	/**
	 * Delete All Page Authority Under Specific menu ID
	 * @param menuId
	 * @param maUsrGrpCommonDTO
	 * @return 
	 */
	public int deleteAllPageAuth(String menuId, MaUsrGrpCommonDTO maUsrGrpCommonDTO);

	/**
	 * Delete All Menu Autority Under Specific menu ID
	 * @param menuId
	 * @param maUsrGrpCommonDTO
	 * @return 
	 */
	public int delteAllMenuAuth(String menuId, MaUsrGrpCommonDTO maUsrGrpCommonDTO);

	public void grantAllBtnAuth(String menuId, MaUsrGrpCommonDTO maUsrGrpCommonDTO);

	public void grantAllTabAuth(String menuId, MaUsrGrpCommonDTO maUsrGrpCommonDTO);

	public void grantAllPageAuth(String menuId, MaUsrGrpCommonDTO maUsrGrpCommonDTO);

	public void grantAllMenuAuth(String menuId, MaUsrGrpCommonDTO maUsrGrpCommonDTO);

	public List findUsrGrpPageList(MaUsrGrpCommonDTO maUsrGrpCommonDTO);

	public int deleteAllPageBtnAuth(String pageId, MaUsrGrpCommonDTO maUsrGrpCommonDTO);

	public int deleteAllPageTabAuth(String pgPageId, MaUsrGrpCommonDTO maUsrGrpCommonDTO);

	public int deleteAllPagePageAuth(String ppageId, MaUsrGrpCommonDTO maUsrGrpCommonDTO);

	public void grantAllPageBtnAuth(String ppageId, MaUsrGrpCommonDTO maUsrGrpCommonDTO);

	public void grantAllPageTabAuth(String pgPageId, MaUsrGrpCommonDTO maUsrGrpCommonDTO);

	public void grantAllPagePageAuth(String pageId, MaUsrGrpCommonDTO maUsrGrpCommonDTO);

	public List getMpageList(String menuId);

	public List findUsrGrpBtnList(MaUsrGrpCommonDTO maUsrGrpCommonDTO);

	public int deletePageBtnAuth(String pgbtnId, MaUsrGrpCommonDTO maUsrGrpCommonDTO);

	public int grantPageBtnAuth(String pgbtnId, MaUsrGrpCommonDTO maUsrGrpCommonDTO);
	
	public String[] getAdminUsrGrp(String compNo);
}