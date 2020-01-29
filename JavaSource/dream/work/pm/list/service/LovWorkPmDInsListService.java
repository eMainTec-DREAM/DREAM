package dream.work.pm.list.service;

import java.util.List;

import common.bean.User;
import dream.asset.categ.list.dto.LovEqCtgPartAcListDTO;
import dream.asset.categ.list.form.LovEqCtgPartAcListForm;
import dream.work.pm.list.dto.LovWorkPmDInsListDTO;
import dream.work.pm.list.form.LovWorkPmDInsListForm;

/**
 * �����׸� Service
 * @author  euna0207
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovWorkPmDInsListService
{

    /**
     * ������������ǰ
     * @author  euna0207
     * @version $Id:$
     * @since   1.0
     * 
     * @param loginUser
     * @param lovWorkPmDInsListDTO
     * @return
     */

	List findEqCtgPartAcList(LovWorkPmDInsListDTO lovWorkPmDInsListDTO, User user, LovWorkPmDInsListForm lovWorkPmDInsListForm) throws Exception;

	public String findTotalCount(LovWorkPmDInsListDTO lovWorkPmDInsListDTO, User loginUser, LovWorkPmDInsListForm lovWorkPmDInsListForm) throws Exception;
}