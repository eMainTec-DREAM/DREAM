package dream.consult.program.page.service;

import java.util.List;

import common.bean.User;
import dream.consult.program.page.dto.MaPgMngCommonDTO;

/**
 * 화면 - 목록 service
 * @author  kim21017
 * @version $Id: MaPgMngListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaPgMngListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaPgMngListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maPgMngCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findPgList(MaPgMngCommonDTO maPgMngCommonDTO, User user);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaPgMngListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @return
     * @throws Exception
     */
    public int deletePage(String[] deleteRows) throws Exception;
    
    /**
     * find Total Count
     * @author  syyang
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPgMngCommonDTO
     * @param user
     * @return
     */
    public String findTotalCount(MaPgMngCommonDTO maPgMngCommonDTO, User user) throws Exception;

}
