package dream.consult.program.page.service;

import java.util.List;

import common.bean.User;
import dream.consult.program.page.dto.MaGrdMngCommonDTO;
import dream.consult.program.page.dto.MaPgMngCommonDTO;

/**
 * 화면 - 목록 service
 * @author  jung7126
 * @version $Id: MaGrdMngListService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
 * @since   1.0
 */
public interface MaGrdMngListService
{     
    /**
     *  grid find
     * @author  jung7126
     * @version $Id: MaGrdMngListService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @param maGrdMngCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findList(MaPgMngCommonDTO maPgMngCommonDTO, MaGrdMngCommonDTO maGrdMngCommonDTO);
    
    /**
     * delete
     * @author jung7126
     * @version $Id: MaGrdMngListService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @return
     * @throws Exception
     */
    public int deleteList(String[] deleteRows) throws Exception;
    public String findTotalCount(MaPgMngCommonDTO maPgMngCommonDTO, MaGrdMngCommonDTO maGrdMngCommonDTO, User user) throws Exception;

}
