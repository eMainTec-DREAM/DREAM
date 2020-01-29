package mobile.dream.mapm.mains.dao;

import java.util.List;

import common.bean.User;
import mobile.dream.mapm.mains.dto.MaPmInsCommonDTO;
import mobile.dream.mapm.mains.form.MaPmInsListForm;

/**
 * 예방점검 - 목록 dao
 * @author  jung7126
 * @version $Id: MaPmInsListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
 * @since   1.0
 */
public interface MaPmInsListDAO
{
    /**
     * grid find
     * @author  jung7126
     * @version $Id: MaPmInsListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
     * @since   1.0
     * 
     * @param maPmInsCommonDTO
     * @return List
     */
    public List findList(MaPmInsCommonDTO maPmInsCommonDTO, User user);

	public String findTotalCount(MaPmInsListForm maPmInsListForm, User user);
}