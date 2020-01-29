package dream.work.rpt.mabdpoint.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanDetailDTO;
import dream.work.list.service.WoPlanDetailService;
import dream.work.rpt.mabdpoint.dao.MaBdPointWoPlanListDAO;
import dream.work.rpt.mabdpoint.dto.MaBdPointCommonDTO;
import dream.work.rpt.mabdpoint.dto.MaBdPointWoPlanListDTO;
import dream.work.rpt.mabdpoint.service.MaBdPointWoPlanListService;

/**
 * 이상점검조치 - 작업계획 목록 serviceimpl
 * @author syyang
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maBdPointWoPlanListServiceTarget"
 * @spring.txbn id="maBdPointWoPlanListService"
 * @spring.property name="maBdPointWoPlanListDAO" ref="maBdPointWoPlanListDAO"
 * @spring.property name="woPlanDetailService" ref="woPlanDetailService"
 */
public class MaBdPointWoPlanListServiceImpl implements MaBdPointWoPlanListService
{
    private MaBdPointWoPlanListDAO maBdPointWoPlanListDAO	= null;
    private WoPlanDetailService woPlanDetailService			= null;
    

	public MaBdPointWoPlanListDAO getMaBdPointWoPlanListDAO() {
		return maBdPointWoPlanListDAO;
	}

	public void setMaBdPointWoPlanListDAO(MaBdPointWoPlanListDAO maBdPointWoPlanListDAO) {
		this.maBdPointWoPlanListDAO = maBdPointWoPlanListDAO;
	}

	public WoPlanDetailService getWoPlanDetailService() {
		return woPlanDetailService;
	}

	public void setWoPlanDetailService(WoPlanDetailService woPlanDetailService) {
		this.woPlanDetailService = woPlanDetailService;
	}

	public List findList(MaBdPointCommonDTO maBdPointCommonDTO,MaBdPointWoPlanListDTO reqWoPlanRsltListDTO, User user)
    {      
        return maBdPointWoPlanListDAO.findList(maBdPointCommonDTO,reqWoPlanRsltListDTO,user);
    }

	
    public int deleteList(String[] deleteRows, String compNo)
    {
        int result = 0;
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maBdPointWoPlanListDAO.deleteList(id,compNo);
            }
        return result;
    }

    @Override
	public String findTotalCount(MaBdPointCommonDTO maBdPointCommonDTO, MaBdPointWoPlanListDTO maBdPointWoPlanListDTO, User user)
			throws Exception {
		return maBdPointWoPlanListDAO.findTotalCount(maBdPointCommonDTO, maBdPointWoPlanListDTO, user);
	}

	@Override
	public void linkWoPlan(MaBdPointCommonDTO maBdPointCommonDTO, MaBdPointWoPlanListDTO maBdPointWoPlanListDTO, User user) throws Exception 
	{
		String[] multiKey = maBdPointWoPlanListDTO.getMultiKey().split("\\+");
		
		WoPlanCommonDTO woPlanCommonDTO = new WoPlanCommonDTO();
		
		for (int i = 0; i < multiKey.length; i++)
		{
			woPlanCommonDTO.setWkOrId(multiKey[i]);
			woPlanCommonDTO.setCompNo(user.getCompNo());
			woPlanCommonDTO.setUserLang(user.getLangId());
			// 조회된 상세 부분
			WoPlanDetailDTO woPlanDetailDTO = woPlanDetailService.findDetail(woPlanCommonDTO, user);

			maBdPointWoPlanListDTO.setWkorId(multiKey[i]);
			String cnt = this.validWkorId(maBdPointCommonDTO, maBdPointWoPlanListDTO, user);
			
			if("0".equals(cnt))
			{
				maBdPointWoPlanListDTO.setWoNgPointResId(maBdPointWoPlanListDAO.getNextSequence("SQAWONGPOINTRES_ID"));
				maBdPointWoPlanListDAO.insertWoNgPointRes(maBdPointCommonDTO, maBdPointWoPlanListDTO, user);
				
				if("PPC".equals(woPlanDetailDTO.getWoPlanStatusId()))
				{
					this.updateWoPlanPmPointRepStatus(maBdPointCommonDTO, maBdPointWoPlanListDTO, user);
				}
			}
		}
	}
	
	public String validWkorId(MaBdPointCommonDTO maBdPointCommonDTO, MaBdPointWoPlanListDTO maBdPointWoPlanListDTO, User user) throws Exception 
	{
		return maBdPointWoPlanListDAO.validWkorId(maBdPointCommonDTO, maBdPointWoPlanListDTO, user);
	}

	public int updateWoPlanPmPointRepStatus(MaBdPointCommonDTO maBdPointCommonDTO, MaBdPointWoPlanListDTO maBdPointWoPlanListDTO, User user) throws Exception 
	{
		return maBdPointWoPlanListDAO.updateWoPlanPmPointRepStatus(maBdPointCommonDTO, maBdPointWoPlanListDTO, user);
	}

	@Override
	public int insertWoNgPointRes(MaBdPointCommonDTO maBdPointCommonDTO, MaBdPointWoPlanListDTO maBdPointWoPlanListDTO, User user) throws Exception
	{
		maBdPointWoPlanListDTO.setWoNgPointResId(maBdPointWoPlanListDAO.getNextSequence("SQAWONGPOINTRES_ID"));
		return maBdPointWoPlanListDAO.insertWoNgPointRes(maBdPointCommonDTO, maBdPointWoPlanListDTO, user);
	}
	
	
}