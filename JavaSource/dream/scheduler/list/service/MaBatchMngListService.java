package dream.scheduler.list.service;

import java.util.List;

import common.bean.User;
import dream.scheduler.list.dto.MaBatchMngCommonDTO;

/**
 * 버튼 - 목록 service
 * @author  kim21017
 * @version $Id: MaBatchMngListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaBatchMngListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaBatchMngListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maBatchMngCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findBatchList(MaBatchMngCommonDTO maBatchMngCommonDTO);    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaBatchMngListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteBatch(String[] deleteRows, User user) throws Exception;
    /**
     * exec
     * @author kim21017
     * @version $Id: MaBatchMngListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param selectRows
     * @param user
     * @return
     * @throws Exception
     */
    public int execBatch(String[] selectRows, User user) throws Exception;
    
    public String findTotalCount(MaBatchMngCommonDTO maBatchMngCommonDTO, User user);
}
