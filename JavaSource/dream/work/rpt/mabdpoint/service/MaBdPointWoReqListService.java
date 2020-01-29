package dream.work.rpt.mabdpoint.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.mabdpoint.dto.MaBdPointCommonDTO;
import dream.work.rpt.mabdpoint.dto.MaBdPointWoReqListDTO;

/**
 * 이상점검조치 - 작업요청 목록 service
 * @author  syyang
 * @version $Id:$
 * @since   1.0
 */
public interface MaBdPointWoReqListService
{
    /**
     *  grid find
     * @author  syyang
     * @version $Id:$
     * @param maBdPointWoReqListDTO
     * @since   1.0
     *
     * @return 조회 결과
     * @throws Exception
     */
    public List findList(MaBdPointCommonDTO maBdPointCommonDTO, MaBdPointWoReqListDTO maBdPointWoReqListDTO, User user);

    /**
     * 삭제
     * @author  syyang
     * @version $Id:$
     * @since   1.0
     *
     * @param deleteRows
     * @param compNo
     * @return
     */
    public int deleteList(String[] deleteRows, String compNo);

    public int insertWoNgPointRes(MaBdPointCommonDTO maBdPointCommonDTO, MaBdPointWoReqListDTO maBdPointWoReqListDTO, User user) throws Exception;
	public void linkWoReq(MaBdPointCommonDTO maBdPointCommonDTO, MaBdPointWoReqListDTO maBdPointWoReqListDTO, User user) throws Exception;
	
    public String findTotalCount(MaBdPointCommonDTO maBdPointCommonDTO, MaBdPointWoReqListDTO maBdPointWoReqListDTO, User user) throws Exception;

}
