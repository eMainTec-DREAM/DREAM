package dream.work.rpt.mabdpoint.service.spring;

import java.util.List;

import common.bean.User;
import common.util.CommonUtil;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultMstrDetailDTO;
import dream.work.list.service.MaWoResultMstrDetailService;
import dream.work.rpt.mabdpoint.dao.MaBdPointWoRsltListDAO;
import dream.work.rpt.mabdpoint.dto.MaBdPointCommonDTO;
import dream.work.rpt.mabdpoint.dto.MaBdPointDetailDTO;
import dream.work.rpt.mabdpoint.dto.MaBdPointWoRsltListDTO;
import dream.work.rpt.mabdpoint.service.MaBdPointDetailService;
import dream.work.rpt.mabdpoint.service.MaBdPointWoRsltListService;

/**
 * 이상점검조치 - 작업결과 목록 serviceimpl
 * @author syyang
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maBdPointWoRsltListServiceTarget"
 * @spring.txbn id="maBdPointWoRsltListService"
 * @spring.property name="maBdPointWoRsltListDAO" ref="maBdPointWoRsltListDAO"
 * @spring.property name="maWoResultMstrDetailService" ref="maWoResultMstrDetailService"
 */
public class MaBdPointWoRsltListServiceImpl implements MaBdPointWoRsltListService
{
    private MaBdPointWoRsltListDAO maBdPointWoRsltListDAO			= null;
    
    private MaWoResultMstrDetailService maWoResultMstrDetailService = null;
    

	public MaBdPointWoRsltListDAO getMaBdPointWoRsltListDAO() {
		return maBdPointWoRsltListDAO;
	}
	public void setMaBdPointWoRsltListDAO(MaBdPointWoRsltListDAO maBdPointWoRsltListDAO) {
		this.maBdPointWoRsltListDAO = maBdPointWoRsltListDAO;
	}
	public MaWoResultMstrDetailService getMaWoResultMstrDetailService() {
		return maWoResultMstrDetailService;
	}
	public void setMaWoResultMstrDetailService(MaWoResultMstrDetailService maWoResultMstrDetailService) {
		this.maWoResultMstrDetailService = maWoResultMstrDetailService;
	}

	
	public List findList(MaBdPointCommonDTO maBdPointCommonDTO,MaBdPointWoRsltListDTO maBdPointWoRsltListDTO, User user)
    {      
        return maBdPointWoRsltListDAO.findList(maBdPointCommonDTO,maBdPointWoRsltListDTO,user);
    }

