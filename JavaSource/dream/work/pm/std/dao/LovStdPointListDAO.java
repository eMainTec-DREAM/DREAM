package dream.work.pm.std.dao;

import java.util.List;

import common.bean.User;
import dream.work.pm.std.dto.LovStdPointListDTO;

/**
 * 표준항목선택 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface LovStdPointListDAO
{
    /**
     * 항목 검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovStdPointListDTO
     * @param loginUser
     * @return
     */
    public List findStdPointList(LovStdPointListDTO lovStdPointListDTO, User loginUser);

	public void inputStdPoint(LovStdPointListDTO lovStdPointListDTO, User user);
}