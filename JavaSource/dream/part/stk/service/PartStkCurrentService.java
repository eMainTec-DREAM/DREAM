package dream.part.stk.service;

import java.util.List;

import common.bean.User;
import dream.part.stk.dto.PartStkCurrentDTO;

/**
 * ¸ñ·Ï
 * @author  euna0207
 * @version $Id$
 * @since   1.0
 */
public interface PartStkCurrentService
{
    public List<PartStkCurrentDTO> findPtStckList(PartStkCurrentDTO partStkCurrentDTO, User user) throws Exception;
    
    public String findTotalCount(PartStkCurrentDTO partStkCurrentDTO, User user) throws Exception;
    
    public PartStkCurrentDTO findDetail(PartStkCurrentDTO partStkCurrentDTO, User user) throws Exception;
    
}