    public int deleteList(String[] deleteRows, String compNo)
    {
        int result = 0;
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maBdPointWoRsltListDAO.deleteList(id,compNo);
            }
        return result;
    }
	
	@Override
	public String findTotalCount(MaBdPointCommonDTO maBdPointCommonDTO,MaBdPointWoRsltListDTO maBdPointWoRsltListDTO, User user) throws Exception
	{
		return maBdPointWoRsltListDAO.findTotalCount(maBdPointCommonDTO, maBdPointWoRsltListDTO, user);
	}


	@Override
	public void linkWo(MaBdPointCommonDTO maBdPointCommonDTO, MaBdPointWoRsltListDTO maBdPointWoRsltListDTO, User user) throws Exception
	{
		String[] multiKey = maBdPointWoRsltListDTO.getMultiKey().split("\\+");
		
		MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
		//MaBdPointDetailDTO maBdPointDetailDTO = new MaBdPointDetailDTO();
		
		for (int i = 0; i < multiKey.length; i++)
		{
			maWoResultMstrCommonDTO.setWkOrId(multiKey[i]);
	    	maWoResultMstrCommonDTO.setCompNo(user.getCompNo());
	    	maWoResultMstrCommonDTO.setUserLang(user.getLangId());
	        // 조회된 상세 부분
	      //  MaWoResultMstrDetailDTO maWoResultMstrDetailDTO = maWoResultMstrDetailService.findDetail(maWoResultMstrCommonDTO);
	      
			maBdPointWoRsltListDTO.setWkorId(multiKey[i]);
			String cnt = this.validWkorId(maBdPointCommonDTO, maBdPointWoRsltListDTO, user);
			
			if("0".equals(cnt))
			{
				//maBdPointWoRsltListDTO.setWoNgPointResId(maBdPointWoRsltListDAO.getNextSequence("SQAWONGPOINTRES_ID"));
				insertWoNgPointRes(maBdPointCommonDTO, maBdPointWoRsltListDTO, user);

				/*if("C".equals(maWoResultMstrDetailDTO.getWoStatusId())||"PRC".equals(maWoResultMstrDetailDTO.getWoStatusId()))
				{
					this.updateWoRsltPmPointRepStatus(maBdPointCommonDTO, maBdPointWoRsltListDTO, user);
				} else {
					maBdPointDetailDTO.setPmPointRepStatus("BD");
				}*/
			}
			
			// 이상점검조치의 조치자, 조치일자, 조치부서 update
			/*MaBdPointDetailService maBdPointDetailService = (MaBdPointDetailService) CommonUtil.getBean("maBdPointDetailService", user);
			maBdPointDetailDTO.setRepairBy(maWoResultMstrDetailDTO.getEmpId());
			maBdPointDetailDTO.setRepairDept(maWoResultMstrDetailDTO.getDeptId());
			maBdPointDetailDTO.setRepairDate(maWoResultMstrDetailDTO.getWkorDate());
			maBdPointDetailDTO.setWoNgPointId(maBdPointCommonDTO.getWoNgPointId());
			maBdPointDetailDTO.setCompNo(user.getCompNo());

			maBdPointDetailService.updateDetail(maBdPointDetailDTO);*/
		}
	}
	
	public String validWkorId(MaBdPointCommonDTO maBdPointCommonDTO,MaBdPointWoRsltListDTO maBdPointWoRsltListDTO, User user) throws Exception 
	{
		return maBdPointWoRsltListDAO.validWkorId(maBdPointCommonDTO, maBdPointWoRsltListDTO, user);
	}

	public int updateWoRsltPmPointRepStatus(MaBdPointCommonDTO maBdPointCommonDTO,MaBdPointWoRsltListDTO maBdPointWoRsltListDTO, User user) throws Exception 
	{
		return maBdPointWoRsltListDAO.updateWoRsltPmPointRepStatus(maBdPointCommonDTO, maBdPointWoRsltListDTO, user);
	}
	
	public int insertWoNgPointRes(MaBdPointCommonDTO maBdPointCommonDTO,MaBdPointWoRsltListDTO maBdPointWoRsltListDTO, User user) throws Exception 
	{
		maBdPointWoRsltListDTO.setWoNgPointResId(maBdPointWoRsltListDAO.getNextSequence("SQAWONGPOINTRES_ID"));
		MaBdPointDetailDTO maBdPointDetailDTO = new MaBdPointDetailDTO();
		MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
		maWoResultMstrCommonDTO.setWkOrId(maBdPointWoRsltListDTO.getWkorId());
		maWoResultMstrCommonDTO.setCompNo(user.getCompNo());
		
		MaWoResultMstrDetailDTO maWoResultMstrDetailDTO = maWoResultMstrDetailService.findDetail(maWoResultMstrCommonDTO);
		
		if("C".equals(maWoResultMstrDetailDTO.getWoStatusId())||"PRC".equals(maWoResultMstrDetailDTO.getWoStatusId()))
		{
			this.updateWoRsltPmPointRepStatus(maBdPointCommonDTO, maBdPointWoRsltListDTO, user);
		} else {
			maBdPointDetailDTO.setPmPointRepStatus("BD");
		}
		
		// 이상점검조치의 조치자, 조치일자, 조치부서 update
		MaBdPointDetailService maBdPointDetailService = (MaBdPointDetailService) CommonUtil.getBean("maBdPointDetailService", user);

		maBdPointDetailDTO.setRepairBy(maWoResultMstrDetailDTO.getEmpId());
		maBdPointDetailDTO.setRepairDept(maWoResultMstrDetailDTO.getDeptId());
		maBdPointDetailDTO.setRepairDate(maWoResultMstrDetailDTO.getWkorDate());
		maBdPointDetailDTO.setRepairDesc(maWoResultMstrDetailDTO.getPerform());
		maBdPointDetailDTO.setWoNgPointId(maBdPointCommonDTO.getWoNgPointId());
		
		maBdPointDetailDTO.setCompNo(user.getCompNo());
		
		maBdPointDetailService.updateDetail(maBdPointDetailDTO);
		
		return maBdPointWoRsltListDAO.insertWoNgPointRes(maBdPointCommonDTO, maBdPointWoRsltListDTO, user);
	}
}