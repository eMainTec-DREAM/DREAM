package dream.work.rpt.mabmeqlist.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.mabmeqlist.dto.MaBmEqListDTO;

/**
 * ���񺰰���м� service
 * @author  kim21017
 * @version $Id: MaBmEqListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaBmEqListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaBmEqListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maBmEqListDTO
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findBmEqList(MaBmEqListDTO maBmEqListDTO, User user);    
}
