package dream.work.pm.list.service;

import java.util.List;

import common.bean.User;
import dream.work.pm.list.dto.LovWorkPmListUInsListDTO;
import dream.work.pm.list.form.LovWorkPmListUInsListForm;

/**
 * 에너지 측정기준주기 Lov Service
 * @author  sy.yang
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovWorkPmListUInsListService
{

    /**
     * 설비종류별부품
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