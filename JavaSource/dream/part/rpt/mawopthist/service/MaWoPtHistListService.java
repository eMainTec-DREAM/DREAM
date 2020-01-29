package dream.part.rpt.mawopthist.service;

import java.util.List;

import common.bean.User;
import dream.part.rpt.mawopthist.dto.MaWoPtHistCommonDTO;
import dream.part.rpt.mawopthist.form.MaWoPtHistListForm;

/**
 * 부품사용이력 - 목록 service
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface MaWoPtHistListService
{     
    /**
     *  grid find
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param maWoPtHistCommonDTO
     * @param loginUser
     * @return 조회 결과 
     * @throws Exception
     */
    public List findList(MaWoPtHistCommonDTO maWoPtHistCommonDTO, User loginUser);    
   
    /**
     * delete List
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param deleteRows
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int deleteList(String[] deleteRows, User loginUser) throws Exception;
    /**
     * req
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maWoPtHistListForm
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int reqPtBuy(MaWoPtHistListForm maWoPtHistListForm, User loginUser) throws Exception;
    
    public String findTotalCount(MaWoPtHistCommonDTO maWoPtHistCommonDTO, User loginUser);
}
