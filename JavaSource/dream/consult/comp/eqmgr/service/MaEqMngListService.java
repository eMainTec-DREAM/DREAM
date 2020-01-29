package dream.consult.comp.eqmgr.service;

import java.util.List;

import common.bean.User;
import dream.consult.comp.eqmgr.dto.MaEqMngCommonDTO;

/**
 * �������� ���� - ��� service
 * @author  jung7126
 * @version $Id: MaEqMngListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaEqMngListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaEqMngListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maEqMngCommonDTO
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findEqMngList(MaEqMngCommonDTO maEqMngCommonDTO,User user);
    
    public String findTotalCount(MaEqMngCommonDTO maEqMngCommonDTO,User user);

}
