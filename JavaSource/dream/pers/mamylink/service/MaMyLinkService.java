package dream.pers.mamylink.service;

import java.util.List;

import dream.pers.mamylink.dto.MaMyLinkDTO;

/**
 * ���ѱ׷� - ��� service
 * @author  
 * @version $Id:  $
 * @since   1.0
 */
public interface MaMyLinkService
{         
    public List findUseGrpAuthList(MaMyLinkDTO maMyLinkDTO);

    public int deleteList(MaMyLinkDTO maMyLinkDTO, String[] menuIdArr,  String[] stateArr) throws Exception;
}
