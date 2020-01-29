package dream.main.service;

import java.util.List;

import common.bean.User;
import dream.main.dto.MaDbListDTO;

/**
 * Dashboard - ¸ñ·Ï service
 * @author  
 * @version $Id:  $
 * @since   1.0
 */
public interface MaDbListService
{         
    public List findDbList(MaDbListDTO maDbListDTO, User user);
}
