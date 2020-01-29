package mobile.dream.mapm.mains.service;

import java.util.List;

import common.bean.User;
import mobile.dream.mapm.mains.dto.MaPmInsCommonDTO;
import mobile.dream.mapm.mains.form.MaPmInsListForm;

/**
 * 예방점검 - 목록 service
 * @author  jung7126
 * @version $Id: MaPmInsListService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
 * @since   1.0
 */
public interface MaPmInsListService
{     
    /**
     *  grid find
     * @author  jung7126
     * @version $Id: MaPmInsListService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @param maPmInsCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findList(MaPmInsCommonDTO maPmInsCommonDTO, User user);

	/**
	 * Find Total Count
	 * @param maPmInsListForm
	 * @param user
	 * @return
	 */
	public String findTotalCount(MaPmInsListForm maPmInsListForm, User user);

}
