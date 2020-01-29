package dream.work.list.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultToolListDTO;

/**
 * �۾���� ���԰��ⱸ ��� dao
 * @author  kim21017
 * @version $Id: MaWoResultToolListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaWoResultToolListDAO extends BaseJdbcDaoSupportIntf
{
	//��ȸ
    public List findToolList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultToolListDTO maWoResultToolListDTO, User loginUser);
    //����
    public int deleteToolList(String id, String compNo);
	//�Ǽ���ȸ
    public String findTotalCount(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultToolListDTO maWoResultToolListDTO, User user) throws Exception;
}