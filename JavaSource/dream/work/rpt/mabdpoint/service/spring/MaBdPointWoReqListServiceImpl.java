package dream.work.rpt.mabdpoint.service.spring;

import java.util.List;

import common.bean.User;
import dream.req.work.dto.ReqWorkCommonDTO;
import dream.req.work.dto.ReqWorkDetailDTO;
import dream.req.work.service.ReqWorkDetailService;
import dream.work.rpt.mabdpoint.dao.MaBdPointWoReqListDAO;
import dream.work.rpt.mabdpoint.dto.MaBdPointCommonDTO;
import dream.work.rpt.mabdpoint.dto.MaBdPointWoReqListDTO;
import dream.work.rpt.mabdpoint.service.MaBdPointWoReqListService;

/**
 * 이상점검조치 - 작업요청 목록 serviceimpl
 * @author syyang
 * @version $Id:$
 * @since 1.0
 *
 * @spring.bean id="maBdPointWoReqListServiceTarget"
 * @spring.txbn id="maBdPointWoReqListService"
 * @spring.property name="maBdPointWoReqListDAO" ref="maBdPointWoReqListDAO"
 * @spring.property name="reqWorkDetailService" ref="reqWorkDetailService"
 */
public class MaBdPointWoReqListServiceImpl implements MaBdPointWoReqListService
{
    private MaBdPointWoReqListDAO maBdPointWoReqListDAO = null;
    
    private ReqWorkDetailService reqWorkDetailService = null;
    

    public MaBdPointWoReqListDAO getMaBdPointWoReqListDAO() {
		return maBdPointWoReqListDAO;
	}
	public void setMaBdPointWoReqListDAO(MaBdPointWoReqListDAO maBdPointWoReqListDAO) {
		this.maBdPointWoReqListDAO = maBdPointWoReqListDAO;
	}
	public ReqWorkDetailService getReqWorkDetailService() {
		return reqWorkDetailService;
	}
	public void setReqWorkDetailService(ReqWorkDetailService reqWorkDetailService) {
		this.reqWorkDetailService = reqWorkDetailService;
	}

	public List findList(MaBdPointCommonDTO maBdPointCommonDTO, MaBdPointWoReqListDTO maBdPointWoReqListDTO, User user)
    {
        return maBdPointWoReqListDAO.findList(maBdPointCommonDTO, maBdPointWoReqListDTO,user);
    }

    public int deleteList(String[] deleteRows, String compNo)
    {
        int result = 0;
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maBdPointWoReqListDAO.deleteList(id,compNo);
            }
        return result;
    }

	@Override
	public String findTotalCount(MaBdPointCommonDTO maBdPointCommonDTO, MaBdPointWoReqListDTO maBdPointWoReqListDTO, User user) throws Exception {
		return maBdPointWoReqListDAO.findTotalCount(maBdPointCommonDTO, maBdPointWoReqListDTO, user);
	}

	@Override
	public void linkWoReq(MaBdPointCommonDTO maBdPointCommonDTO, MaBdPointWoReqListDTO maBdPointWoReqListDTO, User user) throws Exception
	{
		String[] multiKey = maBdPointWoReqListDTO.getMultiKey().split("\\+");

		ReqWorkCommonDTO reqWorkCommonDTO = new ReqWorkCommonDTO();
		
		for (int i = 0; i < multiKey.length; i++)
		{
			reqWorkCommonDTO.setWoReqId(multiKey[i]);
			// 조회된 상세 부분
			ReqWorkDetailDTO reqWorkDetailDTO = reqWorkDetailService.findDetail(reqWorkCommonDTO, user);

			maBdPointWoReqListDTO.setWoReqId(multiKey[i]);
			
			String cnt = this.validWoReqId(maBdPointCommonDTO, maBdPointWoReqListDTO, user);
			
			if("0".equals(cnt))
			{
				maBdPointWoReqListDTO.setWoNgPointResId(maBdPointWoReqListDAO.getNextSequence("SQAWONGPOINTRES_ID"));
				maBdPointWoReqListDAO.insertWoNgPointRes(maBdPointCommonDTO, maBdPointWoReqListDTO, user);

				if("COM".equals(reqWorkDetailDTO.getWoReqStatusId()))
				{
					this.updateWoReqPmPointRepStatus(maBdPointCommonDTO, maBdPointWoReqListDTO, user);
				}
			}
		}
	}
	
	public String validWoReqId(MaBdPointCommonDTO maBdPointCommonDTO, MaBdPointWoReqListDTO maBdPointWoReqListDTO, User user) throws Exception 
	{
		return maBdPointWoReqListDAO.validWoReqId(maBdPointCommonDTO, maBdPointWoReqListDTO, user);
	}
	
	public int updateWoReqPmPointRepStatus(MaBdPointCommonDTO maBdPointCommonDTO, MaBdPointWoReqListDTO maBdPointWoReqListDTO, User user) throws Exception 
	{
		return maBdPointWoReqListDAO.updateWoReqPmPointRepStatus(maBdPointCommonDTO, maBdPointWoReqListDTO, user);
	}

	@Override
	public int insertWoNgPointRes(MaBdPointCommonDTO maBdPointCommonDTO, MaBdPointWoReqListDTO maBdPointWoReqListDTO, User user) throws Exception
	{
		maBdPointWoReqListDTO.setWoNgPointResId(maBdPointWoReqListDAO.getNextSequence("SQAWONGPOINTRES_ID"));
		return maBdPointWoReqListDAO.insertWoNgPointRes(maBdPointCommonDTO, maBdPointWoReqListDTO, user);
	}
	


}