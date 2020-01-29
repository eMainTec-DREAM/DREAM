package dream.work.list.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultToolDetailDTO;

/**
 * �۾����-���԰��ⱸ �� dao
 * @author  kim21017
 * @version $Id: MaWoResultToolDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface MaWoResultToolDetailDAO extends BaseJdbcDaoSupportIntf
{
	//��ȸ
    public MaWoResultToolDetailDTO findDetail(String wkOrId, String woToolId, User user);

    //����
    public int updateDetail(MaWoResultToolDetailDTO maWoResultToolDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user);
    
    //����
    public int insertDetail(MaWoResultToolDetailDTO maWoResultToolDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO);

    //�����ȸ
    public String getStockQty(MaWoResultToolDetailDTO maWoResultToolDetailDTO, User loginUser);

}