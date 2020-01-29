package dream.work.list.service;

import common.bean.User;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultPartDetailDTO;

/**
 * 작업결과 투입자재 상세
 * @author  kim210117
 * @version $Id: MaWoResultPartDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface MaWoResultPartDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaWoResultPartDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param wkOrId
     * @param woPartId
     * @param compNo
     * @return
     * @throws Exception
     */
    public MaWoResultPartDetailDTO findDetail(String wkOrId, String woPartId,User user)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaWoResultPartDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultPartDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaWoResultPartDetailDTO maWoResultPartDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,User user) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaWoResultPartDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultPartDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaWoResultPartDetailDTO maWoResultPartDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) throws Exception;

    /**
     * 재고확인
     */
    public String getStockQty(MaWoResultPartDetailDTO maWoResultPartDetailDTO, User loginUser);
    public String checkQty(MaWoResultPartDetailDTO maWoResultPartDetailDTO, User loginUser);
    public int insertIssPartDetail(MaWoResultPartDetailDTO maWoResultPartDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) throws Exception;
    public int updateEmgPart(MaWoResultPartDetailDTO maWoResultPartDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) throws Exception;
    public int insertPtIssDetail(MaWoResultPartDetailDTO maWoResultPartDetailDTO, User user) throws Exception;
}
