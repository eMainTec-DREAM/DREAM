package dream.consult.comp.list.service;

import java.util.List;

import common.bean.User;
import dream.consult.comp.list.dto.MaCompMngCommonDTO;

/**
 * 회사설정 - 목록 service
 * @author  kim21017
 * @version $Id: MaCompMngListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaCompMngListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaCompMngListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maCompMngCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findCompList(MaCompMngCommonDTO maCompMngCommonDTO, User user);    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaCompMngListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @return
     * @throws Exception
     */
    public int deleteComp(String[] deleteRows) throws Exception;
    
    public String findTotalCount(MaCompMngCommonDTO maCompMngCommonDTO, User user);
}
