package dream.work.list.service;

import common.bean.User;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultToolDetailDTO;

/**
 * 작업결과 투입공기고 상세
 * @author  kim210117
 * @version $Id: MaWoResultToolDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface MaWoResultToolDetailService
{    
	//상세조회
    public MaWoResultToolDetailDTO findDetail(String wkOrId, String woToolId,User user)throws Exception;
    //수정
    public int updateDetail(MaWoResultToolDetailDTO maWoResultToolDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,User user) throws Exception;
    //생성
    public int insertDetail(MaWoResultToolDetailDTO maWoResultToolDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) throws Exception;
    //현재고조회
    public String getStockQty(MaWoResultToolDetailDTO maWoResultToolDetailDTO, User loginUser);
}
