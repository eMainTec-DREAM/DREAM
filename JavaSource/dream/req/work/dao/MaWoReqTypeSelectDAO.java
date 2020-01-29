package dream.req.work.dao;

import java.util.List;

import common.bean.User;
import dream.req.work.dto.MaWoReqTypeSelectDTO;

/**
 * 작업요청유형 선택팝업
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 *
 */
public interface MaWoReqTypeSelectDAO
{
    /**
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param loginUser
     * @return
     */
    public List findList(User loginUser, MaWoReqTypeSelectDTO maWoReqTypeSelectDTO);
}