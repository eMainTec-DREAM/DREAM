package dream.pers.mamymenu.dao;

import java.util.List;

import dream.pers.mamymenu.dto.MaMyMenuDTO;

/**
 * 사용자메뉴 - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface MaMyMenuDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maMyMenuDTO
     * @param admin 
     * @return List
     */
    public List findUsrGrpAuthList(MaMyMenuDTO maMyMenuDTO);

    public int insertMenuAuth(String menu_id, MaMyMenuDTO maMyMenuDTO);

    public int deleteMenuAuth(String menu_id, MaMyMenuDTO maMyMenuDTO);
}