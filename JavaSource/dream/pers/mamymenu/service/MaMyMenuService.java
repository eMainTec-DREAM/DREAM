package dream.pers.mamymenu.service;

import java.util.List;

import dream.pers.mamymenu.dto.MaMyMenuDTO;

/**
 * 사용자메뉴 - 목록 service
 * @author  
 * @version $Id:  $
 * @since   1.0
 */
public interface MaMyMenuService
{         
    public List findUseGrpAuthList(MaMyMenuDTO maMyMenuDTO);

    public int deleteList(MaMyMenuDTO maMyMenuDTO, String[] menuIdArr,  String[] stateArr) throws Exception;
}
