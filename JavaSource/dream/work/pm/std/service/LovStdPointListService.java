package dream.work.pm.std.service;

import java.util.List;

import common.bean.User;
import dream.work.pm.std.dto.LovStdPointListDTO;

/**
 * 표준항목선택 Service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovStdPointListService
{

    /**
     * 거래처검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param LovStdPointListDTO
     * @param loginUser
     * @return
     */
    List findStdPointList(LovStdPointListDTO lovStdPointListDTO, User loginUser);

	/**
	 * Input the selected point
	 * @param deleteRows
	 * @param user
	 * @param lovStdPointListDTO 
	 */
	void inputStdPoint(String[] deleteRows, User user, LovStdPointListDTO lovStdPointListDTO);
}