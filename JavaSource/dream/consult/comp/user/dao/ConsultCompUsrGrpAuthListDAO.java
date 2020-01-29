package dream.consult.comp.user.dao;

import java.util.List;

import dream.consult.comp.user.dto.ConsultCompUsrGrpCommonDTO;

/**
 * 권한그룹 - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface ConsultCompUsrGrpAuthListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param consultCompUsrGrpCommonDTO
     * @param admin 
     * @return List
     */
    public List findUsrGrpAuthList(ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO, boolean admin);
    
    public List findUsrGrpAuthPageList(ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO);
    
    public List findUsrGrpAuthButtonList(ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO);
    
    public int insertMenuAuth(String menu_id, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO);

    public int deleteMenuAuth(String menu_id, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO);

    public int insertTabAuth(String pgPageId, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO);

    public int deleteTabAuth(String pgPageId, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO);
    
    public int chkTabAuth(String keyNo, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO);

    public int insertBtnAuth(String pgbtn_id, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO);

    public int deleteBtnAuth(String pgbtn_id, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO);
    
    public int chkBtnAuth(String keyNo, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO);

    public List findNoMenuUsrGrpAuthPageList(  ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO);

    public int insertPageAuth(String pageId,ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO);
    
    public int deletePageAuth(String pageId, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO);
    
	public int chkPageAuth(String pageId, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO);

	public int chkMenuAuth(String keyNo, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO);

	/**
	 * Delete All Button Authority 
	 * @param menuId
	 * @param consultCompUsrGrpCommonDTO
	 * @return 
	 */
	public int deleteAllBtnAuth(String menuId, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO);

	/**
	 * Delete All Tab Authority Under specific menu ID
	 * @param menuId
	 * @param consultCompUsrGrpCommonDTO
	 * @return 
	 */
	public int deleteAllTabAuth(String menuId, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO);

	/**
	 * Delete All Page Authority Under Specific menu ID
	 * @param menuId
	 * @param consultCompUsrGrpCommonDTO
	 * @return 
	 */
	public int deleteAllPageAuth(String menuId, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO);

	/**
	 * Delete All Menu Autority Under Specific menu ID
	 * @param menuId
	 * @param consultCompUsrGrpCommonDTO
	 * @return 
	 */
	public int delteAllMenuAuth(String menuId, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO);

	public void grantAllBtnAuth(String menuId, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO);

	public void grantAllTabAuth(String menuId, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO);

	public void grantAllPageAuth(String menuId, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO);

	public void grantAllMenuAuth(String menuId, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO);

	public List findUsrGrpPageList(ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO);

	public int deleteAllPageBtnAuth(String pageId, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO);

	public int deleteAllPageTabAuth(String pgPageId, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO);

	public int deleteAllPagePageAuth(String ppageId, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO);

	public void grantAllPageBtnAuth(String ppageId, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO);

	public void grantAllPageTabAuth(String pgPageId, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO);

	public void grantAllPagePageAuth(String pageId, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO);

	public List getMpageList(String menuId);

	public List findUsrGrpBtnList(ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO);

	public int deletePageBtnAuth(String pgbtnId, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO);

	public int grantPageBtnAuth(String pgbtnId, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO);
	
	public String[] getAdminUsrGrp(String compNo);
}