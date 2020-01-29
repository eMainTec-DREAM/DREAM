package dream.req.work.service;

import java.util.List;

import common.bean.ResponseDTO;
import common.bean.User;
import dream.req.work.dto.ReqWorkCommonDTO;

/**
 * ��� service
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 */
public interface ReqWorkListService
{
    /**
     *  grid find
     * @author  kim21017
     * @version $Id:$
     * @param reqWorkCommonDTO
     * @since   1.0
     *
     * @return ��ȸ ���
     * @throws Exception
     */
    public List findList(ReqWorkCommonDTO reqWorkCommonDTO, User user);

    /**
     * ����
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     *
     * @param deleteRows
     * @param compNo
     * @return
     */
    public ResponseDTO deleteList(String[] deleteRows, User user);
    
    public String findTotalCount(ReqWorkCommonDTO reqWorkCommonDTO, User user) throws Exception;

}
