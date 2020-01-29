package dream.part.pur.req.service;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.ass.asset.dto.AssAssetCommonDTO;
import dream.part.pur.req.dto.MaPtReqCommonDTO;

/**
 * 부품수리 - 목록 service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaPtPurReqListService
{     
    /**
     *  grid find
     * @author  ssong
     * @version $Id:$
     * @param maPtReqCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findList(MaPtReqCommonDTO maPtReqCommonDTO,User user);
    /**
     * grid save
     * @author nhkim8548
     * @version $Id:$
     * @since   1.0
     * @param gridList
     * @param user
     * @throws Exception
     */
    public void saveList(List<Map> gridList, User user) throws Exception;
    /**
     * delete
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param user
     * @param deleteRows
     * @return
     * @throws Exception
     */
    public int deleteList(User user, String[] deleteRows) throws Exception;
    /**
     * update Status
     * @author nhkim8548
     * @version $Id:$
     * @since   1.0
     * @param maPtReqCommonDTO
     * @param user
     * @param deleteRows
     * @return
     * @throws Exception
     */
    public int updateStatus(User user, String[] selectRows) throws Exception;
    public String findTotalCount(MaPtReqCommonDTO maPtReqCommonDTO, User user) throws Exception;

}
