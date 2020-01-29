package dream.pers.mamylink.dao;

import java.util.List;

import dream.pers.mamylink.dto.MaMyLinkDTO;

/**
 * ���ѱ׷� - ��� dao
 * 
 * @author
 * @version $Id: $
 * @since 1.0
 */
public interface MaMyLinkDAO
{
	/**
	 * grid find
	 * 
	 * @author
	 * @version $Id: $
	 * @since 1.0
	 * 
	 * @param maMyLinkDTO
	 * @param admin
	 * @return List
	 */
	public List findUsrGrpAuthList(MaMyLinkDTO maMyLinkDTO);

	public int insertMenuAuth(String menu_id, MaMyLinkDTO maMyLinkDTO);

	public int deleteMenuAuth(String menu_id, MaMyLinkDTO maMyLinkDTO);
}