package dream.mgr.lang.dao;

import java.util.List;

import common.bean.User;
import dream.mgr.lang.dto.MaResCommonDTO;

/**
 * �ٱ�����ȸ޴� - �ٱ�����ȸ޴� dao
 * @author  euna0207
 * @version $Id:$
 * @since   1.0
 */
public interface MgrLangPgBtnDAO
{
    
    public List findList(MaResCommonDTO maResCommonDTO, User user);
    public String findTotalCount(MaResCommonDTO maResCommonDTO, User user) throws Exception;

}