package mobile.dream.work.pm.list.dao;

import java.util.List;

import common.bean.User;
import mobile.dream.work.pm.list.dto.WorkPmWorkCommonDTO;
import mobile.dream.work.pm.list.form.WorkPmWorkListForm;

/**
 * 계획작업 - 목록 dao
 * @author  jung7126
 * @version $Id: WorkPmWorkListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
 * @since   1.0
 */
public interface WorkPmWorkListDAO
{
    /**
     * grid find
     * @author  jung7126
     * @version $Id: WorkPmWorkListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
     * @since   1.0
     * 
     * @param workPmWorkCommonDTO
     * @return List
     */
    public List findList(WorkPmWorkCommonDTO workPmWorkCommonDTO, User user);

	/**
	 * In Case Paging page, Total Count needed
	 * @param workPmWorkListForm
	 * @param user
	 * @return
	 */
	public String findTotalCount(WorkPmWorkListForm workPmWorkListForm, User user);
}