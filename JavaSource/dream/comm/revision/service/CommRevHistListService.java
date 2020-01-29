package dream.comm.revision.service;

import java.util.List;

import common.bean.User;
import dream.comm.revision.dto.CommRevHistCommonDTO;


/**
 * ��/���� �����̷� service
 * @author  kim21017
 * @version $Id: CommRevHistListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface CommRevHistListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: CommRevHistListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param commRevHistListDTO
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findList(CommRevHistCommonDTO commRevHistCommonDTO, User user);    
}
