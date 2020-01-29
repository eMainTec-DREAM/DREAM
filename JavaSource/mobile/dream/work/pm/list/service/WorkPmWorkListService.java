package mobile.dream.work.pm.list.service;

import java.util.List;

import common.bean.User;
import mobile.dream.work.pm.list.dto.WorkPmWorkCommonDTO;
import mobile.dream.work.pm.list.form.WorkPmWorkListForm;

/**
 * 계획작업 - 목록 service
 * @author  jung7126
 * @version $Id: WorkPmWorkListService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
 * @since   1.0
 */
public interface WorkPmWorkListService
{     
    /**
     *  grid find
     * @author  jung7126
     * @version $Id: WorkPmWorkListService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @param workPmWorkCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findList(WorkPmWorkCommonDTO workPmWorkCommonDTO, User user);

	/**
	 * Find Total Count
	 * @param workPmWorkListForm
	 * @param user
	 * @return
	 */
	public String findTotalCount(WorkPmWorkListForm workPmWorkListForm, User user);

}
