package dream.work.pm.list.service;

import java.util.List;

import common.bean.User;
import dream.work.pm.list.dto.LovWorkPmListUInsListDTO;
import dream.work.pm.list.form.LovWorkPmListUInsListForm;

/**
 * ������ ���������ֱ� Lov Service
 * @author  sy.yang
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovWorkPmListUInsListService
{

    /**
     * ������������ǰ
     * @author  sy.yang
     * @version $Id:$
     * @since   1.0
     * 
     * @param loginUser
     * @param lovWorkPmListUInsListDTO
     * @return
     */

	List findPmUInsListAcList(LovWorkPmListUInsListDTO lovWorkPmListUInsListDTO, User user, LovWorkPmListUInsListForm lovWorkPmListUInsListForm) throws Exception;

	public String findTotalCount(LovWorkPmListUInsListDTO lovWorkPmListUInsListDTO, User loginUser, LovWorkPmListUInsListForm lovWorkPmListUInsListForm) throws Exception;
}