package dream.req.work.service;

import java.util.List;

import common.bean.User;
import dream.req.work.dto.MaWoReqTypeSelectDTO;

/**
 * 작업요청유형 팝업 Service
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 *
 */
public interface MaWoReqTypeSelectService
{

    /**
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param loginUser
     * @param maWoReqTypeSelectDTO
     * @return
     */
    List findList(User loginUser, MaWoReqTypeSelectDTO maWoReqTypeSelectDTO);
}