package dream.work.list.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultToolDetailDTO;

/**
 * 작업결과-투입공기구 상세 dao
 * @author  kim21017
 * @version $Id: MaWoResultToolDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface MaWoResultToolDetailDAO extends BaseJdbcDaoSupportIntf
{
	//조회
    public MaWoResultToolDetailDTO findDetail(String wkOrId, String woToolId, User user);

    //수정
    public int updateDetail(MaWoResultToolDetailDTO maWoResultToolDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user);
    
    //생성
    public int insertDetail(MaWoResultToolDetailDTO maWoResultToolDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO);

    //재고조회
    public String getStockQty(MaWoResultToolDetailDTO maWoResultToolDetailDTO, User loginUser);

}